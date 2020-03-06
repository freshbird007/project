package com.leaves.smalltiger.common.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "recording")
public class Recording {
    @Id
    @Column(name="recId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Integer recId;
    @Column(name="conId")
    private Integer conId;
    @Column(name="recTime")
    private Date recTime;

    public Recording(String day) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        recTime = sdf.parse(day);
    }
}
