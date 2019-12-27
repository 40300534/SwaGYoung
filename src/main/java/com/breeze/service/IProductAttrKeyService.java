package com.breeze.service;

import com.breeze.pojo.ProductAttrKey;
    /**
 * @author Created by wdnnccey
 * @date 2019-07-01 09:15
 * @Description: 一句话描述该类的功能
 */
public interface IProductAttrKeyService{

    int deleteById(Integer id);

    int add(ProductAttrKey record);

    ProductAttrKey findById(Integer id);

    int update(ProductAttrKey record);

}
