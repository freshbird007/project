package com.leaves.smalltiger.consumer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 修改昵称信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class NicknameInfo {
    private int conId;//id
    private String conName;//昵称
}
