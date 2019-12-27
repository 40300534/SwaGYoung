package com.breeze.service;

import com.breeze.pojo.OrderPaymentTransaction;
import com.breeze.pojo.bo.AmountBO;

/**
 * @author Created by wdnnccey
 * @date 2019-07-09 09:17
 * @Description: 一句话描述该类的功能
 */
public interface IOrderPaymentTransactionService {

    int deleteById(Long id);

    int add(OrderPaymentTransaction record);

    OrderPaymentTransaction findById(Long id);

    int update(OrderPaymentTransaction record);

    AmountBO findByOrderId(Long orderId);
}
