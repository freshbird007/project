package com.leaves.smalltiger.consumer.service;

import com.leaves.smalltiger.consumer.vo.EmailVerify;
import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.consumer.vo.SMSVerify;
import com.leaves.smalltiger.consumer.vo.*;
import org.springframework.web.multipart.MultipartFile;

public interface ConsumerService {
//=============================================================================================================================================
//==================================================前端前台接口===========================================================================================
//=============================================================================================================================================


    /**
     * 邮箱验证（注册、找回密码）
     * @param conMail
     * @return
     */
    public MsgResult verifyEmail(String conMail);

    /**
     * 短信验证（注册、找回密码）
     * @param conTel
     * @return
     */
    public MsgResult verifySMS(String conTel);


    /**
     * 用户注册
     * @param regConsumer
     * @return
     */
    public MsgResult conReg(RegConsumer regConsumer);

    /**
     * 修改头像
     * @param conId
     * @param file
     * @return
     */
    public MsgResult updateAvatar(int conId, MultipartFile file);

    /**
     * 修改昵称
     * @param nicknameInfo
     * @return
     */
    public MsgResult updateNickname(NicknameInfo nicknameInfo);

    /**
     * 修改性别
     * @param sexInfo
     * @return
     */
    public MsgResult updateSex(SexInfo sexInfo);

    /**
     * 修改邮箱账号
     * @param emailInfo
     * @return
     */
    public MsgResult updateEmail(EmailInfo emailInfo);

    /**
     * 修改手机号
     * @param phoneInfo
     * @return
     */
    public MsgResult updatePhone(PhoneInfo phoneInfo);

    /**
     * 注销登录
     * @param conId
     * @return
     */
    public MsgResult logoutConsumer(int conId);

    /**
     * 用户登录=====后台
     * @param phoneConsumerInfo
     * @return
     */
    public MsgResult loginBackstage(ConsumerInfo phoneConsumerInfo);

//===============================================ZAHANGBO==============================================================================================

    /**
     * 用户登录=====前台
     * @param phoneConsumerInfo
     * @return
     */
    public MsgResult login(ConsumerInfo phoneConsumerInfo);

    /**
     * 找回密码
     * @param phoneConsumerInfo
     * @return
     */
    public MsgResult backPassword(ConsumerInfo phoneConsumerInfo);

    /**
     * 设置预算
     * @param budgetInfo
     * @return
     */
    public MsgResult updateBudget(BudgetInfo budgetInfo);

    /**
     * 查询当前日期打卡状态
     * @param conId
     * @return
     */
    public MsgResult selectSignInStatus(int conId);

    /**
     * 打卡操作
     * @param conId
     * @return
     */
    public MsgResult signIn(int conId);

    /**
     * 每月自动清除当前用户的预算
     * @param conId
     * @return
     */
    public MsgResult autoClear(int conId);

//    ===========================================XIXI============================================================

    //删除用户信息(修改用户状态)
    public MsgResult deleteConsumer(int conId);
    //修改用户状态
    public MsgResult changeStatus(int conId);
    //批量删除用户
    public MsgResult deleteConsumers(int[] conIds);
    //##############################后台方法##################################
    //模糊查询
    public MsgResult queryConsumersByWords(String msgWords,int pageNum,int pageSize );
    //修改用户信息
    public MsgResult updateConsumerById(ConsumerParam consumerParam);


//=============================================================================================================================================
//==================================================前端后台台接口===========================================================================================
//=============================================================================================================================================

}
