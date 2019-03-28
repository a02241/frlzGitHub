package com.frlz.controller;

import com.frlz.pojo.Balance;
import com.frlz.pojo.User;
import com.frlz.service.*;
import com.frlz.util.*;
import com.frlz.utilPojo.UtilBalance;
import com.frlz.utilPojo.UtilTradeLog;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
@RestController
public class UserController extends Cors {

    private final BalanceService balanceService;
    private final UserService userService;
    private final LoginLogService loginLogService;
    private final TradeLogService tradeLogService;
    private final InvitationService invitationService;

    private BalanceUtil balanceUtil = new BalanceUtil();

    @Autowired
    public UserController(BalanceService balanceService, UserService userService, LoginLogService loginLogService, TradeLogService tradeLogService, InvitationService invitationService){
        this.balanceService = balanceService;
        this.userService = userService;
        this.loginLogService = loginLogService;
        this.tradeLogService = tradeLogService;
        this.invitationService = invitationService;
    }
    @PostMapping("/checkAccount")//注册时校验账号是否重复
    /**
     * 注册时校验手机或者邮箱是否重复
     * @title checkAccount
     * @author cz
     * @date 2019/2/28 17:34
     * @Description: TODO 参数account(手机号或者邮箱),返回true为能注册,false不能注册
     * @param account
     * @return boolean
     * @throws
     */
    public R<Boolean> checkAccount(@Param("account") String account)  {
        return R.isOk().data(((userService.checkPhonenumber(account) + userService.checkEmail(account)) == 0));
    }

    @Transactional
    @PostMapping("/check")
    /**
     * 注册时校验是否注册成功
     * @title check
     * @author cz
     * @date 2019/3/1 10:21
     * @param user
     * @param request
     * @param response
     * @param session
     * @param emailCode
     * @param checkCode
     * @Description: TODO 检查注册重复问题,必填字段:phonenumber或者email,password,sentCode(发送验证码),checkCode(填写验证码)
     *                      返回"4"为验证码错误,"5"为注册成功,2为手机被注册,3为邮箱被注册
     * @return void
     * @throws 
     */
    public  R<Object> check(User user ,@RequestParam(defaultValue = "0")String sentCode, @RequestParam(defaultValue = "9")String checkCode,@RequestParam(defaultValue = "")String code){
        String check = userService.check(user);
        if(!sentCode.equals(checkCode)) {
            return R.isFail().data("4");
        }else {
            user.setUsername(check);
            check = "5";
            //有邀请码
            String MyUid;
            int result = invitationService.findStateBycode(code);
            if (code.trim().length() > 0&& result == 1){
                user.setExperience(0);//有邀请码经验为0
            }else {
                user.setExperience(-1);//无邀请码经验为-1
            }
            MyUid = userService.registSave(user);
            if (result==1){
                invitationService.updateInviteState(code,MyUid);
            }
            loginLogService.insertLoginLog(MyUid);//插入登陆日志
            //创建余额账户
            Balance balance;
            balanceService.insertBalance(0,1,0,MyUid);
            balance = balanceService.selectFromBanlanceByUid(MyUid);
            tradeLogService.insertTradeLog(balance.getBalanceId(),1,0,0,"登录奖励增加1点量子");//写入交易记录
        }
        return R.isOk().msg("注册成功").data(check);
    }

    @Transactional
    @PostMapping("/userLogin")
    /**
     * 登录即10天免登陆,插入登录日志
     * @title userLogin
     * @author cz
     * @date 2019/3/1 9:16
     * @param isRember
     * @param request
     * @param session
     * @param response
     * @param resp
     * @Description: TODO 用户登录,必填参数:username,password,选填:isRember-->>十天免登陆(参数“1”为勾选)
     * @return java.util.HashMap<java.lang.String,java.lang.String>
     * @throws
     */

    public R<HashMap<String,String>> userLogin(String username, String password) {
        HashMap<String,String> map = new HashMap<>();
        String data;
        User user = userService.selectUser(username);
        if (user != null) {
            if (user.getPassword().equals(MD5.MD5Encode("fr2018<%" + password  + "%>lz1220"))) {
                data = "1";//密码相同返回1
                String format = DateTime.getNowTimeToString();
                Date loginTime = loginLogService.getLatestLoginLog(user.getUid());
                String lastestTime = DateTime.getTimeByDateToString(loginTime);
                if (!format.equals(lastestTime)){
                    Balance balance;//根据uid查询余额
                    int experience;//登录加1点经验
                    balance = balanceService.selectFromBanlanceByUid(user.getUid());
                    int count = balance.getQuantumBalance() + 1;//量子余额+1
                    balanceService.updateQuantumBalanceByUid(user.getUid(),count);//交易写入数据库
                    experience = user.getExperience() + 1;
                    user.setExperience(experience);
                    userService.updateUser(user);//写入数据库
                    tradeLogService.insertTradeLog(balance.getBalanceId(),1,0,0,"登录奖励增加1点量子");//写入交易记录
                }
                loginLogService.insertLoginLog(user.getUid());//插入登陆日志
                map.put("result",data);
                map.put("Myusermane",user.getUsername());
                map.put("uid",user.getUid());
                map.put("icon",user.getIcon());
                map.put("experience", String.valueOf(user.getExperience()));
            }else {
                data = "2";//密码不同返回2
                map.put("result",data);
                return R.isFail().data(map);
            }
        }else {
            data = "3";//信息为空返回3
            map.put("result",data);
            return R.isFail().data(map);
        }
        return R.isOk().data(map);
    }

    @PostMapping("/emailCode")
    /**
     * 发送邮箱验证码
     * @title emailCode
     * @author cz
     * @date 2019/2/28 17:18
     * @param email
     * @param request
     * @param response
     * @param session
     * @Description: TODO 发送邮件到邮箱 必填字段:email 发送成功返回验证码 发送失败返回error
     * @return void
     * @throws
     */

    public R<String> emailCode(String email){
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";//验证码筛选
        StringBuilder sb = new StringBuilder(4);
        for(int i = 0;i < 4;i++){

            char ch = str.charAt(new Random().nextInt(str.length()));
            sb.append (ch);
        }
        int res = Mail.sendEmail("smtp.163.com", "shiyaogame@163.com", "fr20181220", "shiyaogame@163.com", new String[]{email},
                "短信验证",  "【方融魔方】您的验证码为：" + sb.toString() ,"text/html;charset=utf-8");//发送邮箱
        if(res == 1) {
            return R.isOk().data(sb.toString());
        }else {
            return R.isFail().msg("error");
        }
    }

    @PostMapping("sendPhonenumberMessage")
    /**
     *
     * @title sendPhonenumberMessage
     * @create by: cz
     * @description: TODO 必填字段phonenumber,返回sentCode(验证码)
     * @create time: 2019/3/8 10:09
     * @Param: phonenumber
     * @throws
     * @return java.util.HashMap<java.lang.String,java.lang.Object>
     * @version V1.0
     */

    public R<String> sendPhonenumberMessage(String phonenumber){
        return R.isOk().data(SendMessage.sendMessage(phonenumber));
    }

    @PostMapping("/uploadUserIcon")
    /**
     * 上传头像
     * @title uploadUserIcon
     * @author cz
     * @date 2019/3/2 12:04
     * @param file
     * @param username
     * @Description: TODO 用户头像上次,必填参数:username,file文件
     * @return java.lang.String
     * @throws
     */
    public R<String> uploadUserIcon(String file,@Param("username") String username) throws IOException{
        MultipartFile multipartFile = Base64Decode.base64Convert(file);
        if (multipartFile != null) {// 检查文件是否为空
            String fileName = multipartFile.getOriginalFilename();
            // 检查文件类型
            String type2 = fileName.indexOf(".") != -1?fileName.substring(fileName.lastIndexOf(".")+1):null;
            if (type2 != null) {
                String path = "/usr/local/nginx/html/project/html/userImg/"+  username +".jpg";
                File file2 = new File(path);
                multipartFile.transferTo(file2);
                userService.uploadUserIcon(username,username+".jpg");
                return R.isOk().msg("上传成功");
            }else {
                System.out.println("文件格式不正确");
                return R.isFail().msg("文件格式不正确");
            }
        }else {
            System.out.println("文件为空");
            return R.isFail().msg("文件为空");
        }
    }


    @Transactional
    @PostMapping("/updateUser")
    /**
     * 更新用户信息
     * @title updateUser
     * @author cz
     * @date 2019/3/4 11:28
     * @param uid
     * @param usernmae
     * @param phonenumber
     * @param email
     * @param investmentage
     * @param profile
     * @param profession
     * @param residence
     * @Description: TODO 根据传递的参数进行传值,不传值默认为数据库的值,必填参数uid
     * @return java.lang.String
     * @throws
     */

    public R<String> updateUser(String uid,
                                @RequestParam(defaultValue="")String username, @RequestParam(defaultValue="")String phonenumber, @RequestParam(defaultValue="")String email,
                                @RequestParam(defaultValue="0")int investmentage, @RequestParam(defaultValue="")String profession, @RequestParam(defaultValue="")String residence,
                                @RequestParam(defaultValue="")String province, @RequestParam(defaultValue="")String city, @RequestParam(defaultValue = "")String signature,
                                @RequestParam(defaultValue = "0")int sex,@RequestParam(defaultValue = "")String birthday)  {
        User user = userService.selectByUid(uid);
        if(user != null) {
            if (username.trim().length() > 0) {
                Balance balance = balanceService.selectFromBanlanceByUid(user.getUid());
                if (balance.getQuantumBalance() > 10) {
                    user.setUsername(username);
                    balanceService.updateQuantumBalanceByUid(user.getUid(), balance.getQuantumBalance() - 10);
                    tradeLogService.insertTradeLog(balance.getBalanceId(), -10, 0, 0, "修改昵称花费10量子");
                } else {
                    return R.isFail().msg("量子余额不足");
                }
            }
            if (phonenumber.trim().length() > 0) {
                user.setPhonenumber(phonenumber);
            }
            if (!"".equals(email)) {
                user.setEmail(email);
            }
            if (investmentage != 0) {
                user.setInvestmentage(investmentage);
            }
            if (!"".equals(profession)) {
                user.setProfession(profession);
            }
            if (!"".equals(residence)) {
                user.setResidence(residence);
            }
            if (!"".equals(province)) {
                user.setProvince(province);
            }
            if (!"".equals(city)) {
                user.setCity(city);
            }
            if (!"".equals(signature)){
                userService.changeSignature(signature,uid);
            }
            if (sex!=0){
                user.setSex(sex);
            }
            if (!"".equals(birthday)){
                Date newBirthday = DateTime.getDateByString(birthday);
                user.setBirthday(newBirthday);
            }
            userService.updateUser(user);
            return R.isOk().msg("success");
        }else {
            return R.isFail().msg("false");
        }
    }

    @PostMapping("updatePassword")
    /**
     * 更换密码
     * @title updatePassword
     * @author cz
     * @date 2019/3/4 15:35
     * @param user
     * @Description: TODO 更新密码，必填参数uid,password，newPassword 返回success则为更改成功,false则为原密码错误
     * @return java.lang.String
     * @throws
     */

    public R<String> updatePassword(User user,String newPassword)  {
        String uid = userService.checkPasswordByUId(user.getUid());
        if(uid.equals(MD5.MD5Encode("fr2018<%" + user.getPassword()  + "%>lz1220"))){
            user.setPassword(MD5.MD5Encode("fr2018<%" + newPassword  + "%>lz1220"));
            userService.updatePassword(user);
            return R.isOk().data("success");
        }else {
            return R.isOk().data("false");
        }
    }



    @PostMapping("/seeInformation")
    /**
     * 根据id查询所有信息
     * @title seeInformation
     * @create by: cz
     * @description: TODO 根据id查询用户信息必填字段:uid，返回为user表的信息
     * @create time: 2019/3/13 15:45
     * @Param: uid
     * @throws
     * @return java.util.HashMap<java.lang.String,java.lang.Object>
     * @version V1.0
     */

    public R<HashMap<String,Object>> seeInformation(String uid)  {
        User user = userService.selectByUid(uid);
        if(user != null){
            HashMap<String,Object> map = new HashMap<>();
            map.put("username",user.getUsername());
            map.put("phonenumber",user.getPhonenumber());
            map.put("email",user.getEmail());
            map.put("icon",user.getIcon());
            map.put("investmentage",user.getInvestmentage());
            map.put("profile",user.getProfile());
            map.put("profession",user.getProfession());
            map.put("residence",user.getResidence());
            map.put("privacy",user.getPrivacy());
            map.put("province",user.getProvince());
            map.put("city",user.getCity());
            map.put("registtime",user.getRegistTime());
            map.put("experience", String.valueOf(user.getExperience()));
            map.put("signature",user.getSignature());
            map.put("interestNumber",user.getInterestNumber());
            map.put("fansNumber",user.getFansNumber());
            map.put("sex",user.getSex());
            map.put("birthday",user.getBirthday());
            map.put("balance",user.getBalance());
            return R.isOk().data(map);
        }else {
            return R.isFail().data("false");
        }
    }

    @PostMapping("boundPhone")
    /**
     * 绑定手机
     * @title boundPhone
     * @create by: cz
     * @description: TODO 必填参数：uid，phonenumber
     *                 成功返回success false为手机格式不正确
     * @create time: 2019/3/14 14:21
     * @Param: uid
     * @Param: phone
     * @throws
     * @return java.lang.String
     * @version V1.0
     */

    public R<String> boundPhone(String uid , String phonenumber)  {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(14[5,7])| (17[0,1,3,5-8]))\\d{8}$");
        Matcher m = p.matcher(phonenumber);
        if(!m.matches()){
            return R.isFail().msg("false");
        }else {
            userService.updatePhonenumber(uid, phonenumber);
            balanceUtil.addQuantumBalance(uid,5,"绑定手机增加5个量子");//增加5个量子
        }
        return R.isOk().msg("success");
    }

    @PostMapping("boundEmail")
    /**
     * 绑定邮箱
     * @title boundEmail
     * @create by: cz
     * @description: TODO 必填参数：uid，email
     *                 成功返回success 邮箱格式错误返回false
     * @create time: 2019/3/14 14:28
     * @Param: uid
     * @Param: email
     * @throws
     * @return java.lang.String
     * @version V1.0
     */

    public R<String> boundEmail(String uid , String email)  {
        String regex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
        boolean tag = true;
        if (!email.matches(regex)) {
            tag = false;
        }
        if(tag) {
            userService.updateEmail(uid, email);
            balanceUtil.addQuantumBalance(uid,5,"绑定邮箱增加5个量子");//增加5个量子
        }else {
            return R.isFail().msg("false");
        }
        return R.isOk().msg("success");
    }

    @PostMapping("changeProfile")
    public R<String> changeProfile(String profile, @Valid String uid){
        String result = userService.updateProfile(profile,uid);
        if (result.equals("false")){
            return R.isFail().msg("uid错误");
        }
        return  R.isOk().msg("更新成功");
    }

    //获取安全系数，传入uid，返回绑定信息，若无，则未绑定
    @PostMapping("getSecurity")
    public R getSecurity(String uid){
        return R.isOk().data(userService.getSecurity(uid));
    }

    @PostMapping("showMyInformation")
    /**
     * TODO 个人中心首页展示
     * @title showMyInformation
     * @create by: cz
     * @description: TODO 必填参数 uid
     * @create time: 2019/3/28 9:51
     * @Param: uid
     * @throws
     * @return com.frlz.util.R<java.util.HashMap<java.lang.String,java.lang.Object>>
     * @version V1.0
     */

    public R<HashMap<String,Object>> showMyInformation(String uid){
        HashMap<String,Object> map = new HashMap<>();
        User user = userService.selectByUid(uid);
        UtilBalance balance = balanceService.selectShowBanlanceByUid(uid);
        if (balance!=null&&uid!=null){
            map.put("username",user.getUsername());
            map.put("experience", String.valueOf(user.getExperience()));
            map.put("blance",balance);
        }else {
            return R.isFail().msg("uid错误");
        }
        return R.isOk().data(map);
    }

    @PostMapping("showMyBlock")
    public R<HashMap<String,Object>> showMyBlock(String uid){
        HashMap<String,Object> map = new HashMap<>();
        UtilBalance balance = balanceService.selectShowBanlanceByUid(uid);
        List<UtilTradeLog> utilTradeLog = tradeLogService.getTradeLogByUid(uid);
        if (balance!=null&&utilTradeLog!=null){
            map.put("blance",balance);
            map.put("tradeLog",utilTradeLog);
        }else {
            return R.isFail().msg("uid错误");
        }
        return R.isOk().data(map);
    }
}
