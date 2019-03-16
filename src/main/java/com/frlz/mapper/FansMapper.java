package com.frlz.mapper;

import com.frlz.pojo.Fans;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FansMapper {

    @Insert("insert into fans values(default,#{uid},#{fansUId})")
    void insertFans(String uid,String fansUId);

    @Select("select * from fans where uid = #{uid}")
    List<Fans> selectFansForOne(String uid);

    @Delete("delete from fans where fansUId = #{fansUId}")
    void deleteFans(String fansUId);
}
