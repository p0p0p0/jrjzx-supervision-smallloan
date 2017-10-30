define(function (require, exports, module){
   //融资
	 exports.addFinancing =function () {
	        require('jqueryValidation');

	        console.info(2222222222);
	        var singeFinanceForm = $('#single-finance-form');
	        
	        console.info(11111111111);

	        $.validator.setDefaults({
	            submitHandler: function () {
	                console.log("提交事件!");

	            }
	        });


	        singeFinanceForm.validate({
	            onfocusout: function (element) {
	                $(element).valid();
	            },
	            rules: {
	                "financeDate": {
	                    required: true,
	                },
	                "endDate": {
	                    required: true,

	                },

	            },
	            messages: {
	                "financeDate": {
	                    required: "请输入融资日期",
	                },
	                "endDate": {
	                    required: "请输入截止日期",
	                }

	            },

	        });
	    }
})