package com.breeze.service;

import com.breeze.pojo.ProductAsk;
import com.breeze.pojo.bo.UserSellHistoryBO;
import com.breeze.pojo.bo.ProductAskBO;
import com.breeze.pojo.bo.ProductUserAskBO;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-09 09:18
 * @Description: 一句话描述该类的功能
 */
public interface IProductAskService {

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
     * 正在出价卖的商品
     * @return
     */
    List<ProductAskBO> findAll();

    /**
     * 获取用户出售商品
     */
    List<ProductUserAskBO> findByUserId(Long userId);

    /**
     * 通过产品id和用户id查询ProductAsk。
     * @param userId 用户id
     * @param productId 产品id
     */
    ProductAsk findByUserIdAndProductId(Long userId,Long productId);


    /**
     * 出售历史
     */
    List<UserSellHistoryBO> findUserSellHistory(Long userId);

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
}
