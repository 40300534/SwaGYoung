package com.breeze.service.impl;

import com.breeze.mapper.ProductAttrKeyMapper;
import com.breeze.service.IProductAttrKeyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.breeze.pojo.ProductAttrKey;

/**
 * @author Created by wdnnccey
 * @date 2019-07-01 09:15
 */
@Service
public class ProductAttrKeyServiceImpl implements IProductAttrKeyService {

    @Resource
    private ProductAttrKeyMapper productAttrKeyMapper;

    @Override
    public int deleteById(Integer id) {
        return productAttrKeyMapper.deleteById(id);
    }

    @Override
    public int add(ProductAttrKey record) {
        return productAttrKeyMapper.add(record);
    }


    @Override
    public ProductAttrKey findById(Integer id) {
        return productAttrKeyMapper.findById(id);
    }

    @Override
    public int update(ProductAttrKey record) {
        return productAttrKeyMapper.update(record);
    }

}
