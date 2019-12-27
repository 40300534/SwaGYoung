package com.breeze.mapper;

import com.breeze.pojo.ProductAsk;
import com.breeze.pojo.bo.UserSellHistoryBO;
import com.breeze.pojo.bo.ProductAskBO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-09 09:18
 * @Description: 一句话描述该类的功能
 */
public interface ProductAskMapper {

    int deleteById(Long id);

    int add(ProductAsk record);

    ProductAsk findById(Long id);

    int update(ProductAsk record);

    /**
     * 通过产品id查询出当前想购买者出的最高价格
     * @param productId
     * @return
     */
    BigDecimal findHighestAsk(Long productId);

    /**
     * 通过产品id查询出当前想购买者出的最高价格
     * @param productId
     * @return
     */
    BigDecimal findLowestAsk(Long productId);

    /**
     * 查询所有出售
     * @return
     */
    List<ProductAsk> findAll();

    /**
     * 查询用户出售商品
     * @return
     */
    List<ProductAskBO> findByUserId(Long userId);

    /**
     * 出售商品历史
     * @return
     */
    List<UserSellHistoryBO> findUserSellHistory(Long userId);

    /**
     * 通过产品id和用户id查询ProductAsk。
     * @param userId 用户id
     * @param productId 产品id
     */
    ProductAsk findByUserIdAndProductId(Long userId,Long productId);


    /**
     * 减库存
     * @param id ID
     * @return 影响行数
     */
    int decreaseStock(Long id);

    /**
     * 用户出价卖的单数
     * @param userId
     * @return
     */
    Long sellQuantity(Long userId);

    /**
     * 查詢所有出商品最低賣價
     * @return
     */
    List<ProductAskBO> findAllMinProduct();
}