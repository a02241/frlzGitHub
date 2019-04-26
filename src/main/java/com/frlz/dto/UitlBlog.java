package com.frlz.dto;

import lombok.Data;

/**
 * @program: frlz
 * @description: 输出对象
 * @author: cz
 * @date: 2019-03-22 18:09
 **/
@Data
public class UitlBlog {
    private String blogId;//主键UUID
    private String message;//信息
    private int likes;//点赞数
    private int commentsNumber;//评论数
    private int forwordNumber;//转发数
    private int readNumber;//阅读数
    private String title;//标题
    private String summary;//用户摘要
    private String username;
    private String uid;
    private String icon;
    private String code;
}
