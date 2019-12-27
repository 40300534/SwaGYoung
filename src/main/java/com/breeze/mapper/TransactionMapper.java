package com.breeze.mapper;

import java.math.BigDecimal;
import java.util.List;

import com.breeze.pojo.Transaction;

/**
 * @author Created by wdnnccey
 * @date 2019-07-03 16:26
 * @Description: 一句话描述该类的功能
 */
public interface TransactionMapper {

    int deleteById(Long id);

    int add(Transaction record);

    Transaction findById(Long id);

    int update(Transaction record);

    Transaction findByOutTradeNo(Long outTradeNo);

    BigDecimal findCountAmountByOutTradeNo(List<Long> outTradeNo);

}