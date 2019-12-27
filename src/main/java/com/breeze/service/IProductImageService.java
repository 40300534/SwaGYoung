package com.breeze.service;

import com.breeze.pojo.ProductImage;
import com.breeze.pojo.bo.ProductImageInfoBO;

import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-06-26 00:23
 */
public interface IProductImageService {

    /**
     * 通过id删除产品图片
     * @param id
     * @return
     */
    int deleteById(Long id);

    /**
     * 插入图片
     * @param record
     * @return
     */
    int add(ProductImage record);

    /**
     * 通过主键查询图片
     * @param id
     * @return
     */
    ProductImage findById(Long id);

    /**
     * 通过主键修改图片
     * @param record
     * @return
     */
    int update(ProductImage record);

    /**
     * 通过产品id查询所有产品下的图片信息
     * @param productCategoryId
     * @return
     */
    List<ProductImageInfoBO> findProductImageInfoByproductCategoryId(Integer productCategoryId);

    /**
     *  通过id查询出当前的最大图片号码数
     * @param productId
     * @return int
     */
    int findMaxPictureNumber(Long productId);

    /**
     * 通过产品id查询产品的主图
     * @param productId
     * @return
     */
    String findMasterPictureByProductId(Long productId);

    /**
     * 通过产品id查询一个产品下所有图片
     * @param productId 产品Id
     * @return List<String>
     */
    List<ProductImage> findProductAllImageByProductId(Long productId);
}
