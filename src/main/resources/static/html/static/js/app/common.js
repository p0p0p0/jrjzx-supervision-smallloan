define(function (require, exports, module) {
    //ie反显
    exports.placeholder = function () {
        require('placeholder');
        $('input, textarea').placeholder();
    };

    // 导航条初始化
    exports.navInit = function () {
        require('bootstrap')
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
   /*     var $nav = $('.nav-header');
        $(window).on('scroll', function () {
            var scroll_top = $(window).scrollTop();
            if (scroll_top > 0) {
                console.log(111)
                $nav.css('background', '#F7F7F9')
            } else {
                $nav.css('background', 'none')
            }
        });*/
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

                    '<td ><input type="text" class="dateIcon form_datetime"></td>' +
                    '<td ><input type="text" ></td>' +
                    '<td ><input type="text" ></td>';

            } else if ($(this).parent().find('tbody').hasClass('new_repay')) {
                var $tbody = $('.new_repay');
                var str =
                    '<td ><input type="text" class="dateIcon form_datetime"></td>' +
                    '<td ><input type="text" ></td>' +
                    '<td ><input type="text" ></td>' +
                    '<td><a class="del"  href="javascript:;">删除</a></td>';

            } else if ($(this).parent().find('tbody').hasClass('ensure-tb')) {
                var $tbody = $('.ensure-tb');
                var str =
                    '<td><input type="text" class="form-control"></td>'+
                    '<td>'+
                    '<select class="form-control">'+
                    '<option value="">身份证</option>'+
                    '<option value="">护照</option>'+
                    '<option value="">营业执照</option>'+
                    '<option value="">港澳通行证</option>'+
                    '</select>'+
                    '</td>'+
                    '<td><input type="text" class="form-control"></td>'+
                    '<td><input type="text" class="form-control"></td>'+
                    '<td>'+
                    '<a href="javascript:;" class="del">删除</a>'+
                    '</td>';
            } else if ($(this).parent().find('tbody').hasClass('mortgage-tb')) {
                var $tbody = $('.mortgage-tb');
                var str =
                    '<td><input type="text" class="form-control"></td>' +
                    '<td><input type="text" class="form-control"></td>' +
                    '<td><input type="text" class="form-control"></td>' +
                    '<td>' +
                    '<select class="form-control">' +
                    '<option value="">存货抵押</option>' +
                    '<option value="">客账抵押</option>' +
                    '<option value="">证券抵押</option>' +
                    '<option value="">设备抵押</option>' +
                    '<option value="">不动产抵押</option>' +
                    '<option value="">人寿抵押</option>' +
                    '<option value="">其它</option>' +
                    '</select>' +
                    '</td>' +
                    '<td><input type="text" class="form-control"></td>' +
                    '<td>' +
                    '<a href="javascript:;" class="del">删除</a>' +
                    '</td>';
            } else if ($(this).parent().find('tbody').hasClass('pledge-tb')) {
                var $tbody = $('.pledge-tb');
                var str =
                    '<td><input type="text" class="form-control"></td>'+
                    '<td><input type="text" class="form-control"></td>'+
                    '<td><input type="text" class="form-control"></td>'+
                    '<td>'+
                    '<select class="form-control">'+
                    '<option value="">股权质押</option>'+
                    '<option value="">定期存单质押</option>'+
                    '<option value="">专利权质押</option>'+
                    '<option value="">应收账款质押</option>'+
                    '<option value="">其它</option>'+
                    '</select>'+
                    '</td>'+
                    '<td><input type="text" class="form-control"></td>'+
                    '<td>'+
                    '<a href="javascript:;" class="del">删除</a>'+
                    '</td>';

            } else if ($(this).parent().find('tbody').hasClass('new_rep')) {
                var $tbody = $('.new_rep');
                var str =
                    '<td ><input type="text" class="dateIcon form_datetime"></td>' +
                    '<td ><input type="text" ></td>' +
                    '<td ><input type="text" ></td>';

            } else if ($(this).parent().find('tbody').hasClass('new_repay')) {
                var $tbody = $('.new_repay');
                var str =
                    '<td ><input type="text" class="dateIcon form_datetime"></td>' +
                    '<td ><input type="text" ></td>' +
                    '<td ><input type="text" ></td>' +
                    '<td><a class="del"  href="javascript:;">删除</a></td>';

            } else if ($(this).parent().find('tbody').hasClass('ensure-tb')) {
                var $tbody = $('.ensure-tb');
                var str =
                    '<td><input type="text" class="form-control"></td>' +
                    '<td><input type="text" class="form-control"></td>' +
                    '<td><input type="text" class="form-control"></td>' +
                    '<td>' +
                    '<select class="form-control">' +
                    '<option value="">参考抵押</option>' +
                    '<option value="">客账抵押</option>' +
                    '<option value="">证券抵押</option>' +
                    '<option value="">设备抵押</option>' +
                    '<option value="">不动产抵押</option>' +
                    '<option value="">人寿抵押</option>' +
                    '<option value="">其它</option>' +
                    '</select>' +
                    '</td>' +
                    '<td><input type="text" class="form-control"></td>' +
                    '<td>' +
                    '<a href="javascript:;" class="del">删除</a>' +
                    '</td>';
            } else if ($(this).parent().find('tbody').hasClass('mortgage-tb')) {
                var $tbody = $('.mortgage-tb');
                var str =
                    '<td><input type="text" class="form-control"></td>' +
                    '<td><input type="text" class="form-control"></td>' +
                    '<td><input type="text" class="form-control"></td>' +
                    '<td>' +
                    '<select class="form-control">' +
                    '<option value="">参考抵押</option>' +
                    '<option value="">客账抵押</option>' +
                    '<option value="">证券抵押</option>' +
                    '<option value="">设备抵押</option>' +
                    '<option value="">不动产抵押</option>' +
                    '<option value="">人寿抵押</option>' +
                    '<option value="">其它</option>' +
                    '</select>' +
                    '</td>' +
                    '<td><input type="text" class="form-control"></td>' +
                    '<td>' +
                    '<a href="javascript:;" class="del">删除</a>' +
                    '</td>';
            } else if ($(this).parent().find('tbody').hasClass('pledge-tb')) {
                var $tbody = $('.pledge-tb');
                var str =
                    '<td><input type="text" class="form-control"></td>' +
                    '<td><input type="text" class="form-control"></td>' +
                    '<td><input type="text" class="form-control"></td>' +
                    '<td>' +
                    '<select class="form-control">' +
                    '<option value="">参考抵押</option>' +
                    '<option value="">客账抵押</option>' +
                    '<option value="">证券抵押</option>' +
                    '<option value="">设备抵押</option>' +
                    '<option value="">不动产抵押</option>' +
                    '<option value="">人寿抵押</option>' +
                    '<option value="">其它</option>' +
                    '</select>' +
                    '</td>' +
                    '<td><input type="text" class="form-control"></td>' +
                    '<td>' +
                    '<a href="javascript:;" class="del">删除</a>' +
                    '</td>' +
                    '</tr>';
            } else if ($(this).parent().find('tbody').hasClass('rep-info')){
                var $tbody = $('.rep-info');
                var str =
                    '<td ><input type="text" class="dateIcon form_secondTime"></td>' +
                    '<td ><input type="number"></td>' +
                    '<td ><input type="text" class="dateIcon form_secondTime"></td>' +
                    '<td ><input type="text" class="dateIcon form_secondTime"></td>' +
                    '<td></td>';
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
