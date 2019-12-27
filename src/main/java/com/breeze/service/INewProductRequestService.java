package com.breeze.service;

import com.breeze.pojo.NewProductRequest;
import com.breeze.pojo.Product;
import com.breeze.pojo.bo.NewProductRequestBO;

import java.util.List;

/**
 * @author 莫奇
 */
public interface INewProductRequestService {

    /**
     * 插入
     * @param newProductRequestBO
     * @return int
     */
    void add(NewProductRequestBO newProductRequestBO);

    /**
     * 通过商品ID查询正在审核的商品
     * @param id
     * @return
     */
    NewProductRequestBO findById(Long id);

    /**
     * 刪除正在審核的商品
     * @param id
     */
    void deleteById(Long id);

    /**
     * 是否审核通过
     * @param id
     * @return
     */
    boolean isRequestSuccess(Long id);

    /**
     * 查詢全部新的申請商品
     * @return
     */
    List<NewProductRequestBO> findAllNewProductRequest();

    /**
     * 查詢全部以拒絕的申請商品信息
     * @return
     */
    List<NewProductRequestBO> findAllDeniedProductRequest();
}
