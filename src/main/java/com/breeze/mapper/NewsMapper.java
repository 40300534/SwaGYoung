package com.breeze.mapper;

import com.breeze.pojo.News;
import java.util.List;

/**
 * @author 莫奇
 */
public interface NewsMapper {

    /**
     * 根据文章Id查询文章内容
     * @param newsId
     * @return
     */
    News findById(Long newsId);

    /**
     * 文章标题展示
     * @return
     */
    List<News> findAll();

    /**
     * 根据文章分类Id查询文章标题
     * @param categoryId
     * @return
     */
    List<News> findByCategoryId(int categoryId);

    /**
     * 增加新文章
     * @param newNews
     */
    void add(News newNews);

    /**
     * 更改文章
     * @param news
     */
    void update(News news);

    /**
     * 删除文章
     * @param id
     */
    void deleteById(Long id);
}
