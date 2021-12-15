package com.study.openfeign.controller;

import com.study.openfeign.client.CloudAnalyseClient;
import com.study.openfeign.client.OrderClient;
import com.study.openfeign.client.TraceClient;
import com.study.openfeign.param.ApplyNotifyParam;
import com.study.openfeign.param.GoodsSearchParam;
import com.study.openfeign.param.TokenParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
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

    @Autowired
    private CloudAnalyseClient analyseClient;

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

    @GetMapping("/apply/callback")
    public String codeApplyCallback() {
        String serialNo = "123";
        String sourceCode = "3F3180EC04FD4153AB465F00C82AC701";
        MultiValueMap<String, String> tokenMap = new LinkedMultiValueMap<>();
        long timestamp1 = System.currentTimeMillis();
        String reqStr = "serialNo=123&sourceCode=3F3180EC04FD4153AB465F00C82AC701&version=1.0&salt=qw&timestamp="+ timestamp1;
        String md5Str = DigestUtils.md5DigestAsHex(reqStr.getBytes(StandardCharsets.UTF_8));
        tokenMap.add("signature", md5Str);
        tokenMap.add("timestamp", timestamp1 + "");
        String result = analyseClient.tokenApply(sourceCode, serialNo, tokenMap);


//        ApplyNotifyParam notifyParam = new ApplyNotifyParam();
//        notifyParam.setApplyId(123L);
//        notifyParam.setFileUrl("");
//        notifyParam.setSerialNo("123");
//        notifyParam.setSourceCode("3F3180EC04FD4153AB465F00C82AC701");
//        notifyParam.setTicket("");
//        MultiValueMap<String, String> notifyMap = new LinkedMultiValueMap<>();
//        long timestamp2 = System.currentTimeMillis();
//        String reqStr2 = "serialNo=123&salt=qw&ticket=123a&timestamp=1584713993";
//        notifyMap.add("signature", "");
//        notifyMap.add("timestamp", timestamp2 + "");
//        String notifyRes = analyseClient.applyNotify(notifyParam, tokenMap);

        return result;
    }

}
