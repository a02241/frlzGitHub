package com.frlz.util;

import com.alibaba.fastjson.JSONObject;
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
    public static String sendMessage(String phonenumber){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAILHEY9t3Hc4zU", "jW2VziRJssITv65isnwKyQwr7GOxoC");
        IAcsClient client = new DefaultAcsClient(profile);
        String verifyCode = String.valueOf(new Random().nextInt(899999) + 100000);
        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", phonenumber);
        request.putQueryParameter("SignName", "方融魔方");
        request.putQueryParameter("TemplateCode", "SMS_159830064");
        request.putQueryParameter("TemplateParam","{'code':" + verifyCode + "}" );

        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject jsonObject = JSONObject.parseObject(response.getData());
            if ("OK".equals(jsonObject.getString("Message"))){
                return verifyCode;
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return "false";
    }


}
