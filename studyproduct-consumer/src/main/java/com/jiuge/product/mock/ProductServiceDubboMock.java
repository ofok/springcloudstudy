package com.jiuge.product.mock;

import com.product.entity.ProductEntity;
import com.product.service.ProductService;

import java.util.List;

public class ProductServiceDubboMock implements ProductService {
    @Override
    public List<ProductEntity> list() {
        return null;
    }

    @Override
    public ProductEntity findById(Integer id) {
        return new ProductEntity(0, "====mock===");
    }

}
