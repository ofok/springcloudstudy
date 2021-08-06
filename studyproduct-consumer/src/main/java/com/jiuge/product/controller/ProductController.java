package com.jiuge.product.controller;


import com.product.entity.ProductEntity;
import com.product.service.ProductService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author huwei
 * @version 1.0
 * @date 2021/8/6 15:49
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @DubboReference
    private ProductService productService;


    @GetMapping("/info/{id}")
    public ProductEntity info(@PathVariable("id") Integer id){

        return productService.findById(id);
    }

    @GetMapping("/list")
    public List<ProductEntity> list(){

        return productService.list();
    }
}
