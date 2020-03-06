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
@Table(name = "consumer")//此实体映射consumer表
public class Consumer {
    @Id
    @Column(name="conId")//如果属性和字段名称相符则此注解可以省略
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Integer conId;
    @Column(name="conName")
    private String conName;
    @Column(name="password")
    private String password;
    @Column(name="conAvatar")
    private String conAvatar;
    @Column(name="conSex")
    private Integer conSex;
    @Column(name="conTel")
    private String conTel;
    @Column(name="conMail")
    private String conMail;
    @Column(name="conBudget")
    private Double conBudget;
    @Column(name="conStatus")
    private Integer conStatus;
    @Column(name="conCreateTime")
    private Date conCreateTime;
}
