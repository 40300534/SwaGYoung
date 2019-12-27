package com.breeze.pojo.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.util.Date;
/**
 * @author 田旭
 * @date 2019/7/8 11:51
 */
public class ProductAskBO {
    /**
     * id
     */
    private Long id;
    /**
     * productId
     */
    private Long productId;
    /**
     * 商品名
     */
    private String productName;
    /**
     * 商品图
     */
    private String masterPicture;
    /**
     * 用户名
     */
    private String userName;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;

    /**
     * 库存 （每一个卖家都有不同的库存）
     */
    private Integer inventory;

    /**
     * 卖家出价费用信息（平台交易费、运费等）
     */
    private Object fee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getMasterPicture() {
        return masterPicture;
    }

    public void setMasterPicture(String masterPicture) {
        this.masterPicture = masterPicture;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getInventory() {
        return inventory;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }

    public Object getFee() {
        return fee;
    }

    public void setFee(Object fee) {
        this.fee = fee;
    }
}
