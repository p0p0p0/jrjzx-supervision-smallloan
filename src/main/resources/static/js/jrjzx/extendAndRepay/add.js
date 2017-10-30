 var $moneySum = $("#moneySum");  
 var $remainMoneySum = $("#remainMoneySum");
 var $subMoney = $("#subMoney");
 var lastMoney = 0;
 var formValidator;
$(function () {
	   seajs.use('common',function (c) {
           c.navInit();c.getDayly();c.addItem('addInfo');
           c.format();
       });
       seajs.use('extendAndRepay',function (e) {
           e.newPlan();
       });
       //监听金额输入事件：jquery委托
       $("#extendRepayPlanTable").delegate("input[name=money]","change",function(event){
    	   var $money = $("input[name='money']");
    	   var moneySum = 0;
    	   for(var i = 0; i < $money.length; i++){
   				moneySum += Number($money[i].value);
    	   }
    	   $("#moneySum").text(moneySum);
    	   $("#subMoney").text(Number($remainMoneySum.text())-moneySum);
       });
       
       
       
        
    });
       function saveExtendRepayPlan(){
    	    var contractId = $("#contractId").val(); 
    		var extendRepayPlanList = [];
    		var $end_date = $("input[name='endDate']");
    		var $money = $("input[name='money']");
    		var $interest = $("input[name='interest']");
    		for(var i = 0; i < $end_date.length; i++){
    			var extendRepayPlan = {};
    			extendRepayPlan["endDate"]=$end_date[i].value;
    			extendRepayPlan["money"]=$money[i].value;
    			extendRepayPlan["interest"]=$interest[i].value;
    			extendRepayPlan["contractId"]=contractId;
    			extendRepayPlanList.push(extendRepayPlan);
    		} 
    		
    		if(extendRepayPlan["endDate"] === "" || extendRepayPlan["money"] ==="" || extendRepayPlan["interest"] ===""){
    			bootbox.alert("请将表单填写完整");
    			return ;
    		}
    		
    		//check sum(money) >= the old sum
    		if(Number($moneySum.text()) != Number($("#remainMoneySum").text())){
    			bootbox.alert("还款本金合计必须 等于余额!");
    			return ;
    		}
    		//save list
    		 $.ajax({
                 url: "extendRepayPlan/saveList",
                 contentType: 'application/json',
                 type: 'POST',
                 dataType:"text",
                 data:JSON.stringify(extendRepayPlanList),
                 success: function (data) {
					window.location="page/extendAndRepay/list.html?contractId="+contractId;
                 }
             });
       }