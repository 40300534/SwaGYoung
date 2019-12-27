package com.breeze.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表（简表）
 *
 * @author wdnnccey
 */
public class Order {

    /**
     * Id
     */
    private Long id;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 买家id
     */
    private Long buyerId;

    /**
     * 卖家id
     */
    private Long sellerId;

    /**
     * 卖家费用
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String sellerFee;

    /**
     * 买家费用
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String buyerFee;

    /**
     * 下单时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerFee() {
        return sellerFee;
    }

    public void setSellerFee(String sellerFee) {
        this.sellerFee = sellerFee;
    }

    public String getBuyerFee() {
        return buyerFee;
    }

    public void setBuyerFee(String buyerFee) {
        this.buyerFee = buyerFee;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", price=" + price +
                ", buyerId=" + buyerId +
                ", sellerId=" + sellerId +
                ", sellerFee='" + sellerFee + '\'' +
                ", buyerFee='" + buyerFee + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
