package com.frlz.mapper;

import com.frlz.pojo.Fans;
import com.frlz.utilPojo.UtilFans;
import org.apache.ibatis.annotations.*;

import javax.rmi.CORBA.Util;
import java.util.List;
/**
 * @author cz
 */
public interface FansMapper {

    @Insert("insert into fans values(default,#{uid},#{fansUId})")
    void insertFans(String uid,String fansUId);

    @Select("select * from fans where uid = #{uid}")
    List<Fans> selectFansForOne(String uid);

    @Select("select * from fans s LEFT JOIN user u ON s.fansUId = u.uid where s.uid = #{uid}")
    List<UtilFans> selectFansByUid(String uid);

    @Delete("delete from fans where fansUId = #{fansUId}")
    void deleteFans(String fansUId);
}
