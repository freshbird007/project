package com.leaves.smalltiger.detail.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
/**
 * @author WUTONG
 * @date 19/12/07 上午 09:30
 */
public class DetailIndex {
    private double detAmount;
    private String detRemark;
    private Date detTime;
    private int detSort;
    private String contIcon;
    private String contName;
}
