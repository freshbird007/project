package com.leaves.smalltiger.community.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CommunityInsert {

    private Integer conId;

    private String msgWords;

    private String msgCTime;

    private String msgImage1;

//    private String msgImage2;
//
//    private String msgImage3;
//
//    private String msgImage4;
//
//    private String msgImage5;
//
//    private String msgImage6;
//
//    private String msgImage7;
//
//    private String msgImage8;
//
//    private String msgImage9;
}
