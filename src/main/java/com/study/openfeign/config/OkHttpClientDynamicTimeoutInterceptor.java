package com.study.openfeign.config;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.lang.reflect.Field;

/**
 * @Author: luohx
 * @Description: 由于某个接口耗时比较长，故需要对okHttpClient添加动态的超时时间，来满足业务场景
 * @Date: 2021/10/26 下午3:36
 */
public class OkHttpClientDynamicTimeoutInterceptor implements Interceptor {
    private static final int CONNECT_LONG_TIMEOUT = 20;
    private static final int CONNECT_SHORT_TIMEOUT = 10;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://baseurl").build();
        Request oldRequest = chain.request();
        setDynamicConnectTimeout(oldRequest, retrofit, "add you need dynamic interface");
        Request newRequest = oldRequest.newBuilder()
                .method(oldRequest.method(), oldRequest.body())
                .url(oldRequest.url())
                .build();
        return chain.proceed(newRequest);
    }

    /**
     * 根据所需接口、进行动态设置网络超时时间
     *
     * @param oldRequest
     * @param retrofit
     */
    private void setDynamicConnectTimeout(Request oldRequest, Retrofit retrofit, String url) {
        //动态设置超时时间
        final String questUrl = oldRequest.url().url().toString();
        try {
            //1、private final okhttp3.Call.Factory callFactory;   Retrofit 的源码 构造方法中
            Field callFactoryField = retrofit.getClass().getDeclaredField("callFactory");
            callFactoryField.setAccessible(true);
            //2、callFactory = new OkHttpClient();   Retrofit 的源码 build()中
            OkHttpClient client = (OkHttpClient) callFactoryField.get(retrofit);
            //3、OkHttpClient(Builder builder)     OkHttpClient 的源码 构造方法中
            Field connectTimeoutField = client.getClass().getDeclaredField("connectTimeout");
            connectTimeoutField.setAccessible(true);
            //4、根据所需要的时间进行动态设置超时时间
            if (questUrl.contains(url)) {
                connectTimeoutField.setInt(client, CONNECT_LONG_TIMEOUT * 1000);
            } else {
                connectTimeoutField.setInt(client, CONNECT_SHORT_TIMEOUT * 1000);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
