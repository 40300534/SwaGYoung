package com.breeze.service;

import com.breeze.pojo.bo.ShoppingCartBO;
import com.breeze.pojo.UserProductShoppingCart;

import java.util.List;

/**
 * @author 莫奇
 */
public interface IShoppingCartService {

    /**
     * 将购物车保存到redis中
     * @param newShoppingCart
     */
    void add(UserProductShoppingCart newShoppingCart );

   /**
     * 删除商品
     * @param shoppingCart
     */
    void deleteById(UserProductShoppingCart shoppingCart);

    /**
     * 查询购物车
     * @param userId
     * @return
     */
    List<ShoppingCartBO> findAll(Long userId);

    /**
     * 修改购物车中商品
     * @param shoppingCart
     */
    void update(UserProductShoppingCart shoppingCart);

    /**
     * 清空购物车
     * @param userId
     */
    void delete(Long userId);
}
