package com.study.openfeign.client;

import com.study.openfeign.param.ApplyNotifyParam;
import com.study.openfeign.param.TokenParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: CloudAnalyseClient
 * Description: 象云析回调接口测试
 * Author: luohx
 * Date: 2021/12/14 上午11:00
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0           象云析回调接口测试
 */
@FeignClient(name = "cloud-analyse-serve", url = "${default.url}")
public interface CloudAnalyseClient {

    @PostMapping(value = "/integration/tickettoken/get")
    String tokenApply(@RequestParam("sourceCode") String sourceCode,  @RequestParam("serialNo") String serialNo, @RequestHeader MultiValueMap<String, String> headers);

    @PostMapping(value = "/integration/codeapply/notify")
    String applyNotify(@RequestBody ApplyNotifyParam notifyParam, @RequestHeader MultiValueMap<String, String> headers);
}
