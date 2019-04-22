package com.frlz.mapper;

import com.frlz.pojo.Balance;
import com.frlz.dto.UtilBalance;
import org.apache.ibatis.annotations.*;

import java.util.List;
/**
 * @author cz
 */
public interface BalanceMapper {

    @SelectKey(keyProperty = "balanceId",resultType = String.class, before = true,
            statement = "select replace(uuid(), '-', '')")
    @Options(keyProperty = "balanceId", useGeneratedKeys = true)
    @Insert("insert into balance values(#{balanceId},#{blockBalance},#{quantumBalance},#{magicCubeBalance},#{uid})")
    void insertBalance(Balance balance);

    @Select("select * from balance where uid = #{uid}")
    Balance selectFromBalanceByUid(String uid);

    @Select("select * from balance where uid = #{uid}")
    UtilBalance selectShowBalanceByUid(String uid);

    @Update("update balance set quantumBalance = #{quantumBalance} where uid = #{uid}")
    void updateQuantumBalanceByUid(String uid,int quantumBalance);

    @Update("update balance set blockBalance = #{blockBalance} where uid = #{uid}")
    void updateBlockBalanceByUid(String uid,int blockBalance);

    @Update("update balance set magicCubeBalance = #{magicCubeBalance} where uid = #{uid}")
    void updateMagicCubeBalanceByUid(String uid,int magicCubeBalance);

    @Select("select * from balance limit 30")
    List<Balance> selectAllBalance();
}
