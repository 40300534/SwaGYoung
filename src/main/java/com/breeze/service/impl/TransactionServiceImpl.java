package com.breeze.service.impl;

import com.breeze.mapper.ProductBidMapper;
import com.breeze.mapper.TransactionMapper;
import com.breeze.pojo.ProductBid;
import com.breeze.pojo.Transaction;
import com.breeze.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;


/**
 * @author Created by wdnnccey
 * @date 2019-07-03 16:26
 */
@Service
public class TransactionServiceImpl implements ITransactionService {

    @Resource
    private TransactionMapper transcationMapper;

    @Autowired
    private ProductBidMapper productBidMapper;

    @Override
    public int deleteById(Long id) {
        return transcationMapper.deleteById(id);
    }

    @Override
    public int add(Transaction record) {
        return transcationMapper.add(record);
    }

    @Override
    public Transaction findById(Long id) {
        return transcationMapper.findById(id);
    }

    @Override
    public int update(Transaction record) {
        return transcationMapper.update(record);
    }

    @Override
    public Transaction findByOutTradeNo(Long outTradeNo) {
        return transcationMapper.findByOutTradeNo(outTradeNo);
    }

    @Override
    public BigDecimal findCountAmountByOutTradeNo(List<Long> outTradeNo) {
        return transcationMapper.findCountAmountByOutTradeNo(outTradeNo);
    }
}
