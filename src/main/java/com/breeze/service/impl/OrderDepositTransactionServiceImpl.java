package com.breeze.service.impl;

import com.breeze.pojo.bo.AmountBO;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.breeze.mapper.OrderDepositTransactionMapper;
import com.breeze.pojo.OrderDepositTransaction;
import com.breeze.service.IOrderDepositTransactionService;
/**
 * @author Created by wdnnccey
 * @date 2019-07-09 09:17
 * @Description: 一句话描述该类的功能
 */
@Service
public class OrderDepositTransactionServiceImpl implements IOrderDepositTransactionService {

    @Resource
    private OrderDepositTransactionMapper orderDepositTransactionMapper;

    @Override
    public int deleteById(Long id) {
        return orderDepositTransactionMapper.deleteById(id);
    }

    @Override
    public int add(OrderDepositTransaction record) {
        return orderDepositTransactionMapper.add(record);
    }

    @Override
    public OrderDepositTransaction findById(Long id) {
        return orderDepositTransactionMapper.findById(id);
    }

    @Override
    public int update(OrderDepositTransaction record) {
        return orderDepositTransactionMapper.update(record);
    }

    @Override
    public AmountBO findByOrderId(Long orderId) {
        return orderDepositTransactionMapper.findByOrderId(orderId);
    }

}
