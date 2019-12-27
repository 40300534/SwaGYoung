package com.breeze.controller;

import com.breeze.annotation.LoginRequired;
import com.breeze.constants.ResultMessage;
import com.breeze.enums.ResultStatusEnum;
import com.breeze.pojo.UserProductFavorate;
import com.breeze.pojo.bo.ProductBO;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.IUserFavorateService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author 莫奇
 * @date 2019-06-25 23:22
 * @Description: 收藏相关
 */
@RestController
@RequestMapping(value = "/userFavorate")
@Api(tags = {"用户收藏管理"})
public class UserFavorateController {

    @Autowired
    private IUserFavorateService userFavorateService;

    /**
     * 查询用户全部收藏
     *
     * @param pageNum
     * @param pageSize
     * @param userId
     * @return
     */
    @LoginRequired
    @ApiOperation("查询用户全部收藏")
    @GetMapping(value = "/findAll")
    public PageInfo<ProductBO> findAll(@RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
                                       @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                       @RequestParam("userId") Long userId) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(userFavorateService.findAllByUserId(userId));

    }

    /**
     * 判断当前商品是否是用户喜欢
     *
     * @param userId
     * @param productId
     * @return
     */
    @ApiOperation("判断当前商品是否是用户喜欢")
    @GetMapping(value = "/isFavorate")
    public boolean isFavorate(@RequestParam("userId") Long userId, @RequestParam("productId") Long productId) {

        return userFavorateService.isFavorate(userId, productId);
    }

    /**
     * 收藏
     */
    @ApiOperation("添加收藏")
    @LoginRequired
    @PostMapping
    public int add(@RequestBody UserProductFavorate userProductFavorate) {
        userProductFavorate.setCreateTime(new Date());
        return userFavorateService.add(userProductFavorate);
    }

    /**
     * 取消收藏
     */
    @ApiOperation("取消收藏")
    @LoginRequired
    @DeleteMapping(value = "/{id}")
    public int deleteById(@PathVariable("id") Long id) {
        return userFavorateService.deleteById(id);
    }

    /**
     * toggle
     *
     * @param userProductFavorate
     * @return
     */
    @ApiOperation("toggle")
    @LoginRequired
    @RequestMapping(value = "/toggle")
    public ResultVO toggle(@RequestBody UserProductFavorate userProductFavorate) {

        UserProductFavorate userFavorate = userFavorateService.toggle(userProductFavorate);
        if (userFavorate == null) {
            userProductFavorate.setCreateTime(new Date());
            try {
                add(userProductFavorate);
                return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
            }
        } else {
            try {
                deleteById(userFavorate.getId());
                return new ResultVO(true, 201, ResultMessage.SUCCESS);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
            }
        }
    }


}
