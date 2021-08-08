package com.jiuge.product.controller;


import com.alibaba.cloud.dubbo.annotation.DubboTransported;
import com.alibaba.csp.sentinel.adapter.dubbo.config.DubboAdapterGlobalConfig;
import com.jiuge.product.service.ProductDubboFeignService;
import com.jiuge.product.service.ProductFeignService;
import com.product.entity.ProductEntity;
import com.product.service.ProductService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.checkerframework.common.reflection.qual.GetConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author huwei
 * @version 1.0
 * @date 2021/8/6 15:49
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @DubboReference(mock = "com.jiuge.product.mock.ProductServiceDubboMock")
    private ProductService productService;


    @GetMapping("/info/{id}")
    public ProductEntity info(@PathVariable("id") Integer id) {

        return productService.findById(id);
    }

    @PostConstruct
    public void init() {
        DubboAdapterGlobalConfig.setConsumerFallback(
                (invoker, invocation, ex) -> AsyncRpcResult.newDefaultAsyncResult(
                        new ProductEntity(0,"===fallback=="), invocation));
    }

    @GetMapping("/list")
    public List<ProductEntity> list() {

        return productService.list();
    }




    @Autowired
    @DubboTransported(protocol = "dubbo")
    /**
     * 思考dubbo协议是否生效？  Spring 单例模式   不生效
     */
    private ProductFeignService productFeignService;

    @RequestMapping("/list1")
    public List<ProductEntity> list1() {
        return productFeignService.list();
    }


    /************Openfeign 转成 dubboFeign **************/
    @Autowired
    private ProductDubboFeignService productDubboFeignService;

    @GetMapping("/list2")
    public List<ProductEntity> list2() {
        return productDubboFeignService.list();
    }

    @GetMapping("/findById2/{id}")
    public ProductEntity findById2(@PathVariable("id") Integer id) {
        return productDubboFeignService.findById(id);
    }


    @Autowired
    private RestTemplate restTemplate;

    @Bean
    @LoadBalanced
    @DubboTransported
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @RequestMapping("/list3")
    public List<ProductEntity> list3() {
        String url = "http://springcloud-dubbo-provider-product/product/list";
        return restTemplate.getForObject(url, List.class);
    }
}
