package com.frlz.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Blog {
	
	private String blogId;//主键UUID
	private String message;//信息
	private Date time;//时间
	private int likes;//点赞数
	private int dislike;//踩数
	private int fansNumber;//粉丝数量
	private int commentsNumber;//评论数
	private int forwordNumber;//转发数
	private int readNumber;//阅读数
	private String title;//标题
	private User user;//用户类
	private Comments comments;//评论类
	private String uid;//用户uid主键
	private String summary;//用户摘要

	
	
}
