package com.leaves.smalltiger.consumer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SMSVerify {
    private String conTel;//手机账号
    private int telType;//验证类型(用于验证这个手机账号需要做什么)
}
