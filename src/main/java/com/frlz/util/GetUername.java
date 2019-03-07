package com.frlz.util;

import java.util.Random;

/**
 * @program: frlz
 * @description: 随机生成用户名
 * @author: cz
 * @date: 2019-03-04 16:13
 **/
public class GetUername {
    /**
     * @title
     * @author cz
     * @date 2019/3/1 15:55
     * @param
     * @Description: 随机生成用户名
     * @return
     * @throws
     */

    public static String getUsername(){
        String val = "";
        Random random = new Random();
        for ( int i = 0; i < 9; i++ )
        {
            String str = random.nextInt( 2 ) % 2 == 0 ? "num" : "char";
            if ( "char".equalsIgnoreCase( str ) )
            { // 产生字母
                int nextInt = random.nextInt( 2 ) % 2 == 0 ? 65 : 97;
                // System.out.println(nextInt + "!!!!"); 1,0,1,1,1,0,0
                val += (char) ( nextInt + random.nextInt( 26 ) );
            }
            else if ( "num".equalsIgnoreCase( str ) )
            { // 产生数字
                val += String.valueOf( random.nextInt( 10 ) );
            }
        }
        return val;
    }
}
