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
<title>账户数据</title>

<link href="image/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="build/lib/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="build/lib/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="css/style.css">


</style>
</head>
<body>

	<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>


	<div class="container pt100">
		<div class="account-table">
			<div class="table-template">
				<div class="table-info">
					<h4 class="table-title pull-left">账户信息</h4>
				</div>
				<div class="addSpan">
					<a href="account/add_account" class="btn btn-primary pull-right add-btn">添加</a>
				</div>
				<div class="table-content">
					<table class="table table-hover  cur"
						style="text-align: center; border: 1px solid #ddd;">
						<thead class="thead-col">
							<tr>
								<th>账户类型</th>
								<th>银行名称</th>
								<th>账号</th>
								<th>开户日期</th>
								<th>报备日期</th>
								<th>状态</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="record">
							<c:forEach items="${accounts }" var="account">
								<tr>
									<td><c:if test="${account.type==1 }">基本户</c:if> <c:if
											test="${account.type==2 }">一般户</c:if> <c:if
											test="${account.type==3 }">监管专用户</c:if></td>
									<td>${account.bankOption.optionValue }</td>
									<td>${account.account }</td>
									<td><fmt:formatDate value="${account.openTime }"
											type="both" pattern="yyyy-MM-dd" /></td>
									<td><fmt:formatDate value="${account.createTime }"
											type="both" pattern="yyyy-MM-dd" /></td>
									<td>${account.statusOption.optionValue }</td>
									<td><a
										href="account/modify_account?accountId=${account.id }">修改</a></td>
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
			<div class="table-template">
				<div class="table-info">
					<h4 class="table-title pull-left">银行账户余额报备</h4>
				</div>

				<div class="addSpan">
					<a href="account/add_balance" class="btn btn-primary pull-right add-btn">添加</a>
				</div>
				<div class="table-content">
					<table class="table table-hover"
						style="text-align: center; border: 1px solid #ddd;">
						<thead class="thead-col">
							<tr>
								<th>所属月份</th>
								<th>账户类型</th>
								<th>银行名称</th>
								<th>账户号</th>
								<th>账户余额（万元）</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="brecord">
							<tr>
								<td>2016-05</td>
								<td>监管专用户</td>
								<td>建设银行广东省分行</td>
								<td>44001420201053003290</td>
								<td>44001420201053003290</td>
								<td><a href="modify_balance?id=1">修改</a></td>
							</tr>


						</tbody>
						<tfoot>
							<tr>
								<td id='bpage' align="right" colspan="6"></td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
		</div>
	</div>



	<script src="build/lib/jquery.min.js"></script>
	<script src="build/lib/sea.min.js"></script>
	<script src="js/base.js"></script>

	<script>
		$(function() {
			seajs.use('common', function(c) {
				c.navInit();
			})
		});
		function loadBalance(index) {
			$
					.ajax({
						url : 'account/show_balance',
						dataType : 'json',
						data : {
							page : index
						},
						success : function(data) {
							var tbodyHTML = "";
							var typeArray = new Array();
							typeArray[1] = "基本户";
							typeArray[2] = "一般户";
							typeArray[3] = "监管专用户";
							$.each(data.list, function(index, item) {
								tbodyHTML += "<tr>";
								tbodyHTML += "<td>" + item.dateMonth + "</td>";
								tbodyHTML += "<td>"
										+ typeArray[item.account.type]
										+ "</td>";
								tbodyHTML += "<td>"
										+ item.account.bankOption.optionValue
										+ "</td>";
								tbodyHTML += "<td>" + item.account.account
										+ "</td>";
								tbodyHTML += "<td>" + item.money + "</td>";
								tbodyHTML += "<td><a href='account/modify_balance?id="
										+ item.id + "'>修改</a></td>";
								tbodyHTML += "</tr>";
							});
							$("#brecord").html(tbodyHTML);
							var pageHTML = '';
							var up = data.pageNum - 1;
							if (up < 1) {
								up = 1;
							}
							var next = data.pageNum + 1;
							if (next > data.pages) {
								next = data.pages;
							}
							pageHTML += "<a href=\"javascript:loadBalance(1)\" title=\"首页\">首页</a>&nbsp;&nbsp;"
							pageHTML += "<a href=\"javascript:loadBalance("
									+ up
									+ ")\" title=\"上一页\">上一页</a>&nbsp;&nbsp;";
							pageHTML += "<span>" + data.pageNum + "/"
									+ data.pages + "</span>";
							pageHTML += "&nbsp;&nbsp;<a href=\"javascript:loadBalance("
									+ next
									+ ")\" title=\"下一页\">下一页</a>&nbsp;&nbsp;";
							pageHTML += "<a href=\"javascript:loadBalance("
									+ data.pages + ")\" title=\"尾页\">尾页</a>";
							$("#bpage").html(pageHTML);

						}
					});
		}
		function Paging(index) {
			var RankInfo = document.getElementById('record');
			var totalPage = RankInfo.rows.length; //总条数  
			var pageSize = "${rows }";//每页显示条数  
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
			pageHTML += "<a class='p_first' href=\"javascript:Paging(1)\" title=\"首页\">首页</a>&nbsp;&nbsp;";
			var up = parseInt(currentPage) - 1;
			if (up < 1) {
				up = 1;
			}
			pageHTML += "<a class='p_prev' href=\"javascript:Paging(" + up
					+ ")\" title=\"上一页\">上一页</a>&nbsp;&nbsp;";
			pageHTML += "<span>" + currentPage + "/" + pageNumber + "</span>";

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
		Paging(1);
		loadBalance(1);
	</script>

</body>
</html>