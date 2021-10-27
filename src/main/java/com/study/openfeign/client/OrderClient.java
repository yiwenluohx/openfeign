package com.study.openfeign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: luohx
 * @Description:
 * @Date: 2021/10/26 下午4:13
 */
@FeignClient(name = "order-serve", url = "http://localhost:8762/mall-order")
public interface OrderClient {
    @RequestMapping(value = "/order/getOrderById", method = RequestMethod.GET)
    String getOrderById(@RequestParam("id") String id);
}
