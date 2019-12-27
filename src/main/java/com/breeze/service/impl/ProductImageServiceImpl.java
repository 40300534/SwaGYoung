package com.breeze.service.impl;

import com.breeze.pojo.bo.ProductImageInfoBO;
import com.breeze.service.IProductImageService;
import com.breeze.util.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import com.breeze.pojo.ProductImage;
import com.breeze.mapper.ProductImageMapper;

import java.io.File;
import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-06-26 00:23
 */
@Service
public class ProductImageServiceImpl implements IProductImageService {

    @Resource
    private ProductImageMapper productImageMapper;

    @Value("${uploadImage.productPathPrefix}")
    private String productUploadDir;

    @Override
    public int deleteById(Long id) {
        ProductImage productImage = productImageMapper.findById(id);
        if (productImage == null) {
            return 0;
        }
        if (productImage.getUrl().equals("") || productImage.getUrl() == null) {
            return 0;
        }
        // /Users/wdnnccey/projectx/resource/image/ /2019/06/26/68B83804F182606827AB65BBC2C67C79/1.jpg
        File file = new File("/Users/wdnnccey/projectx/resource/image/" + productImage.getUrl());
        //上级目录
        String strParentDirectory = file.getParent();
        boolean result = FileUtils.deleteFolder(strParentDirectory);
        if (result) {
            productImageMapper.deleteById(id);
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public int add(ProductImage record) {
        return productImageMapper.add(record);
    }

    @Override
    public ProductImage findById(Long id) {
        return productImageMapper.findById(id);
    }

    @Override
    public int update(ProductImage record) {
        return productImageMapper.update(record);
    }

    @Override
    public List<ProductImageInfoBO> findProductImageInfoByproductCategoryId(Integer productCategoryId) {
        return productImageMapper.findProductImageInfoByproductCategoryId(productCategoryId);
    }

    @Override
    public int findMaxPictureNumber(Long productId) {
        return productImageMapper.findMaxPictureNumber(productId);
    }

    @Override
    public String findMasterPictureByProductId(Long productId) {
        return productImageMapper.findMasterPictureByProductId(productId);
    }

    @Override
    public List<ProductImage> findProductAllImageByProductId(Long productId) {
        return productImageMapper.findProductAllImageByProductId(productId);
    }

}
