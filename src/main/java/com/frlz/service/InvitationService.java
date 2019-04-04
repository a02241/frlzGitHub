package com.frlz.service;

import com.frlz.pojo.Invitation;

import java.util.List;

public interface InvitationService {

    public List<Invitation> selectInvatationByUid(String uid);

    public int findStateBycode(String code);

    public void insertInviteCode(String code);

    public void updateInviteState(String code ,String uid);

    Invitation getOneInvitation();

    void updateInviteUid(String uid,String code);
}
