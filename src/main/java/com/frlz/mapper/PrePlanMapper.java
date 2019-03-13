package com.frlz.mapper;

import com.frlz.pojo.PrePlan;
import org.apache.ibatis.annotations.*;

import java.util.Date;

public interface PrePlanMapper {

    @SelectKey(keyProperty = "prePlanId",resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "prePlanId", useGeneratedKeys = true)
    @Insert("insert into prePlan values(#{prePlanId},#{message},#{time},#{uid})")
    void insertIntoPrePlan(PrePlan prePlan);

    @Select("select * from prePlan where uid = #{uid} and time = #{time}")
    PrePlan selectPrePlanByUid(String uid, Date time);

}
