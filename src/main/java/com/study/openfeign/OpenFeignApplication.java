package com.study.openfeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

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
            list1.stream().anyMatch(j->{
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
}
