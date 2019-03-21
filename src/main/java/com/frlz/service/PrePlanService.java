package com.frlz.service;

import com.frlz.pojo.PrePlan;

import java.util.Date;

public interface PrePlanService {

    void insertIntoPrePlan(PrePlan prePlan) throws Exception;

    PrePlan selectPrePlanByUid(String uid, Date time) throws Exception;

}
