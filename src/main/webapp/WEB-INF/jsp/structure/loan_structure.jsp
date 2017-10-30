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
<title>贷款结构</title>

<link href="image/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="build/lib/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="build/lib/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>
	
	<div class="container pt100" style="padding-top:100px">
		<div class="loan-box ">
			<form action="structure/loan_structure" method="get">
				<div class="lossTable">
					<div class="selectMooth">
						<span class="pull-left">请选择月份: </span>
						<div class="month-select pull-left">
							<input type="text" class="pull-left form_monthtime"
								value='<fmt:formatDate value="${dateMonth }" type="both" pattern="yyyy-MM" />'
								name="dateMonth"> <span
								class="glyphicon glyphicon-calendar calendarIcon"
								aria-hidden="true" style="top: 5px"></span>
						</div>
						<button class="glyphicon glyphicon-search pull-left search-icon"
							aria-hidden="true" type="submit"></button>
					</div>
			</form>
		</div>

		<div class="loan-table">
			<h3>
				<fmt:formatDate value="${dateMonth }" type="both" pattern="yyyy年MM月" />
				贷款结构信息
			</h3>
			<table class="table table-hover table-bordered ">
				<thead class="thead-col">
					<tr>
						<th>结构\单位</th>
						<th>贷款余额(万元)</th>
						<th>本年累计投放笔数</th>
						<th>本年累计投放金额(万元)</th>
						<th>本年累计投放占比(%)</th>
						<th>本月累计投放笔数</th>
						<th>本月累计投放金额(万元)</th>
						<th>本月累计投放占比(%)</th>
					</tr>
				</thead>
				<c:if test="${!empty dateMonth }">
					<tbody>
						<!--借贷主体-->
						<tr>
							<td colspan="8" class="main-content">借贷主体</td>
						</tr>
						<c:forEach items="${list1}" var="entity1" varStatus="sta">
							<tr>
								<td align="left"><c:choose>
										<c:when test="${entity1.dataType==1 }">
											1、个人贷款
										</c:when>
										<c:when test="${entity1.dataType==2 }">
											 &nbsp;&nbsp;&nbsp;&nbsp;其中农牧户贷款
										</c:when>
										<c:when test="${entity1.dataType==3 }">
											2、个体工商户贷款
										</c:when>
										<c:when test="${entity1.dataType==4 }">
											3、农村专业合作组织贷款
										</c:when>
										<c:when test="${entity1.dataType==5 }">
											4、微型企业贷款
										</c:when>
										<c:when test="${entity1.dataType==6 }">
											5、小型企业贷款
										</c:when>
										<c:when test="${entity1.dataType==7 }">
											6、中型及以上企业贷款
										</c:when>
										<c:when test="${entity1.dataType==8 }">
											7、其他组织贷款
										</c:when>
									</c:choose></td>
								<td><fmt:formatNumber type="number"
										value="${entity1.balance }" maxFractionDigits="2" /></td>
								<td>${entity1.yearCount }</td>
								<td><fmt:formatNumber type="number"
										value="${entity1.yearMoney }" maxFractionDigits="2" /></td>
								<td>${entity1.yearRatio }</td>
								<td>${entity1.monthCount }</td>
								<td><fmt:formatNumber type="number"
										value="${entity1.monthMoney }" maxFractionDigits="2" /></td>
								<td>${entity1.monthRatio }</td>
							</tr>
						</c:forEach>
						<tr>
							<td style="width: 130px">合计</td>
							<td><fmt:formatNumber type="number"
									value="${info1.balance }" maxFractionDigits="2" /></td>
							<td>${info1.yearCount}</td>
							<td><fmt:formatNumber type="number"
									value="${info1.yearMoney}" maxFractionDigits="2" /></td>
							<td>100</td>
							<td>${info1.monthCount}</td>
							<td><fmt:formatNumber type="number"
									value="${info1.monthMoney}" maxFractionDigits="2" /></td>
							<td>100</td>
						</tr>

						<!--借款额度-->
						<tr>
							<td colspan="8" class="main-content">借款额度</td>
						</tr>
						<c:forEach items="${list2}" var="entity1" varStatus="sta">
							<tr>
								<td align="left"><c:choose>
										<c:when test="${entity1.dataType==1 }">
											5万及以下
										</c:when>
										<c:when test="${entity1.dataType==2 }">
											 5万-10万（含） 	
										</c:when>
										<c:when test="${entity1.dataType==3 }">
											10万-50万（含）
										</c:when>
										<c:when test="${entity1.dataType==4 }">
											50万-100万（含）
										</c:when>
										<c:when test="${entity1.dataType==5 }">
											100万以上
										</c:when>
									</c:choose></td>
								<td><fmt:formatNumber type="number"
										value="${entity1.balance }" maxFractionDigits="2" /></td>
								<td>${entity1.yearCount }</td>
								<td><fmt:formatNumber type="number"
										value="${entity1.yearMoney }" maxFractionDigits="2" /></td>
								<td>${entity1.yearRatio }</td>
								<td>${entity1.monthCount }</td>
								<td><fmt:formatNumber type="number"
										value="${entity1.monthMoney }" maxFractionDigits="2" /></td>
								<td>${entity1.monthRatio }</td>
							</tr>
						</c:forEach>

						<tr>
							<td style="width: 130px">合计</td>
							<td><fmt:formatNumber type="number"
									value="${info2.balance }" maxFractionDigits="2" /></td>
							<td>${info2.yearCount}</td>
							<td><fmt:formatNumber type="number"
									value="${info2.yearMoney}" maxFractionDigits="2" /></td>
							<td>100</td>
							<td>${info2.monthCount}</td>
							<td><fmt:formatNumber type="number"
									value="${info2.monthMoney}" maxFractionDigits="2" /></td>
							<td>100</td>
						</tr>

						<!--借款行业-->
						<tr>
							<td colspan="8" class="main-content">借款行业</td>
						</tr>
						<c:forEach items="${list3}" var="entity1" varStatus="sta">
							<tr>
								<td align="left"><c:choose>
										<c:when test="${entity1.dataType==1 }">
											1、农、林、牧、渔业贷款
										</c:when>
										<c:when test="${entity1.dataType==2 }">
											2、采矿业贷款
										</c:when>
										<c:when test="${entity1.dataType==3 }">
											3、制造业贷款
										</c:when>
										<c:when test="${entity1.dataType==4 }">
											4、电力、燃气及水的生产和供应业贷款
										</c:when>
										<c:when test="${entity1.dataType==5 }">
											5、建筑业贷款
										</c:when>
										<c:when test="${entity1.dataType==6 }">
											6、交通运输、仓储和邮政业贷款
										</c:when>
										<c:when test="${entity1.dataType==7 }">
											7、信息传输、计算机服务和软件业贷款
										</c:when>
										<c:when test="${entity1.dataType==8 }">
											8、批发和零售业贷款
										</c:when>
										<c:when test="${entity1.dataType==9 }">
											9、住宿和餐饮业贷款
										</c:when>
										<c:when test="${entity1.dataType==10 }">
											10、房地产业贷款
										</c:when>
										<c:when test="${entity1.dataType==11 }">
											11、租赁和商务服务业贷款
										</c:when>
										<c:when test="${entity1.dataType==12 }">
											12、居民服务和其他服务业贷款
										</c:when>
										<c:when test="${entity1.dataType==13 }">
											13、其他贷款
										</c:when>
									</c:choose></td>
								<td><fmt:formatNumber type="number"
										value="${entity1.balance }" maxFractionDigits="2" /></td>
								<td>${entity1.yearCount }</td>
								<td><fmt:formatNumber type="number"
										value="${entity1.yearMoney }" maxFractionDigits="2" /></td>
								<td>${entity1.yearRatio }</td>
								<td>${entity1.monthCount }</td>
								<td><fmt:formatNumber type="number"
										value="${entity1.monthMoney }" maxFractionDigits="2" /></td>
								<td>${entity1.monthRatio }</td>
							</tr>
						</c:forEach>
						<tr>
							<td style="width: 130px">合计</td>
							<td><fmt:formatNumber type="number"
									value="${info3.balance }" maxFractionDigits="2" /></td>
							<td>${info3.yearCount}</td>
							<td><fmt:formatNumber type="number"
									value="${info3.yearMoney}" maxFractionDigits="2" /></td>
							<td>100</td>
							<td>${info3.monthCount}</td>
							<td><fmt:formatNumber type="number"
									value="${info3.monthMoney}" maxFractionDigits="2" /></td>
							<td>100</td>
						</tr>
						<!--借款期限-->
						<tr>
							<td colspan="8" class="main-content">借款期限</td>
						</tr>
						<c:forEach items="${list4}" var="entity1" varStatus="sta">
							<tr>
								<td align="left"><c:choose>
										<c:when test="${entity1.dataType==1 }">
											1、3个月（含）以内
										</c:when>
										<c:when test="${entity1.dataType==2 }">
											 2、3-6个月（含） 	
										</c:when>
										<c:when test="${entity1.dataType==3 }">
											3、6-12个月（含）
										</c:when>
										<c:when test="${entity1.dataType==4 }">
											4、12月以上
										</c:when>
									</c:choose></td>
								<td><fmt:formatNumber type="number"
										value="${entity1.balance }" maxFractionDigits="2" /></td>
								<td>${entity1.yearCount }</td>
								<td><fmt:formatNumber type="number"
										value="${entity1.yearMoney }" maxFractionDigits="2" /></td>
								<td>${entity1.yearRatio }</td>
								<td>${entity1.monthCount }</td>
								<td><fmt:formatNumber type="number"
										value="${entity1.monthMoney }" maxFractionDigits="2" /></td>
								<td>${entity1.monthRatio }</td>
							</tr>
						</c:forEach>

						<tr>
							<td style="width: 130px">合计</td>
							<td><fmt:formatNumber type="number"
									value="${info4.balance }" maxFractionDigits="2" /></td>
							<td>${info4.yearCount}</td>
							<td><fmt:formatNumber type="number"
									value="${info4.yearMoney}" maxFractionDigits="2" /></td>
							<td>100</td>
							<td>${info4.monthCount}</td>
							<td><fmt:formatNumber type="number"
									value="${info4.monthMoney}" maxFractionDigits="2" /></td>
							<td>100</td>
						</tr>
						<!--担保方式-->
						<tr>
							<td colspan="8" class="main-content">担保方式</td>
						</tr>
						<c:forEach items="${list5}" var="entity1" varStatus="sta">
							<tr>
								<td align="left"><c:choose>
										<c:when test="${entity1.dataType==1 }">
											1、信用贷款
										</c:when>
										<c:when test="${entity1.dataType==2 }">
											2、保证贷款
										</c:when>
										<c:when test="${entity1.dataType==3 }">
											 &nbsp;&nbsp;&nbsp;&nbsp;其中：多户联保贷款
										</c:when>
										<c:when test="${entity1.dataType==4 }">
											3、抵押贷款
										</c:when>
										<c:when test="${entity1.dataType==5 }">
											4、质押贷款
										</c:when>
										<c:when test="${entity1.dataType==6 }">
											5、留置贷款
										</c:when>
										<c:when test="${entity1.dataType==7 }">
											6、其他方式贷款
										</c:when>
									</c:choose></td>
								<td><fmt:formatNumber type="number"
										value="${entity1.balance }" maxFractionDigits="2" /></td>
								<td>${entity1.yearCount }</td>
								<td><fmt:formatNumber type="number"
										value="${entity1.yearMoney }" maxFractionDigits="2" /></td>
								<td>${entity1.yearRatio }</td>
								<td>${entity1.monthCount }</td>
								<td><fmt:formatNumber type="number"
										value="${entity1.monthMoney }" maxFractionDigits="2" /></td>
								<td>${entity1.monthRatio }</td>
							</tr>
						</c:forEach>

						<tr>
							<td style="width: 130px">合计</td>
							<td><fmt:formatNumber type="number"
									value="${info5.balance }" maxFractionDigits="2" /></td>
							<td>${info5.yearCount}</td>
							<td><fmt:formatNumber type="number"
									value="${info5.yearMoney}" maxFractionDigits="2" /></td>
							<td>100</td>
							<td>${info5.monthCount}</td>
							<td><fmt:formatNumber type="number"
									value="${info5.monthMoney}" maxFractionDigits="2" /></td>
							<td>100</td>
						</tr>
						<!--借款形态-->
						<tr>
							<td colspan="8" class="main-content">借款形态</td>
						</tr>
						<c:forEach items="${list6}" var="entity1" varStatus="sta">
							<tr>
								<td align="left"><c:choose>
										<c:when test="${entity1.dataType==1 }">
											1、正常贷款
										</c:when>
										<c:when test="${entity1.dataType==2 }">
											2、逾期1～30天
										</c:when>
										<c:when test="${entity1.dataType==3 }">
											3、逾期31～60天
										</c:when>
										<c:when test="${entity1.dataType==4 }">
											4、逾期61～90天
										</c:when>
										<c:when test="${entity1.dataType==5 }">
											5、逾期>90天
										</c:when>
									</c:choose></td>
								<td><fmt:formatNumber type="number"
										value="${entity1.balance }" maxFractionDigits="2" /></td>
								<td>${entity1.yearCount }</td>
								<td><fmt:formatNumber type="number"
										value="${entity1.yearMoney }" maxFractionDigits="2" /></td>
								<td>${entity1.yearRatio }</td>
								<td>${entity1.monthCount }</td>
								<td><fmt:formatNumber type="number"
										value="${entity1.monthMoney }" maxFractionDigits="2" /></td>
								<td>${entity1.monthRatio }</td>
							</tr>
						</c:forEach>

						<tr>
							<td style="width: 130px">合计</td>
							<td><fmt:formatNumber type="number"
									value="${info6.balance }" maxFractionDigits="2" /></td>
							<td>${info6.yearCount}</td>
							<td><fmt:formatNumber type="number"
									value="${info6.yearMoney}" maxFractionDigits="2" /></td>
							<td>100</td>
							<td>${info6.monthCount}</td>
							<td><fmt:formatNumber type="number"
									value="${info6.monthMoney}" maxFractionDigits="2" /></td>
							<td>100</td>
						</tr>
						<!--借款利率-->
						<tr>
							<td colspan="8" class="main-content">借款利率</td>
						</tr>
						<c:forEach items="${list7}" var="entity1" varStatus="sta">
							<tr>
								<td align="left"><c:choose>
										<c:when test="${entity1.dataType==1 }">
											1、年利率低于10%（含）
										</c:when>
										<c:when test="${entity1.dataType==2 }">
											2、年利率介于10%-15%（含）
										</c:when>
										<c:when test="${entity1.dataType==3 }">
											3、年利率介于15%-20%（含）
										</c:when>
										<c:when test="${entity1.dataType==4 }">
											4、年利率介于20%-25%（含）
										</c:when>
										<c:when test="${entity1.dataType==5 }">
											5、年利率高于25%
										</c:when>
									</c:choose></td>
								<td><fmt:formatNumber type="number"
										value="${entity1.balance }" maxFractionDigits="2" /></td>
								<td>${entity1.yearCount }</td>
								<td><fmt:formatNumber type="number"
										value="${entity1.yearMoney }" maxFractionDigits="2" /></td>
								<td>${entity1.yearRatio }</td>
								<td>${entity1.monthCount }</td>
								<td><fmt:formatNumber type="number"
										value="${entity1.monthMoney }" maxFractionDigits="2" /></td>
								<td>${entity1.monthRatio }</td>
							</tr>
						</c:forEach>

						<tr>
							<td style="width: 130px">合计</td>
							<td><fmt:formatNumber type="number"
									value="${info7.balance }" maxFractionDigits="2" /></td>
							<td>${info7.yearCount}</td>
							<td><fmt:formatNumber type="number"
									value="${info7.yearMoney}" maxFractionDigits="2" /></td>
							<td>100</td>
							<td>${info7.monthCount}</td>
							<td><fmt:formatNumber type="number"
									value="${info7.monthMoney}" maxFractionDigits="2" /></td>
							<td>100</td>
						</tr>

					</tbody>
				</c:if>
			</table>

		</div>

	</div>

	</div>



	<script src="build/lib/sea.min.js"></script>
	<script src="js/base.js"></script>
	<script>
		$(function() {
			seajs.use('common', function(c) {
				c.navInit();
				c.getMonthly();
			})
		});
	</script>
</body>
</html>