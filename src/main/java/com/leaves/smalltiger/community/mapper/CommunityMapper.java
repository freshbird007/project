package com.leaves.smalltiger.community.mapper;

import com.leaves.smalltiger.common.config.BaseMapper;
import com.leaves.smalltiger.common.po.Community;
import com.leaves.smalltiger.community.vo.CommunityRsg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommunityMapper extends BaseMapper<Community> {
    /**
     * 查询所有状态为0的评论
     * @param
     * @return
     */
    @Select("SELECT * FROM community WHERE msgStatus = 0 order by msgCTime desc")
    public List<Community> selectCommunitiesByMsgStatus();

    //后台
    /**
     * 根据msgId封禁一条留言(实际为改变该留言状态)
     * @param
     */
    @Select("update community set msgStatus = 2 where msgId = #{msgId}")
    public void deleteCommunityByMsgId (@Param("msgId")int msgId);


    /**
     * 模糊查询
     * @param msgWords
     * @return
     */
    @Select("select * from community where msgWords like '%${msgWords}%'")
    public List<Community> selectCommodityByWords(@Param("msgWords")String msgWords);

    /**
     * 通过留言
     * @param msgId
     */
    @Select("update community set msgStatus=0 where msgId=#{msgId}")
    public void resumeCommunityByMsgId(@Param("msgId")int msgId);
    /**
     * 查询留言
     * @return
     */
    @Select("SELECT  con.conName, com.msgId , com.conId , com.msgWords , com.msgCTime , com.msgStatus , " +
            "com.msgImage1 ,com.msgImage2 ,com.msgImage3 ,com.msgImage4 ,com.msgImage5 ,com.msgImage6 ," +
            "com.msgImage7 ,com.msgImage8 ,com.msgImage9, com.msgCTime " +
            "FROM community com, consumer con where com.conId=con.conId")
    public List<CommunityRsg> queryCom();
}
