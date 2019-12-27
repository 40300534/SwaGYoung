package com.breeze.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.breeze.pojo.ProductBidDepositTransaction;
import com.breeze.mapper.ProductBidDepositTransactionMapper;
import com.breeze.service.IProductBidDepositTransactionService;
/**
 * @author Created by wdnnccey
 * @date 2019-07-09 09:18
 * @Description: 一句话描述该类的功能
 */
@Service
public class ProductBidDepositTransactionServiceImpl implements IProductBidDepositTransactionService {

    @Resource
    private ProductBidDepositTransactionMapper productBidDepositTransactionMapper;

    @Override
    public int deleteById(Long id) {
        return productBidDepositTransactionMapper.deleteById(id);
    }

    @Override
    public int add(ProductBidDepositTransaction record) {
        return productBidDepositTransactionMapper.add(record);
    }

    @Override
    public ProductBidDepositTransaction findById(Long id) {
        return productBidDepositTransactionMapper.findById(id);
    }

    @Override
    public int update(ProductBidDepositTransaction record) {
        return productBidDepositTransactionMapper.update(record);
    }

    @Override
    public ProductBidDepositTransaction findByProductBidId(Long productBidId) {
        return productBidDepositTransactionMapper.findByProductBidId(productBidId);
    }

}
