var $contractLossTable = $("#contract-loss-table");
var $contractLossDialog = $("#contract-loss-dialog");
var $contrantLossForm = $('#contract-loss-form'); 
var currentRow;
$(function(){
	seajs.use('common', function(c) {
		c.getDayly();
		c.navInit();
		c.format();
	});
/*	seajs.use('lossManage', function(l) {
		l.addContractLoss();
	});*/
		 $contractLossTable.bootstrapTable({
         url: "contract-loss/page", 
         dataType: "json",
         pagination: true, //分页
         singleSelect: false,
         locale:"zh-US" , //表格汉化
         uniqueId:"id",
         paginationLoop:false,
         toolbar:"#contract-loss-toolbar",
         toolbarAlign:"right",
         sidePagination: "server", //服务端处理分页
               columns: [
                       {
                         title: '合同编号',
                           field: 'contractNumber',
                           align: 'center',
                           valign: 'middle'
                       }, 
                       {
                           title: '坏账金额（元）',
                             field: 'badMoney',
                             align: 'center',
                             valign: 'middle'
                         }, 
                         {
                             title: '定损日期',
                               field: 'lossDate',
                               align: 'center',
                               valign: 'middle'
                           }, 
                         {
                             title: '坏账类型',
                             field: 'badType',
                             align: 'center',
                             valign: 'middle',
                        	 formatter:function(value,row,index){
                               return value==1?"普通坏账":value==2?"逾期转坏账":"unknown";  
                           } 
                         }, 
                         {
                             title: '是否已核销',
                             field: 'isWrittenOff',
                             align: 'center',
                             valign: 'middle',
                             formatter:function(value,row,index){
                                 return value==0?"未核销":value==1?"已核销":"unknown";  
                             } 
                         },
                         
                         {
                             title: '操作',
                             field: 'opt',
                             align: 'center',
                             formatter:function(value,row,index){
                            	 var e = '<a href="javascript:" data-target="#" onclick="editContractLoss(\''+ row.id +'\')">查看/修改</a> ';  
                            	 var d = '<a href="javascript:" data-target="#" onclick="delContractLoss(\''+ row.id +'\')">删除</a> ';  
                               return e+d;  
                           } 
                         }
                        
                   ]
           });
	
	
	

/*    $('#contract-loss-form').validator().on('submit', function (e) {
		  if (e.isDefaultPrevented()) {
		  } else {
		    // everything looks good!
		     e.preventDefault();
		   var row = {};
		   $contractLossDialog.find("input[name],select[name],textarea[name]").each(function () {
			   row[$(this).attr('name')]=$(this).val();
       });
       $.ajax({
           url: "contract-loss/"+($contractLossDialog.data('id') ?"update":"save"),
           type: $contractLossDialog.data('id') ? 'put' : 'post',
           contentType: 'application/json',
           data: JSON.stringify(row),
           success: function () {
        	   $contractLossDialog.modal('hide');
        	   refreshTable($contractLossTable);
               showAlert($("#contract-loss-toolbar").find(".alert"),($contractLossDialog.data('id') ? '编辑' : '新增') + ' 合同报损 成功!', 'success');
           },
           error: function () {
        	   $contractLossDialog.modal('hide');
               showAlert($("#contract-loss-toolbar").find(".alert"),($contractLossDialog.data('id') ? '编辑' : '新增') + ' 合同报损 失败!', 'danger');
           }
       });
				
		  }
		});*/


		 
		  
	      
	      $.validator.setDefaults({
	    	
	          submitHandler: function (form) {
	        	  var $contractLossDialog = $("#contract-loss-dialog");
	        	  var row = {};	        	
	        	  $contractLossDialog.find("input[name],select[name],textarea[name]").each(function () {
	   			   row[$(this).attr('name')]=$(this).val();
	   			   });
	        	  console.log(row);	        	 
	        	  
	        	   $.ajax({
	                   url: "contract-loss/"+($contractLossDialog.data('id') ?"update":"save"),
//	                   url: "contract-loss/update",
	                   type: $contractLossDialog.data('id') ? 'put' : 'post',
	                   contentType: 'application/json',
	                   data: JSON.stringify(row),
	                   success: function () {
	                	   console.log('success')
	                	   $contractLossDialog.modal('hide');
	                	   refreshTable($contractLossTable);
	                       showAlert($("#contract-loss-toolbar").find(".alert"),($contractLossDialog.data('id') ? '编辑' : '新增') + ' 合同报损 成功!', 'success');
	                   },
	                   error: function () {
	                	   console.log('error')
	                	   $contractLossDialog.modal('hide');
	                       showAlert($("#contract-loss-toolbar").find(".alert"),($contractLossDialog.data('id') ? '编辑' : '新增') + ' 合同报损 失败!', 'danger');
	                   }
	               });
	        	  
	        return false;

	          
	          }
	      });


	      $contrantLossForm.validate({
	     /*  onfocusout: function (element) {
	              $(element).valid();
	          },*/
	          rules: {
	              "contractNumber": {
	                  required: true,
	             "checkContract":true,
	              "isContractHadApply":true
	              },
	              "badMoney": {
	                  required: true,
	              },
	              "lossDate": {
	                  required: true,
	              },
	              "badType": {
	                  required: true,
	              },
	              "isWrittenOff": {
	                  required: true,
	              },

	          },
	          messages: {
	              "contractNumber": {
	                  required: "合同编号不能为空",
	              },
	              "badMoney": {
	                  required: "坏账金额不能为空",
	              },
	              "lossDate": {
	                  required: "报损日期不能为空",
	              },
	              "badType": {
	                  required: "坏账类型不能为空",
	              },
	              "isWrittenOff": {
	                  required: "是否核销不能为空",
	              }
	          },

	      });
	      
	      
	      //检查合同编号是否存在
	      $.validator.addMethod("checkContract", function (value, element, params) {
	      	var flag = false;        	
	      	var contractNum =$("input[name = 'contractNumber']").val();  
	        	$.ajax({
	         	 url: 'loanContract/contract-num/'+contractNum,
	              type: "get",               //数据发送方式
	              dataType: "text",           //接受数据格式
	              async: false,
	              success: function (data) {
	              	console.log(data);
	              	if(data == 0){
	              		flag = false;
	              	}else{                		
	              		flag = true;
	              	}                	
	              }
	         })
	         return flag;	        	
	      }, '合同编号不存在');
	      
	      //检查合同编号是否存在
	      $.validator.addMethod("isContractHadApply", function (value, element, params) {
	    	if(currentRow.contractNumber == value){
	    		return true;
	    	}
	      	var flag = false;        	
	      	var contractNum =$("input[name = 'contractNumber']").val();  
	        	$.ajax({
	         	 url: 'contract-loss/contract-num/'+contractNum,
	              type: "get",               //数据发送方式
	              dataType: "text",           //接受数据格式
	              async: false,
	              success: function (data) {
	              	console.log(data);
	              	if(data == 1){
	              		flag = false;
	              	}else{                		
	              		flag = true;
	              	}    
	              	
	              }
	         })
	         console.log(flag);
	         return flag;	        	
	      }, '该合同已申报');
	      
	      
	      
	      
	      

		 
		 
		 
		 
		 
		 
    
})

  function editContractLoss(id){
    	showContractLossDialog("编辑合同报损",$contractLossTable.bootstrapTable('getRowByUniqueId', id)); 
    }
 function delContractLoss(id){
	 var row = $contractLossTable.bootstrapTable('getRowByUniqueId', id);
      	bootbox.confirm('你确定要删除该合同报损吗?', function(e){
      		if(e){
               $.ajax({
                   url: "contract-loss/delete",
                   contentType: 'application/json',
                   data:JSON.stringify({"id":id,"companyId":row.companyId,"contractNumber":row.contractNumber,"isWrittenOff":row.isWrittenOff}),
                   type: 'delete',
                   success: function () {
                	   refreshTable($contractLossTable);
                       showAlert($("#contract-loss-toolbar").find(".alert"),'删除合同报损 成功!', 'success');
                   },
                   error: function () {
                       showAlert($("#contract-loss-toolbar").find(".alert"),'删除合同报损  出错!', 'danger');
                   }
               })
      		}
           });
      }

 function refreshTable($table,newParams){
      	 var params =  $table.bootstrapTable('getOptions')  
           params.queryParams = function(params) {
      		 	for(var key in newParams)
      		 		params[key]=newParams[key];
              return params;  
          }  
      	$table.bootstrapTable('refresh', params);
      }
	
      function showContractLossDialog(title, row,id) {
    	  
          row = row || {
              contractNumber:"",
              badMoney:"",
              lossDate:"",
              id:"",
              badType:1,
              isWrittenOff:1,
              followUp:"",
              companyId:"",
          }; // default row value
          currentRow = row;
          $contractLossDialog.data('id', row.id);
          $contractLossDialog.find('.modal-title').text(title);
          for (var name in row) {
        	  var $el = $contractLossDialog.find("[name="+name+"]");
        	  if($el.length > 0){
        			$el.val(row[name]);
        	  }
          }
          $contractLossDialog.modal('show');
//          $('#contract-loss-form').validator('validate');
      } 
      
      
      
      
       