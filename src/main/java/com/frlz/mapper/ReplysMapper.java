package com.frlz.mapper;

import com.frlz.pojo.Replys;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.SelectKey;

public interface ReplysMapper {

    @SelectKey(keyProperty = "replyId",resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "replyId", useGeneratedKeys = true)
    @Insert("insert into replys(replyId, cId,content,username,contentTime)" +
            " values( #{replyId},#{cId}, #{content}, #{username}, #{contentTime})")
    public void addReplys(Replys replys);
}
