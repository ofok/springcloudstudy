package com.jiuge.user.controller;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.jiuge.common.utils.MyPageUtils;
import com.jiuge.common.utils.R;
import com.jiuge.user.entity.UserEntity;
import com.jiuge.user.feign.OrderFeignService;
import com.jiuge.user.service.UserService;
import com.jiuge.user.util.CommonBlockHandler;
import com.jiuge.user.util.CommonFallback;
import com.jiuge.user.util.MyBlockExceptionHandler;
import com.jiuge.user.util.MyExceptionUtil;
import com.sun.xml.internal.ws.handler.HandlerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Map;

/**
 * @author fox
 * @email 2763800211@qq.com
 * @date 2021-01-28 15:53:24
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderFeignService orderFeignService;

    @Autowired
    private WebClient webClient;


    @RequestMapping(value = "/findOrderByUserId/{id}")
    @SentinelResource(
            value = "findOrderByUserId", fallback = "fallback", fallbackClass = CommonFallback.class, blockHandler = "handlerException", blockHandlerClass = CommonBlockHandler.class
    )
    public Mono<R> findOrderByUserId(@PathVariable("id") Integer id) {
        log.info("根据userId:" + id + "查询订单信息");
        // RestTemplate调用
        String url = "http://localhost:8020/order/findOrderByUserId/" + id;
        // R result = restTemplate.getForObject(url,R.class);

        // 模拟ribbon实现
        // String url = getUri("mall-order")+"/order/findOrderByUserId/"+id;
        // 添加@LoadBalanced
        // String url = "http://order-server/order/findOrderByUserId/" + id;
        // R result = restTemplate.getForObject(url, R.class);

        Mono<R> result = webClient.get().uri(url).retrieve().bodyToMono(R.class);


        if (id == 4) {
            throw new HandlerException("非法参数异常");
        }
        // feign调用
        // R result = orderFeignService.findOrderByUserId(id);


        return result;
    }

    @GetMapping("/findOrderByUserIdForRestTemplate/{id}")
    public R findOrderByUserIdForRestTemplate(@PathVariable("id")Integer id){
        String url = "http://order-server/order/findOrderByUserId/" + id;
        R result = restTemplate.getForObject(url, R.class);
        return result;
    }

    @GetMapping("/findOrderByUserIdForOpenFeign/{id}")
    public R findOrderByUserIdForOpenFeign(@PathVariable("id")Integer id){
        //feign调用
        R result = orderFeignService.findOrderByUserId(id);
        return result;
    }

    @GetMapping("/findOrderByUserIdWithFeign/{id}")
    public R findOrderByUserIdWithFeign(@PathVariable("id") Integer id) {

        // feign调用
        R result = orderFeignService.findOrderByUserId(id);
        return result;
    }

    @Autowired
    private ReactorLoadBalancerExchangeFilterFunction lbfilter;

    @GetMapping(value = "/findOrderByUserIdWithWebFlux/{id}")
    public Mono<R> findOrderByUserIdWithWebFlux(@PathVariable("id") Integer id) {
        String url = "http://order-server/order/findOrderByUserId/" + id;
        // 基于WebClient + webFlux
        Mono<R> result = WebClient.builder().filter(lbfilter).build().get().uri(url).retrieve().bodyToMono(R.class);
        return result;
    }


//    @Autowired
//    private DiscoveryClient discoveryClient;
//    public String getUri(String serviceName) {
//        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serviceName);
//        if (serviceInstances == null || serviceInstances.isEmpty()) {
//            return null;
//        }
//        int serviceSize = serviceInstances.size();
//        //轮询
//        int indexServer = incrementAndGetModulo(serviceSize);
//        return serviceInstances.get(indexServer).getUri().toString();
//    }
//    private AtomicInteger nextIndex = new AtomicInteger(0);
//    private int incrementAndGetModulo(int modulo) {
//        for (;;) {
//            int current = nextIndex.get();
//            int next = (current + 1) % modulo;
//            if (nextIndex.compareAndSet(current, next) && current < modulo){
//                return current;
//            }
//        }
//    }
//

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        MyPageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        UserEntity user = userService.getById(id);

        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UserEntity user) {
        userService.save(user);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserEntity user) {
        userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
