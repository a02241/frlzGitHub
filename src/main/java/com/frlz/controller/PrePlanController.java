package com.frlz.controller;

import com.frlz.pojo.PrePlan;
import com.frlz.service.AfterFatherService;
import com.frlz.service.PrePlanService;
import com.frlz.util.DateTime;
import com.frlz.util.R;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;
import java.util.List;

/**
 * @program frlz
 * @description  盘前计划controller
 * @author cz
 * @date 2019-03-11 15:09
 **/
@RestControllerAdvice
@RestController
@Api(value="盘前controller",tags={"盘前信息操作接口"})
public class PrePlanController {

    private final PrePlanService prePlanService;
    private final AfterFatherService afterFatherService;

    @Autowired
    public PrePlanController(PrePlanService prePlanService, AfterFatherService afterFatherService){
        this.prePlanService = prePlanService;
        this.afterFatherService = afterFatherService;
    }

    
    /**
     * 根据用户uid和时间获取盘前计划
     * @title getPrePlan
     * @create by: cz
     * @description  必填参数uid,time,返回为该用户当前时间的信息
     * @create time: 2019/3/13 15:56
     * @param uid
     * @param time
     * 
     * @return com.frlz.pojo.PrePlan
     * @version V1.0
     */
    @PostMapping("/getPrePlan")
    @ApiOperation(value="根据用户uid和时间获取盘前计划", notes="根据url的信息来根据用户uid和时间获取盘前计划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "用户识别码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "time", value = "时间(yyyy-mm-dd)", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<PrePlan> getPrePlan(String uid, String time){
        return R.isOk().data(prePlanService.selectPrePlanByUid(uid,time));
    }

    
    /**
     * 添加盘前计划
     * @title addPrePlan
     * @create by: cz
     * @description  必填参数message，uid，成功返回success
     * @create time: 2019/3/13 15:58
     * @param prePlan
     * 
     * @return java.lang.String
     * @version V1.0
     */
    @PostMapping("/addPrePlan")
    @ApiOperation(value="添加盘前计划", notes="根据url的信息来添加盘前计划")
    @ApiImplicitParams({
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<String> addPrePlan(@ApiParam(name="盘前对象",value="message",required=true)PrePlan prePlan){
        if (prePlanService.selectPrePlanByUid(prePlan.getUid(),DateTime.getNowTimeToString()) == null){
            prePlanService.insertIntoPrePlan(prePlan);
            return R.isOk().msg("success");
        }else {
            return R.isFail().msg("今天已写盘前，请修改");
        }
    }


    /**
     *  更新盘前计划
     * @title updatePlan
     * @create by: cz
     * @description  必填参数message prePlanId
     * @create time: 2019/4/9 10:24
     * @param message
     * @param prePlanId
     * @return com.frlz.util.R<java.lang.String>
     * @version V1.0
     */
    @PostMapping("updatePlan")
    @ApiOperation(value="更新盘前计划", notes="根据url的信息来更新盘前计划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "message", value = "盘前信息", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "prePlanId", value = "盘前识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<String> updatePlan(String message, String prePlanId){
        if (prePlanService.checkPrePlan(prePlanId) != 0){
            prePlanService.updatePrePlanMessage(message,prePlanId);
            return R.isOk().msg("success");
        }else {
            return R.isFail().msg("参数错误");
        }
    }


    /**
     *  删除盘前计划
     * @title deletePrePlan
     * @create by: cz
     * @description  必填参数 prePlanId
     * @create time: 2019/4/9 10:27
     * @param prePlanId
     * @return com.frlz.util.R<java.lang.String>
     * @version V1.0
     */
    @PostMapping("deletePrePlan")
    @ApiOperation(value="删除盘前计划", notes="根据url的信息来删除盘前计划")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "prePlanId", value = "盘前识别码", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<String> deletePrePlan(String prePlanId){
        if (prePlanService.checkPrePlan(prePlanId) != 0){
            prePlanService.deletePrePlan(prePlanId);
            return R.isOk().msg("success");
        }else {
            return R.isFail().msg("参数错误");
        }
    }


    /**
     *  判断每月是否写盘前
     * @title selectTimeMonth
     * @create by: cz
     * @description  必填参数 uid time
     * @create time: 2019/4/10 14:56
     * @param uid
     * @param time
     * @return com.frlz.util.R<java.lang.String>
     * @version V1.0
     */
    @PostMapping("selectTimeMonth")
    @ApiOperation(value="判断每月是否写盘前", notes="根据url的信息来判断每月是否写盘前")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "盘前识别码", required = true, dataType = "String",paramType = "query"),
            @ApiImplicitParam(name = "time", value = "time(yyyy-mm)", required = true, dataType = "String",paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "请求参数没填好"),
            @ApiResponse(code = 404, message = "请求路径没有或页面跳转路径不对")
    })
    public R<String> selectTimeMonth(String uid,String time){
        if (prePlanService.checkPrePlanByUid(uid) != 0 || afterFatherService.checkAfterFatherByUid(uid) !=0 ){
            List<Date> dateList = prePlanService.selectTimeByMonthUid(time,uid);
            return R.isOk().data(dateList);
        }else {
            return R.isFail().msg("参数错误");
        }
    }
}
