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
    <title>主页</title>
    <link rel="stylesheet" href="build/lib/layer/skin/layer.css">   
    <link rel="stylesheet" href="css/style.css">  
    <link href="favicon.ico" rel="shortcut icon" type="image/x-icon" />
    <style>
    	 .alert {
            padding: 0 14px;
            margin-bottom: 0;
            display: inline-block;
        }
    </style>
</head>

<body>


<div class="container mt100">
    <div class="loan-info">
        <dl>
            <dt>￥ <span id="moneySum"></span> 万元</dt>
            <dd>累计贷款金额</dd>
        </dl>
        <dl>
            <dt><span id="loanInfoCount"></span> 笔</dt>
            <dd>累计笔数</dd>
        </dl>
        <dl>
            <dt>￥ <span id="loanBalanceSum"></span> 万元</dt>
            <dd>贷款余额</dd>
        </dl>
        <dl>
            <dt><span id="averageRate"></span> %</dt>
            <dd>平均利率</dd>
        </dl>
        <dl class="last-dl">
            <dt><span id="averageTerm"></span></dt>
            <dd>平均期限</dd>
        </dl>

    </div>
</div>
<!--资产分类-->
<div class="container clearfix" >
    <div class="asset-info pull-left" id="asset-info"></div>
    <div class="regulated-info pull-right">
        <div class="regulated-info-header">
            <div class="noteIcon pull-left glyphicon glyphicon-bell">
                <span class="unread-num">2</span>
            </div>
            <h5 class="pull-left">消息监管</h5>
            <span class="unread pull-right">未读</span>
        </div>
        <div class="regulated-info-content">
            <ul>
                <li class="clearfix">
                    <a class="pull-left" href="#">监管消息监管消息监管消息监管消息监管消息监管消息1</a>
                    <span class="message-type pull-left">基础数据</span>
                    <span class="message-time pull-left">2017.05.20.12.00.00</span>
                </li>
                <li class="clearfix">
                    <a class="pull-left" href="#">监管消息监管消息监管消息监管消息监管消息监管消息1</a>
                    <span class="message-type pull-left">基础数据</span>
                    <span class="message-time pull-left">2017.05.20.12.00.00</span>
                </li>
                <li class="clearfix">
                    <a class="pull-left" href="#">监管消息监管消息监管消息监管消息监管消息监管消息1</a>
                    <span class="message-type pull-left">基础数据</span>
                    <span class="message-time pull-left">2017.05.20.12.00.00</span>
                </li>
                <li class="clearfix">
                    <a class="pull-left" href="#">监管消息监管消息监管消息监管消息监管消息监管消息1</a>
                    <span class="message-type pull-left">基础数据</span>
                    <span class="message-time pull-left">2017.05.20.12.00.00</span>
                </li>
                <li class="clearfix">
                    <a class="pull-left" href="#">监管消息监管消息监管消息监管消息监管消息监管消息1</a>
                    <span class="message-type pull-left">基础数据</span>
                    <span class="message-time pull-left">2017.05.20.12.00.00</span>
                </li>
                <li class="clearfix">
                    <a class="pull-left" href="#">监管消息监管消息监管消息监管消息监管消息监管消息1</a>
                    <span class="message-type pull-left">基础数据</span>
                    <span class="message-time pull-left">2017.05.20.12.00.00</span>
                </li>
                <li class="clearfix">
                    <a class="pull-left" href="#">监管消息监管消息监管消息监管消息监管消息监管消息1</a>
                    <span class="message-type pull-left">基础数据</span>
                    <span class="message-time pull-left">2017.05.20.12.00.00</span>
                </li>
                <li class="clearfix">
                    <a class="pull-left" href="#">监管消息监管消息监管消息监管消息监管消息监管消息1</a>
                    <span class="message-type pull-left">基础数据</span>
                    <span class="message-time pull-left">2017.05.20.12.00.00</span>
                </li>
                <li class="clearfix">
                    <a class="pull-left" href="#">监管消息监管消息监管消息监管消息监管消息监管消息1</a>
                    <span class="message-type pull-left">基础数据</span>
                    <span class="message-time pull-left">2017.05.20.12.00.00</span>
                </li>
                <li class="clearfix">
                    <a class="pull-left" href="#">监管消息监管消息监管消息监管消息监管消息监管消息1</a>
                    <span class="message-type pull-left">基础数据</span>
                    <span class="message-time pull-left">2017.05.20.12.00.00</span>
                </li>

            </ul>
        </div>
        <div class="regulated-info-footer">
            <nav aria-label="Page navigation" class="pull-right">
                <ul class="pagination pagination-sm">
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
            </nav>
        </div>
    </div>
</div>
<!--今日计划还款合同-->
<div class="container mb50">
    <div class="repayment">
        <div class="table-template">
        <div class="table-info">
            <h4 class="table-title pull-left">今日还款计划</h4>
        </div>
      
        <table id="todayRepayPlanTable">
        </table>
        <div id="todayRepayPlanToolbar">
                <form  class="form-inline" id="searchForm">
                    <div class="form-group">
                    	<label>合同编号</label>
                        <input type="text" class="form-control" name="contractNumber" >
                    </div>
                    <div class="form-group">
  <!-- Single button -->
  <label>是否结清</label>
                        <select name="isSettle" class="form-control">
                            <option value="">请选择</option>
                            <option value="1">是</option>
                            <option value="0">否</option>
                        </select>

                    </div> 
                    <button type="button"  onclick="javascript:searchTodayRepayPlan()"   class="btn btn-info"  >查询</button>
                 </span>
                </form>
        </div>
    </div>
    </div>
</div>
<script src="js/jrjzx/rejoice.js"></script>
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
<script src="js/jrjzx/index.js"></script>
<script>
$(function () {

	 seajs.use('home',function (h) { 
	      h.assetCharts('asset-info');h.showMessage();
	    });
	    seajs.use('common',function (c) {
	        c.tableFun();c.getMonthly();c.navInit();
	    });
   });
/* var $modal = $('#modal').modal({show: false})
var $table = $('#table');
var userMethod;
$alert = $('.alert').hide();
    $(function () {
        seajs.use('common',function (c) {
            c.getCurrentDate();c.assetCharts('asset-info');c.tableFun();c.getMonthly();c.showMessage();
        })
        $table.bootstrapTable({
        url: "/user/page", 
        dataType: "json",
        pagination: true, //分页
        singleSelect: false,
        locale:"zh-US" , //表格汉化
        uniqueId:"id",
        sidePagination: "server", //服务端处理分页
        toolbar:"#toolbar",
              columns: [
                      {
                        title: '用户名',
                          field: 'username',
                          align: 'center',
                          valign: 'middle'
                      }, 
                      {
                          title: '状态',
                          field: 'flag',
                          align: 'center',
                          valign: 'middle',
                          formatter:function(value,row,index){
                        	  return value==1?"<font color='green'>正常</font>":"<font style='text-decoration:line-through;' color='gray'>删除</font>"
                          }
                      }, 
                      {
                          title: '真实姓名',
                          field: 'realName',
                          align: 'center'
                      },
                      {
                          title: '总人数',
                          field: 'totalCounts',
                          align: 'center'
                      },
                      {
                          title: '创建时间',
                          field: 'createTime',
                          align: 'center',
                      },
                      {
                          title: '操作',
                          field: 'id',
                          align: 'center',
                          formatter:function(value,row,index){  
                       var e = '<a href="javascript:"  onclick="javascript:edit(\''+ row.id + '\')">编辑</a> ';  
                       var d = '<a href="javascript:" data-target="#" onclick="del(\''+ row.id +'\')">删除</a> ';  
                            return e+d;  
                        } 
                      }
                  ]
          });
        
        // create event
        $('#add').click(function () {
            showModal($(this).text()+"用户");
        });
        
        
        $('#userForm').validator().on('submit', function (e) {
    		  if (e.isDefaultPrevented()) {
    		  } else {
    		    // everything looks good!
    		     e.preventDefault();
    		   var row = {};
             $modal.find('input[name]').each(function () {
                 row[$(this).attr('name')] = $(this).val();
             });
             $.ajax({
                 url: "/user",
                 type: $modal.data('id') ? 'put' : 'post',
                 contentType: 'application/json',
                 data: JSON.stringify(row),
                 success: function () {
                     $modal.modal('hide');
                     $table.bootstrapTable('refresh');
                     showAlert(($modal.data('id') ? '编辑' : '新增') + ' 用户 成功!', 'success');
                 },
                 error: function () {
                     $modal.modal('hide');
                     showAlert(($modal.data('id') ? '编辑' : '新增') + ' 用户 失败!', 'danger');
                 }
             });
    				
    		  }
    		})
    })
    
    
	function searchUser(){
    	  var params = $table.bootstrapTable('getOptions')  
          params.queryParams = function(params) {  
              //定义参数  
              //遍历form 组装json  
              $.each($("#searchForm").serializeArray(), function(i, field) {  
                  //可以添加提交验证  
                  params[field.name] = field.value;  
              });  
    
              //参数转为json字符串，并赋给search变量 ,JSON.stringify <ie7不支持，有第三方解决插件  
              //search = JSON.stringify(search)  
              return params;  
          }  
    	  $table.bootstrapTable('refresh', params)  
    	 $table.bootstrapTable('refresh', {
			username : $("#searchForm").find("input[name='username']").val(),
			realName : $("#searchForm").find("input[name='realName']").val()
		}); 
    }
    
    	$('#userForm').validator().on('submit', function (e) {
  		  if (e.isDefaultPrevented()) {
  		  } else {
  		    // everything looks good!
  		     e.preventDefault();
  		   var row = {};
           $modal.find('input[name]').each(function () {
               row[$(this).attr('name')] = $(this).val();
           });
           $.ajax({
               url: "/user",
               type: $modal.data('id') ? 'put' : 'post',
               contentType: 'application/json',
               data: JSON.stringify(row),
               success: function () {
                   $modal.modal('hide');
                   $table.bootstrapTable('refresh');
                   showAlert(($modal.data('id') ? '编辑' : '新增') + ' 用户 成功!', 'success');
               },
               error: function () {
                   $modal.modal('hide');
                   showAlert(($modal.data('id') ? '编辑' : '新增') + ' 用户 失败!', 'danger');
               }
           });
  				
  		  }
  		})
    	
  		
  		function del(id){
    	bootbox.confirm('你确定要删除该用户吗?', function(e){
    		if(e){
             $.ajax({
                 url: "/user/" + id,
                 type: 'delete',
                 success: function () {
                     $table.bootstrapTable('refresh');
                     showAlert('Delete user successful!', 'success');
                 },
                 error: function () {
                     showAlert('Delete user error!', 'danger');
                 }
             })
    		}
         });
    }
    
     function edit(id){
		showModal("编辑用户",$table.bootstrapTable('getRowByUniqueId', id)); 
    }
      function showModal(title, row) {
    	//userMethod = row?"PUT":"POST";
        row = row || {
            username:"",
            realName:"",
            password:"",
            id:""
        }; // default row value

        $modal.data('id', row.id);
        $modal.find('.modal-title').text(title);
        for (var name in row) {
            $modal.find('input[name="' + name + '"]').val(row[name]);
        }
        $modal.modal('show');
       	//$('#userForm').validator('validate');
    } 
      
      function showAlert(title, type) {
        $alert.attr('class', 'alert alert-' + type || 'success')
        .html('<i class="glyphicon glyphicon-check"></i> ' + title).show();
  setTimeout(function () {
      $alert.hide();
  }, 3000);
} */
</script>

</body>
</html>
