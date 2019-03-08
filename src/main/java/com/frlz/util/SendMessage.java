package com.frlz.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

import java.util.Random;

public class SendMessage {
    public String sendMessage(String phonenumber){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAILHEY9t3Hc4zU", "jW2VziRJssITv65isnwKyQwr7GOxoC");
        IAcsClient client = new DefaultAcsClient(profile);
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phonenumber);
        request.putQueryParameter("SignName", "方融信息");
        request.putQueryParameter("TemplateCode", "SMS_159830064");
        request.putQueryParameter("TemplateParam","{'code':" + verifyCode + "}" );

        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return verifyCode;
    }


}
