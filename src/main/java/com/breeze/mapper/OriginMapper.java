package com.breeze.mapper;

import com.breeze.pojo.ProductOrigin;

import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-01 08:46
 */
public interface OriginMapper {
    int deleteById(Integer id);

    int add(ProductOrigin record);

    ProductOrigin findById(Integer id);

    int update(ProductOrigin record);

    List<ProductOrigin> findAll();
}