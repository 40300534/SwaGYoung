package com.breeze.service.impl;

import com.breeze.enums.ProductRequestStatusEnum;
import com.breeze.mapper.NewProductRequestImageMapper;
import com.breeze.mapper.NewProductRequestMapper;
import com.breeze.pojo.NewProductRequest;
import com.breeze.pojo.NewProductRequestImage;
import com.breeze.pojo.bo.NewProductRequestBO;
import com.breeze.service.INewProductRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 莫奇
 */
@Service
public class NewProductRequestServiceImpl implements INewProductRequestService {

    @Value("${uploadImage.serverUrlPrefix}")
    private String serverUrlPrefix;

    @Value("${uploadImage.productPathPrefix}")
    private String productUploadDir;

    @Autowired
    NewProductRequestMapper newProductRequestMapper;

    @Autowired
    NewProductRequestImageMapper newProductRequestImageMapper;


    @Override
    public void add(NewProductRequestBO newProductRequestBO) {
        NewProductRequest newProductRequest = new NewProductRequest();
        newProductRequest.setName(newProductRequestBO.getName());
        newProductRequest.setComment(newProductRequestBO.getComment());
        newProductRequest.setDescription(newProductRequestBO.getDescription());
        newProductRequest.setProductBrandId(newProductRequestBO.getProductBrandId());
        newProductRequest.setProductCategoryId(newProductRequestBO.getProductCategoryId());
        newProductRequest.setProductOriginId(newProductRequestBO.getProductOriginId());
        newProductRequest.setStatusId(ProductRequestStatusEnum.PRODUCT_NEW_REVIEW.getCode());
        newProductRequest.setCreateTime(new Date());
        newProductRequestMapper.add(newProductRequest);
        for (String productImagePath : newProductRequestBO.getProductRequestImages()) {
            String s = productImagePath.replace(",", "");
            String s1 = s.replace(" ", "");
            String newProductImagePath = s1.replace("\"", "");
            NewProductRequestImage newProductRequestImage = new NewProductRequestImage();
            newProductRequestImage.setDescription(newProductRequest.getDescription());
            newProductRequestImage.setNewProductRequestId(newProductRequest.getId());
            newProductRequestImage.setCreateTime(new Date());
            newProductRequestImage.setUrl(newProductImagePath);
            newProductRequestImageMapper.add(newProductRequestImage);
        }
    }

    @Override
    public NewProductRequestBO findById(Long id) {
        NewProductRequestBO newProductRequestBO = newProductRequestMapper.findById(id);
        List<String> productRequestImageList = new ArrayList<>();
        List<NewProductRequestImage> productRequestImages = newProductRequestImageMapper.findByProductRequestId(id);
        for (NewProductRequestImage productRequestImage : productRequestImages) {
            productRequestImageList.add(productRequestImage.getUrl());
        }
        newProductRequestBO.setProductRequestImages(productRequestImageList);

        return newProductRequestBO;
    }

    @Override
    public void deleteById(Long id) {
        newProductRequestMapper.deleteById(id);
        newProductRequestImageMapper.deleteByProductRequestId(id);
    }

    @Override
    public boolean isRequestSuccess(Long id) {
        NewProductRequestBO newProductRequestBO = newProductRequestMapper.findById(id);
        if (newProductRequestBO.getStatusId().equals(ProductRequestStatusEnum.PRODUCT_APPROVED_REVIEW.getCode())){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<NewProductRequestBO> findAllNewProductRequest() {
        List<NewProductRequestBO> newProductRequestBOList = new ArrayList<>();
        List<NewProductRequestBO> newProductRequestBOS = newProductRequestMapper.findAllNewProductRequest();
        for (NewProductRequestBO newProductRequestBO : newProductRequestBOS) {
            newProductRequestBOList.add(findById(newProductRequestBO.getId()));
        }
        return newProductRequestBOList;
    }

    @Override
    public List<NewProductRequestBO> findAllDeniedProductRequest() {
        List<NewProductRequestBO> newProductRequestBOList = new ArrayList<>();
        List<NewProductRequestBO> newProductRequestBOS = newProductRequestMapper.findAllDeniedProductRequest();
        for (NewProductRequestBO productRequestBO : newProductRequestBOS) {
            newProductRequestBOList.add(findById(productRequestBO.getId()));
        }
        return newProductRequestBOList;
    }

}
