package com.leaves.smalltiger.consumer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * 注册账户信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegConsumer {
    private String account;//注册账号
    private String password;//密码
}
