package com.breeze.pojo.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 田旭
 * @date 2019/7/8 20:15
 */
public class ProductUserBidBO {
    //id
    private Long id;

    //商品图片列表
    private String masterPicture;

    //商品名
    private String productName;

    //用户名
    private String userName;

    //价格
    private BigDecimal price;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    //创建时间
    private Date createTime;

    //买家出价费用
    private Object fee;

    public Long getId() {
        return id;
    }

    public ProductUserBidBO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getMasterPicture() {
        return masterPicture;
    }

    public void setMasterPicture(String masterPicture) {
        this.masterPicture = masterPicture;
    }

    public String getProductName() {
        return productName;
    }

    public ProductUserBidBO setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public ProductUserBidBO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductUserBidBO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ProductUserBidBO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Object getFee() {
        return fee;
    }

    public void setFee(Object fee) {
        this.fee = fee;
    }
}
