package com.breeze.mapper;

import com.breeze.pojo.bo.*;
import org.apache.ibatis.annotations.Param;

import com.breeze.pojo.Product;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author wdnnccey
 */
public interface ProductMapper {

    /**
     * 插入产品
     *
     * @param product
     * @return int
     */
    int add(@Param("product") Product product);

    /**
     * 通过id删除产品
     *
     * @param id
     * @return int
     */
    int deleteById(@Param("id") Long id);

    /**
     * 通过id修改商品信息
     *
     * @param product
     * @return
     */
    int update(@Param("product")Product product);


    /**
     * 查询所有产品
     *
     * @return List<ProductBO>
     */
    List<ProductBO> findAll();

    /**
     * 如果没有人出价，通过此方法查询
     * @param productId
     * @return
     */
    ProductBO findProductBaseInfoByProductId(Long productId);

    /**
     * 通过产品id查询购买需要的基本属性，不包含其他任何附加属性。
     * @return
     */
    ProductBO findProductBuyBaseInfoByProductId(Long productId);


    /**
     * 通过产品id查询卖需要的基本属性，不包含其他任何附加属性。
     * @return
     */
    ProductBO findProductSellBaseInfoByProductId(Long productId);

    /**
     * 通过商品ID查询商品
     *
     * @param id
     * @return Product
     */
    Product findById(Long id);

    /**
     * 通过分类Id查询所有分类下的商品
     *
     * @param productCategoryId
     * @return List<Product>
     */
    List<ProductBO> findByProductCategoryId(@Param("productCategoryId") Integer productCategoryId);

    /**
     * 通过商品名称模糊查询商品
     *
     * @param name 商品名称
     * @return List<ProductBO>
     */
    List<ProductBO> findByNameAndCategoryId(@Param("name") String name, @Param("product_category_id") Integer categoryId);

    /**
     * 通过产品id查询产品详情页数据
     *
     * @param id 产品id
     * @return Product
     */
    ProductDetailsBO findProductDetailsById(Long id);

    /**
     * 最新的产品（首页Top）
     *
     * @return
     */
    List<ProductBO> newestProduct();

    /**
     * 查询销量top的产品数据
     *
     * @return
     */
    List<ProductSalesVolumeBO> findProductSalesVolumeTop();

    /**
     * 查询目前销量排行
     *
     * @return ProductSalesVolumeBO
     */
    List<ProductBO> recentlySoldProducts();

    /**
     * 查询销量30天内最大的产品信息
     *
     * @return ProductBO
     */
    List<ProductBO> findProductInId(List<Long> productIdList);

    /**
     * 通过关键词返回相关产品名称（搜索键入提示）
     *
     * @param keyword
     * @return
     */
    List<Product> queryPrompt(String keyword);

    /**
     * 通过产品id查询出最新的成交价格（商品详情中）
     *
     * @param productId
     * @return
     */
    BigDecimal findLastTransactionPriceByProductId(Long productId);

    /**
     * 通过产品分类ID，查询销量top的产品数据（30天内销量最多的产品）
     *
     * @param productCategoryId 产品分类Id
     * @return ProductSalesVolumeBO
     */
    List<ProductSalesVolumeBO> findProductSalesVolumeTopByProductCategoryId(Integer productCategoryId);

    /**
     * 通过产品id查询 产品必有属性
     * @param productId
     * @return ProductAttributesBO
     */
    ProductAttributesBO findProductPublicAttributesByProductId(Long productId);

    /**
     * 通过产品Id查询历史成交价格走势（从旧的到最新的）
     * @param productId
     * @return
     */
    List<ProductHistoryPriceBO> findProductHistoryList(Long productId);

    /**
     * 根据商品名称查询商品
     * @param name
     */
    Long findByName(String name);

    /**
     * 商品后台管理查询所有商品
     * @return
     */
    List<ProductManageBO> findAllProduct();
}
