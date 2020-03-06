package com.leaves.smalltiger.common.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "detail")
public class Detail {
    @Id
    @Column(name="detId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Integer detId;
    @Column(name="conId")
    private Integer conId;
    @Column(name="detSort")
    private Integer detSort;
    @Column(name="contId")
    private Integer contId;
    @Column(name="detAmount")
    private Double detAmount;
    @Column(name="detRemark")
    private String detRemark;
    @Column(name="detTime")
    private Date detTime;
    @Column(name="detStatus")
    private Integer detStatus;

}
