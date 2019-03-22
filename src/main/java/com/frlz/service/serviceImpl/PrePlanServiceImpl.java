package com.frlz.service.serviceImpl;

import com.frlz.mapper.PrePlanMapper;
import com.frlz.pojo.PrePlan;
import com.frlz.service.PrePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @program: frlz
 * @description: 盘前计划实现类
 * @author: cz
 * @date: 2019-03-11 15:08
 **/
@Service
public class PrePlanServiceImpl implements PrePlanService {

    private final PrePlanMapper prePlanMapper;

    @Autowired
    public PrePlanServiceImpl(PrePlanMapper prePlanMapper) {
        this.prePlanMapper = prePlanMapper;
    }

    @Override
    public void insertIntoPrePlan(PrePlan prePlan) {
        prePlanMapper.insertIntoPrePlan(prePlan);
    }

    @Override
    public PrePlan selectPrePlanByUid(String uid, Date time) {
        return prePlanMapper.selectPrePlanByUid(uid,time);
    }

}
