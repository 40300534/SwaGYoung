package com.breeze.pojo.dto;

/**
 * @author Created by wdnnccey
 * @date 2019-07-01 11:39
 * @Description: 商品特有属性 key value
 */
public class ProductUniqueAttributeDTO {

    /**
     * 备注（eg:容量）
     */
    private String displayName;

    /**
     * 属性Key（eg:volume）
     */
    private String key;

    /**
     * 属性Value（eg:500L）
     */
    private String value;

    public ProductUniqueAttributeDTO() {
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ProductUniqueAttributeDto{" +
                "remark='" + displayName + '\'' +
                ", key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
