package com.leaves.smalltiger.community.vo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.xml.crypto.Data;
import java.util.Date;

@lombok.Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommunityRsg {
    private String conName;//用户名

    private int msgId; //留言Id

    private int conId; //用户Id

    private String msgWords; //留言内容

    private Date msgCTime; //留言时间

    private int msgStatus; //留言状态

    private String msgImage1; //图片1

    private String msgImage2;

    private String msgImage3;

    private String msgImage4;

    private String msgImage5;

    private String msgImage6;

    private String msgImage7;

    private String msgImage8;

    private String msgImage9;


}
