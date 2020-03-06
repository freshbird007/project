package com.leaves.smalltiger.community.controller;

import com.leaves.smalltiger.common.po.Consumer;
import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.common.utils.QiniuCloudUtil;
import com.leaves.smalltiger.community.service.CommunityService;
import com.leaves.smalltiger.community.vo.CommunityInsert;
import com.leaves.smalltiger.community.vo.FinalName;
import com.leaves.smalltiger.community.vo.FormData;
import com.leaves.smalltiger.community.vo.Postings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sun.text.resources.FormatData;

import java.io.IOException;
import java.util.List;

/**
 * @author WUTONG
 * @date 19/12/07 上午 11:52
 */
@Slf4j
@CrossOrigin
@ResponseBody
@RestController
@RequestMapping(value = "/api")
public class CommunityController {

    @Autowired
    private CommunityService communityService;

    /**
     * 查询所有状态为1的留言
     * @return
     */
    @RequestMapping(value = "selectCommunities",method = RequestMethod.GET)
    public MsgResult selectCommunities(){
        log.info("==========");
        MsgResult msgResult = communityService.selectCommunities();
        log.info("msgResult:"+msgResult);
        return  msgResult;
    }

    /**
     * 新增
     */
    @RequestMapping(value = "insertCommunity",method = RequestMethod.POST)
    public MsgResult insertCommodity(@RequestBody CommunityInsert communityInsert){
        MsgResult msgResult = communityService.insertCommunity(communityInsert);
        log.info("CommunityController --> insertCommodity:"+communityInsert.toString());
        return msgResult;
    }
//图片
    @RequestMapping(value = "upload",method = RequestMethod.POST)
    public void insertCommodity(@RequestParam("file") MultipartFile file){
        FinalName finalName = new FinalName();

        QiniuCloudUtil qiniuCloudUtil = new QiniuCloudUtil();
        log.info("IMPL文件："+file.getOriginalFilename());
        if(file.isEmpty()) {
            log.info("文件为空...........");
        }
        try {
            finalName.setImgurl(qiniuCloudUtil.saveImage(file));
            log.info("文件上传成功："+finalName.getImgurl());

        } catch (IOException e) {
            e.printStackTrace();
            log.info("===异常");
        }
    }

//后台使用

    /**
     * 查询所有的留言
     * @return
     */
    @RequestMapping(value = "selectAllCommunity",method = RequestMethod.GET)
    public MsgResult selectAllCommunity(){
        MsgResult msgResult = communityService.selectAllCommunity();
        log.info("CommunityController --> selectAllComunity");
        return msgResult;
    }

    /**
     * 模糊查询,集成分页
     */
    @RequestMapping(value = "selectCommunityByWords",method = RequestMethod.GET)
    public MsgResult selectCommunityByNum(@RequestParam(value = "msgWords",defaultValue = "")String msgWords,
                                          @RequestParam(value = "pageNum",defaultValue = "1")int pageNum,
                                          @RequestParam(value = "pageSize",defaultValue = "10")int pageSize){
        log.info("CommunityController --> selectAllCommunityByWords:  msgWords:"+msgWords);
        MsgResult msgResult = communityService.selectCommunityByWords(msgWords, pageNum, pageSize);
        return msgResult;
    }

    /**
     * 根据msgId冯进留言,(实际为改变该条留言的状态为0)
     */
    @RequestMapping(value = "deleteCommunity",method = RequestMethod.PUT)
    public MsgResult deleteCommunityById(int[] msgIds){
        MsgResult msgResult = communityService.deleteCommunityByMsgId(msgIds);
        log.info("CommunityController --> insertCommodity:"+msgIds);
        return msgResult;
    }

    /**
     * 根据msgId通过留言
     */
    @RequestMapping(value = "resumeCommunity",method = RequestMethod.PUT)
    public MsgResult resumeCommodity(int[] msgIds){
        MsgResult msgResult = communityService.resumeCommunityByMsgId(msgIds);
        log.info("CommunityController --> insertCommodity:"+msgIds);
        return msgResult;
    }


//    ===========================================================================================
//    @RequestMapping(value = "/uploads" , method = RequestMethod.POST)
//    public MsgResult uplod(@RequestParam FormData file){
//
//      log.info("发帖t图片。。。。。。。。。。"+file);
//        MsgResult result = new MsgResult();
//        result.setData(200);
//        result.setMsg("发帖成功");
//        return result;
//    }
    @RequestMapping(value = "/uploads" , method = RequestMethod.POST)
    public MsgResult uplod(@RequestParam("msgImage1")MultipartFile files){
//        for (MultipartFile file : files) {
//            log.info("发帖t图片。。。。。。。。。。" + file);
//        }
        log.info("发帖t图片。。。。。。。。。。" + files);
        MsgResult result = new MsgResult();
        result.setData(200);
        result.setMsg("发帖成功");
        return result;
    }
}
