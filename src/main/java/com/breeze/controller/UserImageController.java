package com.breeze.controller;

import com.breeze.constants.ResultMessage;
import com.breeze.enums.ResultStatusEnum;
import com.breeze.pojo.UserImage;
import com.breeze.pojo.bo.UserImageBO;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.IUserImageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 莫奇
 */
@RestController
@RequestMapping(value = "/userImage")
@Api(tags = {"用户动态"})
public class UserImageController {

    @Autowired
    private IUserImageService userImageService;

    /**
     * 查询全部动态
     *
     * @return
     */
    @ApiOperation("查询全部动态")
    @GetMapping(value = "/findAll")
    public PageInfo<UserImage> findAll(
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(userImageService.findAll());
    }

    /**
     * 根据用户Id查询动态
     *
     * @param userId
     * @return
     */
    @ApiOperation("根据用户Id查询动态")
    @GetMapping(value = "/userId")
    public PageInfo<UserImageBO> findByUserId(
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam("userId") Long userId) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(userImageService.findByUserId(userId));
    }

    /**
     * 根据Id查询动态
     *
     * @param id
     * @return
     */
    @ApiOperation("根据Id查询动态")
    @GetMapping
    public UserImage findById(@RequestParam("id") Long id) {
        return userImageService.findById(id);
    }

    /**
     * 新增动态
     *
     * @param userImageBO
     * @return
     */
    @ApiOperation("新增动态")
    @PostMapping
    public ResultVO add(@RequestBody UserImageBO userImageBO) {
        try {
            userImageService.add(userImageBO);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultStatusEnum.SUCCESS.getName());
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultStatusEnum.ERROR.getName());
        }

    }

    /**
     * 根据Id删除动态
     *
     * @param id
     * @return
     */
    @ApiOperation("根据Id删除动态")
    @DeleteMapping(value = "/{id}")
    public ResultVO deleteById(@PathVariable("id") Long id) {
        try {
            userImageService.deleteById(id);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

}
