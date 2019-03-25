package com.frlz.pojo;

import com.frlz.utilPojo.UtilReplys;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Comments {

	private String cId;//评论主键
	private String blogId;//外键
	private String comments;//信息
	private String username;//用户名
	private Date commentTime;//提交时间
	private List<UtilReplys> utilReplys;
}
