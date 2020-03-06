package com.leaves.smalltiger.detail.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
/**
 * @author WUTONG
 * @date 19/12/07 上午 09:30
 */

public class DetailUpdate {
    private Integer detId;
    private Integer conId;
    private Integer detSort;
    private Integer contId;
    private Double detAmount;
    private String detRemark;
    private String detTime;
}
