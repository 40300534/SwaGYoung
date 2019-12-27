package com.breeze.pojo.dto;
import com.breeze.pojo.bo.FeeBO;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author TianXu
 * @create 2019/7/24  14:06
 * @Description TODO
 */
public class UserTypeDTO {
    /**
     * 角色Id
     */
    private Integer id;

    /**
     * 角色名
     */
    private String name;
    /**
     * 角色费率
     */
    private List<FeeBO> fee;
    /**
     * 保证金费率
     */
    private BigDecimal deposit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
