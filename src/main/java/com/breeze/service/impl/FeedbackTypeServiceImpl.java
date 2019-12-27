package com.breeze.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.breeze.mapper.FeedbackTypeMapper;
import com.breeze.pojo.FeedbackType;
import com.breeze.service.IFeedbackTypeService;

import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-25 10:21
 */
@Service
public class FeedbackTypeServiceImpl implements IFeedbackTypeService {

    @Resource
    private FeedbackTypeMapper feedbackTypeMapper;

    @Override
    public int deleteById(Integer id) {
        return feedbackTypeMapper.deleteById(id);
    }

    @Override
    public int add(FeedbackType record) {
        return feedbackTypeMapper.add(record);
    }

    @Override
    public FeedbackType findById(Integer id) {
        return feedbackTypeMapper.findById(id);
    }

    @Override
    public int update(FeedbackType record) {
        return feedbackTypeMapper.update(record);
    }

    @Override
    public List<FeedbackType> findAll() {
        return feedbackTypeMapper.findAll();
    }

}
