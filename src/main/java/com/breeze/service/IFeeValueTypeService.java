package com.breeze.service;

import com.breeze.pojo.FeeValueType;

/**
 * @author Created by wdnnccey
 * @date 2019-07-03 15:16
 * @Description: 一句话描述该类的功能
 */
public interface IFeeValueTypeService {


    int deleteById(Integer id);

    int add(FeeValueType record);

    FeeValueType findById(Integer id);

    int update(FeeValueType record);

}
