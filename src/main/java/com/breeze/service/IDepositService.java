package com.breeze.service;

import com.breeze.pojo.Deposit;
import com.breeze.mapper.DepositMapper;

import java.math.BigDecimal;

/**
 * @author Created by wdnnccey
 * @date 2019-07-08 20:04
 */
public interface IDepositService {

    int deleteById(Integer id);

    int add(Deposit record);

    Deposit findById(Integer id);

    int update(Deposit record);

    /**
     * 通过用户类型查询的对应押金比例
     * @param userTypeId
     */
    BigDecimal findDepositByUserTypeId(Integer userTypeId);

}
