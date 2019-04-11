package com.frlz.mapper;

import com.frlz.pojo.AfterTag;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AfterTagMapper {
    @Select("select * from afterTag where afterFatherId = #{afterFatherId}")
    List<AfterTag> selectAfterTagByAftherFatherId(String afterFatherId);
}
