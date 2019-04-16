package com.frlz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "area_List对象",description = "股票对象")
public class Area_List {
	@ApiModelProperty(value = "股票代码",name = "code")
	private String code;//股票代码
	@ApiModelProperty(value = "股票名",name = "name")
	private String name;//股票名
}
