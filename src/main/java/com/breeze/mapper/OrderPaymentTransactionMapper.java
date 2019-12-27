package com.breeze.mapper;

import com.breeze.pojo.OrderPaymentTransaction;
import com.breeze.pojo.bo.AmountBO;

/**
 * @author Created by wdnnccey
 * @date 2019-07-09 09:17
 * @Description: 一句话描述该类的功能
 */
public interface OrderPaymentTransactionMapper {

    int deleteById(Long id);

    int add(OrderPaymentTransaction record);

    OrderPaymentTransaction findById(Long id);

    int update(OrderPaymentTransaction record);

    /**
     * 付款金额
     * 实付金额  押金+付款金额
     * @param orderId
     * @return
     */
    AmountBO findByOrderId(Long orderId);
}