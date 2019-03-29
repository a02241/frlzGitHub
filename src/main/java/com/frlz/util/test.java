package com.frlz.util;


/**
 * @program: frlz
 * @description: 测试
 * @author: cz
 * @date: 2019-03-04 10:52
 **/

public class  test {
    public static void main(String[] args) {
        int a = 1000000;
        int sum = 0;
        int b = 6;
        int count = 6;
        for (int i = 0 ; i < 10000; i++){
            sum = sum + b;
            b ++;
            count++;
            if (sum==221430){
                System.out.println(count+"~~~~");
            }
        }
//        System.out.println(DateTime.getDateByString("1995-01-01"));
    }
}
