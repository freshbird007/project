package com.leaves.smalltiger.consumer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 邮箱账号修改
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailInfo {
    private int conId;
    private String conMail;
}
