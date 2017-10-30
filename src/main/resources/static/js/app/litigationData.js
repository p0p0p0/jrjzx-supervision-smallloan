define(function (require,exports,module) {
    exports.litigationData  = function () {
        require('bootstrapTable');
        require('bootstrapTableCN');
        console.log(99)
        var $litigationData = $('#litigation-data');

        var actionEvents = {
            'click .view': function (e, value, row, index) {
                console.log('查看');
            },
            'click .del': function (e, value, row, index) {
                require('layer');
                layer.confirm('确定要删除改条数据？？', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $litigationData.bootstrapTable('remove', {
                        field: 'id',
                        values: [row.id]
                    });
                    layer.closeAll();
                }, function () {
                    layer.closeAll();
                });
            }
        };
        function actionFormatter(value, row, index) {
            var view = '<a class="view">合同查看</a> &nbsp;'
            var del = '<a class="del">删除</a> &nbsp;';
            return view + "  " + del;
        };
        $litigationData.bootstrapTable({
            url: 'static/json/litigationData.json',
            columns: [{
                field: 'deadline',
                title: '诉讼截止时间'
            }, {
                field: 'amount',
                title: '诉讼总金额（万元）'
            }, {
                field: 'total',
                title: '总笔数'
            }, {
                field: 'operation',
                title: '操作',
                events: actionEvents,
                formatter: actionFormatter
            }],
            pagination: true, //分页
            pageNumber: 1,
            pageSize: 10,
        });
    };
})