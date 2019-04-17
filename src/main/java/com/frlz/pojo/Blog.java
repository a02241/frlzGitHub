package com.frlz.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "blog对象",description = "博客对象blog")
public class Blog {
	@ApiModelProperty(value = "博客识别码",name = "blogId")
	private String blogId;//主键UUID
	@ApiModelProperty(value = "博客主体内容",name = "message")
	private String message;//信息
	@ApiModelProperty(value = "博客发布时间",name = "time")
	private Date time;//时间
	@ApiModelProperty(value = "博客点赞数",name = "likes")
	private int likes;//点赞数
	@ApiModelProperty(value = "博客热度",name = "weight")
	private double weight;//权重
	@ApiModelProperty(value = "博客评论数",name = "commentsNumber")
	private int commentsNumber;//评论数
	@ApiModelProperty(value = "博客转发数",name = "forwordNumber")
	private int forwordNumber;//转发数
	@ApiModelProperty(value = "博客阅读数",name = "readNumber")
	private int readNumber;//阅读数
	@ApiModelProperty(value = "博客标题",name = "title")
	private String title;//标题
	@ApiModelProperty(value = "博客发布人",name = "user")
	private User user;//用户类
	@ApiModelProperty(value = "博客评论",name = "comments")
	private Comments comments;//评论类
	@ApiModelProperty(value = "博客发布人识别码",name = "uid")
	private String uid;//用户uid主键
	@ApiModelProperty(value = "博客摘要",name = "summary")
	private String summary;//用户摘要
	@ApiModelProperty(value = "股票代码",name = "code")
	private String code;//股票代码
	
	
}
