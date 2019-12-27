package com.breeze.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.breeze.mapper.FeedbackImageMapper;
import com.breeze.pojo.FeedbackImage;
import com.breeze.service.IFeedbackImageService;

import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-25 10:17
 */
@Service
public class FeedbackImageServiceImpl implements IFeedbackImageService {

    @Resource
    private FeedbackImageMapper feedbackImageMapper;

    @Override
    public int deleteById(Long id) {
        return feedbackImageMapper.deleteById(id);
    }

    @Override
    public int add(FeedbackImage feedbackImage) {
        return feedbackImageMapper.add(feedbackImage);
    }

    @Override
    public FeedbackImage findById(Long id) {
        return feedbackImageMapper.findById(id);
    }

    @Override
    public int update(FeedbackImage feedbackImage) {
        return feedbackImageMapper.update(feedbackImage);
    }

    @Override
    public List<FeedbackImage> findAll() {
        return feedbackImageMapper.findAll();
    }

}
