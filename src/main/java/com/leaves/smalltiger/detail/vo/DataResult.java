package com.leaves.smalltiger.detail.vo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 统计某年某月总收入和总支出
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DataResult {
    private int modernYear;//当前年份
    private int modernMonth;//当前月份
    private Double monthTotalExpenditure;//每月支出总金额
    private Double monthTotalIncome;//每月收入总金额
    private Double monthSurplus;//每月结余

}
