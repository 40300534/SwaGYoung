package com.breeze.service.impl;

import com.breeze.pojo.bo.ProductUniqueAttributeBO;
import com.breeze.service.IProductAttrValueService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.breeze.mapper.ProductAttrValueMapper;
import com.breeze.pojo.ProductAttrValue;

import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-01 09:20
 */
@Service
public class ProductAttrValueServiceImpl implements IProductAttrValueService {

    @Resource
    private ProductAttrValueMapper productAttrValueMapper;

    @Override
    public int deleteById(Integer id) {
        return productAttrValueMapper.deleteById(id);
    }

    @Override
    public int add(ProductAttrValue record) {
        return productAttrValueMapper.add(record);
    }

    @Override
    public ProductAttrValue findById(Integer id) {
        return productAttrValueMapper.findById(id);
    }

    @Override
    public int update(ProductAttrValue record) {
        return productAttrValueMapper.update(record);
    }

    @Override
    public List<ProductUniqueAttributeBO> findUniqueAttributeByProductId(Long productId) {
        return productAttrValueMapper.findUniqueAttributeByProductId(productId);
    }

}
