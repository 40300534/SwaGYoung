package com.breeze.pojo.bo;

/**
 * @author Created by wdnnccey
 * @date 2019-07-03 19:49
 * @Description: 费用
 */
public class FeeBO {

    /**
     * 费用名称
     */
    private String name;

    /**
     * 费用（几个百分比、几个固定费用）
     */
    private String value;

    /**
     * 费用类型
     */
    private String feeType;

    /**
     * 费用类型编号（1是固定，2是百分比）
     */
    private Integer feeTypeId;

    public FeeBO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public Integer getFeeTypeId() {
        return feeTypeId;
    }

    public void setFeeTypeId(Integer feeTypeId) {
        this.feeTypeId = feeTypeId;
    }
}
