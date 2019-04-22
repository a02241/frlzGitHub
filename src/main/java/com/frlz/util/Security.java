package com.frlz.util;
/**
 * @author cz
 */
public class Security {
    public static String encrypt(String code){
        char[] s = code.toCharArray();
        s[3] = '*';
        s[4] = '*';
        s[5] = '*';
        s[6] = '*';
        return new String(s);
    }
}
