package com.leaves.smalltiger.consumer.mapper;
import java.util.Date;
import java.util.List;
import	java.util.regex.Pattern;
import	java.util.Map;

import com.leaves.smalltiger.common.config.BaseMapper;
import com.leaves.smalltiger.common.po.Consumer;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ConsumerMapper extends BaseMapper<Consumer> {
    /**
     * 根据邮箱账户查询
     * @param conMail
     * @return
     */
    @Select("SELECT * FROM consumer WHERE CONMAIL = #{CONMAIL} AND CONSTATUS = 1")
    public Consumer selectByMail(@Param("CONMAIL") String conMail);

    /**
     * 根据手机号查询
     * @param conTel
     * @return
     */
    @Select("SELECT * FROM consumer WHERE CONTEL = #{CONTEL}")
    public Consumer selectByPhone(@Param("CONTEL") String conTel);

    /**
     * 修改头像
     * @param conId
     * @param conAvatar
     * @return
     */
    @Update("UPDATE consumer SET conAvatar=#{conAvatar} WHERE conId=#{conId}")
    public int updateAvatar(@Param("conId")int conId,@Param("conAvatar")String conAvatar);

    /**
     * 修改昵称
     * @param conId
     * @param conName
     * @return
     */
    @Update("UPDATE consumer SET conName=#{conName} WHERE conId=#{conId}")
    public int updateNickname(@Param("conId")int conId,@Param("conName")String conName);

    /**
     * 修改性别
     * @param conId
     * @param conSex
     * @return
     */
    @Update("UPDATE consumer SET conSex=#{conSex} WHERE conId=#{conId}")
    public int updateSex(@Param("conId")int conId,@Param("conSex")int conSex);

    /**
     * 修改邮箱账号
     * @param conId
     * @param conMail
     * @return
     */
    @Update("UPDATE consumer SET conMail=#{conMail} WHERE conId=#{conId}")
    public int updateEmail(@Param("conId")int conId,@Param("conMail")String conMail);

    /**
     * 修改手机号
     * @param conId
     * @param conTel
     * @return
     */
    @Update("UPDATE consumer SET conTel=#{conTel} WHERE conId=#{conId}")
    public int updatePhone(@Param("conId")int conId,@Param("conTel")String conTel);

    /**
     * 注销登录（状态改为0)
     * @param conId
     * @return
     */
    @Update("UPDATE consumer SET conStatus= 2 WHERE conId=#{conId}" )
    public int logoutConsumer(@Param("conId")int conId);

    /**
     * 根据手机号查询状态为5的=====后台登录
     * @param conTel
     * @return
     */
    @Select("SELECT * FROM consumer WHERE conTel=#{conTel} AND conStatus= 5")
    public Consumer selectByphones(@Param("conTel")String conTel);

    /**
     * 设置用户预算
     */
    @Update("update consumer set conBudget = #{conBudget} where conId = #{conId}")
    public int updateBudget(@Param("conId")int conId, @Param("conBudget")double conBudget);

    /**
     * 清除用户预算
     */
    @Update("update consumer set conBudget = NULL where conId = #{conId}")
    public int deleteBudget(@Param("conId")int conId);


//**************************************zhangbo********************************************************************************
    /**
     * 根据手机号查询状态为1的=====前台登录
     * @param conTM
     * @return
     */
    @Select("SELECT * FROM consumer WHERE conTel=#{conTM} AND conStatus= 1")
    public Consumer selectOneByTS(@Param("conTM") String conTM);

    /**
     * 根据邮箱查询
     * @param conTM
     * @return
     */
    @Select("SELECT * FROM consumer WHERE conMail=#{conTM} AND conStatus= 1")
    public Consumer selectOneByMS(@Param("conTM") String conTM);

    /**
     * 查询当前日期的打卡状态
     * @param conId
     * @param year
     * @param month
     * @param day
     * @return
     */
    @Select("SELECT recTime FROM recording WHERE conId = #{conId} AND YEAR(recTime) = #{year} AND MONTH(recTime) = #{month} AND DAY(recTime) = #{day}")
    public Date selectSignInStatusById(@Param("conId") int conId, @Param("year") int year, @Param("month") int month, @Param("day") int day);

    /**
     * 打卡
     * @param conId
     * @return
     */
    @Insert("INSERT INTO recording (conId) values (#{conId})")
    public int insertRecording(@Param("conId") int conId);

//=======================================XIXI========================================================================

    /**
     * 查询所有用户的详细信息
     * @return
     */
    @Select("SELECT conId, conName,password,conAvatar,conSex,conTel,conMail,conStatus,conCreateTime FROM consumer")
    public List<Consumer> queryConsumers();

    /**
     * 模糊查询+分页
     * @param msgWords
     * @return
     */
    @Select("SELECT * FROM consumer WHERE conName LIKE '%${msgWords}%' ORDER BY conId")
    public List<Consumer> queryConsumersByWords(@Param(value = "msgWords") String msgWords);

    /**
     *删除用户信息(假删除，真修改用户状态)
     */
    @Update("UPDATE consumer SET conStatus=0 WHERE conId = #{conId}")
    public void deleteConsumer(int conId);

    /**
     * 修改用户状态为1
     * @param conId
     */
    @Update("UPDATE consumer SET conStatus=1 WHERE conId = #{conId}")
    public void changeStatus(int conId);
}
