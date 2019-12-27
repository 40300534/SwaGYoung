package com.breeze.mapper;

import com.breeze.pojo.ProductAttrValue;
import com.breeze.pojo.bo.ProductUniqueAttributeBO;

import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-01 09:20
 */
public interface ProductAttrValueMapper {

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