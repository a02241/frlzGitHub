package com.frlz.utilPojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: frlz
 * @description: 返回回复信息pojo类
 * @author: cz
 * @date: 2019-03-23 15:21
 **/
@Data
@ApiModel(value = "UtilReplys对象",description = "回复工具对象")
public class UtilReplys {
    @ApiModelProperty(value = "评论类主键",name = "cId")
    private String cId;//评论类主键
    @ApiModelProperty(value = "回复内容",name = "content")
    private String content;//回复内容
    @ApiModelProperty(value = "用户名",name = "username")
    private String username;//用户名
    @ApiModelProperty(value = "回复时间",name = "contentTime")
    private Date contentTime;//回复时间
}
