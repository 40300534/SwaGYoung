package com.breeze.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author TianXu
 * @create 2019/8/2  11:52
 * @Description UserPost
 */
public class UserPost {
    /**
     * Id
     */
    private Long id;
    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 内容
     */
    private String content;
    /**
     * 是否有效
     */
    private Integer isActive;
    /**
     * 是否是公共
     */
    private Integer isPublic;
    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm" , timezone="GMT+8")
    private Date createTime;
    /**
     * 更新时间
     * @return
     */
    private Date updateTime;
    /**
     * 更新者
     * @return
     */
    private Long updater;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(Integer isPublic) {
        this.isPublic = isPublic;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    @Override
    public String toString() {
        return "UserPost{" +
                "id=" + id +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", isActive=" + isActive +
                ", isPublic=" + isPublic +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updater=" + updater +
                '}';
    }
}
