package com.leaves.smalltiger.detail.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 账单接口接收对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class YearMonthData {
    private int conId;
    private int dateYear;
    private int dateMonth;
}
