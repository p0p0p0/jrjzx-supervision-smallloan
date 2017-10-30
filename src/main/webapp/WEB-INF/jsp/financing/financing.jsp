<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
    <title>融资数据</title>
</head>

<div class="container pt100" >
    <div class="finance-table">
        <div class="table-template">
            <div class="table-info">
                <h4 class="table-title pull-left">单笔融资</h4>
            </div>
            <!-- single-finance start -->
            <table id="single-finance-table">
            </table>
            <div id="single-finance-toolbar">
               <span class="alert"></span>
		                 <a id="add-single-finance" onclick="showSingleFinanceDialog('新增单笔融资')"  class="create btn btn-primary" href="javascript:">新增</a>
            </div>
        </div>
    </div>
</div>



 <div id="single-finance-dialog" class="modal fade" data-backdrop="static" >
        	<div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"></h4>
                </div>
    <div class="add-finance-data panel">
<form id="single-finance-form">
            <div class="input-group pull-left date">
                <span class="input-group-addon">融资日期</span>
                <input required type="text" name="financeDate" class="form-control form_datetime" >
                <span class="glyphicon  date-icon pull-left" aria-hidden="true"></span>
            </div>
            <div class="input-group pull-left date">
                <span class="input-group-addon">到&nbsp;&nbsp;期&nbsp;日</span>
                <input required type="text" name="endDate" class="form-control form_datetime" >
                <span class="glyphicon  date-icon pull-left" aria-hidden="true"></span>
            </div>
            <div class="input-group pull-left date">
                <span class="input-group-addon">融资渠道</span>
                <input required type="text" name="channel" class="form-control date " >
            </div>
            <div class="input-group pull-left date" style="width:600px;">
                <span class="input-group-addon">融资利率</span>
                <input required type="text" name="rate" class="form-control date " >
                <span class=" date-icon "style="right: 100px" >%（折合年化）</span>
            </div>
            <div class="input-group pull-left date">
                <span class="input-group-addon">融资金额</span>
                <input required type="text" name="money" class="form-control date " >
                <span class=" date-icon "style="right: 40px" >万元</span>
            </div>
            <div class="input-group pull-left date">
                <span required class="input-group-addon">融资费率</span>
                <input type="text" name="costRate" class="form-control date " >
                <span class=" date-icon "style="right: 60px" >%（年）</span>
            </div>
            <div class="input-group pull-left date">
                <span class="input-group-addon">担保费率</span>
                <input required type="text" name="guaranteeRate" class="form-control date " >
                <span class=" date-icon "style="right: 60px" ></span>
            </div>
            <div class="form-group btn" style="margin-top: 40px;">
            <div  class="modal-footer">
                <button type="submit" class="btn btn-info">提交</button>
                <button type="button" data-dismiss="modal" class="btn btn-info ">取消</button>
            </div>
            </div>

        </form>
</div>
    </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->

      <!-- single-finance end -->



<div class="container mt30"> 
    <div class="finance-table">
        <div class="table-template">
            <div class="table-info">
                <h4 class="table-title pull-left">月度融资</h4>
            </div>
            <!-- month-finance start -->
            <table id="month-finance-table">
            </table>
            <div id="month-finance-toolbar">
               <span class="alert"></span>
		                 <a id="add-month-finance" onclick="showMonthFinanceDialog('新增月度融资')"  class="create btn btn-primary" href="javascript:">新增</a>
            </div>
        </div>
    </div>
</div>


  
<div id="month-finance-dialog" class="modal fade" data-backdrop="static" >
        	<div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"></h4>
                </div>
    <div class="add-finance-data panel">
<form id="month-finance-form">
            <div class="input-group pull-left date">
                <span class="input-group-addon">融资月份</span>
                <input required type="text" name="financeMonth" class="form-control form_monthtime" >
                <span class="glyphicon  date-icon pull-left" aria-hidden="true"></span>
            </div>
            <div class="input-group pull-left date">
                <span class="input-group-addon">融资余额</span>
                <input required type="text" name="balance" class="form-control" >
                <span class="glyphicon  date-icon pull-left" aria-hidden="true"></span>
            </div>
            <div class="form-group btn" style="margin-top: 40px;">
            <div  class="modal-footer">
                <button type="submit" class="btn btn-info">提交</button>
                <button type="button" data-dismiss="modal" class="btn btn-info ">取消</button>
            </div>
            </div>

        </form>
</div>
    </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
      <!-- month-finance end -->
    
    
<script src="build/lib/sea.min.js"></script>
<script src="js/base.js"></script>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="js/bootstrap-table/1.11.1/bootstrap-table.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="js/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>

<!-- Latest compiled and minified Locales -->
<script src="js/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="js/bootstrap-validator/dist/validator.min.js"></script>
<script src="js/bootbox/bootbox.min.js"></script>
<script src="js/jrjzx/rejoice.js"></script>
<script src="js/jrjzx/financing/financing.js"></script>

</body>
</html>
