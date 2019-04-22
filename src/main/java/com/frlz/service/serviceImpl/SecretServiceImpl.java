package com.frlz.service.serviceImpl;

import com.frlz.mapper.SecretMapper;
import com.frlz.pojo.Secret;
import com.frlz.service.SecretService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: frlz
 * @description: 密保接口实现类
 * @author: cz
 * @date: 2019-03-09 10:19
 **/
@Service
public class SecretServiceImpl implements SecretService {

    private final SecretMapper secretMapper;

    @Autowired
    public SecretServiceImpl(SecretMapper secretMapper) {
        this.secretMapper = secretMapper;
    }



    @Override
    public void insertSecret(Secret secret) {
        secretMapper.insertSecret(secret);

    }

    @Override
    public Map<String,String> selectFromSecret(String uid) {
        Secret secret = secretMapper.selectFromSecret(uid);
        Map<String,String> secretMap = new HashMap<>(3);
        secretMap.put("questionOne",secret.getQuestionOne());
        secretMap.put("questionTwo",secret.getQuestionTwo());
        secretMap.put("questionThree",secret.getQuestionThree());
        return secretMap;
    }

    @Override
    public void updateSecret(Secret secret) {
        Secret secretOld = secretMapper.selectFromSecret(secret.getUid());
        if ("".equals(secret.getQuestionOne())){
            secret.setQuestionOne(secretOld.getQuestionOne());
            secret.setAnswerOne(secretOld.getAnswerOne());
        }
        if ("".equals(secret.getQuestionTwo())){
            secret.setQuestionTwo(secretOld.getQuestionTwo());
            secret.setAnswerTwo(secretOld.getAnswerTwo());
        }
        if ("".equals(secret.getQuestionThree())){
            secret.setQuestionThree(secretOld.getQuestionThree());
            secret.setAnswerThree(secretOld.getAnswerThree());
        }
        secretMapper.updateSecret(secret);
    }

    @Override
    public int checkSecret(String uid,String answer1,String answer2,String answer3) {
        Secret secret = secretMapper.selectFromSecret(uid);
        int n = 0;
        if (answer1.equals(secret.getAnswerOne())){
            n += 1;
        }
        if (answer2.equals(secret.getAnswerTwo())){
            n += 10;
        }
        if (answer3.equals(secret.getAnswerThree())){
            n += 100;
        }
        return n;
    }
}
