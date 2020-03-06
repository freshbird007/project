package com.leaves.smalltiger.consumer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailVerify {
    private String conMail;//邮箱账号
    private int emailType;//验证类型(用于验证这个邮箱需要做什么)
}
