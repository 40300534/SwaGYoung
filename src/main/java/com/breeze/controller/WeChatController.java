package com.breeze.controller;

import com.breeze.util.wechat.WeChatEntity;
import com.breeze.util.wechat.WeChatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author 莫奇
 */
@RestController
@RequestMapping(value = "/weChat")
public class WeChatController {

    @Autowired
    private WeChatUtils weChatUtils;

    @RequestMapping(value = "/share")
    public WeChatEntity share() {
        //微信分享
        String strUrl = "http://10.45.50.11:8082/#/user";

        WeChatEntity wx = weChatUtils.getWeChatEntity(strUrl);

        String url = wx.getTicket() + ",,," + wx.getStr() + ",,," + wx.getAccess_token() + ",,," + wx.getTimestamp() + ",,," + wx.getNonceStr() + ",,," + wx.getSignature();
        return wx;

    }

}