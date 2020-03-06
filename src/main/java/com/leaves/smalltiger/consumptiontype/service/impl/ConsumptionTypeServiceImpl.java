package com.leaves.smalltiger.consumptiontype.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leaves.smalltiger.common.po.ConsumptionType;
import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.consumptiontype.mapper.ConsumptionTypeMapper;
import com.leaves.smalltiger.consumptiontype.service.ConsumptionTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ConsumptionTypeServiceImpl implements ConsumptionTypeService {

    @Autowired
    private ConsumptionTypeMapper consumptionTypeMapper;

    @Override
    public MsgResult updateConsumptiontype(ConsumptionType consumptionType) {
        int i = consumptionTypeMapper.updateByPrimaryKey(consumptionType);
        log.info("ConsumptionTypeServiceImpl --> updateConsumptionType:"+consumptionType);
        if (i>0){
            return new MsgResult(200,"修改成功",consumptionType);
        }else {
            return new MsgResult(201,"修改失败",null);
        }
    }

    @Override
    public MsgResult selectAllConsumptionType(String contName, int pageSize, int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<ConsumptionType> consumptionTypes = consumptionTypeMapper.selectAllConsumptionType(contName);
        PageInfo<ConsumptionType> consumptionTypePageInfo = new PageInfo<>(consumptionTypes);
        log.info("ConsumptionType --> selectAllConsumptionType :"+contName);
        MsgResult msgResult = new MsgResult();
        msgResult.setStatusCode(200);
        msgResult.setMsg("查询成功");
        msgResult.setData(consumptionTypePageInfo);
        return msgResult;
    }

    /**
     * 批量删除,传入ids
     * @param contIds
     * @return
     */
    @Override
    public MsgResult deleteConsumptionsByContId(int[] contIds) {
        log.info("ConsumptionTypeServiceImpl --> deleteConsumptionsByContId 要删除的类型id:"+contIds);
        for (int contId : contIds){
            consumptionTypeMapper.updateContStatus(contId);
        }
        MsgResult msgResult = new MsgResult();
        msgResult.setStatusCode(200);
        msgResult.setMsg("删除成功");
        msgResult.setData(null);
        return msgResult;
    }

    /**
     * 批量修复,传入ids
     * @param contIds
     * @return
     */
    @Override
    public MsgResult consumeConsumptionsByContId(int[] contIds) {
        log.info("ConsumptionTypeServiceImpl --> deleteConsumptionsByContId 要修复的类型id:"+contIds);
        for (int contId : contIds){
            consumptionTypeMapper.consumeContStatus(contId);
        }
        MsgResult msgResult = new MsgResult();
        msgResult.setStatusCode(200);
        msgResult.setMsg("修复成功");
        msgResult.setData(null);
        return msgResult;
    }

    /**
     * 新增一种类型
     * @param consumptionType
     * @return
     */
    @Override
    public MsgResult insertConsumptionType(ConsumptionType consumptionType) {
        int i = consumptionTypeMapper.insert(consumptionType);
        if (i>0){
            log.info("ConsumptionTypeServiceImpl --> insertConsumptionType"+consumptionType.toString());
            return new MsgResult(200,"新增成功",consumptionType);
        }else {
            return new MsgResult(201,"新增失败",null);
        }

    }
}
