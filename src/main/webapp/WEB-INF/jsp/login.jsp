<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="zh-cn" style="width: 100%;height: 100%;">
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script>
    var contextPath = '${pageContext.request.contextPath}';
</script>
<base href="<%=basePath%>" />
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>小贷登录</title>
    <link href="build/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="image/favicon.ico" rel="shortcut icon" type="/image/x-icon"/>
    <link rel="stylesheet" href="css/style.css">
</head>

<body class="login-body">
<div class="login-box">
    <h3 class="page-info">登录</h3>
    <div class="login-content">
    ${error}
        <form action="user/login" class="form-horizontal" method="post" id="login-form">
            <div class="form-group">
                <span class="pull-left">用户名:</span>
                <input id="account" value="admin" class="form-control pull-left" type="text" placeholder="请输入用户名" name="username" autofocus/>
            </div>
            <div class="form-group">
                <span class="pull-left"> 密码：</span>
                <input id="password" value="123456" class="form-control pull-left" type="password" placeholder="请输入密码"
                       name="password"/>
            </div>
            <div class="form-group chaImage-group">
                <span class="pull-left"> 验证码：</span>
                <input  class="form-control " name="chaImage" data-required-error="验证码不能为空" type="text" placeholder="请输入验证码,不区分大小写" required/>
                <!--<img class="chaImage" alt="点击换一张"  title="点击换一张" src="/checkCode">            -->
                <img class="chaImage" alt="点击换一张"  title="点击换一张" src="checkCode">
            </div>

            <div class="form-group operate">
                <button type="submit" id="login"   class="btn btn-primary login-btn">登&nbsp;录</button>
            </div>
            <div class="modify-pwd pull-right change-panle">
                <a href="javascript:;" >修改密码</a>
            </div>
        </form>
    </div>
</div>

<div class="modify-pwd-box">
    <h3 class="page-info">修改密码</h3>
    <div class="login-content">
        <form class="form-horizontal" id="modify-form">
            <div class="form-group">
                <span class="pull-left">原密码:</span>
                <input id="original-password" class="form-control pull-left" type="text" placeholder="输入原密码" name="originalPwd"
                       autofocus="autofocus" required/>
            </div>
            <div class="form-group">
                <span class="pull-left"> 新密码：</span>
                <input id="new-password" class="form-control pull-left" type="password" placeholder="输入新密码"
                       name="newPwd" required/>
            </div>
            <div class="form-group">
                <span class="pull-left"> 密码确认：</span>
                <input id="confirm-password" class="form-control pull-left" type="password" placeholder="新密码确认"
                       name="confirmPwd" required/>
            </div>
            <div class="form-group chaImage-group">
                <span class="pull-left"> 验证码：</span>
                <input class="form-control " name="chaImage" type="text"   placeholder="请输入验证码,不区分大小写"/>
                <img class="chaImage pull-left" src="build/img/captchaImage.jpg">
            </div>


            <div class="form-group operate">
                <button type="submit" class="btn btn-primary login-btn">完 &nbsp;成</button>
            </div>
            <div class="return-login pull-right ">
                <a href="javascript:;">返回登录</a>
            </div>
        </form>
    </div>
</div>



<script src="build/lib/jquery.min.js"></script>
<script src="build/lib/sea.min.js"></script>
<script src="js/base.js"></script>

<script>
    $(function () {
    	  seajs.use('user',function (u) {
              u.login();u.modifyPwd();
          })
        
        $(".chaImage").each(function(){
        	$(this).on("click",function(){
        		$(this).attr("src","checkCode?ran="+new Date().getTime());
        	})
        });
        $(".modify-pwd").on("click",function(){
    		$(".modify-pwd-box").find("img").attr("src","checkCode?ran="+new Date().getTime());
    	});
        $(".return-login").on("click",function(){
    		$(".login-box").find("img").attr("src","checkCode?ran="+new Date().getTime());
    	});
        
        

        
        
        
    })
</script>
</body>
</html>
