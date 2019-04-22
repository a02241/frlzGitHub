package com.frlz.mapper;

import com.frlz.pojo.Invitation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
/**
 * @author cz
 */
public interface InvitationMapper {

    @Select("select * from invitation where uid = #{uid}")
    List<Invitation> selectInvatationByUid(String uid);

    @Select("select * from invitation where code = #{code}")
    Invitation findStateBycode(String code);

    @Select("select * from invitation where state = 1 limit 1")
    Invitation selectOneInvitation();

    @Insert("insert into invitation values(default,#{code},null,1,null,null)")
    void insertInviteCode(String code);

    @Update("update invitation set state = 3,uidTwo = #{uid} where code = #{code}")
    void updateInviteState(String code,String uid);

    @Update("update invitation set uid = #{uid},time = now(),state = 2 where code = #{code}")
    void updateInviteUid(String uid,String code);


}
