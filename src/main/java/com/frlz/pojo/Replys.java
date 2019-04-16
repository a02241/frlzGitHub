package com.frlz.pojo;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: frlz
 * @description: 回复类
 * @author: cz
 * @date: 2019-03-04 10:44
 **/
@Data
@ApiModel(value = "replys对象",description = "回复评论对象")
public class Replys {
    @ApiModelProperty(value = "回复表主键uid",name = "replyId")
    private String replyId;//回复表主键uid
    @ApiModelProperty(value = "评论类主键",name = "cId")
    private String cId;//评论类主键
    @ApiModelProperty(value = "回复内容",name = "content")
    private String content;//回复内容
    @ApiModelProperty(value = "用户名",name = "username")
    private String username;//用户名
    @ApiModelProperty(value = "回复时间",name = "contentTime")
    private Date contentTime;//回复时间
}
