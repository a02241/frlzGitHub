package com.frlz.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
	private String username;//用户名
	private String password;//密码
	private String uid;//用户识别码
	private String phonenumber;//手机 
	private String email;//邮箱 
	private String icon;//头像
	private int investmentage;//投资年龄 
	private String profile;//个人简介 
	private String profession;//职业 
	private String residence;//居住地 
	private int privacy;//隐私识别码 
	private int state;//状态码 
	private String province;//省份
	private String city;//城市
	private Blog blog;
	private Date registTime;//注册时间
	private int experience;//等级
	private int fansNumber;//粉丝数
	private int interestNumber;//关注人数
}
