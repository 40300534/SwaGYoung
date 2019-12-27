package com.breeze.pojo;


import java.util.Date;

/**
 * @author 莫奇
 * 用户投诉类
 */
public class Feedback {

    /**
     * id
     */
    private Long id;
    /**
     * 用户Id
     */
    private Long userId;

    /**
     * 关联订单号
     */
    private Long orderId;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 意见反馈类型， 1订单反馈
     */
    private Integer feedbackTypeId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFeedbackTypeId() {
        return feedbackTypeId;
    }

    public void setFeedbackTypeId(Integer feedbackTypeId) {
        this.feedbackTypeId = feedbackTypeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", userId=" + userId +
                ", orderId=" + orderId +
                ", content='" + content + '\'' +
                ", feedbackTypeId=" + feedbackTypeId +
                ", createTime=" + createTime +
                '}';
    }
}
