package com.breeze.service.impl;

import com.breeze.enums.ImageCategoryEnum;
import com.breeze.constants.ResultMessage;
import com.breeze.enums.ResultStatusEnum;
import com.breeze.exception.ProjectxException;
import com.breeze.service.IFileUploadService;
import com.breeze.util.UploadPictureUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Created by wdnnccey
 * @date 2019-07-25 11:03
 * @Description:
 */
@Service
@Slf4j
public class FileUploadServiceImpl implements IFileUploadService {

    /**
     * 产品图片
     */
    @Value("${uploadImage.productPathPrefix}")
    private String productImagePrefix;

    /**
     * 用户头像
     */
    @Value("${uploadImage.userAvatarPathPrefix}")
    private String userAvatarImagePrefix;

    /**
     * 用户动态
     */
    @Value("${uploadImage.userImagePathPrefix}")
    private String userTrendsImagePrefix;

    /**
     * 意见反馈
     */
    @Value("${uploadImage.feedbackImagePrefix}")
    private String feedbackImagePrefix;


    private String upload(MultipartFile file, Integer imageCategoryEnumId , Long userId) throws ProjectxException {

        //如果参数为空，抛出异常
        if (file.isEmpty() || imageCategoryEnumId == null || "".equals(imageCategoryEnumId)) {
            throw new ProjectxException(ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
        if (!file.isEmpty()) {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            // 文件前缀地址
            String parentFilePath = null;
            switch (imageCategoryEnumId) {
                case 1:
                    parentFilePath = productImagePrefix;
                    break;
                case 2:
                    parentFilePath = userAvatarImagePrefix;
                    break;
                case 3:
                    parentFilePath = userTrendsImagePrefix;
                    break;
                case 4:
                    parentFilePath = feedbackImagePrefix;
                    break;
                default:
                    parentFilePath = null;
            }
            if (parentFilePath == null) {
                throw new ProjectxException(ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
            }
            String filePath = "";
            if (parentFilePath.equals(productImagePrefix)){
                filePath = UploadPictureUtils.generateUUIDProductImageFilePath(fileName);
            }else if (parentFilePath.equals(userAvatarImagePrefix)){
                filePath = UploadPictureUtils.generateUUIDUserAvaterFilePath(fileName , userId);
            }else if (parentFilePath.equals(userTrendsImagePrefix)){
                filePath = UploadPictureUtils.generateUUIDUserImageFilePath(fileName , userId);
            }else {
                filePath = UploadPictureUtils.generateUUIDUserAvaterFilePath(fileName , userId);
            }

            // /Users/wdnnccey/projectx/resource/image/2019/06/26/68B83804F182606827AB65BBC2C67C79/1.jpg
            File dest = new File(parentFilePath + filePath);
            // 检测是否存在目录
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
                //本地地址+日期地址+uuid路径地址+uuid文件名+文件类型
                log.info("上传成功后的文件路径未：" + parentFilePath + filePath);
                return filePath;
            } catch (IllegalStateException | IOException e) {
                throw new ProjectxException(ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
            }
        }
        return null;
    }

    @Override
    public String productImageUpload(MultipartFile file) {
        return upload(file, ImageCategoryEnum.PRODUCT_IMAGE.getCode(),null);
    }

    @Override
    public String userAvatarImageUpload(MultipartFile file, Long userId) {
        return upload(file, ImageCategoryEnum.USER_AVATAR_IMAGE.getCode(), userId);
    }

    @Override
    public String userTrendsImageUpload(MultipartFile file, Long userId) {
        return upload(file, ImageCategoryEnum.USER_TRENDS_IMAGE.getCode(),userId);
    }

    @Override
    public String feedbackImage(MultipartFile file, Long userId) {
        return upload(file, ImageCategoryEnum.FEEDBACK_IMAGE.getCode(), userId);
    }
}
