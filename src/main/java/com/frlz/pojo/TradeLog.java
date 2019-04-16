package com.frlz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: frlz
 * @description: 记录交易时间
 * @author: cz
 * @date: 2019-03-07 15:16
 **/
@Data
@ApiModel(value = "tradeLog对象",description = "交易记录对象")
public class TradeLog {
    @ApiModelProperty(value = "主键",name = "id")
    private int id;//主键
    @ApiModelProperty(value = "交易时间",name = "tradeTime")
    private Date tradeTime;//交易时间
    @ApiModelProperty(value = "外键",name = "balanceId")
    private String balanceId;//外键
    @ApiModelProperty(value = "量子交易",name = "tradeQuantum")
    private int tradeQuantum;//量子交易
    @ApiModelProperty(value = "方块交易",name = "tradeBlock")
    private int tradeBlock;//方块交易
    @ApiModelProperty(value = "魔方交易",name = "tradeMagicCube")
    private int tradeMagicCube;//魔方交易
    @ApiModelProperty(value = "备注",name = "remarks")
    private String remarks;//备注
}
