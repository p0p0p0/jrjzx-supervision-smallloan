define(function (require, exports, module){
    require('jquery');
    //消息弹窗
    exports.showMessage = function () {
        require('layer');
        var $newList = $('.regulated-info-content ul li');
        $newList.on('click', function () {
            layer.open({
                type: 1,
                skin: 'layui-layer-rim', //加上边框
                area: ['420px', '240px'], //宽高
                content: '监管消息监管消息监管消息'
            });
            $(this).css({
                color: '#bbb'
            });
        });
    }


    //五类资产图表
    exports.assetCharts = function (id) {
        require('echarts');
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById(id));

        // 指定图表的配置项和数据
        var option = {
            backgroundColor: '#fff',//背景色
            title: {
                text: '贷款资产五级分类'
            },

            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['正常', '关注', '次级', '可疑', '损失']
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                type: 'category',
                boundaryGap: false,
                data: ['2016-11', '2016-12', '2017-01', '2017-02', '2017-03', '2017-04']
            },
            yAxis: {
                type: 'value',
                axisLabel: {
                    formatter: '{value}万元'
                }
            },
            series: [
                {
                    name: '正常',
                    type: 'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data: ['5000', '4000', '4100', '4200', '3600', '600']
                },
                {
                    name: '关注',
                    type: 'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data: ['800', '850', '700', '720', '600', '300']
                },
                {
                    name: '次级',
                    type: 'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data: ['700', '650', '620', '580', '400', '200']
                },
                {
                    name: '可疑',
                    type: 'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data: ['600', '520', '250', '230', '160', '100']
                },
                {
                    name: '损失',
                    type: 'line',
                    stack: '总量',
                    areaStyle: {normal: {}},
                    data: ['100', '80', '60', '50', '40', '30']
                }
            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    };

})