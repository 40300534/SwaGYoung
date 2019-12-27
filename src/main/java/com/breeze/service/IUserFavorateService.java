package com.breeze.service;

import com.breeze.pojo.UserProductFavorate;
import com.breeze.pojo.bo.ProductBO;

import java.util.List;

/**
 * @author wdnnccey
 * @date 2019-06-25 23:16
 */
public interface IUserFavorateService {

    /**
     * 通过id删除
     * @param id
     * @return int
     */
    int deleteById(Long id);

    /**
     * 插入数据
     * @param record
     * @return int
     */
    int add(UserProductFavorate record);

    /**
     * 通过id查询
     * @param productId
     * @param userId
     * @return
     */
    UserProductFavorate findById(Long productId , Long userId );

    /**
     * 通过id修改数据
     * @param record
     * @return int
     */
    int update(UserProductFavorate record);

    /**
     * 查询所有我的收藏
     * @param uid 用户Id
     * @return
     */
    List<ProductBO> findAllByUserId(Long uid);

    /**
     * toggle
     * @param userProductFavorate
     * @return
     */
    UserProductFavorate toggle(UserProductFavorate userProductFavorate);

    /**
     * 根据用户id和商品ID判断是否是收藏
     * @param userId
     * @param productId
     * @return
     */
    boolean isFavorate(Long userId, Long productId);

    /**
     * 用户收藏商品的数量
     * @param userId
     * @return
     */
    Long userFavorateNumber(Long userId);
}
