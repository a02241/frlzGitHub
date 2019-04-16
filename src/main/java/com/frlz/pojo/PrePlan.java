package com.frlz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: frlz
 * @description: 盘前计划
 * @author: cz
 * @date: 2019-03-11 15:06
 **/
@Data
@ApiModel(value = "prePlan对象",description = "盘前计划对象")
public class PrePlan {
    @ApiModelProperty(value = "盘前计划主键",name = "prePlanId")
    private String prePlanId;//盘前计划主键
    @ApiModelProperty(value = "信息内容",name = "message")
    private String message;//信息内容
    @ApiModelProperty(value = "时间",name = "time")
    private Date time;//时间
    @ApiModelProperty(value = "外键",name = "uid")
    private String uid;//外键
}
