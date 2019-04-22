package com.frlz.utilPojo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author cz
 * @program frlz
 * @description 评论工具类
 * @date 2019-04-22 10:07
 **/
@Data
public class UtilComments {
    @ApiModelProperty(value = "评论主键",name = "cId")
    private String cId;//评论主键
    @ApiModelProperty(value = "外键",name = "blogId")
    private String blogId;//外键
    @ApiModelProperty(value = "信息",name = "comments")
    private String comments;//信息
    @ApiModelProperty(value = "用户名",name = "username")
    private String username;//用户名
    @ApiModelProperty(value = "用户识别码",name = "uid")
    private String uid;//用户识别码
    @ApiModelProperty(value = "用户头像",name = "icon")
    private String icon;//用户头像
    @ApiModelProperty(value = "提交时间",name = "commentTime")
    private Date commentTime;//提交时间
    @ApiModelProperty(value = "UtilReplys对象",name = "utilReplys")
    private List<UtilReplys> utilReplys;
}
