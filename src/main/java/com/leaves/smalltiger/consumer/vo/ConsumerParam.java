package com.leaves.smalltiger.consumer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConsumerParam {
    private Integer conId;//用户id

    private String conName;//用户名

    private String conAvatar;//用户头像

    private Integer conSex;//性别

    private String conTel;//电话

    private String conMail;//邮箱

    private Integer conStatus;//状态

}
