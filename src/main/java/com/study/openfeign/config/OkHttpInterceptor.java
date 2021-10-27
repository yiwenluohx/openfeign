package com.study.openfeign.config;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.boot.ApplicationRunner;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @Author: luohx
 * @Description: OkHttp拦截器
 * @Date: 2021/10/26 下午4:02
 */
public class OkHttpInterceptor implements Interceptor {
    private static final Logger logger = LoggerFactory.getLogger(ApplicationRunner.class);
    private static final String TRACE_ID = "traceId";
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        //追加请求头
        String traceId = MDC.get(TRACE_ID);
        if (!StringUtils.isEmpty(traceId)) {
            request = chain.request().newBuilder().addHeader(TRACE_ID, traceId).build();
        }
        //请求发起的时间
        long t1 = System.currentTimeMillis();
        logger.info("【OkHttpLogInterceptor】{}------>开始时间:{}", request.url(), t1);
        Response response = chain.proceed(request);
        //收到响应的时间
        long t2 = System.currentTimeMillis();

        logger.info("【OkHttpLogInterceptor】{}------>结束时间:{},调用时长:{}ms",
                request.url(), t2, t2 - t1);
        return response;
    }
}
