package com.frlz.util;

import com.frlz.pojo.Replys;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @program: frlz
 * @description: 测试
 * @author: cz
 * @date: 2019-03-04 10:52
 **/

public class  test {
    public static void main(String[] args) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(14[5,7])| (17[0,1,3,5-8]))\\d{8}$");
        Matcher m = p.matcher("1888888888");
        System.out.println(m.matches());
    }
}
