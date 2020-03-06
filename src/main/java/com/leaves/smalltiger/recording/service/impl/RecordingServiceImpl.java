package com.leaves.smalltiger.recording.service.impl;

import com.leaves.smalltiger.common.po.Detail;
import com.leaves.smalltiger.common.po.Recording;
import com.leaves.smalltiger.common.utils.MsgResult;
import com.leaves.smalltiger.detail.vo.DataResult;
import com.leaves.smalltiger.recording.vo.RecordingInfo;
import com.leaves.smalltiger.recording.mapper.RecordingMapper;
import com.leaves.smalltiger.recording.service.RecordingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Service
public class RecordingServiceImpl implements RecordingService {
    @Resource
    private RecordingMapper recordingMapper;
    @Override
    public MsgResult recordingInfoSelect(int conId) {
        RecordingInfo recordingInfo = new RecordingInfo();
        //连续打卡天数
        List<Recording> conRecordings = recordingMapper.selectOneByConId(conId);
        List<Recording> signInList = new ArrayList<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (Recording conRecording : conRecordings) {
            try {
                signInList.add(new Recording(dateFormat.format(conRecording.getRecTime())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int continuousSignInDay = getContinuousSignInDay(signInList);
        log.info("======连续签到日："+continuousSignInDay);
        List<Detail> details = recordingMapper.selectDetailsByConId(conId);
        //记账总天数
        log.info("最早的一笔账："+details.get(0).getDetTime());
        log.info("最后的一笔账："+details.get(details.size()-1).getDetTime());
        Date smallDay = details.get(0).getDetTime();
        Date largeDay = details.get(details.size()-1).getDetTime();
        int accountDays = distanceDay(largeDay, smallDay);
        log.info("======记账总天数："+accountDays);
        //记账总笔数
        int detailTotal = details.size();
        log.info("======记账总笔数："+detailTotal);
        recordingInfo.setContinuousSignInDay(continuousSignInDay);
        recordingInfo.setAccountDays(accountDays);
        recordingInfo.setDetailTotal(detailTotal);
        return new MsgResult(200,"查询成功",recordingInfo);
    }

    @Override
    public MsgResult dataSelect(int conId) {
        //获取当前年月日
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DATE);
        //当月总收入
        Double monthTotalIncome = recordingMapper.selectIncomeByConIdCurrentDate(conId, year, month);
        if (monthTotalIncome == null){
            monthTotalIncome = 0.0;
        }
//        保留两位小数
        BigDecimal ti = new BigDecimal(monthTotalIncome);
        Double monthTotalIncomeSum = ti.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        log.info(conId+"在"+year+"年"+month+"月的收入"+monthTotalIncomeSum);

        //当月总支出
        Double monthTotalExpenditure = recordingMapper.selectExpendByConIdCurrentDate(conId, year, month);
        if (monthTotalExpenditure == null){
            monthTotalExpenditure = 0.0;
        }
        BigDecimal te = new BigDecimal(monthTotalExpenditure);
        Double monthTotalExpenditureSum = te.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        log.info(conId+"在"+year+"年"+month+"月的支出"+monthTotalExpenditureSum);
        //当月结余
        Double monthSurplus = monthTotalIncome - monthTotalExpenditure;
        BigDecimal sm = new BigDecimal(monthSurplus);
        Double monthSurplu = sm.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        log.info(conId+"在"+year+"年"+month+"月的结余"+monthSurplu);

        DataResult dataResult = new DataResult(year,month,monthTotalIncomeSum,monthTotalExpenditureSum,monthSurplu);
        log.info(dataResult.toString()+"======***===");
        return new MsgResult(200,"查询成功",dataResult);
    }

    //连续打卡天数function
    private static int getContinuousSignInDay(List<Recording> recordings) {
        for (Recording recording : recordings) {
            log.info("遍历" + recording.toString());
        }
        //continuousDay 连续签到数
        int continuousDay = 1;
        boolean todaySignIn = false;
        Date today = new Date();
        for (int i = 0; i < recordings.size(); i++) {
            Recording recording = recordings.get(i);
            log.info("签到日期："+recording.getRecTime());
            int intervalDay = distanceDay(today, recording.getRecTime());
            //当天签到
            if (intervalDay == 0 && i == 0) {
                todaySignIn = true;
            }
            else if (intervalDay == continuousDay) {
                continuousDay++;
            }else {
                //不连续，终止判断
                break;
            }
        }
        if (!todaySignIn) {
            continuousDay--;
        }
        return continuousDay;
    }
    //两个日期对比间隔天数function
    private static int distanceDay(Date largeDay, Date smallDay) {
        log.info("大==="+largeDay);
        log.info("小==="+smallDay);
        int day = (int) ((largeDay.getTime() - smallDay.getTime()) / (1000 * 60 * 60 * 24));
        return day;
    }
}
