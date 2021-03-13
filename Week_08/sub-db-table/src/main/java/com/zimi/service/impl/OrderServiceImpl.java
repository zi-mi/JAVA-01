package com.zimi.service.impl;

import com.zimi.entity.Order;
import com.zimi.mapper.OrderMapper;
import com.zimi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;



@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public  List<Order> list() {
        List<Order> orders = orderMapper.selectAll();
        return orders;
    }

    @Override
    public String insertForeach(List<Order> orderList) {
        for (Order order : orderList) {
            order.setCreateTime(new Date());
            order.setUpdateTime(new Date());
            order.setStatus(1);
        }
        //批量插入数据
        orderMapper.insertForeach(orderList);
        return "保存成功";
    }

    @Override
    public void updateByOrderId(Order order){
        orderMapper.updateByOrderId(order);
    }
}
