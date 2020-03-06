package com.leaves.smalltiger.common.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author WUTONG
 * @date 19/12/07 上午 11:34
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "topic")
public class Topic {
    @Id
    @Column(name = "topicId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)//主键生成策略
    private Integer topicId;
    @Column(name = "topicWords")
    private String topicWords;
    @Column(name = "topicStatus")
    private Integer topicStatus;
}
