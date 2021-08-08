package com.jiuge.user.config;


import com.alibaba.cloud.sentinel.annotation.SentinelRestTemplate;
import com.jiuge.user.util.GlobalExceptionUtil;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author jiuge
 */
@Configuration
public class RestConfig {

    @Bean
    @LoadBalanced
    @SentinelRestTemplate(
            blockHandler = "handlerException",blockHandlerClass = GlobalExceptionUtil.class,
            fallback = "fallback",fallbackClass = GlobalExceptionUtil.class)
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    WebClient webClient() {
        return WebClient.builder().build();
    }

}
