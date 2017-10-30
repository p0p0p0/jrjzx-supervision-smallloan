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
<title>资产负债表</title>
<link rel="stylesheet" href="css/style.css">
<link href="image/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="build/lib/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="build/lib/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>

	<div class="container mt100">
		<div class="table-template">
			<div class="table-info">
				<h4 class="table-title pull-left">利润表</h4>


			</div>
			<div class="table-content">
				<table class="table table-hover table-bordered ">
					<thead>
						<tr class="tint-col">
							<th>编制单位:</th>
							<th>${company.name }</th>
							<th>${dateMonth }</th>
							<th>金额单位：人民币元</th>
						</tr>
						<tr class="label-info">
							<th>项目</th>
							<th>行次</th>
							<th>本期金额</th>
							<th>本年累计数</th>
						</tr>
					</thead>
					<c:if test="${!empty profit }">
						<tbody>
							<tr>
								<td class="text-left">一、营业收入</td>
								<td>1</td>
								<td>${profit.businessIncome }</td>
								<td>${profit.yearBusinessIncome }</td>
							</tr>
							<tr>
								<td class="text-left">利息收入</td>
								<td>2</td>
								<td>${profit.interestIncome }</td>
								<td>${profit.yearInterestIncome }</td>
							</tr>
							<tr>
								<td class="text-left">金融机构往来利息收入</td>
								<td>3</td>
								<td>${profit.financialInterestIncome }</td>
								<td>${profit.yearFinancialInterestIncome }</td>
							</tr>
							<tr>
								<td class="text-left">手续费收入</td>
								<td>4</td>
								<td>${profit.feeIncome }</td>
								<td>${profit.yearFeeIncome }</td>
							</tr>
							<tr>
								<td class="text-left">公允价值变动收益（损失用“-"号填列）</td>
								<td>5</td>
								<td>${profit.changeIncome }</td>
								<td>${profit.yearChangeIncome }</td>
							</tr>
							<tr>
								<td class="text-left">汇兑收益</td>
								<td>6</td>
								<td>${profit.aggregateIncome }</td>
								<td>${profit.yearAggregateIncome }</td>
							</tr>
							<tr>
								<td class="text-left">其他营业收入</td>
								<td>7</td>
								<td>${profit.otherIncome }</td>
								<td>${profit.yearOtherIncome }</td>
							</tr>
							<tr>
								<td class="text-left">二、营业支出</td>
								<td>8</td>
								<td>${profit.businessExpenses }</td>
								<td>${profit.yearBusinessExpenses }</td>
							</tr>
							<tr>
								<td class="text-left">利息支出</td>
								<td>9</td>
								<td>${profit.interestExpense }</td>
								<td>${profit.yearInterestExpense }</td>
							</tr>

							<tr>
								<td class="text-left">金融机构往来利息支出</td>
								<td>10</td>
								<td>${profit.financialInterestExpense }</td>
								<td>${profit.yearFinancialInterestExpense }</td>
							</tr>
							<tr>
								<td class="text-left">手续费支出</td>
								<td>11</td>
								<td>${profit.feeExpense }</td>
								<td>${profit.yearFeeExpense }</td>
							</tr>
							<tr>
								<td class="text-left">业务及管理费</td>
								<td>12</td>
								<td>${profit.managementFees }</td>
								<td>${profit.yearManagementFees }</td>
							</tr>
							<tr>
								<td class="text-left">汇兑损失</td>
								<td>13</td>
								<td>${profit.exchangeLoss }</td>
								<td>${profit.yearExchangeLoss }</td>
							</tr>
							<tr>
								<td class="text-left">资产减值损失</td>
								<td>14</td>
								<td>${profit.assetsLoss }</td>
								<td>${profit.yearAssetsLoss }</td>
							</tr>
							<tr>
								<td class="text-left">其他营业支出</td>
								<td>15</td>
								<td>${profit.otherExpenses }</td>
								<td>${profit.yearOtherExpenses }</td>
							</tr>
							<tr>
								<td class="text-left">三、营业税金及附加</td>
								<td>16</td>
								<td>${profit.businessTax }</td>
								<td>${profit.yearBusinessTax }</td>
							</tr>

							<tr>
								<td class="text-left">四、营业利润</td>
								<td>17</td>
								<td>${profit.operatingIncome }</td>
								<td>${profit.yearOperatingIncome }</td>
							</tr>
							<tr>
								<td class="text-left">加：投资收益</td>
								<td>18</td>
								<td>${profit.investmentIncome }</td>
								<td>${profit.yearInvestmentIncome }</td>
							</tr>
							<tr>
								<td class="text-left">加：营业外收入</td>
								<td>19</td>
								<td>${profit.nonOperatingIncome }</td>
								<td>${profit.yearNonOperatingIncome }</td>
							</tr>
							<tr>
								<td class="text-left">减：营业外支出</td>
								<td>20</td>
								<td>${profit.nonOperatingPayment }</td>
								<td>${profit.yearNonOperatingPayment }</td>
							</tr>
							<tr>
								<td class="text-left">五、利润总额</td>
								<td>21</td>
								<td>${profit.totalProfit }</td>
								<td>${profit.yearTotalProfit }</td>
							</tr>
							<tr>
								<td class="text-left">减：所得税</td>
								<td>22</td>
								<td>${profit.incomeTax }</td>
								<td>${profit.yearIncomeTax }</td>
							</tr>
							<tr>
								<td class="text-left">六、净利润</td>
								<td>23</td>
								<td>${profit.netProfit }</td>
								<td>${profit.yearNetProfit }</td>
							</tr>

							<tr>
								<td class="text-left">单位负责：</td>
								<td class="text-left">${profit.leader }</td>
								<td class="text-left">填报人：</td>
								<td class="text-left">${profit.informant }</td>
							</tr>
							<tr>
								<td></td>
								<td></td>
								<td class="text-left">报出日期：</td>
								<td class="text-left">${profit.reportDate }</td>
							</tr>
							<!--                     <tr> -->
							<!--                         <td rowspan="3">填报说明:</td> -->
							<!--                         <td colspan="3"  class="text-left">1．本表由各小额贷款公司填报。</td> -->
							<!--                     </tr> -->
							<!--                     <tr> -->
							<!--                         <td colspan="3" class="text-left">2．本文件中辖区指报送部门所在行政区划。本表报送范围为在辖区内登记注册的小额贷款公司 -->
							<!--                             （含辖区外小额贷款公司法人机构在本辖区设立的分支机构，但不含本辖区小额贷款公司法人机构在辖区外设立的分支机构）。</td> -->
							<!--                     </tr> -->
							<!--                     <tr> -->
							<!--                         <td colspan="3"  class="text-left">3. 报表填列的金额精确到分。</td> -->
							<!--                     </tr> -->



						</tbody>
					</c:if>
				</table>
			</div>

		</div>
	</div>
	<script src="build/lib/jquery.min.js"></script>
	<script src="build/lib/sea.min.js"></script>
	<script src="js/base.js"></script>
	<script>
		$(function() {
			   seajs.use('common',function (c) {
				      c.navInit();
				});
		});
	</script>
</body>
</html>