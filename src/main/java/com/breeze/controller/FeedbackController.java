package com.breeze.controller;

import com.breeze.constants.ResultMessage;
import com.breeze.enums.ResultStatusEnum;
import com.breeze.pojo.Feedback;
import com.breeze.pojo.FeedbackImage;
import com.breeze.pojo.Transaction;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.IFeedbackImageService;
import com.breeze.service.IFeedbackService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 莫奇
 * 用户投诉反馈
 */
@RestController
@RequestMapping(value = "/feedback")
@Api(tags = "用户投诉")
public class FeedbackController {

    @Autowired
    private IFeedbackService feedbackService;

    @Autowired
    private IFeedbackImageService feedbackImageService;

    /**
     * 获取全部用户投诉反馈
     *
     * @return
     */
    @ApiOperation("获取全部用户投诉")
    @GetMapping(value = "/findAll")
    public PageInfo<Feedback> findAll(
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(feedbackService.findAll());
    }

    /**
     * 获取某一用户的全部投诉
     *
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取某一用户的全部投诉")
    @GetMapping(value = "/findByUserId")
    public PageInfo<Feedback> findByUserId(
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam("userId") Long userId) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(feedbackService.findByUserId(userId));
    }

    /**
     * 根据Id获取用户投诉
     *
     * @param id
     * @return
     */
    @ApiOperation("获取指定用户投诉")
    @GetMapping
    public Feedback findById(@RequestParam("id") Long id) {
        return feedbackService.findById(id);
    }

    /**
     * 新增投诉
     *
     * @param feedback
     * @return
     */
    @ApiOperation("新增投诉")
    @PostMapping
    public ResultVO add(@RequestBody Feedback feedback, @RequestParam("ids") List<Long> feedbackImageIdList) {
        int feedbackReturnId = feedbackService.add(feedback, feedbackImageIdList);
        if (feedbackReturnId != 0) {
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } else {
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 修改用户投诉
     *
     * @param feedback
     * @return
     */
    @ApiOperation("修改用户投诉")
    @PutMapping
    public ResultVO update(@RequestBody Feedback feedback) {
        try {
            feedbackService.update(feedback);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 删除投诉
     *
     * @param id
     * @return
     */
    @ApiOperation("删除投诉")
    @DeleteMapping(value = "/{id}")
    public ResultVO deleteById(@PathVariable("id") Long id) {
        try {
            feedbackService.deleteById(id);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }


}
