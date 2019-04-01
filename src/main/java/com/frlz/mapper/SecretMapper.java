package com.frlz.mapper;

import com.frlz.pojo.Secret;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface SecretMapper {

    @Insert("insert into secret values(default,#{questionOne},#{answerOne},#{questionTwo},#{answerTwo},#{questionThree},#{answerThree},#{uid})")
    void insertSecret(Secret secret);

    @Select("select * from secret where uid = #{uid}")
    Secret selectFromSecret(String uid);

    @Update("update secret set questionOne = #{questionOne},answerOne = #{answerOne},questionTwo = #{questionTwo},answerTwo = #{answerTwo},questionThree = #{questionThree},answerThree = #{answerThree} where uid = #{uid}")
    void updateSecret(Secret secret);

}
