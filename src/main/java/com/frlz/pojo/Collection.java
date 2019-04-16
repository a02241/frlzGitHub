package com.frlz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel(value = "collection对象",description = "收藏对象")
public class Collection {
    @ApiModelProperty(value = "收藏识别码",name = "collectionId")
    private int collectionId;
    @ApiModelProperty(value = "外键",name = "uid")
    private String uid;
    @ApiModelProperty(value = "新闻地址",name = "newsAddress")
    private String newsAddress;
    @ApiModelProperty(value = "收藏时间",name = "collectTime")
    private Date collectTime;
}
