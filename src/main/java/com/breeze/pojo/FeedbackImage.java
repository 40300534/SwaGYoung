package com.breeze.pojo;

import java.util.Date;

/**
 * @author Created by wdnnccey
 * @date 2019-07-25 10:17
 * @Description: 意见反馈图片
 */
public class FeedbackImage {
    /**
    * id
    */
    private Long id;

    /**
    * 意见反馈id
    */
    private Long feedbackId;

    /**
    * 图片地址
    */
    private String url;

    /**
    * 创建时间
    */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}