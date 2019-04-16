package com.frlz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "fans对象",description = "粉丝对象")
public class Fans {
    @ApiModelProperty(value = "粉丝识别码",name = "fansId")
    private int fansId;//
    @ApiModelProperty(value = "被粉的人的uid",name = "uid")
    private String uid;//被粉的人的uid
    @ApiModelProperty(value = "粉丝的uid",name = "fansUId")
    private String fansUId;//粉丝的uid
}
