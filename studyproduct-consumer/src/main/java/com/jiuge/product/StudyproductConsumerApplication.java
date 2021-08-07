package com.jiuge.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StudyproductConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyproductConsumerApplication.class, args);
    }

}
