package com.breeze.mapper;

import com.breeze.pojo.User;
import com.breeze.pojo.bo.UserInfoBO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * @author tx
 */
@Repository
public interface UserMapper {

    /**
     * 查询所有用户
     * @return List<User>
     */
    List<User> findAll();

    /**
     * 添加用户
     * @param user
     */
    void add(User user);

    /**
     * 通过手机号查询用户
     * @param phone
     * @return User
     */
    User findByPhone(String phone);

    /**
     * 通过id查询用户
     * @param id
     * @return User
     */
    User findById(Long id);

    /**
     * 通过id查询用户
     * @param id
     * @return User
     */
    @Select("SELECT * FROM  user  WHERE  id=#{id}")
    User findBy(@Param("id") String id);

    /**
     * 修改用户信息
     * @param user
     */
    void update(@Param("user") User user);

    /**
     * 修改用户头像
     * @param user
     */
    int updateAvatar(User user);

    /**
     * 删除用户
     * @param userId
     * @return
     */
    Integer delete(Long userId);

    /**
     * 查用户姓名的用户
     * @param name
     * @return
     */
    List<User> findByName(String name);

    /**
     * 用户登录信息
     * @param userId
     * @return
     */
    UserInfoBO findUserInfo(Long userId);

    /**
     * 修改手机号
     * @param phone
     * @return
     */
    Long updatePhone(Long userId,String phone);

    /**
     * 修改密码
     * @param userId
     * @param newPassword
     * @return
     */
    Long updatePassword(Long userId, String newPassword);







    @Select("SELECT * FROM user WHERE name=#{username}")
    public User findUserName(@Param("username") String username);

//    @Insert("INSERT INTO user " +
//            "(id,userTypeId,isActive,name,displayName,password,sex,dob,phone,avatarUrl,createTime,updater) " +
//            "VALUES (#{id},#{userTypeId},#{isActive},#{name},#{displayName},#{password},#{sex},#{dob},#{phone},#{avatarUrl}," +
//            "#{createTime},#{updater})")
//    void save(User user);

    @Select("SELECT * FROM user WHERE name=#{name} AND password=#{password}")
    User findUserAndPasswrod(@Param("name") String name, @Param("password") String password);

    @Delete("DELETE FROM user WHERE id in (${ids})")
    void del(@Param("ids") String ids);

//    @Update("UPDATE user SET user_type_Id=#{user_type_Id},is_active=#{is_active},name=#{name},display_name=#{display_name},password=#{password}," +
//            "sex=#{sex},dob=#{dob},phone=#{phone},avatar_url=#{avatar_url},create_time=#{create_time},update_time=#{update_time},updater=#{updater}")
//    void modification(User user);
}
