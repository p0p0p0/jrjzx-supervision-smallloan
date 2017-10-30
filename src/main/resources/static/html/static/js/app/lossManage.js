define(function (require, exports, module) {

        exports.lossManage = function () {
            require('bootstrapTable');
            require('bootstrapTableCN');

            var $lossMangement = $('#loss-mangement');
            var $insertRow = $('#insertRow');


            var actionEvents = {
                'click .view': function (e, value, row, index) {
                    //修改操作
                    console.log('查看');
                },
                'click .delete': function (e, value, row, index) {
                    //删除操作
                    console.log('del');
                    $lossMangement.bootstrapTable('remove', {
                        field: 'id',
                        values: [row.id]
                    });
                }
            };
            function actionFormatter(value, row, index) {
                return '<a class="delete">删除</a>';
            };

            $lossMangement.bootstrapTable({
                url: 'static/json/baosun1.json',
                columns: [{
                    field: 'id',
                    title: 'ID'
                }, {
                    field: 'badMenoy',
                    title: '坏账金额（元）'
                }, {
                    field: 'badDate',
                    title: '定损日期',
                    formatter: function () {
                        return '<input type="text" class="form-control form_datetime">'
                    },
                }, {
                    field: 'badType',
                    title: '坏账类型'
                }, {
                    field: 'otherRecord',
                    title: '第三方系统记录'
                }, {
                    field: 'isSure',
                    title: '是否已核实'
                }, {
                    field: 'afterWork',
                    title: '后续工作',
                    formatter: function () {
                        return '<a class="view">查看</a>'
                    },
                    events: actionEvents
                }, {
                    field: 'operation',
                    title: '操作',
                    events: actionEvents,
                    formatter: actionFormatter
                }],
                pagination: true, //分页
                pageNumber: 1,
                pageSize: 10,
                // pageList:[10,20,30,All]


            });

            $insertRow.on('click', function () {
                $lossMangement.bootstrapTable('insertRow', {
                    index:$lossMangement.bootstrapTable('getOptions').totalRows,
                    row:{
                        "id":"01",
                        "badMenoy":5666,
                        "badDate":'<input type="text" onclick="console.log(111)" class="form-control form_datetime">'
                    }
                });
            });


            $lossMangement.bootstrapTable({
                'onClickRow':function () {
                    console.log('单击了一行');
                },

            });
            $lossMangement.on('onAll.bs.table', function () {
                console.log('单击了sadad一行');
            });








        }
    }
)