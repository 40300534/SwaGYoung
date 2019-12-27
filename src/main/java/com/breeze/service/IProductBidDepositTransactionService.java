package com.breeze.service;

import com.breeze.pojo.ProductBidDepositTransaction;

/**
 * @author Created by wdnnccey
 * @date 2019-07-09 09:18
 * @Description: 一句话描述该类的功能
 */
public interface IProductBidDepositTransactionService {

    int deleteById(Long id);

    int add(ProductBidDepositTransaction record);

    ProductBidDepositTransaction findById(Long id);

    int update(ProductBidDepositTransaction record);

    ProductBidDepositTransaction findByProductBidId(Long productBidId);
}
