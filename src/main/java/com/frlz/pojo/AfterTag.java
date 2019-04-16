package com.frlz.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @program: frlz
 * @description: 盘后信息
 * @author: cz
 * @date: 2019-04-11 09:57
 **/
@Data
public class AfterTag {
    private String afterTagId;//主键
    private String shares;//股票名
    private String changes;//涨跌幅
    private String highest;//纵坐标
    private String time;//标签时间
    private String message;//信息
    private String afterFatherId;//外键
    private Date insertTime;//插入时间
}
