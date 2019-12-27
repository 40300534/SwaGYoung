package com.breeze.pojo.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 田旭
 * @date 2019/7/9 15:04
 */
public class ProductBidBO {
    //id
    private Long id;

    //商品id
    private Long productId;

    //商品名
    private String productName;

    //商品图片列表
    private String masterPicture;

    //用户名
    private String userName;

    //价格
    private BigDecimal price;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    //创建时间
    private Date createTime;


    //买家出价费用
    private Object  fee;

    public Long getId() {
        return id;
    }

    public Long getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getMasterPicture() {
        return masterPicture;
    }

    public String getUserName() {
        return userName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Object getFee() {
        return fee;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setMasterPicture(String masterPicture) {
        this.masterPicture = masterPicture;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public void setFee(Object fee) {
        this.fee = fee;
    }

    @Override
    public String toString() {
        return "ProductBidBO{" +
                "id=" + id +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", masterPicture='" + masterPicture + '\'' +
                ", userName='" + userName + '\'' +
                ", price=" + price +
                ", createTime=" + createTime +
                ", fee=" + fee +
                '}';
    }
}
