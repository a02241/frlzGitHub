package com.frlz.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @program: frlz
 * @description: 当前时间Date或String工具类
 * @author: cz
 * @date: 2019-03-14 15:41
 **/
public class DateTime {
    /**
     *
     * @title getNowTimeToString
     * @create by: cz
     * @description: TODO 获取格式为yyyy-MM-dd的当前时间字符串 返回格式为String
     * @create time: 2019/3/14 15:44
     * @Param: null
     * @throws
     * @return
     * @version V1.0
     */

    public static String getNowTimeToString(){
        Date date= new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String NowTime = sdf.format(date);
        return NowTime;
    }

    /**
     *
     * @title getBeforeTwoYearTimeToString
     * @create by: cz
     * @description: TODO 获取两年前的时间String类型
     * @create time: 2019/3/22 14:03
     * @Param: null
     * @throws
     * @return
     * @version V1.0
     */

    public static String getBeforeTwoYearTimeToString(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -3);
        Date twoYearDate = c.getTime();
        String beforeTwoYear = sdf.format(twoYearDate);
        return beforeTwoYear;
    }

    /**
     *
     * @title getDateToDate
     * @create by: cz
     * @description: TODO 把Date格式转换为yyyy-MM-dd格式，返回格式为Date
     * @create time: 2019/3/14 15:52
     * @Param: date
     * @throws
     * @return
     * @version V1.0
     */

    public static Date getDateToDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String SDate = sdf.format(date);
        Date NewDate = null;
        try {
            NewDate = sdf.parse(SDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return NewDate;
    }

    /**
     *
     * @title getTimeByDateToString
     * @create by: cz
     * @description: TODO 转换Date类型格式为yyyy-MM-dd格式的字符串，返回为String类型
     * @create time: 2019/3/14 15:47
     * @Param: date
     * @throws
     * @return
     * @version V1.0
     */

    public static String getTimeByDateToString(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String NowTime = sdf.format(date);
        return NowTime;
    }

    public static Date getDate(){
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String format = sdf.format(date);
        Date newDate = null;
        try {
            newDate = sdf.parse(format);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

    public static Date getDateByString(String dateString){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}
