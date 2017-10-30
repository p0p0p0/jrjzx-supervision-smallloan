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


	<div class="container pt100">
		<form action="asset_loss/save" method="post">
			<div class="add-capital-loss-box">
				<h3>添加资产损失准备计提</h3>
				<div class="lossTable">
					<div class="selectMooth">
						<span class="pull-left">请选择月份: </span>
						<div class="month-select pull-left">
							<input type="text" class="pull-left form_monthtime"
								name="dateMonth"> <span
								class="glyphicon glyphicon-calendar calendarIcon"
								aria-hidden="true"></span>
						</div>
						<span class="glyphicon glyphicon-search pull-left search-icon"
							aria-hidden="true"> </span>
					</div>
				</div>
				<div class="capital-table">
					<div class="table-template" style="padding-bottom: 30px;">
						<div class="table-content">
							<table class="table table-hover table-bordered  cur">
								<thead class="thead-col">
									<tr>
										<th>类别\项目</th>
										<th>余额（万元）</th>
										<th>计提比例(%)</th>
										<th>应计金额（万元）</th>
										<th>实际计提金额（万元）</th>
										<th>充足率(%)</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td>正常类贷款余额</td>
										<td><input type="text" name="normalBalance" class="number"
											id="normalBalance"
											onkeyup="toSum('normalBalance', 'followBalance', 'minorBalance', 'suspiciousBalance', 'lossBalance', 'balance')" /></td>
										<td><input type="text" name="normalRate" id="normalRate" class="number"/></td>
										<td><input type="text" name="normalAccrued"
											id="normalAccrued" class="number"
											onkeyup="toSum('normalAccrued', 'followAccrued', 'minorAccrued', 'suspiciousAccrued', 'lossAccrued', 'accrued');toAdequacy('normalAccrued', 'normalReal', 'normalAdequacy')" /></td>
										<td><input type="text" name="normalReal" id="normalReal" class="number"
											onkeyup="toSum('normalReal', 'followReal', 'minorReal', 'suspiciousReal', 'lossReal', 'real');toAdequacy('normalAccrued', 'normalReal', 'normalAdequacy')" /></td>
										<td><input type="text" name="normalAdequacy" class="number"
											id="normalAdequacy" readonly="readonly" /></td>
									</tr>
									<tr>
										<td>关注类贷款余额</td>
										<td><input type="text" name="followBalance"
											id="followBalance"
											onkeyup="toSum('normalBalance', 'followBalance', 'minorBalance', 'suspiciousBalance', 'lossBalance', 'balance')" /></td>
										<td><input type="text" name="followRate" id="followRate" /></td>
										<td><input type="text" name="followAccrued"
											id="followAccrued"
											onkeyup="toSum('normalAccrued', 'followAccrued', 'minorAccrued', 'suspiciousAccrued', 'lossAccrued', 'accrued');toAdequacy('followAccrued', 'followReal', 'followAdequacy')" /></td>
										<td><input type="text" name="followReal" id="followReal"
											onkeyup="toSum('normalReal', 'followReal', 'minorReal', 'suspiciousReal', 'lossReal', 'real');toAdequacy('followAccrued', 'followReal', 'followAdequacy')" /></td>
										<td><input type="text" name="followAdequacy"
											id="followAdequacy" readonly="readonly" /></td>

									</tr>
									<tr>
										<td>次级类贷款余额</td>
										<td><input type="text" name="minorBalance"
											id="minorBalance"
											onkeyup="toSum('normalBalance', 'followBalance', 'minorBalance', 'suspiciousBalance', 'lossBalance', 'balance')" /></td>
										<td><input type="text" name="minorRate" id="minorRate" /></td>
										<td><input type="text" name="minorAccrued"
											id="minorAccrued"
											onkeyup="toSum('normalAccrued', 'followAccrued', 'minorAccrued', 'suspiciousAccrued', 'lossAccrued', 'accrued');toAdequacy('minorAccrued', 'minorReal', 'minorAdequacy')" /></td>
										<td><input type="text" name="minorReal" id="minorReal"
											onkeyup="toSum('normalReal', 'followReal', 'minorReal', 'suspiciousReal', 'lossReal', 'real');toAdequacy('minorAccrued', 'minorReal', 'minorAdequacy')" /></td>
										<td><input type="text" name="minorAdequacy"
											id="minorAdequacy" readonly="readonly" /></td>

									</tr>
									<tr>
										<td>可疑类贷款余额</td>
										<td><input type="text" name="suspiciousBalance"
											id="suspiciousBalance"
											onkeyup="toSum('normalBalance', 'followBalance', 'minorBalance', 'suspiciousBalance', 'lossBalance', 'balance')" /></td>
										<td><input type="text" name="suspiciousRate"
											id="suspiciousRate" /></td>
										<td><input type="text" name="suspiciousAccrued"
											id="suspiciousAccrued"
											onkeyup="toSum('normalAccrued', 'followAccrued', 'minorAccrued', 'suspiciousAccrued', 'lossAccrued', 'accrued');toAdequacy('suspiciousAccrued', 'suspiciousReal', 'suspiciousAdequacy')" /></td>
										<td><input type="text" name="suspiciousReal"
											id="suspiciousReal"
											onkeyup="toSum('normalReal', 'followReal', 'minorReal', 'suspiciousReal', 'lossReal', 'real');toAdequacy('suspiciousAccrued', 'suspiciousReal', 'suspiciousAdequacy')" /></td>
										<td><input type="text" name="suspiciousAdequacy"
											id="suspiciousAdequacy" readonly="readonly" /></td>

									</tr>
									<tr>
										<td>损失类贷款余额</td>
										<td><input type="text" name="lossBalance"
											id="lossBalance"
											onkeyup="toSum('normalBalance', 'followBalance', 'minorBalance', 'suspiciousBalance', 'lossBalance', 'balance')" /></td>
										<td><input type="text" name="lossRate" id="lossRate" /></td>
										<td><input type="text" name="lossAccrued"
											id="lossAccrued"
											onkeyup="toSum('normalAccrued', 'followAccrued', 'minorAccrued', 'suspiciousAccrued', 'lossAccrued', 'accrued');toAdequacy('lossAccrued', 'lossReal', 'lossAdequacy')" /></td>
										<td><input type="text" name="lossReal" id="lossReal"
											onkeyup="toSum('normalReal', 'followReal', 'minorReal', 'suspiciousReal', 'lossReal', 'real');toAdequacy('lossAccrued', 'lossReal', 'lossAdequacy')" /></td>
										<td><input type="text" name="lossAdequacy"
											id="lossAdequacy" readonly="readonly" /></td>

									</tr>
									<tr>
										<td>合计</td>
										<td><input type="text" name="totalBalance" id="balance"
											readonly="readonly" /></td>
										<td><input type="text" readonly="readonly" /></td>
										<td><input type="text" name="totalAccrued" id="accrued"
											readonly="readonly" /></td>
										<td><input type="text" name="totalReal" id="real"
											readonly="readonly" /></td>
										<td><input type="text" name="totalAdequacy"
											readonly="readonly" id="adequacy" /></td>
									</tr>
								</tbody>
							</table>

						</div>


					</div>
					<div class="form-group">
						<button type="submit"
							class="btn btn-info col-sm-1 col-sm-offset-4">提交</button>
						<button type="reset" class="btn btn-info col-sm-1 col-sm-offset-1">取消</button>
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
			c.format();
		});
		function toSum(normal, follow, minor, suspicious, loss, target) {
			$("#" + target).val(
					parseFloat($("#" + normal).val() == "" ? 0
							: $("#" + normal).val())
							+ parseFloat($("#" + follow).val() == "" ? 0 : $(
									"#" + follow).val())
							+ parseFloat($("#" + minor).val() == "" ? 0 : $(
									"#" + minor).val())
							+ parseFloat($("#" + suspicious).val() == "" ? 0
									: $("#" + suspicious).val())
							+ parseFloat($("#" + loss).val() == "" ? 0 : $(
									"#" + loss).val()));
		}
		function toAdequacy(accrued, real, target) {
			var tem = parseFloat($("#" + real).val() == "" ? 0 : $("#" + real)
					.val())
					/ parseFloat($("#" + accrued).val() == "" ? 0 : $(
							"#" + accrued).val());
			$("#" + target).val(tem.toFixed(4) * 100);
			var tem1 = parseFloat($("#real").val() == "" ? 0 : $("#real").val())
					/ parseFloat($("#accrued").val() == "" ? 0
							: $("#accrued").val());
			$("#adequacy").val(tem1.toFixed(4) * 100);
		}
	</script>

</body>
</html>