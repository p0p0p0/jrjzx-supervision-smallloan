define(function (require, exports, module) {
    require('jquery');
    /**
     * 财务页面生成
     */
    exports.financePage = function () {

        require('echarts');
        require('easyui');

        var $echarView = $('#financial-char'),       // char
            $tableView = $('#tableView'),            //table 视图
            $tabBtn = $('.finance-left>button'),     // 却换按钮
            titleArr = [
                '2015-01-01',
                '2015-01-02',
                '2015-01-03',
                '2015-01-04',
                '2015-01-05',
                '2015-01-06',
                '2015-01-07',
                '2015-01-08',
                '2015-01-09',
                '2015-01-10',
                '2015-01-11',
                '2015-01-12',
            ],     //标题数组
            contentArr = [],     // 存放一行内容数组
            titleName = '',      //char 的标题
            url = "static/json/fuzhai.json",
            urlArr = [
                "static/json/fuzhai.json",
                "static/json/niandu.json",
                "static/json/xianjin.json",
                "static/json/yuedu.json"
            ];

        $tabBtn.on('click', function () {
            var index = $(this).index();
            url = urlArr[index];
            render(0);
        });

        // 初次加载 生成第一行数据的绘制char
        render(0);
        // 根据行号绘制char
        function render(rowIndex) {
            $.ajax({
                url: url,
                dataType: 'json',
                success: function (data) {
                    // 重定向url 更新tableView
                    $tableView.datagrid({url: url});

                    //重新绘制char
                    contentArr = []; // 清空数组
                    for (var i in data[rowIndex]) {
                        contentArr.push(data[rowIndex][i]);
                    }
                    titleName = contentArr.slice(1, 2).toString();
                    contentArr.splice(0, 2);

                    //横向滚动条联动
                    var contents2 = $('div.datagrid-view2 div.datagrid-body');
                    contents2.on('mousedown', function () {
                        contents2.on('scroll', function (event) {
                            var e = event || window.event;
                            var leftX = -e.currentTarget.scrollLeft;
                            $echarView.css('left', leftX);
                        });
                    });
                    renderchar();
                }
            })
        };
        function renderchar() {
            // 基于准备好的dom，初始化echarts实例
            var myChart = echarts.init(document.getElementById('financial-char'));
            // 指定图表的配置项和数据
            var option = {
                title: {
                    subtext: '单位万元',
                },
                tooltip: {
                    trigger: 'axis'
                },
                legend: {
                    data: [titleName]
                },
                textStyle: {
                    color: 'black'
                },
                backgroundColor: "white",
                xAxis: {
                    type: 'category',
                    boundaryGap: false,
                    offset: 10,
                    data: titleArr
                },
                yAxis: {
                    type: 'value',
                    show: true,
                    offset: 10,
                    axisLabel: {
//                        formatter: '{value}万元'
                    },
                },
                series: [
                    {
                        name: titleName,
                        type: 'line',
                        data: contentArr,
                        markPoint: {
                            data: [
                                {type: 'max', name: '最大值'},
                                {type: 'min', name: '最小值'},
                            ]
                        },
                        itemStyle: {normal: {label: {show: true}}}
                    },
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        };
        // 定义点击行的回调函数
        $tableView.datagrid({
            onClickRow: function (rowIndex) {
                render(rowIndex);
            }
        })
    }

    /**
     * 财务数据图表
     * @param id
     */
    exports.finaceCharts = function (id) {
        require('echarts');
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById(id));
        // 指定图表的配置项和数据
        var option = {
            tooltip: {
                trigger: 'axis'
            },
            legend: {
                data: ['预付存款']
            },
            textStyle: {
                color: 'black'
            },
            backgroundColor: "white",
            xAxis: {
                type: 'category',
                boundaryGap: false,
                offset: -20,
                data: ['2016-11', '2016-12', '2017-01', '2017-02', '2017-03', '2017-04', '2016-12', '2017-01', '2017-02', '2017-03', '2017-04', '2016-12', '2017-01', '2017-02', '2017-03', '2017-04']
            },
            yAxis: {
                type: 'value',
                show: true,
                axisLabel: {
                    formatter: '{value}万元'
                },
            },
            series: [
                {
                    name: '预付存款',
                    type: 'line',
                    data: ['1200', '1300', '2400', '3000', '2400', '1600', '1300', '2400', '3000', '2400', '1600', '1300', '2400', '3000', '2400', '1600'],
                    markPoint: {
                        data: [
                            {type: 'max', name: '最大值'},
                            {type: 'min', name: '最小值'},
                        ]
                    },
                },

            ]
        };
        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    };

    /**
     * 滚动条
     */
    exports.bar = function () {
        // 1 获取标签
        var financialBox = $('.finance-content').get(0); // 外盒子
        var char = $('#financial-char').get(0); //图表
        var scrollBox = $('#scroll-box').get(0); //滚动盒子
        var bar = $('#scroll-bar').get(0); //滚动条
        var tableContent = $('#finance-data-outBox').get(0); //表格
        //2 取值
        var financialBoxW = financialBox.offsetWidth;
        var charW = char.offsetWidth;
        var scrollBoxW = scrollBox.offsetWidth;
        var barW = bar.offsetWidth;
        //监听鼠标的相应事件
        bar.onmousedown = function (event) {
            var event = event || window.event;
            // 3.1 求出起始位置
            var beginX = event.clientX - bar.offsetLeft;

            // 3.2 鼠标拖拽
            document.onmousemove = function (event) {
                var event = event || window.event;
                var endX = event.clientX - beginX;

                // 3.2.1 边界值处理
                if (endX < 0) {
                    endX = 0;
                } else if (endX > scrollBoxW - barW) {
                    endX = scrollBoxW - barW;
                }

                // 正常情况 盒子滚动
                // 内容走的距离 = （内容的长度 - 盒子的长度） / (盒子长度 - 滚动条的长度) * 滚动条走的距离
                var charDis = (charW - financialBoxW) / (scrollBoxW - barW) * endX;
                char.style.left = -charDis + 'px';
                tableContent.style.marginLeft = -charDis + 'px';
                bar.style.left = endX + 'px';
                window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
            }
            // 3.3 松开鼠标
            document.onmouseup = function () {
                document.onmousemove = null;
            }
        }
        $('.finance-content-left button').on('click', function () {
            var index = $(this).index();
            $(this).addClass('active').siblings().removeClass('active');
            $('.finance-data-box .finance-data').eq(index).addClass('active').siblings().removeClass('active');

            //利润表处理
            if (index === 1) {
                $('.finance-content .finance-content-left ul').css('display', 'block');
                $('.finance-content .finance-content-left ul li').on('click', function () {
                    $(this).addClass('active').siblings().removeClass('active');
                    $('.finance-data-box .finance-data .profit').eq($(this).index()).addClass('active').siblings().removeClass('active');
                })
            } else {
                $('.finance-content .finance-content-left ul').css('display', 'none');
            }
        });
    };


    /**
     * 自动生成发布月份表格
     * @param id
     * @param url
     */
    exports.getMonthTable = function (id, url) {
        var monthArr = [];
        $.ajax({
            url: url,
            dataType: 'json',
            success: function (data) {
                for (var i in data) {
                    //去掉- 转成数字
                    var newMouth = data[i].replace('-', '');
                    monthArr.push(parseInt(newMouth));
                }
                // 当前属于哪一年
                var curYear = parseInt(new Date().getFullYear());
                // 数据中最小年份
                var minYear = parseInt((Math.min.apply(Math, monthArr)) / 100);

                // 根据差 算出需要加载表格行数
                var trList = curYear - minYear + 1;
                var trHtml = '';
                var oneTr = '<tr><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td><td></td></tr>';
                for (var k = 0; k < trList; k++) {
                    trHtml += oneTr;
                }
                var $tbody = $('#' + id).find('tbody');
                $tbody.append($(trHtml));
                $tbody.find("tr td").css('width', "8.33%")

                //内容填充
                var row = 0, col = 0;
                $.each(monthArr, function (index, item) {
                    var year = parseInt(item / 100)
                    row = curYear - year; // 第几行
                    col = item % 100 - 1;  //第几列
                    $tbody.find("tr").eq(row).find("td").eq(col).html(year + '年' + (col + 1) + '月');
                })
            },
            error: function (err) {
                console.log(err)
            }
        })


    };

    
    
})