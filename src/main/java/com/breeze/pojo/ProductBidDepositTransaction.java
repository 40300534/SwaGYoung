package com.breeze.pojo;

import java.util.Date;

/**
 * @author Created by wdnnccey
 * @date 2019-07-09 09:18
 * @Description: 一句话描述该类的功能
 */
public class ProductBidDepositTransaction {
    /**
    * id
    */
    private Long id;

    /**
    * 
    */
    private Long productBidId;

    /**
    * 
    */
    private Long transactionId;

    /**
    * 
    */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductBidId() {
        return productBidId;
    }

    public void setProductBidId(Long productBidId) {
        this.productBidId = productBidId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}