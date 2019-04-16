package com.frlz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: frlz
 * @description: 盘中标签信息
 * @author: cz
 * @date: 2019-03-11 15:17
 **/
@Data
@ApiModel(value = "shorthandTag对象",description = "盘中速记标签对象")
public class ShorthandTag {
    @ApiModelProperty(value = "盘中速记标签主键",name = "shorthandTagId")
    private String shorthandTagId;//盘中速记标签主键
    @ApiModelProperty(value = "股票信息",name = "shares")
    private String shares;//股票信息
    @ApiModelProperty(value = "价位涨幅信息",name = "changes")
    private String changes;//价位涨幅信息
    @ApiModelProperty(value = "时间",name = "time")
    private Date time;//时间
    @ApiModelProperty(value = "信息",name = "message")
    private String message;//信息
    @ApiModelProperty(value = "外键",name = "shorthandPhotoId")
    private String shorthandPhotoId;//外键
}
