package com.breeze.util.wechat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

import com.breeze.util.RedisCommonUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author 莫奇
 */
@Component
public class WeChatUtils {

    @Autowired
    public RedisCommonUtils redisCommonUtils;

    public WeChatEntity getWeChatEntity(String url){
        WeChatEntity wx = new WeChatEntity();
        String access_token = getAccessToken();
        String ticket = getTicket(access_token);
        Map<String, String> ret = Sign.sign(ticket, url);
        ArrayList<Object> list = new ArrayList<>();
        list.add("updateAppMessageShareData");
        list.add("updateTimelineShareData");

        wx.setStr(list);
        wx.setAccess_token(access_token);
        wx.setTicket(ret.get("jsapi_ticket"));
        wx.setSignature(ret.get("signature"));
        wx.setNonceStr(ret.get("nonceStr"));
        wx.setTimestamp(ret.get("timestamp"));
        return wx;
    }

    //获取token
    private  String getAccessToken() {
        String access_token = "";

        Object accessToken = redisCommonUtils.get("access_token");

        if (accessToken == null ){
            //获取access_token填写client_credential
            String grant_type = "client_credential";
            //第三方用户唯一凭证
            String AppId = "wx12e89e49926d870f";
            //第三方用户唯一凭证密钥，即appsecret
            String secret = "db12afed6b200439af6d04536348c200";
            //这个url链接地址和参数皆不能变
            //访问链接
            String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=" + grant_type + "&appid=" + AppId + "&secret=" + secret;

            try {
                URL urlGet = new URL(url);
                HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
                // 必须是get方式请求
                http.setRequestMethod("GET");
                http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                http.setDoOutput(true);
                http.setDoInput(true);
                http.connect();
                InputStream is = http.getInputStream();
                int size = is.available();
                byte[] jsonBytes = new byte[size];
                is.read(jsonBytes);
                String message = new String(jsonBytes, "UTF-8");
                JSONObject demoJson = JSONObject.fromObject(message);
                access_token = demoJson.getString("access_token");

                redisCommonUtils.set("access_token", access_token, 7100000);
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
       }else {
          access_token = (String) accessToken;
       }
        return access_token;
    }

    //获取ticket
    private static String getTicket(String access_token) {
        String ticket = null;
        //这个url链接和参数不能变
        String url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+ access_token +"&type=jsapi";
        try {
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            // 必须是get方式请求
            http.setRequestMethod("GET");
            http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            // 连接超时30秒
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
            // 读取超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000");
            http.connect();
            InputStream is = http.getInputStream();
            int size = is.available();
            byte[] jsonBytes = new byte[size];
            is.read(jsonBytes);
            String message = new String(jsonBytes, "UTF-8");
            JSONObject demoJson = JSONObject.fromObject(message);
            ticket = demoJson.getString("ticket");
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ticket;
    }
}
