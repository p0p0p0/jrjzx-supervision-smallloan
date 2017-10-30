var $singleFinanceTable = $("#single-finance-table");
var $singleFinanceDialog = $("#single-finance-dialog");
var $monthFinanceTable = $("#month-finance-table");
var $monthFinanceDialog = $("#month-finance-dialog");
$(function(){
	seajs.use('common', function(c) {
		c.getDayly();
		 c.getMonthly();
		  c.navInit();
	})
		 $singleFinanceTable.bootstrapTable({
         url: "single-finance/page", 
         dataType: "json",
         pagination: true, //分页
         singleSelect: false,
         locale:"zh-US" , //表格汉化
         uniqueId:"id",
         paginationLoop:false,
         toolbar:"#single-finance-toolbar",
         toolbarAlign:"right",
         sidePagination: "server", //服务端处理分页
               columns: [
                       {
                         title: '融资日期',
                           field: 'financeDate',
                           align: 'center',
                           valign: 'middle'
                       }, 
                       {
                           title: '融资金额（万元）',
                             field: 'money',
                             align: 'center',
                             valign: 'middle'
                         }, 
                         {
                             title: '到期日',
                               field: 'endDate',
                               align: 'center',
                               valign: 'middle'
                           }, 
                         {
                             title: '融资渠道',
                             field: 'channel',
                             align: 'center',
                             valign: 'middle'
                         }, 
                         {
                             title: '融资利率',
                             field: 'rate',
                             align: 'center',
                             valign: 'middle'
                         },
                         {
                             title: '融资费率',
                             field: 'costRate',
                             align: 'center',
                             valign: 'middle'
                         },
                         {
                             title: '担保费率',
                             field: 'guaranteeRate',
                             align: 'center',
                             valign: 'middle'
                         },
                         {
                             title: '操作',
                             field: 'opt',
                             align: 'center',
                             formatter:function(value,row,index){
                            	 var d = '<a href="javascript:" data-target="#" onclick="delSingleFinance(\''+ row.id +'\')">删除</a> ';  
                               return d;  
                           } 
                         }
                        
                   ]
           });
	
	
	

    $('#single-finance-form').validator().on('submit', function (e) {
		  if (e.isDefaultPrevented()) {
		  } else {
		    // everything looks good!
		     e.preventDefault();
		   var row = {};
		   $singleFinanceDialog.find('input[name]').each(function () {
           row[$(this).attr('name')] = $(this).val();
       });
       $.ajax({
           url: "single-finance",
           type: $singleFinanceDialog.data('id') ? 'put' : 'post',
           contentType: 'application/json',
           data: JSON.stringify(row),
           success: function () {
        	   $singleFinanceDialog.modal('hide');
        	   refreshTable($singleFinanceTable);
               showAlert($("#single-finance-toolbar").find(".alert"),($singleFinanceDialog.data('id') ? '编辑' : '新增') + ' 单笔融资 成功!', 'success');
           },
           error: function () {
        	   $singleFinanceDialog.modal('hide');
               showAlert($("#single-finance-toolbar").find(".alert"),($singleFinanceDialog.data('id') ? '编辑' : '新增') + ' 单笔融资 失败!', 'danger');
           }
       });
				
		  }
		});
    
    
    
    
    
    $monthFinanceTable.bootstrapTable({
        url: "month-finance/page", 
        dataType: "json",
        pagination: true, //分页
        monthSelect: false,
        locale:"zh-US" , //表格汉化
        uniqueId:"id",
        paginationLoop:false,
        toolbar:"#month-finance-toolbar",
        toolbarAlign:"right",
        sidePagination: "server", //服务端处理分页
              columns: [
                      {
                        title: '融资月份',
                          field: 'financeMonth',
                          align: 'center',
                          valign: 'middle'
                      }, 
                      {
                          title: '融资余额（万元）',
                            field: 'balance',
                            align: 'center',
                            valign: 'middle'
                        },
                        {
                            title: '操作',
                            field: 'opt',
                            align: 'center',
                            formatter:function(value,row,index){
                           	 var d = '<a href="javascript:" data-target="#" onclick="delMonthFinance(\''+ row.id +'\')">删除</a> ';  
                              return d;  
                          } 
                        }
                       
                  ]
          });
	
	
	

   $('#month-finance-form').validator().on('submit', function (e) {
		  if (e.isDefaultPrevented()) {
		  } else {
		    // everything looks good!
		     e.preventDefault();
		   var row = {};
		   $monthFinanceDialog.find('input[name]').each(function () {
          row[$(this).attr('name')] = $(this).val();
      });
      $.ajax({
          url: "month-finance",
          type: $monthFinanceDialog.data('id') ? 'put' : 'post',
          contentType: 'application/json',
          data: JSON.stringify(row),
          success: function () {
       	   $monthFinanceDialog.modal('hide');
       	   refreshTable($monthFinanceTable);
              showAlert($("#month-finance-toolbar").find(".alert"),($monthFinanceDialog.data('id') ? '编辑' : '新增') + ' 月度融资 成功!', 'success');
          },
          error: function () {
        	  $monthFinanceDialog.modal('hide');
              showAlert($("#month-finance-toolbar").find(".alert"),($monthFinanceDialog.data('id') ? '编辑' : '新增') + ' 月度融资 失败!', 'danger');
          }
      });
				
		  }
		});
    
})


 function delSingleFinance(id){
      	bootbox.confirm('你确定要删除该单笔融资吗?', function(e){
      		if(e){
               $.ajax({
                   url: "single-finance/"+id,
                   contentType: 'application/json',
                   type: 'delete',
                   success: function () {
                	   refreshTable($singleFinanceTable);
                       showAlert($("#single-finance-toolbar").find(".alert"),'删除单笔融资 成功!', 'success');
                   },
                   error: function () {
                       showAlert($("#single-finance-toolbar").find(".alert"),'删除单笔融资  出错!', 'danger');
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
	
      function showSingleFinanceDialog(title, row) {
          row = row || {
              financeDate:"",
              money:"",
              endDate:"",
              id:"",
              channel:"",
              rate:"",
              costRate:"",
              guaranteeRate:""
          }; // default row value
          $singleFinanceDialog.data('id', row.id);
          $singleFinanceDialog.find('.modal-title').text(title);
          for (var name in row) {
        	  $singleFinanceDialog.find('input[name="' + name + '"]').val(row[name]);
          }
          $singleFinanceDialog.modal('show');
          $('#single-finance-form').validator('validate');
      } 
      
      
      
      
      function delMonthFinance(id){
        	bootbox.confirm('你确定要删除该月度融资吗?', function(e){
        		if(e){
                 $.ajax({
                     url: "month-finance/"+id,
                     contentType: 'application/json',
                     type: 'delete',
                     success: function () {
                  	   refreshTable($monthFinanceTable);
                         showAlert($("#month-finance-toolbar").find(".alert"),'删除月度融资 成功!', 'success');
                     },
                     error: function () {
                         showAlert($("#month-finance-toolbar").find(".alert"),'删除月度融资  出错!', 'danger');
                     }
                 })
        		}
             });
        }
  	
        function showMonthFinanceDialog(title, row) {
            row = row || {
                financeMonth:"",
                balance:"",
                id:""
            }; // default row value
            $monthFinanceDialog.data('id', row.id);
            $monthFinanceDialog.find('.modal-title').text(title);
            for (var name in row) {
          	  $monthFinanceDialog.find('input[name="' + name + '"]').val(row[name]);
            }
            $monthFinanceDialog.modal('show');
            $('#month-finance-form').validator('validate');
        } 