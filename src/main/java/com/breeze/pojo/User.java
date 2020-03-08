package com.breeze.pojo;
import com.breeze.pojo.dto.Pagedto;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 用户表
 * @author tx
 */
@Entity
public class User extends Pagedto {
    /**
     * 用户Id
     */
    @Id
    private String id;
    /**
     * 用户类型Id
     */
    private Integer userTypeId;
    /**
     * 是否活跃
     */
    private Integer isActive;
    /**
     * 用户姓名
     */
    private String name;
    /**
     * 昵称
     */
    private String displayName;
    /**
     * 密码
     */
    private String password;
    /**
     * 性别
     */
    private int sex;
    /**
     * 生日
     */
    private String dob;
    /**
     * 电话号码
     */
    private String phone;
    /**
     * 用户头像
     */
    private String avatarUrl;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 用户
     */
    private Long updater;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }



    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }


    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public Long getUpdater() {
        return updater;
    }

    public void setUpdater(Long updater) {
        this.updater = updater;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userTypeId=" + userTypeId +
                ", isActive=" + isActive +
                ", name='" + name + '\'' +
                ", displayName='" + displayName + '\'' +
                ", password='" + password + '\'' +
                ", sex=" + sex +
                ", dob=" + dob +
                ", phone='" + phone + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", updater=" + updater +
                '}';
    }
}
