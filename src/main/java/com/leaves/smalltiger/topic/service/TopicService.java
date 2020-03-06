package com.leaves.smalltiger.topic.service;

import com.leaves.smalltiger.common.po.Topic;
import com.leaves.smalltiger.common.utils.MsgResult;

/**
 * @author WUTONG
 * @date 19/12/07 上午 11:40
 */
public interface TopicService {

    public MsgResult selectTopics();

    /**
     * 查询所有话题,后台使用
     * @return
     */
    public MsgResult selectAllTopic(String topicWords,int pageNum,int pageSize);

    /**
     * 批量禁用话题
     * @param topicIds
     * @return
     */
    public MsgResult deleteTopics(int[] topicIds);

    /**
     * 批量恢复话题
     * @param topicIds
     * @return
     */
    public MsgResult resumeTopics(int[] topicIds);

    /**
     * 新增话题
     * @param topic
     */
    public MsgResult insertTopic(Topic topic);


}
