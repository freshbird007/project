package com.leaves.smalltiger.topic.mapper;

import com.leaves.smalltiger.common.config.BaseMapper;
import com.leaves.smalltiger.common.po.Topic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author WUTONG
 * @date 19/12/07 上午 11:43
 */
@Mapper
@Repository
public interface TopicMapper extends BaseMapper<Topic> {

    /**
     * 查询所有状态为1的话题
     */
    @Select("select * from topic where topicStatus = 1")
    public List<Topic> selectTopics();


    //后台

    /**
     * 模糊查询
     * @param topicWords
     * @return
     */
    @Select("select * from topic where topicWords like '%${topicWords}%'")
    public List<Topic> selectTopicByWords(@Param("topicWords")String topicWords);

    /**
     * 删除话题
     * @param topicid
     */
    @Select("update topic set topicStatus=0 where topicId=#{topicId}")
    public void deleteTopic(@Param("topicId")int topicid);

    /**
     * 恢复话题
     * @param topicid
     */
    @Select("update topic set topicStatus=1 where topicId=#{topicId}")
    public void resumeTopic(@Param("topicId")int topicid);
}
