package com.breeze.service.impl;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.breeze.mapper.FeeValueTypeMapper;
import com.breeze.pojo.FeeValueType;
import com.breeze.service.IFeeValueTypeService;

/**
 * @author Created by wdnnccey
 * @date 2019-07-03 15:16
 */
@Service
public class FeeValueTypeServiceImpl implements IFeeValueTypeService {

    @Resource
    private FeeValueTypeMapper feeValueTypeMapper;

    @Override
    public int deleteById(Integer id) {
        return feeValueTypeMapper.deleteById(id);
    }

    @Override
    public int add(FeeValueType record) {
        return feeValueTypeMapper.add(record);
    }

    @Override
    public FeeValueType findById(Integer id) {
        return feeValueTypeMapper.findById(id);
    }

    @Override
    public int update(FeeValueType record) {
        return feeValueTypeMapper.update(record);
    }

}
