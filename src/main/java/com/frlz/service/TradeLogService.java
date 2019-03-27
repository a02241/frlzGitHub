package com.frlz.service;

import com.frlz.pojo.TradeLog;

import java.util.List;

public interface TradeLogService {
    void insertTradeLog(String balanceId,int tradeQuantum,int tradeBlock,int tradeMagicCube,String remarks);

    void insertTradeLogQuantumToBlock(String balanceId,int quantum);

    void insertTradeLogBlockToQuantum(String balanceId,int block);

    void insertTradeLogMagicCubeToBlock(String balanceId,int magicCube);

    void insertTradeLogBlockToMagicCube(String balanceId,int block);

    List<TradeLog> getTradeLogByUid(String uid);
}
