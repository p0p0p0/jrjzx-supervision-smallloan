<!DOCTYPE html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<script>
    var contextPath = '${pageContext.request.contextPath}';
</script>
<html lang="en">
<head>
<base href="<%=basePath%>" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="image/favicon.ico" rel="shortcut icon" type="/image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="build/lib/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="build/lib/bootstrap-datepicker/bootstrap-datetimepicker.min.css" />
<script src="build/lib/jquery.min.js"></script>
<script src="build/lib/bootstrap/bootstrap.min.js"></script>
<script src="js/bootbox/bootbox.min.js"></script>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
<input type="hidden" name="contextPath" value="${pageContext.request.contextPath}" />
<c:set var="contextPath" value="${pageContext.request.contextPath}" scope="application"/>  
	<nav class="navbar navbar-default navbar-fixed-top">
		<div class="login-info clearfix">
			<div class="container">
				<span class="pull-left get-date"> </span>
				<div class="pull-right">
					<span class="pull-right navbar-collapse">帮助中心</span>
					<div class="pull-right">
						您好<a href="#"> ${sessionScope.loginUser.username}</a> <span>|</span>
						<a
						href="javascript:void(0);" onclick="javascript:bootbox.confirm('你确定要退出当前用户吗?',function(e){if(e)window.location='user/logout'})">退出</a>
					</div>

				</div>
			</div>
		</div>
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<div class="navbar-header logo">
					<a href="" class="pull-left"></a> <span class="pull-left"
						style="cursor: pointer;" onclick="javascript:window.location='';">金融街在线监控平台</span>
				</div>
			</div>
			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<div id="menus">
					<ul class="nav navbar-nav pull-right">
						<li><a href="page/index.html">主页 </a></li>
						<li><a href="company/info.html">基础数据</a></li>
						<li><a href="finance/add_finance">财务数据</a></li>
						<li><a href="loanContract/list.html">合同数据</a></li>
						 <li><a href="javascript:;">合同转让</a></li>
						<li><a href="page/financing/financing.html">融资数据</a></li>
						<li><a href="account/info">账户数据</a></li>					
						<li><a href="asset_loss/info">计提数据</a></li>
						<li><a href="page/contract-loss/contract-loss.html">报损数据</a></li>
						<li><a href="javascript:;" class="pr0">诉求数据</a></li>

					</ul>
				</div>

			</div>
		</div>
	</nav>
	<script>
		$(function() {
			var path = location.pathname;
			var currentPath = path.replace(path.split("/")[1],"").substring(2);
			$("#menus").find("li > a").each(function() {
				if ($(this).attr("href") == currentPath) {
					$(this).parent().addClass("active");
				} else {
					$(this).parent().removeClass("active");
				}
			})
		})
	</script>
</body>

</html>