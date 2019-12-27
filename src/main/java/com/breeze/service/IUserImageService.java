package com.breeze.service;

import com.breeze.pojo.UserImage;
import com.breeze.pojo.bo.UserImageBO;
import com.breeze.pojo.vo.ResultVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 莫奇
 */
public interface IUserImageService {

    /**
     * 查询全部动态
     * @return
     */
    List<UserImage> findAll();

    /**
     * 根据UserId查询用户动态
     * @param userId
     * @return
     */
    List<UserImageBO> findByUserId(Long userId);

    /**
     * 根据Id查动态
     * @param id
     * @return
     */
    UserImage findById(Long id);

    /**
     * 新增动态
     * @param userImageBO
     */
    void add(UserImageBO userImageBO );

    /**
     * 根据Id删除动态
     * @param id
     */
    void deleteById(Long id);
}
