define(function (require, exports, module) {

    exports.contractTransfer = function () {
        require('bootstrapTable');
        require('bootstrapTableCN');

        var $contractTransfer = $('#contract-transfer');

        var actionEvents = {
            'click .view': function (e, value, row, index) {
                //修改操作
                console.log('查看');
            },
            'click .delete': function (e, value, row, index) {
                //删除操作
                console.log('del');
                require('layer');
                layer.confirm('确定要删除改条数据？？', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $contractTransfer.bootstrapTable('remove', {
                        field: 'transferNum',
                        values: [row.transferNum]
                    });
                    layer.closeAll();
                }, function () {
                    layer.closeAll();
                });


            }
        };

        function actionFormatter(value, row, index) {
            var view = '<a class="view">修改/查看</a> &nbsp;'
            var del = '<a class="delete">删除</a> &nbsp;';
            return view + "  " + del;
        };
        $contractTransfer.bootstrapTable({
            url: 'static/json/contractTransfer.json',
            columns: [{
                field: 'transferNum',
                title: '转让编号'
            }, {
                field: 'sellUnit',
                title: '出让单位'
            }, {
                field: 'lumpSum',
                title: '转让金（元）'
            }, {
                field: 'originalAssets',
                title: '原资产金额（元）'
            }, {
                field: 'discountAmount',
                title: '溢价\折价金额（元）'
            }, {
                field: 'acceptingUnit',
                title: '受让单位'
            }, {
                field: 'transferContractNum',
                title: '转让协议编号'
            }, {
                field: 'transferDate',
                title: '转让日期',
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


    };

    exports.hadTransferContract = function () {
        require('bootstrapTable');
        require('bootstrapTableCN');
        var $hadTransferContract = $('#had-transfer-contract');


        var actionEvents = {
            'click .view': function (e, value, row, index) {
                //修改操作
                console.log('查看');
            },

        };
        function actionFormatter(value, row, index) {
            var view = '<a class="view">合同查看</a> &nbsp;';
            return view
        };
        $hadTransferContract.bootstrapTable({
            url: 'static/json/transferContract.json',
            columns: [{
                field: 'loanContractNum',
                title: '贷款合同编号'
            }, {
                field: 'reportedDate',
                title: '报备日期'
            }, {
                field: 'loanDate',
                title: '贷款日期'
            }, {
                field: 'borrower',
                title: '借款人'
            }, {
                field: 'amount',
                title: '金额（元）'
            }, {
                field: 'timeLimit',
                title: '期限'
            }, {
                field: 'annualRate',
                title: '年化利率'
            }, {
                field: 'balance',
                title: '余额',
            }, {
                field: 'isRollOver',
                title: '是否展期',
            }
                , {
                    field: 'state',
                    title: '状态',
                }, {
                    field: 'operation',
                    title: '操作',
                    events: actionEvents,
                    formatter: actionFormatter
                }],
            pagination: true, //分页
            pageNumber: 1,
            pageSize: 10

        });


    };

    exports.transferContract = function () {
        require('bootstrapTable');
        require('bootstrapTableCN');
        var $transferContract = $('#transfer-contract');


        var actionEvents = {
            'click .view': function (e, value, row, index) {
                //修改操作
                console.log('查看');
            },
            'click .delete': function (e, value, row, index) {
                //删除操作
                console.log('del');
                require('layer');
                layer.confirm('确定要删除改条数据？？', {
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $transferContract.bootstrapTable('remove', {
                        field: 'transferNum',
                        values: [row.transferNum]
                    });
                    layer.closeAll();
                }, function () {
                    layer.closeAll();
                });


            }
        };
        function actionFormatter(value, row, index) {
            var view = '<a class="view">合同查看</a> &nbsp;'
            var del = '<a class="delete">删除</a> &nbsp;';
            return view + "  " + del;
        };
        $transferContract.bootstrapTable({
            url: 'static/json/transferContract.json',
            columns: [{
                field: 'loanContractNum',
                title: '贷款合同编号'
            }, {
                field: 'reportedDate',
                title: '报备日期'
            }, {
                field: 'loanDate',
                title: '贷款日期'
            }, {
                field: 'borrower',
                title: '借款人'
            }, {
                field: 'amount',
                title: '金额（元）'
            }, {
                field: 'timeLimit',
                title: '期限'
            }, {
                field: 'annualRate',
                title: '年化利率'
            }, {
                field: 'balance',
                title: '余额',
            }, {
                    field: 'isRollOver',
                    title: '是否展期',
                }
                , {
                    field: 'state',
                    title: '状态',
                }, {
                    field: 'operation',
                    title: '操作',
                    events: actionEvents,
                    formatter: actionFormatter
                }],
            pagination: true, //分页
            pageNumber: 1,
            pageSize: 10

        });


    };

    exports.selectContract = function () {
        require('bootstrapTable');
        require('bootstrapTableCN');
        var $selectContract = $('#select-contract');

        var actionEvents = {
            'click .view': function (e, value, row, index) {
                console.log('查看');
            },
            'click .select': function (e, value, row, index) {
                console.log('选择转让');
            }
        };
        function actionFormatter(value, row, index) {
            var view = '<a class="view">合同查看</a> &nbsp;'
            var del = '<a class="select">选择转让</a> &nbsp;';
            return view + "  " + del;
        };
        $selectContract.bootstrapTable({
            url: 'static/json/selectContract.json',
            columns: [{
                field: 'loanContractNum',
                title: '贷款合同编号'
            }, {
                field: 'reportedDate',
                title: '报备日期'
            }, {
                field: 'loanDate',
                title: '贷款日期'
            }, {
                field: 'borrower',
                title: '借款人'
            }, {
                field: 'amount',
                title: '金额（元）'
            }, {
                field: 'timeLimit',
                title: '期限'
            }, {
                field: 'annualRate',
                title: '年化利率'
            }, {
                field: 'balance',
                title: '余额',
            }, {
                field: 'isRollOver',
                title: '是否展期',
            }, {
                    field: 'state',
                    title: '状态',
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