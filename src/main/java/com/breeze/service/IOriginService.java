package com.breeze.service;

import com.breeze.pojo.ProductOrigin;

import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-01 08:46
 * @Description: 一句话描述该类的功能
 */
public interface IOriginService {


    int deleteById(Integer id);

    int add(ProductOrigin record);

    ProductOrigin findById(Integer id);

    int update(ProductOrigin record);

    List<ProductOrigin> findAll();
}
