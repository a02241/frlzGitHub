//登陆注册页面跳转
$('.login_zhuce').click(function(){
    $('.login_box').css('display','none')
    $('.zhuce_box').css('display','block')
    $('#phone_zhuce').css('display','block')
    $('#email_zhuce').css('display','none')
})
$('.zhuce_change').click(function(){
    if($("#phone_zhuce").css("display") == 'block'){
    $('#phone_zhuce').css('display','none')
    $('#email_zhuce').css('display','block')
    }
    else{
    $('#phone_zhuce').css('display','block')
    $('#email_zhuce').css('display','none')
    }
})
$('.zhuce_x').click(function(){
    $('.login_box').css('display','none')
    $('.zhuce_box').css('display','none')
    $('#login_page').css('display','none')
})
$('.login_x').click(function(){
    $('.login_box').css('display','none')
    $('.zhuce_box').css('display','none')
    $('#login_page').css('display','none')
})
$('#login_test').click(function(){
    $('#login_page').css('display','block')
    $('.login_box').css('display','block')
})
//登陆界面接口验证
var userlogin = function(username,password){
    $.ajax({
        type:'post',
        url:'http://192.168.3.173:8080/userLogin',
       data:{
        username:username,
        password:password,
       },
        success: function(res){
            console.log(res)
            var typecode=res.result
            if(typecode == 3){
            //
            $('#login_username').val('用户不存在')
            }
            else if(typecode == 2){
            //密码错误
            $('#login_password').val('密码错误')
            }
            else{
                //登陆成功
            }
        }
    })
}

$('#login_btn').click(function(){
var username = $('#login_username').val()
var password = $('#login_password').val()
userlogin(username,password)
})
//手机注册
//邮箱注册
/*邮箱验证码*/
//注册账号是否已存在
var checkaccount = function(email){
    $.ajax({
        type:'post',
        url:'http://192.168.3.173:8080/checkAccount',
        data:{
          account: email
        },
        success: function(res){
            console.log(res);
            if(res == true){
                emailcode(email)
            }else{
                $('#zhuce_email').val('该账号已被注册')
            }
        }
    })    
}
//发送验证码
var emailcode = function(email){
    $.ajax({
        type:'post',
        url:'http://192.168.3.173:8080/emailCode',
        data:{
            email: email
        },
        success: function(res){
        $('#zhuce_email_btn').css('value',res);
        console.log($('#zhuce_email_btn').val())
        }
    })
}
$('#email_checkcode_btn').click(function(){
    var email = $('#zhuce_email').val()
    checkaccount(email);
})
/*手机号位数加数字判断*/
function checkTel() { 
    var str = '11111111111'; 
	var regTel1 = /^1(3|4|5|6|7|8|9)\d{9}$/.test(str);
	if (str != "") { 
		if (!regTel1 ) { 
		alert("电话号码输入有误！"); 
		obj.focus(); 
		}else{
            alert("电话号码输入正确！"); 
        }
	} else { 
		alert("请输入电话号码！"); 
	} 
} 
/* 密码大于等于6位 字母数字组成 */
function test(str){
    var str = str;
        var regUpper = /[A-Z]/;
        var regLower = /[a-z]/;
        var regStr = /[0-9]/;
        var complex = 0;
        if (regLower.test(str)) {
        ++complex;
        }
        if (regUpper.test(str)) {
        ++complex;
        }
        if(regStr.test(str)){
        ++complex;
        }
        if ( str.length >= 8 && complex>=2) {
        return(1)
        }else{
        return(2)
        }
}

$('#zhuce_email_btn').click(function(){
    var email = $('#zhuce_email').val()
    var checkcode = $('#zhuce_checkcode').val()
    var password = $('#zhuce_password').val()
    var password_1 = $('#zhuce_password_1').val()
    var yaoqing = $('#zhuce_yaoqing').val()
    console.log(1)
    if(test(password) == 1){
        if(password == password_1){
            $.ajax({
            type:'post',
            url:'http://192.168.3.173:8080/check',
            data:{
                email: email,
                emailCode:emailcode,
                checkCode:checkcode,
                password:password
            },
            success: function(res){
                console.log(res);
            }
        })
        }else{
            //两次密码不同
        }
    }else{
        alert('6位数 中英文')
    }
})