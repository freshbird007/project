package com.leaves.smalltiger.detail.mapper;

import com.leaves.smalltiger.common.config.BaseMapper;
import com.leaves.smalltiger.common.po.Detail;
import com.leaves.smalltiger.detail.vo.DataResult;
import com.leaves.smalltiger.detail.vo.DetailHome;
import com.leaves.smalltiger.detail.vo.DetailIndex;
import com.leaves.smalltiger.detail.vo.DetailMingXi;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface DetailMapper extends BaseMapper<Detail> {
    /**
     * 某年每个月的账单
     * @param dateYear
     * @param conId
     * @return
     */
    @Select("SELECT MONTH (DETTIME) AS modernMonth, SUM(CASE WHEN DETSORT=1 THEN DETAMOUNT ELSE NULL END) AS monthTotalIncome, " +
            "SUM( CASE WHEN DETSORT=2 THEN  DETAMOUNT ELSE NULL END ) AS monthTotalExpenditure FROM detail" +
            " WHERE YEAR (DETTIME) = #{DATEYEAR} AND CONID=#{CONID} AND detStatus = 1 GROUP BY MONTH (DETTIME) ")
    public List<DataResult> selectByPerMonthSums(@Param("DATEYEAR")int dateYear, @Param("CONID") int conId);

    /**
     * 某年某个月具体的账单
     * @param dateYear
     * @param dateMonth
     * @param conId
     * @return
     */
    @Select("SELECT MONTH (DETTIME) AS modernMonth, SUM(CASE WHEN DETSORT=1 THEN DETAMOUNT ELSE NULL END) AS monthTotalIncome, " +
            "SUM( CASE WHEN DETSORT=2 THEN  DETAMOUNT ELSE NULL END ) AS monthTotalExpenditure FROM detail" +
            " WHERE YEAR (DETTIME) = #{DATEYEAR} AND MONTH (DETTIME) = #{DATEMOUNTH} AND CONID=#{CONID}  AND detStatus = 1  GROUP BY MONTH (DETTIME) ")
    public DataResult selectByMonthSums(@Param("DATEYEAR")int dateYear, @Param("DATEMOUNTH")int dateMonth, @Param("CONID") int conId);

//    ===============================================================================================================

    /**
     * 根据收入/支出类型查消费明细
     * @param detSort
     * @return
     */
    @Select("SELECT * FROM detail WHERE detSort = #{detSort}")
    public List<Detail> selectDetailByDetSort(@Param("detSort") int detSort);

    /**
     * 根据年月查消费明细
     * @param year year(detTime)
     * @param month month(detTime)
     * @param conId
     * @return
     */
    @Select("SELECT detail.detAmount,detail.detRemark,detail.detTime,\n" +
            "detail.detSort,consumptiontype.contIcon,consumptiontype.contName \n" +
            "FROM detail,consumptiontype WHERE detail.contId = consumptiontype.contId\n" +
            "and detail.conId=#{conId} and YEAR(detTime)=#{year} and MONTH(detTime)=#{month} and detStatus = 1 ")
    public List<DetailIndex> selectDetailIndexByMonth(@Param("conId")int conId, @Param("year")int year, @Param("month")int month);

    /**
     * 根据detId删除一条明细(实际为改变该明细的状态)
     * @param detId
     */
    @Select("update detail set detStatus = 0 where detId = #{detId}")
    public void updateDetailById (@Param("detId")int detId);

    /**
     * 根据detId恢复一条明细
     */
    @Update("update detail set detStatus = 1 where detId = #{detId}")
    public void resumeDetailById (@Param("detId")int detId);

    /**
     * 查询所有状态为1的明细
     * @return Detail集合
     */
    @Select("SELECT * FROM detail WHERE detStatus=1")
    public List<Detail> selectDetails();

    //=========================================================================================
    /**
     * 通过用户id conId，年份year，月份month计算该用户当前月份的总支出和总收入
     * @param year
     * @param month
     * @param conId
     * @return
     */
    @Select("SELECT MONTH (detTime) AS modernMonth, SUM(CASE WHEN detSort=2 THEN detAmount ELSE null END) AS monthTotalExpenditure," +
            "SUM( CASE WHEN detSort=1 THEN  detAmount ELSE null END ) AS monthTotalIncome FROM detail " +
            "WHERE YEAR (detTime) = #{year} and conId=#{conId} and MONTH(detTime) = #{month} GROUP BY MONTH (detTime) DESC")
    public List<DataResult> queryDetails(@Param("year") int year,
                                         @Param("month") int month,
                                         @Param("conId") int conId);

    /**
     * 通过用户id conId，年份year，月份month查询当月流水账
     * @param year
     * @param month
     * @param conId
     * d.detRemark,d.detSort,d.detAmount,d.detTime,
     * @return
     */
    @Select("select c.conId, c.conName, d.detId, cpt.contIcon, d.detRemark, d.detSort, d.detAmount, d.detTime, cpt.contName " +
            " from consumer c, consumptiontype cpt, detail d where c.conId=d.conId and d.contId=cpt.contId and MONTH(detTime)=#{month}" +
            " AND YEAR(detTime)=#{year} AND c.conId=#{conId} AND d.detStatus=1")
    public List<DetailHome> queryHome(@Param("conId") int conId,
                                      @Param("year") int year,
                                      @Param("month") int month
                                     );

    /**
     * 查询东西
     * @return
     */
    @Select("SELECT c.conName , d.detId, d.conId , d.detSort ,d.contId,  " +
            "d.detRemark , d.detAmount , detStatus , d.detTime   FROM  consumer c , detail d " +
            "where c.conId = d.conId")
    public List<DetailMingXi> queryAllDetails();

    /**
     * 明细页面====前台
     * @param conId
     * @param year
     * @param month
     * @param day
     * @return
     */
    @Select("select d.*,cp.contIcon,cp.contName from detail d, consumptiontype cp where" +
            " d.contId=cp.contId and year(detTime)=#{year} and month(detTime)=#{month} and day(detTime)=#{day}" +
            " and d.conId=#{conId} ")
    public List<DetailHome> queryAllHome(@Param("conId") int conId,
                                         @Param("year") int year,
                                         @Param("month") int month,
                                         @Param("day")int day);

    /**
     * 前台页面具体数据
     * @param conId
     * @param year
     * @param month
     * @param day
     * @return
     */
    @Select("select d.*,cp.contIcon,cp.contName from detail d, consumptiontype cp where" +
            " d.contId=cp.contId and year(detTime)=#{year} and month(detTime)=#{month} " +
            " and day(detTime)=#{day} and d.conId=#{conId} ")
    public List<DetailHome> queryAllHomes(@Param("conId") int conId,
                                          @Param("year") int year,
                                          @Param("month") int month,
                                          @Param("day") int day);
}
