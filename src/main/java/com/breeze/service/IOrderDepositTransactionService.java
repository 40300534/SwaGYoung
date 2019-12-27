package com.breeze.service;

import com.breeze.pojo.OrderDepositTransaction;
import com.breeze.pojo.bo.AmountBO;

/**
 * @author Created by wdnnccey
 * @date 2019-07-09 09:17
 * @Description: 一句话描述该类的功能
 */
public interface IOrderDepositTransactionService {

    int deleteById(Long id);

    int add(OrderDepositTransaction record);

    OrderDepositTransaction findById(Long id);

    int update(OrderDepositTransaction record);

    AmountBO findByOrderId(Long orderId);
}
