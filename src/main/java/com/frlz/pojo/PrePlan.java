package com.frlz.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @program: frlz
 * @description: 盘前计划
 * @author: cz
 * @date: 2019-03-11 15:06
 **/
@Data
public class PrePlan {
    private String prePlanId;//盘前计划主键
    private String message;//信息内容
    private Date time;//时间
    private String uid;//外键
}
