package com.frlz.mapper;

import com.frlz.pojo.TradeLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TradeLogMapper {

    @Insert("insert into tradelog values(default,now(),#{balanceId},#{tradeQuantum},#{tradeBlock},#{tradeMagicCube},#{remarks})")
    void insertTradeLog(TradeLog tradeLog);

    @Select("select * from tradelog where balanceId = #{balanceId}")
    List<TradeLog> selectTradeLog(String balanceId);
}
