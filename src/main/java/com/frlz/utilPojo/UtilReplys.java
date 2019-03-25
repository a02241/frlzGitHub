package com.frlz.utilPojo;

import lombok.Data;

import java.util.Date;

/**
 * @program: frlz
 * @description: 返回回复信息pojo类
 * @author: cz
 * @date: 2019-03-23 15:21
 **/
@Data
public class UtilReplys {
    private String cId;//评论类主键
    private String content;//回复内容
    private String username;//用户名
    private Date contentTime;//回复时间
}
