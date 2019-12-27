package com.breeze.service.impl;

import com.breeze.constants.ResultMessage;
import com.breeze.enums.ResultStatusEnum;
import com.breeze.exception.ProjectxException;
import com.breeze.mapper.FeedbackImageMapper;
import com.breeze.mapper.FeedbackMapper;
import com.breeze.pojo.Feedback;
import com.breeze.pojo.FeedbackImage;
import com.breeze.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 莫奇
 */
@Service
public class FeedbackServiceImpl implements IFeedbackService {

    @Autowired
    private FeedbackMapper feedbackMapper;

    @Autowired
    private FeedbackImageMapper feedbackImageMapper;

    /**
     * 查询全部用户反馈
     *
     * @return
     */
    @Override
    public List<Feedback> findAll() {
        return feedbackMapper.findAll();
    }

    /**
     * 查询某一用户的全部反馈
     *
     * @param userId
     * @return
     */
    @Override
    public List<Feedback> findByUserId(Long userId) {
        return feedbackMapper.findByUserId(userId);
    }

    /**
     * 根据Id查询用户投诉反馈
     *
     * @param id
     * @return
     */
    @Override
    public Feedback findById(Long id) {
        return feedbackMapper.findById(id);
    }

    /**
     * 新增投诉
     *
     * @param feedback
     */
    @Override
    @Transactional
    public int add(Feedback feedback, List<Long> feedbackImageIdList) {
        int feedbackReturnId = feedbackMapper.add(feedback);
        if (feedbackReturnId != 0) {
            for (Long feedbackImages : feedbackImageIdList) {
                FeedbackImage feedbackImage = feedbackImageMapper.findById(feedbackImages);
                if (feedbackImage != null && feedbackImage.getFeedbackId() == null) {
                    feedbackImage.setFeedbackId(feedback.getId());
                    feedbackImageMapper.update(feedbackImage);
                } else {
                    throw new ProjectxException(ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
                }
            }
        }
        return feedbackReturnId;
    }

    /**
     * 修改用户投诉
     *
     * @param feedback
     */
    @Override
    public void update(Feedback feedback) {
        feedbackMapper.update(feedback);
    }

    /**
     * 删除投诉
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        feedbackMapper.deleteById(id);
    }
}
