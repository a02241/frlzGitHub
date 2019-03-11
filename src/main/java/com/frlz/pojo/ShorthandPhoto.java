package com.frlz.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @program: frlz
 * @description: 盘中图片位置
 * @author: cz
 * @date: 2019-03-11 15:11
 **/
@Data
public class ShorthandPhoto {
    private String shorthandPhotoId;//盘中图片位置主键
    private String shares;//股票信息
    private Date time;//时间
    private String uid;//外键
}
