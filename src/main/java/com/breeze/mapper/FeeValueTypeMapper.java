package com.breeze.mapper;

import com.breeze.pojo.FeeValueType;

/**
 * @author Created by wdnnccey
 * @date 2019-07-03 15:16
 * @Description: 一句话描述该类的功能
 */
public interface FeeValueTypeMapper {
    int deleteById(Integer id);

    int add(FeeValueType record);

    FeeValueType findById(Integer id);

    int update(FeeValueType record);
}