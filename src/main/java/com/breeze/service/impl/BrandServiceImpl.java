package com.breeze.service.impl;

import com.breeze.pojo.ProductBrand;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.breeze.mapper.BrandMapper;
import com.breeze.service.IBrandService;

import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-07-01 08:32
 */
@Service
public class BrandServiceImpl implements IBrandService {

    @Resource
    private BrandMapper brandMapper;

    @Override
    public int deleteById(Integer id) {
        return brandMapper.deleteById(id);
    }

    @Override
    public int add(ProductBrand record) {
        return brandMapper.add(record);
    }

    @Override
    public ProductBrand findById(Integer id) {
        return brandMapper.findById(id);
    }

    @Override
    public int update(ProductBrand record) {
        return brandMapper.update(record);
    }

    @Override
    public List<ProductBrand> findAll() {
        return brandMapper.findAll();
    }


}
