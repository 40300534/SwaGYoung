package com.breeze.service;

import com.breeze.pojo.ProductCategory;

import java.util.List;

/**
 * @author wdnnccey
 */
public interface IProductCategoryService {

    /**
     * 插入数据
     *
     * @param productCategory 商品分类对象
     * @return
     */
    int add(ProductCategory productCategory);


    /**
     * 通过id修改分类
     *
     * @param productCategory
     * @return
     */
    int update(ProductCategory productCategory);

    /**
     * 查询所有产品分类
     *
     * @return 产品分类List
     */
    public List<ProductCategory> findAll();

    /**
     * 通过id查询分类
     *
     * @param id
     * @return
     */
    ProductCategory findById(Integer id);

    /**
     * 通过id删除分类
     * @param id
     * @return int
     */
    int deleteById(Integer id);


}
