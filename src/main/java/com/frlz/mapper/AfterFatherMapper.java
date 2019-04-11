package com.frlz.mapper;

import com.frlz.utilPojo.UtilAfterFather;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface AfterFatherMapper {
    @Select("select time from afterFather where DATE_FORMAT(time, '%Y-%m') = #{time} and uid = #{uid}")
    List<Date> selectTimeByMonthUid(String time , String uid);

    @Select("select count(*) from afterFather where uid = #{uid}")
    int checkAfterFatherByUid(String uid);

    @Select("select * from afterFather where uid = #{uid} and DATE_FORMAT(time, '%Y-%m-%d') = #{time}")
    @Results({
            @Result(property = "afterFatherId", column = "afterFatherId"),
            @Result(property = "afterTag", column = "afterFatherId",
                    many = @Many(select = "com.frlz.mapper.AfterTagMapper.selectAfterTagByAftherFatherId"))
    })
    List<UtilAfterFather> selectAfterByTimeUid(String uid , String time);
}
