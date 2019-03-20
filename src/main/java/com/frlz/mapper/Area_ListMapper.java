package com.frlz.mapper;

import com.frlz.pojo.Area_List;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Area_ListMapper {

    @Select("select code from area_list where name = #{name}")
    String selectCode(String name);

    @Select("select code,name,area from area_list order by code")
    List<Area_List> selectAll();

    @Select("select * from area_list where name like concat('%',#{name},'%') or code  like  concat('%',#{name},'%') limit 5")
    List<Area_List> selectName(String name);

    @Select("select name from area_list where code =  #{realCode}")
    String selectRealName(String realcode);
}
