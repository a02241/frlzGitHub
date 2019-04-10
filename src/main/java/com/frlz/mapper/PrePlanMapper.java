package com.frlz.mapper;

import com.frlz.pojo.PrePlan;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface PrePlanMapper {

    @SelectKey(keyProperty = "prePlanId",resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "prePlanId", useGeneratedKeys = true)
    @Insert("insert into prePlan values(#{prePlanId},#{message},now(),#{uid})")
    void insertIntoPrePlan(PrePlan prePlan);

    @Select("select * from prePlan where uid = #{uid} and  DATEDIFF(time,#{time}) =0")
    PrePlan selectPrePlanByUid(String uid, String time);

    @Select("select count(*) from prePlan where prePlanId = #{prePlanId}")
    int checkPrePlan(String prePlanId);

    @Select("select count(*) from prePlan where uid = #{uid}")
    int checkPrePlanByUid(String uid);

    @Select("select time from prePlan where DATE_FORMAT(time, '%Y-%m') = #{time} and uid = #{uid}")
    List<Date> selectTimeByMonthUid(String time , String uid);

    @Update("update prePlan set message = #{message} where prePlanId = #{prePlanId}")
    void updatePrePlanMessage(String message,String prePlanId);

    @Delete("delete from prePlan where prePlanId = #{prePlanId}")
    void deletePrePlanByprePlanId(String prePlanId);
}
