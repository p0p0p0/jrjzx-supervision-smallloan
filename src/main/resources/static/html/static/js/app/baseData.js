define(function (require, exports, module){
    //高管普通员工信息表切换
    exports.shareholderTab = function () {
        $("input[name='shareholder']").click(function () {
            if ($(this).val() == 'option1') {
                $('.corporate_shareholder').addClass('cur').siblings().removeClass('cur');
            } else {
                $('.natural_shareholder').addClass('cur').siblings().removeClass('cur');
            }
        });
    };

})