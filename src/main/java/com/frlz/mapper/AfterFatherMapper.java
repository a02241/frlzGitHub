package com.frlz.mapper;

import com.frlz.pojo.AfterFather;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.Date;
import java.util.List;

public interface AfterFatherMapper {


    @SelectKey(keyProperty = "afterFatherId",resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "afterFatherId", useGeneratedKeys = true)
    @Insert("insert into afterFather" +
            " values(  #{afterFatherId} ,now(), #{uid})")
    void insertAfterFather(AfterFather afterFather);

    @Select("select afterFatherId from afterFather where uid = #{uid}")
    String selectAfterFatherId(String uid);

    @Select("select time from afterFather where DATE_FORMAT(time, '%Y-%m') = #{time} and uid = #{uid}")
    List<Date> selectTimeByMonthUid(String time , String uid);

    @Select("select count(*) from afterFather where uid = #{uid}")
    int checkAfterFatherByUid(String uid);
}
