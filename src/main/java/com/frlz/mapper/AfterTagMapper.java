package com.frlz.mapper;

import com.frlz.pojo.AfterTag;
import org.apache.ibatis.annotations.*;

import com.frlz.pojo.AfterTag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * @author cz
 */
public interface AfterTagMapper {
    @Select("select * from afterTag where afterFatherId = #{afterFatherId} order by insertTime")
    List<AfterTag> selectAfterTagByAftherFatherId(String afterFatherId);

    @Select("select count(*) from afterTag where afterTagId = #{afterTagId}")
    int checkAfterTagByAfterTagId(String afterTagId);

    @Delete("delete from afterTag where afterTagId = #{afterTagId}")
    void deleteAfterTagByAfterTagId(String afterTagId);
    @SelectKey(keyProperty = "afterTagId",resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "afterTagId", useGeneratedKeys = true)
    @Insert("insert into afterTag values(#{afterTagId},#{shares},#{changes},#{highest},#{time},#{message},#{afterFatherId},now())")
    void insertAfterTag(AfterTag afterTag);

    @Select("select * from afterTag where afterFatherId = #{afterFatherId} and shares = #{shares} and time = #{time}")
    AfterTag selectAfterTagByAfterTag(AfterTag afterTag);

    @Update("update afterTag set message = #{message} where afterTagId = #{afterTagId}")
    void updateAfterTag(String message,String afterTagId);
}
