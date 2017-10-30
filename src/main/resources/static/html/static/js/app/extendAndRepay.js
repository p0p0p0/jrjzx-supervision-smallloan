define(function (require, exports, module){
    /**
     * 合同表格切换
     */
    exports.contractTab = function () {
        $('.contract-view>ul>li').on('click', function () {
            $(this).addClass('active').siblings().removeClass('active');
            $('.contract-view .contract-view-table').eq($(this).index()).addClass('active').siblings().removeClass('active');
        });
    };

    /**
     * 新建展期计划 弹窗
     */
    exports.newPlan = function () {
        require('layer');
        var $newBtn = $('#new-plan');
        $newBtn.on('click', function () {
            layer.confirm('新建展期后，原计划不可用，新建展期计划还款本金不能小于原计划还款本金余额。', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                layer.closeAll();
            }, function () {
                //取消回调
            });

        })
    };

})