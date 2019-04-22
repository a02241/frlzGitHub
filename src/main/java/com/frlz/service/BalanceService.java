package com.frlz.service;


import com.frlz.pojo.Balance;
import com.frlz.dto.UtilBalance;

import java.util.List;
/**
 * @author cz
 */
public interface BalanceService {

    void insertBalance(int blockBalance,int quantumBalance,int magicCubeBalance,String uid);

    Balance selectFromBalanceByUid(String uid);

    UtilBalance selectShowBalanceByUid(String uid);

    void updateQuantumBalanceByUid(String uid,int quantumBalance);

    void updateBlockBalanceByUid(String uid,int blockBalance);

    void updateMagicCubeBalanceByUid(String uid,int magicCubeBalance);

    List<Balance> selectAllBalance();

    void loginAddQuantumBalanceByUid(String uid,int quantumBalance);
}
