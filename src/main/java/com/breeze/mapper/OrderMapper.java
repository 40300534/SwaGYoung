package com.breeze.mapper;

import com.breeze.pojo.Order;
import com.breeze.pojo.bo.AmountBO;
import com.breeze.pojo.bo.OrderBO;

import java.util.List;

/**
 * @author tx
 */
public interface OrderMapper {

    int deleteById(Long id);

    int add(Order record);

    Order findById(Long id);

    int update(Order record);

    List<OrderBO> findAll();

    /**
     * 用户销售单数
     * @param userId
     * @return
     */
    Long findSalesQuantity(Long userId);

    /**
     * 用户购买单数
     * @param userId
     * @return
     */
    Long findPurchaseQuantity(Long userId);
    /**
     * 出价买 买家付的押金
     * @param buyerId
     * @return
     */
    AmountBO findBuyerPaysDeposit(Long buyerId);

    /**
     * 出价卖  卖家付的押金
     * @param sellerId
     * @return
     */
    AmountBO findSellerPaysDeposit(Long sellerId);

    /**
     * 查所有后台订单详情
     * @param status
     * @return
     */
    List<OrderBO> findOrderByStatusId(Long status);
}
