package com.leaves.smalltiger.detail.controller;
import	java.awt.Desktop.Action;

import com.leaves.smalltiger.common.po.Detail;
import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.detail.service.DetailService;
import com.leaves.smalltiger.detail.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@ResponseBody
@RequestMapping("/api")
public class DetailController {
    @Autowired
    private DetailService detailService;

    /**
     * 查询某人某年的数据
     * @param yearMonthData
     * @return
     */
    @RequestMapping(value = "/detailPermonth", method = RequestMethod.GET)
    public MsgResult selectByPerMonthSums(YearData yearMonthData){
        log.info("某年的账单：yearMonthData===="+yearMonthData.toString());
        MsgResult result = detailService.selectByPerMonthSums(yearMonthData.getDateYear(), yearMonthData.getConId());
        return result;
    }

    /**
     * 查询某人某年某月的数据
     * @param yearMonthData
     * @return
     */
    @RequestMapping(value = "/detailMonthSums", method = RequestMethod.GET)
    public MsgResult selectByMonthSums(@RequestBody YearMonthData yearMonthData){
        log.info("某年具体某个月的账单：yearMonthData===="+yearMonthData.toString());
        MsgResult result = detailService.selectByMonthSums(yearMonthData.getDateYear(), yearMonthData.getDateMonth(), yearMonthData.getConId());
        return result;
    }



//    =====================================================================================================



    /**
     * 明细新增
     */
    @RequestMapping(value = "/insertDetail",method = RequestMethod.POST)
    public MsgResult insertDetail(@RequestBody DetailInsert detailInsert){
        log.info("DetailController --> insertDetail:   "+detailInsert.toString());
        MsgResult msgResult = detailService.insertDetail(detailInsert);
        return msgResult;
    }

    /**
     * 修改明细
     */
    @RequestMapping(value = "/updateDetail",method = RequestMethod.PUT)
    public MsgResult updateDetail(@RequestBody DetailUpdate detailUpdate){
        log.info("DetailController-->UpdateDetail: "+detailUpdate.toString());
        MsgResult msgResult = detailService.updateDetail(detailUpdate);
        return msgResult;
    }

    /**
     * 查询所有明细
     */
    @RequestMapping(value = "/selectDetails",method = RequestMethod.GET)
    public MsgResult selectDetails(){
        MsgResult msgResult = detailService.selectDetails();
        log.info("DetailController --> selectDetails");
        return msgResult;
    }

    /**
     * 根据类型查明细
     */
    @RequestMapping(value = "/selectDetailsByDetSort",method = RequestMethod.GET)
    public MsgResult selectDetailsByDetSort(@RequestParam(value = "detSort") int detSort){
        MsgResult msgResult = detailService.selectDetailsByDetSort(detSort);
        log.info("DetailController --> selectDetailsByDetSort"+detSort);
        return msgResult;
    }

    /**
     * 根据月份查明细
     */
//    @RequestMapping(value = "/selectDetailsByMonth",method = RequestMethod.GET)
//    public MsgResult selectDetailsByMonth(@RequestParam(value = "conId") int conId,@RequestParam(value = "month")int month,@RequestParam(value = "year")int year){
//        MsgResult msgResult = detailService.selectDetailsByMonth(conId,year, month);
//        log.info("DetailController --> selectDetailsByMonth:"+year+" "+month+" "+conId);
//        return msgResult;
//    }

    /**
     * 根据id查一条明细,要改，不能查0
     */
    @RequestMapping(value = "/selectDetailByDetId",method = RequestMethod.GET)
    public MsgResult selectDetailByDetId(@RequestParam(value = "detId") int detId){
        MsgResult msgResult = detailService.selectDetailsByDetId(detId);
        log.info("DetailController --> selectDetailsByMonth"+detId);
        return msgResult;
    }

    /**
     * 根据id删除一条明细
     */
    @RequestMapping(value = "/deleteDetailByDetId",method = RequestMethod.PUT)
    public MsgResult deleteDetailByDetId(@RequestParam(value = "detId") int detId){
        MsgResult msgResult = detailService.deleteDetailById(detId);
        log.info("DetailController --> selectDetailsByDetId"+detId);
        return msgResult;
    }

//========================================后台使用的接口=========================================


    /**
     * 后台使用的全查，包含状态
     */
    @RequestMapping(value = "selectAllDetail",method = RequestMethod.GET)
    public MsgResult selectAllDetail(@RequestParam(value = "pageNum",defaultValue = "1") int pageNum,
                                     @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        MsgResult msgResult = detailService.selectAllDetail(pageNum,pageSize);
        log.info("DetailController --> selectAllDetail");
        return msgResult;
    }

    /**
     * 后台使用的批量删除，也可以单个删除
     * @param detIds
     * @return
     */
    @RequestMapping(value = "deleteDetailByIds",method = RequestMethod.PUT)
    public MsgResult deleteDetailByIds(@Param("detIds") int[] detIds){
        MsgResult msgResult = detailService.deleteDetailByIds(detIds);
        log.info("DetailController --> deleteDetailByIds");
        return msgResult;
    }

    /**
     * 后台使用的批量恢复，也可以单个恢复
     * @param detIds
     * @return
     */
    @RequestMapping(value = "resumeDetailByIds",method = RequestMethod.PUT)
    public MsgResult resumeDetailByIds(@RequestParam("detIds") int[] detIds){
        MsgResult msgResult = detailService.resumeDetailByIds(detIds);
        log.info("DetailController --> resumeDetailByIds");
        return msgResult;
    }

    /**
     * 修改一条明细，参数为全字段
     */
    @RequestMapping(value = "updateDetailBack",method = RequestMethod.PUT)
    public MsgResult updateDetailBack(@RequestBody DetailUpdateBack detailUpdateBack){
        log.info("DetailController --> updateDetailBack");
        MsgResult msgResult = detailService.updateDetailBack(detailUpdateBack);
        return msgResult;
    }

//    ====================================XIXI===============================================

    /**
     *根据年份、月份和yonghuId查询该用户当月的总收入和总支出
     * @param detailParam
     * @return
     */
    @RequestMapping(value = "/queryDetails",method = RequestMethod.GET)
    public MsgResult query(@RequestBody DetailParam detailParam){
        log.info("前台传来的参数: 用户ID"+detailParam.getConId()+"  年份： "+detailParam.getYear()+"  月份："+detailParam.getMonth());
        MsgResult msgResult = detailService.querydetails(detailParam);
        return msgResult;
    }

    /**
     * 查询并返回明细
     * @param detailParam
     * @return
     */
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public MsgResult queryHome(DetailParam detailParam){
        log.info("前台传来的参数: 用户ID"+detailParam.getConId()+"  年份： "+detailParam.getYear()+"  月份："+detailParam.getMonth());
        MsgResult msgResult = detailService.queryHome(detailParam);
        return msgResult;
    }

    /**
     * 前台=====明细页面
     * @param detailParam
     * @return
     */
    @RequestMapping(value = "/homes",method = RequestMethod.GET)
    public MsgResult queryHomes(DetailParam detailParam){
        log.info("前台首页明细页面："+detailParam.toString());
        MsgResult result = detailService.queryAllHome(detailParam);
        return result;
    }
}
