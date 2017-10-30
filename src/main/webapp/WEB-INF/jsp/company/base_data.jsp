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
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>
	<div class="container">
		<div class="register-info pt100">
			<div class="table-template">
				<div class="table-info">
					<h4 class="table-title pull-left">注册信息</h4>
				</div>
				<c:if test="${company.status==0 }">
					<div class="addSpan">
						<font class="pull-right" color="red">待审核</font>
					</div>
				</c:if>
				<c:if test="${company.status==1 }">
					<div class="addSpan">
						<a href="company/modify_company" class="pull-right">修改</a>
					</div>
				</c:if>

				<div class="table-content">
					<table class="table table-hover cur">
						<tbody>
							<tr>
								<td >法人信息</td>
								<td class="fw"style="width: 45%;">${company.legalPerson }</td>
								<td>注册资本</td>
								<td  class="fw">${company.registCapital }万人民币</td>
							</tr>
							<tr>
								<td>设立时间</td>
								<td class="fw"><fmt:formatDate value="${company.foundTime}"
										type="both" pattern="yyyy-MM-dd" /></td>
								<td>开业时间</td>
								<td class="fw"><fmt:formatDate value="${company.openTime}" type="both"
										pattern="yyyy-MM-dd" /></td>
							</tr>
							<tr>
								<td>营业执照编号</td>
								<td class="fw">${company.licenseCode }</td>
								<td>统一信用代码</td>
								<td class="fw">${company.creditCode }</td>
							</tr>
							<tr>
								<td>是否互联网小贷</td>
								<td class="fw"><c:if test="${company.isInternet==1 }">是</c:if> <c:if
										test="${company.isInternet!=1 }">否</c:if></td>
								<td>组织形式</td>
								<td class="fw"><c:choose>
										<c:when test="${company.orgForm==1 }">股份有限公司</c:when>
										<c:when test="${company.orgForm==2 }">有限责任公司</c:when>
									</c:choose></td>
							</tr>
							<tr>
								<td>场地面积</td>
								<td class="fw">${company.acreage }平方米</td>
								<td>传真</td>
								<td class="fw">${company.fax }</td>
							</tr>
							<tr>
								<td>联系人</td>
								<td class="fw">${company.linkman }</td>
								<td>联系电话</td>
								<td class="fw">${company.tel }</td>
							</tr>
							<tr style="width: 100%; height: 100px;">
								<td style="width: 15%; line-height: 100px;">经营范围</td>
								<td class="fw" style="width: 85%; height: auto; line-height: 24px;"
									colspan="3">${company.busiScope }</td>
							</tr>
							<tr>
								<td>经营地址</td>
								<td class="fw" colspan="3">${company.address }</td>
							</tr>
						</tbody>
					</table>
				</div>

			</div>

		</div>
		<div class="shareholder-info">
			<div class="table-template">
				<div class="table-info">
					<h4 class="table-title pull-left">股东信息</h4>
				</div>
				<div class="addSpan">
					<a href="partner/add_partner" class="btn btn-primary pull-right add-btn">添加</a>					
					
				</div>
				<div class="table-content">
					<table class="table table-hover table-bordered ">
						<thead class="thead-col">
							<tr>
								<th>股东名称</th>
								<th>入股日期</th>
								<th>统一信用编码</th>
								<th>身份证号码</th>
								<th>出资额（万元）</th>
								<th>出资比例</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="record">
							<c:forEach items="${company.partners }" var="partner">
								<tr>
									<td>${partner.name }</td>
									<td><fmt:formatDate value="${partner.shareStart}"
											type="both" pattern="yyyy-MM-dd" /></td>
									<td>${partner.companyCreditCode }</td>
									<td>${partner.personCardNumber }</td>
									<td>${partner.capitalAmount }</td>
									<td>${partner.capitalProportion }%</td>
									<td><a
										href="partner/modify_partner?partnerId=${partner.id }">修改/查看</a>&nbsp;&nbsp;
										<c:if test="${partner.status==0 }">
											<font color="red">(待审核)</font>
										</c:if></td>
								</tr>
							</c:forEach>
						</tbody>
						<tfoot>
							<tr>
								<td id='page' align="right" colspan="7"></td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>

	<div class="container mb50">
		<div class="employee-info">
			<div class="table-template">
				<div class="table-info">
					<h4 class="table-title pull-left">员工信息</h4>					
						<button type="button" class="searchSpan btn btn-info pull-right">搜索
  							<span class="glyphicon glyphicon-arrow-down" ></span>
						</button>
				</div>
		
				<div class="searchContent">
					<form class="navbar-form navbar-left" style="width:100%;height:40px;">
						<div class="form-group">
							<div class="input-group">
								<span class="input-group-addon">姓名:</span> <input type="text"
									class="form-control" name="name" id="name">
							</div>
							<div class="input-group">
								<span class="input-group-addon">性别:</span> <select
									name="personSex" class="form-control" id="sex">
									<option value="" />不限制
									<option value="1">男</option>
									<option value="2">女</option>
								</select>
							</div>
							<div class="input-group">
								<span class="input-group-addon">学历:</span> <select
									name="education" id="education" class="form-control">
									<option value="" />不限制
									<option value="1">博士研究生</option>
									<option value="2">本科</option>
									<option value="3">专科</option>
									<option value="4">高中或以下</option>
								</select>
							</div>
							<div class="input-group">
								<span class="input-group-addon">部门:</span> <input type="text"
									class="form-control" name="department" id="department">
							</div>
							<div class="input-group">
								<span class="input-group-addon">状态:</span> <select
									name="isonjob" class="form-control" id="isonjob">
									<option value="" />不限制
									<option value="1">在职</option>
									<option value="0">离职</option>
								</select>
							</div>
						</div>
						<div style="width:100%;height:40px;">
							<input type="hidden" id="type" name="type" value="1" /> <input
								type="hidden" id="page" name="page" value="" /> <span
								class="btn btn-info searchBtn pull-right"
								onclick="jumpPage(1)">搜索</span>
						</div>
					</form>
				</div>
				<div>
					<div class="btn-group table-switch employee-btn" role="group">
						<button type="button" class="btn btn-default active"
							onclick="loadEmpoy(1)">高管信息</button>
						<button type="button" class="btn btn-default"
							onclick="loadEmpoy(2)">普通员工</button>
					</div>
				</div>
				<div class="addSpan">
					<a href="employees/add_emp" class="btn btn-primary pull-right add-btn">添加</a>	
				</div>
				<div class="table-content employee-tab">
					<table class="table table-hover table-bordered  tabTable cur"
						style="text-align: center;">
						<thead class="thead-col">
							<tr>
								<th>职务</th>
								<th>姓名</th>
								<th>性别</th>
								<th>身份证号码</th>
								<th>学历</th>
								<th>批复时间</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="executives">
							<tr>
								<td>董事长</td>
								<td>沙瑞金</td>
								<td>男</td>
								<td>1111112250XX</td>
								<td>本科</td>
								<td>2016-06-09</td>
								<td><span>修改/查看</span></td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td id='pageexecutives' align="right" colspan="7"></td>
							</tr>
						</tfoot>
					</table>
					<table class="table table-hover table-bordered tabTable "
						style="text-align: center;">
						<thead class="thead-col">
							<tr>
								<th>职务</th>
								<th>姓名</th>
								<th>性别</th>
								<th>身份证号码</th>
								<th>学历</th>
								<th>批复时间</th>
								<th>操作</th>
							</tr>

						</thead>
						<tbody id="emp">
							<tr>
								<td>董事长</td>
								<td>沙瑞金</td>
								<td>男</td>
								<td>1111112250XX</td>
								<td>本科</td>
								<td>2016-06-09</td>
								<td><span>修改/查看</span></td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td id='pageemp' align="right" colspan="7"></td>
							</tr>
						</tfoot>
					</table>
				</div>

			</div>
		</div>
	</div>

	<script src="build/lib/sea.min.js"></script>
	<script src="js/base.js"></script>
	<script>
		$(function() {
			seajs.use('common', function(c) {
				c.navInit();
				c.tableFun('employee-btn', 'employee-tab');
				c.toSearch();
			})
		});
		function loadEmpoy(type) {
			switch (type) {
			case 1:
				$("#type").val("1");
				$.ajax({
					url : 'employees/findByParam',
					dataType : 'json',
					data : {
						type : "1",
						name : $("#name").val(),
						sex : $("#sex").val(),
						education : $("#education").val(),
						department : $("#department").val(),
						isonjob : $("#isonjob").val(),
						page : $("#page").val()
					},
					success : function(data) {
						showData(data, "executives");
					}

				});
				break;
			case 2:
				$("#type").val("2");
				$.ajax({
					url : 'employees/findByParam',
					dataType : 'json',
					data : {
						type : "2",
						name : $("#name").val(),
						sex : $("#sex").val(),
						education : $("#education").val(),
						department : $("#department").val(),
						isonjob : $("#isonjob").val(),
						page : $("#page").val()
					},
					success : function(data) {
						showData(data, "emp");
					}

				});
				break;
			}
		}
		function jumpPage(page) {

			$("#page").val(page);
			if ($("#type").val() == 1) {
				loadEmpoy(1);
			} else {
				loadEmpoy(2);
			}
		}
		function showData(data, id) {
			$("#" + id).html("");
			$("#page").val("");
			$
					.each(
							data.list,
							function(index, item) {
								var tem1;
								if (item.sex == 1) {
									tem1 = '男';
								} else {
									tem1 = '女';
								}
								var tem2;
								switch (item.education) {
								case 1:
									tem2 = "博士";
									break;
								case 2:
									tem2 = "硕士";
									break;
								case 3:
									tem2 = "本科";
									break;
								case 4:
									tem2 = "大专";
								case 5:
									tem2 = "高中或以下";
									break;
								}
								$("#" + id)
										.append(
												"<tr><td>"
														+ item.position
														+ "</td><td>"
														+ item.name
														+ "</td><td>"
														+ tem1
														+ "</td><td>"
														+ item.cardNumber
														+ "</td><td>"
														+ tem2
														+ "</td><td>"
														+ new Date(
																item.signTime)
																.format("yyyy-MM-dd")
														+ "</td><td> <a href='employees/modify_emp?employeeId="
														+ item.id
														+ "'>修改/查看</a></td></tr>");

							});
			var pageHTML = '';
			if (data.total > data.pageSize) {
				var up = data.pageNum - 1;
				if (up < 1) {
					up = 1;
				}
				var next = data.pageNum + 1;
				if (next > data.pages) {
					next = data.pages;
				}
				pageHTML += "<a href=\"javascript:jumpPage(1)\" title=\"首页\">首页</a>&nbsp;&nbsp;"
				pageHTML += "<a href=\"javascript:jumpPage(" + up
						+ ")\" title=\"上一页\">上一页</a>&nbsp;&nbsp;";
				pageHTML += "<span>" + data.pageNum + "/" + data.pages
						+ "</span>";
				pageHTML += "&nbsp;&nbsp;<a href=\"javascript:jumpPage(" + next
						+ ")\" title=\"下一页\">下一页</a>&nbsp;&nbsp;";
				pageHTML += "<a href=\"javascript:jumpPage(" + data.pages
						+ ")\" title=\"尾页\">尾页</a>";
				$("#page" + id).html(pageHTML);
			}
		}
		Date.prototype.format = function(fmt) {
			var o = {
				"M+" : this.getMonth() + 1, //月份   
				"d+" : this.getDate(), //日   
				"h+" : this.getHours(), //小时   
				"m+" : this.getMinutes(), //分   
				"s+" : this.getSeconds(), //秒   
				"q+" : Math.floor((this.getMonth() + 3) / 3), //季度   
				"S" : this.getMilliseconds()
			//毫秒   
			};
			if (/(y+)/.test(fmt))
				fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
						.substr(4 - RegExp.$1.length));
			for ( var k in o)
				if (new RegExp("(" + k + ")").test(fmt))
					fmt = fmt.replace(RegExp.$1,
							(RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
									.substr(("" + o[k]).length)));
			return fmt;
		}
		function Paging(index) {
			var RankInfo = document.getElementById('record');
			var totalPage = RankInfo.rows.length; //总条数  
			var pageSize = "${rows }";
			//每页显示条数  
			var pageNumber = Math.ceil(totalPage / pageSize); //总页数  
			var currentPage = index;//当前页数  
			var start_row = (currentPage - 1) * pageSize;//开始显示的行  
			var end_row = currentPage * pageSize;//结束显示的行  
			end_row = (end_row > totalPage) ? totalPage : end_row;
			for (var i = 0; i < totalPage; i++) {
				var irow = RankInfo.rows[i];
				if (i >= start_row && i < end_row) {
					irow.style.display = 'table-row';
				} else {
					irow.style.display = 'none';
				}
			}
			var pageHTML = '';
			if (totalPage > pageSize) {
				pageHTML += "<a class='p_first' href=\"javascript:Paging(1)\" title=\"首页\">首页</a>&nbsp;&nbsp;";
				var up = parseInt(currentPage) - 1;
				if (up < 1) {
					up = 1;
				}
				pageHTML += "<a class='p_prev' href=\"javascript:Paging(" + up
						+ ")\" title=\"上一页\">上一页</a>&nbsp;&nbsp;";
				pageHTML += "<span>" + currentPage + "/" + pageNumber
						+ "</span>";

				var next = parseInt(currentPage) + 1;
				if (next > pageNumber) {
					next = pageNumber;
				}
				pageHTML += "&nbsp;&nbsp;<a class='p_next js_page' href=\"javascript:Paging("
						+ next + ")\" title=\"下一页\">下一页</a>&nbsp;&nbsp;";
				pageHTML += "<a  class='p_last js_page' href=\"javascript:Paging("
						+ pageNumber + ")\" title=\"尾页\">尾页</a>";
				if (totalPage == 0) {
					$("#page").html('');
				} else {
					$("#page").html(pageHTML);
				}
			}
		}

		Paging(1);
		loadEmpoy(1);
	</script>

</body>
</html>