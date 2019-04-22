package com.frlz.service.impl;

import com.frlz.mapper.BalanceMapper;
import com.frlz.mapper.TradeLogMapper;
import com.frlz.pojo.TradeLog;
import com.frlz.service.TradeLogService;
import com.frlz.dto.UtilTradeLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: frlz
 * @description: 交易日志实现类
 * @author: cz
 * @date: 2019-03-07 15:41
 **/
@Transactional
@Service
public class TradeLogServiceImpl implements TradeLogService {

    private final TradeLogMapper tradeLogMapper;
    private final BalanceMapper balanceMapper;
    @Autowired
    public TradeLogServiceImpl(TradeLogMapper tradeLogMapper,BalanceMapper balanceMapper) {
        this.tradeLogMapper = tradeLogMapper;
        this.balanceMapper = balanceMapper;
    }

    @Override
    public void insertTradeLog(String balanceId,int tradeQuantum,int tradeBlock,int tradeMagicCube,String remarks){
        TradeLog tradeLog = new TradeLog();
        tradeLog.setBalanceId(balanceId);
        tradeLog.setTradeQuantum(tradeQuantum);
        tradeLog.setTradeBlock(tradeBlock);
        tradeLog.setTradeMagicCube(tradeMagicCube);
        tradeLog.setRemarks(remarks);
        tradeLogMapper.insertTradeLog(tradeLog);
    }

    @Override
    public void insertTradeLogQuantumToBlock(String balanceId,int quantum){
        TradeLog tradeLog = new TradeLog();
        tradeLog.setBalanceId(balanceId);
        tradeLog.setTradeQuantum(-quantum);
        tradeLog.setTradeBlock(quantum/10);
        tradeLog.setRemarks("量子兑换方块");
        tradeLogMapper.insertTradeLog(tradeLog);
    }

    @Override
    public void insertTradeLogBlockToQuantum(String balanceId, int block) {
        TradeLog tradeLog = new TradeLog();
        tradeLog.setBalanceId(balanceId);
        tradeLog.setTradeQuantum(block * 10);
        tradeLog.setTradeBlock(-block);
        tradeLog.setRemarks("方块兑换量子");
        tradeLogMapper.insertTradeLog(tradeLog);
    }

    @Override
    public void insertTradeLogMagicCubeToBlock(String balanceId, int magicCube) {
        TradeLog tradeLog = new TradeLog();
        tradeLog.setBalanceId(balanceId);
        tradeLog.setTradeMagicCube(-magicCube);
        tradeLog.setTradeBlock(magicCube * 26);
        tradeLog.setRemarks("魔方兑换方块");
        tradeLogMapper.insertTradeLog(tradeLog);
    }

    @Override
    public void insertTradeLogBlockToMagicCube(String balanceId, int block) {
        TradeLog tradeLog = new TradeLog();
        tradeLog.setBalanceId(balanceId);
        tradeLog.setTradeMagicCube(block / 27);
        tradeLog.setTradeBlock(-block);
        tradeLog.setRemarks("方块兑换魔方");
        tradeLogMapper.insertTradeLog(tradeLog);
    }

    @Override
    public List<UtilTradeLog> getTradeLogByUid(String uid) {
        return tradeLogMapper.selectTradeLog(balanceMapper.selectFromBalanceByUid(uid).getBalanceId());
    }
}
