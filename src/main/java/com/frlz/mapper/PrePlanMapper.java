package com.frlz.mapper;

import com.frlz.pojo.PrePlan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;

public interface PrePlanMapper {

    @Insert("insert into prePlan values(#{prePlanId},#{message},#{time},#{uid})")
    void insertIntoPrePlan(PrePlan prePlan);

    @Select("select * from prePlan where uid = #{uid} and time = #{time}")
    PrePlan selectPrePlanByUid(String uid, Date time);

    @Update("update prePlan set message = #{message} where uid = #{uid}")
    void updatePrePlan(PrePlan prePlan);
}
