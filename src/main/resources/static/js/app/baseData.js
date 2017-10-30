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
            } else {
                return false;
            }
            var $tr = $('<tr></tr>');
            $tr.get(0).innerHTML = str;
            $tbody.append($tr);
            seajs.use('common', function (c) {
                c.getDayly();
                c.getMonthly();
                c.format();
            })
        })
    };

})