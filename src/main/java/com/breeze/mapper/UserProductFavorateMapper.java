package com.breeze.mapper;

import com.breeze.pojo.UserProductFavorate;
import com.breeze.pojo.bo.ProductBO;
import com.breeze.pojo.bo.ProductManageBO;

import java.util.List;

/**
 * @author 莫奇
 * @date 2019-07-01
 */
public interface UserProductFavorateMapper {


    /**
     * 通过id删除
     *
     * @param id
     * @return int
     */
    int deleteById(Long id);

    /**
     * 插入数据
     *
     * @param record
     * @return int
     */
    int add(UserProductFavorate record);

    /**
     * 通过id查询
     *
     * @param productId
     * @param userId
     * @return ProductUserFavorate
     */
    UserProductFavorate findByUserIdAndProductId(Long productId , Long userId);

    /**
     * 通过id修改数据
     *
     * @param record
     * @return int
     */
    int update(UserProductFavorate record);

    /**
     * 查询所有我的收藏
     *
     * @return
     */
    List<UserProductFavorate> findAllByUserId(Long userId);

    /**
     * 根据商品id查询商品
     * @param productId
     */
    ProductBO findByProductId(Long productId);

    /**
     *收藏商品数
     */
    Long userFavorateNumber(Long userId);

    /**
     * 查询每个商品收藏总数
     * @return
     */
    List<ProductManageBO> findByProductFavorateCounts();
}