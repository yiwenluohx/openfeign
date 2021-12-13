package com.study.openfeign.controller;

import com.study.openfeign.client.OrderClient;
import com.study.openfeign.client.TraceClient;
import com.study.openfeign.param.GoodsSearchParam;
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

    @Autowired
    private TraceClient traceClient;

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
            backoff = @Backoff(delay = 5000L, multiplier = 2))
    public HttpStatus testRest() {
        System.out.println("发起远程API请求:" + DATE_TIME_FORMATTER.format(LocalDateTime.now()));
        String url = "http://jsonplaceholder.typicode.com/postss/1";
        ResponseEntity<String> responseEntity
                = restTemplate.getForEntity(url, String.class);
        return responseEntity.getStatusCode(); // 获取响应码
    }

    @GetMapping("/pack/info")
    public String packInfo() {
        String token = "Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxMDAxIiwicGxhdGZvcm1UeXBlIjoiV0VCIiwiZXhwIjoxNjM5NDEwMjEwLCJ0b2tlblR5cGUiOiJJRF9UT0tFTiIsImlhdCI6MTYzOTM4MTQxMCwianRpIjoiZWQ3ZmM0MDg5NWQzNGM4MjhjOTM4NTBmNDMxODk1ZjQifQ.GCKZpyjDQbKYcP7ioqz1T4feIciApSSiQ53XiHqKPlTSlWBICDW-SQbONydacTRRP4RMXBj_T8gvYyCVwUdoEA9dVal_t-5xN0Q39S0cOG52gUZTaqpwqkSVVY--5cxh8Yln63Aw0om-B_XrypIQW595pnJNn0IEMsiFcxhMY1Wuu5pIFDZyzieXXg7ufOihNEq-aE-XBitpltY_1lGXpOqPiPCz091GaF-27FcEWezNl5QSLNqOxtw4qikc7-3khCIWm0t3bzVwSK6JAFsXbLu0ixjrxH_c8yowY6NepwhcvwTAx6MpSd9TrKVE1G_SYgqj9h_fW3roD6wGdZC3PA";
        String result = traceClient.packInfo("002", token);
        return result;
    }

    @GetMapping("/goods/search")
    public String goodsSearch() {
        String token = "Bearer eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiIxMDAxIiwicGxhdGZvcm1UeXBlIjoiV0VCIiwiZXhwIjoxNjM5NDEwMjEwLCJ0b2tlblR5cGUiOiJJRF9UT0tFTiIsImlhdCI6MTYzOTM4MTQxMCwianRpIjoiZWQ3ZmM0MDg5NWQzNGM4MjhjOTM4NTBmNDMxODk1ZjQifQ.GCKZpyjDQbKYcP7ioqz1T4feIciApSSiQ53XiHqKPlTSlWBICDW-SQbONydacTRRP4RMXBj_T8gvYyCVwUdoEA9dVal_t-5xN0Q39S0cOG52gUZTaqpwqkSVVY--5cxh8Yln63Aw0om-B_XrypIQW595pnJNn0IEMsiFcxhMY1Wuu5pIFDZyzieXXg7ufOihNEq-aE-XBitpltY_1lGXpOqPiPCz091GaF-27FcEWezNl5QSLNqOxtw4qikc7-3khCIWm0t3bzVwSK6JAFsXbLu0ixjrxH_c8yowY6NepwhcvwTAx6MpSd9TrKVE1G_SYgqj9h_fW3roD6wGdZC3PA";
        GoodsSearchParam param = new GoodsSearchParam();
        param.setEid(1L);
        param.setPageSize(20);
        param.setPageNum(1);
        String result = traceClient.packGoodsSearch(param, token);
        return result;
    }

}
