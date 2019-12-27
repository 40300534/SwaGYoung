package com.breeze.controller;

import com.breeze.constants.ResultMessage;
import com.breeze.enums.ResultStatusEnum;
import com.breeze.pojo.FeedbackType;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.IFeedbackTypeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Created by wdnnccey
 * @date 2019-08-01 08:56
 * @Description: 意见反馈类型
 */
@RestController
@RequestMapping(value = "/feedbackType")
@Api(tags = "意见反馈-类型")
public class FeedbackTypeController {

    @Autowired
    private IFeedbackTypeService feedbackTypeService;


    /**
     * 获取全部用户投诉反馈
     *
     * @return
     */
    @ApiOperation("获取全部意见反馈类型")
    @GetMapping(value = "/findAll")
    public PageInfo<FeedbackType> findAll(
            @RequestParam(name = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return new PageInfo<>(feedbackTypeService.findAll());
    }


    /**
     * 根据Id获取意见反馈类型
     */
    @ApiOperation("根据ID获取意见反馈类型")
    @GetMapping(value = "/{id}")
    public FeedbackType findById(@PathVariable("id") Integer id) {
        return feedbackTypeService.findById(id);
    }

    /**
     * 新增意见反馈类型
     */
    @ApiOperation("新增意见反馈类型")
    @PostMapping
    public ResultVO add(@RequestBody FeedbackType feedbackType) {
        try {
            feedbackTypeService.add(feedbackType);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 修改意见反馈类型
     */
    @ApiOperation("修改意见反馈类型")
    @PutMapping
    public ResultVO update(@RequestBody FeedbackType feedbackType) {
        try {
            feedbackTypeService.update(feedbackType);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

    /**
     * 删除意见反馈类型
     */
    @ApiOperation("删除意见反馈类型")
    @DeleteMapping(value = "/{id}")
    public ResultVO deleteById(@PathVariable("id") Integer id) {
        try {
            feedbackTypeService.deleteById(id);
            return new ResultVO(true, ResultStatusEnum.SUCCESS.getCode(), ResultMessage.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultVO(false, ResultStatusEnum.ERROR.getCode(), ResultMessage.ERROR);
        }
    }

}
