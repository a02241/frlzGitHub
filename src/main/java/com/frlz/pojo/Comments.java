package com.frlz.pojo;

import com.frlz.utilPojo.UtilReplys;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel(value = "comments对象",description = "评论对象")
public class Comments {
	@ApiModelProperty(value = "评论主键",name = "cId")
	private String cId;//评论主键
	@ApiModelProperty(value = "外键",name = "blogId")
	private String blogId;//外键
	@ApiModelProperty(value = "信息",name = "comments")
	private String comments;//信息
	@ApiModelProperty(value = "用户名",name = "username")
	private String username;//用户名
	@ApiModelProperty(value = "提交时间",name = "commentTime")
	private Date commentTime;//提交时间
	@ApiModelProperty(value = "回复列表",name = "utilReplys")
	private List<UtilReplys> utilReplys;
}
