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
<title>添加资产负债表记录</title>

<link href="image/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="build/lib/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="build/lib/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>
	<div class="container pt100">
		<div class="contract-view ">
			<ul>
				<li style="width: 33%" class="active">资产负债表</li>
				<li style="width: 33%">利润表</li>
				<li style="width: 33%">现金流量表</li>
			</ul>
			<div class="contract-content">
				<div class="contract-view-table panel active">
					<form action="finance/save_balance" method="POST"
						enctype="multipart/form-data">
						<div class="form-inline " style="margin: 30px 0 30px 300px;">
							<div class="form-group">
								<p class="form-control-static">所属月份</p>
							</div>
							<div class="form-group">
								<input type="text" class="form-control form_monthtime"
									name="dateMonth" />
							</div>
						</div>
						<div class="form-inline" style="margin: 30px 0 30px 300px;">
							<div class="form-group">
								<p class="form-control-static">附件上传</p>
							</div>
							<div class="form-group">
								<input name="file" type="file" class="form-control" />
							</div>
							<a class="btn btn-default" href="finance/download?type=1">点击查看标准模版</a>
						</div>
						<div class="form-group">
							<button type="submit"
								class="btn btn-info col-sm-1 col-sm-offset-4">提交</button>
							<button type="reset"
								class="btn btn-info col-sm-1 col-sm-offset-1">取消</button>
						</div>
						<div class="table-template">
							<div class="table-info">
								<h4 class="table-title pull-left">资产负债表已发记录</h4>
							</div>


							<div class="table-content">
								<table class="table table-hover table-bordered cur"
									style="font-size: 12px;" id="balanTable">
									<tbody>

									</tbody>
								</table>
							</div>

						</div>
					</form>
				</div>
				<div class="contract-view-table panel">
					<form action="finance/save_profit" method="POST"
						enctype="multipart/form-data">
						<div class="form-inline " style="margin: 30px 0 30px 300px;">
							<div class="form-group">
								<p class="form-control-static">所属月份</p>
							</div>
							<div class="form-group">
								<input type="text" class="form-control form_monthtime"
									name="dateMonth">
							</div>
						</div>
						<div class="form-inline" style="margin: 30px 0 30px 300px;">
							<div class="form-group">
								<p class="form-control-static">附件上传</p>
							</div>
							<div class="form-group">
								<input type="file" class="form-control" name="file">
							</div>
							<a class="btn btn-default" href="finance/download?type=2">点击查看标准模版</a>
						</div>
						<div class="form-group">
							<button type="submit"
								class="btn btn-info col-sm-1 col-sm-offset-4">提交</button>
							<button type="reset"
								class="btn btn-info col-sm-1 col-sm-offset-1">取消</button>
						</div>
						<div class="table-template">
							<div class="table-info">
								<h4 class="table-title pull-left">利润表已发记录</h4>
							</div>
							<div class="table-content">
								<table class="table table-hover table-bordered cur"
									style="font-size: 12px;" id="profitTable">
									<tbody>

									</tbody>
								</table>
							</div>
						</div>
					</form>
				</div>
				<div class="contract-view-table panel">
					<div class="table-template t2">
						<div class="addSpan addInfo">
							<!-- 							<a href="javascript:;" class="pull-right">添加</a> -->
						</div>
						<form action="finance/save_cash" method="POST">
							<div class="table-content">
								<table class="table table-hover table-bordered cur"
									style="text-align: center;">
									<thead class="thead-col">
										<tr>
											<th>所属月份</th>
											<th>收到利息、劳务费用现金（元）</th>
											<!-- 											<th>操作</th> -->
										</tr>
									</thead>
									<tbody class="cash-flow">
										<tr>
											<td><input type="text" class="dateIcon form_monthtime"
												name="dateMonth"></td>
											<td><input type="text" name="money"></td>
											<!-- 											<td><a href="javascript:;" class="del">删除</a></td> -->
										</tr>
									</tbody>

								</table>
								<div class="form-group">
									<button type="submit"
										class="btn btn-info col-sm-1 col-sm-offset-4">提交</button>
									<button type="reset"
										class="btn btn-info col-sm-1 col-sm-offset-1">取消</button>
								</div>
								<div class="table-template">
									<div class="table-info">
										<h4 class="table-title pull-left">现金流量表已发记录</h4>
									</div>
									<table class="table table-hover table-bordered cur"
										style="text-align: center;">
										<thead class="thead-col">
											<tr>
												<th>所属月份</th>
												<th>收到利息、劳务费用现金（元）</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody class="cash-flow" id="cash_flow">
											<tr>
												<td></td>
												<td></td>
												<td><a href="javascript:;" class="del">删除</a></td>
											</tr>
										</tbody>
										<tfoot>
											<tr>
												<td colspan="3" align="right" id="tfoot"></td>
											</tr>
										</tfoot>
									</table>
								</div>
							</div>
						</form>
					</div>
				</div>

			</div>
		</div>
	</div>



	<script src="build/lib/sea.min.js"></script>
	<script src="js/base.js"></script>
	<script>
	$(function() {
			 seajs.use('common',function (c) {
			        c.navInit();
			        c.getMonthly();	 
			    });
			    seajs.use('extendAndRepay',function (e) {
			        e.contractTab();			    
			    });
			
		});

		$(function() {
			var balanceUrl = 'finance/show_balance_month'
			var profitUrl = 'finance/show_profit_month'
			function getMonthTable(id, url) {
				var monthArr = [];
				$
						.ajax({
							url : url,
							dataType : 'json',
							success : function(data) {
								for ( var i in data) {
									//去掉- 转成数字
									var newMouth = data[i].replace('-', '');
									if (newMouth !== "") {
										monthArr.push(parseInt(newMouth));
									}
									;
								}
								console.log(monthArr);
								// 当前属于哪一年
								var curYear = parseInt(new Date().getFullYear());
								// 数据中最小年份
								var minYear = parseInt((Math.min.apply(Math,
										monthArr)) / 100);

								// 根据差 算出需要加载表格行数
								var trList = curYear - minYear + 1;
								console.log(trList);
								var trHtml = '';
								var oneTr = '<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>';
								for (var k = 0; k < trList; k++) {
									trHtml += oneTr;
								}
								var $tbody = $('#' + id).find('tbody');
								$tbody.append($(trHtml));
								$tbody.find("tr td").css('width', "8.33%")

								//内容填充
								var row = 0, col = 0;
								$
										.each(
												monthArr,
												function(index, item) {
													var year = parseInt(item / 100)
													row = curYear - year; // 第几行
													col = item % 100 - 1; //第几列
													if (url == balanceUrl) {
														$tbody
																.find("tr")
																.eq(row)
																.find("td")
																.eq(col)
																.html(
																		'<a target="_blank" href="finance/show_balance?dateMonth='
																				+ data[index]
																				+ '">'
																				+ year
																				+ '年'
																				+ (col + 1)
																				+ '月'
																				+ '</a>');
													} else if (url == profitUrl) {
														$tbody
																.find("tr")
																.eq(row)
																.find("td")
																.eq(col)
																.html(
																		'<a target="_blank" href="finance/show_profit?dateMonth='
																				+ data[index]
																				+ '">'
																				+ year
																				+ '年'
																				+ (col + 1)
																				+ '月'
																				+ '</a>');
													}
												})
							},
							error : function(err) {
								console.log(err)
							}
						})
			}
			getMonthTable('balanTable', balanceUrl);
			getMonthTable('profitTable', profitUrl);
		})
		function loadCashFlow(page) {
			$
					.ajax({
						url : 'finance/show_cashflow',
						dataType : 'json',
						data : {
							"pageNum" : page
						},
						success : function(data) {
							var cashtbody = $("#cash_flow");
							var tbodyHTML = "";
							$
									.each(
											data.list,
											function(index, item) {
												tbodyHTML += "<tr><td>"
														+ item.dateMonth
														+ "</td><td>"
														+ item.money
														+ "</td><td><a href='javascript:delCashFlow("
														+ item.id
														+ ");' class='del'>删除</a></td></tr>";
											});
							cashtbody.html(tbodyHTML);
							var up = data.pageNum - 1;
							if (up < 1) {
								up = 1;
							}
							var next = data.pageNum + 1;
							if (next > data.pages) {
								next = data.pages;
							}
							var pageHTML = "";
							if (data.total > data.pageSize) {
								pageHTML += "<a href=\"javascript:loadCashFlow(1)\" title=\"首页\">首页</a>&nbsp;&nbsp;"
								pageHTML += "<a href=\"javascript:loadCashFlow("
										+ up
										+ ")\" title=\"上一页\">上一页</a>&nbsp;&nbsp;";
								pageHTML += "<span>" + data.pageNum + "/"
										+ data.pages + "</span>";
								pageHTML += "&nbsp;&nbsp;<a href=\"javascript:loadCashFlow("
										+ next
										+ ")\" title=\"下一页\">下一页</a>&nbsp;&nbsp;";
								pageHTML += "<a href=\"javascript:loadCashFlow("
										+ data.pages
										+ ")\" title=\"尾页\">尾页</a>";
								$("#tfoot").html(pageHTML);
							}
						}
					});
		}
		function delCashFlow(id) {
			$.ajax({
				url : 'finance/del_cashflow',
				dataType : 'json',
				data : {
					"cashFlowId" : id
				},
				success : function(data) {
					alert(data.message);
					loadCashFlow(1);
				}
			});
		}
		loadCashFlow(1);
	</script>
</body>
</html>