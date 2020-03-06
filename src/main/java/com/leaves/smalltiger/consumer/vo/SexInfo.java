package com.leaves.smalltiger.consumer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 性别修改
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SexInfo {
    private int conId;//id
    private int conSex;//性别：1-男、2-女
}
