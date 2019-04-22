package com.frlz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
/**
 * @author cz
 */
@Data
@ApiModel(value = "session对象",description = "会话对象")
public class Session {
    @ApiModelProperty(value = "",name = "")
    private int id;
    @ApiModelProperty(value = "",name = "")
    private String sessionID;
}
