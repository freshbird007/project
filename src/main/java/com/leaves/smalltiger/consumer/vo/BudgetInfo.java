package com.leaves.smalltiger.consumer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
/**
 * 用于设置预算接收数据的封装类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BudgetInfo {
    private Integer conId;
    private Double conBudget;
}
