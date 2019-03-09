package com.frlz.mapper;

import com.frlz.pojo.Secret;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface SecretMapper {

    @Insert("insert into secret values(default,#{questionOne},#{answerOne},#{questionTwo},#{answerTwo},#{questionThree},#{answerThree},#{uid})")
    void insertSecret(Secret secret,String uid);

    @Select("select * from secret where uid = #{uid}")
    Secret selectFromSecret(String uid);
}
