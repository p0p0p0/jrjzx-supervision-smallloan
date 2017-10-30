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
            	$.StandardPost('extendRepayPlan/add.html',
             			{
             		"contractId":$("#contractId").val(),
             		"remainMoneySum":contract.loanBalance
             			});
                layer.closeAll();
            }, function () {
                //取消回调
            });

        })
    };
    //添加一行
    exports.addItem = function (btnName) {
        var $btn = $('.' + btnName);
        $btn.on('click', function () {
            if ($(this).parent().find('tbody').hasClass('rep-info')){
                var $tbody = $('.rep-info');
                var str =
                    '<input type="hidden" name="id" />'+
                    '<td ><input type="text" style="width:200px;display:inline-block;" name="loanTime" class="form-control form_secondTime"></td>'+
                    '<td ><input type="text" name="money" class="number" ></td>'+
                    '<td ><input type="text" name="startDate" style="width:120px;" class="dateIcon form_datetime"><i class="glyphicon glyphicon-calendar"></i></td>'+
                    '<td ><input type="text" name="endDate" style="width:120px;" class="dateIcon form_datetime"><i class="glyphicon glyphicon-calendar"></i></td>'+
                    '<td><input type="text" name="remark" style="width:300px;"></td>';
            } else if ($(this).parent().find('tbody').hasClass('cash-flow')) {
                var $tbody = $('.cash-flow');
                var str =
                    '<td ><input type="text" class="dateIcon form_monthtime"></td>' +
                    '<td ><input type="text" ></td>' +
                    '<td><a href="javascript:;" class="del">删除</a></td>';
            } else {
                return false;
            }
            var $tr = $('<tr></tr>');
            $tr.get(0).innerHTML = str;
            $tbody.append($tr);
            seajs.use('common', function (c) {
                c.getDayly();
                c.getMonthly();
                c.getSecondTime();
                c.format();
            })
        })
    };

})