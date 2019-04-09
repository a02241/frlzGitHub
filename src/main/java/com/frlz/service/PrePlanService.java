package com.frlz.service;

import com.frlz.pojo.PrePlan;

import java.util.Date;

public interface PrePlanService {

    void insertIntoPrePlan(PrePlan prePlan);

    PrePlan selectPrePlanByUid(String uid, String time);

    void updatePrePlanMessage(String message,String prePlanId);

    int checkPrePlan(String prePlanId);

    void deletePrePlan(String prePlanId);
}
