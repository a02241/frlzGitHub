package com.frlz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "ip对象",description = "IP地址对象")
public class TaobaoIPResult {
    @ApiModelProperty(value = "IP地址",name = "code")
    private int code;
    @ApiModelProperty(value = "国家区域",name = "country")
    private String country;
    @ApiModelProperty(value = "地区",name = "area")
    private String area;
    @ApiModelProperty(value = "地域",name = "region")
    private String region;
    @ApiModelProperty(value = "城市",name = "city")
    private String city;
    @ApiModelProperty(value = "国家",name = "county")
    private String county;
    @ApiModelProperty(value = "运营商",name = "isp")
    private String isp;
    @ApiModelProperty(value = "ip",name = "ip")
    private String ip;

}
