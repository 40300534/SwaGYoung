package com.breeze.service.impl;

import com.breeze.enums.ResultStatusEnum;
import com.breeze.enums.UserPromptStatusEnum;
import com.breeze.mapper.UserMapper;
import com.breeze.pojo.User;
import com.breeze.pojo.bo.UserInfoBO;
import com.breeze.util.UploadPictureUtils;
import com.breeze.service.IUserService;
import com.breeze.util.Md5TokenGenerator;
import com.breeze.util.RedisLoginUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @author XiaoCi
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    public UserMapper userMapper;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private RedisLoginUtils redisLoginUtil;

    @Value("${uploadImage.userAvatarPathPrefix}")
    private String userAvatarPathPrefix;

    @Value("${uploadImage.serverUrlPrefix}")
    private String serverUrlPrefix;

    /**
     * 查询全部用户
     *
     * @return
     */
    @Override
    public List<User> findAll() {
        List<User> userList = userMapper.findAll();
        for (User user : userList) {
            user.setAvatarUrl(serverUrlPrefix + user.getAvatarUrl());
        }
        return userList;
    }

    /**
     * 用户添加
     *
     * @return
     */
    @Override
    public void add(User user) {
        //创建时间
        user.setCreateTime(new Date());
        // 密码加密
        user.setPassword(encoder.encode(user.getPassword()));
        user.setUpdateTime(new Date());
        userMapper.add(user);
    }

    /**
     * 验证手机号密码是否匹配
     *
     * @param phone
     * @param password
     * @return
     */
    @Override
    public User login(String phone, String password) {
        User user = userMapper.findByPhone(phone);
        if (user != null && encoder.matches(password, user.getPassword())) {
            String token = Md5TokenGenerator.generate(phone, password);
            //设置key生存时间，当key过期时，它会被自动删除，时间是秒
            redisLoginUtil.set(token, user.getId());
            redisLoginUtil.expire(token, 1000 * 60 * 60 * 24);
            Long currentTime = System.currentTimeMillis();
            redisLoginUtil.set(user.getId() + token, currentTime.toString());
            redisLoginUtil.expire("" + user.getId() + token, 1000 * 60 * 60 * 24);
            System.out.println(redisLoginUtil.get(token));
            redisLoginUtil.set("" + user.getId(), token);
            redisLoginUtil.expire("" + user.getId(), 1000 * 60 * 60 * 24);
            return user;
        }

        return new User();
    }

    /**
     * 根据手机查询
     *
     * @return
     */
    @Override
    public User findByPhone(String phone) {
        User user = userMapper.findByPhone(phone);
        if (user != null) {
            user.setAvatarUrl(serverUrlPrefix + user.getAvatarUrl());
        }
        return user;
    }

    /**
     * 根据ID查询
     *
     * @return
     */
    @Override
    public User findById(Long id) {
        User user = userMapper.findById(id);
        if (user != null) {
            user.setAvatarUrl(serverUrlPrefix + user.getAvatarUrl());
        } else {
            return null;
        }
        return user;
    }

    /**
     * 用户修改
     *
     * @return
     */
    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    /**
     * 修改头像
     *
     * @param imageFile
     * @param userId
     * @return
     */
    @Override
    public boolean updateAvatar(MultipartFile imageFile, Long userId) {

        String fileName = "";
        if (!imageFile.isEmpty()) {
            User user = new User();

            fileName = UploadPictureUtils.generateUUIDUserAvaterFilePath(imageFile.getOriginalFilename(), userId);
            // 路径存库
            user.setId(userId);
            user.setAvatarUrl(fileName);
            userMapper.updateAvatar(user);


            UploadPictureUtils.uploadPicture(fileName, imageFile, userAvatarPathPrefix);
            return true;
        } else {
            return false;
        }

    }

    /**
     * 删除用户
     *
     * @param userId
     * @return
     */
    @Override
    public Integer delete(Long userId) {
        return userMapper.delete(userId);
    }

    /**
     * 姓名查找
     *
     * @param name
     * @return
     */
    @Override
    public List<User> findByName(String name) {
        List<User> userList = userMapper.findByName(name);
        userList.forEach(u -> {
            u.setPassword("密码被隐藏 管理员不可查看");
        });
        return userList;
    }

    /**
     * 用户登录信息
     *
     * @param userId
     * @return
     */
    @Override
    public UserInfoBO findUserInfo(Long userId) {
        UserInfoBO userInfoBO = userMapper.findUserInfo(userId);
        userInfoBO.setAvatarUrl(serverUrlPrefix + userInfoBO.getAvatarUrl());
        return userInfoBO;
    }

    /**
     * 修改手机号
     *
     * @param phone
     * @return
     */
    @Override
    public Integer updatePhone(Long userId, String phone) {
        User user = userMapper.findById(userId);
        User byPhone = userMapper.findByPhone(phone);
        //存在该手机
        if (byPhone != null) {
            //账户已经被其他用户绑定
            return UserPromptStatusEnum.USER_ACCOUNT_BOUND_BY_OTHERS.getCode();
        }
        //用户不为空 传的新手机和旧手机不一致进入
        if (user!=null&&!phone.equals(user.getPhone())) {
            //成功 200
            return userMapper.updatePhone(userId,phone) == 1 ? UserPromptStatusEnum.USER_UPDATE_PHONE_SUCCESS.getCode() : UserPromptStatusEnum.USER_SERVER_ERROR.getCode();
        }else if (user==null){
            //用户不存在 209
            return UserPromptStatusEnum.USER_ACCOUNT_NOT_FOUND.getCode();
        }else {
            //服务器内部故障 500
            return UserPromptStatusEnum.USER_SERVER_ERROR.getCode();
        }
    }

    /**修改密码
     * @param userId
     * @param newPassword
     * @return
     */
    @Override
    public Integer updatePassword(Long userId, String newPassword) {
        User user = findById(userId);
        //用户存在
        if (user != null) {
            //密码比较  新密码 数据库原密码
            if (encoder.matches(newPassword, user.getPassword())) {
                return UserPromptStatusEnum.USER_PASSWORD_SAME_ERROR.getCode();
            } else {
                //去修改
                return userMapper.updatePassword(userId, encoder.encode(newPassword))==1? ResultStatusEnum.SUCCESS.getCode(): UserPromptStatusEnum.USER_SERVER_ERROR.getCode();
            }
        } else {
            return UserPromptStatusEnum.USER_SERVER_ERROR.getCode();
        }
    }

    /**
     * 查找数据库密码和旧密码的参数密码比较
     * @param userId
     * @param oldPassword
     * @return
     */
    @Override
    public boolean findOldPassword(Long userId, String oldPassword) {
        //根据前台传过来的Id查询
        User user = userMapper.findById(userId);
        //用户存在 旧密码  数据库存的密码
        if (user != null && encoder.matches(oldPassword, user.getPassword())) {
            return true;
        } else {
            //否则
            return false;
        }
    }
}

