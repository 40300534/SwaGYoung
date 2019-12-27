package com.breeze.mapper;

import com.breeze.pojo.NewProductRequestImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 莫奇
 */
public interface NewProductRequestImageMapper {
    /**
     * 通過商品Id查詢正在審核的商品圖片
     * @param newProductRequestId
     * @return
     */
    List<NewProductRequestImage> findByProductRequestId(Long newProductRequestId);

    /**
     *根據申請商品Id刪除商品圖片
     * @param newProductRequestId
     */
    void deleteByProductRequestId(Long newProductRequestId);

    /**
     * 新增申請商品圖片
     * @param newProductRequestImage
     */
    void add(@Param("newProductRequestImage") NewProductRequestImage newProductRequestImage);
}
