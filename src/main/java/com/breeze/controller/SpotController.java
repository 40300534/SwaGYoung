package com.breeze.controller;

import com.breeze.pojo.Spot;
import com.breeze.pojo.vo.ResultVO;
import com.breeze.service.SpotService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/spot")
@CrossOrigin
@Api(value = "景点",description = "景点")
public class SpotController {

    @Autowired
    private SpotService spotService;


    @RequestMapping(value = "/saveSpot",method = RequestMethod.POST)
    @ApiOperation("保存景点")
    public ResultVO saveSpot(@RequestBody Spot spot){
        spotService.saveSpot(spot);
        return new ResultVO(true,200,"成功");
    }

    @RequestMapping(value = "/updateSpot",method = RequestMethod.POST)
    @ApiOperation("修改景点")
    public ResultVO updateSpot(@RequestBody Spot spot){
        spotService.updateSpot(spot);
        return new ResultVO(true,200,"成功");
    }

    @RequestMapping(value = "/findSpot",method = RequestMethod.POST)
    @ApiOperation("检索景点")
    public ResultVO findSpot(@RequestBody Spot spot){
        Page<Spot> pageInfo = spotService.findSpot(spot);
        return new ResultVO(true,200,"成功",pageInfo);
    }

    @RequestMapping(value = "/delSpot/{ids}",method = RequestMethod.POST)
    @ApiOperation("删除景点")
    public ResultVO delSpot(@PathVariable("ids") String ids){
        spotService.delSpot(ids);
        return new ResultVO(true,200,"成功");
    }

    @RequestMapping(value = "/findSpotById/{id}",method = RequestMethod.POST)
    @ApiOperation("查询对应的景点")
    public ResultVO findSpotById(@PathVariable("id") String id){
        Spot spot = spotService.findSpotById(id);
        return new ResultVO(true,200,"成功",spot);
    }




}
