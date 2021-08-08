package com.jiuge.user.feign;

import com.jiuge.common.utils.R;
import com.jiuge.user.feign.impl.FallbackOrderFeignServiceFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author jiuge
 * @version 1.0
 * @date 2021/8/5 10:56
 */
// feignConfig 的局部配置
@FeignClient(value = "order-server", path = "order", fallbackFactory = FallbackOrderFeignServiceFactory.class)
public interface OrderFeignService {
    @RequestMapping("/findOrderByUserId/{id}")
        // @RequestLine("GET /findOrderByUserId/{id}")
    R findOrderByUserId(@PathVariable("id") Integer id);
}
