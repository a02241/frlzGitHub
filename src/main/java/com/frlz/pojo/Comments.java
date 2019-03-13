package com.frlz.pojo;

import java.util.Date;

public class Comments {

	private String cId;//评论主键
	private String blogId;//外键
	private String comments;//信息
	private String username;//用户名
	private Date commentTime;//提交时间
	public String getcId() {
		return cId;
	}
	public void setcId(String cId) {
		this.cId = cId;
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	public Comments(String cId, String blogId, String comments, String username, Date commentTime) {
		super();
		this.cId = cId;
		this.blogId = blogId;
		this.comments = comments;
		this.username = username;
		this.commentTime = commentTime;
	}
	public Comments() {
		super();
	}
	@Override
	public String toString() {
		return "Comments [cId=" + cId + ", blogId=" + blogId + ", comments=" + comments + ", username=" + username
				+ ", commentTime=" + commentTime + "]";
	}
	
}
