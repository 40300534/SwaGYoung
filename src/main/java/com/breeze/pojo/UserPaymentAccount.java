package com.breeze.pojo;

import java.util.Date;

/**
 * @author Created by wdnnccey
 * @date 2019-07-03 16:31
 * @Description: 一句话描述该类的功能
 */
public class UserPaymentAccount {
    /**
    * id
    */
    private Long id;

    /**
    * 用户id
    */
    private Long userId;

    /**
     * 用户姓名
     */
    private String userName;

    /**
    * 支付类型Id
    */
    private Integer paymentTypeId;

    /**
    * 付款账号
    */
    private String accountNo;

    /**
    * 是否是默认方式
    */
    private Integer isDefault;
    /**
     * 创建时间
     * @return
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(Integer paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "UserPaymentAccount{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", paymentTypeId=" + paymentTypeId +
                ", accountNo='" + accountNo + '\'' +
                ", isDefault=" + isDefault +
                ", createTime=" + createTime +
                '}';
    }
}