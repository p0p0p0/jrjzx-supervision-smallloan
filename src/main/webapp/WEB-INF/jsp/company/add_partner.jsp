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
<link rel="stylesheet" href="css/style.css">
<link href="image/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="build/lib/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" href="build/lib/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>

	<div class="container">
		<div class="add_shareholder pt100">
			<legend> 添加股东记录 </legend>
			<div class="form-group">
				<label class="col-sm-offset-4"> <input type="radio"
					name="shareholder" checked value="option1"> 法人股东
				</label> <label class="col-sm-offset-2"> <input type="radio"
					name="shareholder" value="option2"> 自然人股东
				</label>
				<p style="width: 100%; text-align: center;">
					<span>*</span>为必填项
				</p>
			</div>
			<!--法人股东表单-->
			<form class="form-horizontal cur corporate_shareholder"
				action="partner/save_partner" method="post">
				<input type="hidden" name="partnerType" value="1">
				<fieldset>
					<div class="form-group">
						<label class="col-sm-2 control-label">公司全称</label>
						<div class="col-sm-2 required">
							<input class="form-control " type="text" required name="name"/>

						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">法定代表人</label>
						<div class="col-sm-2 required">
							<input class="form-control" type="text" required name="companyLegalPerson" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">入股日期</label>
						<div class="col-sm-2 required">
							<input class="form-control form_datetime " type="text" required 
								name="shareStart" />

						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">出资额</label>
						<div class="col-sm-2 required addon-outer">
							<div class="input-group">
								<input type="text" class="form-control number" required name="capitalAmount">
								<span class="input-group-addon">万元</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">联系电话</label>
						<div class="col-sm-2 required">
							<input class="form-control " type="text" required 
								name="phone" />

						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">出资比例</label>
						<div class="col-sm-2 addon-outer">
							<div class="input-group">
								<input type="text" class="form-control" name="capitalProportion">
								<span class="input-group-addon">%</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">营业执照编号</label>
						<div class="col-sm-2 required">
							<input class="form-control" id="business_license" type="text" required
								name="companyLicenseCode" />
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">统一信用代码</label>
						<div class="col-sm-2 required">
							<input class="form-control" id="credit_code" type="password" required
								name="companyCreditCode" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">是否上市</label>
						<div class="col-sm-2 required">
							<select name="companyListed" class="form-control" required>
								<option value="">--请选择--</option>
								<option value="1">是</option>
								<option value="0">否</option>
							</select>
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">是否国有企业</label>
						<div class="col-sm-2 required">
							<select name="companyStateOwned" class="form-control" required>
								<option value="">--请选择--</option>
								<option value="1">是</option>
								<option value="0">否</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">电子邮件</label>
						<div class="col-sm-2">
							<input class="form-control" type="text" name="email" />
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">传真</label>
						<div class="col-sm-2" style="margin-bottom: 25px;">
							<input class="form-control" id="fax" type="text"
								name="companyFax" />
						</div>
					</div>				
					<div class="form-group">
						<label class="col-sm-2 control-label ">通信地址</label>
						<div class="col-sm-8 required business_address">
							<input class="form-control" type="text" name="address" required
								placeholder="详细地址（**省**市**区（县）**街道（镇）、村（路）****）" />
						</div>
					</div>				
					<div class="form-group">
						<label class="col-sm-2 control-label">经营范围</label>
						<div class="required business_scope">
							<textarea maxlength="300" rows="3" placeholder="300字以内" required
								name="companyBusiScope"></textarea>
						</div>
					</div>
					<div class="form-group">
						<button type="submit"
							class="btn btn-info col-sm-1 col-sm-offset-4">提交</button>
						<button type="reset" class="btn btn-info col-sm-1 col-sm-offset-1">取消</button>
					</div>
				</fieldset>
			</form>

			<!--自然人股东表单-->
			<form class="form-horizontal natural_shareholder" method="post" action="partner/save_partner">
			<input type="hidden" name="partnerType" value="2"/>
				<fieldset>
					<div class="form-group">
						<label class="col-sm-2 control-label">股东名称</label>
						<div class="col-sm-2 required">
							<input class="form-control " type="text" required name="name" />
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">性别</label>
						<div class="col-sm-2 required">
							<select name="sex" class="form-control" required>
								<option value="1">男</option>
								<option value="2">女</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">证件类型</label>
						<div class="col-sm-2 required">
							<select class="form-control" required name="personCardType">
								<c:forEach items="${lexicon.dataOptions }" var="option">
									<option value="${option.optionKey }" />${option.optionValue }
                        	</c:forEach>
							</select>
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">证件号码</label>
						<div class="col-sm-2 required">
							<input class="form-control" required type="text" name="personCardNumber" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">出资额</label>
						<div class="col-sm-2 required addon-outer">
							<div class="input-group">
								<input type="text" class="form-control number" required name="capitalAmount">
								<span class="input-group-addon">万元</span>
							</div>
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">联系电话</label>
						<div class="col-sm-2 required">
							<input class="form-control" type="text" required
								name="phone" />
						</div>				

					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">入股时间</label>
						<div class="col-sm-2 required">
							<input class="form-control form_datetime" type="text" required
								name="shareStart" />
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">出资比例</label>
						<div class="col-sm-2 ">
							<div class="input-group">
								<input type="text" class="form-control" name="capitalProportion">
								<span class="input-group-addon">%</span>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">工作单位</label>
						<div class="col-sm-2 ">
							<input class="form-control" type="text" name="personWorkUnit" />
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">电子邮件</label>
						<div class="col-sm-2">
							<input class="form-control" type="email" required name="email" />
						</div>
					</div>

					<div class="form-group">
						<label class="col-sm-2 control-label">通信地址</label>
						<div class="col-sm-8 required business_address">
							<input class="form-control" type="text" name="address"
								placeholder="详细地址（**省**市**区（县）**街道（镇）、村（路）****）" />
						</div>
					</div>
					<div class="form-group">
						<button type="submit"
							class="btn btn-info col-sm-1 col-sm-offset-4">提交</button>
						<button type="reset" class="btn btn-info col-sm-1 col-sm-offset-1">取消</button>
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
				c.navInit();
				c.getDayly();
				c.format();
			});
		    seajs.use('baseData',function (b) {
		           b.shareholderTab();
		 	});
		})
	</script>
</body>
</html>