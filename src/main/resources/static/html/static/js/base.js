/*var app ='static/build/js/app/';
var lib ='static/build/lib/';
var url ='static/build/js/';*/
var app ='static/js/app/';
var lib ='static/build/lib/';
var url ='static/build/js/';

seajs.config({
    base:'./',
    alias:{

        'jquery':lib+'jquery.min',										//jQuery框架
        'bootstrap':lib+'bootstrap/bootstrap.min',						//Bootstrap框架
        'bootstrapTable':lib + 'bootstrap-table/bootstrap-table.js',     //bootstrap-table
        'bootstrapTableCN':lib + 'bootstrap-table/bootstrap-table-zh-CN.js', //bootstrap-table 中文
        'html5shiv':lib+'html5shiv.min',
        'respond':lib+'respond.min',

        'placeholder':lib+'placeholder.min',							//IE8反显插件
        'md5':lib+'md5.min',											//MD5加密插件
        'jqform':lib+'jquery.form.min',									//jQuery Form 插件
        'datetimepicker':lib+'bootstrap-datepicker/bootstrap-datetimepicker.min',	//日历插件
        'webuploader':lib+'webuploader/webuploader.min',				//图片上传插件
        'area': lib + 'area',										//城市三级联动插件
        'echarts':lib + 'echarts/echarts',                              //echars 插件
        'layer':lib+'layer/layer.min',									//弹框插件
        'easyui':lib+'easyui/jquery.easyui.min',                        //easyui 插件

        'common':app+'common',										//通用JS'
        'home':app+'home',										    //首页'
        'financialData':app+'financialData',						//财务数据JS'
        'baseData':app+'baseData',								    //基础数据JS'
        'extendAndRepay':app+'extendAndRepay',					    //展期与还款JS'
        'loanContract':app+'loanContract',					        //贷款合同JS'
        'lossManage':app + 'lossManage',                            //报损管理
        'accountData':app + 'accountData',                           //账户数据
        'financing':app + 'financing',                               //融资数据
        'user':app + 'user',                                        //用户登录模块

        'upload':url+'upload',										//图片上传配置

    },
    charset:'utf-8',
})