<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="zh-cn">
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>" />
    <title>新建展期还款计划</title>
</head>
<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>


<div class="container pt100">
    <div class="table-template t2">
        <h2>新建展期还款计划</h2>
        <div class="table-content">
        	<div style="float:left;">
        	<input type=hidden id="contractId" value="${contractId }">
        	
        	余额：<span id="remainMoneySum" style="color:red;">${remainMoneySum }</span>&nbsp;&nbsp;还款本金合计：<span id="moneySum" style="color:red;">0</span>
        	&nbsp;&nbsp;差值：<span id="subMoney" style="color:red;">${remainMoneySum }</span>
        	</div>
        		 <div class="addSpan addInfo" style="width:auto;">
        	    <a href="javascript:;" class="pull-right">添加</a>
        	</div>
	            <table  id="extendRepayPlanTable" class="table table-hover table-bordered cur" style="text-align: center;">
	                <thead class="thead-col">
	                <tr >
	                    <th>截止日期</th>
	                    <th>还款本金（元）</th>
	                    <th>还款利息（元）</th>
	                </tr>
	                </thead>
	                <tbody class="new_rep">
	                <tr>
	                    <td ><input  name="endDate"  type="text" class="dateIcon form_datetime"></td>
	                    <td ><input  class="number" name="money"  type="text" ></td>
	                    <td ><input  name="interest"  type="text" ></td>
	                </tr>
	                </tbody>
	
	            </table>
        
            <div class="form-group">
                <button type="submit" onclick="saveExtendRepayPlan()"  class="btn btn-info col-sm-1 col-sm-offset-4"  >提交</button>
                <button type="reset" class="btn btn-info col-sm-1 col-sm-offset-1" >取消</button>
            </div>
        </div>

    </div>
</div>







<script src="build/lib/bootstrap/bootstrap.min.js"></script>
<script src="build/lib/sea.min.js"></script>
<script src="js/base.js"></script>
<script src="js/bootstrap-validator/dist/validator.min.js"></script>
<script src="js/bootbox/bootbox.min.js"></script>
<script src="js/jrjzx/rejoice.js"></script>
<script src="js/jrjzx/extendAndRepay/add.js"></script>
<script>
        
</script>

</body>
</html>
