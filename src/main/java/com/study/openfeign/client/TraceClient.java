package com.study.openfeign.client;

import com.study.openfeign.param.GoodsSearchParam;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName: TraceClient
 * Description: 一码追溯接口feign调用
 *
 * @Author: luohx
 * Date: 2021/12/13 下午3:29
 * History:
 * <author>          <time>          <version>          <desc>
 * luohx            修改时间           1.0          一码追溯接口feign调用
 */
@FeignClient(name = "trace-serve", url = "${qatrace.url}")
public interface TraceClient {

    /**
     * 查询商品信息
     *
     * @param goodsParam
     * @param token
     * @return
     */
    @RequestMapping(value = "/pack/goods/search", method = RequestMethod.POST)
//    @Headers({"Content-Type: application/json;charset=UTF-8", "Authorization: {token}"})
    String packGoodsSearch(@RequestBody GoodsSearchParam goodsParam, @RequestHeader("Authorization") String token);

    /**
     * 包装规格信息
     *
     * @param goodsCode
     * @param token
     * @return
     */
    @RequestMapping(value = "/pack/info/get", method = RequestMethod.POST)
    String packInfo(@RequestParam("goodsCode") String goodsCode, @RequestHeader("Authorization") String token);


//    @RequestLine("GET /simple/{id}")
//    @Headers({"Content-Type: application/json;charset=UTF-8", "Authorization: {token}"})
//    String findById(@Param("id") String id, @Param("token") String token);

    /**
     * 置多个header属性
     *
     * @param condition
     * @param headers
     * @return
     */
    @PostMapping(value = "/card")
    String createCard(@RequestBody GoodsSearchParam condition, @RequestHeader MultiValueMap<String, String> headers);

}
