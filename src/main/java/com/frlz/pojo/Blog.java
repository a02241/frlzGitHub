package com.frlz.pojo;

import java.util.Date;

public class Blog {
	
	private String blogId;//主键UUID
	private String message;//信息
	private Date time;//时间
	private int like;//点赞数
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
	public Blog(String blogId, String message, Date time, int like, int dislike, int fansNumber, int commentsNumber,
			int forwordNumber, int readNumber, String title, User user, Comments comments, String uid, String summary) {
		super();
		this.blogId = blogId;
		this.message = message;
		this.time = time;
		this.like = like;
		this.dislike = dislike;
		this.fansNumber = fansNumber;
		this.commentsNumber = commentsNumber;
		this.forwordNumber = forwordNumber;
		this.readNumber = readNumber;
		this.title = title;
		this.user = user;
		this.comments = comments;
		this.uid = uid;
		this.summary = summary;
	}
	public Blog() {
		super();
	}
	public String getBlogId() {
		return blogId;
	}
	public void setBlogId(String blogId) {
		this.blogId = blogId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getDislike() {
		return dislike;
	}
	public void setDislike(int dislike) {
		this.dislike = dislike;
	}
	public int getFansNumber() {
		return fansNumber;
	}
	public void setFansNumber(int fansNumber) {
		this.fansNumber = fansNumber;
	}
	public int getCommentsNumber() {
		return commentsNumber;
	}
	public void setCommentsNumber(int commentsNumber) {
		this.commentsNumber = commentsNumber;
	}
	public int getForwordNumber() {
		return forwordNumber;
	}
	public void setForwordNumber(int forwordNumber) {
		this.forwordNumber = forwordNumber;
	}
	public int getReadNumber() {
		return readNumber;
	}
	public void setReadNumber(int readNumber) {
		this.readNumber = readNumber;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Comments getComments() {
		return comments;
	}
	public void setComments(Comments comments) {
		this.comments = comments;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	@Override
	public String toString() {
		return "Blog [blogId=" + blogId + ", message=" + message + ", time=" + time + ", like=" + like + ", dislike="
				+ dislike + ", fansNumber=" + fansNumber + ", commentsNumber=" + commentsNumber + ", forwordNumber="
				+ forwordNumber + ", readNumber=" + readNumber + ", title=" + title + ", user=" + user + ", comments="
				+ comments + ", uid=" + uid + ", summary=" + summary + "]";
	}
	
	
}
