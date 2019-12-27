package com.breeze.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.breeze.pojo.PaymentType;
import com.breeze.mapper.PaymentTypeMapper;
import com.breeze.service.IPaymentTypeService;
/**
 * @author Created by wdnnccey
 * @date 2019-07-01 08:54
 */
@Service
public class PaymentTypeServiceImpl implements IPaymentTypeService {

    @Resource
    private PaymentTypeMapper paymentTypeMapper;

    @Override
    public int deleteById(Integer id) {
        return paymentTypeMapper.deleteById(id);
    }

    @Override
    public int add(PaymentType record) {
        return paymentTypeMapper.add(record);
    }

    @Override
    public PaymentType findById(Integer id) {
        return paymentTypeMapper.findById(id);
    }

    @Override
    public int update(PaymentType record) {
        return paymentTypeMapper.update(record);
    }

}
