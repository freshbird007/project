package com.leaves.smalltiger.detail.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
/**
 * @author James
 * @date 2019/12/12 17:15
 */
public class DetailMingXi {
    private String conName;

    private int contId;

    private int detId;

    private int conId;

    private int detsort;

    private String detRemark;

    private double detAmount;

    private int detStatus;

    private Date detTime;
}
