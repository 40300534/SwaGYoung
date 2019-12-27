package com.breeze.pojo.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author 莫奇
 */
public class MessageBO {

    /**
     * 发信者Id
     */
    private Long messageSanderId;

    /**
     * 收信者Id
     */
    private Long messageReceiverId;

    /**
     * 发信时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date messageCreateTime;

    /**
     * 发信内容
     */
    private String messageText;

    /**
     * 未读数量
     */
    private Integer unreadMessageCount;

    /**
     * 消息类型id
     */
    private Integer messageTypeId;
    /**
     * 消息类型名称
     */
    private String messageTypeName;

    public Long getMessageSanderId() {
        return messageSanderId;
    }

    public void setMessageSanderId(Long messageSanderId) {
        this.messageSanderId = messageSanderId;
    }

    public Long getMessageReceiverId() {
        return messageReceiverId;
    }

    public void setMessageReceiverId(Long messageReceiverId) {
        this.messageReceiverId = messageReceiverId;
    }

    public Date getMessageCreateTime() {
        return messageCreateTime;
    }

    public void setMessageCreateTime(Date messageCreateTime) {
        this.messageCreateTime = messageCreateTime;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public String getMessageTypeName() {
        return messageTypeName;
    }

    public void setMessageTypeName(String messageTypeName) {
        this.messageTypeName = messageTypeName;
    }

    public Integer getUnreadMessageCount() {
        return unreadMessageCount;
    }

    public void setUnreadMessageCount(Integer unreadMessageCount) {
        this.unreadMessageCount = unreadMessageCount;
    }

    public Integer getMessageTypeId() {
        return messageTypeId;
    }

    public void setMessageTypeId(Integer messageTypeId) {
        this.messageTypeId = messageTypeId;
    }

    @Override
    public String toString() {
        return "MessageBO{" +
                "messageSanderId=" + messageSanderId +
                ", messageReceiverId=" + messageReceiverId +
                ", messageCreateTime=" + messageCreateTime +
                ", messageText='" + messageText + '\'' +
                ", unreadMessageCount=" + unreadMessageCount +
                ", messageTypeId=" + messageTypeId +
                ", messageTypeName='" + messageTypeName + '\'' +
                '}';
    }
}
