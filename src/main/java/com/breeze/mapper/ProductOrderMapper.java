package com.breeze.mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

import com.breeze.pojo.ProductOrder;
import com.breeze.pojo.bo.ProductOrderBO;

/**
 * @author Created by wdnnccey
 * @date 2019-07-08 10:15
 * @Description: 一句话描述该类的功能
 */
public interface ProductOrderMapper {
    
    int deleteById(Long id);

    int add(ProductOrder record);

    ProductOrder findById(Long id);

    int update(ProductOrder record);

    /**
     * 查询订单相关商品信息
     * @param orderId
     */
    ProductOrderBO findByOrderId(Long orderId);
}