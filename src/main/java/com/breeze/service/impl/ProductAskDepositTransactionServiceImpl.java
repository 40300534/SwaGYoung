package com.breeze.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.breeze.pojo.ProductAskDepositTransaction;
import com.breeze.mapper.ProductAskDepositTransactionMapper;
import com.breeze.service.IProductAskDepositTransactionService;
/**
 * @author Created by wdnnccey
 * @date 2019-07-09 09:18
 * @Description: 一句话描述该类的功能
 */
@Service
public class ProductAskDepositTransactionServiceImpl implements IProductAskDepositTransactionService {

    @Resource
    private ProductAskDepositTransactionMapper productAskDepositTransactionMapper;

    @Override
    public int deleteById(Long id) {
        return productAskDepositTransactionMapper.deleteById(id);
    }

    @Override
    public int add(ProductAskDepositTransaction record) {
        return productAskDepositTransactionMapper.add(record);
    }

    @Override
    public ProductAskDepositTransaction findById(Long id) {
        return productAskDepositTransactionMapper.findById(id);
    }

    @Override
    public int update(ProductAskDepositTransaction record) {
        return productAskDepositTransactionMapper.update(record);
    }

    @Override
    public ProductAskDepositTransaction findTranscationIdByProductAskId(Long productAskId) {
        return productAskDepositTransactionMapper.findTranscationIdByProductAskId(productAskId);
    }


}
