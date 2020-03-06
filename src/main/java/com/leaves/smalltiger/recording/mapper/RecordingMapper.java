package com.leaves.smalltiger.recording.mapper;

import com.leaves.smalltiger.common.config.BaseMapper;
import com.leaves.smalltiger.common.po.Detail;
import com.leaves.smalltiger.common.po.Recording;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RecordingMapper extends BaseMapper<Recording> {
    /**
     * 查询当前用户的打卡记录，并按时间排序
     */
    @Select("SELECT * FROM recording WHERE conId=#{conId} ORDER BY recTime DESC")
    public List<Recording> selectOneByConId(@Param("conId") int conId);

    /**
     * 查询当前用户的记账记录，并按时间排序
     */
    @Select("SELECT * FROM detail WHERE conId=#{conId} and detStatus = 1 ORDER BY detTime")
    public List<Detail> selectDetailsByConId(@Param("conId") int conId);

    /**
     * 查询当前用户当月的收入总和
     */
    @Select("SELECT SUM(detAmount) FROM detail WHERE conId=#{conId} AND detStatus = 1 AND detSort = 1" +
            " AND YEAR(detail.detTime) = #{year} and MONTH(detail.detTime) = #{month}")
    Double selectIncomeByConIdCurrentDate(@Param("conId") int conId, @Param("year") int year, @Param("month") int month);

    /**
     * 查询当前用户当月的支出总和
     */
    @Select("SELECT SUM(detAmount) FROM detail WHERE conId=#{conId} AND detStatus = 1 AND detSort = 2" +
            " AND YEAR(detail.detTime) = #{year} and MONTH(detail.detTime) = #{month}")
    Double selectExpendByConIdCurrentDate(@Param("conId") int conId, @Param("year") int year, @Param("month") int month);
}
