package com.breeze.util.wechat;

import java.util.List;

/**
 * @author 莫奇
 */
public class WeChatEntity {

    private String access_token;
    private String ticket ;
    private String nonceStr;
    private String timestamp;
    private List str;
    private String signature;

    public List getStr() {
        return str;
    }

    public void setStr(List str) {
        this.str = str;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getNonceStr() {
        return nonceStr;
    }

    public void setNonceStr(String nonceStr) {
        this.nonceStr = nonceStr;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
}
