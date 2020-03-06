package com.leaves.smalltiger.recording.controller;
import	java.awt.Desktop.Action;

import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.recording.service.RecordingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@CrossOrigin
@ResponseBody
public class RecordingController {
    @Autowired
    private RecordingService recordingService;
    /**
     * 我的界面===个人信息记录
     */
    @RequestMapping(value = "/recording",method = RequestMethod.GET)
    public MsgResult recordingInfoSelect(@RequestParam("conId") int conId){
        MsgResult msgResult = recordingService.recordingInfoSelect(conId);
        return msgResult;
    }
    /**
     * 当前用户当月账单信息
     */
    @RequestMapping(value = "/accounts" , method = RequestMethod.GET)
    public MsgResult accountSelect(@RequestParam("conId") int conId){
        log.info("===="+conId);
        MsgResult msgResult = recordingService.dataSelect(conId);
        return msgResult;
    }
}
