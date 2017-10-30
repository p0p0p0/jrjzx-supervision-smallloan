<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>" />
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>添加账户信息</title>

<link href="image/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="build/lib/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="build/lib/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="css/style.css">


</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>

	<div class="container clearfix pt100">
		<div class="add-form add-finance-data panel clearfix mt100">
			<h3>添加账户</h3>
			<form class="clearfix" method="post" action="account/save_account">
				<div class="form-group row">
					<label class="col-sm-2 control-label col-sm-offset-3 lh30">账号类型</label>
					<div class="col-sm-6">
						<select class="form-control" name="type">
							<option value="1">基本户</option>
							<option value="2">一般户</option>
							<option value="3">监管专用户</option>
						</select>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label col-sm-offset-3 lh30">银行名称</label>
					<div class="col-sm-6">
						<select class="form-control" name="bank">
							<c:forEach items="${bank.dataOptions }" var="option">
								<option value="${option.optionKey }" />${option.optionValue }
                    	</c:forEach>
						</select>
					</div>
				</div>


				<div class="form-group row">
					<label class="col-sm-2 control-label col-sm-offset-3 lh30">帐号</label>
					<div class="col-sm-6">
						<input class="form-control" type="text" name="account" />
					</div>
				</div>
				<div class="form-group row clearfix">
					<label class="col-sm-2 control-label col-sm-offset-3 lh30">开户日期</label>
					<div class="col-sm-6">
						<input class="form-control form_datetime" type="text"
							name="openTime" />
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label col-sm-offset-3 lh30">状态</label>
					<div class="col-sm-6">
						<select class="form-control" name="accountStatus">
							<c:forEach items="${status.dataOptions}" var="option">
								<option value="${option.optionKey }" />${option.optionValue }
                    </c:forEach>

						</select>
					</div>
				</div>

				<div class="form-group ">
					<button type="submit"
						class="btn btn-info col-sm-2 col-sm-offset-5 mt40">提交</button>
					<button type="reset"
						class="btn btn-info col-sm-2 col-sm-offset-1 mt40">取消</button>
				</div>
			</form>

		</div>

	</div>





	<script src="build/lib/jquery.min.js"></script>
	<script src="build/lib/sea.min.js"></script>
	<script src="js/base.js"></script>

	<script>
		$(function() {
			seajs.use('common', function(c) {
				c.navInit();
				c.getDayly();
			})
		})
	</script>

</body>
</html>