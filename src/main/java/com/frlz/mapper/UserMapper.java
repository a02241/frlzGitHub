package com.frlz.mapper;

import com.frlz.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("select * from user")
    List<User> selectAll();

    @Select("select * from user where username = #{account} or phonenumber = #{account} or email = #{account} limit 1")
    User loginSelect(String account);

    @Select("select * from user where uid = #{uid} limit 1")
    User selectByUid(String uid);

    @Select("select username from user where uid = #{uid} limit 1")
    String selectUsernameByUid(String uid);

    @Select("select count(*) from user where phonenumber = #{phonenumber}")
    int checkPhonenumber(String phonenumber);

    @Select("select count(*) from user where email = #{email}")
    int checkEmail(String email);

    @Select("select username from user where uid = #{uid}")
    String searchUsernameById(String uid);

    @Select("select * from user where username = #{username}")
    User selectUserByUsername(String username);

    @Select("select * from user where uid = #{uid}")
    User searchUserById(String uid);

    @Select("select username from user where email=#{email}")
    String searchUsernameByEmail(String email);

    @Select("select count(*) from user where username=#{username}")
    int checkUsername(String username);

    @Select("select password from user where uid=#{uid}")
    String checkPasswordByUId(String uid);

    @SelectKey(keyProperty = "uid",resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "uid", useGeneratedKeys = true)
    @Insert("insert into user(uid, " +
            "phonenumber," +
            "username," +
            "password," +
            "email," +
            "icon," +
            "investmentage," +
            "profile,profession, " +
            "residence," +
            "privacy," +
            "state," +
            "registTime," +
            "experience," +
            "fansNumber," +
            "interestNumber," +
            "signature )" +
            " values( #{uid}, " +
            "#{phonenumber}, " +
            "#{username}, " +
            "#{password}, " +
            "#{email}, " +
            "#{icon}, " +
            "#{investmentage}, " +
            "#'普通用户', " +
            "#{profession}, " +
            "#{residence}, " +
            "#{privacy}, " +
            "#{state}, " +
            "#{registTime}, " +
            "#{experience}, " +
            "#{fansNumber}," +
            "#{interestNumber},'快来添加你的个性签名吧！')")
    void registSave(User user);

    @Update("update user set icon = #{icon} where username = #{username}")
    void uploadUserIcon(String username,String icon);

    @Update("update user set username = #{username}, " +
            "phonenumber = #{phonenumber}, " +
            "email =#{email}, " +
            "profile = #{profile}, " +
            "profession = #{profession}, " +
            "residence = #{residence}, " +
            "investmentage = #{investmentage} ," +
            "province = #{province} ," +
            "city = #{city} ," +
            "experience = #{experience} " +
            "where uid = #{uid}")
    void updateUser(User user);

    @Update("update user set password = #{password} where uid = #{uid}")
    void updatePassword(User user);

    @Update("update user set phonenumber = #{phonenumber} where uid = #{uid}")
    void updatePhonenumber(String uid,String phonenumber);

    @Update("update user set email = #{email} where uid = #{uid}")
    void updateEmail(String uid,String email);

    @Update("update user set fansNumber = fansNumber + 1 where uid = #{uid}")
    void updateFansNumberAdd(String uid);

    @Update("update user set fansNumber = fansNumber - 1 where uid = #{uid}")
    void updateFansNumberReduce(String uid);

    @Update("update user set interestNumber = interestNumber + 1 where uid = #{uid}")
    void updateInterestNumberAdd(String uid);

    @Update("update user set interestNumber = interestNumber - 1 where uid = #{uid}")
    void updateInterestNumberReduce(String uid);

    @Delete("delete from user where uid = #{uid}")
    void deleteByUid(String uid);

    @Update("update user set signature = #{signature} where uid = #{uid}")
    void updateSignature(String signature,String user);
}
