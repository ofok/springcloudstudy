package com.product.service;

import com.product.entity.ProductEntity;

import java.util.List;

/**
 * @author huwei
 * @version 1.0
 * @date 2021/8/6 16:18
 */
public interface ProductService {
    /**
     * 产品信息集合
     *
     * @return
     */
    List<ProductEntity> list();

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    ProductEntity findById(Integer id);

}
