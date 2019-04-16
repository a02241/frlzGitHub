package com.frlz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: frlz
 * @description: 盘后主表
 * @author: cz
 * @date: 2019-04-11 09:47
 **/
@Data
@ApiModel(value = "afterFather对象",description = "盘后观察对象afterFather")
public class AfterFather {
    @ApiModelProperty(value = "盘后观察识别码",name = "afterFatherId")
    private String afterFatherId;//主键
    @ApiModelProperty(value = "盘后观察发布时间",name = "time")
    private Date time;//创建时间
    @ApiModelProperty(value = "盘后观察发布人识别码",name = "uid")
    private String uid;//外键
}
