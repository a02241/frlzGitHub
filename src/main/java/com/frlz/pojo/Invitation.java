package com.frlz.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Invitation {
    private int invitationId;//邀请码表主键
    private String code;//邀请码
    private String uid;//uer表主键  兑换邀请码的用户id
    private int state;//状态
    private Date time;//兑换日期
    private String uidTwo;//使用邀请码的用户id
}
