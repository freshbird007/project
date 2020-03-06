package com.leaves.smalltiger.detail.service;

import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.detail.vo.*;
import org.apache.ibatis.annotations.Param;

public interface DetailService {

    /**
     * 查询某年每个月的数据
     * @param dateYear
     * @param conId
     * @return
     */
    public MsgResult selectByPerMonthSums(int dateYear, int conId);

    /**
     * 某年某个月具体的账单
     * @param dateYear
     * @param dateMonth
     * @param conId
     * @return
     */
    public MsgResult selectByMonthSums(int dateYear, int dateMonth, int conId);

//    ===============================================================================

    /**
     * 前后台都可以使用
     * @param detailInsert
     * @return
     */
    public MsgResult insertDetail(DetailInsert detailInsert);

    /**
     * 修改明细信息,前台使用，不能改变状态
     * @param detailUpdate
     * @return
     */
    public MsgResult updateDetail(DetailUpdate detailUpdate);

    /**
     *
     * @return
     */
    public MsgResult selectDetails();

    /**
     * 根据消费/支出类型查明细
     * @param detSort
     * @return
     */
    public MsgResult selectDetailsByDetSort(int detSort);

    /**
     * 按照月份查明细
     */
    public MsgResult selectDetailsByMonth(int conId,int year,int month);

    /**
     * @param detId
     * @return details
     * 根据id查询一条明细
     */
    public MsgResult selectDetailsByDetId(int detId);

    /**
     * 根据id删除一条数据
     */
    public MsgResult deleteDetailById(int detId);




    //后台使用的方法

    /**
     * 查所有消费明细
     * @return
     */
    public MsgResult selectAllDetail(int pageNum,int pageSize);

    /**
     * 根据id删除所有
     * @param detIds
     * @return
     */
    public MsgResult deleteDetailByIds(int[] detIds);

    /**
     * 根据id删除所有
     * @param detIds
     * @return
     */
    public MsgResult resumeDetailByIds(int[] detIds);

    /**
     *修改明细，后台使用，可以更改其状态
     * @param detailUpdateBack
     * @return
     */
    public MsgResult updateDetailBack(DetailUpdateBack detailUpdateBack);

// =============================================================================================

    //    查询明细页面的当月总收入和总支出
    public MsgResult querydetails(DetailParam detailParam);
    //    查询明细页面当月的流水账
    public MsgResult queryHome(DetailParam detailParam);

    public MsgResult queryAllHome(DetailParam detailParam);
}
