package com.breeze.service.impl;

import com.breeze.pojo.ProductOrigin;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import com.breeze.mapper.OriginMapper;
import com.breeze.service.IOriginService;

import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-01 08:46
 */
@Service
public class OriginServiceImpl implements IOriginService {

    @Resource
    private OriginMapper originMapper;

    @Override
    public int deleteById(Integer id) {
        return originMapper.deleteById(id);
    }

    @Override
    public int add(ProductOrigin record) {
        return originMapper.add(record);
    }


    @Override
    public ProductOrigin findById(Integer id) {
        return originMapper.findById(id);
    }

    @Override
    public int update(ProductOrigin record) {
        return originMapper.update(record);
    }

    @Override
    public List<ProductOrigin> findAll() {
        return originMapper.findAll();
    }

}
