package com.frlz.service.serviceImpl;

import com.frlz.mapper.InvitationMapper;
import com.frlz.pojo.Invitation;
import com.frlz.service.InvitationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @program: frlz
 * @description:
 * @author: cz
 * @date: 2019-03-01 10:51
 **/
@Transactional
@Service
public class InvitationServiceImpl implements InvitationService {


    private final InvitationMapper invitationMapper;

    @Autowired
    public InvitationServiceImpl(InvitationMapper invitationMapper) {
        this.invitationMapper = invitationMapper;
    }

    @Override
    public List<Invitation> selectInvatationByUid(String uid) throws Exception{
        return invitationMapper.selectInvatationByUid(uid);
    }

    @Override
    public int findStateBycode(String code) throws Exception{
        int result = 0;//默认为0，没有该邀请码
        Invitation invitation =invitationMapper.findStateBycode(code);
        if (invitation!=null){
            if (invitation.getState()==1){
                result = 1;//1位为邀请码未被使用
                return result;
            }if (invitation.getState()==2){
                result = 2;//2为邀请码已被使用
                return result;
            }
        }
        return result;
    }

    @Override
    public void insertInviteCode(String code) throws Exception{
        invitationMapper.insertInviteCode(code);
    }

    @Override
    public void updateInviteState(String code,String uid) throws Exception{
        invitationMapper.updateInviteState(code,uid);
    }


}
