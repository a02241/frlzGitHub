package com.frlz.util;


import java.util.Date;
import java.util.Random;

/**
 * @program: frlz
 * @description: 测试
 * @author: cz
 * @date: 2019-03-04 10:52
 **/

public class  test {
    public static void main(String[] args) {
        int rand = 0;
        int maxCount = 0;
        int minCount = 0;
        int count = 0;
        for (int i = 0 ; i <= 9 ; i++){
            maxCount=i;
            for (int j = 0 ; j <= 9 ; j++) {
                minCount=j;
                for (int g = 0 ; g <= 9 ; g++){
                    rand=g;
                    if (maxCount >= 8 && minCount <= 2 && rand > minCount && rand < maxCount) {
                        count++;
                        System.out.println(count + "~~~");
                    }
                }
            }
        }
        double result = count/ new Double(10*9*8/(3*2));
        System.out.println(result);
    }
}
