package com.breeze.constants;

public final class ResultMessage {

    public static final String SUCCESS = "访问成功";
    public static final String ERROR = "访问失败";
    public static final String NOT_FOUND = "请求不存在";
    public static final String SERVER_ERROR = "访问时服务器错误";
    public static final String PARAM_ERROR = "参数有误";

    //支付状态
    public static final String PAY_SUCCESS = "支付成功";

    //支付异常状态
    public static final String PAY_EXCEPTION_PRODUCT_SOLD_OUT = "商品已售空";
    public static final String PAY_EXCEPTION_PRODUCT_NO_BID = "当前商品还不能立即出售";
    public static final String PAY_EXCEPTION_PRODUCT_NO_SOLD_ONESELF = "不能购买自己出售的商品";
    public static final String PAY_EXCEPTION_ERROR = "支付发生异常";
    public static final String PAY_EXCEPTION_USER_BID_ERROR = "出价错误";
    public static final String PAY_EXCEPTION_USER_BID_MULTIPLE_ERROR = "出价必须是10的倍数";
    public static final String PAY_EXCEPTION_USER_BID_SAME_ERROR = "两次出价相同";

    //出价状态
    public static final String BID_TRADING_PRODUCT_UPDATE_BID_SUCCESS = "商品出价成功";
    public static final String BID_TRADING_PRODUCT_UPDATE_BID_FAIL = "商品出价失败";

    //USER相关
    public static final String USER_ID_IS_NULL = "用户未登录";
    public static final String USER_ADDRESS_IS_NULL = "地址不能为空";
    public static final String USER_NOT_EXIST = "该用户不存在";
    public static final String USER_OLD_PASSWORD_ERROR = "输入的旧密码有误";
    public static final String USER_UPDATE_PHONE_SUCCESS = "修改手机号成功";
    public static final String USER_PHONE_ALREADY_EXIST_BINDING = "该手机已经绑定过了 请更换新手机号绑定";
    public static final String USER_PHONE_SAME_ERROR = "输入的手机和原来的手机号一致不需要再次修改";
    public static final String USER_PASSWORD_SAME_ERROR = "输入的密码和原密码一致不需要再次修改";
    public static final String USER_UPDATE_NEW_PASSWORD_SUCCESS = "修改成功 新密码已经更新了;您下次登录可以用新的密码登录了";

}
