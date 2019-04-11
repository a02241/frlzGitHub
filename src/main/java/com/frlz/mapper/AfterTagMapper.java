package com.frlz.mapper;

import com.frlz.pojo.AfterTag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AfterTagMapper {
    @Select("select * from afterTag where afterFatherId = #{afterFatherId}")
    List<AfterTag> selectAfterTagByAftherFatherId(String afterFatherId);

    @Select("select count(*) from afterTag where afterTagId = #{afterTagId}")
    int checkAfterTagByAfterTagId(String afterTagId);

    @Delete("delete from afterTag where afterTagId = #{afterTagId}")
    void deleteAfterTagByAfterTagId(String afterTagId);
}
