package com.frlz.mapper;

import com.frlz.pojo.LoginLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface LoginLogMapper {
    @Insert("insert into loginlog(uid,logintime) values(#{uid},now())")
    void insertLoginLog(String uid);

    @Select("select * from loginlog")
    List<LoginLog> selectAllLoginLog();

    @Select("select max(logintime) from loginlog where uid = #{uid}")
    Date selectLatestLoginLog(String uid);
}
