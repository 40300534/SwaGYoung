package com.breeze.pojo;

/**
 * @author TianXu
 * @create 2019/8/2  15:35
 * @Description TODO
 */
public class MessageImage {
    /**
     * id
     */
    private  Long id;
    /**
     * 消息Id
     */
    private Long messageId;
    /**
     * 图片路径
     */
    private String url;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
