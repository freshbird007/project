package com.leaves.smalltiger.consumer.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CurrentBudget {
    private Double currentSurplus;
    private Double currentBudget;
    private Double currentExpenditure;
}
