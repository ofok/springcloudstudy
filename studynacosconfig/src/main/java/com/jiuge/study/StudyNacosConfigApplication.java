package com.jiuge.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class StudyNacosConfigApplication {

    public static void main(String[] args) throws InterruptedException {

        ConfigurableApplicationContext applicationContext = SpringApplication.run(StudyNacosConfigApplication.class, args);
        while (true) {
            String userName = applicationContext.getEnvironment().getProperty("common.name");
            String userAge = applicationContext.getEnvironment().getProperty("common.age");
            String ddd = applicationContext.getEnvironment().getProperty("common.type");
            String eee = applicationContext.getEnvironment().getProperty("common.name");

            System.err.println("common name :" + userName + "; age: " + userAge + ", type=" + ddd + " ; eee:=" + eee);
            TimeUnit.SECONDS.sleep(1);
        }
    }

}
