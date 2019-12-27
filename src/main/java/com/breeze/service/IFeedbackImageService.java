package com.breeze.service;

import com.breeze.pojo.FeedbackImage;

import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-25 10:17
 */
public interface IFeedbackImageService {

    int deleteById(Long id);

    int add(FeedbackImage record);

    FeedbackImage findById(Long id);

    int update(FeedbackImage record);

    List<FeedbackImage> findAll();
}
