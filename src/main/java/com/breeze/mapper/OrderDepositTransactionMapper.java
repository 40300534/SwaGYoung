package com.breeze.mapper;

import com.breeze.pojo.OrderDepositTransaction;
import com.breeze.pojo.bo.AmountBO;

/**
 * @author Created by wdnnccey
 * @date 2019-07-09 09:17
 */
public interface OrderDepositTransactionMapper {


    int deleteById(Long id);

    int add(OrderDepositTransaction record);

    OrderDepositTransaction findById(Long id);

    int update(OrderDepositTransaction record);

    /**
     * 押金
     * 实付金额  押金+付款金额
     * @param orderId
     * @return
     */
     AmountBO findByOrderId(Long orderId);
}
