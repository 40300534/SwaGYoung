package com.breeze.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.breeze.pojo.Deposit;
import com.breeze.mapper.DepositMapper;
import com.breeze.service.IDepositService;

import java.math.BigDecimal;

/**
 * @author Created by wdnnccey
 * @date 2019-07-08 20:04
 */
@Service
public class DepositServiceImpl implements IDepositService{

    @Resource
    private DepositMapper depositMapper;

    @Override
    public int deleteById(Integer id) {
        return depositMapper.deleteById(id);
    }

    @Override
    public int add(Deposit record) {
        return depositMapper.add(record);
    }

    @Override
    public Deposit findById(Integer id) {
        return depositMapper.findById(id);
    }

    @Override
    public int update(Deposit record) {
        return depositMapper.update(record);
    }

    @Override
    public BigDecimal findDepositByUserTypeId(Integer userTypeId) {
        return depositMapper.findDepositByUserTypeId(userTypeId);
    }

}
