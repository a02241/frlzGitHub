package com.frlz.pojo;

import lombok.Data;

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
	private String code;//激活码
	private String province;//省份
	private String city;//城市
	private Blog blog;

	public User(String username, String password, String uid, String phonenumber, String email, String icon, int investmentage, String profile, String profession, String residence, int privacy, int state, String code, String province, String city, Blog blog) {
		this.username = username;
		this.password = password;
		this.uid = uid;
		this.phonenumber = phonenumber;
		this.email = email;
		this.icon = icon;
		this.investmentage = investmentage;
		this.profile = profile;
		this.profession = profession;
		this.residence = residence;
		this.privacy = privacy;
		this.state = state;
		this.code = code;
		this.province = province;
		this.city = city;
		this.blog = blog;
	}

	public User() {
	}
}
