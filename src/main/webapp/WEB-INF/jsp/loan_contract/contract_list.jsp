<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE HTML>
<html lang="zh-cn">
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
<title>合同数据</title>
<link rel="stylesheet" href="build/lib/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet"
	href="build/lib/bootstrap-datepicker/bootstrap-datetimepicker.min.css">
<link rel="stylesheet" href="css/style.css">
<link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />

</head>
<jsp:include page="/WEB-INF/jsp/common/top.jsp" />

<div class="container pt100">
	<div class="contract-table">
		<div class="table-template">
			<div class="table-info">
				<h4 class="table-title pull-left">合同数据</h4>
				<button type="button" class="searchSpan btn btn-info pull-right">搜索
  					<span class="glyphicon glyphicon-arrow-down" ></span>
				</button>
			</div>
			<div class="searchContent contract-search">
				<form class="navbar-form navbar-left">
					<div class="form-group">
						<div class="input-group pull-left">
							<span class="input-group-addon">合同编号</span> <input type="text"
								class="form-control" id="contractNumber">
						</div>
						<div class="input-group pull-right">
							<span class="input-group-addon">借款人</span> <input type="text"
								class="form-control" id="name" >
						</div>
						<div class="input-group pull-left date">
							<span class="input-group-addon">报备日期</span> <input type="text"
								class="form-control date pull-left form_datetime"
								id="sreportTime">  <span class="parting-line pull-left">-</span>
							<input type="text"
								class="form-control date pull-left form_datetime"
								id="ereportTime"> 
						</div>
						<div class="input-group pull-right date">
							<span class="input-group-addon">贷款日期</span> <input type="text"
								class="form-control date pull-left form_datetime" id="ssignTime">
							 <span class="parting-line pull-left">-</span>
							<input type="text"
								class="form-control date pull-left form_datetime" id="esignTime">							
						</div>
						<div class="input-group pull-left date">
							<span class="input-group-addon">贷款金额</span> <input type="text"
								class="form-control date pull-left" id="smoney"> <span
								class=" date-icon1 pull-left" aria-hidden="true">万元</span> <span
								class="parting-line pull-left">-</span> <input type="text"
								class="form-control date pull-left" id="emoney"> <span
								class=" date-icon2 pull-left" aria-hidden="true">万元</span>
						</div>
						<div class="input-group pull-right date">
							<span class="input-group-addon">年利率区间</span> <input type="text"
								class="form-control date pull-left" id="sannualRate"> <span
								class=" date-icon1 pull-left" aria-hidden="true">%</span> <span
								class="parting-line pull-left">-</span> <input type="text"
								class="form-control date pull-left" id="eannualRate"> <span
								class=" date-icon2 pull-left" aria-hidden="true">%</span>
						</div>

						<div class="input-group pull-left">
							<span class="input-group-addon">合同状态:</span> <select
								class="form-control" id="status">
								<option value="">全部</option>
								<option value="1">未结清</option>
								<option value="2">已结清</option>
								<option value="3">逾期未结清</option>
								<option value="4">逾期已结清</option>
							</select>
						</div>
						<div class="input-group pull-right">
							<span class="input-group-addon">是否展期:</span> <select
								class="form-control" id="isExtend">
								<option value="">全部</option>
								<option value="1">是</option>
								<option value="0">否</option>
							</select>
						</div>
						<div class="input-group pull-left loan-term">
							<span class="input-group-addon">贷款期限:</span> <label
								class="radio-inline"> <input type="radio"
								name="loan-term" checked value="0"> 全部
							</label> <label class="radio-inline"> <input type="radio"
								name="loan-term" value="1"> 10天以下
							</label> <label class="radio-inline"> <input type="radio"
								name="loan-term" value="2"> 10天-30天
							</label> <label class="radio-inline"> <input type="radio"
								name="loan-term" value="3"> 30天-90天
							</label> <label class="radio-inline"> <input type="radio"
								name="loan-term" value="4"> 90天-半年
							</label> <label class="radio-inline"> <input type="radio"
								name="loan-term" value="5"> 半年到1年
							</label> <label class="radio-inline"> <input type="radio"
								name="loan-term" value="6"> 1年以上
							</label>
						</div>


					</div>

					<span type="button" class="btn btn-info searchBtn pull-right "
						onclick="search()">搜索</span>
				</form>
			</div>
			<div class="addSpan">
				<a href="loanContract/toAdd.html" class="btn btn-primary pull-right add-btn mt10" >添加</a>	
			</div>
			<div class="table-content">
				<table class="table table-hover table-bordered cur table-responsive"
					id="table1"></table>
			</div>
		</div>
	</div>
</div>

<div class="container">
	<div class="contract-table">
		<div class="table-template">
			<div class="table-info">
				<h4 class="table-title pull-left">合同存稿信息</h4>
			</div>
			<div class="table-content">
				<table class="table table-hover table-bordered cur table-responsive"
					id="table2"></table>

				<table class="table table-hover table-bordered cur table-responsive">


				</table>
			</div>
			<!-- <p class="pull-left" >共309条记录</p>
            <nav aria-label="Page navigation" class="pull-right">
                <ul class="pagination">
                    <li>
                        <a href="#" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                    <li class="active"><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li>
                        <a href="#" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </ul>
            </nav> -->
		</div>
	</div>
</div>

<script src="build/lib/sea.min.js"></script>
<script src="js/base.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="js/bootstrap-table/1.11.1/bootstrap-table.min.css">
<!-- Latest compiled and minified JavaScript -->
<script src="js/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<!-- Latest compiled and minified Locales -->
<script
	src="js/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script>
	var $table1 = $('#table1');
	var $table2 = $('#table2');
	$(function() {

		seajs.use('common', function(c) {
			c.navInit();
			c.getDayly();
			c.addItem('addInfo');
			c.delItem('del');
			c.toSearch();
		});

		$table1
				.bootstrapTable({
					url : "loanContract/search",
					dataType : "json",
					pagination : true, //分页
					singleSelect : false,
					locale : "zh-US", //表格汉化
					uniqueId : "id",
					sidePagination : "server", //服务端处理分页
					toolbar : "#toolbar",
					columns : [
							{
								title : '贷款合同编号',
								field : 'contractNumber',
								align : 'center',
								valign : 'middle'
							},
							{
								title : '报备日期',
								field : 'reportTime',
								align : 'center',
								valign : 'middle',
								formatter : function(value, row, index) {
									if(value == null){
										return "-";
									}
									var result = value.substring(0, 10);
									return result;
								}

							},
							{
								title : '贷款日期',
								field : 'signTime',
								align : 'center',
								formatter : function(value, row, index) {
									var result = value.substring(0, 10);
									return result;
								}
							},
							{
								title : '借款人',
								field : 'borrowerName',
								align : 'center'
							},
							{
								title : '金额(元)',
								field : 'money',
								align : 'center',
							},
							{
								title : '期限',
								field : 'term',
								align : 'center'
							},
							{
								title : '年化利率(%)',
								field : 'annualRate',
								align : 'center'
							},
							{
								title : '贷款方式',
								field : 'loanMethod',
								align : 'center',
								formatter : function(value, row, index) {																
									var result = value.replace(/1/, '信用')
											.replace(/2/, '保证').replace(/3/,
													'抵押').replace(/4/, '质押')
											.replace(/5/, '其它');
									return result;
								}
							},
							{
								title : '操作',
								field : 'id',
								align : 'center',
								formatter : function(value, row, index) {
									var e = '<a href="page/extendAndRepay/list.html?contractId='
											+ row.id + '">还款/展期/放款</a> ';
									var d = '<a href="loanContract/view.html?id=' + row.id
											+ '">合同查看</a> ';
									var f = '<a href="loanContract/toUpdate.html?contractId='
											+ row.id + '">修改</a> ';
									var g = '<a href="javascript:"  onclick="delContract(\''+ row.id +'\')">删除</a>';
									return e + d + f + g;
								}
							} ],
					queryParams : function(params) {
						params.name = $("#name").val();
						params.contractNumber = $("#contractNumber").val();
						params.sreportTime = $("#sreportTime").val();
						params.ereportTime = $("#ereportTime").val();
						params.ssignTime = $("#ssignTime").val();
						params.esignTime = $("#esignTime").val();
						params.smoney = $("#smoney").val();
						params.emoney = $("#emoney").val();
						params.sannualRate = $("#sannualRate").val();
						params.eannualRate = $("#eannualRate").val();
						params.status = $("#status").val();
						params.isExtend = $("#isExtend").val();
						params.term = $("input[name='loan-term']:checked")
								.val();
						return params;
					}
				});

		 $table2.bootstrapTable({
		    url: "loanContract/listDraft", 
		    dataType: "json",
		    pagination: true, //分页
		    singleSelect: false,
		    locale:"zh-US" , //表格汉化
		    uniqueId:"id",
		    sidePagination: "client", //客户端处理分页
		    toolbar:"#toolbar",
		          columns: [
		                  {
		                      title: '贷款合同编号',
		                      field: 'contractNumber',
		                      align: 'center',
		                      valign: 'middle'
		                  }, 
		                  {
		                      title: '贷款日期',
		                      field: 'signTime',
		                      align: 'center',
		                      formatter:function(value,row,index){
		                    	  if(value==null) return "";
								var result = value.substring(0,10);
								return result;
							  }
		                  },
		                  {
		                      title: '借款人',
		                      field: 'money',
		                      align: 'center'
		                  },
		                  {
		                      title: '金额(元)',
		                      field: 'money',
		                      align: 'center',
		                  },
		                  {
		                      title: '期限',
		                      field: 'term',
		                      align: 'center'
		                  },
		                  {
		                      title: '年化利率(%)',
		                      field: 'annualRate',
		                      align: 'center'
		                  },
		                  {
		                      title: '贷款方式',
		                      field: 'loanMethod',
		                      align: 'center',
		                      formatter:function(value,row,index){
		                    	  if(value==null) return "";
		                    	  var result = '';
		                    	  if(value != ''){
		                    		  result = value.replace(/1/,'信用').replace(/2/,'保证').replace(/3/,'抵押').replace(/4/,'质押').replace(/5/,'其它');
		                    	  }
								  return result;
							  }
		                  },
		                  {
		                      title: '操作',
		                      field: 'id',
		                      align: 'center',
		                      formatter:function(value,row,index){
								var d = '<a href="loanContract/continue.html?contractId='+row.id+'">继续完成</a>  ';
		                        var e = '<a href="loanContract/delete.html?id='+row.id+'">删除</a>';
								return d+e;
							  }
		                  }
		              ]
		      }); 
	});
	function search() {
		var params = $table1.bootstrapTable('getOptions');
		params.queryParams = function(params) {
			params.offset = 0;
			params.name = $("#name").val();
			params.contractNumber = $("#contractNumber").val();
			params.sreportTime = $("#sreportTime").val();
			params.ereportTime = $("#ereportTime").val();
			params.ssignTime = $("#ssignTime").val();
			params.esignTime = $("#esignTime").val();
			params.smoney = $("#smoney").val();
			params.emoney = $("#emoney").val();
			params.sannualRate = $("#sannualRate").val();
			params.eannualRate = $("#eannualRate").val();
			params.status = $("#status").val();
			params.isExtend = $("#isExtend").val();
			params.term = $("input[name='loan-term']:checked").val();
			return params;
		}
		$table1.bootstrapTable('refresh', params);
	}
	
	
	
	function delContract(id){
    	bootbox.confirm('你确定要删除该合同吗?', function(e){
    		if(e){
             $.ajax({
                 url: "loanContract/delete/"+id,
                 contentType: 'application/json',
                 type: 'delete',
                 success: function () {
                     $table1.bootstrapTable('refresh');
                 }
             })
    		}
         });
    }
</script>

</body>
</html>