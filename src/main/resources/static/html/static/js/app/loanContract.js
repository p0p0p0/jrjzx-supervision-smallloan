define(function (require, exports, module) {
    /**
     *
     * @constructor
     */
    exports.LoanStyle = function () {
        //监听input的onchange 事件
        $(".loan-mode input[name = ensure]").on('change', function () {
            var $ensure = $('.ensure');
            if ($(this).prop("checked")) {
                $ensure.fadeIn(200);
            } else {
                $ensure.fadeOut(200);
            }
        });

        $(".loan-mode input[name = mortgage]").on('change', function () {
            var $mortgage = $('.mortgage');
            if ($(this).prop("checked")) {
                $mortgage.fadeIn(200);
            } else {
                $mortgage.fadeOut(200);
            }
        });

        $(".loan-mode input[name = pledge]").on('change', function () {
            var $pledge = $('.pledge');
            if ($(this).prop("checked")) {
                $pledge.fadeIn(200);
            } else {
                $pledge.fadeOut(200);
            }
        });
    };

    //省市区三级联动
    exports.city = function () {
        require('area')
        _init_area();
        var show = document.getElementById('show');
        var county = document.getElementById('s_county');
        var city = document.getElementById('s_city');
        var province = document.getElementById('s_province');
        var showArea = function () {
            show.innerHTML = "<h3> 省" + province.value + " - 市" +
                city.value + " - 县/区" +
                county.value + "</h3>"
        }
    }

    /**
     * 合同操作步骤
     * @constructor
     */
    exports.ContractProcess = function () {

        var stepList = $('.add_loan_contract .step li'),
            $contentList = $('.add_loan_contract .fieldset'),
            $nextBtn = $('.add_loan_contract .next'), //下一步按钮
            $prevBtn = $('.add_loan_contract .prev'), //下一步按钮
            curPage = 0;
        $nextBtn.on('click', function () {
            curPage++;
            if (curPage >= 2) {
                curPage = 2;
            }
            judgePage();
        });
        $prevBtn.on('click', function () {
            curPage--;
            if (curPage <= 0) {
                curPage = 0;
            }
            judgePage();
        });
        function judgePage() { //判断却换到哪一页
            if (curPage == 0) {
                trunTo(0);
            } else if (curPage == 1) {
                trunTo(1);
            } else {
                trunTo(2);
            }
        };
        function trunTo(index) {
            stepList.eq(index).addClass('active').siblings().removeClass('active');
            $contentList.eq(index).addClass('active').siblings().removeClass('active');
        }
    }


    /**
     * 高管普通员工信息表切换
     */
    exports.employeeInfoTab  = function () {
        $("input[name='lenderType']").click(function () {
            if ($(this).val() == 'option1') {
                $('.naturalPerson').addClass('active').siblings().removeClass('active');
            } else {
                $('.enterprise').addClass('active').siblings().removeClass('active');
            }
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