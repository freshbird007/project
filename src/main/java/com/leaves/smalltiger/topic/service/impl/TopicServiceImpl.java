package com.leaves.smalltiger.topic.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leaves.smalltiger.common.po.Topic;
import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.topic.mapper.TopicMapper;
import com.leaves.smalltiger.topic.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author WUTONG
 * @date 19/12/07 上午 11:41
 */
@Slf4j
@Service
public class TopicServiceImpl implements TopicService {

    @Autowired
    private TopicMapper topicMapper;

    @Override
    public MsgResult selectTopics() {
        List<Topic> topics = topicMapper.selectTopics();
        log.info("TopicServiceImpl --> selectTopics:"+topics.toString());
        return new MsgResult(200,"查询成功",topics);
    }

    //==========================后台=================================

    /**
     * 查询所有话题，无论状态
     * @return
     */
    @Override
    public MsgResult selectAllTopic(String topicWords,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Topic> topics = topicMapper.selectTopicByWords(topicWords);
        PageInfo<Topic> topicPageInfo = new PageInfo<>(topics);
        log.info("TopicServiceImpl --> selectAllTopic topicPageInfo:"+topicPageInfo);
        return new MsgResult(200,"查询成功",topicPageInfo);
    }

    /**
     * 批量删除话题
     * @param topicIds
     * @return
     */
    @Transactional
    @Override
    public MsgResult deleteTopics(int[] topicIds) {
        for (int topicId : topicIds){
            if (topicId<=0){
                log.info("TopicServiceImpl --> deleteTopics:传入的id有负数");
                return new MsgResult(201,"屏蔽话题失败",null);
            }else {
                log.info("TopicServiceImpl --> deleteTopics:成功");
                topicMapper.deleteTopic(topicId);
            }
        }
        return new MsgResult(200,"屏蔽话题成功",topicIds);
    }

    /**
     * 批量解封话题
     * @param topicIds
     * @return
     */
    @Transactional
    @Override
    public MsgResult resumeTopics(int[] topicIds) {
        for (int topicId: topicIds) {
            topicMapper.resumeTopic(topicId);
        }
        log.info("TopicServiceImpl --> resumeTopics:成功");
        return new MsgResult(200,"解封话题成功",topicIds);
    }

    /**
     * 新增一条话题
     * @param topic
     * @return
     */
    @Override
    public MsgResult insertTopic(Topic topic) {
        int i = topicMapper.insert(topic);
        if (i>0){
            log.info("TopicServiceImpl --> insertTopic:"+topic.toString());
            return new MsgResult(200,"新增话题成功",topic);
        }else {
            return new MsgResult(201,"新增话题失败",null);
        }
    }
}
