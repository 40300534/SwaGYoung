package com.breeze.service;

import com.breeze.pojo.ProductOrder;
import com.breeze.pojo.bo.ProductOrderBO;

/**
 * @author wdnnccey
 */
public interface IProductOrderService {

    int deleteById(Long id);

    int add(ProductOrder record);

    ProductOrder findById(Long id);

    int update(ProductOrder record);

    /**
     * 查询商品订单信息
     * @param orderId
     * @return
     */
    ProductOrderBO findByOrderId(Long orderId);
}
