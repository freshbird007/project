package com.leaves.smalltiger.community.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leaves.smalltiger.common.po.Community;
import com.leaves.smalltiger.common.po.Consumer;
import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.common.utils.QiniuCloudUtil;
import com.leaves.smalltiger.community.mapper.CommunityMapper;
import com.leaves.smalltiger.community.service.CommunityService;
import com.leaves.smalltiger.community.vo.CommunityInsert;
import com.leaves.smalltiger.community.vo.CommunityRsg;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    private CommunityMapper communityMapper;

    @Override
    public MsgResult selectCommunities() {
        List<Community> communities = communityMapper.selectCommunitiesByMsgStatus();
        log.info("CommunityServiceImpl --> selectCommunities:"+communities.toString());
        return new MsgResult(200,"查询成功",communities);
    }

    @Override
    public MsgResult insertCommunity(CommunityInsert communityInsert) {
        log.info("发帖内容" + communityInsert.toString());
        Community community = new Community();
        community.setConId(communityInsert.getConId());
        community.setMsgWords(communityInsert.getMsgWords());
        community.setMsgImage1(communityInsert.getMsgImage1());
//        community.setMsgImage2(communityInsert.getMsgImage2());
//        community.setMsgImage3(communityInsert.getMsgImage3());
//        community.setMsgImage4(communityInsert.getMsgImage4());
//        community.setMsgImage5(communityInsert.getMsgImage5());
//        community.setMsgImage6(communityInsert.getMsgImage6());
//        community.setMsgImage7(communityInsert.getMsgImage7());
//        community.setMsgImage8(communityInsert.getMsgImage8());
//        community.setMsgImage9(communityInsert.getMsgImage9());
        //将传入的String格式转成Date类型
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition parsePosition = new ParsePosition(0);
        Date parse = simpleDateFormat.parse(communityInsert.getMsgCTime(), parsePosition);
        community.setMsgCTime(parse);
        community.setMsgStatus(1);
        int i = communityMapper.insertSelective(community);
        if (i>0){
            log.info("CommunityServiceImpl --> insertCommunity:"+community.toString());
            return new MsgResult(200,"发帖成功",community);
        }else {
            return new MsgResult(201,"发帖失败",null);
        }
    }

//    ===============================后台使用===================================

    /**
     * 查询所有的留言
     * @return
     */
    @Override
    public MsgResult selectAllCommunity() {
        List<CommunityRsg> communities = communityMapper.queryCom();
        log.info("CommunityServiceImpl --> selectAllCommunity"+communities.toString());
        return new MsgResult(200,"查询成功",communities);
    }

    /**
     *  根据三个参数查留言,默认值"",1,10
     * @param msgWords
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public MsgResult selectCommunityByWords(String msgWords, int pageNum, int pageSize) {
        log.info("CommunityServiceImpl --> selectAllCommunityByWords: msgWords:"+msgWords);
        PageHelper.startPage(pageNum,pageSize);
        List<Community> communities = communityMapper.selectCommodityByWords(msgWords);
        PageInfo<Community> communityPageInfo = new PageInfo<>(communities);
        log.info(communityPageInfo.toString());
        MsgResult msgResult = new MsgResult();
        msgResult.setStatusCode(200);
        msgResult.setMsg("查询成功");
        msgResult.setData(communityPageInfo);
        return msgResult;
    }

    /**
     * 根据id删除留言
     * @param msgIds
     * @return
     */
    @Override
    public MsgResult deleteCommunityByMsgId(int[] msgIds) {
        for (int msgId: msgIds){
            communityMapper.deleteCommunityByMsgId(msgId);
        }
        log.info("CommunityServiceImpl --> deleteCommunityByMsgId"+msgIds.toString());
        return new MsgResult(200,"屏蔽留言成功",msgIds);
    }


    /**
     * 根据msgId恢复留言
     * @param msgIds
     * @return
     */
    @Override
    public MsgResult resumeCommunityByMsgId(int[] msgIds) {
        for (int msgId: msgIds){
            communityMapper.resumeCommunityByMsgId(msgId);
        }
        log.info("CommunityServiceImpl --> resumeCommunityByMsgId"+msgIds.toString());
        return new MsgResult(200,"恢复留言成功",msgIds);
    }
}

