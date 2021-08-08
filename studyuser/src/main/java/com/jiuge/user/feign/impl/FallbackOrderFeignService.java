package com.jiuge.user.feign.impl;

import com.jiuge.common.utils.R;
import com.jiuge.user.feign.OrderFeignService;
import org.springframework.stereotype.Component;

@Component   //必须交给spring 管理
public class FallbackOrderFeignService implements OrderFeignService {
    @Override
    public R findOrderByUserId(Integer id) {
        return R.error(-1,"=======服务降级了========");
    }
}
