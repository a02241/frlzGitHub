package com.frlz.service;

import com.frlz.pojo.Secret;

import java.util.Map;
/**
 * @author cz
 */
public interface SecretService {

    void insertSecret(Secret secret);

    Map<String,String> selectFromSecret(String uid);

    void updateSecret(Secret secret);

    int checkSecret(String uid,String answer1,String answer2,String answer3);
}
