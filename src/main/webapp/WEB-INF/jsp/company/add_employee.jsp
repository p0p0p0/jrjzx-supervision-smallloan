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
<link rel="stylesheet" href="build/lib/layer/skin/layer.css">
<link rel="stylesheet"
	href="build/lib/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>

	<div class="container">
		<div class="add_shareholder mt100">
			<legend> 添加员工记录 </legend>
			<div class="form-group">
				<label class="col-sm-offset-4"> <input type="radio"
					name="shareholder" id="corporate_shareholder" checked
					value="option1"> 高管
				</label> <label class="col-sm-offset-2"> <input type="radio"
					name="shareholder" id="natural_shareholder" value="option2">
					普通员工
				</label>
				<p style="width: 100%; text-align: center;">
					<span>*</span>为必填项
				</p>
			</div>
			<!--高管职员表单-->
			<form class="form-horizontal cur corporate_shareholder"
				action="employees/save_emp" method="post">
				<input type="hidden" name="positionType" value="1" id="positionType" />
				<input type="hidden" name="position" value="董事长" id="position" />
				<fieldset>
					<div class="form-group">
						<label class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-2 required">
							<input class="form-control " type="text" required name="name" />
						</div>

						<label class="col-sm-2 control-label col-sm-offset-2">性别</label>
						<div class="col-sm-2 required">
							<select name="sex" required class="form-control">
								<option value="1">男</option>
								<option value="2">女</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">证件类型</label>
						<div class="col-sm-2 required">
							<select class="form-control" required name="cardType">
								<c:forEach items="${lexicon.dataOptions }" var="option">
									<option value="${option.optionKey }" />${option.optionValue }
                        	</c:forEach>
							</select>

						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">证件号码</label>
						<div class="col-sm-2 required">
							<input class="form-control" type="text" required name="cardNumber" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">现任职务</label>
						<div class="col-sm-2 required">
							<select onchange="changePos(this)" required class="form-control"
								id="selePos">
								<option value="1" selected="selected">董事长</option>
								<option value="2">执行董事</option>
								<option value="3">董事</option>
								<option value="4">监事长</option>
								<option value="5">监事</option>
								<option value="6">总经理</option>
								<option value="7">副总经理</option>
								<option value="8">风控总监</option>
								<option value="9">财务总监</option>
								<option value="10">业务总监</option>
								<option value="11">其他高管</option>
							</select>

						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">学历</label>
						<div class="col-sm-2 ">
							<select class="form-control" name="education">
								<option value="1">博士</option>
								<option value="2">硕士</option>
								<option value="3">本科</option>
								<option value="4">大专</option>
								<option value="5">高中及以下</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">专业技能职称</label>
						<div class="col-sm-2 ">
							<input class="form-control" id="business_license" type="text"
								name="title" />
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">入职日期</label>
						<div class="col-sm-2 required">
							<input class="form-control form_datetime" required name="entryTime" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">状态</label>
						<div class="col-sm-2 required">
							<select name="isonjob" required class="form-control">
								<option value="1">在职</option>
								<option value="0">离职</option>
							</select>
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">联系电话/手机</label>
						<div class="col-sm-2 required">
							<input class="form-control" required name="phone" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">离职日期</label>
						<div class="col-sm-2 ">
							<input class="form-control form_datetime" name="quitTime" />
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">电子邮件</label>
						<div class="col-sm-2 ">
							<input class="form-control" name="email" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label ">通信地址</label>
						<div class="col-sm-8 required business_address">
							<input class="form-control" type="text" name="address"
								placeholder="详细地址（**省**市**区（县）**街道（镇）、村（路）****）" />
						</div>
					</div>
					<div class="table-template t1">
						<div class="table-info">
							<h4 class="table-title pull-left">学习经历</h4>
						</div>
						<div class="addSpan addInfo">
							<a href="javascript:;" class="btn btn-primary pull-right add-btn">添加</a>
						</div>

						<div class="table-content">
							<table class="table table-hover table-bordered cur"
								style="text-align: center;">

								<thead class="thead-col">
									<tr>
										<th>起始时间</th>
										<th>截止时间</th>
										<th>学校</th>
										<th>专业</th>
										<th>学历</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody class="study">
									<tr>
										<td><input type="text" class="dateIcon form_datetime"
											name="learningExps[0].startTime"><i
											class="glyphicon glyphicon-calendar"></i></td>
										<td><input type="text" class="dateIcon form_datetime"
											name="learningExps[0].endTime"><i
											class="glyphicon glyphicon-calendar"></i></td>
										<td><input type="text" name="learningExps[0].school"></td>
										<td><input type="text"
											name="learningExps[0].professional"></td>
										<td><input type="text"
											name="learningExps[0].qualifications"></td>
										<td><a class="del" href="javascript:;">删除</a></td>
									</tr>

								</tbody>

							</table>

						</div>

					</div>
					<div class="table-template t2">
						<div class="table-info">
							<h4 class="table-title pull-left">工作经历</h4>
						</div>
						<div class="addSpan addInfo">
							<a href="javascript:;" class="btn btn-primary pull-right add-btn">添加</a>
						</div>
						<div class="table-content">
							<table class="table table-hover table-bordered cur"
								style="text-align: center;">

								<thead class="thead-col">
									<tr>
										<th>起始时间</th>
										<th>截止时间</th>
										<th>单位</th>
										<th>职务</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody class="work">
									<tr>
										<td><input type="text" name="workExps[0].startTime"
											class="dateIcon form_datetime"><i
											class="glyphicon glyphicon-calendar"></i></td>
										<td><input type="text" name="workExps[0].endTime"
											class="dateIcon form_datetime"><i
											class="glyphicon glyphicon-calendar"></i></td>
										<td><input type="text" name="workExps[0].company"></td>
										<td><input type="text" name="workExps[0].position"></td>
										<td><a class="del" href="javascript:;">删除</a></td>
									</tr>

								</tbody>

							</table>

						</div>

					</div>
					<div class="table-template">
						<div class="table-info">
							<h4 class="table-title pull-left">家庭成员</h4>
						</div>
						<div class="addSpan addInfo">
							<a href="javascript:;" class="btn btn-primary pull-right add-btn">添加</a>
						</div>
						<div class="table-content">
							<table class="table table-hover table-bordered cur"
								style="text-align: center;">

								<thead class="thead-col">
									<tr>
										<th>关系</th>
										<th>姓名</th>
										<th>性别</th>
										<th>现学习/工作单位</th>
										<th>操作</th>
									</tr>
								</thead>
								<tbody class="member">
									<tr>
										<td><input type="text" name="familys[0].relation"></td>
										<td><input type="text" name="familys[0].name"></td>
										<td><select class="form-control" name="familys[0].sex"
											style="text-align: center; width: 60%; margin: 0 20%">
												<option value="1">男</option>
												<option value="2">女</option>
										</select></td>
										<td><input type="text" name="familys[0].curUnit"></td>
										<td><a class="del" href="javascript:;">删除</a></td>
									</tr>

								</tbody>

							</table>

						</div>

					</div>
					<div class="form-group last">
						<button type="submit"
							class="btn btn-info col-sm-1 col-sm-offset-4">提交</button>
						<button type="reset" class="btn btn-info col-sm-1 col-sm-offset-1">取消</button>
					</div>
				</fieldset>
			</form>

			<!--普通职员表单-->
			<form class="form-horizontal natural_shareholder"
				action="employees/save_emp" method="post">
				<input type="hidden" name="positionType" value="50"
					id="positionType" />
				<fieldset>
					<div class="form-group">
						<label class="col-sm-2 control-label">姓名</label>
						<div class="col-sm-2 required">
							<input class="form-control " type="text" name="name" />
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">性别</label>
						<div class="col-sm-2 required">
							<select name="sex" class="form-control">
								<option value="1">男</option>
								<option value="2">女</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">证件类型</label>
						<div class="col-sm-2 required">
							<select class="form-control" name="cardType">
								<c:forEach items="${lexicon.dataOptions }" var="option">
									<option value="${option.optionKey }" />${option.optionValue }
                        	</c:forEach>
							</select>

						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">证件号码</label>
						<div class="col-sm-2 required">
							<input class="form-control" type="text" name="cardNumber" />
						</div>
					</div>


					<div class="form-group">
						<label class="col-sm-2 control-label">学历</label>
						<div class="col-sm-2 required">
							<select class="form-control" name="education">
								<option value="1">博士</option>
								<option value="2">硕士</option>
								<option value="3">本科</option>
								<option value="4">大专</option>
								<option value="5">高中及以下</option>
							</select>
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">入职日期</label>
						<div class="col-sm-2 required">
							<input class="form-control form_datetime" type="text"
								name="entryTime" />
						</div>

					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">现任职务</label>
						<div class="col-sm-2 required">
							<input class="form-control " type="text" name="position" />
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">状态</label>
						<div class="col-sm-2 required">
							<select name="isonjob" class="form-control">
								<option value="1">在职</option>
								<option value="0">离职</option>
							</select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">部门</label>
						<div class="col-sm-2 required">
							<input class="form-control " type="text" name="department" />
						</div>
						<label class="col-sm-2 control-label col-sm-offset-2">电话/手机</label>
						<div class="col-sm-2 required">
							<input class="form-control " type="text" name="phone" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">离职日期</label>
						<div class="col-sm-2 required">
							<input class="form-control form_datetime" type="text"
								name="quitTime" />
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
				c.delItem('del');
			});
			seajs.use('baseData', function(b) {
				b.shareholderTab();
				b.addItem('addInfo');
			});
		});
		function changePos(sele) {
			$("#positionType").val($(sele).val());
			$("#position").val($(sele).find("option:selected").text());
		}
	</script>
</body>
</html>