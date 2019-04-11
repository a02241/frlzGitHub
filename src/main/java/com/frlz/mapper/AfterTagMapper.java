package com.frlz.mapper;

import com.frlz.pojo.AfterTag;
import org.apache.ibatis.annotations.*;

import com.frlz.pojo.AfterTag;

import java.util.List;

public interface AfterTagMapper {
    @SelectKey(keyProperty = "afterTagId",resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "afterTagId", useGeneratedKeys = true)
    @Insert("insert into afterTag values(#{afterTagId},#{shares},#{changes},#{highest},#{time},#{message},#{afterFatherId})")
    void insertAfterTag(AfterTag afterTag);

    @Select("select * from afterTag where afterFatherId = #{afterFatherId} and shares = #{shares} and time = #{time}")
    AfterTag selectAfterTagByAfterTag(AfterTag afterTag);

    @Update("update afterTag set message = #{message} where afterTagId = #{afterTagId}")
    void updateAfterTag(String message,String afterTagId);
}
