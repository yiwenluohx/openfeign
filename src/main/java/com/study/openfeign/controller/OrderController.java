package com.study.openfeign.controller;

import com.study.openfeign.client.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: luohx
 * @Description: 测试openfeign-okhttp调用接口
 * @Date: 2021/10/26 下午4:16
 */
@RestController
@RequestMapping("/demo")
public class OrderController {

    @Autowired
    private OrderClient orderClient;

    @Resource
    private RestTemplate restTemplate;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @GetMapping("/feignOkHttp")
    public String feignOkHttp() {
        String result = orderClient.getOrderById("2");
        return result;
    }

    @GetMapping("/restTemplate")
    @Retryable(value = RestClientException.class, maxAttempts = 3,
            backoff = @Backoff(delay = 5000L,multiplier = 2))
    public HttpStatus testRest() {
        System.out.println("发起远程API请求:" + DATE_TIME_FORMATTER.format(LocalDateTime.now()));
        String url = "http://jsonplaceholder.typicode.com/postss/1";
        ResponseEntity<String> responseEntity
                = restTemplate.getForEntity(url, String.class);
        return responseEntity.getStatusCode(); // 获取响应码
    }

}
