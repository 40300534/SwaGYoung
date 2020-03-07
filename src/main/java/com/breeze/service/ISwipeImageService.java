package com.breeze.service;

import com.breeze.pojo.SwipeImage;

import java.util.List;

/**
 * 轮播图
 * @author wdnnccey
 */
public interface ISwipeImageService {

    /**
     * 添加轮播图数据
     *
     * @param record
     * @return
     */
    int add(SwipeImage record);

    /**
     * 通过id删除轮播图数据
     *
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 通过id删除轮播图数据
     *
     * @param record
     * @return
     */
    int update(SwipeImage record);

    /**
     * 通过id查询轮播图数据
     *
     * @param id
     * @return
     */
    SwipeImage findById(Integer id);

    /**
     * 查询轮播图所有数据
     *
     * @return
     */
    List<SwipeImage> findAll();

}