package com.frlz.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class LoginLog {
    private int lid;//登录日志主键
    private String uid;//外键
    private Date logintime;//时间

}
