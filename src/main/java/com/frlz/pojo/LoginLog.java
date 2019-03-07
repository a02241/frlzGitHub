package com.frlz.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class LoginLog {
    private int lid;
    private String uid;
    private Date logintime;

}
