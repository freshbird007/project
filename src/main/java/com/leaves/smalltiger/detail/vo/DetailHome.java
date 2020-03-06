package com.leaves.smalltiger.detail.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DetailHome {
/*
* DetailHome(contIcon=img09, detRemark=外快, detSort=1,
* detAmount=500.0, detTime=Thu Dec 05 15:59:05 CST 2019,
* contName=娱乐, conId=1)
*
* detId
conId
detSort
contId
detAmount
detRemark
detTime
detStatus*/
    private int detId;//明细id
    private String contIcon;//类型图片
    private String detRemark;//备注
    private int detSort;//明细类型 1.收入  2.支出
    private double detAmount;//明细对应金额
    private Date detTime;//记账准确时间
    private String contName;//记账对应类型
    private int conId;//用户id

}
