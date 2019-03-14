package com.frlz.service;

import com.frlz.pojo.Invitation;

import java.util.List;

public interface InvitationService {

    public List<Invitation> selectInvatationByUid(String uid);

    public int findStateBycode(String code);

    void insertInviteCode(String code);
}
