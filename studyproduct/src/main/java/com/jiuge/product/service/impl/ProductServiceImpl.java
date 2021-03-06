package com.jiuge.product.service.impl;


import com.alibaba.csp.sentinel.adapter.dubbo.config.DubboAdapterGlobalConfig;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.jiuge.product.dao.ProductDao;
import com.product.entity.ProductEntity;
import com.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.AsyncRpcResult;
import org.checkerframework.common.reflection.qual.GetConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author huwei
 * @version 1.0
 * @date 2021/8/6 10:29
 */
@DubboService
@Slf4j
@RestController
@RequestMapping("/product")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    @GetMapping("/list")
    public List<ProductEntity> list() {
        return productDao.selectDataList();
    }

    @Override
    @GetMapping("/findById/{id}")
    @SentinelResource("findById")
    public ProductEntity findById(@PathVariable("id") Integer id) {
        return productDao.findById(id);
    }



    @PostConstruct
    public void init() {
        DubboAdapterGlobalConfig.setProviderFallback(
                (invoker, invocation, ex) -> AsyncRpcResult.newDefaultAsyncResult(new ProductEntity(0,"===provider fallback=="), invocation));
    }
}
