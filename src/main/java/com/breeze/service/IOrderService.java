package com.breeze.service;

import com.breeze.pojo.Order;
import com.breeze.pojo.bo.AmountBO;
import com.breeze.pojo.bo.OrderBO;
import com.breeze.pojo.bo.OrderDetailsBO;

import java.util.List;

/**
 * @author wdnnccey
 */
public interface IOrderService {
    //删除订单
    int deleteById(Long id);
    //添加订单
    int add(Order record);
    //查id订单
    Order findById(Long id);
    //修改订单
    int update(Order record);
    //查询所有
    List<OrderBO> findAll();

    /**
     * 查询订单详情
     * @param orderId
     * @return
     */
    OrderDetailsBO findOrderId(Long orderId);

    /**
     * 用户销售单数
     * @param userId
     */
    Long findSalesQuantity(Long userId);

    /**
     * 用户购买单数
     * @param userId
     * @return
     */
    Long findPurchaseQuantity(Long userId);

    /**
     * 出价买 买家支付的押金
     * @param buyerId
     * @return
     */
    AmountBO findBuyerPaysDeposit(Long buyerId);

    AmountBO findSellerPaysDeposit(Long sellerId);
    //查询所有后台的订单详情
    List<OrderBO> findOrderByStatusId(Long status);
}
