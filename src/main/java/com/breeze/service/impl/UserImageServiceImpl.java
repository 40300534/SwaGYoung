package com.breeze.service.impl;

import com.breeze.enums.ResultStatusEnum;
import com.breeze.mapper.UserImageMapper;
import com.breeze.pojo.User;
import com.breeze.pojo.UserImage;
import com.breeze.pojo.bo.UserImageBO;
import com.breeze.pojo.dto.UserImageDTO;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.util.UploadPictureUtils;
import com.breeze.service.IUserImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author 莫奇
 */
@Service
public class UserImageServiceImpl implements IUserImageService {

    @Autowired
    private UserImageMapper userImageMapper;

    @Value("${uploadImage.userImagePathPrefix}")
    private String userImagePathPrefix;

    @Value("${uploadImage.serverUrlPrefix}")
    private String serverUrlPrefix;

    @Override
    public List<UserImage> findAll() {
        List<UserImage> userImageList = userImageMapper.findAll();
        for (UserImage userImage : userImageList) {
            userImage.setUrl(serverUrlPrefix + userImage.getUrl());
        }
        return userImageList;
    }

    /**
     * 根据UserId查询用户动态
     *
     * @param userId
     * @return
     */
    @Override
    public List<UserImageBO> findByUserId(Long userId) {
        List<UserImageBO> userImageBOS = new ArrayList<>();
        UserImageBO ImageBO = new UserImageBO();
        List<String> userImageUrls = new ArrayList<>();
        List<UserImageDTO> userImageList = userImageMapper.findByUserId(userId);
        for (UserImageDTO userImageDTO : userImageList) {
            if (userImageDTO.getDescription().equals(ImageBO.getDescription())
                    && userImageDTO.getCreateTime().equals(ImageBO.getCreateTime())){

            }
            ImageBO.setUserAvatar(userImageDTO.getUserAvatar());
            ImageBO.setCreateTime(userImageDTO.getCreateTime());
            ImageBO.setUserName(userImageDTO.getUserName());
            ImageBO.setPhone(userImageDTO.getPhone());
            ImageBO.setDescription(userImageDTO.getDescription());
            userImageUrls.add(userImageDTO.getUserImageUrl());
            ImageBO.setUserImageUrls(userImageUrls);
            userImageBOS.add(ImageBO);
        }



        return userImageBOS;
    }

    /**
     * 根据Id查动态
     *
     * @param id
     * @return
     */
    @Override
    public UserImage findById(Long id) {
        UserImage userImage = userImageMapper.findById(id);
        userImage.setUrl(serverUrlPrefix + userImage.getUrl());
        return userImage;
    }

    /**
     * 新增动态
     *
     * @param userImageBO
     */
    @Override
    public void add(UserImageBO userImageBO) {

        List<String> userImagePaths = userImageBO.getUserImageUrls();
        for (String userImagePath : userImagePaths) {
            UserImage userImage = new UserImage();
            String s = userImagePath.replace(",", "");
            String s1 = s.replace(" ", "");
            String newPath = s1.replace("\"", "");
            userImage.setUrl(newPath);
            userImage.setUserId(userImageBO.getUserId());
            userImage.setDescription(userImageBO.getDescription());
            userImage.setCreateTime(new Date());
            userImage.setModifiedTime(new Date());
            userImageMapper.add(userImage);
        }
       /* for (MultipartFile multipartFile : file) {
            if (!multipartFile.isEmpty()) {
                String newProfileName = UploadPictureUtils.generateUUIDFilePath(multipartFile.getOriginalFilename());
                userImage.setUrl(newProfileName);
                userImage.setCreateTime(new Date());
                userImage.setModifiedTime(new Date());
                userImageMapper.add(userImage);
                UploadPictureUtils.uploadPicture(newProfileName, multipartFile, userImagePathPrefix);

                return true;
            }else {
                return false;
            }
        }
         return false;*/
    }

    /**
     * 根据Id删除动态
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        UserImage userImage = findById(id);
        File file = new File(userImage.getUrl());
        file.delete();
        userImageMapper.deleteById(id);
    }

}
