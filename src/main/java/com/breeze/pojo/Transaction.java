package com.breeze.pojo;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Created by wdnnccey
 * @date 2019-07-03 16:26
 * 支付记录表
 */
public class Transaction {
    /**
     * id
     */
    private Long id;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 支付方式
     */
    private Integer paymentTypeId;

    /**
     * 订单号
     */
    private String tradeNo;

    /**
     * 商家订单号
     */
    private Long outTradeNo;
    /**
     * 商家付款账户id
     */
    private String sellerAccountId;
    /**
     * 创建时间
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public Long getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(Long outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getSellerAccountId() {
        return sellerAccountId;
    }

    public void setSellerAccountId(String sellerAccountId) {
        this.sellerAccountId = sellerAccountId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentTypeId=" + paymentTypeId +
                ", tradeNo='" + tradeNo + '\'' +
                ", outTradeNo=" + outTradeNo +
                ", sellerAccountId='" + sellerAccountId + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}