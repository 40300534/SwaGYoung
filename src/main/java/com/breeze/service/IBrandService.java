package com.breeze.service;

import com.breeze.pojo.ProductBrand;

import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-01 08:32
 * @Description: 一句话描述该类的功能
 */
public interface IBrandService {

    int deleteById(Integer id);

    int add(ProductBrand record);

    ProductBrand findById(Integer id);

    int update(ProductBrand record);

    List<ProductBrand> findAll();
}
