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
<title>资产负债表</title>
<link rel="stylesheet" href="css/style.css">
<link href="image/favicon.ico" rel="shortcut icon" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="build/lib/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="build/lib/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>

	<div class="container mt100">
		<div class="table-template">
			<div class="table-info">
				<h4 class="table-title pull-left">资产负债表</h4>


			</div>
			<div class="table-content">
				<table class="table table-hover table-bordered">
					<thead>
						<tr class="label-info">
							<th style="width: 100px">单位:</th>
							<th colspan="2">${company.name }</th>
							<th style="width: 100px">周期：</th>
							<th>${dateMonth }</th>
							<th colspan="1">金额单位：人民币元</th>
						</tr>
						<tr class="tint-col">
							<th style="width: 100px">行次</th>
							<th>资产</th>
							<th>金额</th>
							<th style="width: 100px">行次</th>
							<th>资产</th>
							<th>金额</th>
						</tr>
					</thead>
					<c:if test="${!empty balance }">
						<tbody>
							<tr>
								<td>1</td>
								<td>流动资产：</td>
								<td colspan="1"></td>
								<td>33</td>
								<td>流动负债：</td>
								<td colspan="1"></td>
							</tr>
							<tr>
								<td>2</td>
								<td>货币资金</td>
								<td>${balance.monetary }</td>
								<td>34</td>
								<td>短期借款</td>
								<td>${balance.borrowing }</td>
							</tr>
							<tr>
								<td>3</td>
								<td>应收账款</td>
								<td>${balance.receivable }</td>
								<td>35</td>
								<td>应付帐款</td>
								<td>${balance.payable }</td>
							</tr>
							<tr>
								<td>4</td>
								<td>预付账款</td>
								<td>${balance.prepayment }</td>
								<td>36</td>
								<td>应付保费</td>
								<td>${balance.premium }</td>
							</tr>
							<tr>
								<td>5</td>
								<td>应收利息</td>
								<td>${balance.interestReceivable }</td>
								<td>37</td>
								<td>预收账款</td>
								<td>${balance.received }</td>
							</tr>
							<tr>
								<td>6</td>
								<td>其他应收款</td>
								<td>${balance.otherReceivable }</td>
								<td>38</td>
								<td>应付职工薪酬</td>
								<td>${balance.payroll }</td>
							</tr>
							<tr>
								<td>7</td>
								<td>贷款</td>
								<td>${balance.loanMoney }</td>
								<td>39</td>
								<td>应交税费</td>
								<td>${balance.taxation }</td>
							</tr>

							<tr>
								<td>8</td>
								<td>减：贷款损失准备</td>
								<td>${balance.loanLoss }</td>
								<td>40</td>
								<td>应付利息</td>
								<td>${balance.interestPayable }</td>
							</tr>
							<tr>
								<td>9</td>
								<td>待摊费用</td>
								<td>${balance.prepaidExpenses }</td>
								<td>41</td>
								<td>其他应交款</td>
								<td>${balance.otherAccount }</td>
							</tr>
							<tr>
								<td>10</td>
								<td>待处理流动资产净损失</td>
								<td>${balance.pendingCurrentAssets }</td>
								<td>42</td>
								<td>其他应付款</td>
								<td>${balance.otherPayable }</td>
							</tr>
							<tr>
								<td>11</td>
								<td>短期投资</td>
								<td>${balance.shortInvestment }</td>
								<td>43</td>
								<td>应付利润</td>
								<td>${balance.profitPayable }</td>
							</tr>
							<tr>
								<td>12</td>
								<td>其他流动资产</td>
								<td>${balance.otherCurrentAssets }</td>
								<td>44</td>
								<td>发行短期债券</td>
								<td>${balance.bondIssued }</td>
							</tr>
							<tr>
								<td>13</td>
								<td>流动资产合计：</td>
								<td>${balance.totalCurrentAssets }</td>
								<td>45</td>
								<td>流动负债合计：</td>
								<td>${balance.totalCurrentLiabilities }</td>
							</tr>
							<tr>
								<td>14</td>
								<td></td>
								<td>-</td>
								<td>46</td>
								<td>长期负债：</td>
								<td></td>
							</tr>

							<tr>
								<td>15</td>
								<td>长期投资：</td>
								<td></td>
								<td>47</td>
								<td>长期借款</td>
								<td>${balance.longBorrowing }</td>
							</tr>
							<tr>
								<td>16</td>
								<td>长期股权投资</td>
								<td>${balance.longInvestment }</td>
								<td>48</td>
								<td>长期应付款</td>
								<td>${balance.longPayables }</td>
							</tr>
							<tr>
								<td>17</td>
								<td>长期投资合计：</td>
								<td>${balance.totalLongInvestment }</td>
								<td>49</td>
								<td>长期负债合计：</td>
								<td>${balance.totalLongLiabilities }</td>
							</tr>
							<tr>
								<td>18</td>
								<td></td>
								<td>-</td>
								<td>50</td>
								<td></td>
								<td>-</td>
							</tr>
							<tr>
								<td>19</td>
								<td>固定资产：</td>
								<td></td>
								<td>51</td>
								<td>递延税款</td>
								<td>${balance.deferredTax }</td>
							</tr>
							<tr>
								<td>20</td>
								<td>固定资产原价</td>
								<td>${balance.fixedAssets }</td>
								<td>52</td>
								<td>负债合计：</td>
								<td>${balance.totalLiabilities }</td>
							</tr>
							<tr>
								<td>21</td>
								<td>减：累计折旧</td>
								<td>${balance.depreciation }</td>
								<td>53</td>
								<td></td>
								<td></td>
							</tr>

							<tr>
								<td>22</td>
								<td>固定资产净值</td>
								<td>${balance.netFixedAssets }</td>
								<td>54</td>
								<td>所有者权益：</td>
								<td></td>
							</tr>
							<tr>
								<td>23</td>
								<td>固定资产清理</td>
								<td>${balance.liquidationFixedAssets }</td>
								<td>55</td>
								<td>实收资本</td>
								<td>${balance.paidCapital }</td>
							</tr>
							<tr>
								<td>24</td>
								<td>固定资产合计：</td>
								<td>${balance.totalFixedAssets }</td>
								<td>56</td>
								<td>资本公积</td>
								<td>${balance.capitalSurplus }</td>
							</tr>
							<tr>
								<td>25</td>
								<td></td>
								<td></td>
								<td>57</td>
								<td>盈余公积</td>
								<td>${balance.reserveSurplus }</td>
							</tr>
							<tr>
								<td>26</td>
								<td>无形资产及其他资产：</td>
								<td></td>
								<td>58</td>
								<td>其中：法定盈余公积</td>
								<td>${balance.legalSurplus }</td>
							</tr>
							<tr>
								<td>27</td>
								<td>无形资产</td>
								<td>${balance.intangibleAssets }</td>
								<td>59</td>
								<td>公益金</td>
								<td>${balance.publicWelfare }</td>
							</tr>
							<tr>
								<td>28</td>
								<td>递延资产</td>
								<td>${balance.deferredAssets }</td>
								<td>60</td>
								<td>未分配利润</td>
								<td>${balance.undistributedProfit }</td>
							</tr>
							<tr>
								<td>29</td>
								<td>长期待摊费用</td>
								<td>${balance.longPrepaidExpenses }</td>
								<td>61</td>
								<td>一般准备金</td>
								<td>${balance.generalReserve }</td>
							</tr>
							<tr>
								<td>30</td>
								<td>无形资产及其他资产合计：</td>
								<td>${balance.totalOtherAssets }</td>
								<td>62</td>
								<td>所有者权益合计：</td>
								<td>${balance.totalOwnershipInterest }</td>
							</tr>
							<tr>
								<td>31</td>
								<td></td>
								<td>-</td>
								<td>63</td>
								<td></td>
								<td>-</td>
							</tr>
							<tr>
								<td>32</td>
								<td>资产合计：</td>
								<td>${balance.totalAssets }</td>
								<td>64</td>
								<td>负债及所有者权益合计：</td>
								<td>${balance.totalLiabilitiesOwnershipInterest }</td>
							</tr>
							<tr>
								<td>单位负责人</td>
								<td>${balance.leader }</td>
								<td>填表人</td>
								<td>${balance.informant }</td>
								<td>报出日期</td>
								<td>${balance.reportDate }</td>
							</tr>

							<!--                 <tr> -->
							<!--                     <td rowspan="3">填报说明:</td> -->
							<!--                     <td colspan="7"  class="text-left">1．本表由各小额贷款公司填报。</td> -->
							<!--                 </tr> -->
							<!--                 <tr> -->
							<!--                     <td colspan="7" class="text-left">2．本文件中辖区指报送部门所在行政区划。本表报送范围为在辖区内登记注册的小额贷款公司 -->
							<!--                         （含辖区外小额贷款公司法人机构在本辖区设立的分支机构，但不含本辖区小额贷款公司法人机构在辖区外设立的分支机构）。</td> -->
							<!--                 </tr> -->
							<!--                 <tr> -->
							<!--                     <td colspan="7"  class="text-left">3. 报表填列的金额精确到分。</td> -->
							<!--                 </tr> -->







						</tbody>
					</c:if>
				</table>
			</div>

		</div>
	</div>


	<script src="build/lib/sea.min.js"></script>
	<script src="js/base.js"></script>
	<script>
		$(function() {
			   seajs.use('common',function (c) {
				      c.navInit();
				    });
		});
	</script>
</body>
</html>