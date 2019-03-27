package com.frlz.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class User {
	private String username;//用户名
	private String password;//密码
	@NotNull(message = "用户UID不能为空")
	private String uid;//用户识别码
	private String phonenumber;//手机 
	private String email;//邮箱 
	private String icon;//头像
	private int investmentage;//投资年龄 
	private String profile;//头衔
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
	private String signature;//个性签名
	private int sex;//性别
	private Date birthday;//生日
}
