<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>主页</title>
    <link href="/build/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="/build/lib/layer/skin/layer.css">
    <link rel="stylesheet" href="/build/lib/layer/skin/layui.css">
    <link rel="stylesheet" href="/css/style.css">
    <!--<link rel="stylesheet" href="/build/css/style.min.css">-->
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

<table id="table"></table>
<table id="table2"></table>
<script src="/build/lib/jquery.min.js"></script>
<script src="/build/lib/bootstrap/bootstrap.min.js"></script>
<script src="/build/lib/sea.min.js"></script>
<script src="/js/base.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="/js/bootstrap-table/1.11.1/bootstrap-table.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="/js/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>

<!-- Latest compiled and minified Locales -->
<script src="/js/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="/js/bootstrap-validator/dist/validator.min.js"></script>
<script src="/js/bootbox/bootbox.min.js"></script>
<script src="/js/jrjzx/rejoice.js"></script>
<script>
var $modal = $('#modal').modal({show: false})
var $table = $('#table');
var userMethod;
$alert = $('.alert').hide();
    $(function () {
    	
    	
    	$("#table2").bootstrapTable({
            url: "/extendRepayPlan/page/123", 
            dataType: "json",
            pagination: true, //分页
            singleSelect: false,
            locale:"zh-US" , //表格汉化
            uniqueId:"id",
            paginationLoop:false,
            sidePagination: "server", //服务端处理分页
                  columns: [
                          {
                            title: '截止日期',
                              field: 'endDate',
                              align: 'center',
                              valign: 'middle'
                          }, 
                          {
                              title: '还款本金',
                                field: 'money',
                                align: 'center',
                                valign: 'middle'
                            }, 
                            {
                                title: '已还本金',
                                  field: 'repaidMoney',
                                  align: 'center',
                                  valign: 'middle'
                              }, 
                            {
                                title: '还款利息',
                                field: 'interest',
                                align: 'center',
                                valign: 'middle'
                            }, 
                            
                            {
                                title: '已还利息',
                                field: 'repaidInterest',
                                align: 'center',
                                valign: 'middle'
                            }, 
                            {
                                title: '是否结清',
                                field: 'isSettle',
                                align: 'center',
                                valign: 'middle',
                                formatter:function(value,row,index){
                                	  return value==1?"是":"否";
                                  }
                            },
                            {
                                title: '是否逾期',
                                field: 'isOverdue',
                                align: 'center',
                                valign: 'middle',
                                formatter:function(value,row,index){
                                	return value==1?"是":"否";
                                }
                            }
                      ]
              });
    	
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
    	/* $table.bootstrapTable('refresh', {
			username : $("#searchForm").find("input[name='username']").val(),
			realName : $("#searchForm").find("input[name='realName']").val()
		}); */
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
        $('#userForm').validator('validate');
    } 
      
      function showAlert(title, type) {
        $alert.attr('class', 'alert alert-' + type || 'success')
        .html('<i class="glyphicon glyphicon-check"></i> ' + title).show();
  setTimeout(function () {
      $alert.hide();
  }, 3000);
}
</script>

</body>
</html>
