package com.frlz.mapper;

import com.frlz.pojo.Invitation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface InvitationMapper {

    @Select("select * from invitation where uid = #{uid}")
    List<Invitation> selectInvatationByUid(String uid);

    @Select("select * from invitation where code = #{code}")
    Invitation findStateBycode(String code);

    @Insert("insert into invitation values(default,#{code},null,1)")
    void insertInviteCode(String code);

    @Update("update invitation set state = 2 where code = #{code}")
    void updateInviteState(String code);
}
