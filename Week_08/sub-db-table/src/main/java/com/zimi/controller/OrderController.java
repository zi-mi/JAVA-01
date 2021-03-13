package com.zimi.controller;

import com.google.common.collect.Lists;
import com.zimi.entity.Order;
import com.zimi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 模拟批量保存
     * @return
     */
    @RequestMapping("/save-order")
    public Object saveOrder(){

        List<Order> orderList = Lists.newArrayList();
        Order order = new Order();
        order.setOrderId(42109742899l);
        order.setUserId(12l);
        order.setPayment(1233333l);
        order.setPaymentType(1);
        order.setPostFee(232l);
        order.setBuyerAddress(13);

        Order order1 = new Order();
        order1.setOrderId(678261874206l);
        order1.setUserId(13l);
        order1.setPayment(1233333l);
        order1.setPaymentType(1);
        order1.setPostFee(232l);
        order1.setBuyerAddress(12);

        orderList.add(order);
        orderList.add(order1);

        return orderService.insertForeach(orderList);

    }

    /**
     * 模拟更新操作
     */
    @RequestMapping("/update-order")
    public void updateOrderById(){
        Order order = new Order();
        order.setOrderId(678261874206l);
        order.setBuyerNick("nick name");
        orderService.updateByOrderId(order);
    }

    /**
     * 查询订单
     * @return
     */
    @RequestMapping("/list-order")
    public Object listOrder(){

        return orderService.list();
    }

}
