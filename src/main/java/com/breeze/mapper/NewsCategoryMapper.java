package com.breeze.mapper;


import com.breeze.pojo.NewsCategory;
import java.util.List;

/**
 * @author 莫奇
 */
public interface NewsCategoryMapper {

    /**
     * 查询所有文章分类
     * @return
     */
    List<NewsCategory> findAll();

    /**
     * 添加文章分类
     * @param newsCategory
     */
    void add(NewsCategory newsCategory);

    /**
     * 删除文章分类
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 修改文章分类
     * @param newsCategory
     */
    void update(NewsCategory newsCategory);
}
