package com.zimi.service;

import com.zimi.entity.Order;

import java.util.List;


public interface OrderService {

    /**
     * 获取所有订单信息
     */
    List<Order>  list();

    /**
     *   批量 保存订单信息
     *
     */
    String  insertForeach(List<Order> orderVOList);

    void updateByOrderId(Order order);
}