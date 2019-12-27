package com.breeze.controller;

import com.alibaba.fastjson.JSONObject;
import com.breeze.annotation.Admin;
import com.breeze.annotation.LoginRequired;
import com.breeze.constants.ResultMessage;
import com.breeze.enums.ResultStatusEnum;
import com.breeze.enums.UserPromptStatusEnum;
import com.breeze.pojo.User;
import com.breeze.pojo.UserAddress;
import com.breeze.pojo.bo.UserInfoBO;
import com.breeze.pojo.dto.UserDTO;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.*;
import com.breeze.util.RedisLoginUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * @author tx
 */
@RestController
@RequestMapping(value = "/user")
@Api(tags = {"User相关管理Login/Register"})
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private RedisLoginUtils redisLoginUtil;

    @Autowired
    private IUserTypeService userTypeService;

    @Autowired
    private IUserAddressService userAddressService;

    @Autowired
    private IFeeService feeService;

    @Autowired
    private IDepositService depositService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IUserFavorateService userRavorateService;

    @Autowired
    private IProductBidService productBidService;

    @Autowired
    private IProductAskService productAskService;

    /**
     * 后台用户管理查询全部用户数据
     * 只有管理员可以调API
     * <p>
     * return
     */
    @ApiOperation("用户管理页面")
//    @LoginRequired
//    @Admin
    @GetMapping
    public PageInfo<UserDTO> findAll(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                     @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        //组装DTO
        List<UserDTO> userDTOList = new ArrayList<>();
        ;
        try {
            //查询所有用户信息
            List<User> userList = userService.findAll();
            //遍历所有用户信息
            userList.forEach(user -> {
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(user, userDTO);
                userDTO.setId(user.getId());
                String name = userTypeService.findById(user.getUserTypeId().longValue()).getName();
                userDTO.setUserType(name);
                userDTO.setFee(feeService.findFeeByUserTypeId(user.getUserTypeId()));
                userDTO.setDeposit(depositService.findDepositByUserTypeId(user.getUserTypeId()));
                //违规的次数 TODO
                userDTO.setInfractions(9L);
                Long orderSalesQuantity = orderService.findSalesQuantity(user.getId());
                Long salesQuantity = productAskService.sellQuantity(user.getId());
                //为空默认为0
                userDTO.setSalesQuantity((orderSalesQuantity == null ? orderSalesQuantity = 0L : orderSalesQuantity) +
                        (salesQuantity == null ? salesQuantity = 0L : salesQuantity));
                Long orderPurchaseQuantity = orderService.findPurchaseQuantity(user.getId());
                Long buyQuantity = productBidService.findBuyQuantity(user.getId());
                //为空默认为0
                userDTO.setPurchaseQuantity((orderPurchaseQuantity == null ? orderPurchaseQuantity = 0L : orderPurchaseQuantity) +
                        (buyQuantity == null ? buyQuantity = 0L : buyQuantity));
                ;
                Long favorateNumber = userRavorateService.userFavorateNumber(user.getId());
                //收藏商品数为空默认为0
                userDTO.setFavorate(favorateNumber == null ? favorateNumber = 0L : favorateNumber);
                userDTO.setGrade(name);
                //总资产 TODO
                DecimalFormat df = new DecimalFormat("###.##");
                userDTO.setAsset(new BigDecimal(df.format(215.21)));
                //上次登录时间 TODO
                userDTO.setLastLoginTime(new Date());
                //总登录次数 TODO
                userDTO.setLoginTotalDegree(169L);
                List<UserAddress> userAddresses = userAddressService.findByUserId(user.getId());
                userDTO.setUserAddress(userAddresses);
                userDTOList.add(userDTO);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new PageInfo<>(userDTOList);
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     *           return
     */
    @ApiOperation("通过ID查询用户")
    @PostMapping(value = "/{id}")
    @LoginRequired
    public ResultVO findById(@PathVariable Long id) {
        try {
            User user = userService.findById(id);
            user.setPassword("隐藏密码");
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS, user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);

        }
    }


    /**
     * 修改
     * <p>
     * param user
     */
    @ApiOperation("用户修改信息")
    @PutMapping(value = "/{id}")
    @LoginRequired
    public ResultVO update(@RequestBody User user, @PathVariable Long id) {
        try {
            user.setId(id);
            userService.update(user);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 删除用户
     */
    @DeleteMapping(value = "/{userId}")
    public ResultVO delete(@PathVariable Long userId) {
        try {
            Integer delete = userService.delete(userId);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), "删除成功", delete);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), "删除失败");
        }
    }

    /**
     * 后台新增用户
     * @param user
     * @return
     */
    @LoginRequired
    @Admin
    @ApiOperation("添加用户")
    @PostMapping(value = "/add")
    public ResultVO add(@RequestBody User user) {
        try {
            User byPhone = userService.findByPhone(user.getPhone());
            if (byPhone == null) {
                userService.add(user);
                return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), "添加成功");
            } else {
                return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), "该手机号已经被绑定啦，请您更换其他手机号再进行注册。");

            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), "服务器开小差了 暂时比较忙");
        }
    }

    /**
     * 根据姓名查找用户
     *
     * @param name
     * @return
     */
    @ApiOperation("搜索用户")
    @GetMapping(value = "/search")
//    @LoginRequired
//    @Admin
    public PageInfo<User> search(@RequestParam("name") String name,
                                 @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                 @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        List<User> userList = null;
        try {
            PageHelper.startPage(pageNum, pageSize);
            userList = userService.findByName(name);
            return new PageInfo<>(userList);
        } catch (Exception e) {
            e.printStackTrace();
            return new PageInfo<>(userList);
        }
    }

    /**
     * 用户注册
     * <p>
     * param user 用户相关信息
     * return
     */
    @ApiOperation("用户注册")
    @PostMapping
    public ResultVO register(@RequestBody User user) {
        try {
            User byPhone = userService.findByPhone(user.getPhone());
            if (byPhone == null) {
                //将用户信息保存到数据库（密码加密）
                userService.add(user);
                return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), "注册成功");
            } else {
                return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), "该手机号已经被绑定啦，请您更换其他手机号再进行注册。");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), "服务器繁忙！");
        }
    }

    /**
     * 用户登录
     *
     * @param phone
     * @param password
     * @return
     */
    @ApiOperation("用户登录")
    @PostMapping(value = "/login")
    public ResultVO login(@RequestParam("phone") String phone,
                          @RequestParam("password") String password) {
        JSONObject result = new JSONObject();
        try {
            User login = userService.login(phone, password);
            if (login.getName() != null) {
                String token = (String) redisLoginUtil.get(String.valueOf(login.getId()));
                result.put("status", "登录成功");
                result.put("token", token);
                result.put("UserId", login.getId());
                result.put("displayName", login.getDisplayName());
                return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), "登录成功", result);
            } else {
                return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), "你输入的密码和账户名不匹配，是否忘记密码或忘记会员名");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }

    }


    /**
     * 修改用户头像
     *
     * @param request
     * @param userId
     * @return
     */
    @LoginRequired
    @ApiOperation("修改用户头像")
    @PostMapping("/updateAvatar")
    public ResultVO updateAvatar(MultipartHttpServletRequest request, @RequestParam("userId")Long userId) {
        List<MultipartFile> multipartFiles = request.getFiles("file");
        String url = "";
        if(userService.updateAvatar(multipartFiles.get(0), userId)) {
            url = userService.findById(userId).getAvatarUrl();
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getName());
        } else {
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), "服务器错误");
        }
    }

    /**
     * 清除cookies
     */
    @LoginRequired
    @ApiOperation("清除cookies")
    @DeleteMapping(value = "/clearCookies")
    public void clearCookies(HttpServletRequest request, HttpServletResponse response) {
        try {
            Cookie newCookie = new Cookie("newCookie", null);
            newCookie.setMaxAge(0);
            newCookie.setPath(request.getContextPath());
            response.addCookie(newCookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 用户的退出
     *
     * @param request
     * @return
     * @throws IOException
     */
    @LoginRequired
    @ApiOperation("用户登出")
    @DeleteMapping(value = "/logout")
    public ResultVO logout(HttpServletRequest request) throws IOException {
        String token;
        String userId;
        try {
            token = request.getHeader("token");
            if (token != null) {
                userId = redisLoginUtil.get(token).toString();
                String time = userId + token;
                redisLoginUtil.del(token, userId, time);
                return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), "退出成功！", "token已被清理：" + token);
            } else {
                return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), "退出失败,请携带token");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), "服务器错误");
        }

    }

    /**
     * 用户登录完后调的信息
     *
     * @param userId
     * @return
     */
    @LoginRequired
    @ApiOperation("用户信息")
    @GetMapping(value = "/userInfo/{userId}")
//    @LoginRequired
    public ResultVO findUserInfo(@PathVariable("userId") Long userId) {
        try {
            UserInfoBO findUserInfo = userService.findUserInfo(userId);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), "个人信息获取成功", findUserInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), "服务器繁忙请稍后再试");
        }
    }

    @LoginRequired
    @ApiOperation("用户换绑手机只能绑定从未绑定过的手机")
    @PostMapping(value = "/updatePhone")
    public ResultVO updatePhone(@RequestParam("userId") Long userId, @RequestParam(value = "phone") String phone) {
        try {
            User user = userService.findById(userId);
            //有用户进判断里
            if (user != null) {
                //拿数据库存的phone和参数Phone比较
                if (user.getPhone().equals(phone)) {
                    //输入的手机和之前手机一样直接返回
                    return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.USER_PHONE_SAME_ERROR);
                } else if (!phone.equals(user.getPhone())) {
                    //修改手机
                    Integer updateStatus = userService.updatePhone(userId, phone);
                    //拿 updateStatus状态来判断  决定返回什么样的状态信息
                    if (updateStatus.equals(UserPromptStatusEnum.USER_UPDATE_PHONE_SUCCESS.getCode())) {
                        //修改成功返回true
                        return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.USER_UPDATE_PHONE_SUCCESS);
                    } else if (updateStatus.equals(UserPromptStatusEnum.USER_ACCOUNT_BOUND_BY_OTHERS.getCode())) {
                        //该手机已经绑定过了 请更换新手机号绑定
                        return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.USER_PHONE_ALREADY_EXIST_BINDING);
                    } else if (updateStatus.equals(UserPromptStatusEnum.USER_ACCOUNT_NOT_FOUND.getCode())) {
                        //修改时候可能查不到用户  返回用户不存在
                        return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.USER_NOT_EXIST);
                    } else {
                        //访问失败
                        return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
                    }
                }
            } else {
                //未查到这个用户  等于空的话直接返回false 没有该用户
                return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.USER_NOT_EXIST);
            }
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.SERVER_ERROR);
        }
    }

    @LoginRequired
    @ApiOperation("用户输入旧密码修改新密码 输入的新密码必须和以前密码不同")
    @PostMapping(value = "/updatePassword")
    public ResultVO updatePhone(@RequestParam("userId") Long userId, @RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword) {
        try {
            User byId = userService.findById(userId);
            //用户不存在
            if (byId == null) {
                return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.USER_NOT_EXIST);
            }
            //旧密码 true 修改新密码  else 旧密码有误
            boolean resultOldPassword = userService.findOldPassword(userId, oldPassword);
            if (resultOldPassword) {
                //去修改
                Integer changeStatus = userService.updatePassword(userId, newPassword);
                //状态码的信息
                if (changeStatus.equals(UserPromptStatusEnum.USER_PASSWORD_SAME_ERROR.getCode())) {

                    return new ResultVO(false, UserPromptStatusEnum.USER_PASSWORD_SAME_ERROR.getCode(), ResultMessage.USER_PASSWORD_SAME_ERROR);
                } else if (changeStatus.equals(UserPromptStatusEnum.USER_SERVER_ERROR.getCode())) {

                    return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.USER_NOT_EXIST);
                } else if (changeStatus.equals(ResultStatusEnum.SUCCESS.getCode())) {

                    return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.USER_UPDATE_NEW_PASSWORD_SUCCESS);
                }
            } else {
                return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.USER_OLD_PASSWORD_ERROR);
            }
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.SERVER_ERROR);
        }
    }
}


