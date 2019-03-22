package com.frlz.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CollectionMapper {

    @Insert("insert into collection values(default,#{uid},#{blogId},now())")
    void insertIntoCollection(String uid,String blogId);

    @Select("select blogId from collection where uid = #{uid} order by collectTime desc")
    List<String> selectFromCollectionByUid(String uid);

    @Select("select uid from collection where blogId = #{blogId} order by collectTime")
    List<String> selectFromCollectionByBlogId(String blogId);
}
