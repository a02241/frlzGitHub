package com.frlz.mapper;

import com.frlz.pojo.Session;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface SessionMapper {
    @Insert("insert into session values(default,#{sessionID})")
    void insertSession(String sessionID);

    @Delete("delete from session where sessionID = #{sessionID}")
    void deleteSession(String sessionID);

    @Select("select * from session where sessionID = #{sessionID} limit 1")
    Session selectSession(String sessionID);
}
