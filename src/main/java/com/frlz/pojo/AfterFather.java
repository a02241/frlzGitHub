package com.frlz.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @program: frlz
 * @description: 盘后主表
 * @author: cz
 * @date: 2019-04-11 09:47
 **/
public class AfterFather {
    private String afterFatherId;//主键
    private Date time;//创建时间
    private String uid;//外键

    public void setAfterFatherId(String afterFatherId) {
        this.afterFatherId = afterFatherId;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAfterFatherId() {
        return afterFatherId;
    }

    public Date getTime() {
        return time;
    }

    public String getUid() {
        return uid;
    }

    public AfterFather() {
    }

    public AfterFather(String afterFatherId, Date time, String uid) {
        this.afterFatherId = afterFatherId;
        this.time = time;
        this.uid = uid;
    }
}
