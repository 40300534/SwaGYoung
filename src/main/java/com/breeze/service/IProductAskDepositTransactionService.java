package com.breeze.service;

import com.breeze.pojo.ProductAskDepositTransaction;
import com.breeze.mapper.ProductAskDepositTransactionMapper;

/**
 * @author Created by wdnnccey
 * @date 2019-07-09 09:18
 * @Description: 一句话描述该类的功能
 */
public interface IProductAskDepositTransactionService {

    int deleteById(Long id);

    int add(ProductAskDepositTransaction record);

    ProductAskDepositTransaction findById(Long id);

    int update(ProductAskDepositTransaction record);

    /**
     * 通过ProductAskId查询TransactionId
     * @param productAskId
     */
    ProductAskDepositTransaction findTranscationIdByProductAskId(Long productAskId);



}
