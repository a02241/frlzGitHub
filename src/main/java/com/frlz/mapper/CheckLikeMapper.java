package com.frlz.mapper;

import com.frlz.pojo.CheckLike;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface CheckLikeMapper {

    @Insert("insert into checkLike values(default,#{blogId},#{uid},#{state})")
    void insertIntoCheckLike(CheckLike checkLike);

    @Select("select * from checkLike where blogId = #{blogId} and uid = #{uid}")
    CheckLike selectFromCheckLike(String blogId,String uid);

    @Delete("delete from checkLike where likeId = #{likeId}")
    void deleteFromCheckLike(int likeId);
}
