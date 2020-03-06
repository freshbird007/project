package com.leaves.smalltiger.detail.service.impl;
import	java.lang.ref.Reference;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leaves.smalltiger.common.po.Detail;
import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.detail.mapper.DetailMapper;
import com.leaves.smalltiger.detail.service.DetailService;
import com.leaves.smalltiger.detail.vo.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class DetailServiceImpl implements DetailService {
    @Resource
    private DetailMapper detailMapper;

    /**
     * 查询某人某年某月的数据
     * @param dateYear
     * @param conId
     * @return
     */
    @Override
    public MsgResult selectByPerMonthSums(int dateYear, int conId) {
        log.info("IncomeServiceImpl查询detail中"+conId+"===" + dateYear + "年的每个月收支数据" );
        MsgResult msgResult = new MsgResult();
//            保留两位小数
        DecimalFormat df = new DecimalFormat(".00");
//       查询的结果
        List<DataResult> dataResults = detailMapper.selectByPerMonthSums(dateYear, conId);
//        判断查询的结果是否为空
        if (!dataResults.isEmpty()){//数据不为空时
            for (int i = 0; i < dataResults.size(); i++) {
//            dataResults.get(i).setModernMonth(i+1);
                dataResults.get(i).setModernYear(dateYear);
                if (dataResults.get(i).getMonthTotalExpenditure()==null){
                    dataResults.get(i).setMonthTotalExpenditure(0.0);
                }
                if (dataResults.get(i).getMonthTotalIncome()==null){
                    dataResults.get(i).setMonthTotalIncome(0.0);
                }

//            将计算的结果（String类型）转为double类型的
                double difference = Double.parseDouble(df.format(dataResults.get(i).getMonthTotalExpenditure()-dataResults.get(i).getMonthTotalIncome()));
                dataResults.get(i).setMonthSurplus(difference);
                log.info("用户"+conId+"在"+dateYear+"年"+(i+1)+"月的收入、支出、结余数据为："+dataResults.get(i));
            }
//            将dataResults反转
            Collections.reverse(dataResults);
//        将结果封装到返回类里面
            msgResult.setStatusCode(200);
            msgResult.setData(dataResults);
            msgResult.setMsg("查询成功");
            return msgResult;
        } else {//数据为空时
            msgResult.setStatusCode(200);
            msgResult.setData(dataResults);
            msgResult.setMsg("查询成功,暂无数据");
            return msgResult;
        }
    }
    /**
     * 某年某个月具体的账单
     * @param dateYear
     * @param dateMonth
     * @param conId
     * @return
     */
    @Override
    public MsgResult selectByMonthSums(int dateYear, int dateMonth, int conId) {
        log.info("IncomeServiceImpl中selectByPerMonthSums方法：查询detail中" + conId + "===" + dateYear + "年" + dateMonth + "月的收支数据");
        MsgResult msgResult = new MsgResult();
//            保留两位小数
        DecimalFormat df = new DecimalFormat(".00");
//       查询的结果
        DataResult dataResult = detailMapper.selectByMonthSums(dateYear, dateMonth, conId);
//        判断查询的结果是否为空
        if (dataResult != null) {//数据不为空时
            log.info("======dataResult不为空=====");
            dataResult.setModernYear(dateYear);
            if (dataResult.getMonthTotalExpenditure() == null) {
                dataResult.setMonthTotalExpenditure(0.0);
            }
            if (dataResult.getMonthTotalIncome() == null) {
                dataResult.setMonthTotalIncome(0.0);
            }
//            支出转换
            double totalExpenditure = Double.parseDouble(df.format(dataResult.getMonthTotalExpenditure()));
            dataResult.setMonthTotalExpenditure(totalExpenditure);
//            收入转换
            double totalIncome = Double.parseDouble(df.format(dataResult.getMonthTotalIncome()));
            dataResult.setMonthTotalIncome(totalIncome);
////            将计算的结果（String类型）转为double类型的
//            double difference = Double.parseDouble(df.format(dataResult.getMonthTotalExpenditure() - dataResult.getMonthTotalIncome()));
            double difference = totalExpenditure-totalIncome;
//            DecimalFormat dfs = new  DecimalFormat("#.00");
//            String format = dfs.format(difference);
            BigDecimal bd = new BigDecimal(difference);
            Double surplus = bd.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

            dataResult.setMonthSurplus(surplus);
            log.info("用户" + conId + "单单在" + dateYear + "年" + dateMonth + "月的收入、支出、结余数据为：" + dataResult.toString());
//         将结果封装到返回类里面
            msgResult.setStatusCode(200);
            msgResult.setData(dataResult);
            msgResult.setMsg("查询成功");
            return msgResult;
        }
        msgResult.setStatusCode(200);
        msgResult.setMsg("查询成功,暂无数据");
        return msgResult;
    }


//*********************************************************************************************************************


    /**
     * 新增明细(前后台都能使用)
     * @param detailInsert
     * @return
     */
    @Override
    public MsgResult insertDetail(DetailInsert detailInsert) {
        Detail detail = new Detail();
        log.info("新增记账"+detailInsert.toString());
        detail.setConId(detailInsert.getConId());
        detail.setDetSort(detailInsert.getDetSort());
        detail.setContId(detailInsert.getContId());
        detail.setDetAmount(detailInsert.getDetAmount());
        detail.setDetRemark(detailInsert.getDetRemark());

        //将detailInsert中String类型的detTime转换为date类型 并set进detail中
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition parsePosition = new ParsePosition(0);

        Date parse = simpleDateFormat.parse(detailInsert.getDetTime(), parsePosition);
        detail.setDetTime(parse);
        detail.setDetStatus(1);
        log.info("记账：DetailServiceImpl --> insertDetail:  "+detail.toString());
        int i = detailMapper.insertSelective(detail);
        if (i>0){
            return new MsgResult(200,"新增成功",detail);
        }
        return new MsgResult(201,"新增失败",null);
    }

    /**
     * 修改明细
     */
    @Override
    public MsgResult updateDetail(DetailUpdate detailUpdate) {
        Detail detail = new Detail();
        detail.setDetId(detailUpdate.getDetId());
        detail.setConId(detailUpdate.getContId());
        detail.setDetSort(detailUpdate.getDetSort());
        detail.setContId(detailUpdate.getContId());
        detail.setDetAmount(detailUpdate.getDetAmount());
        detail.setDetRemark(detailUpdate.getDetRemark());

        //将detailInsert中String类型的detTime转换为date类型 并set进detail中
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition parsePosition = new ParsePosition(0);
        Date date = format.parse(detailUpdate.getDetTime(), parsePosition);
        detail.setDetTime(date);

        detail.setDetStatus(1);
        int i = detailMapper.updateByPrimaryKey(detail);
        log.info("DetailServiceImpl --> UpdateDetail : "+detail.toString());
        if (i>0){
            return new MsgResult(200,"修改成功",detail);
        }else {
            return new MsgResult(201,"修改失败",null);
        }
    }


    /**
     * 查询所有明细(后台使用)
     * @return
     */
    @Override
    public MsgResult selectDetails() {
        List<Detail> details = detailMapper.selectAll();
        log.info("DetailServiceImpl --> selectDetails:"+details.toString());
        return new MsgResult(200,"查询完成",details);
    }

    /**
     * 按照消费/支出类型来查明细1收入2支出
     */
    @Override
    public MsgResult selectDetailsByDetSort(int detSort) {
        List<Detail> details = detailMapper.selectDetailByDetSort(detSort);
        log.info("DetailServiceImpl --> selectDetailsByDetSort: "+detSort);
        return new MsgResult(200,"查询完成",details);
    }

    /**
     * 根据年、月份、用户id查明细
     */
    @Override
    public MsgResult selectDetailsByMonth(int conId, int year,int month) {
        List<DetailIndex> detailIndices = detailMapper.selectDetailIndexByMonth(conId,year,month);
        log.info("DetailServiceImpl --> selectDetailsByDetMonth: "+detailIndices.toString());
        return new MsgResult(200,"查询完成",detailIndices);
    }

    /**
     * 根据detId查询一条明细(用于选择修改或者删除一条明细的页面)
     * @param detId
     * @return
     */
    @Override
    public MsgResult selectDetailsByDetId(int detId) {
        Detail detail = detailMapper.selectByPrimaryKey(detId);
        log.info("DetailServiceImpl --> selectDetailsByDetId:"+detId);
        return new MsgResult(200,"查询完成",detail);
    }

    @Override
    /**
     * 根据detId删除一条明细(将该条明细的状态改为0)
     */
    public MsgResult deleteDetailById(int detId) {
        detailMapper.updateDetailById(detId);
        return new MsgResult(200,"删除成功",null);
    }


    //以下后台使用的方法

    /**
     * 后台使用的全查，包含状态,包含分页
     * @return
     */
    @Override
    public MsgResult selectAllDetail(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<DetailMingXi> details = detailMapper.queryAllDetails();
        log.info("DetailServiceImpl --> selectAllDetail : "+details.toString());
        PageInfo<DetailMingXi> detailPageInfo = new PageInfo<>(details);
        return new MsgResult(200,"查询完成",detailPageInfo);
    }

    /**
     * 后台使用的批量删除，也可以单删
     * @param detIds
     * @return
     */
    @Transactional
    @Override
    public MsgResult deleteDetailByIds(int[] detIds) {
        for (int detId: detIds){
            if (detId > 0){
                detailMapper.updateDetailById(detId);
            }else {
                return new MsgResult(201,"删除失败",null);
            }

        }

        return new MsgResult(200,"删除成功",null);
    }

    /**
     * 后台使用的批量恢复，也可以单个恢复
     * @param detIds
     * @return
     */
    @Transactional
    @Override
    public MsgResult resumeDetailByIds(int[] detIds) {
        for (int detId: detIds){
            detailMapper.resumeDetailById(detId);
        }
        return new MsgResult(200,"恢复成功",null);
    }

    /**
     * 修改用户信息,要求所有参数
     * @param detailUpdateBack
     * @return
     */
    @Override
    public MsgResult updateDetailBack(DetailUpdateBack detailUpdateBack) {
        Detail detail = new Detail();
        detail.setDetId(detailUpdateBack.getDetId());
        detail.setConId(detailUpdateBack.getConId());
        detail.setDetSort(detailUpdateBack.getDetSort());
        detail.setContId(detailUpdateBack.getConId());
        detail.setDetAmount(detailUpdateBack.getDetAmount());
        detail.setDetRemark(detailUpdateBack.getDetRemark());
        detail.setDetStatus(detailUpdateBack.getDetStatus());

        //日期格式转化
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition parsePosition = new ParsePosition(0);
        Date date = format.parse(detailUpdateBack.getDetTime(), parsePosition);
        detail.setDetTime(date);

        int i = detailMapper.updateByPrimaryKey(detail);
        if (i>0){
            log.info("DetailServiceImpl --> updateDetailBack:" +detail.toString());
            return new MsgResult(200,"修改成功",detail);
        }else{
            return new MsgResult(201,"修改失败",null);
        }
    }

//=================================XIXI=============================================

    /**
     * 计算用户当月总收入和总支出
     * @param detailParam
     * @return
     */
    @Override
    public MsgResult querydetails(DetailParam detailParam) {
        MsgResult result = new MsgResult();
        log.info("传来的年份是:"+detailParam.getYear()+" 月份:"+detailParam.getMonth()+" 用户ID是："+detailParam.getConId());
        List<DataResult> details=detailMapper.queryDetails(detailParam.getYear(),detailParam.getMonth(),detailParam.getConId());
        if (!details.isEmpty()){
//            将查询到总和的数据保留小数位，整数保留一位，非整数保留两位小数
            for (int i=0;i<details.size();i++){
//                设置保留小数位格式
                DecimalFormat df = new DecimalFormat("0.00");
//                将当月总收入保留小数
                double income = Double.parseDouble(df.format(details.get(i).getMonthTotalIncome()));
//                将当月总支出保留两位小数
                double Expenditure = Double.parseDouble(df.format(details.get(i).getMonthTotalExpenditure()));
//                将当月总支出和总收入放入包装类中
                details.get(i).setMonthTotalIncome(income);
                details.get(i).setMonthTotalExpenditure(Expenditure);
//                打印值
                log.info("当月总收入"+income);
                log.info("当月总支出"+Expenditure);
//                将结果封装到返回前端类中
                result.setStatusCode(200);
                result.setData(details);
                result.setMsg("查询成功");
                return result;
            }
        }
//        查询失败
        result.setStatusCode(201);
        result.setMsg("查询失败");
        return result;
    }

    /**
     * 首页显示明细
     * @param detailParam
     * @return
     */
    @Override
    public MsgResult queryHome(DetailParam detailParam) {
//        打印传来的参数值
        log.info("传来的年份是:"+detailParam.getYear()+" 月份:"+detailParam.getMonth()+" 用户ID是："+detailParam.getConId());
        MsgResult result = new MsgResult();
        Map<Object,Object> map = new HashMap<>();
        List<Object> times = new ArrayList<>();
        times.add("2019-12-14");
        times.add("2019-12-15");
        times.add("2019-12-16");
//        把查询到的数据放到list里
        List<DetailHome> detailHomes = detailMapper.queryHome( detailParam.getConId(), detailParam.getYear(), detailParam.getMonth());
//        map.put("detailHomes",detailHomes);
//        map.put("times",times);

        for (DetailHome detailHome : detailHomes){
            map.put(detailHome.getDetTime(),detailHome);
        }
        for (DetailHome dataResult:detailHomes){
            log.info(dataResult+"打印查询结果");
        }
//        判断是否为空值
        if (!detailHomes.isEmpty()){
//            返回查询状态吗
            result.setStatusCode(200);
//            返回查询信息
            result.setMsg("查询成功");
//            将结果封装到包装类里返回到前端
            result.setData(map);
            return result;
        }
        result.setStatusCode(201);
        result.setMsg("查询失败");
        return result;
//        log.info(detailParam.getConId()+""+ detailParam.getYear()+detailParam.getMonth());
//        List<DetailHome> detailHomes = detailMapper.queryHome(detailParam.getConId(), detailParam.getYear(), detailParam.getMonth());
//        return new MsgResult(200,"xxx",detailHomes);
    }

    @Override
    public MsgResult queryAllHome(DetailParam detailParam) {
        MsgResult result = new MsgResult();
        List<DataTime> dataTimes = new ArrayList<>();
//        List<DetailHome> detailHomes = detailMapper.queryAllHome(detailParam.getConId(), detailParam.getYear(), detailParam.getMonth());
//        if (!detailHomes.isEmpty()){
//            for (DetailHome dataTime : detailHomes){
//                dataTimes.add(new DataTime(dataTime.getDetTime(),dataTime));
//                log.info("====="+new DataTime(dataTime.getDetTime(),dataTime).toString());
//            }
//            result.setStatusCode(200);
//            result.setData(dataTimes);
//            result.setMsg("查询成功");
//            return result;
//        }
        int year = detailParam.getYear();
        int month = detailParam.getMonth();
        for (int i=31; i>0; i--){
            List<DetailHome> detailHomes = detailMapper.queryAllHomes(detailParam.getConId(), year, month, i);
            for (DetailHome detailHome : detailHomes){
                log.info("*=*=/=/=/"+detailHome.toString());
            }
            if (!detailHomes.isEmpty()){
                log.info("==queryAllHome==if=="+i);
                dataTimes.add(new DataTime(year+"年"+month+"月"+i+"日",detailHomes));
//                log.info("======"+new DataTime(year+"年"+month+"月"+i+"日",detailHomes).toString());
                log.info("======"+i);
            }else {
                log.info("=queryAllHome==else==="+i);
                continue;
            }
        }
        result.setStatusCode(200);
        result.setData(dataTimes);
        result.setMsg("查询成功");
        return result;

//        result.setStatusCode(201);
//        result.setMsg("查询失败");
//        return result;

    }



}
