package com.leaves.smalltiger.detail.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataTime {
    private String time;
    private List<DetailHome> detailHomes;
}
