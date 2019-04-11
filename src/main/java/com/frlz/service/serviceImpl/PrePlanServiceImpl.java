package com.frlz.service.serviceImpl;

import com.frlz.mapper.AfterFatherMapper;
import com.frlz.mapper.PrePlanMapper;
import com.frlz.pojo.PrePlan;
import com.frlz.service.PrePlanService;
import com.frlz.util.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @program: frlz
 * @description: 盘前计划实现类
 * @author: cz
 * @date: 2019-03-11 15:08
 **/
@Service
public class PrePlanServiceImpl implements PrePlanService {

    private final PrePlanMapper prePlanMapper;
    private final AfterFatherMapper afterFatherMapper;

    @Autowired
    public PrePlanServiceImpl(PrePlanMapper prePlanMapper, AfterFatherMapper afterFatherMapper) {
        this.prePlanMapper = prePlanMapper;
        this.afterFatherMapper = afterFatherMapper;
    }

    @Override
    public void insertIntoPrePlan(PrePlan prePlan) {
        prePlanMapper.insertIntoPrePlan(prePlan);
    }

    @Override
    public PrePlan selectPrePlanByUid(String uid, String time) {
        System.out.println();
        return prePlanMapper.selectPrePlanByUid(uid,time);
    }

    @Override
    public void updatePrePlanMessage(String message, String prePlanId) {
        prePlanMapper.updatePrePlanMessage(message,prePlanId);
    }

    @Override
    public int checkPrePlan(String prePlanId) {
        return prePlanMapper.checkPrePlan(prePlanId);
    }

    @Override
    public int checkPrePlanByUid(String uid) {
        return prePlanMapper.checkPrePlanByUid(uid);
    }

    @Override
    public List<Date> selectTimeByMonthUid(String time, String uid) {
        List<Date> dateList = prePlanMapper.selectTimeByMonthUid(time,uid);
        List<Date> dateList2 = afterFatherMapper.selectTimeByMonthUid(time,uid);
        dateList.addAll(dateList2);
        Set<Date> set = new HashSet<Date>();
        for (Date date : dateList){
            set.add(DateTime.getTimeByDateToDate(date));
        }
        List<Date> newDateList = new ArrayList<Date>(set);
        return newDateList;
    }

    @Override
    public void deletePrePlan(String prePlanId) {
        prePlanMapper.deletePrePlanByprePlanId(prePlanId);
    }


}
