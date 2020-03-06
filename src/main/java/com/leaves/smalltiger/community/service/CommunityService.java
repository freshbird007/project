package com.leaves.smalltiger.community.service;

import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.community.vo.CommunityInsert;
import org.springframework.web.multipart.MultipartFile;

public interface CommunityService {

    /**
     * 展示所有留言，前台使用
     *
     * @return
     */
    public MsgResult selectCommunities();

    /**
     * 新增留言 可以配图
     * @param communityInsert
     * @return
     */
    public MsgResult insertCommunity(CommunityInsert communityInsert);



//    ====================================后台使用============================
    /**
     * 查询所有留言,后台使用
     * @return
     */
    public MsgResult selectAllCommunity();

    /**
     * 分页查询,模糊搜索的绑定,默认查询的是"",1,10
     * @param msgWords
     * @param pageNum
     * @param pageSize
     * @return
     */
    public MsgResult selectCommunityByWords(String msgWords,int pageNum,int pageSize);

    /**
     * 根据msgId删除留言
     * @param msgIds
     * @return
     */
    public MsgResult deleteCommunityByMsgId(int[] msgIds);

    /**
     * 根据msgId恢复留言
     * @param msgIds
     * @return
     */
    public MsgResult resumeCommunityByMsgId(int[] msgIds);
}
