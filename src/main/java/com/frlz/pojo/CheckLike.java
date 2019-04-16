package com.frlz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "checkLike对象",description = "点赞对象")
public class CheckLike {
    @ApiModelProperty(value = "外键",name = "blogId")
    private String blogId;//外键
    @ApiModelProperty(value = "外键",name = "uid")
    private String uid;//外键
    @ApiModelProperty(value = "主键",name = "likeId")
    private int likeId;//主键
    @ApiModelProperty(value = "状态",name = "state")
    private boolean state;//状态
}
