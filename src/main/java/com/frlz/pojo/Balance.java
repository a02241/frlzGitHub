package com.frlz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: frlz
 * @description: 量子余额其方块余额
 * @author: cz
 * @date: 2019-03-07 11:23
 **/
@Data
@ApiModel(value = "balance对象",description = "账户对象")
public class Balance {
    @ApiModelProperty(value = "账户识别码",name = "balanceId")
    private String balanceId;//主键
    @ApiModelProperty(value = "方块余额",name = "blockBalance")
    private int blockBalance;//方块余额
    @ApiModelProperty(value = "量子余额",name = "quantumBalance")
    private int quantumBalance;//量子余额
    @ApiModelProperty(value = "魔方余额",name = "magicCubeBalance")
    private int magicCubeBalance;//魔方余额
    @ApiModelProperty(value = "用户表主键",name = "uid")
    private String uid;//用户表主键
}
