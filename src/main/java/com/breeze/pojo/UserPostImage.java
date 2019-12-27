package com.breeze.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author TianXu
 * @create 2019/8/2  11:53
 * @Description PostImage
 */
public class UserPostImage {
    /**
     *Id
     */
    private Long id;
    /**
     * 用户postId
     */
    private Long userPostId;
    /**
     * 照片
     */
    private String url;
    /**
     * 描述
     */
    private String description;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm" , timezone="GMT+8")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserPostId() {
        return userPostId;
    }

    public void setUserPostId(Long userPostId) {
        this.userPostId = userPostId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserPostImage{" +
                "id=" + id +
                ", userPostId=" + userPostId +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
