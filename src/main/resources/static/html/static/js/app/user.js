define(function (require, exports, module) {
    //登录
    exports.login = function () {
        var $loginBtn = $('#login');
        /*     var $account = $('#account');
         var $password = $('#password');*/
        var $tips = $('.panel-tips');
        var $modifyPwd = $('.modify-pwd');
        var $loginBox = $('.login-box');
        var $modifyPwdBox = $('.modify-pwd-box');

        document.onclick = function (event) {
            var nodeName = event.target.nodeName;
            if (nodeName.toLocaleUpperCase() == 'INPUT') {
                $tips.text('');
                document.onclick = null;
            } else {
                $tips.text('未登录,请输入登录账号');
            }
        };


        $modifyPwd.click(function (event) {
            var event = event || window.event;
            event.stopPropagation();
            $loginBox.fadeOut(500);
            $modifyPwdBox.fadeIn(1200);
            $tips.text('');
        });
        //提交验证
        $loginBtn.on('click', function () {

        });
    };

    //修改密码
    exports.modifyPassword = function () {
        var $oldPwd = $('#old-password');
        var $newPwd = $('#new-password');
        var $confirmPwd = $('#confirm-password');
        var $toLoginBox = $('.return-login');
        var $tips = $('.panel-tips');
        var $loginBox = $('.login-box');
        var $modifyPwdBox = $('.modify-pwd-box');

        $oldPwd.blur(function () {
            var text_val = $(this).val();
            if (text_val == '') {
                $tips.text('请输入原始密码');
                return false;
            } else {
                $tips.text('');
                return true;
            }
            ;
        });

        $newPwd.blur(function () {
            var newpwd_val = $(this).val();
            var pwd_reg = /(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}/;
            if (!pwd_reg.test(newpwd_val) || newpwd_val == '') {
                $tips.text('密码至少8位,必需含字母数字');
                return false;
            } else {
                $tips.text('');
                return true;
            }
            ;
        });
        $confirmPwd.blur(function () {
            var newpwd_val = $('#new-password').val();
            var confirmpwd_val = $('#confirm-password').val();
            console.log(newpwd_val);
            console.log(confirmpwd_val);
            if (newpwd_val !== confirmpwd_val || confirmpwd_val == '') {
                $tips.text('密码不一致,请重新输入');
                return false;
            } else {
                $tips.text('');
                return true;
            }
            ;
        })
        $toLoginBox.click(function (event) {
            var event = event || window.event;
            event.stopPropagation();
            $modifyPwdBox.fadeOut(500);
            $loginBox.fadeIn(1200);
            $tips.text('');
        });
    };

    //检查密码
    exports.checkPwd = function(pwd){
        var password = $('#'+pwd);
        var $tips = $('.panel-tips');
        var password_val = password.val();
        var pwd_reg = /(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,}/;
        if(pwd_reg.test(password_val)){
            $tips.text('');
            return true;
        } else {
            $tips.text('密码至少8位,必需含字母数字');
            return false;
        }
    };
    exports.checkAccount = function (account) {
        var $username = $('#' + account);
        var username_val = $username.val();
        var $tips = $('.panel-tips');
        if (username_val == '') {
            $tips.text('用户名不能为空');
            return false;
        } else {
            $tips.text('');
            return true;
        }
    }



})
