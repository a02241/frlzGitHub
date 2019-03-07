package com.frlz.pojo;

import lombok.Data;

/**
 * @program: frlz
 * @description: 记录交易时间
 * @author: cz
 * @date: 2019-03-07 15:16
 **/
@Data
public class TradeLog {
    private int id;//主键
    private Data tradeTime;//交易时间
    private String balanceId;//外键
    private int tradeQuantum;//量子交易
    private int tradeBlock;//方块交易
    private int tradeMagicCube;//魔方交易
}
