package com.breeze.mapper;

import com.breeze.pojo.NewProductRequest;
import com.breeze.pojo.bo.NewProductRequestBO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 莫奇
 */
public interface NewProductRequestMapper {

    /**
     * 插入申請產品
     * @param newProductRequest
     */
    void add(@Param("newProductRequest") NewProductRequest newProductRequest);

    /**
     * 通过商品ID查询正在审核的商品
     * @param id
     * @return
     */
    NewProductRequestBO findById(Long id);

    /**
     * 刪除審核商品
     * @param id
     */
    void deleteById(Long id);

    /**
     * 查詢全部新的商品審核信息
     * @return
     */
    List<NewProductRequestBO> findAllNewProductRequest();

    /**
     * 查詢全部以拒絕的商品審核信息
     * @return
     */
    List<NewProductRequestBO> findAllDeniedProductRequest();
}
