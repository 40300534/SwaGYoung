package com.breeze.service.impl;

import com.breeze.pojo.ProductCategory;
import com.breeze.service.IProductCategoryService;
import com.breeze.mapper.ProductCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wdnnccey
 */
@Service
public class ProductCategoryServiceImpl implements IProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public int add(ProductCategory productCategory) {
        return productCategoryMapper.add(productCategory);
    }

    @Override
    public int update(ProductCategory productCategory) {
        return productCategoryMapper.update(productCategory);
    }


    @Override
    public List<ProductCategory> findAll() {
        return productCategoryMapper.findAll();
    }

    @Override
    public ProductCategory findById(Integer id) {
        return productCategoryMapper.findById(id);
    }

    @Override
    public int deleteById(Integer id) {
        return productCategoryMapper.deleteById(id);
    }

}
