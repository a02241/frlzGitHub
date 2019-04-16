package com.frlz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: frlz
 * @description: 盘中图片位置
 * @author: cz
 * @date: 2019-03-11 15:11
 **/
@Data
@ApiModel(value = "shorthandPhoto对象",description = "盘中速记对象")
public class ShorthandPhoto {
    @ApiModelProperty(value = "盘中图片位置主键",name = "shorthandPhotoId")
    private String shorthandPhotoId;//盘中图片位置主键
    @ApiModelProperty(value = "股票信息",name = "shares")
    private String shares;//股票信息
    @ApiModelProperty(value = "时间",name = "time")
    private Date time;//时间
    @ApiModelProperty(value = "外键",name = "uid")
    private String uid;//外键
}
