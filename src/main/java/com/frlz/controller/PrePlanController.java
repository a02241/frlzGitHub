package com.frlz.controller;

import com.frlz.pojo.PrePlan;
import com.frlz.service.PrePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @program: frlz
 * @description: TODO 盘前计划controller
 * @author: cz
 * @date: 2019-03-11 15:09
 **/
@RestController
public class PrePlanController {
    @Autowired
    private PrePlanService prePlanService;

    @RequestMapping("/getPrePlan")
    /**
     * 根据用户uid和时间获取盘前计划
     * @title getPrePlan
     * @create by: cz
     * @description: TODO 必填参数uid,time,返回为该用户当前时间的信息
     * @create time: 2019/3/13 15:56
     * @Param: uid
     * @Param: time
     * @throws
     * @return com.frlz.pojo.PrePlan
     * @version V1.0
     */

    public PrePlan getPrePlan(String uid, Date time){
        return prePlanService.selectPrePlanByUid(uid,time);
    }

    @RequestMapping("/addPrePlan")
    /**
     * 添加盘前计划
     * @title addPrePlan
     * @create by: cz
     * @description: TODO 必填参数message，uid，time，成功返回success
     * @create time: 2019/3/13 15:58
     * @Param: prePlan
     * @throws
     * @return java.lang.String
     * @version V1.0
     */

    public String addPrePlan(PrePlan prePlan){
        prePlanService.insertIntoPrePlan(prePlan);
        return "success";
    }
}
