package com.frlz.mapper;

import com.frlz.pojo.Balance;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BalanceMapper {

    @Insert("insert into balance values(#{balanceId},#{blockBalance},#{quantumBalance},#{magicCubeBalance},#{uid})")
    void insertBalance(Balance balance);

    @Select("select * from balance where uid = #{uid}")
    Balance selectFromBanlanceByUid(String uid);

    @Update("update balance set quantumBalance = #{quantumBalance} where uid = #{uid}")
    void updateQuantumBalanceByUid(String uid,int quantumBalance);

    @Update("update balance set blockBalance = #{blockBalance} where uid = #{uid}")
    void updateBlockBalanceByUid(String uid,int blockBalance);

    @Update("update balance set magicCubeBalance = #{magicCubeBalance} where uid = #{uid}")
    void updateMagicCubeBalanceByUid(String uid,int magicCubeBalance);

    @Select("select * from balance limit 30")
    List<Balance> selectAllBalance();
}
