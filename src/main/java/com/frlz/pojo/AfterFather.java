package com.frlz.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @program: frlz
 * @description: 盘后主表
 * @author: cz
 * @date: 2019-04-11 09:47
 **/
@Data
public class AfterFather {
    private String afterFatherId;//主键
    private Date time;//创建时间
    private String uid;//外键
}
