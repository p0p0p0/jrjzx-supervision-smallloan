<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
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
<title>添加贷款合同</title>
<link href="build/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="build/lib/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="build/lib/layer/skin/layer.css">
<link rel="stylesheet" href="css/style.css">
<link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />
</head>

<body>
	<jsp:include page="/WEB-INF/jsp/common/top.jsp" />

	<div class="container pt100">
		<div class="panel add_loan_contract ">
			<h2>添加贷款合同</h2>
			<ul class="step">
				<li class="active">第一步：合同信息</li>
				<li>第二步：借款人</li>
				<li>第三步：完成</li>
			</ul>

			<form id="dataForm" action="loanContract/save.html" method="POST"
				class="form-horizontal" role="form">

				<div class="fieldset active">
					<div class="form-group">
						<label class="col-sm-2 control-label">贷款合同编号</label>
						<div class="col-sm-2 required">
							<input class="form-control " name="contractNumber" type="text"
								value="${contract.contractNumber }" />
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">合同名称</label>
						<div class="col-sm-2 ">
							<input class="form-control" name="contractName" type="text"
								value="${contract.contractName }" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">合同签订日期</label>
						<div class="col-sm-2 ">
							<input class="form-control form_datetime" name="signTime"
								type="text" value="${contract.signTime }" />
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">贷款开始日期</label>
						<div class="col-sm-2 ">
							<input class="form-control form_datetime" name="startDate"
								value="${contract.startDate }" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">贷款截止日期</label>
						<div class="col-sm-2 ">
							<input class="form-control form_datetime" name="endDate"
								value="${contract.endDate }" type="text" />
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">贷款金额</label>
						<div class="col-sm-2 ">
							<div class="input-group ">
								<input type="text" class="form-control number" name="money"
									value="${contract.money }"> <span
									class="input-group-addon">元</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">贷款期限类型</label>
						<div class="col-sm-3" style="text-align: left;">
							<label class="radio-inline"> <input type="radio"
								name="termType" value="1"
								<c:if test="${contract.termType==1 }">checked="checked"</c:if>>
								日
							</label> <label class="radio-inline"> <input type="radio"
								name="termType" value="2"
								<c:if test="${contract.termType==2 }">checked="checked"</c:if>>
								周
							</label> <label class="radio-inline"> <input type="radio"
								name="termType" value="3"
								<c:if test="${contract.termType==3 }">checked="checked"</c:if>>
								月
							</label> <label class="radio-inline"> <input type="radio"
								name="termType" value="4"
								<c:if test="${contract.termType==4 }">checked="checked"</c:if>>
								季
							</label> <label class="radio-inline"> <input type="radio"
								name="termType" value="5"
								<c:if test="${contract.termType==5 }">checked="checked"</c:if>>
								年
							</label>
						</div>
						<label class="col-sm-2 control-label col-sm-offset-1">贷款期限</label>
						<div class="col-sm-2 ">
							<div class="input-group ">
								<input type="text" class="form-control" name="term"
									value="${contract.term }"> <span
									class="input-group-addon">期</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">贷款利率</label>
						<div class="col-sm-2 ">
							<div class="input-group ">
								<input type="text" class="form-control" name="rate"
									value="${contract.rate }"> <span
									class="input-group-addon termTypeDesc">%(年)</span>
							</div>
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">折合年化</label>
						<div class="col-sm-2 ">
							<div class="input-group ">
								<input type="text" class="form-control" name="annualRate"
									value="${contract.annualRate }" readonly> <span
									class="input-group-addon">%</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">受托支付</label>
						<div class="col-sm-2 ">
							<select name="isEntrust" class="form-control">
								<option value="0"
									<c:if test="${contract.isEntrust==0 }">selected="selected"</c:if>>否</option>
								<option value="1"
									<c:if test="${contract.isEntrust==1 }">selected="selected"</c:if>>是</option>
							</select>
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">还款方式</label>
						<div class="col-sm-2 ">
							<select name="repayMethod" class="form-control">
								<option value="-1">请选择</option>
								<option value="1"
									<c:if test="${contract.repayMethod==1 }">selected="selected"</c:if>>等额本息</option>
								<option value="2"
									<c:if test="${contract.repayMethod==2 }">selected="selected"</c:if>>等额本金</option>
								<option value="3"
									<c:if test="${contract.repayMethod==3 }">selected="selected"</c:if>>先息后本</option>
								<option value="4"
									<c:if test="${contract.repayMethod==4 }">selected="selected"</c:if>>灵活还款</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">借款区域</label>
						<div class="col-sm-4 ">
							<select id="s_province" class="form-control pull-left"
								name="areaProvince" style="width: 100px">
								<%-- 								<option value="${contract.areaProvince }"/>${contract.areaProvince } --%>
							</select>   <select id="s_city" class="form-control pull-left"
								name="areaCity" style="width: 100px"></select>   <select
								id="s_county" class="form-control pull-left" name="areaDistrict"
								style="width: 120px"></select>
						</div>
						<div id="show"></div>
						<label class="col-sm-2 control-label ">投向行业</label>
						<div class="col-sm-2 ">
							<select name="business" class="form-control">
								<option value="-1">请选择</option>
								<option value="1"
									<c:if test="${contract.business==1 }">selected="selected"</c:if>>居民服务和其他服务业</option>
								<option value="2"
									<c:if test="${contract.business==2 }">selected="selected"</c:if>>建筑业</option>
								<option value="3"
									<c:if test="${contract.business==3 }">selected="selected"</c:if>>交通运输、仓储和邮政业</option>
								<option value="4"
									<c:if test="${contract.business==4 }">selected="selected"</c:if>>农、林、牧、渔业</option>
								<option value="5"
									<c:if test="${contract.business==5 }">selected="selected"</c:if>>采矿业</option>
								<option value="6"
									<c:if test="${contract.business==5 }">selected="selected"</c:if>>制造业</option>
								<option value="7"
									<c:if test="${contract.business==7 }">selected="selected"</c:if>>电力、燃气及水的生产和供应业</option>
								<option value="8"
									<c:if test="${contract.business==8 }">selected="selected"</c:if>>信息传输、计算机服务和软件业</option>
								<option value="9"
									<c:if test="${contract.business==9 }">selected="selected"</c:if>>批发和零售业</option>
								<option value="10"
									<c:if test="${contract.business==10 }">selected="selected"</c:if>>住宿和餐饮业</option>
								<option value="11"
									<c:if test="${contract.business==11 }">selected="selected"</c:if>>房地产业</option>
								<option value="12"
									<c:if test="${contract.business==12 }">selected="selected"</c:if>>租赁和商务服务业</option>
								<option value="13"
									<c:if test="${contract.business==13 }">selected="selected"</c:if>>其它</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">贷款用途</label>
						<div class="col-sm-2 ">
							<select name="purpose" class="form-control">
								<option value="-1">请选择</option>
								<option value="1"
									<c:if test="${contract.purpose==1 }">selected="selected"</c:if>>流动资金贷款</option>
								<option value="2"
									<c:if test="${contract.purpose==2 }">selected="selected"</c:if>>固定资产投资贷款</option>
								<option value="3"
									<c:if test="${contract.purpose==3 }">selected="selected"</c:if>>其它</option>
							</select>
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">还款来源</label>
						<div class="col-sm-2 ">
							<input class="form-control" type="text" name="repaySource"
								value="${contract.repaySource }" placeholder="填写比如营业收、工资等" />
						</div>
					</div>

					<!--还款计划-->
					<div class="container" style="margin-top: 100px;">
						<div class="table-template t2">
							<div class="pull-left">
								<label class=" control-label pull-left">还本付息日</label> <select
									class="form-control pull-left" style="width: 120px;"
									name="fuxiri">
									<option value="01">1日</option>
									<option value="02">2日</option>
									<option value="03">3日</option>
									<option value="04">4日</option>
									<option value="05">5日</option>
									<option value="06">6日</option>
									<option value="07">7日</option>
									<option value="08">8日</option>
									<option value="09">9日</option>
									<option value="10">10日</option>
									<option value="11">11日</option>
									<option value="12">12日</option>
									<option value="13">13日</option>
									<option value="14">14日</option>
									<option value="15">15日</option>
									<option value="16">16日</option>
									<option value="17">17日</option>
									<option value="18">18日</option>
									<option value="19">19日</option>
									<option value="20">20日</option>
									<option value="21">21日</option>
									<option value="22">22日</option>
									<option value="23">23日</option>
									<option value="24">24日</option>
									<option value="25">25日</option>
									<option value="26">26日</option>
									<option value="27">27日</option>
									<option value="28">28日</option>
									<option value="29">29日</option>
									<option value="30">30日</option>
									<option value="31">31日</option>
								</select> 
								<a href="javascript:;" 
									style="position: relative; left: 20px; top: 0px;" class="btn btn-primary pull-right"
									id="createRepayPlan">生成还款</a>
							</div>
							<div class="addSpan addInfo">
								<a href="javascript:;" class="btn btn-primary pull-right add-btn">添加</a>
							</div>
							<div class="table-content" style="margin-top: 20px;">
								<table class="table table-hover table-bordered cur"
									style="text-align: center;" id="repayPlanTable">
									<thead class="thead-col">
										<tr>
											<th>还款日期</th>
											<th>还款本金（元）</th>
											<th>还款利息（元）</th>
											<th>操作</th>
										</tr>
									</thead>
									<tbody class="new_repay">
										<c:choose>
											<c:when
												test="${ contract.repayPlans==null || fn:length(contract.repayPlans)==0 }">
												<tr>
													<td><input type="text" name="repayPlans[0].endDate"
														class="dateIcon form_datetime"></td>
													<td><input class="number" type="text" name="repayPlans[0].money"></td>
													<td><input class="number" type="text" name="repayPlans[0].interest"></td>
													<td><a class="del" href="javascript:;">删除</a></td>
												</tr>
											</c:when>
											<c:otherwise>
												<c:forEach items="${contract.repayPlans }" var="rp"
													varStatus="statu">
													<tr>
														<td><input type="text"
															name="repayPlans[${statu.index }].endDate"
															value="${rp.endDate }" class="dateIcon form_datetime"></td>
														<td><input type="text" class="number"
															name="repayPlans[${statu.index }].money"
															value="${rp.money }"></td>
														<td><input type="text" class="number"
															name="repayPlans[${statu.index }].interest"
															value="${rp.interest }"></td>
														<td><a class="del" href="javascript:;">删除</a></td>
													</tr>
												</c:forEach>
											</c:otherwise>
										</c:choose>
									</tbody>

								</table>
							</div>

						</div>
					</div>

					<div class="form-group last">
						<span class="btn btn-info col-sm-1 col-sm-offset-5"
							id="pageOneDraft" onclick="saveDraft()">存稿</span> <span
							class="btn btn-info col-sm-1 col-sm-offset-1 next"
							id="pageOneNext">下一步</span>
					</div>
				</div>

				<!-- 新增借款客户 	START-->

				<div class="fieldset ">
					<div class="form-group">
						<div class="form-group">
							<label class="col-sm-2 control-label ">借款人类型</label>
							<div class="col-sm-4 ">
								<label class="radio-inline"> <input type="radio"
									name="borrowerType"
									<c:if test="${contract.borrowerType==2 }">checked="checked"</c:if>
									value="2">自然人
								</label> <label class="radio-inline"> <input type="radio"
									<c:if test="${contract.borrowerType==1 }">checked="checked"</c:if>
									name="borrowerType" value="1"> 企业
								</label>
							</div>
						</div>

					</div>
					<!--自然人-->
					<fieldset
						class="naturalPerson lenderType <c:if test="${contract.borrowerType==2 }">active</c:if>">
						<div class="form-group">
							<label class="col-sm-2 control-label">借款人</label>
							<div class="col-sm-2 required">
								<input class="form-control " type="text"
									name="personBorrower.name"
									value="${contract.personBorrower.name }" />
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">性别</label>
							<div class="col-sm-2 required">
								<select name="personBorrower.sex" class="form-control">
									<option value="-1">请选择</option>
									<option value="1"
										<c:if test="${contract.personBorrower.sex==1 }">selected="selected"</c:if>>男</option>
									<option value="2"
										<c:if test="${contract.personBorrower.sex==2 }">selected="selected"</c:if>>女</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">国籍</label>
							<div class="col-sm-2 ">
								<input class="form-control" type="text"
									name="personBorrower.nationality"
									value="${contract.personBorrower.nationality }" />
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">民族</label>
							<div class="col-sm-2 ">
								<div class="input-group">
									<input type="text" class="form-control"
										name="personBorrower.nation"
										value="${contract.personBorrower.nation }">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">籍贯</label>
							<div class="col-sm-2 ">
								<input class="form-control " type="text"
									name="personBorrower.nativePlace"
									value="${contract.personBorrower.nativePlace }" />

							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">出生日期</label>
							<div class="col-sm-2">
								<div class="input-group">
									<input type="text" class="form-control form_datetime"
										name="personBorrower.birthday"
										value="${contract.personBorrower.birthday }">
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">证件类型</label>
							<div class="col-sm-2 required">
								<select class="form-control" name="personBorrower.cardType">
									<c:forEach items="${lexicon.dataOptions }" var="option">
										<option value="${option.optionKey }"
											<c:if test="${option.optionKey eq contract.personBorrower.cardType }">selected="selected"</c:if> />${option.optionValue }
                        	</c:forEach>
								</select>
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">证件号码</label>
							<div class="col-sm-2 required">
								<input class="form-control" type="text"
									name="personBorrower.cardNumber"
									value="${contract.personBorrower.cardNumber }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">联系电话</label>
							<div class="col-sm-2 required">
								<input class="form-control" id="business_license" type="text"
									name="personBorrower.phone"
									value="${contract.personBorrower.phone }" />
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">是否农牧民</label>
							<div class="col-sm-2 required">
								<select class="form-control" name="personBorrower.isFarmer">
									<option value="0"
										<c:if test="${contract.personBorrower.isFarmer==0 }">selected="selected"</c:if>>否</option>
									<option value="1"
										<c:if test="${contract.personBorrower.isFarmer==1 }">selected="selected"</c:if>>是</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label ">学历</label>
							<div class="col-sm-2 ">
								<select class="form-control" name="personBorrower.education">
									<option value="-1">请选择</option>
									<option value="1"
										<c:if test="${contract.personBorrower.education==1 }">selected="selected"</c:if>>博士</option>
									<option value="2"
										<c:if test="${contract.personBorrower.education==2 }">selected="selected"</c:if>>硕士</option>
									<option value="3"
										<c:if test="${contract.personBorrower.education==3 }">selected="selected"</c:if>>本科</option>
									<option value="4"
										<c:if test="${contract.personBorrower.education==4 }">selected="selected"</c:if>>专科</option>
									<option value="5"
										<c:if test="${contract.personBorrower.education==5 }">selected="selected"</c:if>>高中及以下</option>
								</select>
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">婚姻状况</label>
							<div class="col-sm-2 required">
								<select name="personBorrower.isMarry" class="form-control">
									<option value="-1">请选择</option>
									<option value="0"
										<c:if test="${contract.personBorrower.isMarry==0 }">selected="selected"</c:if>>未婚</option>
									<option value="1"
										<c:if test="${contract.personBorrower.isMarry==1 }">selected="selected"</c:if>>已婚</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">电子邮件</label>
							<div class="col-sm-2">
								<input class="form-control" type="text"
									name="personBorrower.email"
									value="${contract.personBorrower.email }" />
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">邮政编码</label>
							<div class="col-sm-2">
								<input class="form-control" type="text"
									name="personBorrower.postCode"
									value="${contract.personBorrower.postCode }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">现工作单位</label>
							<div class="col-sm-2">
								<input class="form-control" type="text"
									name="personBorrower.workUnit"
									value="${contract.personBorrower.workUnit }" />
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">现任职务</label>
							<div class="col-sm-2">
								<input class="form-control" type="text"
									name="personBorrower.position"
									value="${contract.personBorrower.position }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label ">户口所在地</label>
							<div class="col-sm-8 business_address">
								<input class="form-control"
									name="personBorrower.registeredAddress" type="text"
									value="${contract.personBorrower.registeredAddress }"
									placeholder="详细地址（**省**市**区（县）**街道（镇）、村（路）****）" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label ">固定住址</label>
							<div class="col-sm-8 required business_address">
								<input class="form-control" name="personBorrower.address"
									value="${contract.personBorrower.address }" type="text"
									placeholder="详细地址（**省**市**区（县）**街道（镇）、村（路）****）" />
							</div>
						</div>
					</fieldset>

					<!--企业-->
					<fieldset
						class="enterprise lenderType <c:if test="${contract.borrowerType==1 }">active</c:if>">

						<div class="form-group">
							<label class="col-sm-2 control-label">企业名称</label>
							<div class="col-sm-2 required">
								<input class="form-control " type="text"
									name="enterpriseBorrower.name"
									value="${contract.enterpriseBorrower.name }" />
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">企业规模</label>
							<div class="col-sm-2 required">
								<select class="form-control" name="enterpriseBorrower.scale">
									<option value="-1">请选择</option>
									<option value="1"
										<c:if test="${contract.enterpriseBorrower.scale==1 }">selected="selected"</c:if>>工商个体户</option>
									<option value="2"
										<c:if test="${contract.enterpriseBorrower.scale==2 }">selected="selected"</c:if>>农村专业合作组织</option>
									<option value="3"
										<c:if test="${contract.enterpriseBorrower.scale==3 }">selected="selected"</c:if>>微型企业</option>
									<option value="4"
										<c:if test="${contract.enterpriseBorrower.scale==4 }">selected="selected"</c:if>>小型企业</option>
									<option value="5"
										<c:if test="${contract.enterpriseBorrower.scale==5 }">selected="selected"</c:if>>中型企业</option>
									<option value="6"
										<c:if test="${contract.enterpriseBorrower.scale==6 }">selected="selected"</c:if>>大型企业</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">产业类型</label>
							<div class="col-sm-2 required">
								<select class="form-control"
									name="enterpriseBorrower.industryType">
									<option value="-1">请选择</option>
									<option value="1"
										<c:if test="${contract.enterpriseBorrower.industryType==1 }">selected="selected"</c:if>>第一产业</option>
									<option value="2"
										<c:if test="${contract.enterpriseBorrower.industryType==2 }">selected="selected"</c:if>>第二产业</option>
									<option value="3"
										<c:if test="${contract.enterpriseBorrower.industryType==3 }">selected="selected"</c:if>>第三产业</option>
								</select>
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">所属行业</label>
							<div class="col-sm-2 required">
								<select name="enterpriseBorrower.industryInvolved"
									class="form-control">
									<option value="-1">请选择</option>
									<option value="1"
										<c:if test="${contract.enterpriseBorrower.industryInvolved==1 }">selected="selected"</c:if>>居民服务和其他服务行业</option>
									<option value="2"
										<c:if test="${contract.enterpriseBorrower.industryInvolved==2 }">selected="selected"</c:if>>建筑业</option>
									<option value="3"
										<c:if test="${contract.enterpriseBorrower.industryInvolved==3 }">selected="selected"</c:if>>交通运输、仓储和邮政业</option>
									<option value="4"
										<c:if test="${contract.enterpriseBorrower.industryInvolved==4 }">selected="selected"</c:if>>农林牧渔业</option>
									<option value="5"
										<c:if test="${contract.enterpriseBorrower.industryInvolved==5 }">selected="selected"</c:if>>采矿业</option>
									<option value="6"
										<c:if test="${contract.enterpriseBorrower.industryInvolved==6 }">selected="selected"</c:if>>制造业</option>
									<option value="7"
										<c:if test="${contract.enterpriseBorrower.industryInvolved==7 }">selected="selected"</c:if>>电力、燃气及水的产生和供应业</option>
									<option value="8"
										<c:if test="${contract.enterpriseBorrower.industryInvolved==8 }">selected="selected"</c:if>>信息传输、计算机软件服务业</option>
									<option value="9"
										<c:if test="${contract.enterpriseBorrower.industryInvolved==9 }">selected="selected"</c:if>>批发零售业</option>
									<option value="10"
										<c:if test="${contract.enterpriseBorrower.industryInvolved==10 }">selected="selected"</c:if>>住宿餐饮业</option>
									<option value="11"
										<c:if test="${contract.enterpriseBorrower.industryInvolved==11 }">selected="selected"</c:if>>房地产业</option>
									<option value="12"
										<c:if test="${contract.enterpriseBorrower.industryInvolved==12 }">selected="selected"</c:if>>租赁和商业服务业</option>
									<option value="13"
										<c:if test="${contract.enterpriseBorrower.industryInvolved==13 }">selected="selected"</c:if>>其它</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">法定代表人</label>
							<div class="col-sm-2 ">
								<input class="form-control" type="text"
									name="enterpriseBorrower.legalPerson"
									value="${contract.enterpriseBorrower.legalPerson }" />

							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">成立日期</label>
							<div class="col-sm-2 ">
								<input class="form-control form_datetime" type="text"
									name="enterpriseBorrower.foundDate"
									value="${contract.enterpriseBorrower.foundDate }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">组织机构代码</label>
							<div class="col-sm-2 required">
								<input class="form-control " type="text"
									name="enterpriseBorrower.organizationCode"
									value="${contract.enterpriseBorrower.organizationCode }" />
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">公司注册登记号</label>
							<div class="col-sm-2 ">
								<input class="form-control" type="text"
									name="enterpriseBorrower.registCode"
									value="${contract.enterpriseBorrower.registCode }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">工商注册登记机关</label>
							<div class="col-sm-2">
								<input class="form-control " type="text"
									name="enterpriseBorrower.registOffice"
									value="${contract.enterpriseBorrower.registOffice }" />

							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">工商注册登记日期</label>
							<div class="col-sm-2 ">
								<input class="form-control form_datetime" type="text"
									name="enterpriseBorrower.registDate"
									value="${contract.enterpriseBorrower.registDate }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">国税税务登记号</label>
							<div class="col-sm-2">
								<input class="form-control " type="text"
									name="enterpriseBorrower.nationalTaxCode"
									value="${contract.enterpriseBorrower.nationalTaxCode }" />

							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">地税税务登记号</label>
							<div class="col-sm-2 ">
								<input class="form-control" type="text"
									name="enterpriseBorrower.landTaxCode"
									value="${contract.enterpriseBorrower.landTaxCode }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">营业执照编号</label>
							<div class="col-sm-2 required">
								<input class="form-control " type="text"
									name="enterpriseBorrower.licenseCode"
									value="${contract.enterpriseBorrower.licenseCode }" />

							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">营业执照截止日期</label>
							<div class="col-sm-2 required">
								<input class="form-control form_datetime" type="text"
									name="enterpriseBorrower.licenseEndDate"
									value="${contract.enterpriseBorrower.licenseEndDate }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">实际控制人</label>
							<div class="col-sm-2">
								<input class="form-control " type="text"
									name="enterpriseBorrower.controller"
									value="${contract.enterpriseBorrower.controller }" />

							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">实收资本</label>
							<div class="col-sm-2 ">
								<input class="form-control" type="text"
									name="enterpriseBorrower.reallyCapital"
									value="${contract.enterpriseBorrower.reallyCapital }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">联系人</label>
							<div class="col-sm-2">
								<input class="form-control" type="text"
									name="enterpriseBorrower.linkman"
									value="${contract.enterpriseBorrower.linkman }" />

							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">联系电话</label>
							<div class="col-sm-2 ">
								<input class="form-control" type="text"
									name="enterpriseBorrower.phone"
									value="${contract.enterpriseBorrower.phone }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">公司邮箱</label>
							<div class="col-sm-2">
								<input class="form-control " type="text"
									name="enterpriseBorrower.email"
									value="${contract.enterpriseBorrower.email }" />

							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">公司网址</label>
							<div class="col-sm-2 ">
								<input class="form-control" type="text"
									name="enterpriseBorrower.website"
									value="${contract.enterpriseBorrower.website }" />
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-2 control-label ">注册地址</label>
							<div class="col-sm-8  business_address">
								<input class="form-control" type="text"
									name="enterpriseBorrower.registerAddress"
									value="${contract.enterpriseBorrower.registerAddress }"
									placeholder="填写街道（镇）或村（路）****等详细地址" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label ">通信地址</label>
							<div class="col-sm-8 required business_address">
								<input class="form-control" type="text"
									name="enterpriseBorrower.address"
									value="${contract.enterpriseBorrower.address }"
									placeholder="填写街道（镇）或村（路）****等详细地址" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label ">经营范围</label>
							<div class="col-sm-8 business_address">
								<textarea class="form-control" rows="4"
									name="enterpriseBorrower.businessScope">${contract.enterpriseBorrower.businessScope }</textarea>
							</div>
						</div>

					</fieldset>
					<div class="form-group last">
						<span class="btn btn-info col-sm-1 col-sm-offset-4"
							onclick="saveDraft()">存稿</span> <span
							class="btn btn-info col-sm-1 col-sm-offset-1 prev"
							id="pageTwoPre">上一步</span> <span
							class="btn btn-info col-sm-1 col-sm-offset-1 next"
							id="pageTwoNext">下一步</span>
					</div>
				</div>

				<div class="fieldset">
					<!--贷款方式-->
					<fieldset class="loan-mode">
						<div class="table-template clearfix">
							<div class="table-info">
								<h4 class="table-title pull-left">贷款方式</h4>
							</div>
							<div class="input-group pull-left clearfix loan-style">
								<span class="select">请选择:</span> <label class="checkbox-inline">
									<input type="checkbox" name="loanMethod" value="1" id="credit"
									<c:if test="${fn:contains(contract.loanMethod, '1')}">checked="checked"</c:if>>信用
								</label> <label class="checkbox-inline"> <input type="checkbox"
									name="loanMethod" value="2" id="ensure"
									<c:if test="${fn:contains(contract.loanMethod, '2')}">checked="checked"</c:if>>保证
								</label> <label class="checkbox-inline"> <input type="checkbox"
									name="loanMethod" value="3" id="mortgage"
									<c:if test="${fn:contains(contract.loanMethod, '3')}">checked="checked"</c:if>>抵押
								</label> <label class="checkbox-inline"> <input type="checkbox"
									name="loanMethod" value="4" id="pledge"
									<c:if test="${fn:contains(contract.loanMethod, '4')}">checked="checked"</c:if>>质押
								</label> <label class="checkbox-inline"> <input type="checkbox"
									name="loanMethod" value="5" id="other"
									<c:if test="${fn:contains(contract.loanMethod, '5')}">checked="checked"</c:if>>其它
								</label>
							</div>

							<div class="ensure "
								<c:if test="${!fn:contains(contract.loanMethod, '2')}">style="display: none;"</c:if>>
								<h5>保证合同信息</h5>
								<div class="addSpan addInfo">
									<a href="javascript:;" class="pull-right">添加</a>
								</div>
								<div class="table-content">
									<table class="table table-hover table-bordered cur"
										style="text-align: center;">
										<thead>
											<tr class="thead-col">
												<th>保证人/企业名</th>
												<th>证件种类</th>
												<th>证件号码/营业执照编号</th>
												<th>联系地址/注册地址</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody class="ensure-tb">
											<c:choose>
												<c:when
													test="${contract.ensureContracts==null || fn:length(contract.ensureContracts)==0 }">
													<tr>
														<td><input type="text" name="ensureContracts[0].name"
															class="form-control"></td>
														<td><select name="ensureContracts[0].cardType"
															class="form-control">
																<option value="1">身份证</option>
																<option value="2">驾驶证</option>
																<option value="3">港澳通行证</option>
																<option value="4">护照</option>
																<option value="5">营业执照</option>
														</select></td>
														<td><input type="text"
															name="ensureContracts[0].cardNumber" class="form-control"></td>
														<td><input type="text"
															name="ensureContracts[0].address" class="form-control"></td>
														<td><a href="javascript:;" class="del">删除</a></td>
													</tr>
												</c:when>
												<c:otherwise>
													<c:forEach items="${ contract.ensureContracts }"
														var="ensureContracts" varStatus="statu">
														<tr>
															<td><input type="text"
																name="ensureContracts[${statu.index }].name"
																class="form-control" value="${ensureContracts.name }"></td>
															<td><select
																name="ensureContracts[${statu.index }].cardType"
																class="form-control">
																	<option value="1"
																		<c:if test="${ensureContracts.cardType==1 }">selected="selected"</c:if>>身份证</option>
																	<option value="2"
																		<c:if test="${ensureContracts.cardType==2 }">selected="selected"</c:if>>驾驶证</option>
																	<option value="3"
																		<c:if test="${ensureContracts.cardType==3 }">selected="selected"</c:if>>港澳通行证</option>
																	<option value="4"
																		<c:if test="${ensureContracts.cardType==4 }">selected="selected"</c:if>>护照</option>
																	<option value="5"
																		<c:if test="${ensureContracts.cardType==5 }">selected="selected"</c:if>>营业执照</option>
															</select></td>
															<td><input type="text"
																name="ensureContracts[${statu.index }].cardNumber"
																class="form-control"
																value="${ensureContracts.cardNumber }"></td>
															<td><input type="text"
																name="ensureContracts[${statu.index }].address"
																class="form-control" value="${ensureContracts.address }"></td>
															<td><a href="javascript:;" class="del">删除</a></td>
														</tr>
													</c:forEach>
												</c:otherwise>
											</c:choose>


										</tbody>

									</table>
								</div>
							</div>

							<div class="mortgage"
								<c:if test="${!fn:contains(contract.loanMethod, '3')}">style="display: none;"</c:if>>
								<h5>抵押合同信息</h5>
								<div class="addSpan addInfo">
									<a href="javascript:;" class="pull-right">添加</a>
								</div>
								<div class="table-content">

									<table class="table table-hover table-bordered cur"
										style="text-align: center;">
										<thead>
											<tr class="thead-col">
												<th>抵押物名称</th>
												<th>数据及单位</th>
												<th>存放地点</th>
												<th>抵押物类型</th>
												<th>评估价值(元)</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody class="mortgage-tb">
											<c:choose>
												<c:when
													test="${ contract.mortgageContracts==null || fn:length(contract.mortgageContracts)==0 }">
													<tr>
														<td><input type="text"
															name="mortgageContracts[0].name" class="form-control"></td>
														<td><input type="text"
															name="mortgageContracts[0].unit" class="form-control"></td>
														<td><input type="text"
															name="mortgageContracts[0].address" class="form-control"></td>
														<td><select name="mortgageContracts[0].mortgageType"
															class="form-control">
																<option value="1">存货抵押</option>
																<option value="2">客账抵押</option>
																<option value="3">证券抵押</option>
																<option value="4">设备抵押</option>
																<option value="5">不动产抵押</option>
																<option value="6">人寿抵押</option>
																<option value="7">其它</option>
														</select></td>
														<td><input type="text"
															name="mortgageContracts[0].worth" class="form-control"></td>
														<td><a href="javascript:;" class="del">删除</a></td>
													</tr>
												</c:when>
												<c:otherwise>
													<c:forEach items="${contract.mortgageContracts }"
														var="mortgageContracts" varStatus="statu">
														<tr>
															<td><input type="text"
																value="${mortgageContracts.name }"
																name="mortgageContracts[${statu.index }].name"
																class="form-control"></td>
															<td><input type="text"
																value="${mortgageContracts.unit }"
																name="mortgageContracts[${statu.index }].unit"
																class="form-control"></td>
															<td><input type="text"
																value="${mortgageContracts.address }"
																name="mortgageContracts[${statu.index }].address"
																class="form-control"></td>
															<td><select
																name="mortgageContracts[${statu.index }].mortgageType"
																class="form-control">
																	<option value="1"
																		<c:if test="${mortgageContracts.mortgageType==1 }">selected="selected"</c:if>>存货抵押</option>
																	<option value="2"
																		<c:if test="${mortgageContracts.mortgageType==2 }">selected="selected"</c:if>>客账抵押</option>
																	<option value="3"
																		<c:if test="${mortgageContracts.mortgageType==3 }">selected="selected"</c:if>>证券抵押</option>
																	<option value="4"
																		<c:if test="${mortgageContracts.mortgageType==4 }">selected="selected"</c:if>>设备抵押</option>
																	<option value="5"
																		<c:if test="${mortgageContracts.mortgageType==5 }">selected="selected"</c:if>>不动产抵押</option>
																	<option value="6"
																		<c:if test="${mortgageContracts.mortgageType==6 }">selected="selected"</c:if>>人寿抵押</option>
																	<option value="7"
																		<c:if test="${mortgageContracts.mortgageType==7 }">selected="selected"</c:if>>其它</option>
															</select></td>
															<td><input type="text"
																value="${mortgageContracts.worth }"
																name="mortgageContracts[${statu.index }].worth"
																class="form-control"></td>
															<td><a href="javascript:;" class="del">删除</a></td>
														</tr>
													</c:forEach>
												</c:otherwise>
											</c:choose>


										</tbody>

									</table>
								</div>
							</div>

							<div class="pledge"
								<c:if test="${!fn:contains(contract.loanMethod, '4')}">style="display: none;"</c:if>>
								<h5>质押合同信息</h5>
								<div class="addSpan addInfo">
									<a href="javascript:;" class="pull-right">添加</a>
								</div>
								<div class="table-content">
									<table class="table table-hover table-bordered cur"
										style="text-align: center;">
										<thead>
											<tr class="thead-col">
												<th>质押物名称</th>
												<th>数据及单位</th>
												<th>存放地点</th>
												<th>抵押物类型</th>
												<th>评估价值(元)</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody class="pledge-tb">
											<c:choose>
												<c:when
													test="${ contract.pledgeContracts==null || fn:length(contract.pledgeContracts)==0 }">
													<tr>
														<td><input type="text" name="pledgeContracts[0].name"
															class="form-control"></td>
														<td><input type="text" name="pledgeContracts[0].unit"
															class="form-control"></td>
														<td><input type="text"
															name="pledgeContracts[0].address" class="form-control"></td>
														<td><select name="pledgeContracts[0].pledgeType"
															class="form-control">
																<option value="1">股权质押</option>
																<option value="2">定期存单质押</option>
																<option value="3">专利权质押</option>
																<option value="4">应收账款质押</option>
																<option value="5">其它</option>
														</select></td>
														<td><input type="text"
															name="pledgeContracts[0].worth" class="form-control"></td>
														<td><a href="javascript:;" class="del">删除</a></td>
													</tr>
												</c:when>
												<c:otherwise>
													<c:forEach items="${contract.pledgeContracts }" var="pc"
														varStatus="statu">
														<tr>
															<td><input type="text"
																name="pledgeContracts[${statu.index }].name"
																class="form-control" value="${pc.name }"></td>
															<td><input type="text"
																name="pledgeContracts[${statu.index }].unit"
																class="form-control" value="pc.unit"></td>
															<td><input type="text"
																name="pledgeContracts[${statu.index }].address"
																class="form-control"></td>
															<td><select
																name="pledgeContracts[${statu.index }].pledgeType"
																class="form-control">
																	<option value="1"
																		<c:if test="${mc.pledgeType==1 }">selected="selected"</c:if>>股权质押</option>
																	<option value="2"
																		<c:if test="${mc.pledgeType==2 }">selected="selected"</c:if>>定期存单质押</option>
																	<option value="3"
																		<c:if test="${mc.pledgeType==3 }">selected="selected"</c:if>>专利权质押</option>
																	<option value="4"
																		<c:if test="${mc.pledgeType==4 }">selected="selected"</c:if>>应收账款质押</option>
																	<option value="5"
																		<c:if test="${mc.pledgeType==5 }">selected="selected"</c:if>>其它</option>
															</select></td>
															<td><input type="text" value="${pc.worth }"
																name="pledgeContracts[${statu.index }].worth"
																class="form-control"></td>
															<td><a href="javascript:;" class="del">删除</a></td>
														</tr>
													</c:forEach>
												</c:otherwise>
											</c:choose>


										</tbody>

									</table>
								</div>
							</div>
						</div>
					</fieldset>
					<div class="form-group last">
						<span class="btn btn-info col-sm-1 col-sm-offset-4"
							onclick="saveDraft()">存稿</span> <span
							class="btn btn-info col-sm-1 col-sm-offset-1 prev"
							id="pageThreePre">上一步</span> <span
							class="btn btn-info col-sm-1 col-sm-offset-1 submitBtn" id="submitBtn">完成</span>
					</div>
				</div>

			</form>


		</div>

	</div>

	<script src="build/lib/sea.min.js"></script>
	<script src="js/base.js"></script>
	<script src="js/app/loanContract.js"></script>
	<script src="js/loan_contract/daikuan_common.js"></script>
	<script type="text/javascript">
		$(function() {
			seajs.use('common', function(c) {
				c.navInit();
				c.getDayly();				
				c.delItem('del');
				c.format();
			});
			seajs.use('loanContract', function(l) {
				l.LoanStyle();
				l.addItem('addInfo');
				l.city([ "${contract.areaProvince}", "${contract.areaCity}",
						"${contract.areaDistrict}" ]);
				//      l.city(["广东省","广州市","天河区"]);
				l.ContractProcess();
				l.annualRate();
				l.createRepayPlan();
				l.employeeInfoTab();
				l.newPlan();
			})
		});
		function saveDraft() {
			$("#dataForm").attr("action", "loanContract/saveDraft");
			$("#dataForm").submit();
		}
	</script>
</body>
</html>
