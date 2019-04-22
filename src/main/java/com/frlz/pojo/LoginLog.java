package com.frlz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
/**
 * @author cz
 */
@Data
@ApiModel(value = "loginLog对象",description = "登录日志对象")
public class LoginLog {
    @ApiModelProperty(value = "登录日志主键",name = "lid")
    private int lid;//登录日志主键
    @ApiModelProperty(value = "外键",name = "uid")
    private String uid;//外键
    @ApiModelProperty(value = "时间",name = "logintime")
    private Date logintime;//时间

}
