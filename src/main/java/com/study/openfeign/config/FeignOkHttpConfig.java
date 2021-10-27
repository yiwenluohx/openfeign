package com.study.openfeign.config;

import feign.Feign;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.commons.httpclient.OkHttpClientFactory;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.cloud.openfeign.support.FeignHttpClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @Author: luohx
 * @Description: 将feign默认hd修改为okhttp配置
 * @Date: 2021/10/26 下午3:03
 */
@Configuration
@ConditionalOnClass(Feign.class)
@AutoConfigureBefore(FeignAutoConfiguration.class)
public class FeignOkHttpConfig {

    private OkHttpClient okHttpClient;

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
    public okhttp3.OkHttpClient okHttpClient(OkHttpClientFactory okHttpClientFactory, FeignHttpClientProperties httpClientProperties) {
        this.okHttpClient = okHttpClientFactory.createBuilder(httpClientProperties.isDisableSslValidation())
                .connectTimeout(httpClientProperties.getConnectionTimeout(), TimeUnit.SECONDS)
                .followRedirects(httpClientProperties.isFollowRedirects())
                .connectionPool(new ConnectionPool(5, 10, TimeUnit.MINUTES))
                .addInterceptor(okHttpInterceptor())
                .addInterceptor(dynamicTimeoutInterceptor())
                .build();
        return this.okHttpClient;
    }
}
