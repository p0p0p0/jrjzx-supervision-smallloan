var $extendRepayPlanTable = $("#extendRepayPlanTable")
var $extendRepayPlanHisTable = $("#extendRepayPlanHisTable")
var $extendDateList = $("#extendDateList");
var $extendRepayTable = $("#extendRepayTable");
var $extendRepayHisTable = $("#extendRepayHisTable");
var $repayPlanTable = $("#repayPlanTable");
var $repayTable = $("#repayTable");
var $extendRepayDialog = $('#extendRepayDialog').modal({show: false})
var $repayDialog = $('#repayDialog').modal({show: false})

//load contract
var contractId = getUrlParam("contractId");
$("#contractId").val(contractId);
var contract;
function refreshContract(){
	 $.ajax({
	        url: "loanContract/"+contractId,
	        contentType: 'application/json',
	        type: 'GET',
	        async:false,
	        dataType:"json",
	        success: function (data) {
	        	contract = data;
	        	//other opts
	            if(contract.statusIsSettle==1){
	            	$("#new-plan").hide();
	            }else{ 
	            	$("#addRepay").show();
	            	$("#addExtendRepay").show();
	            	$("#new-plan").show();
	            }
	            if(contract.isExtend == 1){
	            	$("#addRepay").hide();
	            }else{
	            	$("#addExtendRepay").hide();
	            }
	        } 
	        
	    });
}
    //dom ready
    $(function () {
        seajs.use('common',function (c) {
         c.getDayly();
         c.getSecondTime();
         c.navInit();
         c.format();
        })
         seajs.use('extendAndRepay',function (e) {
           e.newPlan();e.contractTab();e.addItem('addInfo');
        });
        refreshContract();
        
        //历史展期日期列表
         $.ajax({
                 url: "extendRepayPlan/extendDates/"+$("#contractId").val(),
                 contentType: 'application/json',
                 type: 'GET',
                 dataType:"json",
                 success: function (data) {
                	 	if(data.length==0){
                	 		initExtendRepayTable({"extendDate":"-1"})
                	 		return;
                	 	}
                		for(var i = 0; i < data.length; i++){
                			if(i == 0){
                				//not history extendDates,just to refresh extendRepay
                				//refreshTable($extendRepayTable,{"extendDate":data[0],"sort":"repayTime","order":"asc"});
                				$("input[name='extendDate']").val(data[0]);
                				initExtendRepayTable({"extendDate":data[0]})
                				continue;
                			}
                			var $span = $("<span>");
                			if(i == 1){
                				$span.addClass("selectedExtendDate");
                				//refresh max extendDate
                				refreshHisExtrendRepayPlanTable(data[1],$("#contractId").val());
                				/* refreshHisExtrendRepayTable(data[1]); */
                				refreshTable($extendRepayHisTable,{"extendDate":data[1]})
                			}
                			$span.text(data[i]);
                			$extendDateList.append($span);
                		}
                		
                		//bind extendDate click event
                		$("#extendDateList").find("span").each(function(){
                			$(this).on("click",function(){
                				$(this).siblings().removeClass("selectedExtendDate");
                				var extendDate = $(this).text();
                				//refresh data
                				refreshHisExtrendRepayPlanTable(extendDate,$("#contractId").val());
                				refreshTable($extendRepayHisTable,{"extendDate":extendDate})
                				//set current bg
                				$(this).addClass("selectedExtendDate")
                			});
                		});
                 }
             });
        
        
        
        
        
        $repayPlanTable.bootstrapTable({
            url: "repayPlan/page?sort=end_date&order=asc",
            queryParams:function(params) {
            	params["loanContractId"]=$("#contractId").val();
            	return params;
            },
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
                                	  return value==1?"是":value==0?"否":"unknown";
                                  }
                            },
                            {
                                title: '是否逾期',
                                field: 'isOverdue',
                                align: 'center',
                                valign: 'middle',
                                formatter:function(value,row,index){
                                  return value==1?"是":value==0?"否":"unknown";
                                }
                            },
                            {
                                title: '逾期天数',
                                field: 'overdue',
                                align: 'center',
                                visible: false,
                                valign: 'middle'
                            }
                            
                      ]
              });
        
        $repayTable.bootstrapTable({
            url: "repay/page?sort=repayTime", 
            dataType: "json",
            queryParams:function(params) {
            	params["contractId"]=$("#contractId").val();
            	return params;
            },
            pagination: true, //分页
            singleSelect: false,
            locale:"zh-US" , //表格汉化
            uniqueId:"id",
            paginationLoop:false,
            toolbar:"#repayToolbar",
            toolbarAlign:"right",
            sidePagination: "server", //服务端处理分页
                  columns: [
                          {
                            title: '报备时间',
                              field: 'reportTime',
                              align: 'center',
                              valign: 'middle'
                          }, 
                          {
                              title: '还款时间',
                                field: 'repayTime',
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
                                title: '还款利息',
                                field: 'interest',
                                align: 'center',
                                valign: 'middle'
                            },
                            {
                                title: '操作',
                                field: 'id',
                                align: 'center',
                                formatter:function(value,row,index){  
                             if(contract.isExtend == 0){
                            	 var e = '<a href="javascript:"  onclick="javascript:editRepay(\''+ row.id + '\')">编辑</a> ';  
                                 var d = '<a href="javascript:" data-target="#" onclick="delRepay(\''+ row.id +'\')">删除</a> ';  
                                      return e+d;  
                             }
                            
                              } 
                            }
                            
                            
                      ]
              });
        
        $extendRepayPlanTable.bootstrapTable({
            url: "extendRepayPlan/page/"+$("#contractId").val(), 
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
                            },
                            {
                                title: '逾期天数',
                                field: 'overdue',
                                align: 'center',
                                visible: false,
                                valign: 'middle'
                            }
                            
                      ]
              });
        
      
        $extendRepayHisTable.bootstrapTable({
            url: "extendRepay/history/page/", 
            dataType: "json",
            queryParams:function(params) {
            	params["contractId"]=$("#contractId").val();
            	params["extendDate"]="111111";
            	return params;
            },
            pagination: true, //分页
            singleSelect: false,
            locale:"zh-US" , //表格汉化
            uniqueId:"id",
            paginationLoop:false,
            toolbarAlign:"right",
            sidePagination: "server", //服务端处理分页
                  columns: [
                          {
                            title: '报备时间',
                              field: 'reportTime',
                              align: 'center',
                              valign: 'middle'
                          }, 
                          {
                              title: '还款时间',
                                field: 'repayTime',
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
                                title: '还款利息',
                                field: 'interest',
                                align: 'center',
                                valign: 'middle'
                            }
                            
                            
                      ]
              });
        
        $extendRepayPlanHisTable.bootstrapTable({
            url: "extendRepayPlan/history/page/", 
            dataType: "json",
            pagination: true, //分页
            queryParams:function(params) {
            	params["contractId"]=$("#contractId").val();
            	params["extendDate"]="12121";
            	return params;
            },
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
       
        
        $('#extendRepayForm').validator({custom: {
        	  format:function($el) {
        		  		if(!$.isDateFormat2($el.val())){
							return "必须是yyyy-MM-dd HH:mm:ss格式";
        		  		}
        	  		}
        		  }
        		}).on('submit', function (e) {
  		  if (e.isDefaultPrevented()) {
  		  } else {
  		    // everything looks good!
  		     e.preventDefault();
  		   var row = {};
           $extendRepayDialog.find('input[name]').each(function () {
               row[$(this).attr('name')] = $(this).val();
           });
           $.ajax({
               url: "extendRepay",
               type: $extendRepayDialog.data('id') ? 'put' : 'post',
               contentType: 'application/json',
               data: JSON.stringify(row),
               success: function () {
                   $extendRepayDialog.modal('hide');
                   refreshTable($extendRepayPlanTable);
                   refreshTable($extendRepayTable,{"extendDate":$("input[name='extendDate']").val(),"sort":"repayTime","order":"asc"});
                   showAlert($("#extendRepayToolbar").find(".alert"),($extendRepayDialog.data('id') ? '编辑' : '新增') + ' 展期实际还款 成功!', 'success');
                   refreshContract();
               },
               error: function () {
                   $extendRepayDialog.modal('hide');
                   showAlert($("#extendRepayToolbar").find(".alert"),($extendRepayDialog.data('id') ? '编辑' : '新增') + ' 展期实际还款 失败!', 'danger');
               }
           });
  				
  		  }
  		});
        
        $("#addExtendRepay").on("click",function(){
        	showExtendRepayDialog("新增展期实际还款");
        })
          $("#addRepay").on("click",function(){
        	 showRepayDialog("新增实际还款");
        })
        
        refreshLoanInfos();
        
        
        
    });
    
    function editExtendRepay(id){
    	showExtendRepayDialog("编辑展期实际还款",$extendRepayTable.bootstrapTable('getRowByUniqueId', id)); 
    }
    function delExtendRepay(id){
    	bootbox.confirm('你确定要删除该展期实际还款吗?', function(e){
    		if(e){
    		var row = $extendRepayTable.bootstrapTable('getRowByUniqueId', id);
             $.ajax({
                 url: "extendRepay/delete",
                 contentType: 'application/json',
                 type: 'delete',
                 data:JSON.stringify({"extendDate":row.extendDate,"id":row.id,"contractId":row.contractId}),
                 success: function () {
                     $extendRepayTable.bootstrapTable('refresh');
                     $extendRepayPlanTable.bootstrapTable('refresh');
                     showAlert($("#extendRepayToolbar").find(".alert"),'删除展期实际还款成功!', 'success');
                     refreshContract();
                 },
                 error: function () {
                     showAlert($("#extendRepayToolbar").find(".alert"),'删除展期实际还款出错!', 'danger');
                 }
             })
    		}
         });
    }
    
    
    
      function showExtendRepayDialog(title, row) {
    	//userMethod = row?"PUT":"POST";
        row = row || { 
            repayTime:"",
            money:"",
            interest:"",
            id:"",
            contractId:$("#contractId").val()
        }; // default row value
        $extendRepayDialog.data('id', row.id);
        $extendRepayDialog.find('.modal-title').text(title);
        for (var name in row) {
            $extendRepayDialog.find('input[name="' + name + '"]').val(row[name]);
        }
        $extendRepayDialog.modal('show');
        $('#extendRepayForm').validator('validate');
    } 
    
      
      
      
      function editRepay(id){
      	showRepayDialog("编辑实际还款",$repayTable.bootstrapTable('getRowByUniqueId', id)); 
      }
      function delRepay(id){
      	bootbox.confirm('你确定要删除该实际还款吗?', function(e){
      		if(e){
      		var row = $repayTable.bootstrapTable('getRowByUniqueId', id);
               $.ajax({
                   url: "repay/delete",
                   contentType: 'application/json',
                   type: 'delete',
                   data:JSON.stringify({"id":row.id,"contractId":row.contractId}),
                   success: function () {
                       $repayTable.bootstrapTable('refresh');
                       $repayPlanTable.bootstrapTable('refresh');
                       showAlert($("#repayToolbar").find(".alert"),'删除实际还款成功!', 'success');
                       refreshContract();
                   },
                   error: function () {
                       showAlert($("#repayToolbar").find(".alert"),'删除实际还款出错!', 'danger');
                   }
               })
      		}
           });
      }
      
      function showRepayDialog(title, row) {
      	//userMethod = row?"PUT":"POST";
          row = row || {
              repayTime:"",
              money:"",
              interest:"",
              id:"",
              contractId:$("#contractId").val()
          }; // default row value
          $repayDialog.data('id', row.id);
          $repayDialog.find('.modal-title').text(title);
          for (var name in row) {
              $repayDialog.find('input[name="' + name + '"]').val(row[name]);
          }
          $repayDialog.modal('show');
          $('#repayForm').validator('validate');
      } 
      
      
      $('#repayForm').validator({custom: {
    	  format:function($el) {
    		  		if(!$.isDateFormat2($el.val())){
						return "必须是yyyy-MM-dd HH:mm:ss格式";
    		  		}
    	  		}
    		  }
    		}).on('submit', function (e) {
		  if (e.isDefaultPrevented()) {
		  } else {
		    // everything looks good!
		     e.preventDefault();
		   var row = {};
       $repayDialog.find('input[name]').each(function () {
           row[$(this).attr('name')] = $(this).val();
       });
       $.ajax({
           url: "repay",
           type: $repayDialog.data('id') ? 'put' : 'post',
           contentType: 'application/json',
           data: JSON.stringify(row),
           success: function () {
               $repayDialog.modal('hide');
               $repayPlanTable.bootstrapTable("refresh")
               $repayTable.bootstrapTable("refresh")
               showAlert($("#repayToolbar").find(".alert"),($repayDialog.data('id') ? '编辑' : '新增') + ' 实际还款 成功!', 'success');
               refreshContract();
               
           },
           error: function () {
               $repayDialog.modal('hide');
               showAlert($("#repayToolbar").find(".alert"),($repayDialog.data('id') ? '编辑' : '新增') + ' 实际还款 失败!', 'danger');
           }
       });
				
		  }
		});
    function refreshHisExtrendRepayPlanTable(extendDate,contractId){
    	 var params =  $extendRepayPlanHisTable.bootstrapTable('getOptions')  
         params.queryParams = function(params) {  
            //定义参数  
            //遍历form 组装json  
           /*  $.each($("#searchForm").serializeArray(), function(i, field) {  
                //可以添加提交验证  
                params[field.name] = field.value;  
            });   */
       
  			params["extendDate"]=extendDate;
            params["contractId"]=contractId;
            return params;  
        }  
		 params["pageNumber"]=1;//切换时从第1页开始
		 $extendRepayPlanHisTable.bootstrapTable('refresh', params);
		//change background
		$(this).addClass("selectedExtendDate");
    }
    
    function refreshTable($table,newParams){
      	 var params =  $table.bootstrapTable('getOptions')  
           params.queryParams = function(params) {  
      		 	for(var key in newParams)
      		 		params[key]=newParams[key];
      		 	params["contractId"]=$("#contractId").val();
              return params;  
          }  
      	$table.bootstrapTable('refresh', params);
      }
    
    function saveLoanInfos(){
    	  var contractId = $("#contractId").val(); 
  		var loanInfoList = [];
  		var $loanTime = $("#loanInfoForm").find("input[name='loanTime']");
  		var $id = $("#loanInfoForm").find("input[name='id']");
  		var $money = $("#loanInfoForm").find("input[name='money']");
  		var $startDate = $("#loanInfoForm").find("input[name='startDate']");
  		var $endDate = $("#loanInfoForm").find("input[name='endDate']");
  		var $remark = $("#loanInfoForm").find("input[name='remark']");
  		for(var i = 0; i < $loanTime.length; i++){
  			var loanInfo = {};
  			loanInfo["id"]=$id[i].value;
  			loanInfo["loanTime"]=$loanTime[i].value;
  			loanInfo["money"]=$money[i].value;
  			loanInfo["startDate"]=$startDate[i].value;
  			loanInfo["endDate"]=$endDate[i].value;
  			loanInfo["remark"]=$remark[i].value;
  			loanInfo["contractId"]=contractId;
  			loanInfoList.push(loanInfo);
  		} 
  		//save list
  		 $.ajax({
               url: "loanInfo/saveOrUpdateList",
               contentType: 'application/json',
               type: 'POST',
               dataType:"text",
               data:JSON.stringify(loanInfoList),
               success: function (data) {
					bootbox.alert("更新放款信息成功")
					//refresh loanInfos
					refreshLoanInfos();
               }
           });
			
    }
    
    function initExtendRepayTable(initParams){
		  $extendRepayTable.bootstrapTable({
	            url: "extendRepay/page/", 
	            dataType: "json",
	            queryParams:function(params) {
	            	for(var key in initParams){
	            		params[key]=initParams[key];
	            	}
	            	params["contractId"]=$("#contractId").val();
	            	return params;
	            },
	            pagination: true, //分页
	            singleSelect: false,
	            locale:"zh-US" , //表格汉化
	            uniqueId:"id",
	            paginationLoop:false,
	            toolbar:"#extendRepayToolbar",
	            toolbarAlign:"right",
	            sidePagination: "server", //服务端处理分页
	                  columns: [
	                          {
	                            title: '报备时间',
	                              field: 'reportTime',
	                              align: 'center',
	                              valign: 'middle'
	                          }, 
	                          {
	                              title: '还款时间',
	                                field: 'repayTime',
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
	                                title: '还款利息',
	                                field: 'interest',
	                                align: 'center',
	                                valign: 'middle'
	                            },
	                            {
	                                title: '操作',
	                                field: 'id',
	                                align: 'center',
	                                formatter:function(value,row,index){  
	                             var e = '<a href="javascript:"  onclick="javascript:editExtendRepay(\''+ row.id + '\')">编辑</a> ';  
	                             var d = '<a href="javascript:" data-target="#" onclick="delExtendRepay(\''+ row.id +'\')">删除</a> ';  
	                                  return e+d;  
	                              } 
	                            }
	                            
	                            
	                      ]
	              });
    }
    
    function refreshLoanInfos(params){
    	var params = params?params:{};
    	params["contractId"]=$("#contractId").val();
    	 $.ajax({
             url: "loanInfo/all",
             contentType: 'application/json',
             type: 'GET',
             data:params,
             dataType:"json",
             success:function(data){
            	 if(data.length > 0){
            		 var $tbody = $("#loanInfoForm").find("tbody");
            		 $tbody.empty();
            		 for(var i = 0; i < data.length; i++){
            			 var str =
	                     	'<input type="hidden" name="id" value="'+data[i].id+'" />'+
	                         '<td ><input type="text" value="'+data[i].loanTime+'" name="loanTime" class="form-control form_secondTime"></td>'+
	                         '<td ><input type="text" value="'+data[i].money+'" name="money"></td>'+
	                         '<td ><input type="text" value="'+data[i].startDate+'" name="startDate" style="width:120px;" class="dateIcon form_datetime"><i class="glyphicon glyphicon-calendar"></i></td>'+
	                         '<td ><input type="text" value="'+data[i].endDate+'" name="endDate" style="width:120px;" class="dateIcon form_datetime"><i class="glyphicon glyphicon-calendar"></i></td>'+
	                         '<td><input type="text" value="'+data[i].remark+'" name="remark" style="width:300px;"></td>';
	                     
	                     var $tr = $('<tr></tr>');
	                     $tr.html(str);
	                     $tbody.append($tr);
            		 }
            	 }
             }
		 });
    }
    
		   
