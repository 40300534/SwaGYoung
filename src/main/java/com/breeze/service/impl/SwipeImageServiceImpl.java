package com.breeze.service.impl;

import com.breeze.mapper.SwipeImageMapper;
import com.breeze.pojo.SwipeImage;
import com.breeze.service.ISwipeImageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 轮播图
 *
 * @author wdnnccey
 */
@Service
public class SwipeImageServiceImpl implements ISwipeImageService {

    @Resource
    private SwipeImageMapper swipeImageMapper;

    @Override
    public int deleteById(Integer id) {
        return swipeImageMapper.deleteById(id);
    }

    @Override
    public int add(SwipeImage record) {
        return swipeImageMapper.add(record);
    }

    @Override
    public SwipeImage findById(Integer id) {
        return swipeImageMapper.findById(id);
    }

    @Override
    public List<SwipeImage> findAll() {
        return swipeImageMapper.findAll();
    }

    @Override
    public int update(SwipeImage record) {
        return swipeImageMapper.update(record);
    }


}
