package com.leaves.smalltiger.common.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "community")
public class Community {
    @Id
    @Column(name = "msgId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Integer msgId;
    @Column(name = "conId")
    private Integer conId;
    @Column(name = "msgWords")
    private String msgWords;
    @Column(name = "msgCTime")
    private Date msgCTime;
    @Column(name = "msgStatus")
    private Integer msgStatus;
    @Column(name = "msgImage1")
    private String msgImage1;
    @Column(name = "msgImage2")
    private String msgImage2;
    @Column(name = "msgImage3")
    private String msgImage3;
    @Column(name = "msgImage4")
    private String msgImage4;
    @Column(name = "msgImage5")
    private String msgImage5;
    @Column(name = "msgImage6")
    private String msgImage6;
    @Column(name = "msgImage7")
    private String msgImage7;
    @Column(name = "msgImage8")
    private String msgImage8;
    @Column(name = "msgImage9")
    private String msgImage9;
}
