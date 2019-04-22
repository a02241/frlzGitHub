package com.frlz.service;

import com.frlz.pojo.PrePlan;

import java.util.Date;
import java.util.List;
/**
 * @author cz
 */
public interface PrePlanService {

    void insertIntoPrePlan(PrePlan prePlan);

    PrePlan selectPrePlanByUid(String uid, String time);

    void updatePrePlanMessage(String message,String prePlanId);

    int checkPrePlan(String prePlanId);

    int checkPrePlanByUid(String uid);

    List<Date> selectTimeByMonthUid(String time , String uid);

    void deletePrePlan(String prePlanId);
}
