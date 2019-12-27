package com.breeze.mapper;

import com.breeze.pojo.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wdnnccey
 */
public interface ProductCategoryMapper {

    /**
     * 插入用户分类信息
     * @param productCategory
     * @return
     */
    int add(@Param("productCategory") ProductCategory productCategory);

    /**
     * 通过id
     * @param productCategory
     * @return
     */
    int update(@Param("productCategory") ProductCategory productCategory);

    /**
     * 查询所有产品分类
     * @return List<ProductCategory>
     */
    public List<ProductCategory> findAll();

    /**
     * 通过id修改产品分类信息
     * @param id
     * @return
     */
    ProductCategory findById(@Param("id")Integer id);

    /**
     * 通过id删除产品分类信息
     * @param id
     * @return
     */
    int deleteById(@Param("id")Integer id);




}
