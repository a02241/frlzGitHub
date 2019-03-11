package com.frlz.mapper;

import com.frlz.pojo.TradeLog;
import org.apache.ibatis.annotations.Insert;

public interface TradeLogMapper {

    @Insert("insert into tradelog values(default,now(),#{balanceId},#{tradeQuantum},#{tradeBlock},#{tradeMagicCube},#{remarks})")
    void insertTradeLog(TradeLog tradeLog);
}
