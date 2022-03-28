package com.study.openfeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

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
