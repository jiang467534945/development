<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>500错误页面</title>
    <link href="/static/error/css/pintuer.css" rel="stylesheet"/>
    <style>
        *{ margin:0; padding:0; list-style:none;}
        table{border-collapse:collapse;border-spacing:0;}
        body,html{ height:100%; font-family:'微软雅黑'; overflow-y:hidden;}
        .main{ width:60%; margin-left:20%; margin-right:20%; margin-top:10%;}
        .main_left{ width:38%; margin-left:12%; margin-top:10%; float:left;}
        .main_right{width:50%; float:left;}
        .main_radius{ padding-top:14%; width:100%; height:230px; border-radius:50%; background:#fef2ec; font-size:38px;text-align:center;}
        .main_p{ font-family:'华文行楷';}
    </style>
</head>

<body>
<div class="main">
    <div class="main_left"><img src="/static/error/img/img2.png" width="229" height="128"/></div>
    <div class="main_right">
        <div class="main_radius">
            <p class="main_p">数据出现错误</p>
            <p class="main_p">请联系管理员修改吧！</p>
        </div>
    </div>
</div>
</body>
</html>
