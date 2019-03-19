package com.frlz.controller;

import com.frlz.pojo.Balance;
import com.frlz.pojo.TradeLog;
import com.frlz.pojo.User;
import com.frlz.service.BalanceService;
import com.frlz.service.LoginLogService;
import com.frlz.service.TradeLogService;
import com.frlz.service.UserService;
import com.frlz.util.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.frlz.util.GetUername.getUsername;

@RestController
public class UserController extends Cors {

    private final BalanceService balanceService;
    private final UserService userService;
    private final LoginLogService loginLogService;
    private final TradeLogService tradeLogService;

    private BalanceUtil balanceUtil = new BalanceUtil();

    @Autowired
    public UserController(BalanceService balanceService,UserService userService,LoginLogService loginLogService,TradeLogService tradeLogService){
        this.balanceService = balanceService;
        this.userService = userService;
        this.loginLogService = loginLogService;
        this.tradeLogService = tradeLogService;
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



    public boolean checkAccount(@Param("account") String account){
        return ((userService.checkPhonenumber(account) + userService.checkEmail(account)) == 0);
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

    public  R<Object> check(User user , HttpServletResponse response,@RequestParam(defaultValue = "0")String sentCode, @RequestParam(defaultValue = "9")String checkCode) {
        String username = getUsername();
        user.setUsername(username);
        String check = userService.check(user);
        //如果用户名重复，再随机生成一个用户名
        if(check.equals("1")) {
            username = getUsername();
            user.setUsername(username);
            check = userService.check(user);
        }
        System.out.println(sentCode + ":" + checkCode);
        try {
            if(check.equals("5") && !sentCode.equals(checkCode)) {
                check = "4";
            }
            System.out.println(check + "~~~~");
            if(check.equals("5") && sentCode.equals(checkCode)) {
                //随机用户名
                user.setUsername(username);
                //默认头像
                user.setIcon("default.png");
                //默认投资年龄
                user.setInvestmentage(1);
                //默认投资简介
                user.setPrivacy(1);
                //默认状态码
                user.setState(1);
                /*//默认邀请码
                user.setCode("a123");*/
                //注册时间
                Date date = new Date();
                SimpleDateFormat sdf;
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String format = sdf.format(date);
                Date newDate = sdf.parse(format);//创建当前时间以yyyy-MM-dd HH:mm:ss格式
                user.setRegistTime(newDate);
                //默认粉丝数
                user.setFansNumber(0);
                //关注人数
                user.setInterestNumber(0);
                //MD5加密
                user.setPassword(MD5.MD5Encode("fr2018<%" + user.getPassword()  + "%>lz1220"));
                user.setExperience(0);
                //注册信息
                userService.registSave(user);
                User MyUser =userService.selectUserByUsername(username);
                loginLogService.insertLoginLog(MyUser.getUid());//插入登陆日志
                //创建余额账户
                Balance balance = new Balance();
                balance.setUid(MyUser.getUid());
                balance.setBlockBalance(0);
                balance.setQuantumBalance(1);
                balance.setMagicCubeBalance(0);
                balanceService.insertBalance(balance);
                balance = balanceService.selectFromBanlanceByUid(MyUser.getUid());
                TradeLog tradeLog = new TradeLog();
                tradeLog.setBalanceId(balance.getBalanceId());
                tradeLog.setTradeQuantum(1);
                tradeLog.setRemarks("登录奖励增加1点量子");
                tradeLogService.insertTradeLog(tradeLog);//写入交易记录
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return R.isOk().data(check);
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

    public R<HashMap<String,String>> userLogin(String username, String password, @RequestParam(defaultValue = "")String isRember, HttpServletResponse resp) {
        HashMap<String,String> map = new HashMap<>();
        String data;
        User user;
        try {
            user = userService.selectUser(username);
            if (user != null) {
                if (user.getPassword().equals(MD5.MD5Encode("fr2018<%" + password  + "%>lz1220"))) {
                    data = "1";//密码相同返回1
                    if("1".equals(isRember)){//10天免登陆
                        Cookie cookieName = new Cookie("Myusermane", user.getUsername());
                        Cookie cookiePass = new Cookie("Mypassword", user.getPassword());
                        cookieName.setMaxAge(10*24*3600);
                        cookiePass.setMaxAge(10*24*3600);
                        resp.addCookie(cookieName);
                        resp.addCookie(cookiePass);
                    }
                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String format = sdf.format(date);//创建当前时间以yyyy-MM-dd格式
                    Date loginTime = loginLogService.getLatestLoginLog(user.getUid());
                    String lastestTime = sdf.format(loginTime);
                    if (!format.equals(lastestTime)){
                        Balance balance = balanceService.selectFromBanlanceByUid(user.getUid());//根据uid查询余额
                        int count = balance.getQuantumBalance() + 1;//量子余额+1
                        balanceService.updateQuantumBalanceByUid(user.getUid(),count);//交易写入数据库
                        TradeLog tradeLog = new TradeLog();
                        tradeLog.setBalanceId(balance.getBalanceId());
                        tradeLog.setTradeQuantum(1);
                        tradeLog.setRemarks("登录奖励增加1点量子");
                        int experience = user.getExperience() + 5;//登录加1点经验
                        user.setExperience(experience);
                        userService.updateUser(user);//写入数据库
                        tradeLogService.insertTradeLog(tradeLog);//写入交易记录
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
                }
            }else {
                data = "3";//信息为空返回3
                map.put("result",data);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public void emailCode(String email, HttpServletResponse response){
        System.out.println("发送邮件");
        String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";//验证码筛选
        StringBuilder sb = new StringBuilder(4);
        for(int i = 0;i < 4;i++){
            char ch = str.charAt(new Random().nextInt(str.length()));
            sb.append (ch);
        }
        System.out.println(sb.toString()); //随机生成验证码

        int res = Mail.sendEmail("smtp.163.com", "shiyaogame@163.com", "fr20181220", "shiyaogame@163.com", new String[]{email},
                "短信验证",  "【方融魔方】您的验证码为：" + sb.toString() ,"text/html;charset=utf-8");//发送邮箱
        System.out.println("\n发送结果:"+res);
        try {
            if(res == 1) {
                response.getWriter().write(sb.toString());
            }else {
                response.getWriter().write("error");
            }

        } catch (IOException e) {
            e.printStackTrace();
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

    public HashMap<String,Object> sendPhonenumberMessage(String phonenumber){
        HashMap<String,Object> map = new HashMap<>();
        String message = SendMessage.sendMessage(phonenumber);
        map.put("sentCode",message);
        return map;
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
    public String uploadUserIcon(MultipartFile file,@Param("username") String username) throws IOException{
        System.out.println(file + username);
        if (file != null) {// 检查文件是否为空
            String path;
            String type2;
            String fileName = file.getOriginalFilename();
            // 检查文件类型
            type2 = fileName.indexOf(".") != -1?fileName.substring(fileName.lastIndexOf(".")+1):null;
            if (type2 != null) {
                if ("GIF".equals(type2.toUpperCase()) || "PNG".equals(type2.toUpperCase()) || "JPG".equals(type2.toUpperCase())) {

                    String userHome = System.getProperties().getProperty("java.home");

                    path = "/usr/local/nginx/html/project/html/userImg"+  username +".jpg";

                    File file2 = new File(path);

                    file.transferTo(file2);

                    userService.uploadUserIcon(username,username+".jpg");

                    return "上传成功";
                }else {
                    System.out.println("文件后缀不正确");
                    return "文件后缀不正确";
                }
            }else {
                System.out.println("文件格式不正确");
                return "文件格式不正确";
            }
        }else {
            System.out.println("文件为空");
            return "文件为空";
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

    public String updateUser(String uid,
                             @RequestParam(defaultValue="")String username,@RequestParam(defaultValue="")String phonenumber,@RequestParam(defaultValue="")String email,
                             @RequestParam(defaultValue="0")int investmentage,@RequestParam(defaultValue="")String profile,@RequestParam(defaultValue="")String profession,
                             @RequestParam(defaultValue="")String residence,@RequestParam(defaultValue="")String province,@RequestParam(defaultValue="")String city){
        User user = userService.selectByUid(uid);
        if (username.trim().length()>0){

            Balance balance = balanceService.selectFromBanlanceByUid(user.getUid());
            if (balance.getQuantumBalance() > 10) {
                user.setUsername(username);
                balanceService.updateQuantumBalanceByUid(user.getUid(), balance.getQuantumBalance() - 10);
                TradeLog tradeLog = new TradeLog();
                tradeLog.setTradeQuantum(-10);
                tradeLog.setBalanceId(balance.getBalanceId());
                tradeLog.setRemarks("修改昵称");
                tradeLogService.insertTradeLog(tradeLog);
            }else {
                return "量子余额不足";
            }
        }
        if (phonenumber.trim().length()>0){
            user.setPhonenumber(phonenumber);
        }
        if (!"".equals(email)){
            user.setEmail(email);
        }
        if (investmentage != 0){
            user.setInvestmentage(investmentage);
        }
        if (!"".equals(profile)){
            user.setProfile(profile);
        }
        if (!"".equals(profession)){
            user.setProfession(profession);
        }
        if (!"".equals(residence)){
            user.setResidence(residence);
        }
        if (!"".equals(province)){
            user.setProvince(province);
        }
        if (!"".equals(city)){
            user.setCity(city);
        }
        userService.updateUser(user);
        return "success";
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

    public HashMap<String,Object> updatePassword(User user,String newPassword){
        HashMap<String,Object> map = new HashMap<>();
        if(userService.checkPasswordByUId(user.getUid()).equals(
                MD5.MD5Encode("fr2018<%" + user.getPassword()  + "%>lz1220"))){
            user.setPassword(MD5.MD5Encode("fr2018<%" + newPassword  + "%>lz1220"));
            userService.updatePassword(user);
            map.put("result","success");
        }else {
            map.put("result","false");
        }
        return map;
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

    public HashMap<String,Object> seeInformation(String uid){
        User user = userService.selectByUid(uid);
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
        return map;
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

    public String boundPhone(String uid , String phonenumber){
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0-9])|(14[5,7])| (17[0,1,3,5-8]))\\d{8}$");
        Matcher m = p.matcher(phonenumber);
        if(!m.matches()){
            return "false";
        }else {
            userService.updatePhonenumber(uid, phonenumber);
            balanceUtil.addQuantumBalance(uid,5);//增加5个量子
        }
        return "success";
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

    public String boundEmail(String uid , String email){
        String regex = "\\w+@\\w+(\\.\\w{2,3})*\\.\\w{2,3}";
        boolean tag = true;
        if (!email.matches(regex)) {
            tag = false;
        }
        if(tag) {
            userService.updateEmail(uid, email);
            balanceUtil.addQuantumBalance(uid,5);//增加5个量子
        }else {
            return "false";
        }
        return "success";
    }

    @RequestMapping("ok")
    public R<User> ok(){
        User user = new User();
        user.setCity("123");
        user.setFansNumber(233);
        user.setExperience(324);
        return R.isOk().data(user);
    }
}
