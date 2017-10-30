<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>财务数据</title>

<link href="/image/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="/build/lib/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="/build/lib/easyui/easyui.css">
<link rel="stylesheet" type="text/css" href="/build/lib/easyui/icon.css">
<link rel="stylesheet" type="text/css" href="/build/lib/easyui/demo.css">
<link rel="stylesheet" href="/css/style.css">





<style>
.finance {
	width: 100%;
	overflow: hidden;
	margin: 30px 0;
	background: #fff;
	padding-top: 30px;
	padding-left: 15px;
	box-sizing: border-box;
}

.financial-char {
	width: 1300px;
	height: 400px;
	position: absolute;
	left: 0;
	top: 0;
}

.finance-left {
	width: 100px;
	height: 400px;
	background: #fff;
	float: left;
}

.finance-left>button {
	margin-top: 20px;
}
</style>
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>

	<a href="/finance/add_balance">添加资产负债表</a>&nbsp;&nbsp;
	<a href="/finance/add_profit">添加利润表</a>&nbsp;&nbsp;
	<a href="/finance/add_cash">添加现金流量表</a>&nbsp;&nbsp;


	<div class="container">
		<div class="finance ">
			<div class="finance-left">
				<button class="btn btn-primary">资产负债表</button>
				<button class="btn btn-primary">月度利润表</button>
				<button class="btn btn-primary">年度利润表</button>
				<button class="btn btn-primary">现金流量表</button>
			</div>
			<div class="btn-group btn-group-xs pull-right">
				<button type="button" class="btn btn-default">打印</button>
				<a type="button" class="btn btn-default" href="/finance/add_finance">添加</a>

			</div>
			<!--   <a href="javascript:;" class="pull-right" style="position: relative;top: -10px;z-index: 2">添加</a>-->
			<div
				style="width: 1000px; height: 400px; border: 1px solid #ddd; position: relative; overflow: hidden;">

				<div class="financial-char" id="financial-char"></div>
			</div>

			<table class="easyui-datagrid  " id="tableView"
				style="width: 1170px; height: 450px">
				<thead data-options="frozen:true">
					<tr>
						<th data-options="field:'item',width:80">名称</th>
					</tr>
				</thead>
				<thead>
					<tr id="month">
<!-- 						<th data-options="field:'month1',width:100,align:'center'">2015-01-01</th> -->
<!-- 						<th data-options="field:'month2',width:100,align:'center'">2015-01-02</th> -->
<!-- 						<th data-options="field:'month3',width:100,align:'center'">2015-01-03</th> -->
<!-- 						<th data-options="field:'month4',width:100,align:'center'">2015-01-04</th> -->
<!-- 						<th data-options="field:'month5',width:100,align:'center'">2015-01-05</th> -->
<!-- 						<th data-options="field:'month6',width:100,align:'center'">2015-01-06</th> -->
<!-- 						<th data-options="field:'month7',width:100,align:'center'">2015-01-07</th> -->
<!-- 						<th data-options="field:'month8',width:100,align:'center'">2015-01-08</th> -->
<!-- 						<th data-options="field:'month9',width:100,align:'center'">2015-01-09</th> -->
<!-- 						<th data-options="field:'month10',width:100,align:'center'">2015-01-10</th> -->
<!-- 						<th data-options="field:'month11',width:100,align:'center'">2015-01-11</th> -->
<!-- 						<th data-options="field:'month12',width:100,align:'center'">2015-01-12</th> -->
					</tr>
				</thead>
				<tbody id="balance_data">
				</tbody>
			</table>
		</div>

	</div>



	<script src="/build/lib/sea.min.js"></script>
	<script src="/js/base.js"></script>
	<script type="text/javascript"
		src="/build/lib/easyui/jquery.easyui.min.js"></script>
	<script src="/build/lib/echarts/echarts.js"></script>

	<script>
		$(function() {

			var $echarView = $('#financial-char'), // char
			$tableView = $('#tableView'), //table 视图
			$tabBtn = $('.finance-left>button'), // 却换按钮

			titleArr = [ '2015-01-01', '2015-01-02', '2015-01-03',
					'2015-01-04', '2015-01-05', '2015-01-06', '2015-01-07',
					'2015-01-08', '2015-01-09', '2015-01-10', '2015-01-11',
					'2015-01-12', ], //标题数组
			contentArr = [], // 存放一行内容数组
			titleName = '', //char 的标题

			url = "/html/static/json/fuzhai.json", urlArr = [
					"/html/static/json/fuzhai.json",
					"/html/static/json/niandu.json",
					"/html/static/json/xianjin.json",
					"/html/static/json/yuedu.json" ]

			$tabBtn.on('click', function() {
				var index = $(this).index();
				url = urlArr[index];
				render(0);
			})

			// 初次加载 生成第一行数据的绘制char
			render(0);
			// 根据行号绘制char
			function render(rowIndex) {
				$
						.ajax({
							url : url,
							dataType : 'json',
							success : function(data) {
								// 重定向url 更新tableView
								$tableView.datagrid({
									url : url
								});

								//重新绘制char
								contentArr = []; // 清空数组
								for ( var i in data[rowIndex]) {
									contentArr.push(data[rowIndex][i]);
								}
								titleName = contentArr.slice(1, 2).toString();
								contentArr.splice(0, 2);

								//横向滚动条联动
								var contents2 = $('div.datagrid-view2 div.datagrid-body');
								contents2
										.on(
												'mousedown',
												function() {
													contents2
															.on(
																	'scroll',
																	function(
																			event) {
																		var e = event
																				|| window.event;
																		var leftX = -e.currentTarget.scrollLeft;
																		$echarView
																				.css(
																						'left',
																						leftX);
																	});
												});
								renderchar();
							}
						})
			}

			function renderchar() {
				// 基于准备好的dom，初始化echarts实例
				var myChart = echarts.init(document
						.getElementById('financial-char'));
				// 指定图表的配置项和数据
				var option = {
					title : {
						subtext : '单位万元',
					},
					tooltip : {
						trigger : 'axis'
					},
					legend : {
						data : [ titleName ]
					},
					textStyle : {
						color : 'black'
					},
					backgroundColor : "white",
					xAxis : {
						type : 'category',
						boundaryGap : false,
						offset : 10,
						data : titleArr
					},
					yAxis : {
						type : 'value',
						show : true,
						offset : 10,
						axisLabel : {
						//                        formatter: '{value}万元'
						},
					},
					series : [ {
						name : titleName,
						type : 'line',
						data : contentArr,
						markPoint : {
							data : [ {
								type : 'max',
								name : '最大值'
							}, {
								type : 'min',
								name : '最小值'
							}, ]
						},
						itemStyle : {
							normal : {
								label : {
									show : true
								}
							}
						}
					}, ]
				};
				// 使用刚指定的配置项和数据显示图表。
				myChart.setOption(option);
			}
			;

			// 定义点击行的回调函数
			$tableView.datagrid({
				onClickRow : function(rowIndex) {
					render(rowIndex);
				}
			})

		});
		function test() {

			$
					.ajax({
						url : '/finance/show_balance',
						dataType : 'json',
						success : function(data) {
							var str = new Array;
							for (var i = 0; i < 59; i++) {
								str[i] = new Array;
							}
							var th = "";
							$
									.each(
											data,
											function(index, item) {
												str[0][index] = item.monetary;
												str[1][index] = item.receivable;
												str[2][index] = item.prepayment;
												str[3][index] = item.interestReceivable;
												str[4][index] = item.otherReceivable;
												str[5][index] = item.loanMoney;
												str[6][index] = item.loanLoss;
												str[7][index] = item.pendingCurrentAssets;
												str[8][index] = item.shortInvestment;
												str[9][index] = item.otherCurrentAssets;
												str[10][index] = item.totalCurrentAssets;
												str[11][index] = item.longInvestment;
												str[12][index] = item.totalLongInvestment;
												str[13][index] = item.fixedAssets;
												str[14][index] = item.depreciation;
												str[15][index] = item.netFixedAssets;
												str[16][index] = item.liquidationFixedAssets;
												str[17][index] = item.totalFixedAssets;
												str[18][index] = item.intangibleAssets;
												str[19][index] = item.deferredAssets;
												str[20][index] = item.prepaidExpenses;
												str[21][index] = item.longPrepaidExpenses;
												str[22][index] = item.totalOtherAssets;
												str[23][index] = item.totalAssets;
												str[24][index] = item.borrowing;
												str[25][index] = item.payable;
												str[26][index] = item.premium;
												str[27][index] = item.received;
												str[28][index] = item.payroll;
												str[29][index] = item.taxation;
												str[30][index] = item.interestPayable;
												str[31][index] = item.otherAccount;
												str[32][index] = item.otherPayable;
												str[33][index] = item.profitPayable;
												str[34][index] = item.bondIssued;
												str[35][index] = item.totalCurrentLiabilities;
												str[36][index] = item.longBorrowing;
												str[37][index] = item.longPayables;
												str[38][index] = item.totalLongLiabilities;
												str[39][index] = item.deferredTax;
												str[40][index] = item.totalLiabilities;
												str[41][index] = item.paidCapital;
												str[42][index] = item.capitalSurplus;
												str[43][index] = item.reserveSurplus;
												str[44][index] = item.legalSurplus;
												str[45][index] = item.publicWelfare;
												str[46][index] = item.undistributedProfit;
												str[47][index] = item.generalReserve;
												str[48][index] = item.totalOwnershipInterest;
												str[49][index] = item.totalLiabilitiesOwnershipInterest;
												str[50][index] = item.dateMonth;
												str[51][index] = item.leader;
												str[52][index] = item.informant;
												str[53][index] = item.reportDate;
												th += "<th data-options=\"field:'"+item.dateMonth+"' ,width:100\" >" + item.dateMonth
														+ "</th>"
											});
							var tr = "";
							for (var i = 0; i < str.length; i++) {
								tr += "<tr>";
								switch (i) {
								case 0:
									tr += "<td>货币资金</td>";
									break;
								case 1:
									tr += "<td>应收账款</td>";
									break;
								case 2:
									tr += "<td>预付账款</td>";
									break;
								case 3:
									tr += "<td> 应收利息</td>";
									break;
								case 4:
									tr += "<td>其他应收款</td>";
									break;
								case 5:
									tr += "<td>贷款</td>";
									break;
								case 6:
									tr += "<td>贷款损失准备</td>";
									break;
								case 7:
									tr += "<td>待处理流动资产净损失</td>";
									break;
								case 8:
									tr += "<td>短期投资</td>";
									break;
								case 9:
									tr += "<td>其他流动资产</td>";
									break;
								case 10:
									tr += "<td>流动资产合计</td>";
									break;
								case 11:
									tr += "<td>长期股权投资</td>";
									break;
								case 12:
									tr += "<td>长期投资合计</td>";
									break;
								case 13:
									tr += "<td>固定资产原价</td>";
									break;
								case 14:
									tr += "<td>累计折旧</td>";
									break;
								case 15:
									tr += "<td>固定资产净值</td>";
									break;
								case 16:
									tr += "<td>固定资产清理</td>";
									break;
								case 17:
									tr += "<td>固定资产合计</td>";
									break;
								case 18:
									tr += "<td>无效资产</td>";
									break;
								case 19:
									tr += "<td>递延资产</td>";
									break;
								case 20:
									tr += "<td>待摊费用</td>";
									break;
								case 21:
									tr += "<td>长期待摊费用</td>";
									break;
								case 22:
									tr += "<td>其他资产合计</td>";
									break;
								case 23:
									tr += "<td>资产合计</td>";
									break;
								case 24:
									tr += "<td>短期借款</td>";
									break;
								case 25:
									tr += "<td>应付账款</td>";
									break;
								case 26:
									tr += "<td>应付保费</td>";
									break;
								case 27:
									tr += "<td>预收账款</td>";
									break;
								case 28:
									tr += "<td>应付职工薪酬</td>";
									break;
								case 29:
									tr += "<td>应缴税费</td>";
									break;
								case 30:
									tr += "<td>应付利息</td>";
									break;
								case 31:
									tr += "<td>其他应缴款</td>";
									break;
								case 32:
									tr += "<td>其他应付款</td>";
									break;
								case 33:
									tr += "<td>应付利润</td>";
									break;
								case 34:
									tr += "<td>发行短期债券</td>";
									break;
								case 35:
									tr += "<td>流动负债合计</td>";
									break;
								case 36:
									tr += "<td>长期借款</td>";
									break;
								case 37:
									tr += "<td>长期应付款</td>";
									break;
								case 38:
									tr += "<td>长期负债合计</td>";
									break;
								case 39:
									tr += "<td>递延税款</td>";
									break;
								case 40:
									tr += "<td>负债合计</td>";
									break;
								case 41:
									tr += "<td>实收资本</td>";
									break;
								case 42:
									tr += "<td>资本公积</td>";
									break;
								case 43:
									tr += "<td>盈余公积</td>";
									break;
								case 44:
									tr += "<td>法定盈余公积</td>";
									break;
								case 45:
									tr += "<td>公益金</td>";
									break;
								case 46:
									tr += "<td>未分配利润</td>";
									break;
								case 47:
									tr += "<td>一般准备金</td>";
									break;
								case 48:
									tr += "<td>所有者权益合计</td>";
									break;
								case 49:
									tr += "<td>负债及所有者权益合计</td>";
									break;
								case 50:
									tr += "<td>所属月份</td>";
									break;
								case 51:
									tr += "<td>单位负责人</td>";
									break;
								case 52:
									tr += "<td>填报人</td>";
									break;
								case 53:
									tr += "<td>填报日期</td>";
									break;
								}
								for (var j = 0; j < str[i].length; j++) {
									tr += "<td>" + str[i][j] + "</td>";
								}
								tr += "</tr>";
							}
							$("#month").html(th);
							$("#balance_data").html(tr);
						}

					});
		}
		test();
	</script>

</body>
</html>