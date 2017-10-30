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
<title>基础数据</title>
<link href="image/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"	href="build/lib/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"	href="build/lib/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>

	<div class="container">
		<div class="add_shareholder mt100">
			<legend> 修改股东记录 </legend>
			<div class="form-group">
				<p style="width: 100%; text-align: center;">
					<span>*</span>为必填项
				</p>
			</div>
			<c:if test="${partner.partnerType==1 }">
				<!--法人股东表单-->
				<form class="form-horizontal cur corporate_shareholder"
					action="partner/update_partner" method="post">
					<input type="hidden" name="partnerType"
						value="${partner.partnerType }" /> <input type="hidden" name="id"
						value="${partner.id }" />
					<fieldset>
						<div class="form-group">
							<label class="col-sm-2 control-label">机构全称</label>
							<div class="col-sm-2 required">
								<input class="form-control " type="text" name="name" required
									value="${partner.name }" />

							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">法定代表人</label>
							<div class="col-sm-2 required">
								<input class="form-control" type="text" required
									name="companyLegalPerson"
									value="${partner.companyLegalPerson }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">入股日期</label>
							<div class="col-sm-2 required">
								<input class="form-control form_datetime " type="text" required
									placeholder="xx-xx-xx" name="shareStart"
									value="<fmt:formatDate value="${partner.shareStart }"
											type="both" pattern="yyyy-MM-dd" />" />

							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">出资额</label>
							<div class="col-sm-2 required addon-outer">
								<div class="input-group">
									<input type="text" class="form-control" name="capitalAmount" onchange="resetProp('capital',this)"
										value="${partner.capitalAmount }" /> <span
										class="input-group-addon">万元</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">联系电话</label>
							<div class="col-sm-2 required">
								<input class="form-control " type="text" required
									name="phone" value="${partner.phone }" />

							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">出资比例</label>
							<div class="col-sm-2 addon-outer">
								<div class="input-group">
									<input type="text" class="form-control" id="capital"
										name="capitalProportion" value="${partner.capitalProportion }">
									<span class="input-group-addon">%</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">营业执照编号</label>
							<div class="col-sm-2 required">
								<input class="form-control" id="business_license" type="text" required
									name="companyLicenseCode"
									value="${partner.companyLicenseCode }" />
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">统一信用代码</label>
							<div class="col-sm-2 required">
								<input class="form-control" id="credit_code" type="text" required
									name="companyCreditCode" value="${partner.companyCreditCode }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">是否上市</label>
							<div class="col-sm-2 required">
								<select name="companyListed" required class="form-control">
									<option value="1"
										<c:if test="${partner.companyListed==1 }">selected="selected"</c:if>>是</option>
									<option value="0"
										<c:if test="${partner.companyListed==0 }">selected="selected"</c:if>>否</option>
								</select>
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">是否国有企业</label>
							<div class="col-sm-2 required">
								<select name="companyStateOwned" required class="form-control">
									<option value="1"
										<c:if test="${partner.companyStateOwned==1 }">selected="selected"</c:if>>是</option>
									<option value="0"
										<c:if test="${partner.companyStateOwned==0 }">selected="selected"</c:if>>否</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">电子邮件</label>
							<div class="col-sm-2">
								<input class="form-control" type="text" name="email"
									value="${partner.email }" />
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">传真</label>
							<div class="col-sm-2" style="margin-bottom: 25px;">
								<input class="form-control" id="fax" type="text"
									name="companyFax" value="${partner.companyFax }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label ">注册地址</label>
							<div class="col-sm-8 required business_address">
								<input class="form-control" type="text"
									name="companyRegistAddress"
									value="${partner.companyRegistAddress }"
									placeholder="详细地址（**省**市**区（县）**街道（镇）、村（路）****）" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label ">通信地址</label>
							<div class="col-sm-8 required business_address">
								<input class="form-control" type="text" required name="address"
									value="${partner.address }"
									placeholder="详细地址（**省**市**区（县）**街道（镇）、村（路）****）" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label ">经营地址</label>
							<div class="col-sm-8 required business_address">
								<input class="form-control" type="text" required
									name="companyBusiAddress"
									value="${partner.companyBusiAddress }"
									placeholder="详细地址（**省**市**区（县）**街道（镇）、村（路）****）" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">经营范围</label>
							<div class="required business_scope">
								<textarea maxlength="300" rows="3" placeholder="300字以内" required
									name="companyBusiScope">${partner.companyBusiScope }</textarea>
							</div>
						</div>
						<div class="form-group">
							<c:if test="${partner.status==0 }">
								<button class="btn btn-info col-sm-1 col-sm-offset-4"
									disabled="disabled">待审核</button>
							</c:if>
							<c:if test="${partner.status==1 }">
								<button type="submit"
									class="btn btn-info col-sm-1 col-sm-offset-4">提交</button>
							</c:if>
							<a href="company/info"
								class="btn btn-info col-sm-1 col-sm-offset-1">取消</a>
						</div>
					</fieldset>
				</form>
			</c:if>
			<c:if test="${partner.partnerType==2 }">
				<!--自然人股东表单-->
				<form class="form-horizontal cur corporate_shareholder"
					method="post" action="partner/update_partner">
					<input type="hidden" name="partnerType"
						value="${partner.partnerType }" /> <input type="hidden" name="id"
						value="${partner.id }" />
					<fieldset>
						<div class="form-group">
							<label class="col-sm-2 control-label">股东名称</label>
							<div class="col-sm-2 required">
								<input class="form-control " type="text" name="name" required
									value="${partner.name }" />
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">性别</label>
							<div class="col-sm-2 required">
								<select name="personSex" required class="form-control">
									<option value="1"
										<c:if test="${partner.personSex==1 }">selected="selected"</c:if>>男</option>
									<option value="2"
										<c:if test="${partner.personSex==2 }">selected="selected"</c:if>>女</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">证件类型</label>
							<div class="col-sm-2 required">
								<select class="form-control" required name="personCardType">
									<c:forEach items="${lexicon.dataOptions }" var="option">
										<option value="${option.optionKey }"
											<c:if test="${option.optionKey eq partner.personCardType }">selected="selected"</c:if> />${option.optionValue }
                        	</c:forEach>
								</select>
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">证件号码</label>
							<div class="col-sm-2 required">
								<input class="form-control" type="text" required name="personCardNumber"
									value="${partner.personCardNumber }" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">出资额</label>
							<div class="col-sm-2 required addon-outer">
								<div class="input-group">
									<input type="text" class="form-control" required name="capitalAmount"
										value="${partner.capitalAmount }"
										onchange="resetProp('capital1',this)">
									<span class="input-group-addon">万元</span>
								</div>
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">联系电话/手机</label>
							<div class="col-sm-2 ">
								<div class="input-group">
									<input type="text" required class="form-control" name="phone"
										value="${partner.phone }">
								</div>
							</div>

						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">入股时间</label>
							<div class="col-sm-2 required">
								<input class="form-control form_datetime" type="text" required
									name="shareStart"
									value="<fmt:formatDate value="${partner.shareStart }"
											type="both" pattern="yyyy-MM-dd" />" />
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">出资比例</label>
							<div class="col-sm-2 ">
								<div class="input-group">
									<input type="text" class="form-control" id="capital1"
										value="${partner.capitalProportion }" name="capitalProportion">
									<span class="input-group-addon">%</span>
								</div>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">工作单位</label>
							<div class="col-sm-2 ">
								<input class="form-control" type="text" name="personWorkUnit"
									value="${partner.personWorkUnit }" />
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">电子邮件</label>
							<div class="col-sm-2 required">
								<input class="form-control" type="email" required name="email"
									value="${partner.email }" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-2 control-label">户口地址</label>
							<div class="col-sm-8 required business_address">
								<input class="form-control" type="text" required name="personAddress"
									value="${partner.personAddress }"
									placeholder="详细地址（**省**市**区（县）**街道（镇）、村（路）****）" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">通信地址</label>
							<div class="col-sm-8 required business_address">
								<input class="form-control" type="text" required name="address"
									value="${partner.address }"
									placeholder="详细地址（**省**市**区（县）**街道（镇）、村（路）****）" />
							</div>
						</div>
						<div class="form-group">
							<button type="submit"
								class="btn btn-info col-sm-1 col-sm-offset-4">提交</button>
							<button type="reset"
								class="btn btn-info col-sm-1 col-sm-offset-1">取消</button>
						</div>
					</fieldset>
				</form>
			</c:if>
		</div>

	</div>




	<script src="build/lib/sea.min.js"></script>
	<script src="js/base.js"></script>
	<script>
		$(function() {
			seajs.use('common', function(c) {
				c.navInit();
				c.getDayly();
			});
			 seajs.use('baseData',function (b) {
		        b.shareholderTab();
		    });
		});
		function resetProp(id,amount) {
			var regCap = ${company.registCapital};
			$("#" + id).val((($(amount).val()/regCap)*100).toFixed(2));
		}
	</script>
</body>
</html>