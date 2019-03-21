package com.frlz.service;

import com.frlz.pojo.Invitation;

import java.util.List;

public interface InvitationService {

    public List<Invitation> selectInvatationByUid(String uid) throws Exception;

    public int findStateBycode(String code) throws Exception;

    public void insertInviteCode(String code) throws Exception;

    public void updateInviteState(String code ,String uid) throws Exception;
}
