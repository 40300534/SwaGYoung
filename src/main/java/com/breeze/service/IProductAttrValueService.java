package com.breeze.service;

import com.breeze.pojo.ProductAttrValue;
import com.breeze.pojo.bo.ProductUniqueAttributeBO;
import com.breeze.pojo.dto.ProductUniqueAttributeDTO;

import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-01 09:20
 * @Description: 一句话描述该类的功能
 */
public interface IProductAttrValueService {

    int deleteById(Integer id);

    int add(ProductAttrValue record);

    ProductAttrValue findById(Integer id);

    int update(ProductAttrValue record);

    /**
     * 通过产品id查询该产品具备的其他特有属性
     * @param productId
     * @return
     */
    List<ProductUniqueAttributeBO> findUniqueAttributeByProductId(Long productId);

}
