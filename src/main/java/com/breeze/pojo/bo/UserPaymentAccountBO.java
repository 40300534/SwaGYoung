package com.breeze.pojo.bo;

/**
 * @author 莫奇
 */
public class UserPaymentAccountBO {
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
     * 銀行卡名稱
     */
    private String bankName;

    @Override
    public String toString() {
        return "UserPaymentAccountBO{" +
                "id=" + id +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", paymentTypeId=" + paymentTypeId +
                ", accountNo=" + accountNo +
                ", isDefault=" + isDefault +
                ", bankName='" + bankName + '\'' +
                '}';
    }

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

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
