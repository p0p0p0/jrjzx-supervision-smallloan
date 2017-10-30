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
<title>账户余额报备</title>

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

	<div class="container pt100">
		<div class="add-form add-finance-data panel">
			<h3>增加银行账户余额报备</h3>
			<form method="post" action="account/save_balance">

				<div class="form-group row">
					<label class="col-sm-2 control-label col-sm-offset-3 lh30">所属月份</label>
					<div class="col-sm-6">
						<input type="text" class="form-control form_monthtime"
							name="dateMonth" />
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label col-sm-offset-3 lh30">账户</label>
					<div class="col-sm-6">
						<select class="form-control" name="accountId">
							<c:forEach items="${accounts }" var="account">
								<option value="${account.id }" />${account.account }(${account.bankOption.optionValue })
						</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group row">
					<label class="col-sm-2 control-label col-sm-offset-3 lh30">当前余额</label>
					<div class="input-group col-sm-6" style="padding:0 15px;">
						<input type="text" class="form-control" name="money" /> <span
							class="input-group-addon">元</span>
					</div>
				</div>



				<div class="form-group ">
					<button type="submit"
						class="btn btn-info col-sm-2 col-sm-offset-4 mt40">提交</button>
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
				c.getMonthly();
			})
		})
	</script>

</body>
</html>