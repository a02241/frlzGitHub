package com.frlz.mapper;

import com.frlz.pojo.Replys;
import com.frlz.utilPojo.UtilReplys;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;
/**
 * @author cz
 */
public interface ReplysMapper {

    @Select("select * from replys where cId = #{cId} order by contentTime limit 1,1")
    UtilReplys selectReplysByCId(String cId);

    @Select("select * from replys where cId = #{cId} order by contentTime")
    List<UtilReplys> selectReplysAllByCId(String cId);

    @SelectKey(keyProperty = "replyId",resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "replyId", useGeneratedKeys = true)
    @Insert("insert into replys(replyId, cId,content,username,contentTime)" +
            " values( #{replyId},#{cId}, #{content}, #{username}, #{contentTime})")
    void addReplys(Replys replys);
}
