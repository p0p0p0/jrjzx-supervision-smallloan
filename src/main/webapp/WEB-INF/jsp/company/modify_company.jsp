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
<link rel="stylesheet" type="text/css"
	href="build/lib/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="build/lib/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>
	<div class="container">
		<div class="modify-reg-info mt100">
			<form class="form-horizontal" method="post"
				action="company/update_company">
				<fieldset>
					<legend>
						修改注册资料
						<p>
							<span>*</span>为必填项
						</p>
					</legend>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="organization-name">机构全称</label>
						<div class="col-sm-2 required">
							<input class="form-control"  id="organization-name" name="name"
								type="text" required value='${company.name }' />

						</div>
						<label class="col-sm-2 control-label col-sm-offset-2"
							for="lagel_name">法人名字</label>
						<div class="col-sm-2 required">
							<input class="form-control" id="lagel_name" name="legalPerson"
								type="text" required value='${company.legalPerson }' />
						</div>

					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label " for="reg_capital">注册资本</label>
						<div class="col-sm-2 required">
							<div class="input-group">
								<input class="form-control number" id="reg_capital" type="text" required
									name="registCapital" value='${company.registCapital }' /> <span
									class="input-group-addon">万元</span>
							</div>
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2"
							for="org_code">组织机构代码</label>
						<div class="col-sm-2">
							<input class="form-control" id="org_code" type="text"
								value="${company.orgCode }" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="nationalTaxCode">国税编码</label>
						<div class="col-sm-2 required">
							<input class="form-control" id="nationalTaxCode" type="text" required
								name="nationalTaxCode" value="${company.nationalTaxCode }" />
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2"
							for="landTaxCode">地税编码</label>
						<div class="col-sm-2 required">
							<input class="form-control" id="landTaxCode" type="text" required
								name="landTaxCode" value="${company.landTaxCode }" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="establishment_time">设立时间</label>
						<div class="col-sm-2 required">
							<input class="form-control form_datetime" id="establishment_time"
								type="text" required name="foundTime"
								value='<fmt:formatDate value="${company.foundTime }" type="both"
										pattern="yyyy-MM-dd" />' />
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2"
							for="open_time">开业时间</label>
						<div class="col-sm-2 required">
							<input class="form-control form_datetime" id="open_time"
								type="text" required name="openTime"
								value='<fmt:formatDate value="${company.openTime }" type="both"
										pattern="yyyy-MM-dd" />' />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="business_license">营业执照编号</label>
						<div class="col-sm-2 required">
							<input class="form-control" id="business_license" type="text" required
								name="licenseCode" value="${company.licenseCode }" />
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2"
							for="credit_code">统一信用代码</label>
						<div class="col-sm-2 required">
							<input class="form-control" id="credit_code" type="text" required
								name="creditCode" value="${company.creditCode }" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">是否互联网小贷</label>
						<div class="col-sm-2 required">
							<select name="isInternet" class="form-control" required>
								<option value="">--请选择--</option>
								<option value="1"
									<c:if test="${company.isInternet==1 }">selected="selected"</c:if>>是</option>
								<option value="0"
									<c:if test="${company.isInternet==0 }">selected="selected"</c:if>>否</option>
							</select>
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">组织形式</label>
						<div class="col-sm-2 required">
							<select name="orgForm" class="form-control" required>
								<option value="">--请选择--</option>
								<option value="1"
									<c:if test="${company.orgForm==1 }">selected="selected"</c:if>>股份有限公司</option>
								<option value="2"
									<c:if test="${company.orgForm==2 }">selected="selected"</c:if>>有限责任公司</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="site_area">场地面积</label>
						<div class="col-sm-2">
							<div class="input-group">
								<input class="form-control" id="site_area" type="text" 
									name="acreage" value="${company.acreage }" /> <span
									class="input-group-addon">平方米</span>
							</div>

						</div>
						<label class="col-sm-2 control-label col-sm-offset-2" for="fax">传真</label>
						<div class="col-sm-2 required">
							<input class="form-control" id="fax" type="text" required name="fax" 
								value="${company.fax }" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label" for="contacts">联系人</label>
						<div class="col-sm-2 required">
							<input class="form-control" id="contacts" type="text" required
								name="linkman" value="${company.linkman }" />
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2"
							for="contact_number">联系电话</label>
						<div class="col-sm-2 required">
							<input class="form-control" id="contact_number" type="text" required
								name="tel" value="${company.tel }" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label " for="business_address">经营地址</label>
						<div class="col-sm-8 required business_address">
							<input class="form-control" id="business_address" type="text" required
								placeholder="详细地址（**省**市**区（县）**街道（镇）、村（路）****）" name="address"
								value="${company.address }" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">经营范围</label>
						<div class="required business_scope">
							<textarea required name="busiScope">${company.busiScope }</textarea>
						</div>
					</div>
					<div class="form-group">
						<button type="submit"
							class="btn btn-info col-sm-1 col-sm-offset-4">提交</button>
						<a href="company/info" class="btn btn-info col-sm-1 col-sm-offset-1">取消</a>
					</div>
				</fieldset>

			</form>
		</div>

	</div>

	<script src="build/lib/sea.min.js"></script>
	<script src="js/base.js"></script>
	<script>
		$(function() {
			seajs.use('common', function(c) {
				 c.navInit();c.getDayly();c.format();
			})
		})
	</script>

</body>
</html>