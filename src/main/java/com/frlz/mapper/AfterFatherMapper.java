package com.frlz.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

public interface AfterFatherMapper {


    @SelectKey(keyProperty = "afterFatherId",resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "afterFatherId", useGeneratedKeys = true)
    @Insert("insert into afterFather" +
            " values(  '123' ,now(), '4a9286e30a3e11e995e3e0d55ebbf96c')")
    void insertAfterFather(String uid);

    @Select("select afterFatherId from afterFather where uid = #{uid}")
    String selectAfterFatherId(String uid);
}
