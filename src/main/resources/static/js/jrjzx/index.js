
var $todayRepayPlanTable = $("#todayRepayPlanTable");
$(function(){
	 $.ajax({
         url: "index/basicStatistics",
         contentType: 'application/json',
         type: 'GET',
         dataType:"json",
         success:function(data){
        	 for(var key in data){
        		 $("#"+key).text(data[key]);
        	 }
        	 
         }
	 });
	 
	 
	 
	 $todayRepayPlanTable.bootstrapTable({
         url: "index/todayRepayPlans", 
         dataType: "json",
         pagination: true, //分页
         singleSelect: false,
         locale:"zh-US" , //表格汉化
         uniqueId:"id",
         paginationLoop:false,
         toolbar:"#todayRepayPlanToolbar",
         toolbarAlign:"left",
         sidePagination: "server", //服务端处理分页
               columns: [
                       {
                         title: '合同编号',
                           field: 'contractNumber',
                           align: 'center',
                           valign: 'middle'
                       }, 
                       {
                           title: '贷款日期',
                             field: 'signTime',
                             align: 'center',
                             valign: 'middle'
                         }, 
                         {
                             title: '今日应还本金',
                               field: 'money',
                               align: 'center',
                               valign: 'middle'
                           }, 
                         {
                             title: '今日应还利息',
                             field: 'interest',
                             align: 'center',
                             valign: 'middle'
                         }, 
                         {
                             title: '今日已还本金',
                             field: 'repaidMoney',
                             align: 'center',
                             valign: 'middle'
                         },
                         {
                             title: '今日已还利息',
                             field: 'repaidInterest',
                             align: 'center',
                             valign: 'middle'
                         },
                         {
                             title: '当笔是否结清',
                             field: 'isSettle',
                             align: 'center',
                             valign: 'middle',
                             formatter:function(value,row,index){
                             	return value==1?"是":value==0?"否":"unknown";
                             }
                         },
                         {
                             title: '操作',
                             field: 'opt',
                             align: 'center',
                             formatter:function(value,row,index){
                          var e = '<a href="page/extendAndRepay/list.html?contractId='+row.contractId+'">还款/展期/放款</a> ';  
                          var d = '<a href="loanContract/view.html?id='+row.contractId+'">合同查看</a>';  
                               return e+d;  
                           } 
                         }
                        
                   ]
           });
	 
})

 
	 function searchTodayRepayPlan(){
   	  var params = $todayRepayPlanTable.bootstrapTable('getOptions')  
         params.queryParams = function(params) {  
             //定义参数  
             //遍历form 组装json  
             $.each($("#searchForm").serializeArray(), function(i, field) {  
                 //可以添加提交验证  
                 params[field.name] = field.value;  
             });  
             return params;  
         }  
   	$todayRepayPlanTable.bootstrapTable('refresh', params);
   }