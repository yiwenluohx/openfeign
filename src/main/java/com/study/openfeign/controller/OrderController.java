package com.study.openfeign.controller;

import com.alibaba.fastjson.JSON;
import com.study.openfeign.client.CloudAnalyseClient;
import com.study.openfeign.client.OrderClient;
import com.study.openfeign.client.TraceClient;
import com.study.openfeign.dto.BaseResult;
import com.study.openfeign.dto.TokenApplyResult;
import com.study.openfeign.param.ApplyNotifyParam;
import com.study.openfeign.param.EntregisterNotifyParam;
import com.study.openfeign.param.GoodsSearchParam;
import com.study.openfeign.param.TokenParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
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
//        String result = orderClient.getOrderById("2");
        MultiValueMap<String, String> tokenMap = new LinkedMultiValueMap<>();
        tokenMap.add("grant_type", "openId");
        tokenMap.add("openId", "cdb9ac63efac90680e0954df203ece4ed24cd5ec");
        String result = analyseClient.getToken(tokenMap, "d2ViQXBwOndlYkFwcA==");
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
        String reqStr = "serialNo=123&sourceCode=3F3180EC04FD4153AB465F00C82AC701&version=1.0&salt=qw&timestamp=" + timestamp1;
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

    @GetMapping("/apply/callback1")
    public String codeApplyCallback1() throws Exception {
        String resStr = "";

        String url = "http://api.ygyg.dev1/qatrace/integration/tickettoken/get";
//        url = "http://localhost:8080/integration/tickettoken/get";
        String serialNo = "123";
        String sourceCode = "3F3180EC04FD4153AB465F00C82AC701";
        long timestamp1 = System.currentTimeMillis();
        String reqStr = "serialNo=123&sourceCode=3F3180EC04FD4153AB465F00C82AC701&version=1.0&salt=qw&timestamp=" + timestamp1;
        String md5Str = DigestUtils.md5DigestAsHex(reqStr.getBytes(StandardCharsets.UTF_8));
        HttpHeaders headers = getHeader("application/x-www-form-urlencoded");
        headers.set("signature", md5Str);
        headers.set("timestamp", timestamp1 + "");
        //发起post远程调用请求
        String tokenStr = restTemplate.postForEntity(url + "?sourceCode=" + sourceCode + "&serialNo=" + serialNo, new HttpEntity<>("", headers), String.class).getBody();
        BaseResult baseResult = JSON.parseObject(tokenStr, BaseResult.class);
        if (baseResult != null && baseResult.getSuccess()) {
            TokenApplyResult applyResult = JSON.parseObject(baseResult.getData().toString(), TokenApplyResult.class);

            ApplyNotifyParam notifyParam = new ApplyNotifyParam();
            notifyParam.setApplyId("20211217-10049-00006");
            notifyParam.setFileUrl("https://lfrz1.stor.enncloud.cn/ennew-dev/public/qT8I30Ep8LClHsA9/industria-security-platform/20211217-10049-00006_16397318751639731875271.xls");
            notifyParam.setSerialNo("123");
            notifyParam.setSourceCode("3F3180EC04FD4153AB465F00C82AC701");
            notifyParam.setTicket(applyResult.getTicket());
            //发起post远程调用请求
            String applyUrl = "http://api.ygyg.dev1/qatrace/integration/codeapply/notify";
//            applyUrl = "http://localhost:8080/integration/codeapply/notify";
            long timestamp2 = System.currentTimeMillis();
            String reqStr2 = "serialNo=123&salt=qw&ticket=" + applyResult.getTicket() + "&timestamp=" + timestamp2;
            HttpHeaders header = getHeader("");
            String md5Str1 = DigestUtils.md5DigestAsHex(reqStr2.getBytes(StandardCharsets.UTF_8));
            header.set("signature", md5Str1);
            header.set("timestamp", timestamp2 + "");
            resStr = restTemplate.postForEntity(applyUrl, new HttpEntity<>(JSON.toJSON(notifyParam), header), String.class).getBody();
        }

        return resStr;
    }

    @GetMapping("/apply/callback2")
    public String codeApplyCallback2() throws Exception {
        String resStr = "";

        String url = "http://api.ygyg.dev1/qatrace/integration/tickettoken/get";
        url = "http://localhost:8080/integration/tickettoken/get";
        String serialNo = "123";
        String sourceCode = "3F3180EC04FD4153AB465F00C82AC701";
        long timestamp1 = System.currentTimeMillis();
        String reqStr = "serialNo=123&sourceCode=3F3180EC04FD4153AB465F00C82AC701&version=1.0&salt=qw&timestamp=" + timestamp1;
        String md5Str = DigestUtils.md5DigestAsHex(reqStr.getBytes(StandardCharsets.UTF_8));
        HttpHeaders headers = getHeader("application/x-www-form-urlencoded");
        headers.set("signature", md5Str);
        headers.set("timestamp", timestamp1 + "");
        //发起post远程调用请求
        String tokenStr = restTemplate.postForEntity(url + "?sourceCode=" + sourceCode + "&serialNo=" + serialNo, new HttpEntity<>("", headers), String.class).getBody();
        BaseResult baseResult = JSON.parseObject(tokenStr, BaseResult.class);
        if (baseResult != null && baseResult.getSuccess()) {
            TokenApplyResult applyResult = JSON.parseObject(baseResult.getData().toString(), TokenApplyResult.class);

            EntregisterNotifyParam notifyParam = new EntregisterNotifyParam();

            notifyParam.setEnterpriseId("1120");
            notifyParam.setOpenId("1234");
            notifyParam.setRuleId("2");
            notifyParam.setRuleTemp("");
            notifyParam.setSerialNo("123");
            notifyParam.setSourceCode("3F3180EC04FD4153AB465F00C82AC701");
            notifyParam.setTicket(applyResult.getTicket());
            //发起post远程调用请求
            String applyUrl = "http://api.ygyg.dev1/qatrace/integration/entregister/notify";
            applyUrl = "http://localhost:8080/integration/entregister/notify";
            long timestamp2 = System.currentTimeMillis();
            String reqStr2 = "serialNo=123&salt=qw&ticket=" + applyResult.getTicket() + "&timestamp=" + timestamp2;
            HttpHeaders header = getHeader("");
            String md5Str1 = DigestUtils.md5DigestAsHex(reqStr2.getBytes(StandardCharsets.UTF_8));
            header.set("signature", md5Str1);
            header.set("timestamp", timestamp2 + "");
            resStr = restTemplate.postForEntity(applyUrl, new HttpEntity<>(JSON.toJSON(notifyParam), header), String.class).getBody();
        }

        return resStr;

    }


    /**
     * 设置请求头
     *
     * @return
     * @throws Exception
     */
    private HttpHeaders getHeader(String mediaType) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType(!StringUtils.isEmpty(mediaType) ? mediaType : "application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());

        return headers;
    }

}
