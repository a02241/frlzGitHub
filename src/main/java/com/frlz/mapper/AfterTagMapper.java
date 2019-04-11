package com.frlz.mapper;

import com.frlz.pojo.AfterTag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;

import com.frlz.pojo.AfterTag;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AfterTagMapper {
    @SelectKey(keyProperty = "afterTagId",resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "afterTagId", useGeneratedKeys = true)
    @Insert("insert into afterTag values(#{afterTagId},#{shares},#{changes},#{highest},#{time},#{message},#{afterFatherId})")
    void insertAfterTag(AfterTag afterTag);
    @Select("select * from afterTag where afterFatherId = #{afterFatherId}")
    List<AfterTag> selectAfterTagByAftherFatherId(String afterFatherId);
}
