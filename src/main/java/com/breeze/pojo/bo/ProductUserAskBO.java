package com.breeze.pojo.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 田旭
 * @date 2019/7/8 16:08
 */
public class ProductUserAskBO {
    /**
     * id
     */
    private Long id;
    /**
     * 商品图片
     */
    private String masterPicture;
    /**
     * 商品名
     */
    private String productName;

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

    public ProductUserAskBO setId(Long id) {
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

    public ProductUserAskBO setProductName(String productName) {
        this.productName = productName;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public ProductUserAskBO setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductUserAskBO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public ProductUserAskBO setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Integer getInventory() {
        return inventory;
    }

    public ProductUserAskBO setInventory(Integer inventory) {
        this.inventory = inventory;
        return this;
    }

    public Object getFee() {
        return fee;
    }

    public void setFee(Object fee) {
        this.fee = fee;
    }
}
