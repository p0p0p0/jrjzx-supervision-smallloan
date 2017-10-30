<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-cn">
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
<title>合同查看</title>
<link href="build/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="build/lib/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="css/style.css">
<link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />

</head>
<jsp:include page="/WEB-INF/jsp/common/top.jsp" />

<div class="container pt100">
	<div class="contract-view">
		<div class="contract-view-header">
			<h2 class="text-center">贷款合同</h2>
			<p class="text-center">${loanContract.contractNumber }</p>
			<input type="hidden" id="contractId" name="contractId"
				value="${loanContract.id }" />
		</div>
		<ul>
			<li class="active">合同基本信息</li>
			<li>实际还款信息</li>
			<li>展期还款信息</li>
			<li>合同放款信息</li>
			<li>借款人信息</li>
			<li>保证合同信息</li>
			<li>抵押合同信息</li>
			<li>质押合同信息</li>
		</ul>
		<div class="contract-content">
			<div class="contract-view-table active">
				<div class="table-template">
					<div class="table-content">
						<table class="table table-hover  cur">
							<tbody>
								<tr>
									<td>借款人</td>
									<td>${loanContract.borrowerName }</td>
									<td>贷款合同编号</td>
									<td>${loanContract.contractNumber }</td>
								</tr>
								<tr>
									<td>贷款开始日期</td>
									<td>${loanContract.startDate }</td>
									<td>贷款截止日期</td>
									<td>${loanContract.endDate }</td>
								</tr>
								<tr>
									<td>贷款金额</td>
									<td>${loanContract.money }元</td>
									<td>贷款期限类型</td>
									<td><c:if test="${loanContract.termType == 1 }">日</c:if> <c:if
											test="${loanContract.termType == 2 }">周</c:if> <c:if
											test="${loanContract.termType == 3 }">月</c:if> <c:if
											test="${loanContract.termType == 4 }">季</c:if> <c:if
											test="${loanContract.termType == 5 }">年</c:if></td>
								</tr>
								<tr>
									<td>贷款期限</td>
									<td>${loanContract.term }期</td>
									<td>贷款利率</td>
									<td>${loanContract.rate }%</td>
								</tr>
								<tr>
									<td>折化年利率</td>
									<td>${loanContract.annualRate }%</td>
									<td>还款方式</td>
									<td><c:if test="${loanContract.repayMethod == 1 }">等额本息</c:if>
										<c:if test="${loanContract.repayMethod == 2 }">等额本金</c:if> <c:if
											test="${loanContract.repayMethod == 3 }">先息后本</c:if> <c:if
											test="${loanContract.repayMethod == 4 }">灵活还款</c:if></td>
								</tr>
								<tr>
									<td>借款地区</td>
									<td>${loanContract.areaProvince }${loanContract.areaCity }${loanContract.areaDistrict }</td>
									<td>借款人类型</td>
									<td><c:if test="${loanContract.borrowerType == 1 }">企业</c:if>
										<c:if test="${loanContract.borrowerType == 2 }">自然人</c:if></td>
								</tr>
								<tr>
									<td>投向行业</td>
									<td><c:if test="${loanContract.business == 1 }">居民服务和其他服务业</c:if>
										<c:if test="${loanContract.business == 2 }">建筑业</c:if> <c:if
											test="${loanContract.business == 3 }">交通运输、仓储和邮政业</c:if> <c:if
											test="${loanContract.business == 4 }">农、林、牧、渔业</c:if> <c:if
											test="${loanContract.business == 5 }">采矿业</c:if> <c:if
											test="${loanContract.business == 6 }">制造业</c:if> <c:if
											test="${loanContract.business == 7 }">电力、燃气及水的生产和供应业</c:if> <c:if
											test="${loanContract.business == 8 }">信息传输、计算机服务和软件业</c:if> <c:if
											test="${loanContract.business == 9 }">批发和零售业</c:if> <c:if
											test="${loanContract.business == 10 }">住宿和餐饮业</c:if> <c:if
											test="${loanContract.business == 11 }">房地产业</c:if> <c:if
											test="${loanContract.business == 12 }">租赁和商务服务业</c:if> <c:if
											test="${loanContract.business == 13 }">其它</c:if></td>
									<td>贷款用途</td>
									<td><c:if test="${loanContract.purpose == 1 }">流动资金贷款</c:if>
										<c:if test="${loanContract.purpose == 2 }">固定资产投资贷款</c:if> <c:if
											test="${loanContract.purpose == 3 }">其它</c:if></td>
								</tr>
								<tr>
									<td>贷款方式</td>
									<td>${loanContract.loanMethod }</td>
									<td></td>
									<td></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="contract-view-table ">
				<div class="table-template table">
					<div class="table-info">
						<h4 class="table-title pull-left">计划还款信息</h4>
					</div>
					<div class="table-content">
						<table id="repayPlanTable"
							class="table table-hover table-bordered cur"></table>
					</div>

				</div>
				<div class="table-template table">
					<div class="table-info">
						<h4 class="table-title pull-left">实际还款信息</h4>
					</div>
					<div class="table-content">
						<table id="repayTable"
							class="table table-hover table-bordered cur"></table>
					</div>
				</div>
			</div>
			<div class="contract-view-table ">
				<div class="table-template table">
					<div class="table-info">
						<h4 class="table-title pull-left">展期计划还款信息</h4>
					</div>
					<div class="table-content">
						<table id="extendRepayPlanTable"
							class="table table-hover table-bordered cur"></table>
					</div>
				</div>
				<div class="table-template table">
					<div class="table-info">
						<h4 class="table-title pull-left">展期实际还款信息</h4>
					</div>
					<div class="table-content">
						<table id="extendRepayTable"
							class="table table-hover table-bordered cur"></table>
					</div>
				</div>
			</div>
			<div class="contract-view-table">
				<div class="table-template table">
					<div class="table-info">
						<h4 class="table-title pull-left">合同放款信息</h4>
					</div>
					<div class="table-content">
						<table class="table table-hover table-bordered cur">

							<thead class="thead-col">
								<tr>
									<th>放款日期</th>
									<th>放款金额</th>
									<th>债权开始日期</th>
									<th>债权结束日期</th>
									<th>备注</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${loanInfos }" var="loanInfo">
									<tr>
										<td>${loanInfo.loanTime }</td>
										<td>${loanInfo.money }</td>
										<td>${loanInfo.startDate }</td>
										<td>${loanInfo.endDate }</td>
										<td>${loanInfo.remark }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>


				</div>
			</div>
			<div class="contract-view-table">

				<c:if test="${loanContract.personBorrower != null }">
					<div class="table-template">
						<div class="table-content">
							<table class="table table-hover  cur">
								<tbody>
									<tr>
										<td>借款人</td>
										<td>${loanContract.personBorrower.name }</td>
										<td>性别</td>
										<td><c:if test="${loanContract.personBorrower.sex == 1 }">男</c:if>
											<c:if test="${loanContract.personBorrower.sex == 2 }">女</c:if>
										</td>
									</tr>
									<tr>
										<td>国籍</td>
										<td>${loanContract.personBorrower.nationality }</td>
										<td>民族</td>
										<td>${loanContract.personBorrower.nation }</td>
									</tr>
									<tr>
										<td>籍贯</td>
										<td>${loanContract.personBorrower.nativePlace }</td>
										<td>出生日期</td>
										<td>${loanContract.personBorrower.birthday }</td>
									</tr>
									<tr>
										<td>证件种类</td>
										<td><c:forEach items="${lexicon.dataOptions }"
												var="option">
												<c:if
													test="${option.optionKey eq loanContract.personBorrower.cardType}">${option.optionValue }</c:if>
											</c:forEach> 
										</td>
										<td>证件号码</td>
										<td>${loanContract.personBorrower.cardNumber }</td>
									</tr>
									<tr>
										<td>学历</td>
										<td><c:if
												test="${loanContract.personBorrower.education == 1 }">博士</c:if>
											<c:if test="${loanContract.personBorrower.education == 2 }">硕士</c:if>
											<c:if test="${loanContract.personBorrower.education == 3 }">本科</c:if>
											<c:if test="${loanContract.personBorrower.education == 4 }">专科</c:if>
											<c:if test="${loanContract.personBorrower.education == 5 }">高中及以下</c:if>
										</td>
										<td>婚姻状况</td>
										<td><c:if
												test="${loanContract.personBorrower.isMarry == 0 }">未婚</c:if>
											<c:if test="${loanContract.personBorrower.isMarry == 1 }">已婚</c:if>
										</td>
									</tr>
									<tr>
										<td>电子邮件</td>
										<td>${loanContract.personBorrower.email }</td>
										<td>邮政编码</td>
										<td>${loanContract.personBorrower.postCode }</td>
									</tr>
									<tr>
										<td>现工作单位</td>
										<td>${loanContract.personBorrower.workUnit }</td>
										<td>现任职务</td>
										<td>${loanContract.personBorrower.position }</td>
									</tr>
									<tr>
										<td>户口所在地</td>
										<td colspan="3">${loanContract.personBorrower.registeredAddress }</td>
									</tr>
									<tr>
										<td>通信地址</td>
										<td colspan="3">${loanContract.personBorrower.address }</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</c:if>

				<c:if test="${loanContract.enterpriseBorrower != null }">
					<div class="table-template">
						<div class="table-content">
							<table class="table table-hover  cur">
								<tbody>
									<tr>
										<td>企业名称</td>
										<td>${loanContract.enterpriseBorrower.name }</td>
										<td>企业规模</td>
										<td><c:if
												test="${loanContract.enterpriseBorrower.scale == 1 }">个体工商户</c:if>
											<c:if test="${loanContract.enterpriseBorrower.scale == 2 }">农村专业合作组织</c:if>
											<c:if test="${loanContract.enterpriseBorrower.scale == 3 }">微型企业</c:if>
											<c:if test="${loanContract.enterpriseBorrower.scale == 4 }">小型企业</c:if>
											<c:if test="${loanContract.enterpriseBorrower.scale == 5 }">中型企业</c:if>
											<c:if test="${loanContract.enterpriseBorrower.scale == 6 }">大型企业</c:if>
											<c:if test="${loanContract.enterpriseBorrower.scale == 7 }">其他组织</c:if>
										</td>
									</tr>
									<tr>
										<td>产业类型</td>
										<td><c:if
												test="${loanContract.enterpriseBorrower.industryType == 1 }">第一产业</c:if>
											<c:if
												test="${loanContract.enterpriseBorrower.industryType == 2 }">第二产业</c:if>
											<c:if
												test="${loanContract.enterpriseBorrower.industryType == 3 }">第三产业</c:if>
										</td>
										<td>所属行业</td>
										<td><c:if
												test="${loanContract.enterpriseBorrower.industryInvolved == 1 }">居民服务和其他服务业</c:if>
											<c:if
												test="${loanContract.enterpriseBorrower.industryInvolved == 2 }">建筑业</c:if>
											<c:if
												test="${loanContract.enterpriseBorrower.industryInvolved == 3 }">交通运输、仓储和邮政业</c:if>
											<c:if
												test="${loanContract.enterpriseBorrower.industryInvolved == 4 }">农、林、牧、渔业</c:if>
											<c:if
												test="${loanContract.enterpriseBorrower.industryInvolved == 5 }">采矿业</c:if>
											<c:if
												test="${loanContract.enterpriseBorrower.industryInvolved == 6 }">制造业</c:if>
											<c:if
												test="${loanContract.enterpriseBorrower.industryInvolved == 7 }">电力、燃气及水的生产和供应业</c:if>
											<c:if
												test="${loanContract.enterpriseBorrower.industryInvolved == 8 }">信息传输、计算机服务和软件业</c:if>
											<c:if
												test="${loanContract.enterpriseBorrower.industryInvolved == 9 }">批发和零售业</c:if>
											<c:if
												test="${loanContract.enterpriseBorrower.industryInvolved == 10 }">住宿和餐饮业</c:if>
											<c:if
												test="${loanContract.enterpriseBorrower.industryInvolved == 11 }">房地产业</c:if>
											<c:if
												test="${loanContract.enterpriseBorrower.industryInvolved == 12 }">租赁和商务服务业</c:if>
											<c:if
												test="${loanContract.enterpriseBorrower.industryInvolved == 13 }">其他</c:if>
										</td>
									</tr>
									<tr>
										<td>法定代表人</td>
										<td>${loanContract.enterpriseBorrower.legalPerson }</td>
										<td>成立日期</td>
										<td>${loanContract.enterpriseBorrower.foundDate }</td>
									</tr>
									<tr>
										<td>组织机构代码</td>
										<td>${loanContract.enterpriseBorrower.organizationCode }</td>
										<td>公司注册登记号</td>
										<td>${loanContract.enterpriseBorrower.registCode }</td>
									</tr>
									<tr>
										<td>工商注册登记机关</td>
										<td>${loanContract.enterpriseBorrower.registOffice }</td>
										<td>工商注册登记日期</td>
										<td>${loanContract.enterpriseBorrower.registDate }</td>
									</tr>
									<tr>
										<td>国税税务登记号</td>
										<td>${loanContract.enterpriseBorrower.nationalTaxCode }</td>
										<td>地税税务登记号</td>
										<td>${loanContract.enterpriseBorrower.landTaxCode }</td>
									</tr>
									<tr>
										<td>营业执照编号</td>
										<td>${loanContract.enterpriseBorrower.licenseCode }</td>
										<td>营业执照截止日期</td>
										<td>${loanContract.enterpriseBorrower.licenseEndDate }</td>
									</tr>
									<tr>
										<td>实际控制人</td>
										<td>${loanContract.enterpriseBorrower.controller }</td>
										<td>实收资本</td>
										<td>${loanContract.enterpriseBorrower.reallyCapital }</td>
									</tr>
									<tr>
										<td>联系人</td>
										<td>${loanContract.enterpriseBorrower.linkman }</td>
										<td>联系电话</td>
										<td>${loanContract.enterpriseBorrower.phone }</td>
									</tr>
									<tr>
										<td>公司邮箱</td>
										<td>${loanContract.enterpriseBorrower.email }</td>
										<td>公司网址</td>
										<td>${loanContract.enterpriseBorrower.website }</td>
									</tr>
									<tr>
										<td>注册地址</td>
										<td colspan="3">${loanContract.enterpriseBorrower.registerAddress }</td>
									</tr>
									<tr>
										<td>通信地址</td>
										<td colspan="3">${loanContract.enterpriseBorrower.address }</td>
									</tr>
									<tr style="height: 60px;">
										<td>经营范围</td>
										<td colspan="3">${loanContract.enterpriseBorrower.businessScope }</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</c:if>
			</div>
			<div class="contract-view-table">
				<div class="table-template table">
					<div class="table-info">
						<h4 class="table-title pull-left">保证合同信息</h4>
					</div>
					<div class="table-content">
						<table id="ensureContractTable"
							class="table table-hover table-bordered cur"></table>
					</div>


				</div>
			</div>
			<div class="contract-view-table">
				<div class="table-template table">
					<div class="table-info">
						<h4 class="table-title pull-left">抵押合同信息</h4>
					</div>
					<div class="table-content">
						<table id="mortgageContractTable"
							class="table table-hover table-bordered cur"></table>
					</div>


				</div>
			</div>
			<div class="contract-view-table">
				<div class="table-template table">
					<div class="table-info">
						<h4 class="table-title pull-left">质押合同信息</h4>
					</div>
					<div class="table-content">
						<table id="pledgeContractTable"
							class="table table-hover table-bordered cur"></table>
					</div>
				</div>
			</div>


		</div>
	</div>
</div>



<script src="build/lib/sea.min.js"></script>
<script src="js/base.js"></script>
<script src="js/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<!-- Latest compiled and minified Locales -->
<script
	src="js/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="js/app/loanContractView.js"></script>
<script>
	$(function() {
		seajs.use('common', function(c) {
			c.navInit();
		})
		seajs.use('extendAndRepay', function(e) {
			e.contractTab();
		})
	})
</script>


</body>
</html>
