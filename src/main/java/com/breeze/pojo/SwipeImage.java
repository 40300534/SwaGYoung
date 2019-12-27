package com.breeze.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 *
 * @author wdnnccey
 * @date 2019-06-25 11:18
 * @Description: 轮播图
 */
public class SwipeImage {

    /**
     * id
     */
    private Integer id;

    /**
     * 图片地址
     */
    private String imageFolder;

    /**
     * 图片Url
     */
    private String url;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    /**
     * 状态 0未使用，1使用
     */
    private Integer status;

    public SwipeImage() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageFolder() {
        return imageFolder;
    }

    public void setImageFolder(String imageFolder) {
        this.imageFolder = imageFolder;
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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "SwipeImage{" +
                "id=" + id +
                ", imageFolder='" + imageFolder + '\'' +
                ", url='" + url + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                '}';
    }
}
