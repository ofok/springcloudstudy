package com.jiuge.order.controller;

import com.jiuge.common.utils.MyPageUtils;
import com.jiuge.common.utils.R;
import com.jiuge.order.entity.OrderEntity;
import com.jiuge.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;


/**
 * @author fox
 * @email 2763800211@qq.com
 * @date 2021-01-28 15:46:19
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 根据用户id查询订单信息
     *
     * @param userId
     * @return
     */
    @RequestMapping("/findOrderByUserId/{userId}")
    public R findOrderByUserId(@PathVariable("userId") Integer userId) {

//        try {
//            Thread.sleep(8000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        //模拟异常
//        if(userId==5){
//            throw new IllegalArgumentException("非法参数异常");
//        }

        log.info("根据userId:" + userId + "查询订单信息");
        List<OrderEntity> orderEntities = orderService.listByUserId(userId);
        return R.ok().put("orders", orderEntities);
    }

    /**
     * 测试gateway
     * @param request
     * @return
     * @throws Exception
     */
//    @GetMapping("/testgateway")
//    public String testGateway(HttpServletRequest request) throws Exception {
//        log.info("gateWay获取请求头X-Request-color："
//                +request.getHeader("X-Request-color"));
//        return "success";
//    }
//    @GetMapping("/testgateway2")
//    public String testGateway(@RequestHeader("X-Request-color") String color) throws Exception {
//        log.info("gateWay获取请求头X-Request-color："+color);
//        return "success";
//    }
//    @GetMapping("/testgateway3")
//    public String testGateway3(@RequestParam("color") String color) throws Exception {
//        log.info("gateWay获取请求参数color:"+color);
//        return "success";
//    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        MyPageUtils page = orderService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        OrderEntity order = orderService.getById(id);
        return R.ok().put("order", order);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody OrderEntity order) {
        orderService.save(order);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody OrderEntity order) {
        orderService.updateById(order);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        orderService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
