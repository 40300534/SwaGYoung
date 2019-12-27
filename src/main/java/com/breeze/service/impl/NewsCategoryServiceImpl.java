package com.breeze.service.impl;

import com.breeze.mapper.NewsCategoryMapper;
import com.breeze.pojo.NewsCategory;
import com.breeze.service.INewsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 莫奇
 */
@Service
public class NewsCategoryServiceImpl implements INewsCategoryService {

    @Autowired
    private NewsCategoryMapper newsCategoryMapper;

    /**
     * 查询所有文章分类
     *
     * @return
     */
    @Override
    public List<NewsCategory> findAll() {
        return newsCategoryMapper.findAll();
    }

    /**
     * 添加文章分类
     *
     * @param newsCategory
     */
    @Override
    public void add(NewsCategory newsCategory) {
        newsCategoryMapper.add(newsCategory);
    }

    /**
     * 删除文章分类
     *
     * @param id
     */
    @Override
    public void deleteById(Integer id) {
        newsCategoryMapper.deleteById(id);
    }

    /**
     * 修改文章分类
     *
     * @param newsCategory
     */
    @Override
    public void update(NewsCategory newsCategory) {
        newsCategoryMapper.update(newsCategory);
    }
}
