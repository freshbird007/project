package com.leaves.smalltiger.consumptiontype.controller;

import com.leaves.smalltiger.common.po.ConsumptionType;
import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.consumptiontype.service.ConsumptionTypeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin
@ResponseBody
@RequestMapping(value = "/api")
public class ConsumptionTypeController {
    @Autowired
    private ConsumptionTypeService consumptionTypeService;

    /**
     *  修改账单类型
     */
    @RequestMapping(value = "/updateType",method = RequestMethod.PUT)
    public MsgResult updateConsumptionType(ConsumptionType consumptionType){
        MsgResult msgResult = consumptionTypeService.updateConsumptiontype(consumptionType);
        log.info("ConsuptionController --> update: "+consumptionType);
        return msgResult;
    }

//    后台使用

    /**
     * 模糊搜索,集成分页,参数为类型名
     * @param contName
     * @param pageSize
     * @param pageNum
     * @return
     */
    @RequestMapping(value = "/selectAllConsumptionType",method = RequestMethod.GET)
    public MsgResult selectAllConsumptionType(@RequestParam(value = "contName",defaultValue = "") String contName,
                                              @RequestParam(value = "pageSize",defaultValue = "10") int pageSize,
                                              @RequestParam(value = "pageNum",defaultValue = "1") int pageNum){
        MsgResult msgResult = consumptionTypeService.selectAllConsumptionType(contName, pageSize, pageNum);
        log.info("ConsumptionTypeController --> selectAllConsumptionType: contName"+contName);
        return msgResult;
    }

    /**
     * 批量删除
     * @param contIds
     * @return
     */
    @RequestMapping(value = "/deleteConsumptionsByContId",method = RequestMethod.PUT)
    public MsgResult deleteConsumptionsByContId(int[] contIds){
        log.info("ConsumptionTypeController --> deleteConsumptionsByContId 要删除的明细类型是: "+contIds);
        MsgResult msgResult = consumptionTypeService.deleteConsumptionsByContId(contIds);
        return msgResult;
    }

    /**
     * 批量修复
     * @param contIds
     * @return
     */
    @RequestMapping(value = "/consumeConsumptionsByContId",method = RequestMethod.PUT)
    public MsgResult consumeConsumptionsByContId(int[] contIds){
        log.info("ConsumptionTypeController --> consumeConsumptionsByContId 要修复的明细类型是: "+contIds);
        MsgResult msgResult = consumptionTypeService.consumeConsumptionsByContId(contIds);
        return msgResult;
    }

    /**
     * 新增一种明细类型
     */
    @RequestMapping(value = "/insertConsumptionType",method = RequestMethod.POST)
    public MsgResult insertConsumptionType(@RequestBody ConsumptionType consumptionType){
        log.info("ConsumptionTypeController --> insertConsumptionType 要新增的明细类型是: "+consumptionType.toString());
        MsgResult msgResult = consumptionTypeService.insertConsumptionType(consumptionType);
        return msgResult;
    }

}
