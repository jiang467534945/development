<%--
  Created by IntelliJ IDEA.
  User: 榕榕
  Date: 2017/10/10
  Time: 15:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
        <meta http-equiv="Content-Type" content="multipart/form-data; charset=utf-8" />
        <style type="text/css">
            *{
                margin: 0;
                padding: 0;
            }
            #inputBox{
                margin: 0 auto;
                margin-top: 16px;
                width: 50%;
                height: 40px;
                border: 1px solid black;
                color: black/*cornflowerblue*/;
                border-radius: 20px;
                position: relative;
                text-align: center;
                line-height: 40px;
                overflow: hidden;
                font-size: 16px;
            }
            #inputBox input{
                text-align: center;
                width: 114%;
                height: 40px;
                opacity: 0;
                cursor: pointer;
                position: absolute;
                top: 0;
                left: -14%;

            }
            #imgBox{
                text-align: left;
            }
            .imgContainer{
                display: inline-block;
                width: 32%;
                height: 150px;
                margin-left: 1%;
                border: 1px solid #666666;
                position: relative;
                margin-top: 30px;
                box-sizing: border-box;
            }
            .imgContainer img{
                width: 100%;
                height: 150px;
                cursor: pointer;
            }
            .imgContainer p{
                position: absolute;
                bottom: -1px;
                left: 0;
                width: 100%;
                height: 30px;
                background: black;
                text-align: center;
                line-height: 30px;
                color: white;
                font-size: 16px;
                font-weight: bold;
                cursor: pointer;
                display: none;
            }
            .imgContainer:hover p{
                display: block;
            }
            #btn{
                outline: none;
                width: 100px;
                height: 30px;
                background: cornflowerblue;
                border: 1px solid cornflowerblue;
                color: white;
                cursor: pointer;
                margin-bottom: 30px;
                margin-top: 30px;
                border-radius: 5px;
                margin-right: 100px;
            }
            #btnDiv{
                text-align:right;
                height: 90px;
            }
        </style>
        <%--<script type="text/javascript" src="/static/js/plugins/jquery/jquery.min.js"></script>--%>
</head>
<body>
<div style="width: 100%;height: 100vh;position: relative;">
        <div id="inputBox"><input type="file" title="请选择图片" id="file" multiple accept="image/png,image/jpg,image/gif,image/JPEG"/>点击选择图片</div>
        <div id="imgBox">
        </div>
        <div id="btnDiv">
            <button id="btn">上传</button>
        </div>

</div>

<script src="/static/js/plugins/upload/uploadImg.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    imgUpload({
        inputId:'file', //input框id
        imgBox:'imgBox', //图片容器id
        buttonId:'btn', //提交按钮id
        upUrl:'/picture/insert',  //提交地址
        data:'file' //参数名
    })
</script>
</body>

</html>
