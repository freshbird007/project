package com.leaves.smalltiger.consumer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 手机号修改
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PhoneInfo {
    private int conId;
    private String conTel;//电话
}
