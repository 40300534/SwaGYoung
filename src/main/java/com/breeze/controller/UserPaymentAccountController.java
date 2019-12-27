package com.breeze.controller;

import com.breeze.constants.ResultMessage;
import com.breeze.enums.ResultStatusEnum;
import com.breeze.pojo.UserPaymentAccount;
import com.breeze.pojo.bo.UserPaymentAccountBO;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.IUserPaymentAccountService;
import com.breeze.util.GetNameOfBankUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 莫奇
 */
@RestController
@RequestMapping(value = "/userPaymentAccount")
@Api(tags = {"用户账户管理"})
public class UserPaymentAccountController {

    @Autowired
    private IUserPaymentAccountService userPaymentAccountService;

    /**
     * 查询全部用户的账户
     *
     * @return
     */
    @ApiOperation("查询全部账户")
    @GetMapping(value = "/findAll")
    public PageInfo<UserPaymentAccount> findAll(
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(userPaymentAccountService.findAll());
    }

    /**
     * 根据用户Id查询账户
     *
     * @param userId
     * @return
     */
    @ApiOperation("根据用户Id查询账户")
    @GetMapping(value = "/userId")
    public List<UserPaymentAccountBO> findByUserId(@RequestParam("userId") Long userId) {
        return userPaymentAccountService.findByUserId(userId);
    }

    /**
     * 根据账户Id查询账户
     *
     * @param id
     * @return
     */
    @ApiOperation("根据账户Id查询账户")
    @GetMapping
    public UserPaymentAccount findById(@RequestParam("id") Long id) {
        return userPaymentAccountService.findById(id);
    }

    /**
     * 新增用户账户
     *
     * @param userPaymentAccount
     * @return
     */
    @ApiOperation("新增用户账户")
    @PostMapping
    public ResultVO add(@RequestBody UserPaymentAccount userPaymentAccount) {
        String nameOfBank = GetNameOfBankUtils.getNameOfBank(userPaymentAccount.getAccountNo());
        if (userPaymentAccountService.add(userPaymentAccount)){
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS,nameOfBank);
        }else {
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 修改用户账户
     *
     * @param userPaymentAccount
     * @return
     */
    @ApiOperation("修改用户账户")
    @PutMapping
    public ResultVO update(@RequestBody UserPaymentAccount userPaymentAccount) {
        try {
            userPaymentAccountService.update(userPaymentAccount);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 删除指定账户
     *
     * @return
     */
    @ApiOperation("删除指定账户")
    @DeleteMapping(value = "/{id}")
    public ResultVO deleteById(@PathVariable("id") Long id) {
        try {
            userPaymentAccountService.deleteById(id);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }
}
