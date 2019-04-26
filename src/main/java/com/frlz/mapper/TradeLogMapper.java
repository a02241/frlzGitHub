package com.frlz.mapper;

import com.frlz.pojo.TradeLog;
import com.frlz.dto.UtilTradeLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;
/**
 * @author cz
 */
public interface TradeLogMapper {

    @Insert("insert into tradelog values(default,now(),#{balanceId},#{tradeQuantum},#{tradeBlock},#{tradeMagicCube},#{remarks})")
    void insertTradeLog(TradeLog tradeLog);


    @Select("select tradeTime,balanceId,tradeQuantum,tradeBlock,tradeMagicCube,remarks from tradelog where balanceId = #{balanceId}")
    @Results({
            @Result(property = "tradeTime",column = "tradeTime"),
            @Result(property = "balanceId", column = "balanceId"),
            @Result(property = "tradeQuantum", column = "tradeQuantum"),
            @Result(property = "tradeBlock", column = "tradeBlock"),
            @Result(property = "tradeMagicCube", column = "tradeMagicCube"),
            @Result(property = "remarks", column = "remarks")
    })
    List<UtilTradeLog> selectTradeLog(String balanceId);
}
