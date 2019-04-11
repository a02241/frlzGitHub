package com.frlz.mapper;

import com.frlz.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("select * from user")
    List<User> selectAll();

    @Select("select * from user where username = #{account} or phonenumber = #{account} or email = #{account} limit 1")
    User loginSelect(String account);

    @Select("select * from user u left join balance b on u.uid = b.uid where u.uid = #{uid} limit 1")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "phonenumber", column = "phonenumber"),
            @Result(property = "email", column = "email"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "investmentage", column = "investmentage"),
            @Result(property = "profile", column = "profile"),
            @Result(property = "profession", column = "profession"),
            @Result(property = "residence", column = "residence"),
            @Result(property = "privacy", column = "privacy"),
            @Result(property = "province", column = "province"),
            @Result(property = "registTime", column = "registTime"),
            @Result(property = "experience", column = "experience"),
            @Result(property = "signature", column = "signature"),
            @Result(property = "interestNumber", column = "interestNumber"),
            @Result(property = "fansNumber", column = "fansNumber"),
            @Result(property = "sex", column = "sex"),
            @Result(property = "birthday", column = "birthday"),
            @Result(property = "balance", column = "uId",
                    one = @One(select = "com.frlz.mapper.BalanceMapper.selectFromBalanceByUid"))
    })
    User selectByUid(String uid);

    @Select("select experience from user where uid = #{uid}")
    int selectExperienceByUid(String uid);

    @Select("select * from user where uid = #{uid}")
    User selectUserByUid(String uid);



    @Select("select count(*) from user where uid = #{uid} limit 1")
    int selectByUidIsture(String uid);

    @Select("select count(*) from user where phonenumber = #{phonenumber}")
    int checkPhonenumber(String phonenumber);

    @Select("select count(*) from user where email = #{email}")
    int checkEmail(String email);

    @Select("select username from user where uid = #{uid}")
    String searchUsernameById(String uid);

    @Select("select * from user where username = #{username}")
    User selectUserByUsername(String username);

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
            "signature," +
            "sex," +
            "birthday )" +
            " values( #{uid}, " +
            "#{phonenumber}, " +
            "#{username}, " +
            "#{password}, " +
            "#{email}, " +
            "#{icon}, " +
            "#{investmentage}, " +
            "'普通用户', " +
            "#{profession}, " +
            "#{residence}, " +
            "#{privacy}, " +
            "#{state}, " +
            "#{registTime}, " +
            "#{experience}, " +
            "#{fansNumber}," +
            "#{interestNumber},'快来添加你的个性签名吧！',0,#{birthday})")
    void registSave(User user);

    @Update("update user set icon = #{icon} where uid = #{uid}")
    void uploadUserIcon(String uid,String icon);

    @Update("update user set username = #{username}, " +
            "phonenumber = #{phonenumber}, " +
            "email =#{email}, " +
            "profession = #{profession}, " +
            "residence = #{residence}, " +
            "investmentage = #{investmentage} ," +
            "province = #{province} ," +
            "city = #{city} ," +
            "experience = #{experience} ," +
            "sex = #{sex}, " +
            "signature = #{signature}, " +
            "birthday = #{birthday}" +
            "where uid = #{uid}")
    void updateUser(User user);

    @Update("update user set experience = #{experience} where uid = #{uid}")
    void updateExperienceByUid(String uid,int experience);

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
    void updateSignature(String signature,String uid);

    @Update("update user set profile = #{profile} where uid = #{uid}")
    void updateProfile(String profile,String uid);
}
