package com.frlz.pojo;

import lombok.Data;

/**
 * @program: frlz
 * @description: 量子余额其方块余额
 * @author: cz
 * @date: 2019-03-07 11:23
 **/
@Data
public class Balance {
    private String balanceId;//主键
    private int blockBalance;//方块余额
    private int quantumBalance;//量子余额
    private int magicCubeBalance;//魔方余额
    private String uid;//用户表主键
}
