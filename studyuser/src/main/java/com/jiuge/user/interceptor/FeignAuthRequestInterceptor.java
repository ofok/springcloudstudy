package com.jiuge.user.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.UUID;

/**
 * @author huwei
 * @version 1.0
 * @date 2021/8/5 16:24
 */
public class FeignAuthRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate template) {
        String access_token = UUID.randomUUID().toString();
        template.header("Authorization", access_token);
    }
}
