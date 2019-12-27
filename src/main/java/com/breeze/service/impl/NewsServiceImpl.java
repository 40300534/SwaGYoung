package com.breeze.service.impl;

import com.breeze.mapper.NewsMapper;
import com.breeze.pojo.News;
import com.breeze.service.INewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 莫奇
 */
@Service
public class NewsServiceImpl implements INewsService {

    @Autowired
    private NewsMapper newsMapper;

    /**
     * 根据文章Id查询文章内容
     *
     * @param newsId
     * @return
     */
    @Override
    public News findById(Long newsId) {
        return newsMapper.findById(newsId);
    }

    /**
     * 全部文章展示
     *
     * @return
     */
    @Override
    public List<News> findAll() {
        return newsMapper.findAll();
    }

    /**
     * 根据文章分类Id查询文章
     *
     * @param categoryId
     * @return
     */
    @Override
    public List<News> findByCategoryId(int categoryId) {
        return newsMapper.findByCategoryId(categoryId);
    }

    /**
     * 增加文章
     *
     * @param newNews
     */
    @Override
    public void add(News newNews) {
        newsMapper.add(newNews);
    }

    /**
     * 更改文章
     *
     * @param news
     */
    @Override
    public void update(News news) {
        newsMapper.update(news);
    }

    /**
     * 删除文章
     *
     * @param id
     */
    @Override
    public void deleteById(long id) {
        newsMapper.deleteById(id);
    }
}
