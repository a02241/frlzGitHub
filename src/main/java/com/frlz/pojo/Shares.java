package com.frlz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @author cz
 */
@Data
@ApiModel(value = "shares对象",description = "股票对象")
public class Shares {
	@ApiModelProperty(value = "股票代码",name = "code")
	private String code;
	@ApiModelProperty(value = "日期",name = "date")
	private String date;
	@ApiModelProperty(value = "开盘价",name = "open")
	private String open;
	@ApiModelProperty(value = "最高价",name = "high")
	private String high;
	@ApiModelProperty(value = "收盘价",name = "close")
	private String close;
	@ApiModelProperty(value = "最低价",name = "low")
	private String low;
}
