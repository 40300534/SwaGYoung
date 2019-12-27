package com.breeze.service.impl;

import com.breeze.pojo.ProductOrder;
import com.breeze.pojo.bo.ProductOrderBO;
import com.breeze.service.IProductOrderService;
import com.breeze.mapper.ProductOrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wdnnccey
 */
@Service
public class ProductOrderServiceImpl implements IProductOrderService {

    @Autowired
    private ProductOrderMapper productOrderMapper;

    @Override
    public int deleteById(Long id) {
        return productOrderMapper.deleteById(id);
    }

    @Override
    public int add(ProductOrder record) {
        return productOrderMapper.add(record);
    }

    @Override
    public ProductOrder findById(Long id) {
        return productOrderMapper.findById(id);
    }

    @Override
    public int update(ProductOrder record) {
        return productOrderMapper.update(record);
    }
    /**
     * 查询订单相关商品信息
     * @param orderId
     */
    @Override
    public ProductOrderBO findByOrderId(Long orderId) {
        return productOrderMapper.findByOrderId(orderId);
    }

}

