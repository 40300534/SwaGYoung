package com.breeze.mapper;

import com.breeze.pojo.ProductBrand;

import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-01 08:32
 * @Description: 产品属性
 */
public interface BrandMapper {

    int deleteById(Integer id);

    int add(ProductBrand record);

    ProductBrand findById(Integer id);

    int update(ProductBrand record);

    List<ProductBrand> findAll();
}