package com.leaves.smalltiger.common.utils;
import java.io.IOException;
import	java.util.concurrent.ExecutionException;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MailSend {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private FreeMarkerConfigurer markerConfigurer;

    /**
     * 邮箱验证
     * @param conMail
     * @return
     */
    public MsgResult verifySendMail(String conMail){
        MsgResult msgResult = new MsgResult();
        log.info("找回密码邮箱验证的邮箱为："+conMail);
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String timess = dateFormat.format( now );
//        调用redoms方法得到随机生成验证码
        String randoms = randoms();
//        邮件接收账户
        String account = conMail;
//        姓名
//        String conNames = conName;
        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        发送账户
        mailMessage.setFrom("clovewb@163.com");
//        接收账户
        mailMessage.setTo(account);
//        发送的标题
        mailMessage.setSubject("SBastClassic Account Verification");
        Map<String,String> data = new HashMap<>();
//        加入时间
        data.put("times",timess);
//        加入验证码
        data.put("rendoms" , randoms);
//        加入姓名
//        data.put("conNames" ,conNames);
//        调用getMailContent方法------发送的内容
        String templateIntoString = null;
        try {//会产生异常
            Template template = markerConfigurer.getConfiguration().getTemplate("mail1.ftl");
//            mailMessage.setText(getMailContent(data));
            templateIntoString = FreeMarkerTemplateUtils.processTemplateIntoString(template, data);
        } catch (Exception e) {
            e.printStackTrace();
            msgResult.setStatusCode(201);
            msgResult.setMsg("邮件发送失败");
            return msgResult;
        }
        mailMessage.setText(templateIntoString);
        mailSender.send(mailMessage);
        msgResult.setStatusCode(200);
        msgResult.setMsg("邮件发送成功");
        msgResult.setData(randoms);
        return msgResult;

    }

//=====================================================================================================================
    /**
     * 登录、注册、找回密码邮箱验证
     * @param conMail
     * @return
     */
    public MsgResult regSendMail(String conMail){
        MsgResult msgResult = new MsgResult();
        log.info("找回密码邮箱验证的邮箱为："+conMail);
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String timess = dateFormat.format( now );
//        调用redoms方法得到随机生成验证码
        String randoms = randoms();
//        邮件接收账户
        String account = conMail;
//        姓名
//        String conNames = conName;
        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        发送账户
        mailMessage.setFrom("clovewb@163.com");
//        接收账户
        mailMessage.setTo(account);
//        发送的标题
        mailMessage.setSubject("SBastClassic Account Verification");
        Map<String,String> data = new HashMap<>();
//        加入时间
        data.put("times",timess);
//        加入验证码
        data.put("rendoms" , randoms);
//        加入姓名
//        data.put("conNames" ,conNames);
//        调用getMailContent方法------发送的内容
        String templateIntoString = null;
        try {//会产生异常
            Template template = markerConfigurer.getConfiguration().getTemplate("mail1.ftl");
//            mailMessage.setText(getMailContent(data));
            templateIntoString = FreeMarkerTemplateUtils.processTemplateIntoString(template, data);
        } catch (Exception e) {
            e.printStackTrace();
            msgResult.setStatusCode(201);
            msgResult.setMsg("邮件发送失败");
            return msgResult;
        }
        mailMessage.setText(templateIntoString);
        mailSender.send(mailMessage);
        msgResult.setStatusCode(200);
        msgResult.setMsg("邮件发送成功");
        msgResult.setData(randoms);
        return msgResult;

    }

    /**
     * 找回密码邮箱验证
     * @param conMail
     * @return
     */
    public MsgResult backSendMail(String conMail){
        MsgResult msgResult = new MsgResult();
        log.info("注册邮箱验证的邮箱为："+conMail);
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String timess = dateFormat.format( now );
//        调用redoms方法得到随机生成验证码
        String randoms = randoms();
//        邮件接收账户
        String account = conMail;
//        姓名
//        String conNames = conName;
        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        发送账户
        mailMessage.setFrom("clovewb@163.com");
//        接收账户
        mailMessage.setTo(account);
//        发送的标题
        mailMessage.setSubject("SBastClassic Account Verification");
        Map<String,String> data = new HashMap<>();
//        加入时间
        data.put("times",timess);
//        加入验证码
        data.put("rendoms" , randoms);
//        加入姓名
//        data.put("conNames" ,conNames);
//        调用getMailContent方法------发送的内容
        String templateIntoString = null;
        try {//会产生异常
            Template template = markerConfigurer.getConfiguration().getTemplate("mail2.ftl");
//            mailMessage.setText(getMailContent(data));
            templateIntoString = FreeMarkerTemplateUtils.processTemplateIntoString(template, data);
        } catch (Exception e) {
            e.printStackTrace();
            msgResult.setStatusCode(201);
            msgResult.setMsg("邮件发送失败");
            return msgResult;
        }
        mailMessage.setText(templateIntoString);
        mailSender.send(mailMessage);
        msgResult.setStatusCode(200);
        msgResult.setMsg("邮件发送成功");
        msgResult.setData(randoms);
        return msgResult;
    }

    /**
     * 登录验证
     * @param conMail
     * @return
     */
    public MsgResult loginSendMail(String conMail){
        MsgResult msgResult = new MsgResult();
        log.info("注册邮箱验证的邮箱为："+conMail);
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String timess = dateFormat.format( now );
//        调用redoms方法得到随机生成验证码
        String randoms = randoms();
//        邮件接收账户
        String account = conMail;
//        姓名
//        String conNames = conName;
        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        发送账户
        mailMessage.setFrom("clovewb@163.com");
//        接收账户
        mailMessage.setTo(account);
//        发送的标题
        mailMessage.setSubject("SBastClassic Account Verification");
        Map<String,String> data = new HashMap<>();
//        加入时间
        data.put("times",timess);
//        加入验证码
        data.put("rendoms" , randoms);
//        加入姓名
//        data.put("conNames" ,conNames);
//        调用getMailContent方法------发送的内容
        String templateIntoString = null;
        try {//会产生异常
            Template template = markerConfigurer.getConfiguration().getTemplate("mail2.ftl");
//            mailMessage.setText(getMailContent(data));
            templateIntoString = FreeMarkerTemplateUtils.processTemplateIntoString(template, data);
        } catch (Exception e) {
            e.printStackTrace();
            msgResult.setStatusCode(201);
            msgResult.setMsg("邮件发送失败");
            return msgResult;
        }
        mailMessage.setText(templateIntoString);
        mailSender.send(mailMessage);
        msgResult.setStatusCode(200);
        msgResult.setMsg("邮件发送成功");
        msgResult.setData(randoms);
        return msgResult;

    }


    /**
     * 发的内容方法
    public String getMailContent(Map<String,String> data){
        try {
            Template template = markerConfigurer.getConfiguration().getTemplate("mail.ftl");
            return FreeMarkerTemplateUtils.processTemplateIntoString(template,data);
        } catch (Exception ex) {
            ex.printStackTrace();
            log.error("生成邮件内容出错",ex);
            return "默认邮件内容";
        }

    }*/

    /**
     * 生成随机验证码
     * @return
     */
    public String getRandomChar() {
        int rand = (int) Math.round(Math.random() * 2);
        long itmp = 0;
        char ctmp = '\u0000';
        switch (rand) {
            case 1:
                itmp = Math.round(Math.random() * 25 + 65);
                ctmp = (char) itmp;
                return String.valueOf(ctmp);
            case 2:
                itmp = Math.round(Math.random() * 25 + 97);
                ctmp = (char) itmp;
                return String.valueOf(ctmp);
            default:
                itmp = Math.round(Math.random() * 9);
                return String.valueOf(itmp);
        }
    }

    /**
     * 随机生成邮件验证码
     * @return
     */
    public String randoms(){
        MailSend mailSend = new MailSend();
        String rands = "";
        for (int i = 0; i < 5; i++) {
            String tmp = mailSend.getRandomChar();
            rands += tmp;
        }
        return rands;
    }
}
