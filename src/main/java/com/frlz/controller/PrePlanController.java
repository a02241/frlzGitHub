package com.frlz.controller;

import com.frlz.pojo.PrePlan;
import com.frlz.service.PrePlanService;
import com.frlz.util.DateTime;
import com.frlz.util.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * @program: frlz
 * @description: TODO 盘前计划controller
 * @author: cz
 * @date: 2019-03-11 15:09
 **/
@RestControllerAdvice
@RestController
public class PrePlanController {

    private final PrePlanService prePlanService;

    @Autowired
    public PrePlanController(PrePlanService prePlanService){
        this.prePlanService = prePlanService;
    }

    @PostMapping("/getPrePlan")
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

    public R<PrePlan> getPrePlan(String uid, String time){
        return R.isOk().data(prePlanService.selectPrePlanByUid(uid,time));
    }

    @PostMapping("/addPrePlan")
    /**
     * 添加盘前计划
     * @title addPrePlan
     * @create by: cz
     * @description: TODO 必填参数message，uid，成功返回success
     * @create time: 2019/3/13 15:58
     * @Param: prePlan
     * @throws
     * @return java.lang.String
     * @version V1.0
     */

    public R<String> addPrePlan(PrePlan prePlan){
        if (prePlanService.selectPrePlanByUid(prePlan.getUid(),DateTime.getNowTimeToString()) == null){
            prePlanService.insertIntoPrePlan(prePlan);
            return R.isOk().msg("success");
        }else {
            return R.isFail().msg("今天已写盘前，请修改");
        }
    }

    @PostMapping("updatePlan")
    /**
     * TODO 更新盘前计划
     * @title updatePlan
     * @create by: cz
     * @description: TODO 必填参数message prePlanId
     * @create time: 2019/4/9 10:24
     * @Param: message
 * @Param: prePlanId
     * @throws
     * @return com.frlz.util.R<java.lang.String>
     * @version V1.0
     */

    public R<String> updatePlan(String message, String prePlanId){
        if (prePlanService.checkPrePlan(prePlanId) != 0){
            prePlanService.updatePrePlanMessage(message,prePlanId);
            return R.isOk().msg("success");
        }else {
            return R.isFail().msg("参数错误");
        }
    }

    @PostMapping("deletePrePlan")
    /**
     * TODO 删除盘前计划
     * @title deletePrePlan
     * @create by: cz
     * @description: TODO 必填参数 prePlanId
     * @create time: 2019/4/9 10:27
     * @Param: message
     * @Param: prePlanId
     * @throws
     * @return com.frlz.util.R<java.lang.String>
     * @version V1.0
     */

    public R<String> deletePrePlan(String prePlanId){
        if (prePlanService.checkPrePlan(prePlanId) != 0){
            prePlanService.deletePrePlan(prePlanId);
            return R.isOk().msg("success");
        }else {
            return R.isFail().msg("参数错误");
        }
    }
}
