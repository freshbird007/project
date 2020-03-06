package com.leaves.smalltiger.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.zhenzi.sms.ZhenziSmsClient;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

/**
 * 短信发送
 */
@Slf4j
public class SMS {
    //短信平台相关参数
    //这个不用改
    String apiUrl = "https://sms_developer.zhenzikj.com";
    //榛子云系统上获取
    String appId = "103613";
    String appSecret = "61c279c1-de84-4d49-925a-a4ec30ccf409";

    /**
     * 短信验证
     * @param conTel
     * @return
     */
    public MsgResult verifySmsSend(String conTel){
        MsgResult msgResult = new MsgResult();
        try {

            JSONObject json = null;
            //随机生成验证码
            String smsCode = String.valueOf(new Random().nextInt(999999));
            //将验证码通过榛子云接口发送至手机
//            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl,appId, appSecret);
            String result = client.send(conTel, "您的验证码为:" + smsCode + "。您正在使用小老虎记账，感谢您的支持" +
                    "（验证码在5分钟内有效）!");
            log.info(result+"=注册发送结果====");

            json = JSONObject.parseObject(result);
            if (json.getIntValue("code") != 0) {//发送短信失败
                msgResult.setStatusCode(201);
                msgResult.setMsg("短信发送失败！");
                return msgResult;
            }
            msgResult.setStatusCode(200);
            msgResult.setMsg(smsCode);
            msgResult.setData(result);
            log.info(msgResult + "**注册发送结果2****" );
            return msgResult;

        } catch (Exception e) {
            e.printStackTrace();
            msgResult.setStatusCode(201);
            msgResult.setMsg("短信发送异常！");
            return msgResult;
        }
    }

//============================================================================
    /**
     * 注册短信验证
     * @param conTel 用户手机号码
     * @return
     */
    public MsgResult regSmsSend(String conTel){
        MsgResult msgResult = new MsgResult();
        try {

            JSONObject json = null;
            //随机生成验证码
            String smsCode = String.valueOf(new Random().nextInt(999999));
            //将验证码通过榛子云接口发送至手机
//            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl,appId, appSecret);
            String result = client.send(conTel, "您的验证码为:" + smsCode + "。您正在注册新用户，感谢您的支持" +
                    "（验证码在5分钟内有效）!");
            log.info(result+"=注册发送结果====");

            json = JSONObject.parseObject(result);
            if (json.getIntValue("code") != 0) {//发送短信失败
                msgResult.setStatusCode(201);
                msgResult.setMsg("短信发送失败！");
                return msgResult;
            }
            msgResult.setStatusCode(200);
            msgResult.setMsg(smsCode);
            msgResult.setData(result);
            log.info(msgResult + "**注册发送结果2****" );
            return msgResult;

        } catch (Exception e) {
            e.printStackTrace();
            msgResult.setStatusCode(201);
            msgResult.setMsg("短信发送异常！");
            return msgResult;
        }
    }

    /**
     * 找回密码短信验证
     * @param conTel 用户手机号码
     * @return
     */
    public MsgResult backSmsSend(String conTel){
        MsgResult msgResult = new MsgResult();
        try {

            JSONObject json = null;
            //随机生成验证码
            String smsCode = String.valueOf(new Random().nextInt(999999));
            //将验证码通过榛子云接口发送至手机
//            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl,appId, appSecret);
            String result = client.send(conTel, "您的验证码为:" + smsCode + "。您正在重置密码，感谢您的支持" +
                    "（验证码在5分钟内有效）!");
            log.info(result+"=注册发送结果====");

            json = JSONObject.parseObject(result);
            if (json.getIntValue("code") != 0) {//发送短信失败
                msgResult.setStatusCode(201);
                msgResult.setMsg("短信发送失败！");
                return msgResult;
            }
            msgResult.setStatusCode(200);
            msgResult.setMsg(smsCode);
            msgResult.setData(result);
            log.info(msgResult + "**注册发送结果2****" );
            return msgResult;

        } catch (Exception e) {
            e.printStackTrace();
            msgResult.setStatusCode(201);
            msgResult.setMsg("短信发送异常！");
            return msgResult;
        }
    }

    /**
     * 登录短信验证
     * @param conTel
     * @return
     */
    public MsgResult loginSmsSend(String conTel){
        MsgResult msgResult = new MsgResult();
        try {

            JSONObject json = null;
            //随机生成验证码
            String smsCode = String.valueOf(new Random().nextInt(999999));
            //将验证码通过榛子云接口发送至手机
//            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl, appId, appSecret);
            ZhenziSmsClient client = new ZhenziSmsClient(apiUrl,appId, appSecret);
            String result = client.send(conTel, "您的验证码为:" + smsCode + "。您正在登录小脑斧记账，感谢您的支持" +
                    "（验证码在5分钟内有效）!");
            log.info(result+"=注册发送结果====");

            json = JSONObject.parseObject(result);
            if (json.getIntValue("code") != 0) {//发送短信失败
                msgResult.setStatusCode(201);
                msgResult.setMsg("短信发送失败！");
                return msgResult;
            }
            msgResult.setStatusCode(200);
            msgResult.setMsg(smsCode);
            msgResult.setData(result);
            log.info(msgResult + "**注册发送结果2****" );
            return msgResult;

        } catch (Exception e) {
            e.printStackTrace();
            msgResult.setStatusCode(201);
            msgResult.setMsg("短信发送异常！");
            return msgResult;
        }
    }
}
