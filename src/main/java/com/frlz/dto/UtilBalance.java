package com.frlz.dto;

import lombok.Data;

/**
 * @program: frlz
 * @description: 量子余额其方块余额
 * @author: cz
 * @date: 2019-03-07 11:23
 **/
@Data
public class UtilBalance {
    private int blockBalance;//方块余额
    private int quantumBalance;//量子余额
    private int magicCubeBalance;//魔方余额
}
