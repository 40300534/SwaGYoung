package com.breeze.service;

import com.breeze.pojo.FeedbackType;

import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-25 10:21
 */
public interface IFeedbackTypeService {

    int deleteById(Integer id);

    int add(FeedbackType record);

    FeedbackType findById(Integer id);

    int update(FeedbackType record);

    List<FeedbackType> findAll();
}