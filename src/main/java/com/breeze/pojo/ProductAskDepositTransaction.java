package com.breeze.pojo;

import java.util.Date;

/**
 * @author Created by wdnnccey
 * @date 2019-07-09 09:18
 * @Description: 一句话描述该类的功能
 */
public class ProductAskDepositTransaction {
    /**
     *id
     */
    private Long id;

    /**
     * 商品AskId
     */
    private Long productAskId;

    /**
     * 交易记录Id
     */
    private Long transactionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductAskId() {
        return productAskId;
    }

    public void setProductAskId(Long productAskId) {
        this.productAskId = productAskId;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
}