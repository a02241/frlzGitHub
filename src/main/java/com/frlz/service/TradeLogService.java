package com.frlz.service;

import com.frlz.pojo.TradeLog;

public interface TradeLogService {
    void insertTradeLog(String balanceId,int tradeQuantum,int tradeBlock,int tradeMagicCube,String remarks) throws Exception;

    void insertTradeLogQuantumToBlock(String balanceId,int quantum) throws Exception;

    void insertTradeLogBlockToQuantum(String balanceId,int block) throws Exception;

    void insertTradeLogMagicCubeToBlock(String balanceId,int magicCube) throws Exception;

    void insertTradeLogBlockToMagicCube(String balanceId,int block) throws Exception;
}
