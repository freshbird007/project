package com.leaves.smalltiger.topic.controller;

import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.topic.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author WUTONG
 * @date 19/12/07 上午 11:47
 */

@RestController
@Slf4j
@CrossOrigin
@ResponseBody
@RequestMapping(value = "/api")
public class TopicController {
    @Autowired
    private TopicService topicService;

    /**
     * 查询话题
     * @return
     */
    @RequestMapping(value = "/selectTopics",method = RequestMethod.GET)
    public MsgResult selectTopics(){
        MsgResult msgResult = topicService.selectTopics();
        log.info("TopicController --> selectTopics");
        return msgResult;
    }
//    =========后台========
    /**
     * 后台使用，查询所有话题
     * @return
     */
    @RequestMapping(value = "/selectAllTopic",method = RequestMethod.GET)
    public MsgResult selectAllTopic(@RequestParam(value = "topicWords",defaultValue = "")String topicWords,
                                    @RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                    @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        MsgResult msgResult = topicService.selectAllTopic(topicWords,pageNum,pageSize);
        log.info("TopicController --> selectAllTopic");
        return msgResult;
    }

    /**
     * 批量禁用话题
     * @param topicIds
     * @return
     */
    @RequestMapping(value = "/deleteTopics",method = RequestMethod.PUT)
    public MsgResult deleteTopics(int[] topicIds){
        MsgResult msgResult = topicService.deleteTopics(topicIds);
        log.info("TopicController --> deleteTopics");
        return msgResult;
    }

    /**
     * 批量解封话题
     * @param topicIds
     * @return
     */
    @RequestMapping(value = "/resumeTopics",method = RequestMethod.PUT)
    public MsgResult resumeTopic(int[] topicIds){
        MsgResult msgResult = topicService.resumeTopics(topicIds);
        log.info("TopicController --> resumeTopic");
        return msgResult;
    }
}
