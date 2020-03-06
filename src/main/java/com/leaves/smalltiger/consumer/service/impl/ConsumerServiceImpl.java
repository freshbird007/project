package com.leaves.smalltiger.consumer.service.impl;
import	java.lang.ref.Reference;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leaves.smalltiger.common.po.Consumer;
import com.leaves.smalltiger.common.utils.*;
import com.leaves.smalltiger.consumer.mapper.ConsumerMapper;
import com.leaves.smalltiger.consumer.service.ConsumerService;
import com.leaves.smalltiger.consumer.vo.*;
import com.leaves.smalltiger.recording.mapper.RecordingMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import sun.rmi.server.LoaderHandler;

import javax.annotation.Resource;

@Slf4j
@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Resource
    private ConsumerMapper consumerMapper;
    @Resource
    private RecordingMapper recordingMapper;

//=============================================================================================================================================
//==================================================前端前台接口===========================================================================================
//=============================================================================================================================================

    /**
     * 邮箱验证
     * @param conMail
     * @return
     */
    @Override
    public MsgResult verifyEmail(String conMail) {
//        调用发送邮件的类
        MailSend mailSend = new MailSend();
//        发送邮件
        MsgResult result = mailSend.verifySendMail(conMail);
        return result;
    }

    /**
     * 短信验证
     * @param conTel
     * @return
     */
    @Override
    public MsgResult verifySMS(String conTel) {
//        调用发送短信的类
        SMS sms = new SMS();
        MsgResult result = sms.verifySmsSend(conTel);
        return result;
    }



    /*/**
     * 邮箱验证（注册、登录、找回密码）
     * @param emailVerify
     * @return
     *//*
    @Override
    public MsgResult verifyEmail(EmailVerify emailVerify) {
        MsgResult result = new MsgResult();
//        调用发送邮件的类
        MailSend mailSend = new MailSend();
        Consumer consumer = consumerMapper.selectByMail(emailVerify.getConMail());
//        判读邮箱账号查询的结果是否为空并且邮箱账号是否相同
//         emailVerify.getEmailType()为类型  1：注册、2：登录、3：找回密码
        if (consumer!=null && consumer.getConMail().equals(emailVerify.getConMail())){//相同
            if (emailVerify.getEmailType()==2){//等于2即为登录验证
//                邮箱登录发送验证码
                MsgResult verifySendMailResult = mailSend.loginSendMail(emailVerify.getConMail());
                return verifySendMailResult;
            }else if (emailVerify.getEmailType()==3){//等于3即为找回密码验证
//                邮箱找回密码发送验证码
                MsgResult backSendMailResult = mailSend.backSendMail(emailVerify.getConMail());
                return  backSendMailResult;
            }
        }else {//不同
            if (emailVerify.getEmailType()==3){//等于3即为注册验证
//                邮箱注册时发送验证码
                MsgResult verifySendMailResult = mailSend.regSendMail(emailVerify.getConMail());
                return verifySendMailResult;
            }
        }
        result.setStatusCode(201);
        result.setMsg("发送失败");
        return result;
    }

    *//**
     *手机账号（注册、登录、找回密码）
     * @param smsVerify
     * @return
     *//*
    @Override
    public MsgResult verifySMS(SMSVerify smsVerify) {
        MsgResult result = new MsgResult();
//        调用发送短信的类
        SMS sms = new SMS();
        Consumer consumer = consumerMapper.selectByPhone(smsVerify.getConTel());
//        判读手机账号查询的结果是否为空并且手机账号是否相同
        //         emailVerify.getEmailType()为类型  1：注册、2：登录、3：找回密码
        if (consumer!=null && consumer.getConMail().equals(smsVerify.getConTel())){
            if (smsVerify.getTelType() ==2){//等于2即为登录验证
//                手机号登录发送验证码
                MsgResult loginSmsSendResult = sms.loginSmsSend(smsVerify.getConTel());
                return loginSmsSendResult;
            }else if(smsVerify.getTelType()==3){//等于3即为找回密码验证
//                手机号找回密码发送验证码
                MsgResult backSmsSendResult = sms.backSmsSend(smsVerify.getConTel());
                return backSmsSendResult;
            }
        }else {
            if (smsVerify.getTelType()==1){//等于1即为注册验证
//                手机注册发送验证码
                MsgResult regSmsSendResult = sms.regSmsSend(smsVerify.getConTel());
                return regSmsSendResult;
            }
        }
        result.setStatusCode(201);
        result.setMsg("发送失败");
        return result;
    }*/

    /**
     * 用户注册
     * @param regConsumer
     * @return
     */
    @Override
    public MsgResult conReg(RegConsumer regConsumer) {
        MsgResult msgResult = new MsgResult();
        Consumer consumer = new Consumer();
//        调用RegularExpression.randomId()方法随机生成id
        consumer.setConId(RegularExpression.randomId());
//        调用RegularExpression.randomNickname()方法生成昵称
        consumer.setConName(RegularExpression.randomNickname());
//        调用RegularExpression.AVATAR_PATH生成默认头像
        consumer.setConAvatar(RegularExpression.AVATAR_PATH);
//        性别默认为0（0、未选择、1：男、2：女）
        consumer.setConSex(0);
//        状态默认为1
        consumer.setConStatus(1);

//        邮箱账号验证
//        if (RegularExpression.orEmail(regConsumer.getAccount())){
//            Consumer consumer1 = consumerMapper.selectByMail(regConsumer.getAccount());
//            if (consumer1 == null){
////                添加账户和密码
//                consumer.setConMail(regConsumer.getAccount());
//                consumer.setPassword(regConsumer.getPassword());
////                向数据库加入数据
//                int i = consumerMapper.insertSelective(consumer);
//                if (i > 0){
//                    msgResult.setStatusCode(200);
//                    msgResult.setMsg("邮箱注册成功");
//                    return msgResult;
//                }
//            }
//        }else
        if (RegularExpression.orPhone(regConsumer.getAccount())) {
            log.info("注册手机号为："+regConsumer.getAccount()+"========密码："+regConsumer.getPassword());
            Consumer consumer1 = consumerMapper.selectByPhone(regConsumer.getAccount());
            log.info(".................");
            if (StringUtils.isEmpty(consumer1)) {
                log.info("==========" );
                //                添加账户和密码
                consumer.setConTel(regConsumer.getAccount());
                consumer.setPassword(regConsumer.getPassword());
//                向数据库加入数据
                int i = consumerMapper.insertSelective(consumer);
                log.info("手机号注册结果："+i);
                if (i > 0) {
                    Consumer consumer2 = consumerMapper.selectByPhone(regConsumer.getAccount());
                    msgResult.setStatusCode(200);
                    msgResult.setMsg("手机号注册成功");
                    msgResult.setData(consumer2);
                    log.info("注册成功");
                    return msgResult;
                }
            }else {
                log.info("***************");
                log.info("注册手机号查询的结果："+consumer1.toString());
                msgResult.setStatusCode(201);
                msgResult.setMsg("手机号已存在");
                log.info("注册失败=====手机号已存在");
                return msgResult;
            }

        }
        log.info("手机号格式不正确");
        msgResult.setStatusCode(201);
        msgResult.setMsg("请输入正确的手机号");
        log.info("注册成功=====手机号格式不正确");
        return msgResult;
    }

    /**
     * 修改头像
     * @param conId
     * @param file
     * @return
     */
    @Override
    public MsgResult updateAvatar(int conId, MultipartFile file) {
        MsgResult result = new MsgResult();
        log.info("IMPL修改"+conId+"头像："+file);
        QiniuCloudUtil qiniuCloudUtil = new QiniuCloudUtil();
        log.info("IMPL文件："+file.getOriginalFilename());
        if(file.isEmpty()) {
            result.setStatusCode(201);
            result.setMsg("头像修改失败");
            return result;
        }
        try {
            String avatarPath= qiniuCloudUtil.saveImage(file);
            int i = consumerMapper.updateAvatar(conId, avatarPath);
            if (i> 0) {
                Consumer consumer = consumerMapper.selectByPrimaryKey(conId);
                result.setStatusCode(200);
                result.setMsg("头像修改成功");
                result.setData(consumer);
                return result;
            }
            result.setStatusCode(201);
            result.setMsg("头像修改失败");
            return result;

        } catch (IOException e) {
            e.printStackTrace();
            result.setStatusCode(201);
            result.setMsg("头像修改失败");
            return result;
        }
    }

    /**
     * 修改昵称
     * @param nicknameInfo
     * @return
     */
    @Override
    public MsgResult updateNickname(NicknameInfo nicknameInfo) {
        log.info("IMPL修改昵称："+nicknameInfo.toString());
        MsgResult result = new MsgResult();
        int i = consumerMapper.updateNickname(nicknameInfo.getConId(), nicknameInfo.getConName());
        if (i> 0) {
            Consumer consumer = consumerMapper.selectByPrimaryKey(nicknameInfo.getConId());
            result.setStatusCode(200);
            result.setMsg("昵称修改成功");
            result.setData(consumer);
            return result;
        }
        result.setStatusCode(201);
        result.setMsg("昵称修改失败");
        return result;

    }

    /**
     * 修改性别
     * @param sexInfo
     * @return
     */
    @Override
    public MsgResult updateSex(SexInfo sexInfo) {
        log.info("IMPL修改性别："+sexInfo.toString());
        MsgResult result = new MsgResult();
        int i = consumerMapper.updateSex(sexInfo.getConId(), sexInfo.getConSex());
        if (i> 0) {
            Consumer consumer = consumerMapper.selectByPrimaryKey(sexInfo.getConId());
            result.setStatusCode(200);
            result.setMsg("性别修改成功");
            result.setData(consumer);
            return result;
        }
        result.setStatusCode(201);
        result.setMsg("性别修改失败");
        return result;
    }

    /**
     * 修改邮箱账号
     * @param emailInfo
     * @return
     */
    @Override
    public MsgResult updateEmail(EmailInfo emailInfo) {
        log.info("IMPL修改邮箱账号："+emailInfo.toString());
        MsgResult result = new MsgResult();
        int i = consumerMapper.updateEmail(emailInfo.getConId(), emailInfo.getConMail());
        if (i> 0) {
            Consumer consumer = consumerMapper.selectByPrimaryKey(emailInfo.getConId());
            result.setStatusCode(200);
            result.setMsg("邮箱修改成功");
            result.setData(consumer);
            return result;
        }
        result.setStatusCode(201);
        result.setMsg("邮箱修改失败");
        return result;
    }

    /**
     * 修改手机号
     * @param phoneInfo
     * @return
     */
    @Override
    public MsgResult updatePhone(PhoneInfo phoneInfo) {
        log.info("IMPL修改手机账号："+phoneInfo.toString());
        MsgResult result = new MsgResult();
        int i = consumerMapper.updatePhone(phoneInfo.getConId(), phoneInfo.getConTel());
        if (i> 0) {
            Consumer consumer = consumerMapper.selectByPrimaryKey(phoneInfo.getConId());
            result.setStatusCode(200);
            result.setMsg("手机号修改成功");
            result.setData(consumer);
            return result;
        }
        result.setStatusCode(201);
        result.setMsg("手机号修改失败");
        return result;
    }

//    找回密码


    /**
     * 注销登录
     * @param conId
     * @return
     */
    @Override
    public MsgResult logoutConsumer(int conId) {
        log.info("IMPL++++注销登录............");
        MsgResult result = new MsgResult();
        int i = consumerMapper.logoutConsumer(conId);
        if (i > 0){
            result.setStatusCode(200);
            result.setMsg("注销成功");
            return result;
        }
        result.setStatusCode(201);
        result.setMsg("注销失败");
        return result;
    }

    /**
     * 用户登录====前台
     * @param consumerInfo
     * @return
     */
    @Override
    public MsgResult loginBackstage(ConsumerInfo consumerInfo) {
        String conTM = consumerInfo.getConTel();
        String password = consumerInfo.getPassword();
        log.info("登录账号："+conTM);
        log.info("登录密码："+password);
        //进行判断手机号或邮箱
        if (RegularExpression.orPhone(conTM)) {//是不是手机号
            Consumer consumer = consumerMapper.selectByphones(conTM);
            if (consumer !=null) {
                log.info("===============");
                if (consumer.getPassword().equals(password)) {
                    return new MsgResult(200, "登录成功", consumer);
                }else {
                    return new MsgResult(201, "用户名或密码错误", null);
                }
            } else {
                log.info("***************=========");
                return new MsgResult(201, "该账号不存在，请注册", null);
            }
        }
        if (RegularExpression.orEmail(conTM)) {//是不是邮箱号
            Consumer consumer = consumerMapper.selectOneByMS(conTM);
            if (consumer != null) {
                if (consumer.getPassword().equals(password)) {
                    return new MsgResult(200, "登录成功", consumer);
                }else {
                    return new MsgResult(201, "用户名或密码错误", null);
                }
            } else {
                return new MsgResult(201, "该账号不存在，请注册", null);
            }
        }
        log.info("------------*******------------*******");
        return new MsgResult(201, "账号格式错误", null);
    }


    //=============================================================================================================================================

    /**
     * 用户登录====前台
     */
    @Override
    public MsgResult login(ConsumerInfo consumerInfo) {
        String conTM = consumerInfo.getConTel();
        String password = consumerInfo.getPassword();
        log.info("登录账号："+conTM);
        log.info("登录密码："+password);
        //进行判断手机号或邮箱
        if (RegularExpression.orPhone(conTM)) {//是不是手机号
            Consumer consumer = consumerMapper.selectOneByTS(conTM);
            if (consumer !=null) {
                log.info("===============");
                if (consumer.getPassword().equals(password)) {
                    return new MsgResult(200, "登录成功", consumer);
                }else {
                    return new MsgResult(201, "用户名或密码错误", null);
                }
            } else {
                log.info("***************=========");
                return new MsgResult(201, "该账号不存在，请注册", null);
            }
        }
        if (RegularExpression.orEmail(conTM)) {//是不是邮箱号
            Consumer consumer = consumerMapper.selectOneByMS(conTM);
            if (consumer != null) {
                if (consumer.getPassword().equals(password)) {
                    return new MsgResult(200, "登录成功", consumer);
                }else {
                    return new MsgResult(201, "用户名或密码错误", null);
                }
            } else {
                return new MsgResult(201, "该账号不存在，请注册", null);
            }
        }
        log.info("------------*******------------*******");
        return new MsgResult(201, "账号格式错误", null);
    }

    /**
     * 找回密码
     */
    @Override
    public MsgResult backPassword(ConsumerInfo consumerInfo) {
        String conTM = consumerInfo.getConTel();
        String password = consumerInfo.getPassword();
        log.info("找回密码---账号："+conTM);
        log.info("找回密码---密码："+password);
        //进行判断手机号或邮箱
        if (RegularExpression.orPhone(conTM)) {//是不是手机号
            Consumer consumer = consumerMapper.selectOneByTS(conTM);
            return getBackPwdMsgResult(password, consumer);
        }
        if (RegularExpression.orEmail(conTM)) {//是不是邮箱
            Consumer consumer = consumerMapper.selectOneByMS(conTM);
            return getBackPwdMsgResult(password, consumer);
        }
        return new MsgResult(201, "账号格式错误", null);
    }

    /**
     * 设置预算
     */
    @Override
    public MsgResult updateBudget(BudgetInfo budgetInfo) {
        Double conBudget = budgetInfo.getConBudget();
        CurrentBudget currentBudget = new CurrentBudget();
        //获取当前年月日
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;

        Consumer consumer1 = consumerMapper.selectByPrimaryKey(budgetInfo.getConId());
        if (consumer1 != null){

            int i = consumerMapper.updateBudget(budgetInfo.getConId(),conBudget);
            if (i>0){
//                修改后的预算
                Consumer consumer2 = consumerMapper.selectByPrimaryKey(budgetInfo.getConId());
                //当月总支出
                Double monthTotalExpenditure = recordingMapper.selectExpendByConIdCurrentDate(budgetInfo.getConId(), year, month);
                if (monthTotalExpenditure == null){
                    monthTotalExpenditure = 0.0;
                }
                BigDecimal te = new BigDecimal(monthTotalExpenditure);
                Double monthTotalExpenditureSum = te.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                log.info(budgetInfo.getConId()+"在"+year+"年"+month+"月的支出"+monthTotalExpenditureSum);
//               预算剩余
                Double currentSurplus = consumer2.getConBudget() - monthTotalExpenditureSum;
                BigDecimal sy = new BigDecimal(currentSurplus);
                Double currentSurplu = sy.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                currentBudget.setCurrentBudget(currentSurplu);
                currentBudget.setCurrentExpenditure(monthTotalExpenditureSum);
                currentBudget.setCurrentSurplus(currentSurplu);
                return new MsgResult(200,"设置预算成功",currentBudget);
            }else {
                return new MsgResult(201,"设置预算失败",null);
            }
        }else {
            return new MsgResult(201,"用户不存在，信息错误",null);
        }
    }

    /**
     * 打卡状态查询
     */
    @Override
    public MsgResult selectSignInStatus(int conId) {
        //获取当前年月日
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DATE);
        //根据当前日期查询签到的数据记录
        Date signInDate = consumerMapper.selectSignInStatusById(conId, year, month, day);
        if (signInDate == null){
            return new MsgResult(201,"未进行打卡签到",null);
        }else {
            return new MsgResult(200,"已进行打卡签到",null);
        }
    }

    /**
     * 打卡操作
     */
    @Override
    public MsgResult signIn(int conId) {
        int i = consumerMapper.insertRecording(conId);
        if (i>0){
            return new MsgResult(200, "打卡成功", null);
        }else {
            return new MsgResult(200, "打卡失败", null);
        }
    }

    /**
     * 每月自动清除当前用户的预算
     */
    @Override
    public MsgResult autoClear(int conId) {
        CurrentBudget currentBudget = new CurrentBudget();
        //获取当前年月日
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DATE);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        Consumer consumer = new Consumer();
        Consumer consumer1 = consumerMapper.selectByPrimaryKey(conId);
        Double conBudget = consumer1.getConBudget();
        //======================================================================================
        //当月总支出
        Double monthTotalExpenditure = recordingMapper.selectExpendByConIdCurrentDate(conId, year, month);
        if (monthTotalExpenditure == null){
            monthTotalExpenditure = 0.0;
        }
        BigDecimal te = new BigDecimal(monthTotalExpenditure);
        Double monthTotalExpenditureSum = te.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        log.info(conId+"在"+year+"年"+month+"月的支出"+monthTotalExpenditureSum);
        currentBudget.setCurrentBudget(conBudget);
        currentBudget.setCurrentExpenditure(monthTotalExpenditureSum);
        //预算剩余
        Double currentSurplus = conBudget - monthTotalExpenditureSum;
        BigDecimal sy = new BigDecimal(currentSurplus);
        Double currentSurplu = sy.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        currentBudget.setCurrentSurplus(currentSurplu);
        //=========================================================================================
        if (day == 1) {
            consumer.setConId(conId);
            consumer.setConBudget(null);
            int i = consumerMapper.deleteBudget(conId);
            if (i>0){
                return new MsgResult(200, "月初清除预算成功", new CurrentBudget(0.0,0.0,0.0));
            }else {
                return new MsgResult(201, "月初清除预算失败", currentBudget);
            }
        }
        return new MsgResult(201, "不是月初，不进行清除预算", currentBudget);
    }


    /**
     * 返回MsgResult状态的方法
     */
    private MsgResult getBackPwdMsgResult(String password, Consumer consumer) {
        if (consumer != null) {
            consumer.setPassword(password);
            int i = consumerMapper.updateByPrimaryKey(consumer);
            if (i>0){
                return new MsgResult(200, "密码重置成功", consumer);
            }else {
                return new MsgResult(200, "密码重置失败", null);
            }
        } else {
            return new MsgResult(201, "该账号不存在，请重新输入", null);
        }
    }

//  ========================================XIXI=========================================================================

    /**
     * 分页查询
     * @param msgWords
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public MsgResult queryConsumersByWords(String msgWords,int pageNum,int pageSize) {
        log.info("ConsumerServiceImpl => queryConsumersByWords方法要查询的关键字是:"+msgWords);
        MsgResult msgResult = new MsgResult();
        PageHelper.startPage(pageNum,pageSize);
        List<Consumer> consumers = consumerMapper.queryConsumersByWords(msgWords);
        PageInfo<Consumer> pageInfo = new PageInfo<>(consumers);
        log.info(pageInfo.toString());
        if (pageInfo.getSize()>0){
            msgResult.setStatusCode(200);
            msgResult.setMsg("查询成功");
            msgResult.setData(pageInfo);
            return msgResult;
        }
        msgResult.setStatusCode(201);
        msgResult.setMsg("查询失败");
        return msgResult;
    }

    /**
     * 根据用户id删除该用户信息(修改该用户状态)
     * @param conId
     * @return
     */
    @Override
    public MsgResult deleteConsumer(int conId) {
        MsgResult msgResult = new MsgResult();
        consumerMapper.deleteConsumer(conId);
        if (conId>0){
            List<Consumer> consumers = consumerMapper.queryConsumersByWords("");
            msgResult.setStatusCode(200);
            msgResult.setMsg("删除成功");
            msgResult.setData(consumers);
            return msgResult;
        }
        msgResult.setStatusCode(201);
        msgResult.setMsg("删除失败");
        return msgResult;
    }

    /**
     * 修改用户信息
     * @param conId
     * @return
     */
    @Override
    public MsgResult changeStatus(int conId) {
        MsgResult msgResult = new MsgResult();
        consumerMapper.changeStatus(conId);
        if(conId>0){
            List<Consumer> consumers = consumerMapper.queryConsumers();
            msgResult.setStatusCode(200);
            msgResult.setMsg("修改成功");
            msgResult.setData(consumers);
            return msgResult;
        }
        msgResult.setStatusCode(201);
        msgResult.setMsg("修改失败");
        return msgResult;
    }

    /**
     * 批量删除用户信息(修改状态)
     * @param conIds
     * @return
     */
    @Override
    public MsgResult deleteConsumers(int[] conIds) {
        MsgResult msgResult = new MsgResult();
        for (int id:conIds){
            if (id>0){
                consumerMapper.deleteConsumer(id);
            }else {
                msgResult.setStatusCode(201);
                msgResult.setMsg("批量删除失败");
                return msgResult;
            }
        }
//        List<Consumer> consumers =consumerMapper.queryConsumers();
        msgResult.setStatusCode(200);
        msgResult.setMsg("批量删除成功");
//        msgResult.setData(consumers);
        return msgResult;
    }

    /**
     * 修改用户信息
     * @param consumerParam
     * @return
     */
    @Override
    public MsgResult updateConsumerById(ConsumerParam consumerParam) {
        Consumer consumer = new Consumer();

        consumer.setConId(consumerParam.getConId());

        consumer.setConName(consumerParam.getConName());

        consumer.setConAvatar(consumerParam.getConAvatar());

        consumer.setConSex(consumerParam.getConSex());

        consumer.setConMail(consumerParam.getConMail());

        consumer.setConTel(consumerParam.getConTel());

        consumer.setConStatus(consumerParam.getConStatus());

        MsgResult msgResult = new MsgResult();
        int i = consumerMapper.updateByPrimaryKeySelective(consumer);
        if (i>0){
            msgResult.setStatusCode(200);
            msgResult.setMsg("修改成功");
            return msgResult;
        }

        msgResult.setStatusCode(201);
        msgResult.setMsg("修改失败");
        return msgResult;
    }

    //=============================================================================================================================================
//==================================================前端后台台接口===========================================================================================
//=============================================================================================================================================

    /**
     * 修改用户信息
     * @param consumerParam
     * @return
     */
    /*@Override
    public MsgResult updateConsumerById(ConsumerParam consumerParam) {
        Consumer consumer = new Consumer();

        consumer.setConId(consumerParam.getConId());

        consumer.setConName(consumerParam.getConName());

        consumer.setConAvatar(consumerParam.getConAvatar());

        consumer.setConSex(consumerParam.getConSex());

        consumer.setConMail(consumerParam.getConMail());

        consumer.setConTel(consumerParam.getConTel());

        consumer.setConStatus(consumerParam.getConStatus());

        MsgResult msgResult = new MsgResult();
        int i = consumerMapper.updateByPrimaryKeySelective(consumer);
        if (i>0){
            msgResult.setStatusCode(200);
            msgResult.setMsg("修改成功");
            return msgResult;
        }

        msgResult.setStatusCode(201);
        msgResult.setMsg("修改失败");
        return msgResult;
    }*/

}
