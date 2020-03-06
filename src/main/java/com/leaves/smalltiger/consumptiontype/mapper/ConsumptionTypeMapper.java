package com.leaves.smalltiger.consumptiontype.mapper;

import com.leaves.smalltiger.common.config.BaseMapper;
import com.leaves.smalltiger.common.po.ConsumptionType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ConsumptionTypeMapper extends BaseMapper<ConsumptionType> {

    /**
     * 根据名称模糊查询
     * @param contName
     * @return
     */
    @Select("SELECT * FROM consumptiontype WHERE CONTNAME LIKE '%${contName}%'")
    public List<ConsumptionType> selectAllConsumptionType(@Param("contName")String contName);

    /**
     * 删除一条数据(改变状态为0)
     * @param contId
     */
    @Select("UPDATE consumptiontype SET CONTSTATUS = 0 WHERE CONTID = #{contId}")
    public void updateContStatus(@Param("contId") int contId);

    /**
     * 修复一条数据(改变状态为1)
     * @param contId
     */
    @Select("UPDATE consumptiontype SET CONTSTATUS = 1 WHERE CONTID = #{contId}")
    public void consumeContStatus(@Param("contId") int contId);
}
