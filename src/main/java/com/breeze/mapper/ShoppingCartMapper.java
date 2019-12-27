package com.breeze.mapper;

import com.breeze.pojo.UserProductShoppingCart;
import com.breeze.pojo.bo.ShoppingCartBO;

import java.util.List;

/**
 * @author 莫奇
 */
public interface ShoppingCartMapper {


    /**
     * 查询购物车
     * @param userId
     * @return
     */
    List<ShoppingCartBO> findAll(Long userId);

    /**
     * 从redis获取查询商品Id查询
     * @param productId
     * @return ShoppingCartDto
     */
    ShoppingCartBO findByProductId(Long productId);


    /**
     * 查询加入购物车时间
     * @param userId
     * @return List<UserProductShoppingCart>
     */
    List<UserProductShoppingCart> findByUserId(Long userId);
}
