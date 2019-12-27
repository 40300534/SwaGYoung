package com.breeze.mapper;

import com.breeze.pojo.OrderStatus;

/**
 * @author Created by wdnnccey
 * @date 2019-07-03 15:43
 * @Description: 订单状态
 */
public interface OrderStatusMapper {
    int deleteById(Integer id);

    int add(OrderStatus record);

    OrderStatus findById(Integer id);

    int update(OrderStatus record);
}