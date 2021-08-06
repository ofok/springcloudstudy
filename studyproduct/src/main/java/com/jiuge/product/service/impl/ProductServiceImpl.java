package com.jiuge.product.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jiuge.product.dao.ProductDao;

import com.product.entity.ProductEntity;
import com.product.service.ProductService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author huwei
 * @version 1.0
 * @date 2021/8/6 10:29
 */
@DubboService
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Override
    public List<ProductEntity> list() {
        return productDao.selectDataList();
    }

    @Override
    public ProductEntity findById(Integer id) {
        return productDao.findById(id);
    }
}
