package com.product.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author huwei
 * @version 1.0
 * @date 2021/8/6 16:23
 */
@Data
public class ProductEntity implements Serializable {
    private Integer id;

    private String productName;

    private String productType;

    private String productUnit;
}
