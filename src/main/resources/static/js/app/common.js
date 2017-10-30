define(function (require, exports, module) {
    //ie反显
    exports.placeholder = function () {
        require('placeholder');
        $('input, textarea').placeholder();
    };

    // 导航条初始化
    exports.navInit = function () {
        //获取当前时间
        var $timeID = $(".get-date");
        Date.prototype.pattern = function (fmt) {
            var o = {
                "M+": this.getMonth() + 1, //月份
                "d+": this.getDate(), //日
                "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时
                "H+": this.getHours(), //小时
                "m+": this.getMinutes(), //分
                "s+": this.getSeconds(), //秒
                "q+": Math.floor((this.getMonth() + 3) / 3), //季度
                "S": this.getMilliseconds() //毫秒
            };
            var week = {
                "0": "天",
                "1": "一",
                "2": "二",
                "3": "三",
                "4": "四",
                "5": "五",
                "6": "六"
            };
            if (/(y+)/.test(fmt)) {
                fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
            }
            if (/(E+)/.test(fmt)) {
                fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "星期" : "周") : "") + week[this.getDay() + ""]);
            }
            for (var k in o) {
                if (new RegExp("(" + k + ")").test(fmt)) {
                    fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
                }
            }
            return fmt;
        }
        var date = new Date();
        var currentDate = date.pattern("yyyy年MM月dd日 EEE");
        $timeID.html(currentDate);
        //导航条变透明
        var $nav = $('.nav-header');
        $(window).on('scroll', function () {
            var scroll_top = $(window).scrollTop();
            if (scroll_top > 0) {
                $nav.css('background', '#F7F7F9')
            } else {
                $nav.css('background', 'none')
            }
        });
    };

    /**
     * 日历 分秒时间
     */
    exports.getSecondTime = function () {
        require('datetimepicker');
        $('.form_secondTime').datetimepicker({
            format: 'yyyy-mm-dd hh:ii:ss',
            autoclose: true,
            startView: 'day',
            minView: 'month',
            maxView: 'decade',
            language: 'zh-CN',
            bootcssVer: 3,
        });
    };
    /**
     * 日历 具体天数
     */
    exports.getDayly = function () {
        require('datetimepicker');
        $('.form_datetime').datetimepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            // todayBtn: true,
            startView: 'month',
            minView: 'year',
            maxView: 'decade',
            language: 'zh-CN',
            bootcssVer: 3,
        });
    };

    /**
     * 日历 具体月份
     */
    exports.getMonthly = function () {
        require('datetimepicker');
        $('.form_monthtime').datetimepicker({
            format: 'yyyy-mm',
            autoclose: true,
            // todayBtn: true,
            startView: 'year',
            minView: 'year',
            maxView: 'decade',
            language: 'zh-CN',
            bootcssVer: 3,
        });
    };
    /**
     * 表单格式化
     */
    exports.format = function () {
        // 只允许输入合法的数字
        $('input.number').on('keyup',function () {
            var obj = this;
            obj.value = obj.value.replace(/[^\d.]/g,"");  //清除“数字”和“.”以外的字符
            obj.value = obj.value.replace(/\.{2,}/g,"."); //只保留第一个. 清除多余的
            obj.value = obj.value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
            obj.value = obj.value.replace(/^(\-)*(\d+)\.(\d\d).*$/,'$1$2.$3');//只能输入两个小数
            if(obj.value.indexOf(".")< 0 && obj.value !=""){//以上已经过滤，此处控制的是如果没有小数点，首位不能为类似于 01、02的金额
                obj.value= parseFloat(obj.value);
            }
        });
    }

    /**
     *
     * @param btnClass
     * @param tableClass
     */
    exports.tableFun = function (btnClass, tableClass) {
        var $btn = $('.' + btnClass);
        var $table = $('.' + tableClass);
        $btn.find('button').on('click', function () {
            $(this).addClass('active').siblings().removeClass('active');
            $table.find('.tabTable').eq($(this).index()).addClass('cur').siblings().removeClass('cur');
        });
    };


    /**
     * 搜索框的展开
     */
    exports.toSearch = function () {
        var $btnSearch = $('.searchSpan'),
            $searchContent = $('.searchContent'),
            $arrow = $btnSearch.find('.glyphicon');
        $btnSearch.on('click', function () {
            $searchContent.slideToggle();
            if ($arrow.hasClass('glyphicon-arrow-down')) {
                $arrow.removeClass('glyphicon-arrow-down').addClass('glyphicon-arrow-up');
            } else {
                $arrow.removeClass('glyphicon-arrow-up').addClass('glyphicon-arrow-down');
            }
        });

    };



    //添加一行
    exports.addItem = function (btnName) {
        var $btn = $('.' + btnName);
        $btn.on('click', function () {
            if ($(this).parent().find('tbody').hasClass('study')) {
                var $tbody = $('.study');
                var size = $('.study tr').length;
                if (size > 0) {
                    var inputName = $('.study').find('tr:last input:first').attr('name');
                    size = parseInt(inputName.substring(inputName.indexOf('[') + 1, inputName.indexOf(']'))) + 1;
                }
                var str =
                    '<td ><input type="text" name="learningExps[' + size + '].startTime" class="dateIcon form_datetime"><i class="glyphicon glyphicon-calendar"></i></td>' +
                    '<td ><input type="text" neme="learningExps[' + size + '].endTime" class="dateIcon form_datetime"><i class="glyphicon glyphicon-calendar"></i></td>' +
                    '<td><input type="text" name="learningExps[' + size + '].school"></td>' +
                    '<td><input type="text" name="learningExps[' + size + '].professional"></td>' +
                    '<td><input type="text" name="learningExps[' + size + '].qualifications"></td>' +
                    '<td>' +
                    '<a class="del" href="javascript:;">删除</a>' +
                    '</td>';
            } else if ($(this).parent().find('tbody').hasClass('work')) {
                var $tbody = $('.work');
                var size = $('.work tr').length;
                if (size > 0) {
                    var inputName = $('.work').find('tr:last input:first').attr('name');
                    size = parseInt(inputName.substring(inputName.indexOf('[') + 1, inputName.indexOf(']'))) + 1;
                }
                var str =
                    '<td ><input type="text"  name="workExps[' + size + '].startTime" class="dateIcon form_datetime"><i class="glyphicon glyphicon-calendar"></i></td>' +
                    '<td ><input type="text" name="workExps[' + size + '].endTime" class="dateIcon form_datetime"><i class="glyphicon glyphicon-calendar"></i></td>' +
                    '<td><input type="text" name="workExps[' + size + '].company"></td>' +
                    '<td><input type="text" name="workExps[' + size + '].position"></td>' +
                    '<td>' +
                    '<a class="del" href="javascript:;">删除</a>' +
                    '</td>';
            } else if ($(this).parent().find('tbody').hasClass('member')) {
                var $tbody = $('.member');
                var size = $('.member tr').length;
                if (size > 0) {
                    var inputName = $('.member').find('tr:last input:first').attr('name');
                    size = parseInt(inputName.substring(inputName.indexOf('[') + 1, inputName.indexOf(']'))) + 1;
                }
                var str =
                    '<td><input type="text" name="familys[' + size + '].relation"></td>' +
                    '<td><input type="text" name="familys[' + size + '].name"></td>' +
                    '<td>' +
                    '<select  class="form-control" name="familys[' + size + '].sex" style="text-align: center;width: 60%;margin: 0 20%">' +
                    '<option value="1">男</option>' +
                    '<option value="2">女</option>' +
                    '</select>' +
                    '</td>' +
                    '<td><input type="text" name="familys[' + size + '].curUnit"></td>' +
                    '<td>' +
                    '<a class="del" href="javascript:;">删除</a>' +
                    '</td>';
            } else if ($(this).parent().find('tbody').hasClass('new_rep')) {
                var $tbody = $('.new_rep');
                var str =

                    '<td ><input type="text" name="endDate" class="dateIcon form_datetime"></td>' +
                    '<td ><input class="number" type="text" name="money" ></td>' +
                    '<td ><input type="text" name="interest" ></td>';

            } else if ($(this).parent().find('tbody').hasClass('new_repay')) {
            	//添加还款计划
            	var $tbody = $('.new_repay');
            	var size = $('.new_repay tr').length;
                if(size>0) {
                    var inputName=$('.new_repay').find('tr:last input:first').attr('name');
                    size = parseInt(inputName.substring(inputName.indexOf('[')+1,inputName.indexOf(']')))+1;
                }
            	var str =
                    '<td ><input name="repayPlans['+size+'].endDate" type="text" class="dateIcon form_datetime"></td>'+
                    '<td ><input class="number" name="repayPlans['+size+'].money" type="text" ></td>'+
                    '<td ><input name="repayPlans['+size+'].interest" type="text" ></td>'+
                    '<td><a class="del"  href="javascript:;">删除</a></td>';
            } else if ($(this).parent().find('tbody').hasClass('ensure-tb')) {
                //保证合同
            	var $tbody = $('.ensure-tb');
            	var size = $('.ensure-tb tr').length;
                if(size>0) {
                    var inputName=$('.ensure-tb').find('tr:last input:first').attr('name');
                    size = parseInt(inputName.substring(inputName.indexOf('[')+1,inputName.indexOf(']')))+1;
                }
            	var str =
                    '<td><input type="text" name="ensureContracts['+size+'].name" class="form-control"></td>'+
                    '<td>'+
                    '<select name="ensureContracts['+size+'].cardType" class="form-control">'+
	                    '<option value="1">身份证</option>'+
	                    '<option value="2">驾驶证</option>'+
	                    '<option value="3">港澳通行证</option>'+
	                    '<option value="4">护照</option>'+
	                    '<option value="5">营业执照</option>'+
                    '</select>'+
                    '</td>'+
                    '<td><input type="text" name="ensureContracts['+size+'].cardNumber" class="form-control"></td>'+
                    '<td><input type="text" name="ensureContracts['+size+'].address" class="form-control"></td>'+
                    '<td>'+
                    '<a href="javascript:;" class="del">删除</a>'+
                    '</td>';
            } else if ($(this).parent().find('tbody').hasClass('mortgage-tb')) {
                //抵押
            	var $tbody = $('.mortgage-tb');
            	var size = $('.mortgage-tb tr').length;
                if(size>0) {
                    var inputName=$('.mortgage-tb').find('tr:last input:first').attr('name');
                    size = parseInt(inputName.substring(inputName.indexOf('[')+1,inputName.indexOf(']')))+1;
                }
            	var str =
                    '<td><input type="text" name="mortgageContracts['+size+'].name" class="form-control"></td>' +
                    '<td><input type="text" name="mortgageContracts['+size+'].unit" class="form-control"></td>' +
                    '<td><input type="text" name="mortgageContracts['+size+'].address" class="form-control"></td>' +
                    '<td>' +
                    '<select name="mortgageContracts['+size+'].mortgageType" class="form-control">' +
	                    '<option value="1">存货抵押</option>' +
	                    '<option value="2">客账抵押</option>' +
	                    '<option value="3">证券抵押</option>' +
	                    '<option value="4">设备抵押</option>' +
	                    '<option value="5">不动产抵押</option>' +
	                    '<option value="6">人寿抵押</option>' +
	                    '<option value="7">其它</option>' +
                    '</select>' +
                    '</td>' +
                    '<td><input type="text" name="mortgageContracts['+size+'].worth" class="form-control"></td>' +
                    '<td>' +
                    '<a href="javascript:;" class="del">删除</a>' +
                    '</td>';
            } else if ($(this).parent().find('tbody').hasClass('pledge-tb')) {
                //质押
            	var $tbody = $('.pledge-tb');
            	var size = $('.pledge-tb tr').length;
                if(size>0) {
                    var inputName=$('.pledge-tb').find('tr:last input:first').attr('name');
                    size = parseInt(inputName.substring(inputName.indexOf('[')+1,inputName.indexOf(']')))+1;
                }
            	var str =
                    '<td><input type="text" name="pledgeContracts['+size+'].name" class="form-control"></td>'+
                    '<td><input type="text" name="pledgeContracts['+size+'].unit" class="form-control"></td>'+
                    '<td><input type="text" name="pledgeContracts['+size+'].address" class="form-control"></td>'+
                    '<td>'+
                    '<select name="pledgeContracts['+size+'].pledgeType" class="form-control">'+
	                    '<option value="1">股权质押</option>'+
	                    '<option value="2">定期存单质押</option>'+
	                    '<option value="3">专利权质押</option>'+
	                    '<option value="4">应收账款质押</option>'+
	                    '<option value="5">其它</option>'+
                    '</select>'+
                    '</td>'+
                    '<td><input type="text" name="pledgeContracts['+size+'].worth" class="form-control"></td>'+
                    '<td>'+
                    '<a href="javascript:;" class="del">删除</a>'+
                    '</td>';

            } else if ($(this).parent().find('tbody').hasClass('rep-info')){
                var $tbody = $('.rep-info');
                var str =
                    '<input type="hidden" name="id" />'+
                    '<td ><input type="text" style="width:200px;display:inline-block;" name="loanTime" class="form-control form_secondTime"></td>'+
                    '<td ><input type="text" name="money" ></td>'+
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

    //信息删除按钮的实现
    exports.delItem = function (btnName) {
        $('tbody').on('click', '.' + btnName, function () {
            var _this = $(this);
            require('layer');
            layer.confirm('确定要删除改条数据？？', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                // 成功回调
                var item = _this.parent().parent();
                item.remove();
                layer.closeAll();
            }, function () {
                layer.closeAll();
            });


        });
    };

});
