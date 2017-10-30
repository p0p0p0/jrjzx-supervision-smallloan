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
<title>资产损失准备计提</title>


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


	<div class="container pt100" >
		<form action="asset_loss/info" method="post">
			<div class="capital-loss-box">

				<div class="lossTable">
					<div class="selectMooth">
						<span class="pull-left">请选择月份: </span>
						<div class="month-select pull-left">
							<input type="text" class="pull-left monthly form_monthtime"
								name="dateMonth" value="${dateMonth }"> <span
								class="glyphicon glyphicon-calendar calendarIcon"
								aria-hidden="true"></span>
						</div>
						<button class="glyphicon glyphicon-search pull-left search-icon"
							type="submit" aria-hidden="true"></button>
					</div>
				</div>

				<div class="capital-table">
					<div class="account-table">
						<div class="table-template">
							<div class="table-info">
								<h4 class="table-title pull-left">账户信息</h4>
							</div>

							<div class="addSpan">
								<a href="asset_loss/add" class="btn btn-primary pull-right add-btn">添加</a>
							</div>
							<div class="table-content">
								<table class="table table-hover  cur"
									style="text-align: center; border: 1px solid #ddd;">
									<thead>
										<tr>
											<th>类别\项目</th>
											<th>余额（万元）</th>
											<th>计提比例(%)</th>
											<th>应计金额（万元）</th>
											<th>实际计提金额（万元）</th>
											<th>充足率</th>
										</tr>
									</thead>
									<tbody>
										<tr>
											<td>正常类贷款余额</td>
											<td>${asset.normalBalance }</td>
											<td>${asset.normalRate }</td>
											<td>${asset.normalAccrued }</td>
											<td>${asset.normalReal }</td>
											<td>${asset.normalAdequacy }</td>
										</tr>
										<tr>
											<td>关注类贷款余额</td>
											<td>${asset.followBalance }</td>
											<td>${asset.followRate }</td>
											<td>${asset.followAccrued }</td>
											<td>${asset.followReal }</td>
											<td>${asset.followAdequacy }</td>
										</tr>
										<tr>
											<td>次级类贷款余额</td>
											<td>${asset.minorBalance }</td>
											<td>${asset.minorRate }</td>
											<td>${asset.minorAccrued }</td>
											<td>${asset.minorReal }</td>
											<td>${asset.minorAdequacy }</td>
										</tr>
										<tr>
											<td>可疑类贷款余额</td>
											<td>${asset.suspiciousBalance }</td>
											<td>${asset.suspiciousRate }</td>
											<td>${asset.suspiciousAccrued }</td>
											<td>${asset.suspiciousReal }</td>
											<td>${asset.suspiciousAdequacy }</td>
										</tr>
										<tr>
											<td>损失类贷款余额</td>
											<td>${asset.lossBalance }</td>
											<td>${asset.lossRate }</td>
											<td>${asset.lossAccrued }</td>
											<td>${asset.lossReal }</td>
											<td>${asset.lossAdequacy }</td>
										</tr>
										<tr>
											<td>合计</td>
											<td>${asset.totalBalance }</td>
											<td></td>
											<td>${asset.totalAccrued }</td>
											<td>${asset.totalReal }</td>
											<td>${asset.totalAdequacy }</td>
										</tr>


									</tbody>
								</table>
							</div>
						

						</div>
					</div>
				</div>
			</div>
		</form>

	</div>




	<script src="build/lib/sea.min.js"></script>
	<script src="js/base.js"></script>

	<script>
		seajs.use('common', function(c) {
			c.navInit();
			c.getMonthly();
		})
	</script>

</body>
</html>