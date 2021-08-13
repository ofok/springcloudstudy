package com.jiuge.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@SpringBootTest
class StudyuserApplicationTests {

    @Test
    void contextLoads() {
        ZonedDateTime zonedDateTime = ZonedDateTime.now();//默认时区
        // 用指定时区获取当前时间
        ZonedDateTime zonedDateTime2 = ZonedDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(zonedDateTime2);
    }

}
