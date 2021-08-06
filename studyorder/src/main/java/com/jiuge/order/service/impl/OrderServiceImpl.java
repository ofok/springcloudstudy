package com.jiuge.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jiuge.common.utils.MyPageUtils;
import com.jiuge.common.utils.Query;
import com.jiuge.order.dao.OrderDao;
import com.jiuge.order.entity.OrderEntity;
import com.jiuge.order.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Override
    public MyPageUtils queryPage(Map<String, Object> params) {
        IPage<OrderEntity> page = this.page(
                new Query<OrderEntity>().getPage(params),
                new QueryWrapper<OrderEntity>()
        );
        return new MyPageUtils(page);
    }

    @Override
    public List<OrderEntity> listByUserId(Integer userId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id", userId);
        return baseMapper.selectList(queryWrapper);
    }

}
