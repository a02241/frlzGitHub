package com.frlz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel(value="user对象",description="用户对象user")
public class User {
	@ApiModelProperty(value = "用户名",name = "username")
	private String username;//用户名
	@ApiModelProperty(value = "密码",name = "password",required = true)
	private String password;//密码
	@ApiModelProperty(value = "用户识别码",name = "uid")
	private String uid;//用户识别码
	@ApiModelProperty(value = "手机",name = "phonenumber")
	private String phonenumber;//手机
	@ApiModelProperty(value = "邮箱 ",name = "email")
	private String email;//邮箱
	@ApiModelProperty(value = "头像 ",name = "icon")
	private String icon;//头像
	@ApiModelProperty(value = "投资年龄 ",name = "investmentage")
	private int investmentage;//投资年龄
	@ApiModelProperty(value = "头衔 ",name = "profile")
	private String profile;//头衔
	@ApiModelProperty(value = "职业",name = "profession")
	private String profession;//职业
	@ApiModelProperty(value = "居住地",name = "residence")
	private String residence;//居住地
	@ApiModelProperty(value = "隐私识别码",name = "privacy")
	private int privacy;//隐私识别码
	@ApiModelProperty(value = "状态码",name = "state")
	private int state;//状态码
	@ApiModelProperty(value = "省份",name = "province")
	private String province;//省份
	@ApiModelProperty(value = "城市",name = "city")
	private String city;//城市
	@ApiModelProperty(value = "注册时间",name = "registTime")
	private Date registTime;//注册时间
	@ApiModelProperty(value = "等级",name = "experience")
	private int experience;//等级
	@ApiModelProperty(value = "粉丝数",name = "fansNumber")
	private int fansNumber;//粉丝数
	@ApiModelProperty(value = "关注人数",name = "interestNumber")
	private int interestNumber;//关注人数
	@ApiModelProperty(value = "个性签名",name = "signature")
	private String signature;//个性签名
	@ApiModelProperty(value = "性别",name = "sex")
	private int sex;//性别
	@ApiModelProperty(value = "生日",name = "birthday")
	private Date birthday;//生日
	@ApiModelProperty(value = "balance对象",name = "balance对象")
	private Balance balance;
	@ApiModelProperty(value = "blog对象",name = "blog对象")
	private Blog blog;
}
