package com.breeze.mapper;

import com.breeze.pojo.UserImage;
import com.breeze.pojo.bo.UserImageBO;
import com.breeze.pojo.dto.UserImageDTO;

import java.util.List;

/**
 * @author 莫奇
 */
public interface UserImageMapper {

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
    List<UserImageDTO> findByUserId(Long userId);

    /**
     * 根据Id查询动态
     * @param id
     * @return
     */
    UserImage findById(Long id);

    /**
     * 新增动态
     * @param userImage
     */
    void add(UserImage userImage);

    /**
     * 修改动态
     * @param userImage
     */
    void update(UserImage userImage);

    /**
     * 根据id删除动态
     * @param id
     */
    void deleteById(Long id);
}
