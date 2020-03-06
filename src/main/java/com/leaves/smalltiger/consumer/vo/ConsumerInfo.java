package com.leaves.smalltiger.consumer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 用于登录和找回密码的接收数据的封装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ConsumerInfo {
    private String conTel;
    private String password;
}
