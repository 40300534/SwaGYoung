package com.breeze.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.breeze.mapper.OrderStatusMapper;
import com.breeze.pojo.OrderStatus;
import com.breeze.service.IOrderStatusService;
/**
 * @author Created by wdnnccey
 * @date 2019-07-03 15:43
 * @Description: 一句话描述该类的功能
 */
@Service
public class OrderStatusServiceImpl implements IOrderStatusService {

    @Resource
    private OrderStatusMapper orderStatusMapper;

    @Override
    public int deleteById(Integer id) {
        return orderStatusMapper.deleteById(id);
    }

    @Override
    public int add(OrderStatus record) {
        return orderStatusMapper.add(record);
    }


    @Override
    public OrderStatus findById(Integer id) {
        return orderStatusMapper.findById(id);
    }

    @Override
    public int update(OrderStatus record) {
        return orderStatusMapper.update(record);
    }

}
