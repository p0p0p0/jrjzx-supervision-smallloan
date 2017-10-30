define(function (require, exports, module) {
    /**
     * 注册事件
     */
	var checkCodeMessage = "";
	var dynamicCheckCodeMessage = function () { return checkCodeMessage; }
    exports.login = function () {
        var $loginPanle = $("#login-form");
        var $modifyPwd = $('.modify-pwd');
        var $loginBox = $('.login-box');
        var $modifyPwdBox = $('.modify-pwd-box');
        require('jqueryValidation');

        $.validator.setDefaults({
            submitHandler: function (form) {
            	//表单提交
            	form.submit();
            }
        });
        $loginPanle.validate({
            onfocusout: function (element) {
                $(element).valid();
            },
            rules: {
                "username": {
                    required: true,
                },
                "password": {
                    required: true,
                 
                },
                "chaImage": {
                    required: true,
                    checkChaImage:true
                  
            
                },
            },
            messages: {
                "username": {
                    required: "请输用户名",
                },
                "password": {
                    required: "请输入密码",
                    remote:"密码不正确",
                },
                "chaImage": {
                    required: "请输入验证码",
                },
            },

        });
        
        //检查密码格式
        $.validator.addMethod("checkPwd", function (value, element, params) {
            var checkPwd = /(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}/;         
            return this.optional(element) || (checkPwd.test(value));
        }, "至少8位，含数字字母");
        
        //检查验证码
        $.validator.addMethod("checkChaImage", function (value, element, params) {
        	var flag = false;
          	$.ajax({
           	 url: "checkCode/"+value,
                type: "get",               //数据发送方式
                dataType: "text",           //接受数据格式
                async: false,
                success: function (data) {
                	if(data == 1){
                		flag = true;
                	}else if(data == 3){
                		checkCodeMessage = "验证码失效";
                		$(".chaImage").attr("src","checkCode?ran="+new Date().getTime());
                		flag = false;
                	}else{
                		checkCodeMessage = "验证码错误";
                		flag = false;
                	}                	
                }
           })
           return flag;
        }, dynamicCheckCodeMessage);

        
     

        $modifyPwd.click(function (event) {
            var event = event || window.event;
            event.stopPropagation();
            $loginBox.fadeOut(500);
            $modifyPwdBox.fadeIn(1200);
        });

    };

    /**
     * 修改密码事件
     */
    exports.modifyPwd = function () {
        var $modifyForm = $("#modify-form");
        var $toLoginBox = $('.return-login');
        var $loginBox = $('.login-box');
        var $modifyPwdBox = $('.modify-pwd-box');
        require('jqueryValidation');

        $.validator.setDefaults({
            submitHandler: function () {
                console.log("提交修改密码事件!");
            }
        });
        $modifyForm.validate({
            onfocusout: function (element) {
                $(element).valid();
            },
            rules: {
                "originalPwd": {
                    required: true,
                },
                "newPwd": {
                    required: true,
                    checkPwd:true,
                },
                "confirmPwd": {
                    required: true,
                    equalTo: "#new-password"
                },
                "chaImage": {
                    required: true,
                },
            },
            messages: {
                "originalPwd": {
                    required: "请输原始密码",
                },
                "newPwd": {
                    required: "请输新密码",
                },
                "confirmPwd": {
                    required: "确认密码",
                    equalTo: "密码输入不一致"
                },
                "chaImage": {
                    required: "请输验证码",
                },
            },

        });
        $.validator.addMethod("checkPwd", function (value, element, params) {
            var checkPwd = /(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}/;
            return this.optional(element) || (checkPwd.test(value));
        }, "至少8位，含数字字母");

        $toLoginBox.click(function (event) {
            var event = event || window.event;
            event.stopPropagation();
            $modifyPwdBox.fadeOut(500);
            $loginBox.fadeIn(1200);
        });


    }




})
