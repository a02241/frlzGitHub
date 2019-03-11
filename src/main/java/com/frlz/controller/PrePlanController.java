package com.frlz.controller;

import com.frlz.pojo.PrePlan;
import com.frlz.service.PrePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @program: frlz
 * @description: 盘前计划controller
 * @author: cz
 * @date: 2019-03-11 15:09
 **/
@RestController
public class PrePlanController {
    @Autowired
    private PrePlanService prePlanService;

    @RequestMapping("/getPrePlan")
    public PrePlan getPrePlan(String uid, Date time){
        return prePlanService.selectPrePlanByUid(uid,time);
    }

    @RequestMapping("/addPrePlan")
    public String addPrePlan(PrePlan prePlan){
        prePlanService.insertIntoPrePlan(prePlan);
        return "success";
    }

    @RequestMapping("/updatePrePlan")
    public String updatePrePlan(PrePlan prePlan){
        prePlanService.updatePrePlan(prePlan);
        return "success";
    }
}
