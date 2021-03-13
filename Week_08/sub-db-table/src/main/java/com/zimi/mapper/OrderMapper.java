package com.zimi.mapper;


import com.zimi.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;


@Mapper
public interface OrderMapper {

    /**
     * 批量插入
     *
     */
    int insertForeach(List<Order> list);

    /**
     * 获取所有订单
     */
    List<Order> selectAll();

    void updateByOrderId(Order order);

}