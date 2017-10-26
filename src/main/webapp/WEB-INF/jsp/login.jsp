<%--
  Created by IntelliJ IDEA.
  User: 榕榕
  Date: 2017/9/21
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>后台登录</title>
    <link rel="icon" href="/static/img/hr-logo.png" type="image/x-icon"/>
</head>
<script src="/static/js/plugins/jquery/jquery.min.js" type="text/javascript"></script>
<script src="/static/js/plugins/layer/layer.js"></script>

<style type="text/css">
    body,td,th {
        font-family: 微软雅黑;
        font-size: 12px;
        color: #333333;
    }
    body {
        margin:0 auto;
        background-image: url(/static/img/login/loginbg.jpg);
        background-repeat: no-repeat;
        background-position: center top;
        background-color:#F2F2F2;
        width:1000px;
    }
    a:link {
        color: #333333;
        text-decoration: none;
    }
    a:visited {
        text-decoration: none;
        color: #333333;
    }
    a:hover {
        text-decoration: none;
        color: #01A2AA;
    }
    a:active {
        text-decoration: none;
        color: #01A2AA;
    }
    .login2016{ width:auto; height:455px; padding-top:305px; padding-left:678px; padding-right:48px;}
    #logtab{}
    #logtab td{height:55px;}
    .reglink{ color:#FFFFFF; font-weight:bold; font-size:14px;}
    .reglink a{ color:#FFFFFF!important;}

    #logtab input{
        width:224px;
        height:38px;
        line-height:38px;
        background-color:#FFFFFF;
        padding-left:50px;
        border:0px #fff solid;
        color:#666666;
        font-size:14px;
        border-radius:3px;
    }
    #logtab .userName{
        width: 100%;
        background-image: url(/static/img/login/login_3.jpg);
        background-repeat: no-repeat;
        background-position: left center;
    }

    #logtab .password{
        width: 100%;
        background-image: url(/static/img/login/login_6.jpg);
        background-repeat: no-repeat;
        background-position: left center;
    }

    #logtab .code{
        width: 88% !important;
        background-image: url(/static/img/login/login_11.jpg);
        background-repeat: no-repeat;
        background-position: left center;
    }
    #btnLogin{
        background-image: url(/static/img/login/login_14.jpg);
        width:274px;
        height:40px ;
        border:0px;
    }

    .bottom2016{ width:1000px; height:105px; padding-top:35px; line-height:35px; text-align:center; color:#878787;}
    .bottom2016 a{ color:#878787!important;}
</style>


<body>

<div class="login2016">
    <table width="100%" border="0" cellspacing="0" cellpadding="0" id="logtab">
        <tr>
            <td><input type="text" name="userName" id="userName"  class="userName"  placeholder="用户名" value="admin"/></td>
        </tr>
        <tr>
            <td><input type="password" name="password"  id="password" class="password" placeholder="密码" value="123456" /></td>
        </tr>
        <tr>
            <td><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="codeClass"><input type="text" name="code"  id="code" class="code" placeholder="验证码"/></td>
                    <td><img id="imgObj" name="imgCode" src="/validateCode/code" alt="验证码" width="84" height="40" onclick="changeImg()" /></td>
                </tr>
            </table></td>
        </tr>
        <tr>

            <td valign="middle"><button id="btnLogin" onclick="loginForm()" ></button></td>
        </tr>
        <tr>
            <td align="center" class="reglink"><a href="#">立即注册</a> | <a href="#">忘记密码</a></td>
        </tr>
    </table>
</div>

<div class="bottom2016">主办单位：山东慧若电子商务有限公司 承办单位：Easy开发组<br />
    Copyrigth@山东慧若电子商务有限公司 技术支持：<a href="http://www.sd_hrdz.com" target="_blank">慧若电子</a> 服务电话：0574-65006161</div>

</body>
<script type="application/javascript" src="/static/js/plugins/jquery/jquery.min.js"></script>
<script type="text/javascript">
    // 刷新图片
    function changeImg() {
        var imgSrc = $("#imgObj");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", changeUrl(src));
    }
    //为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
    function changeUrl(url) {
        var timestamp = (new Date()).valueOf();
        var index = url.indexOf("?");
        if (index != -1) {
            url = url.substring(0, url.indexOf("?"));
        }
        if ((url.indexOf("&") >= 0)) {
            url = url + "&tamp=" + timestamp;
        } else {
            url = url + "?timestamp=" + timestamp;
        }
        return url;
    }

    //绑定回车事件
    $(document).keydown(function (event) {
        if (event.keyCode == 13) {
            loginForm();
        }
    });


    /**
     * 登录
     * @returns {boolean}
     */
    function loginForm() {
        var userName = $("#userName").val();
        var password = $("#password").val();
        var code = $("#code").val();
        console.info(userName+":"+password+":"+code);

        //验证
        if (userName.trim() == "") {
            layer.msg("请输入用户名",{icon:0});
            return false;
        }
        if (password.trim() == "") {
            layer.msg("请输入密码",{icon:0});
            return false;
        }
//        if(code.trim()==""){
//            layer.msg("请输入验证码",{icon:0})
//        }
        $.ajax({
            type:"POST",
            url : "/login/manager",
            data:{"userName":userName,"password":password,"code":code},
            dataType: "json",
            success : function(data){
                if (data.success) {
                    layer.msg('登录成功！',{icon: 1,time:1000}, function(){
                        window.location.href = "/";
                    });
                } else {
                    if(data.code==4){
                        changeImg()
                    }
                    layer.msg(data.msg,{icon: 2,time:1000})
                }
            }
        });
    }
</script>
</html>