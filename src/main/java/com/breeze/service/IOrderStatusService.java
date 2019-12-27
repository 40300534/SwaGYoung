package com.breeze.service;

import com.breeze.pojo.OrderStatus;

/**
 * @author Created by wdnnccey
 * @date 2019-07-03 15:43
 */
public interface IOrderStatusService {


    int deleteById(Integer id);

    int add(OrderStatus record);

    OrderStatus findById(Integer id);

    int update(OrderStatus record);

}
