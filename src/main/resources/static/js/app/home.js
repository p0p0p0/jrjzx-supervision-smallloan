


define(function (require, exports, module){

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
        myChart.showLoading({
            text : '数据获取中...',
            effect : 'whirling'
        });
        //get assets list
        $.ajax({
            url: "index/five-degrees-asset",
            type: 'GET',
            contentType: 'application/json',
            success: function (data) {    
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
                        data: ['正常', '关注', '次级', '可疑', '损失'],
                        right:20,
                        top:10
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
                        data: $.getLastMonths(new Date(),6).reverse()
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
                            tiled: '总量',
                            areaStyle: {normal: {}},
                            data: data["normal"]
                        },
                        {
                            name: '关注',
                            type: 'line',
                            tiled: '总量',
                            areaStyle: {normal: {}},
                            data: data["follow"]
                        },
                        {
                            name: '次级',
                            type: 'line',
                            tiled: '总量',
                            areaStyle: {normal: {}},
                            data: data["minor"]
                        },
                        {
                            name: '可疑',
                            type: 'line',
                            tiled: '总量',
                            areaStyle: {normal: {}},
                            data: data["suspicious"]
                        },
                        {
                            name: '损失',
                            type: 'line',
                            tiled: '总量',
                            areaStyle: {normal: {}},
                            data: data["loss"]
                        }
                    ]
                };
                // 使用刚指定的配置项和数据显示图表。
                myChart.hideLoading();
                myChart.setOption(option);
            },
            error: function () {
            	bootbox.prompt("Oh! Something went wrong!")
            }
        });
       
      
    };

})