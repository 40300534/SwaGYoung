package com.breeze.service;

import com.breeze.pojo.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-03 16:26
 * @Description: 一句话描述该类的功能
 */
public interface ITransactionService {

    int deleteById(Long id);

    int add(Transaction record);

    Transaction findById(Long id);

    int update(Transaction record);

    Transaction findByOutTradeNo(Long outTradeNo);

    /**
     * 通过订单号查询所有缴纳过的费用（仅用来查询出家中的）
     * @param outTradeNo
     * @return
     */
    BigDecimal findCountAmountByOutTradeNo(List<Long> outTradeNo);
}
