package com.breeze.pojo;

import java.util.Date;

/**
 * @author 莫奇
 */
public class NewProductRequestImage {
    private Long id;
    private Long newProductRequestId;
    private String url;
    private String description;
    private Date createTime;

    @Override
    public String toString() {
        return "NewProductRequestImage{" +
                "id=" + id +
                ", newProductRequestId=" + newProductRequestId +
                ", url='" + url + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNewProductRequestId() {
        return newProductRequestId;
    }

    public void setNewProductRequestId(Long newProductRequestId) {
        this.newProductRequestId = newProductRequestId;
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
}
