define(function (require, exports, module) {

	exports.addContractLoss =function () {
        require('jqueryValidation');

        
        var $contrantLossForm = $('#contract-loss-form');   
        var $contractLossTable = $("#contract-loss-table");
        var $contractLossDialog = $("#contract-loss-dialog");

        $.validator.setDefaults({
            submitHandler: function () {
                console.log("提交s事件!");
             $.ajax({
                    url: "contract-loss/"+($contractLossDialog.data('id') ?"update":"save"),
                    type: $contractLossDialog.data('id') ? 'put' : 'post',
                    contentType: 'application/json',
                    data: JSON.stringify(row),
                    success: function () {/*
                 	   $contractLossDialog.modal('hide');
                 	   refreshTable($contractLossTable);
                        showAlert($("#contract-loss-toolbar").find(".alert"),($contractLossDialog.data('id') ? '编辑' : '新增') + ' 合同报损 成功!', 'success');
                    */console.log('success')
                    	},
                    error: function () {/*
                 	   $contractLossDialog.modal('hide');
                        showAlert($("#contract-loss-toolbar").find(".alert"),($contractLossDialog.data('id') ? '编辑' : '新增') + ' 合同报损 失败!', 'danger');
                    */console.log('fail')}
                });
            }
        });


        $contrantLossForm.validate({
       /*  onfocusout: function (element) {
                $(element).valid();
            },*/
            rules: {
                "contractNumber": {
                    required: true,
                 /* "checkContract":true,
                  "isContractHadApply":true*/
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
                	if(data === 0){
                		flag = true;
                	}else{                		
                		flag = false;
                	}                	
                }
           })
           return flag;
          	
        }, '合同编号不存在');
        
        
        //检查合同编号已申请报损
        $.validator.addMethod("isContractHadApply", function (value, element, params) {
        	var flag = false;        	
        	var contractNum =$("input[name = 'contractNumber']").val();  
          	$.ajax({
           	 url: 'loanContract/contract-num/'+contractNum,
                type: "get",               //数据发送方式
                dataType: "text",           //接受数据格式
                async: false,
                success: function (data) {                
                	if(data === 1){
                		flag = true;
                	}else{                		
                		flag = false;
                	}                	
                }
           })
           return flag;
        }, '该合同已经申请报损');


        
    }




    
    })