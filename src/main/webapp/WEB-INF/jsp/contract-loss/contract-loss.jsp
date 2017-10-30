<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="zh-cn">
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>" />
<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>
<title>报损业务管理</title>

  <style>
      
    </style>

</head>

<body>



	<div class="container pt100">
		<div class="table-template">
			<div class="table-info">
				<h4 class="table-title pull-left">报损业务管理</h4>
			</div>
			<table id="contract-loss-table"></table>
			<div id="contract-loss-toolbar">
				<span class="alert"></span> <a id="add-contract-loss"
					onclick="showContractLossDialog('新增合同报损')"
					class="create btn btn-primary" href="javascript:">新增</a>
			</div>
		</div>
	</div>


	<div id="contract-loss-dialog"  class="modal fade"
		data-backdrop="static">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title"></h4>
				</div>
				<div class="add-finance-data panel"
					style="padding-bottom: 0px; border: 0px;">
					<form id="contract-loss-form"   style="width: auto;">
						<input type="hidden" name="id" /> <input type="hidden"
							name="companyId" />
						<div class="input-group pull-left date">
							<span class="input-group-addon">合同编号</span> <input required
								type="text" data-contractExist="23"
								data-required-error="合同编号不能为空" name="contractNumber"
								class="form-control "> <span
								class="glyphicon  date-icon pull-left" aria-hidden="true"></span>
							<div class="help-block with-errors"></div>
						</div>
						<div class="input-group pull-left date" style="position:relative;">
							<span class="input-group-addon">坏账金额</span> <input required
								type="text" name="badMoney" class="form-control number" >
								 <span class="pull-left"  style="position:absolute;right:195px; z-index:2;bottom:5px">元</span>								 
						</div>
						
					
						<div class="input-group pull-left date">
							<span class="input-group-addon">定损日期</span> <input required
								type="text" name="lossDate" class="form-control form_datetime">
						</div>
						<div class="input-group pull-left date">
							<span class="input-group-addon">坏账类型</span> <select
								name="badType" style="width: 280px;" class="form-control">								
								<option value="1">普通坏账</option>
								<option value="2">逾期转坏账</option>
							</select>
						</div>
						<div class="input-group pull-left date">
							<span class="input-group-addon">是否核销</span> <select
								name="isWrittenOff" style="width: 280px;" class="form-control">
								<option value="1">已核销</option>
								<option value="0">未核销</option>
							</select>
						</div>
						<div class="input-group pull-left date">
							<span class="input-group-addon">后续工作</span>
							<textarea name="followUp" class="form-control"
								style="width: 280px;" rows="8"></textarea>
						</div>
<!-- 
						<div class="form-group btn" style="margin-top: 40px;">
							<div class="modal-footer">
								<button type="submit" class="btn btn-info">提交</button>
								<button type="button" data-dismiss="modal" class="btn btn-info ">取消</button>
							</div>
						</div> -->
					<div class="form-group btn" style="margin-top: 40px;">
							<div class="modal-footer">
								<button type="submit" class="btn btn-info">提交</button>
								<button type="button" data-dismiss="modal" class="btn btn-info ">取消</button>
							</div>
						</div> 
					</form>
				</div>


			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->

	<script src="build/lib/sea.min.js"></script>
	<script src="js/base.js"></script>
	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet"
		href="js/bootstrap-table/1.11.1/bootstrap-table.min.css">
	<!-- Latest compiled and minified JavaScript -->
	<script src="js/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
  <script src="build/lib/jquery.form.min.js"></script>
  <script src="build/lib/jQueryValidation/jquery.validate.min.js"></script>
	<!-- Latest compiled and minified Locales -->
	<script
		src="js/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
	<script src="js/bootstrap-validator/dist/validator.min.js"></script> 
	<script src="js/jrjzx/rejoice.js"></script>
	<script src="js/jrjzx/contract-loss/contract-loss.js"></script>
</body>
</html>
