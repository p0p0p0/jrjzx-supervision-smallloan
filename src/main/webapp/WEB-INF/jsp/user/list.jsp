<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>" />
<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.5.2/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.5.2/themes/icon.css" />
	<link rel="stylesheet" type="text/css"
	href="js/jquery-easyui-1.5.2/themes/color.css" />
<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.5.2/demo/demo.css" />
<link rel="stylesheet" type="text/css" href="css/rejoice.css" />
<link rel="stylesheet" type="text/css" href="css/tooltip.css" />
<script type="text/javascript"
	src="js/jquery-easyui-1.5.2/jquery.min.js"></script>
<script type="text/javascript"
	src="js/jquery-easyui-1.5.2/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="js/jquery-easyui-1.5.2/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
		<script type="text/javascript" src="js/myPlugin.js"></script>
</head>

</head>
<body>

	<table id="userList" class="easyui-datagrid" title="用户列表"
	style="height: 100%; width: 100%;" idField="id"
	data-options="fitColumns:true,nowrap : false,
			striped:true,rownumbers:true,singleSelect:true,pagination:true,
			singleSelect:false,loadMsg:'正在加载数据。。。',checkOnSelect:false,
			multiSort:true,
			url:'/user/page',method:'get',toolbar:userTB">
	<thead>
		<tr>
			<th field="ck" checkbox="true"></th>
			<th data-options="field:'realName',width:100,sortable:true">真实姓名</th>
			<th data-options="field:'username',width:100,sortable:true">用户名</th>
			<th data-options="field:'nickName',width:100,sortable:true">昵称</th>
			<th data-options="field:'englishName',width:100,sortable:true">英文名</th>
			<th data-options="field:'address',width:200">地址</th>
			<th data-options="field:'age',width:100">年龄</th>
			<th data-options="field:'sex',width:100">性别</th>
			<th data-options="field:'description',width:100">描述</th>
			<th data-options="field:'createTime',width:200,sortable:true">创建时间</th>
			<th data-options="field:'updateTime',width:200,sortable:true">更新时间</th>
		</tr>
	</thead>
</table>
<div id="userTB" style="padding: 5px; height: auto">
	<div style="margin-bottom: 5px">
		<a href="#" onclick="addUser(this)" class="easyui-linkbutton"
			iconCls="icon-add" plain="false"></a> <a href="#"
			onclick="updateUser(this)" class="easyui-linkbutton"
			iconCls="icon-edit" plain="false"></a> <a href="#"
			class="easyui-linkbutton" onclick="deleteUser(this)" iconCls="icon-remove" plain="false"></a>
	</div>
	<div style="margin-left: 20px;">
		<input class="easyui-validatebox" name="username" placeholder="用户名"
			style="width: 150px" />&nbsp;&nbsp;&nbsp;&nbsp; <input
			class="easyui-validatebox" name="realName" placeholder="真实姓名"
			style="width: 150px" />&nbsp;&nbsp;&nbsp;&nbsp; <a href="#"
			class="easyui-linkbutton" plan="true" onclick="searchUser()"
			iconCls="icon-search">查询</a>
	</div>
</div>

<div id="userDialog" class="easyui-dialog"
	style="width: 500px; height: 400px; padding: 10px 20px" closed="true"
	buttons="#userButtons">
	<form id="userForm" class="fm" method="post">
		<input type="hidden" name="id" />
		<input type="hidden" name="oldPassword" />
		<div class="fitem">
			<label>真实姓名:</label> <input name="realName" class="easyui-textbox"
				required="true" />
		</div>
		<div class="fitem">
			<label>用户名:</label> <input name="username" class="easyui-textbox"
				required="true" />
		</div>
			<div class="fitem">
			<label>密码:</label> <input name="password" class="easyui-textbox"
				type="password" required="true" />
		</div>
		<div class="fitem">
			<label>英文名:</label> <input name="englishName" class="easyui-textbox" />
		</div>
		<div class="fitem">
			<label>昵称:</label> <input name="nickName" class="easyui-textbox" />
		</div>
		<div class="fitem">
			<label>地址:</label> <input name="address" class="easyui-textbox" />
		</div>
		<div class="fitem">
			<label>性别:</label> <select id="sex" class="easyui-combobox"
				data-options="required:true" name="sex"
				style="width: 200px; height: 32px;">
				<option value=""></option>
				<option value="man">男</option>
				<option value="women">女</option>
				<option value="unknown">不详</option>
			</select>
		</div>
		<div class="fitem">
			<label>年龄:</label> <input name="age" data-options="precision:0,min:1"
				class="easyui-numberbox" style="width: 200px; height: 32px" />
		</div>
	</form>
</div>
<div id="userButtons">
		<a href="javascript:void(0)" class="easyui-linkbutton c6"
			iconCls="icon-ok" onclick="saveUser()" style="width: 90px">Save</a> <a
			href="javascript:void(0)" class="easyui-linkbutton"
			iconCls="icon-cancel"
			onclick="javascript:$('#userDialog').dialog('close')"
			style="width: 90px">Cancel</a>
</div>
<script>
var userMethod = "GET";
	function searchUser(selector, params) {
		$('#userList').datagrid('load', {
			username : $("#userTB").find("input[name='username']").val(),
			realName : $("#userTB").find("input[name='realName']").val()
		});
	}
	function addUser() {
		$('#userDialog').dialog('open').dialog('setTitle', '新增用户');
		$('#userForm').form('clear');
		userMethod="POST";
	}

	function updateUser() {
		var rows = $('#userList').datagrid('getChecked');
		console.info(rows)
		if(rows.length==0){
			alert("至少选择一行")
		}else if(rows.length > 1){
			alert("只能选择一行")
		}else{
			$('#userDialog').dialog('open').dialog('setTitle', '编辑用户');
			$('#userForm').form('load', rows[0]);
		}
		
		userMethod = "PUT"
	}
	function deleteUser(){
		var rows = $('#userList').datagrid('getChecked');
		if(rows.length==0){
			alert("至少选择一行");
		}else{
			confirm('温馨提示','您确定要删除吗?',null,function(r){
				if (r){
					$.ajax({
						type : "DELETE",
						url : "/user/"+getSelectedIds(rows),
						contentType:"application/json;charset=utf-8",
						//data :  JSON.stringify(params),
						success : function(msg) {
							$.messager.alert({
				    			title: "温馨提示",
				    			msg: "删除成功",
				    			fn: function(){
				    				$("#userList").datagrid("reload");
				    				 $('#userList').datagrid('clearChecked')
				    			}
				    		});
						}
					});
				}
			});
		}
	}
	function saveUser(){
		if (!$('#userForm').form('validate')) {
			$.messager.alert('提示', '表单还未填写完成!');
			return;
		}
		//提交到后台的RESTful
		$.ajax({
			type : userMethod,
			url : "user",
			contentType:"application/json;charset=utf-8",
			data :   JSON.stringify($("#userForm").serializeFormJSON()),
			success : function(msg) {
				$('#userDialog').dialog('close');
				$.messager.alert({
	    			title: $('#userDialog').panel('options').title,
	    			msg: "操作成功",
	    			fn: function(){
	    				$("#userList").datagrid("reload");
	    			}
	    		});
			}
		});
	}
	
</script>

</body>

</html>