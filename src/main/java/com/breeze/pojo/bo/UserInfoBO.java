package com.breeze.pojo.bo;

/**
 * @author TianXu
 * @create 2019/7/29  13:31
 * @Description 前端页面点我的 需要 组装的数据
 */
public class UserInfoBO {
    /**
     * 用户头像
     */
    private String avatarUrl;
    /**
     * 用户昵称
     */
    private String displayName;
    /**
     * 购买的数量
     */
    private Long buyNum;
    /**
     * 销售的数量
     */
    private Long sellNum;
    /**
     * 用户的手机
     */
    private Long phone;
    /**
     * 用户的类型
     */
    private String typeName;

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Long buyNum) {
        this.buyNum = buyNum;
    }

    public Long getSellNum() {
        return sellNum;
    }

    public void setSellNum(Long sellNum) {
        this.sellNum = sellNum;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
