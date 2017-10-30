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
<jsp:include page="/WEB-INF/jsp/common/top.jsp"></jsp:include>
    <title>还款与展期</title>
     <link rel="stylesheet" href="build/lib/layer/skin/layer.css">
     <link rel="stylesheet" href="js/bootstrap-table/1.11.1/bootstrap-table.min.css">
     
<style type="text/css">
.dateTitle{
	width:500px;
	margin:0 auto;
}
.dateTitle span{
    border: 1px solid gray;
    padding: 2px 14px;
    cursor: pointer;
    display: inline-block;
    margin:1px;
}
/* .dateTitle > span:first-child{
border-left:1px solid gray;
} */
.dateTitle span:hover,.selectedExtendDate{
	background:gray;
    color:white;
}
</style>
</head>


<div class="container" style="padding-top:100px">
	<input type="hidden" id="contractId" name="contractId" />
    <div class="contract-view">
        <ul>
            <li style="width: 25%" class="active"> 计划还款与实际还款</li>
            <li style="width: 25%">展期计划还款与展期实际还款</li>
            <li style="width: 25%">展期历史计划还款与历史实际还款</li>
            <li style="width: 25%">放款信息</li>
        </ul>
        <div class="contract-content">
            <div class="contract-view-table active">
                <div class="table-template table">
                    <div class="table-info">
                        <h4 class="table-title pull-left">计划还款信息</h4>
                    </div>
                    <table id="repayPlanTable"></table>
                </div>

                <div class="table-template table">
                    <div class="table-info">
                        <h4 class="table-title pull-left">实际还款信息</h4>
                    </div>
                    <table id="repayTable"></table>
			<div id="repayToolbar">
		                  <span class="alert"></span>
		                 <a id="addRepay"  class="create btn btn-primary" href="javascript:">新增</a>
		        </div>

	   <div id="repayDialog" class="modal fade" data-backdrop="static" >
        	<div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"></h4>
                </div>
                <form action="#" id="repayForm">
                <input type="hidden" name="id" />
                <input type="hidden" name="contractId" value="${contractId }" />
                <div class="modal-body">
                    <div class="form-group">
                        <label>还款时间</label>
                        <input required type="text" data-required-error="还款时间不能为空" data-format="yyyy-MM-dd HH:mm:ss"  class="form-control form_secondTime" name="repayTime" placeholder="还款时间">
                    	 <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                        <label>还款金额</label>
                        <input required  type="text" data-required-error="还款金额不能为空" class="form-control number" name="money" placeholder="还款金额">
                    	<div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                        <label>还款利息</label>
                        <input required type="text" data-required-error="还款利息不能为空" class="form-control" name="interest" placeholder="还款利息">
                    	<div class="help-block with-errors"></div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit"  class="btn btn-primary submit"  >提交</button>
                </div>
                </form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->


                </div>
            </div>
            <div class="contract-view-table ">
            
            
                  <div class="table-template table">
                    <div class="table-info">
                        <h4 class="table-title pull-left">展期计划还款信息</h4> 
                        <button id="new-plan" class="btn  btn-sm pull-right btn-primary"  style="cursor:pointer;">新建展期计划</button>
                    </div>

                    <div class="table-content">
                        <table id="extendRepayPlanTable" class="table table-hover cur">
                        </table>
                    </div>
                </div>

                <div class="table-template table">
                    <div class="table-info">
                        <h4 class="table-title pull-left">展期实际还款信息</h4>
                    </div>

				<table id="extendRepayTable">
				</table>
				<div id="extendRepayToolbar">
		                  <span class="alert"></span>
		                 <a id="addExtendRepay"   class="create btn btn-primary" href="javascript:#">新增</a>
		        </div>
		   <div id="extendRepayDialog" class="modal fade" data-backdrop="static" >
        	<div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title"></h4>
                </div>
                <form action="#" id="extendRepayForm">
                <input type="hidden" name="id" />
                <input type="hidden" name="contractId" value="${contractId }" />
                <input type="hidden" name="extendDate"  />
                <div class="modal-body">
                    <div class="form-group">
                        <label>还款时间</label>
                        <input required type="text" data-required-error="还款时间不能为空" data-format="yyyy-MM-dd HH:mm:ss"  class="form-control form_secondTime" name="repayTime" placeholder="还款时间">
                    	 <div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                        <label>还款金额</label> 
                        <input required   type="text" data-required-error="还款金额不能为空" class="form-control number" name="money" placeholder="还款金额">
                    	<div class="help-block with-errors"></div>
                    </div>
                    <div class="form-group">
                        <label>还款利息</label>
                        <input required type="text" data-required-error="还款利息不能为空" class="form-control" name="interest" placeholder="还款利息">
                    	<div class="help-block with-errors"></div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="submit"  class="btn btn-primary submit"  >提交</button>
                </div>
                </form>
            </div><!-- /.modal-content -->
        </div><!-- /.modal-dialog -->
    </div><!-- /.modal -->
                </div>
            </div>
            
            
            
            
            <div class="contract-view-table ">
                <div class="table-template">
                    <div class="table-info">
					<div id="extendDateList" class="dateTitle"></div>
                        <h4 class="table-title pull-left">历史展期计划还款信息</h4>
                                         
                    </div>
                        <table id="extendRepayPlanHisTable" >
                        </table>
                </div>




             <div class="table-template table">
                    <div class="table-info">
                        <h4 class="table-title pull-left">历史展期实际还款信息</h4>
                    </div>
                    <table id="extendRepayHisTable"></table>
            </div>
            
            </div>
            <div class="contract-view-table ">
                <div class="table-template t2">
                    <div class="addSpan addInfo">
                        <a href="javascript:;" class="pull-right">添加</a>
                    </div>
                    <div class="table-content">
                    <form id="loanInfoForm" >
                        <table class="table table-hover table-bordered cur" style="text-align: center;">

                            <thead class="thead-col">
                            <tr >
                                <th>放款时间</th>
                                <th>放款金额</th>
                                <th>债权开始日期</th>
                                <th>债权结束日期</th>
                                <th>备注</th>
                            </tr>
                            </thead>
                            <tbody class="rep-info">
                            <tr>
                            	<input type="hidden" name="id" />
                                <td ><input type="text" style="width:200px;display:inline-block;" name="loanTime" class="form-control form_secondTime"></td>
                                <td ><input type="text" name="money" ></td>
                                <td ><input type="text" name="startDate" style="width:120px;" class="dateIcon form_datetime"><i class="glyphicon glyphicon-calendar"></i></td>
                                <td ><input type="text" name="endDate" style="width:120px;" class="dateIcon form_datetime"><i class="glyphicon glyphicon-calendar"></i></td>
                                <td><input type="text" name="remark" style="width:300px;"></td>
                            </tr>
                            </tbody>
                        </table>
                        
		                <button  type="button" onclick="saveLoanInfos()"   class="btn btn-info col-sm-1 col-sm-offset-4"  >提交</button>
		                <button type="reset" class="btn btn-info col-sm-1 col-sm-offset-1" >取消</button>
                    </form>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>






<script src="build/lib/sea.min.js"></script>
<script src="js/base.js"></script>
<script src="js/jrjzx/rejoice.js"></script>
<!-- Latest compiled and minified JavaScript -->
<script src="js/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>

<!-- Latest compiled and minified Locales -->
<script src="js/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>

<script src="js/bootstrap-validator/dist/validator.min.js"></script>

<script src="js/jrjzx/extendAndRepay/list.js"></script>
<script>

</script>

</body>
</html>
