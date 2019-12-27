package com.breeze.mapper;

import com.breeze.pojo.ProductAttrKey;

/**
 * @author Created by wdnnccey
 * @date 2019-07-01 09:15
 */
public interface ProductAttrKeyMapper {

    int deleteById(Integer id);

    int add(ProductAttrKey record);

    ProductAttrKey findById(Integer id);

    int update(ProductAttrKey record);

}