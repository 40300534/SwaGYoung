package com.breeze.service.impl;

import com.breeze.mapper.ShoppingCartMapper;
import com.breeze.pojo.UserProductShoppingCart;
import com.breeze.pojo.bo.ShoppingCartBO;
import com.breeze.service.IShoppingCartService;
import com.breeze.util.RedisCartUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 莫奇
 */
@Service
public class ShoppingCartServiceImpl implements IShoppingCartService {

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private RedisCartUtils redisCartUtils;

    /**
     * 将购物车保存到redis中
     *
     * @param newShoppingCart 购物车
     */
    @Override
    public void add(UserProductShoppingCart newShoppingCart) {
        List<UserProductShoppingCart> shoppingCartList =
                (List) redisCartUtils.lGet(newShoppingCart.getUserId() + "", 0, -1);
        if (shoppingCartList != null && shoppingCartList.size() > 0) {
            for (UserProductShoppingCart shoppingCart : shoppingCartList) {
                if (shoppingCart.getProductId().equals(newShoppingCart.getProductId())) {
                    deleteById(shoppingCart);
                    shoppingCart.setQuantity(shoppingCart.getQuantity() + newShoppingCart.getQuantity());
                    redisCartUtils.lSet(shoppingCart.getUserId() + "", shoppingCart);
                } else {
                    deleteById(newShoppingCart);
                    redisCartUtils.lSet(newShoppingCart.getUserId() + "", newShoppingCart);
                }
            }
        } else {
            redisCartUtils.lSet(newShoppingCart.getUserId() + "", newShoppingCart);
        }
    }

    /**
     * 删除商品
     *
     * @param shoppingCart 购物车
     */
    @Override
    public void deleteById(UserProductShoppingCart shoppingCart) {
        List<UserProductShoppingCart> shoppingCartList =
                (List) redisCartUtils.lGet(shoppingCart.getUserId() + "", 0, -1);
        for (UserProductShoppingCart cart : shoppingCartList) {
            if (cart.getProductId().equals(shoppingCart.getProductId())) {
                redisCartUtils.lRemove(shoppingCart.getUserId() + "", -1, cart);
            }
        }
    }

    /**
     * 查询购物车
     *
     * @param userId 用户id
     * @return
     */
    @Override
    public List<ShoppingCartBO> findAll(Long userId) {
        List<ShoppingCartBO> shoppingCartBOList = new ArrayList<>();
        List<UserProductShoppingCart> shoppingCartList = (List) redisCartUtils.lGet(userId + "", 0, -1);

        if (shoppingCartList != null && shoppingCartList.size() > 0) {
            for (UserProductShoppingCart shoppingCart : shoppingCartList) {
                ShoppingCartBO cartRedisBo = shoppingCartMapper.findByProductId(shoppingCart.getProductId());
                cartRedisBo.setQuantity(shoppingCart.getQuantity());
                shoppingCartBOList.add(cartRedisBo);
            }
            return shoppingCartBOList;
        } else {
            List<UserProductShoppingCart> shoppingCarts = shoppingCartMapper.findByUserId(userId);
            for (UserProductShoppingCart cart : shoppingCarts) {
                UserProductShoppingCart shoppingCart = new UserProductShoppingCart();
                shoppingCart.setProductId(cart.getProductId());
                shoppingCart.setUserId(cart.getUserId());
                shoppingCart.setQuantity(cart.getQuantity());
                shoppingCart.setCreateTime(cart.getCreateTime());
                update(shoppingCart);
            }
        }
        List<ShoppingCartBO> shoppingCartBOS = shoppingCartMapper.findAll(userId);
        return shoppingCartBOS;
    }

    /**
     * 修改购物车中商品
     *
     * @param shoppingCart
     */
    @Override
    public void update(UserProductShoppingCart shoppingCart) {
        deleteById(shoppingCart);
        shoppingCart.setCreateTime(new Date());
        add(shoppingCart);

    }

    /**
     * 清空购物车
     *
     * @param userId
     */
    @Override
    public void delete(Long userId) {
        redisCartUtils.del(userId + "");
    }
}






