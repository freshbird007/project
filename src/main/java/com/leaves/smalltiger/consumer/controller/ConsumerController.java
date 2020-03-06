package com.leaves.smalltiger.consumer.controller;

import com.leaves.smalltiger.consumer.vo.EmailVerify;
import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.consumer.vo.SMSVerify;
import com.leaves.smalltiger.consumer.service.ConsumerService;
import com.leaves.smalltiger.consumer.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/api")
public class ConsumerController {

    @Autowired
    private ConsumerService consumerService;

//=============================================================================================================================================
//===================================================================LEAVES==========================================================================

    /**
     * 邮箱验证（注册、登录、找回密码）
     * @param conMail
     * @return
     */
    @RequestMapping(value = "/verifyEmail",method = RequestMethod.GET)
    public MsgResult verifyEmail(@RequestParam("conMail") String conMail){
        log.info("邮箱验证时的账号："+conMail);
       MsgResult result = consumerService.verifyEmail(conMail);
       return result;
    }

    /**
     *手机账号（注册、登录、找回密码）
     * @param conTel
     * @return
     */
    @RequestMapping(value = "/verifyPhone",method = RequestMethod.GET)
    public MsgResult verifySMS(@RequestParam("conTel") String conTel) {
        log.info("短信验证时的账号：" + conTel);
        MsgResult result = consumerService.verifySMS(conTel);
        return result;
    }


    /**
     * 用户注册
     * @param regConsumer
     * @return
     */
    @RequestMapping(value = "/regConsumr",method = RequestMethod.POST)
    public MsgResult conReg(@RequestBody RegConsumer regConsumer){
        log.info("注册用户信息==="+regConsumer.toString());
        MsgResult result = consumerService.conReg(regConsumer);
        return result;
    }

    /**
     * 修改头像
     * @param conId
     * @param file
     * @return
     */
    @RequestMapping(value = "/avatar", method = RequestMethod.PUT)
    public MsgResult updateAvatar(@RequestParam("conId") int conId, @RequestParam MultipartFile file) {
        log.info("修改头像："+conId+"====="+file);
        MsgResult result = consumerService.updateAvatar(conId, file);
        return result;
    }

    /**
     * 上传图片
     * @param file
     * @param conId
     * @return
     *//*
    @RequestMapping(value = "/testUpload", method = RequestMethod.POST)
    public MsgResult uploadImage(@RequestParam("file") MultipartFile file) {
        MsgResult result = new MsgResult();
        QiniuCloudUtil qiniuCloudUtil = new QiniuCloudUtil();
        log.info("文件："+file.getOriginalFilename());
        if(file.isEmpty()) {
            result.setStatusCode(201);
            result.setMsg("上传失败");
            return result;
        }
        try {
            String avatar_path= qiniuCloudUtil.saveImage(file);
            result.setStatusCode(200);
            result.setMsg("上传成功");
            result.setData(avatar_path);
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            result.setStatusCode(201);
            result.setMsg("上传失败");
            return result;
        }
    }*/

    /**
     * 修改昵称
     * @param nicknameInfo
     * @return
     */
    @RequestMapping(value = "/nickname", method = RequestMethod.PUT)
    public MsgResult updateNickname(@RequestBody NicknameInfo nicknameInfo) {
        log.info("修改昵称："+nicknameInfo.toString());
        MsgResult result = consumerService.updateNickname(nicknameInfo);
        return result;
    }

    /**
     * 修改性别
     * @param sexInfo
     * @return
     */
    @RequestMapping(value = "/sex", method = RequestMethod.PUT)
    public MsgResult updateSex(@RequestBody SexInfo sexInfo) {
        log.info("修改性别："+sexInfo.toString());
        MsgResult result = consumerService.updateSex(sexInfo);
        return result;
    }

    /**
     * 修改手机号
     * @param phoneInfo
     * @return
     */
    @RequestMapping(value = "/phone", method = RequestMethod.PUT)
    public MsgResult updatePhone(@RequestBody PhoneInfo phoneInfo) {
        log.info("修改手机号："+phoneInfo.toString());
        MsgResult result = consumerService.updatePhone(phoneInfo);
        return result;
    }

    /**
     * 修改邮箱账户
     * @param emailInfo
     * @return
     */
    @RequestMapping(value = "/email", method = RequestMethod.PUT)
    public MsgResult updateEmail(@RequestBody EmailInfo emailInfo) {
        log.info("修改邮箱账户："+emailInfo.toString());
        MsgResult result = consumerService.updateEmail(emailInfo);
        return result;
    }

    /**
     *注销登录
     * @param conId
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.PUT)
    public MsgResult logoutConsumer(@RequestParam("conId") int conId){
        log.info("Controller注销登录======"+conId);
        MsgResult result = consumerService.logoutConsumer(conId);
        return result;
    }


    /**
     * 后台登录
     */
    @RequestMapping(value = "/logins",method = RequestMethod.POST)
    public MsgResult dealLogins(@RequestBody ConsumerInfo consumerInfo){
        MsgResult msgResult = consumerService.loginBackstage(consumerInfo);
        return msgResult;
    }


//=============================================ZHANGBO====xxx============================================================================================

    /**
     * 登录
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public MsgResult dealLogin(@RequestBody ConsumerInfo consumerInfo){
        MsgResult msgResult = consumerService.login(consumerInfo);
        return msgResult;
    }

    /**
     * 找回密码
     */
    @RequestMapping(value = "/password",method = RequestMethod.POST)
    public MsgResult backPassword(@RequestBody ConsumerInfo consumerInfo){
        MsgResult msgResult = consumerService.backPassword(consumerInfo);
        return msgResult;
    }

    /**
     * 我的界面--设置预算
     */
    @RequestMapping(value = "/budget",method = RequestMethod.PUT)
    public MsgResult backPassword(@RequestBody BudgetInfo budgetInfo){
        MsgResult msgResult = consumerService.updateBudget(budgetInfo);
        return msgResult;
    }

    /**
     * 我的界面--查询打卡状态
     */
    @RequestMapping(value = "/record",method = RequestMethod.GET)
    public MsgResult selectSignInStatus(@RequestParam int conId){
        MsgResult msgResult = consumerService.selectSignInStatus(conId);
        return msgResult;
    }
    /**
     * 我的界面--打卡
     */
    @RequestMapping(value = "/record",method = RequestMethod.POST)
    public MsgResult signIn(@RequestParam int conId){
        MsgResult msgResult = consumerService.signIn(conId);
        return msgResult;
    }
    /**
     * 每月自动清除当前用户的预算
     */
    @RequestMapping(value = "/autoclear",method = RequestMethod.DELETE)
    public MsgResult autoClear(@RequestParam("conId") int conId){
        MsgResult msgResult = consumerService.autoClear(conId);
        return msgResult;
    }

//===================================XIXI============================================================================


    /**
     * 根据id删除用户信息(修改状态)
     * @param conId
     * @return
     */
    @RequestMapping(value = "/deletecon",method = RequestMethod.PUT)
    public MsgResult deleteConsumer(@RequestParam("conId") int conId){
        log.info("传来的id："+conId);
        MsgResult msgResult = consumerService.deleteConsumer(conId);
        return msgResult;
    }

    /**
     * 批量删除用户信息(修改状态)
     * @param conIds
     * @return
     */
    @RequestMapping(value = "/deletecons",method = RequestMethod.PUT)
    public MsgResult deleteConsumers(@RequestParam("conIds") int[] conIds){
        log.info("Id数组:"+conIds.length);
        MsgResult msgResult = consumerService.deleteConsumers(conIds);
        return msgResult;
    }

    /**
     * 修改用户状态
     * @param conId
     * @return
     */
    @RequestMapping(value = "/statuschange",method = RequestMethod.PUT)
    public MsgResult statuschange(@RequestParam("conId") int conId){
        log.info(conId+"传来的参数");
        MsgResult msgResult = consumerService.changeStatus(conId);
        return msgResult;
    }

    /**
     * 模糊搜索+分页
     * @param msgWords
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/queryConsumersByWords",method = RequestMethod.GET)
    public MsgResult queryConsumersByWords(@RequestParam(value = "msgWords",defaultValue = "")String msgWords,
                                           @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                           @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        log.info("ConsumerController => queryConsumersByWords要查询的是:"+msgWords);
        //consumerService.queryConsumersByWords(String msgWords);
        MsgResult msgResult = consumerService.queryConsumersByWords(msgWords,pageNum,pageSize);
        return msgResult;

    }

    /**
     * 修改用户信息
     * @param consumerParam
     * @return
     */
    @RequestMapping(value = "/updateConsumer",method = RequestMethod.PUT)
    public MsgResult updateConsumerById(@RequestBody ConsumerParam consumerParam){
        //consumerService.updateConsumerById(conId);
        MsgResult msgResult = consumerService.updateConsumerById(consumerParam);
        return msgResult;

    }



//=============================================================================================================================================
//==================================================前端后台接口===========================================================================================
//=============================================================================================================================================

}
