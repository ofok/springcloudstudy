package com.jiuge.order.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author fox
 * @email 2763800211@qq.com
 * @date 2021-01-28 15:46:19
 */
@Data
@TableName("t_order")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    private Integer id;
    /**
     *
     */
    private String userId;
    /**
     * 商品编号
     */
    private String commodityCode;
    /**
     *
     */
    private Integer count;
    /**
     *
     */
    private Integer amount;

}
