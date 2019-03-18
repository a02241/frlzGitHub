package com.frlz.service.serviceImpl;

import com.frlz.mapper.InvitationMapper;
import com.frlz.pojo.Invitation;
import com.frlz.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @program: frlz
 * @description:
 * @author: cz
 * @date: 2019-03-01 10:51
 **/
@Service
public class InvitationServiceImpl implements InvitationService {


    private final InvitationMapper invitationMapper;

    @Autowired
    public InvitationServiceImpl(InvitationMapper invitationMapper) {
        this.invitationMapper = invitationMapper;
    }

    @Override
    public List<Invitation> selectInvatationByUid(String uid) {
        return invitationMapper.selectInvatationByUid(uid);
    }

    @Override
    public int findStateBycode(String code) {
        return invitationMapper.findStateBycode(code);
    }

    @Override
    public void insertInviteCode(String code) {
        invitationMapper.insertInviteCode(code);
    }


}
