package com.frlz.service;

import com.frlz.utilPojo.UtilTradeLog;

import java.util.List;
/**
 * @author cz
 */
public interface TradeLogService {
    void insertTradeLog(String balanceId,int tradeQuantum,int tradeBlock,int tradeMagicCube,String remarks);

    void insertTradeLogQuantumToBlock(String balanceId,int quantum);

    void insertTradeLogBlockToQuantum(String balanceId,int block);

    void insertTradeLogMagicCubeToBlock(String balanceId,int magicCube);

    void insertTradeLogBlockToMagicCube(String balanceId,int block);

    List<UtilTradeLog> getTradeLogByUid(String uid);
}
