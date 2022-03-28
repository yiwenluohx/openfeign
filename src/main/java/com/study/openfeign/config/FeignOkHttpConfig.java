package com.study.openfeign.config;

import feign.Feign;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.commons.httpclient.OkHttpClientFactory;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.support.FeignHttpClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * @Author: luohx
 * @Description: 将feign默认hd修改为okhttp配置
 * @Date: 2021/10/26 下午3:03
 */
@Configuration
public class FeignOkHttpConfig {
    /**
     * OkHttpInterceptor
     *
     * @return
     */
    @Bean
    public OkHttpInterceptor okHttpInterceptor() {
        return new OkHttpInterceptor();
    }

    @Bean
    public OkHttpClientDynamicTimeoutInterceptor dynamicTimeoutInterceptor() {
        return new OkHttpClientDynamicTimeoutInterceptor();
    }

    /**
     * 注入okhttp
     *
     * @param okHttpClientFactory
     * @param httpClientProperties
     * @return
     */
    @Bean
    @ConditionalOnProperty({"feign.okhttp.enabled"})
    public okhttp3.OkHttpClient okHttpClient(OkHttpClientFactory okHttpClientFactory, FeignHttpClientProperties httpClientProperties) throws Exception {
//        okhttp3.OkHttpClient.Builder builder = okHttpClientFactory.createBuilder(httpClientProperties.isDisableSslValidation())
//                .connectTimeout(httpClientProperties.getConnectionTimeout(), TimeUnit.SECONDS)
//                .followRedirects(httpClientProperties.isFollowRedirects())
//                .connectionPool(new ConnectionPool(5, 10, TimeUnit.MINUTES))
//                .addInterceptor(okHttpInterceptor())
//                .addInterceptor(dynamicTimeoutInterceptor());

//        try {
//            final TrustManager[] trustAllCerts = new TrustManager[]{
//                    new X509TrustManager() {
//                        @Override
//                        public void checkClientTrusted(X509Certificate[] chain, String authType) {
//                        }
//
//                        @Override
//                        public void checkServerTrusted(X509Certificate[] chain, String authType) {
//                        }
//
//                        @Override
//                        public X509Certificate[] getAcceptedIssuers() {
//                            return new X509Certificate[0];
//                        }
//                    }
//            };
//            final SSLContext sslContext = SSLContext.getInstance("SSL");
//            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
//            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
//            X509TrustManager trustAllCert = (X509TrustManager) trustAllCerts[0];
//            builder.sslSocketFactory(sslSocketFactory, trustAllCert);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        return builder.build();
        return okHttpClientFactory.createBuilder(httpClientProperties.isDisableSslValidation())
                .connectTimeout(httpClientProperties.getConnectionTimeout(), TimeUnit.SECONDS)
                .followRedirects(httpClientProperties.isFollowRedirects())
                .connectionPool(new ConnectionPool(5, 10, TimeUnit.MINUTES))
                .addInterceptor(okHttpInterceptor())
                .addInterceptor(dynamicTimeoutInterceptor()).build();
    }
}
