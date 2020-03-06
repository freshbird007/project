package com.leaves.smalltiger.consumer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 修改头像
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AvatarInfo {
    private int conId;//id
    private String conAvatar;//图片路径
}
