package com.frlz.service;

import com.frlz.pojo.Session;

public interface SessionService {
    void addSession(String sessionID);

    void deleteSession(String sessionID);

    Session getSession(String sessionID);
}
