package com.jiuge.user.config;

import com.jiuge.user.interceptor.FeignAuthRequestInterceptor;
import feign.Contract;
import feign.Logger;
import feign.Request;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;

/**
 * @author huwei
 * @version 1.0
 * @date 2021/8/5 11:16
 */
@Configuration
public class FeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }


    /**
     * 修改契约配置，支持Feign原生的注解
     * @return
     */
//    @Bean
//    public Contract feignContract() {
//        return new Contract.Default();
//    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor(){
        return new BasicAuthRequestInterceptor("jiuge","123456");
    }

    /**
     * 自定义拦截器
     * @return
     */
    @Bean
    public FeignAuthRequestInterceptor feignAuthRequestInterceptor(){
        return new FeignAuthRequestInterceptor();
    }

    /**
     * 超时时间配置
     */

    @Bean
    public Request.Options options() {
        return new Request.Options(5000, 10000);
    }

    @Bean
    public Decoder decoder() {
        return new JacksonDecoder();
    }
    @Bean
    public Encoder encoder() {
        return new JacksonEncoder();
    }
}
