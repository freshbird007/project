package com.leaves.smalltiger.consumptiontype.service;

import com.leaves.smalltiger.common.po.ConsumptionType;
import com.leaves.smalltiger.common.utils.MsgResult;

public interface ConsumptionTypeService {
    public MsgResult updateConsumptiontype(ConsumptionType consumptionType);

    /**
     *
     */
//    后台

    /**
     *  模糊搜索,集成分页,参数为类型名称
     * @param contName
     * @param pageSize
     * @param pageNum
     * @return
     */
    public MsgResult selectAllConsumptionType(String contName,int pageSize,int pageNum);

    /**
     * 批量删除
     * @param contIds
     * @return
     */
    public MsgResult deleteConsumptionsByContId(int[] contIds);

    /**
     * 批量修复
     * @param contIds
     * @return
     */
    public MsgResult consumeConsumptionsByContId(int[] contIds);

    /**
     * 新增一种明细类型
     * @param consumptionType
     * @return
     */
    public MsgResult insertConsumptionType(ConsumptionType consumptionType);
}
