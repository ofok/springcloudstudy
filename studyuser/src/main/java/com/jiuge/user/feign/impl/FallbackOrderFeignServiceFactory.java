package com.jiuge.user.feign.impl;

import com.jiuge.common.utils.R;
import com.jiuge.user.feign.OrderFeignService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class FallbackOrderFeignServiceFactory implements FallbackFactory<OrderFeignService> {
    @Override
    public OrderFeignService create(Throwable throwable) {
        return new OrderFeignService() {
            @Override
            public R findOrderByUserId(Integer id) {
                return R.error(-1, "===========服务降级了==========");
            }
        };
    }
}
