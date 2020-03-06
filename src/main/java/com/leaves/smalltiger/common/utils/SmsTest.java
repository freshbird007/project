package com.leaves.smalltiger.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
@Slf4j
public class SmsTest {

    public static void varify(){

    }

   /* public static void main(String[] args) {
        String apiUrl = "https://sms_developer.zhenzikj.com";
        //榛子云系统上获取
        String appId = "\n" +
                "103613";
        String appSecret = "61c279c1-de84-4d49-925a-a4ec30ccf409\n";
        try {
            //随机生成验证码
            String smsCode = String.valueOf(new Random().nextInt(999999));
            //将验证码通过榛子云接口发送至手机
//            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl,appId, appSecret);
            String tel = "18392754114";
            String result = client.send(tel, "您的验证码为:" + smsCode + "。您正在注册新用户，感谢您的支持" +
                    "（验证码在5分钟内有效）!");
            log.info("短信发送的结果："+result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}
