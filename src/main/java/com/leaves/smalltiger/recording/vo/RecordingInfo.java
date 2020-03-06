package com.leaves.smalltiger.recording.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *  我的界面查询记录返回的封装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecordingInfo {
    private Integer continuousSignInDay;//连续打卡天数
    private Integer accountDays;//记账总天数
    private Integer detailTotal;//记账总笔数
}
