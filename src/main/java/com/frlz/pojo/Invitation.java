package com.frlz.pojo;

import lombok.Data;

@Data
public class Invitation {
    private int invitationId;//邀请码表主键
    private String code;//邀请码
    private String uid;//uer表主键
    private int state;//状态
}
