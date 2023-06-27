package com.study.openfeign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.study.openfeign.dto.ApiTokenDto;
import com.study.openfeign.dto.CloudAnalyseResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

import java.lang.annotation.Documented;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import java.util.stream.Collectors;

/**
 * @Author: luohx
 * @Description:
 * @Date: 2021/10/26 下午2:04
 */
@SpringBootApplication
@EnableFeignClients
/**
 * 让重试机制生效
 */
@EnableRetry
public class OpenFeignApplication {

    /**
     * 由于在controller中注入了RestTemplate，所以启动的时候需要实例化该类的一个实例
     */
    @Autowired
    private RestTemplateBuilder builder;

    public static void main(String[] args) {

        Integer tempN = 12;

        List<String> list11 = new ArrayList<>();
        list11.add("ACCOUNT1");
        list11.add("ACCOUNT2");
        list11.add("ACCOUNT3");
        list11.add("ACCOUNT4");
        list11.add("ACCOUNT5");

        List<String> list2 = new ArrayList<>();
        list2.add("ACC1");
        list2.add("ACCOUNT2");
        list2.add("ACCOUNT3");
        list2.add("ACCOUNT6");
        list2.add("ACCOUNT7");

        list11.removeAll(list2);

        System.out.println("list1最终值："+ JSON.toJSONString(list11));


        int[] prices = {7, 1, 5, 3, 6, 4};
        int maxprofit = 0;
        //困难


        //中度
        for (int i = 0; i < prices.length; i++) {
            if (i == 0) continue;
            int tmp = prices[i] - prices[i - 1];
            if (tmp > 0) {
                maxprofit += tmp;
            }
        }
        System.out.println("获取的最大利润max=" + maxprofit);

        //简单
        int minprice = Integer.MAX_VALUE;
        for (int i = 0; i < prices.length; i++) {
            if (minprice > prices[i]) {
                minprice = prices[i];
            } else if (maxprofit < prices[i] - minprice) {
                maxprofit = prices[i] - minprice;
            }
        }
        System.out.println("最大利润：" + maxprofit);

        CloudAnalyseResultDto<ApiTokenDto> dot = getDot();

        Map<Long, String> map = new HashMap<>();
        map.put(2L, "oooj");
        map.put(3L, "998k");
        List<Long> mapKeys = new ArrayList<>(map.keySet());
        System.out.println(JSON.toJSONString(mapKeys));

        Integer goodsNum = 20;
        String specStr = "1:2:6:24";
        String[] arr = specStr.split(":");

        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            //存储数组的前面变量
            String temp = arr[i];
            //替换数组内容
            arr[i] = arr[j];
            //对源数据进行覆盖更改，这函数是先前记录下来的
            arr[j] = temp;
        }


//        String spec = new StringBuffer(specStr).reverse().toString();

        int level = 3;
        List<Integer> levels = new ArrayList<>();
//        for (int i = 0; i <= level; i++) {
//            levels.add(i);
//        }
        for (int i = level; i >= 0; i--) {
            levels.add(i);
        }
//        String[] ratios = specStr.split(":");

        List<Integer> packs = new ArrayList<>();
        levels.stream().filter(k -> k != 0).map(x -> {
            String ratio = arr[arr.length - x - 1];

            Integer sp = Integer.parseInt(arr[0]) / Integer.parseInt(arr[arr.length - x - 1]);
            Double ceilPre = goodsNum / (Double.parseDouble(arr[0]) / Double.parseDouble(arr[arr.length - x]));
            Double packbox = Math.ceil(ceilPre);
            packs.add(packbox.intValue());
            return "";
        }).collect(Collectors.toList());

        Map<Integer, Integer> packMap = new HashMap<>();
        for (int i = 1; i <= level; i++) {
            packMap.put(i, packs.get(i - 1));
        }


        String kk = arr[arr.length - 2];
        int dd = Integer.parseInt(arr[arr.length - 1]) / Integer.parseInt(arr[arr.length - 3]);


        String res = "{\"datas\":[\"企业id为100027585的手机号已注册\"],\"resp_code\":1,\"resp_msg\":\"没有有效数据\"}";
        CloudAnalyseResultDto dto = JSON.parseObject(res, CloudAnalyseResultDto.class);
        System.out.println(dto.getDatas().toString());
        JSONArray dataRes = JSONArray.parseObject(dto.getDatas().toString(), JSONArray.class);
        System.out.println(dataRes.get(0));
        if (dataRes.getClass().isArray()) {
            String[] objArr = (String[]) dto.getDatas();
            System.out.println(objArr[0]);
        }


        String fileName = "RK220317A.3RLB-ZH-C.10-1.5米.CSV";
        fileName = "RK220317-灶具管3RLB-ZH-C-10-1500.CSV";
        String[] names = fileName.split("\\.");
        String prefixN = names[0], postfixN = ".cvs";
        if (names.length > 1) {
            postfixN = ".".concat(names[names.length - 1]);
            prefixN = fileName.substring(0, fileName.length() - postfixN.length());
        }

        fileName = "安全阀‘；。.;.0000.CSV";
        try {
            String downloadFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name()).replaceAll("\\+", "%20");
            String downloadFileName1 = new String(fileName.getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1);

            int i = 0;
        } catch (Exception ex) {

        }

        String str = ";;;;;.CSV";
        System.out.println(str);
        System.out.println(StringFilter(str));
//        for (int i = 0; i < 5; i++) {
//            outer:
//            for (int j = 0; j < 3; j++) {
//                if (j == 2) {
//                    break outer;
//                }
//                System.out.println(i + "==" + j);
//            }
//        }

        List<Integer> list = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
        }};

        List<Integer> list1 = new ArrayList<Integer>() {{
            add(1);
            add(2);
            add(3);
            add(4);
        }};

        list.forEach(k -> {
            list1.stream().anyMatch(j -> {
                System.out.println(k + "==" + j);
                return j == 3;
            });
        });


        SpringApplication.run(OpenFeignApplication.class, args);
    }

    private static CloudAnalyseResultDto<ApiTokenDto> getDot() {
        String jsonStr = "{\"datas\":{\"access_token\":\"50d89176-71f9-4c7d-a8eb-e032c28c5f10\",\"token_type\":\"bearer\",\"refresh_token\":\"e201e694-b501-4e73-a552-f9853647061c\",\"expires_in\":3309,\"scope\":\"all\"},\"resp_code\":0,\"resp_msg\":\"\"}";
        CloudAnalyseResultDto resDto = JSON.parseObject(jsonStr, CloudAnalyseResultDto.class);
        ApiTokenDto tokenDto = JSON.parseObject(resDto.getDatas().toString(), ApiTokenDto.class);


        resDto.setDatas(tokenDto);
        return resDto;
    }


    /**
     * 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return builder.build();
    }

    public static String StringFilter(String str) throws PatternSyntaxException {
        // 只允许字母和数字 // String regEx ="[^a-zA-Z0-9]";
        // 清除掉所有特殊字符
        String regEx = "[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        return m.replaceAll(".").trim();
    }
}
