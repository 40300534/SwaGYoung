package com.breeze.pojo.dto;

import com.breeze.pojo.UserAddress;
import com.breeze.pojo.bo.FeeBO;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author TianXu
 * @create 2019/7/23  15:01
 * @Description 后台 用户管理的数据
 */
public class UserDTO {
    /**
     * 用户Id
     */
    private Long id;
    /**
     * 用户类型
     *
     * @return
     */
    private String userType;
    /**
     * 买卖费率
     */
    private List<FeeBO> fee;
    /**
     * 保证金费率
     */
    private BigDecimal deposit;

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 昵称
     */
    private String displayName;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date createTime;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 生日
     */
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dob;
    /**
     * 电话号码
     */
    private String phone;

    /**
     * 是否有效用户
     */
    private Integer isActive;

    /**
     * 用户头像
     */
    private String avatarUrl;
    /**
     * 违规次数
     */
    private Long infractions;
    /**
     * 卖出单数
     */
    private Long salesQuantity;
    /**
     * 买入单数
     */
    private Long purchaseQuantity;
    /**
     * 收藏商品数
     */
    private Long favorate;
    /**
     * 等级
     */
    private String grade;
    /**
     * 总资产
     */
    private BigDecimal asset;
    /**
     * 上次登录时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" , timezone="GMT+8")
    private Date lastLoginTime;
    /**
     * 总登陆次数
     */
    private Long loginTotalDegree;
    /**
     * 用户所有地址
     *
     * @return
     */
    private List<UserAddress> userAddress;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public List<FeeBO> getFee() {
        return fee;
    }

    public void setFee(List<FeeBO> fee) {
        this.fee = fee;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public Long getInfractions() {
        return infractions;
    }

    public void setInfractions(Long infractions) {
        this.infractions = infractions;
    }

    public Long getSalesQuantity() {
        return salesQuantity;
    }

    public void setSalesQuantity(Long salesQuantity) {
        this.salesQuantity = salesQuantity;
    }

    public Long getPurchaseQuantity() {
        return purchaseQuantity;
    }

    public void setPurchaseQuantity(Long purchaseQuantity) {
        this.purchaseQuantity = purchaseQuantity;
    }

    public Long getFavorate() {
        return favorate;
    }

    public void setFavorate(Long favorate) {
        this.favorate = favorate;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public BigDecimal getAsset() {
        return asset;
    }

    public void setAsset(BigDecimal asset) {
        this.asset = asset;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Long getLoginTotalDegree() {
        return loginTotalDegree;
    }

    public void setLoginTotalDegree(Long loginTotalDegree) {
        this.loginTotalDegree = loginTotalDegree;
    }

    public List<UserAddress> getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(List<UserAddress> userAddress) {
        this.userAddress = userAddress;
    }
}
