package com.frlz.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @program: frlz
 * @description: 盘中标签信息
 * @author: cz
 * @date: 2019-03-11 15:17
 **/
@Data
public class ShorthandTag {
    private String shorthandTagId;//盘中速记标签主键
    private String shares;//股票信息
    private String changes;//价位涨幅信息
    private Date time;//时间
    private String message;//信息
    private String shorthandPhoteId;//外键
}
