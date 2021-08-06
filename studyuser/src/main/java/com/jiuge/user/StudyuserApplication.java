package com.jiuge.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StudyuserApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudyuserApplication.class, args);
    }

}
