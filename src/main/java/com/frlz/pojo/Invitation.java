package com.frlz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "invitation对象",description = "邀请码对象")
public class Invitation {
    @ApiModelProperty(value = "邀请码表主键",name = "invitationId")
    private int invitationId;//邀请码表主键
    @ApiModelProperty(value = "邀请码",name = "code")
    private String code;//邀请码
    @ApiModelProperty(value = "user表主键",name = "uid")
    private String uid;//user表主键  兑换邀请码的用户id
    @ApiModelProperty(value = "状态",name = "state")
    private int state;//状态
    @ApiModelProperty(value = "兑换日期",name = "time")
    private Date time;//兑换日期
    @ApiModelProperty(value = "使用邀请码的用户id",name = "uidTwo")
    private String uidTwo;//使用邀请码的用户id
}
