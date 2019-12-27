package com.breeze.service.impl;

import com.breeze.pojo.bo.AmountBO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.breeze.mapper.OrderPaymentTransactionMapper;
import com.breeze.pojo.OrderPaymentTransaction;
import com.breeze.service.IOrderPaymentTransactionService;
/**
 * @author Created by wdnnccey
 * @date 2019-07-09 09:17
 * @Description: 一句话描述该类的功能
 */
@Service
public class OrderPaymentTransactionServiceImpl implements IOrderPaymentTransactionService {

    @Resource
    private OrderPaymentTransactionMapper orderPaymentTransactionMapper;

    @Override
    public int deleteById(Long id) {
        return orderPaymentTransactionMapper.deleteById(id);
    }

    @Override
    public int add(OrderPaymentTransaction record) {
        return orderPaymentTransactionMapper.add(record);
    }


    @Override
    public OrderPaymentTransaction findById(Long id) {
        return orderPaymentTransactionMapper.findById(id);
    }

    @Override
    public int update(OrderPaymentTransaction record) {
        return orderPaymentTransactionMapper.update(record);
    }

    @Override
    public AmountBO findByOrderId(Long orderId) {
        return orderPaymentTransactionMapper.findByOrderId(orderId);
    }

}
