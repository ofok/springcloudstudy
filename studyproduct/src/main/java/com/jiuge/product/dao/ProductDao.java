package com.jiuge.product.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.product.entity.ProductEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author huwei
 * @version 1.0
 * @date 2021/8/6 15:29
 */
@Mapper
public interface ProductDao extends BaseMapper<ProductEntity> {
    @Select("select * from t_product ")
    List<ProductEntity> selectDataList();

    @Select("select * from t_product where id = #{id}")
    ProductEntity findById(@Param("id") Integer id);
}
