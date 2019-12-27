package com.breeze.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Created by wdnnccey
 * @date 2019-07-25 10:57
 * @Description: 图片上传
 */
public interface IFileUploadService {

    /**
     * 公共上传接口
     * @throws Exception
     */
//    String upload(MultipartFile file, String imageCategory) throws Exception;

    /**
     * 产品图片
     */
    String productImageUpload(MultipartFile file);

    /**
     * 用户头像图片
     */
    String userAvatarImageUpload(MultipartFile file, Long userId);

    /**
     * 用户动态图片
     */
    String userTrendsImageUpload(MultipartFile file, Long userId);

    /**
     * 意见反馈图片
     */
    String feedbackImage(MultipartFile file, Long userId);
}
