package com.frlz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: frlz
 * @description: 盘后信息
 * @author: cz
 * @date: 2019-04-11 09:57
 **/
@Data
@ApiModel(value = "afterTag对象",description = "盘后观察标签对象afterTag")
public class AfterTag {
    @ApiModelProperty(value = "盘后观察标签识别码",name = "afterTagId")
    private String afterTagId;//主键
    @ApiModelProperty(value = "股票名",name = "shares")
    private String shares;//股票名
    @ApiModelProperty(value = "涨跌幅",name = "changes")
    private String changes;//涨跌幅
    @ApiModelProperty(value = "纵坐标",name = "highest")
    private String highest;//纵坐标
    @ApiModelProperty(value = "标签时间",name = "time")
    private String time;//标签时间
    @ApiModelProperty(value = "信息",name = "message")
    private String message;//信息
    @ApiModelProperty(value = "外键",name = "afterFatherId")
    private String afterFatherId;//外键
    @ApiModelProperty(value = "插入时间",name = "insertTime")
    private Date insertTime;//插入时间
}
