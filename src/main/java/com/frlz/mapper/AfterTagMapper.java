package com.frlz.mapper;

import com.frlz.pojo.AfterTag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;

public interface AfterTagMapper {
    @SelectKey(keyProperty = "afterTagId",resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "afterTagId", useGeneratedKeys = true)
    @Insert("insert into afterTag values(#{afterTagId},#{shares},#{changes},#{time},#{message},#{afterFatherId})")
    void insertAfterTag(AfterTag afterTag);
}
