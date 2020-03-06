package com.leaves.smalltiger.common.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "consumptiontype")//此实体映射consumptiontype表
public class ConsumptionType {
    @Id
    @Column(name="contId")
    private Integer contId;
    @Column(name="contName")
    private String contName;
    @Column(name="contIcon")
    private String contIcon;
    @Column(name="contStatus")
    private Integer contStatus;

}
