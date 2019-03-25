package com.frlz.mapper;

import com.frlz.pojo.Replys;
import com.frlz.utilPojo.UtilReplys;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

public interface ReplysMapper {

    @Select("select * from replys where cId = #{cId} order by contentTime desc limit 1,1")
    public List<UtilReplys> selectReplysByCId(String cId);

    @Select("select * from replys where cId = #{cId} order by contentTime")
    public List<UtilReplys> selectReplysAllByCId(String cId);

    @SelectKey(keyProperty = "replyId",resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "replyId", useGeneratedKeys = true)
    @Insert("insert into replys(replyId, cId,content,username,contentTime)" +
            " values( #{replyId},#{cId}, #{content}, #{username}, #{contentTime})")
    public void addReplys(Replys replys);
}
