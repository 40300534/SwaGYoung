package com.breeze.controller;

import com.breeze.constants.ResultMessage;
import com.breeze.enums.ResultStatusEnum;
import com.breeze.exception.ProjectxException;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.IFileUploadService;
import com.breeze.service.IProductCategoryService;
import com.breeze.service.IProductImageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by wdnnccey
 * @date 2019-06-26 00:08
 * @Description: 图片上传
 */
@Controller
@Slf4j
@RequestMapping(value = "/image")
@Api(tags = {"图片上传"})
public class FileUploadController {

    @Value("${uploadImage.productPathPrefix}")
    private String productUploadDir;

    @Autowired
    private IFileUploadService fileUploadService;

    @Autowired
    private IProductImageService productImageService;

    @Autowired
    private IProductCategoryService productCategoryService;


    /**
     * 产品图片上传
     */
    @PostMapping(value = "productImage/upload")
    @ResponseBody
    @ApiOperation("产品图片上传-支持多张")
    public ResultVO uploadProductImage(MultipartHttpServletRequest request) throws ProjectxException {
        MultiValueMap<String, MultipartFile> fileMap  = request.getMultiFileMap();
        List<String> fileList = new ArrayList<>();
        for (MultipartFile multipartFile : fileMap.toSingleValueMap().values()) {
            String imageUrl = fileUploadService.productImageUpload(multipartFile);
            fileList.add(imageUrl);
        }
        if (fileList.size() != 0) {
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS, fileList);
        } else {
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 用户头像上传
     */
    @PostMapping(value = "userAvatarImage/upload")
    @ResponseBody
    @ApiOperation("用户头像上传-仅支持单张")
    public ResultVO uploadUserAvatarImage(MultipartHttpServletRequest request,
                                        @RequestParam("userId")Long userId) throws ProjectxException {
        MultiValueMap<String, MultipartFile> fileMap  = request.getMultiFileMap();
        List<String> fileList = new ArrayList<>();
        for (MultipartFile multipartFile : fileMap.toSingleValueMap().values()) {
            String imageUrl = fileUploadService.userAvatarImageUpload(multipartFile, userId);
            fileList.add(imageUrl);
        }
        if (fileList.size() != 0) {
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getName(), fileList);
        } else {
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 用户动态图片上传
     */
    @PostMapping(value = "userTrendsImage/upload", headers = "content-type=multipart/form-data")
    @ResponseBody
    @ApiOperation("用户动态图片上传-支持多张")
    public ResultVO uploadUserTrendsImage(MultipartHttpServletRequest request,
                                        @RequestParam("userId")Long userId) throws ProjectxException {
        MultiValueMap<String, MultipartFile> fileMap  = request.getMultiFileMap();
        List<String> fileList = new ArrayList<>();
        for (MultipartFile multipartFile : fileMap.toSingleValueMap().values()) {
            String imageUrl = fileUploadService.userTrendsImageUpload(multipartFile, userId);
            fileList.add(imageUrl);
        }
        if (fileList.size() != 0) {
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS, fileList);
        } else {
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 用户反馈图片
     */
    @PostMapping(value = "feedbackImage/upload")
    @ResponseBody
    @ApiOperation("用户反馈图片上传-支持多张")
    public ResultVO uploadFeedbackImage(MultipartHttpServletRequest request,
                                      @RequestParam("userId")Long userId) throws ProjectxException {
        MultiValueMap<String, MultipartFile> fileMap  = request.getMultiFileMap();
        List<String> fileList = new ArrayList<>();
        for (MultipartFile multipartFile : fileMap.toSingleValueMap().values()) {
            String imageUrl = fileUploadService.feedbackImage(multipartFile, userId);
            fileList.add(imageUrl);
        }
        if (fileList.size() != 0) {
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS, fileList);
        } else {
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }


    /**
     * 删除产品图片
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public int delete(@PathVariable("id") Long id) {
        return productImageService.deleteById(id);
    }

}
