var $extendRepayPlanTable = $("#extendRepayPlanTable")
var $extendRepayPlanHisTable = $("#extendRepayPlanHisTable")
var $extendDateList = $("#extendDateList");
var $extendRepayTable = $("#extendRepayTable");
var $extendRepayHisTable = $("#extendRepayHisTable");
var $repayPlanTable = $("#repayPlanTable");
var $repayTable = $("#repayTable");

var $ensureContractTable = $("#ensureContractTable");
var $mortgageContractTable = $("#mortgageContractTable");
var $pledgeContractTable = $("#pledgeContractTable");

$(function () {

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
                        	//do some other opt
                        	var remainMoneySum = Number($("#remainMoneySum").val());
                        	if(remainMoneySum == ""){
                        		remainMoneySum = row.money-row.repaidMoney;
                        	}else{
                        		remainMoneySum+=row.money-row.repaidMoney;
                        	}
                        	$("#remainMoneySum").val(remainMoneySum);
                        	
                        	
                        	
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

$extendRepayTable.bootstrapTable({
    url: "extendRepay/page/", 
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
                    }
              ]
      });


$ensureContractTable.bootstrapTable({
    url: "ensureContract/page/", 
    dataType: "json",
    queryParams:function(params) {
    	params["loanContractId"]=$("#contractId").val();
    	return params;
    },
    pagination: true, //分页
    singleSelect: false,
    locale:"zh-US" , //表格汉化
    uniqueId:"id",
    paginationLoop:false,
    sidePagination: "server", //服务端处理分页
          columns: [
                  {
                    title: '保证人/企业名',
                      field: 'name',
                      align: 'center',
                      valign: 'middle'
                  }, 
                  {
                      title: '证件种类',
                        field: 'cardType',
                        align: 'center',
                        valign: 'middle',
                        formatter:function(value,row,index){
                        	var result = "";
                        	if(value == 1){
                        		result = "身份证";
                        	}else if (value == 2) {
                        		result = "驾驶证";
							}else if (value == 3) {
								result = "港澳通行证";
							}else if (value == 4) {
								result = "护照";
							}else if (value == 5) {
								result = "营业执照";
							}
                        	return result;
                        }
                    }, 
                    {
                        title: '证件号码/营业执照编号',
                          field: 'cardNumber',
                          align: 'center',
                          valign: 'middle'
                      }, 
                    {
                        title: '联系地址/注册地址',
                        field: 'address',
                        align: 'center',
                        valign: 'middle'
                    }
              ]
      });

$mortgageContractTable.bootstrapTable({
    url: "mortgageContract/page/", 
    dataType: "json",
    queryParams:function(params) {
    	params["loanContractId"]=$("#contractId").val();
    	return params;
    },
    pagination: true, //分页
    singleSelect: false,
    locale:"zh-US" , //表格汉化
    uniqueId:"id",
    paginationLoop:false,
    sidePagination: "server", //服务端处理分页
          columns: [
                  {
                    title: '抵押物名称',
                      field: 'name',
                      align: 'center',
                      valign: 'middle'
                  }, 
                  {
                      title: '数据及单位',
                        field: 'unit',
                        align: 'center',
                        valign: 'middle'
                    }, 
                    {
                        title: '存放地点',
                          field: 'address',
                          align: 'center',
                          valign: 'middle'
                      }, 
                    {
                        title: '抵押类型',
                        field: 'mortgageType',
                        align: 'center',
                        valign: 'middle',
                        formatter:function(value,row,index){
                        	var result = "";
                        	if(value == 1){
                        		result = "存货抵押";
                        	}else if (value == 2) {
                        		result = "客账抵押";
							}else if (value == 3) {
								result = "证券抵押";
							}else if (value == 4) {
								result = "设备抵押";
							}else if (value == 5) {
								result = "不动产抵押";
							}else if (value == 6) {
								result = "人寿抵押";
							}else if (value == 7) {
								result = "其它";
							}
                        	return result;
                        }
                    },
                    {
                        title: '评估价值',
                        field: 'worth',
                        align: 'center',
                        valign: 'middle'
                    }
              ]
      });

$pledgeContractTable.bootstrapTable({
    url: "pledgeContract/page/", 
    dataType: "json",
    queryParams:function(params) {
    	params["loanContractId"]=$("#contractId").val();
    	return params;
    },
    pagination: true, //分页
    singleSelect: false,
    locale:"zh-US" , //表格汉化
    uniqueId:"id",
    paginationLoop:false,
    sidePagination: "server", //服务端处理分页
          columns: [
                  {
                    title: '抵押物名称',
                      field: 'name',
                      align: 'center',
                      valign: 'middle'
                  }, 
                  {
                      title: '数据及单位',
                        field: 'unit',
                        align: 'center',
                        valign: 'middle'
                    }, 
                    {
                        title: '存放地点',
                          field: 'address',
                          align: 'center',
                          valign: 'middle'
                      }, 
                    {
                        title: '质押类型',
                        field: 'pledgeType',
                        align: 'center',
                        valign: 'middle',
                        formatter:function(value,row,index){
                        	var result = "";
                        	if(value == 1){
                        		result = "股权质押";
                        	}else if (value == 2) {
                        		result = "定期存单质押";
							}else if (value == 3) {
								result = "专利权质押";
							}else if (value == 4) {
								result = "应收账款质押";
							}else if (value == 5) {
								result = "其它";
							}
                        	return result;
                        }
                    },
                    {
                        title: '评估价值',
                        field: 'worth',
                        align: 'center',
                        valign: 'middle'
                    }
              ]
      });



});