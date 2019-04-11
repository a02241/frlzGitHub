package com.frlz.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface AfterFatherMapper {
    @Select("select time from afterFather where DATE_FORMAT(time, '%Y-%m') = #{time} and uid = #{uid}")
    List<Date> selectTimeByMonthUid(String time , String uid);
}
