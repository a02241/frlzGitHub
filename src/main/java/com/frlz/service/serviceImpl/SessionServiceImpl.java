package com.frlz.service.serviceImpl;

import com.frlz.mapper.SessionMapper;
import com.frlz.pojo.Session;
import com.frlz.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * @author cz
 */
@Service
public class SessionServiceImpl implements SessionService {

    private SessionMapper sessionMapper;
    @Autowired
    public SessionServiceImpl(SessionMapper sessionMapper){
        this.sessionMapper = sessionMapper;
    }

    @Override
    public void addSession(String sessionID) {
        sessionMapper.insertSession(sessionID);
    }

    @Override
    public void deleteSession(String sessionID) {
        sessionMapper.deleteSession(sessionID);
    }

    @Override
    public Session getSession(String sessionID) {
        return sessionMapper.selectSession(sessionID);
    }
}
