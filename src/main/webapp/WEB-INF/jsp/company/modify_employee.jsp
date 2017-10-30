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
<link rel="stylesheet"
	href="build/lib/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
	<link rel="stylesheet" href="build/lib/layer/skin/layer.css">
	<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>

	<div class="container">
		<div class="add_shareholder pt100">
			<legend> 修改员工信息 </legend>
			<div class="form-group">
				<c:if test="${emp.positionType<50 }">
					<label class="col-sm-offset-4"> 高管 </label>
				</c:if>
				<c:if test="${emp.positionType>=50 }">
					<label class="col-sm-offset-2"> 普通员工 </label>
				</c:if>

				<p style="width: 100%; text-align: center;">
					<span>*</span>为必填项
				</p>
			</div>
			<c:if test="${emp.positionType<50 }">
				<!--高管职员表单-->
				<form class="form-horizontal cur corporate_shareholder"
					action="employees/update_emp" method="post">
					<input type="hidden" name="positionType"
						value="${emp.positionType }" id="positionType" /> <input
						type="hidden" name="position" value="${emp.position }"
						id="position" /> <input type="hidden" name="id"
						value="${emp.id }" />
					<fieldset>
						<div class="form-group">
							<label class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-2 required">
								<input class="form-control" type="text" required name="name"
									value="${emp.name }" />
							</div>

							<label class="col-sm-2 control-label col-sm-offset-2">性别</label>
							<div class="col-sm-2 required">
								<select name="sex" required class="form-control">
									<option value="1"
										<c:if test="${emp.sex==1 }">selected="selected"</c:if>>男</option>
									<option value="2"
										<c:if test="${emp.sex==2 }">selected="selected"</c:if>>女</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">证件类型</label>
							<div class="col-sm-2 required">
								<select class="form-control" required name="cardType">
									<c:forEach items="${lexicon.dataOptions }" var="option">
										<option value="${option.optionKey }"
											<c:if test="${option.optionKey eq emp.cardType }">selected="selected"</c:if> />${option.optionValue }
                        	</c:forEach>
								</select>

							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">证件号码</label>
							<div class="col-sm-2 required">
								<input class="form-control" type="text" required name="cardNumber"
									value="${emp.cardNumber }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">现任职务</label>
							<div class="col-sm-2 required">
								<select onchange="changePos(this)" required class="form-control"
									id="selePos">
									<option value="1"
										<c:if test="${emp.positionType==1 }">selected="selected"</c:if>>董事长</option>
									<option value="2"
										<c:if test="${emp.positionType==2 }">selected="selected"</c:if>>执行董事</option>
									<option value="3"
										<c:if test="${emp.positionType==3 }">selected="selected"</c:if>>董事</option>
									<option value="4"
										<c:if test="${emp.positionType==4 }">selected="selected"</c:if>>监事长</option>
									<option value="5"
										<c:if test="${emp.positionType==5 }">selected="selected"</c:if>>监事</option>
									<option value="6"
										<c:if test="${emp.positionType==6 }">selected="selected"</c:if>>总经理</option>
									<option value="7"
										<c:if test="${emp.positionType==7 }">selected="selected"</c:if>>副总经理</option>
									<option value="8"
										<c:if test="${emp.positionType==8 }">selected="selected"</c:if>>风控总监</option>
									<option value="9"
										<c:if test="${emp.positionType==9 }">selected="selected"</c:if>>财务总监</option>
									<option value="10"
										<c:if test="${emp.positionType==10 }">selected="selected"</c:if>>业务总监</option>
									<option value="11"
										<c:if test="${emp.positionType==11 }">selected="selected"</c:if>>其他高管</option>
								</select>

							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">学历</label>
							<div class="col-sm-2 ">
								<select class="form-control" name="education">
									<option value="1"
										<c:if test="${emp.education==1 }">selected="selected"</c:if>>博士</option>
									<option value="2"
										<c:if test="${emp.education==2 }">selected="selected"</c:if>>硕士</option>
									<option value="3"
										<c:if test="${emp.education==3 }">selected="selected"</c:if>>本科</option>
									<option value="4"
										<c:if test="${emp.education==4 }">selected="selected"</c:if>>大专</option>
									<option value="5"
										<c:if test="${emp.education==5 }">selected="selected"</c:if>>高中及以下</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">专业技能职称</label>
							<div class="col-sm-2 ">
								<input class="form-control" id="business_license" type="text"
									name="title" value="${emp.title }" />
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">入职日期</label>
							<div class="col-sm-2 required">
								<input class="form-control form_datetime" required name="entryTime"
									value="<fmt:formatDate value="${emp.entryTime }" type="both"
										pattern="yyyy-MM-dd" />" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">状态</label>
							<div class="col-sm-2 required">
								<select name="isonjob" required class="form-control">
									<option value="1"
										<c:if test="${emp.isonjob==1 }">selected="selected"</c:if>>在职</option>
									<option value="0"
										<c:if test="${emp.isonjob==2 }">selected="selected"</c:if>>离职</option>
								</select>
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">联系电话/手机</label>
							<div class="col-sm-2 required">
								<input class="form-control" required name="phone" value="${emp.phone }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">离职日期</label>
							<div class="col-sm-2 ">
								<input class="form-control form_datetime" name="quitTime"
									value="<fmt:formatDate value="${emp.quitTime }" type="both"
										pattern="yyyy-MM-dd" />" />
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">电子邮件</label>
							<div class="col-sm-2 ">
								<input class="form-control" name="email" value="${emp.email }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label ">通信地址</label>
							<div class="col-sm-8 required business_address">
								<input class="form-control" type="text" required name="address"
									value="${emp.address }"
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
										<c:forEach items="${emp.learningExps }" var="learnintExp"
											varStatus="status">
											<tr>
												<input type="hidden"
													name="learningExps[${status.index }].id"
													value="${learnintExp.id }" />
												<td><input type="text" class="dateIcon form_datetime"
													name="learningExps[${status.index }].startTime"
													value="<fmt:formatDate value="${learnintExp.startTime }" type="both"
										pattern="yyyy-MM-dd" />"><i
													class="glyphicon glyphicon-calendar"></i></td>
												<td><input type="text" class="dateIcon form_datetime"
													name="learningExps[${status.index }].endTime"
													value="<fmt:formatDate value="${learnintExp.endTime }" type="both"
										pattern="yyyy-MM-dd" />"><i
													class="glyphicon glyphicon-calendar"></i></td>
												<td><input type="text"
													name="learningExps[${status.index }].school"
													value="${learnintExp.school }"></td>
												<td><input type="text"
													value="${learnintExp.professional }"
													name="learningExps[${status.index }].professional"></td>
												<td><input type="text"
													value="${learnintExp.qualifications }"
													name="learningExps[${status.index }].qualifications"></td>
												<td><a class="del" href="javascript:;">删除</a></td>
											</tr>
										</c:forEach>

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
										<c:forEach items="#{emp.workExps }" var="workExp"
											varStatus="status">
											<tr>
												<input type="hidden" name="workExps[${status.index }].id"
													value="${workExp.id }" />
												<td><input type="text"
													name="workExps[${status.index }].startTime"
													class="dateIcon form_datetime"
													value="<fmt:formatDate value="${workExp.startTime }" type="both"
										pattern="yyyy-MM-dd" />"><i
													class="glyphicon glyphicon-calendar"></i></td>
												<td><input type="text"
													name="workExps[${status.index }].endTime"
													class="dateIcon form_datetime"
													value="<fmt:formatDate value="${workExp.endTime }" type="both"
										pattern="yyyy-MM-dd" />"><i
													class="glyphicon glyphicon-calendar"></i></td>
												<td><input type="text"
													name="workExps[${status.index }].company"
													value="${workExp.company }"></td>
												<td><input type="text"
													name="workExps[${status.index }].position"
													value="${workExp.position }"></td>
												<td><a class="del" href="javascript:;">删除</a></td>
											</tr>
										</c:forEach>


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
										<c:forEach items="${emp.familys }" var="family"
											varStatus="status">
											<tr>
												<input type="hidden" name="familys[${status.index }].id"
													value="${family.id }" />
												<td><input type="text"
													name="familys[${status.index }].relation"
													value="${family.relation }"></td>
												<td><input type="text"
													name="familys[${status.index }].name"
													value="${family.name }"></td>
												<td><select class="form-control"
													name="familys[${status.index }].sex"
													style="text-align: center; width: 60%; margin: 0 20%">
														<option value="1"
															<c:if test="${family.sex==1 }">selected="selected"</c:if>>男</option>
														<option value="2"
															<c:if test="${family.sex==2 }">selected="selected"</c:if>>女</option>
												</select></td>
												<td><input type="text"
													name="familys[${status.index }].curUnit"
													value="${family.curUnit }"></td>
												<td><a class="del" href="javascript:;">删除</a></td>
											</tr>
										</c:forEach>

									</tbody>

								</table>

							</div>

						</div>
						<div class="form-group last">
							<button type="submit"
								class="btn btn-info col-sm-1 col-sm-offset-4">提交</button>
							<button type="reset"
								class="btn btn-info col-sm-1 col-sm-offset-1">取消</button>
						</div>
					</fieldset>
				</form>
			</c:if>
			<c:if test="${emp.positionType>=50 }">
				<!--普通职员表单-->
				<form class="form-horizontal cur corporate_shareholder"
					action="employees/update_emp" method="post">
					<input type="hidden" name="positionType" value="50"
						id="positionType" /> <input type="hidden" name="id"
						value="${emp.id }" />
					<fieldset>
						<div class="form-group">
							<label class="col-sm-2 control-label">姓名</label>
							<div class="col-sm-2 required">
								<input class="form-control " type="text" name="name"
									value="${emp.name }" />
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">性别</label>
							<div class="col-sm-2 required">
								<select name="sex" class="form-control">
									<option value="1"
										<c:if test="${emp.sex==1 }">selected="selected"</c:if>>男</option>
									<option value="2"
										<c:if test="${emp.sex==2 }">selected="selected"</c:if>>女</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">证件类型</label>
							<div class="col-sm-2 required">
								<select class="form-control" name="cardType">
									<c:forEach items="${lexicon.dataOptions }" var="option">
										<option value="${option.optionKey }"
											<c:if test="${option.optionKey eq emp.cardType }">selected="selected"</c:if> />${option.optionValue }
                        	</c:forEach>
								</select>

							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">证件号码</label>
							<div class="col-sm-2 required">
								<input class="form-control" type="text" name="cardNumber"
									value="${emp.cardNumber }" />
							</div>
						</div>


						<div class="form-group">
							<label class="col-sm-2 control-label">学历</label>
							<div class="col-sm-2 required">
								<select class="form-control" name="education">
									<option value="1"
										<c:if test="${emp.education==1 }">selected="selected"</c:if>>博士</option>
									<option value="2"
										<c:if test="${emp.education==2 }">selected="selected"</c:if>>硕士</option>
									<option value="3"
										<c:if test="${emp.education==3 }">selected="selected"</c:if>>本科</option>
									<option value="4"
										<c:if test="${emp.education==4 }">selected="selected"</c:if>>大专</option>
									<option value="5"
										<c:if test="${emp.education==5 }">selected="selected"</c:if>>高中及以下</option>
								</select>
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">入职日期</label>
							<div class="col-sm-2 required">
								<input class="form-control form_datetime" type="text"
									value="<fmt:formatDate value="${emp.entryTime }" type="both"
										pattern="yyyy-MM-dd" />"
									name="entryTime" />
							</div>

						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">现任职务</label>
							<div class="col-sm-2 required">
								<input class="form-control " type="text" name="position"
									value="${emp.position }" />
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">状态</label>
							<div class="col-sm-2 required">
								<select name="isonjob" class="form-control">
									<option value="1"
										<c:if test="${emp.isonjob==1 }">selected="selected"</c:if>>在职</option>
									<option value="0"
										<c:if test="${emp.isonjob==2 }">selected="selected"</c:if>>离职</option>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">部门</label>
							<div class="col-sm-2 required">
								<input class="form-control " type="text" name="department"
									value="${emp.department }" />
							</div>
							<label class="col-sm-2 control-label col-sm-offset-2">电话/手机</label>
							<div class="col-sm-2 required">
								<input class="form-control " type="text" name="phone"
									value="${emp.phone }" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">离职日期</label>
							<div class="col-sm-2 required">
								<input class="form-control form_datetime" type="text"
									value="<fmt:formatDate value="${emp.quitTime }" type="both"
										pattern="yyyy-MM-dd" />"
									name="quitTime" />
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
			 seajs.use('common',function (c) {
		            c.navInit();c.getDayly();c.delItem('del');
		        });
		        seajs.use('baseData',function (b) {
		            b.shareholderTab();b.addItem('addInfo');
		        });
		});
		function changePos(sele) {
			$("#positionType").val($(sele).val());
			$("#position").val($(sele).find("option:selected").text());
		}
	</script>
</body>
</html>