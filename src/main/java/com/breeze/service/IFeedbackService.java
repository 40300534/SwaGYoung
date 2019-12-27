package com.breeze.service;

import com.breeze.pojo.Feedback;

import java.util.List;

/**
 * @author 莫奇
 */
public interface IFeedbackService {

    /**
     * 查询全部用户反馈
     * @return
     */
    List<Feedback> findAll();

    /**
     * 查询某一用户的全部反馈
     * @param userId
     * @return
     */
    List<Feedback> findByUserId(Long userId);

    /**
     * 根据Id查询用户投诉反馈
     * @param id
     * @return
     */
    Feedback findById(Long id);

    /**
     * 新增投诉
     * @param feedback
     */
    int add(Feedback feedback,List<Long> feedbackImageIdList);

    /**
     * 修改用户投诉
     * @param feedback
     */
    void update(Feedback feedback);

    /**
     * 删除投诉
     * @param id
     */
    void deleteById(Long id);
}
