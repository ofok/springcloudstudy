package com.jiuge.product.service;

import com.product.entity.ProductEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(value = "springcloud-dubbo-provider-product", path = "/product")
public interface ProductFeignService {
    @GetMapping("/list")
    List<ProductEntity> list();

    @GetMapping("/findById/{id}")
    ProductEntity findById(@PathVariable("id") Integer id);
}
