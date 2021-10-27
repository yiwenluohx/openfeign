package com.study.openfeign.controller;

import com.study.openfeign.client.OrderClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/feignOkHttp")
    public String feignOkHttp(){
        String result = orderClient.getOrderById("2");
        return result;
    }

}
