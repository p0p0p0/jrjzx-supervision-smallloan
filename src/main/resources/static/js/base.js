var app = contextPath+'/build/js/app/';
var lib = contextPath+'/build/lib/';
// var url ='static/build/js/';]
var url =contextPath+'/js/app/';
seajs.config({
    base:'./',
    alias:{
        'jquery':lib+'jquery.min',										//jQuery框架
        'bootstrap':lib+'bootstrap/bootstrap.min',						//Bootstrap框架
        'bootstrapTable':lib + 'bootstrap-table/bootstrap-table.min',     //bootstrap-table
        'bootstrapTableCN':lib + 'bootstrap-table/bootstrap-table-zh-CN.min', //bootstrap-table 中文
        'html5shiv':lib+'html5shiv.min',
        'respond':lib+'respond.min',
        'layer':lib+'layer/layer.min',									//弹框插件
        'placeholder':lib+'placeholder.min',							//IE8反显插件
        'md5':lib+'md5.min',											//MD5加密插件
        'jqform':lib+'jquery.form.min',									//jQuery Form 插件
        'jqueryValidation':lib+'jQueryValidation/jquery.validate.min',      //jQuery 表单验证插件
        'datetimepicker':lib+'bootstrap-datepicker/bootstrap-datetimepicker.min',			//日历插件
        'webuploader':lib+'webuploader/webuploader.min',				//图片上传插件
        'city': lib + 'city.min',										//城市三级联动插件
        'echarts':lib + 'echarts/echarts.common.min',                                  //echars 插件

        'area': lib + 'area',										//城市三级联动插件

        'common':url+'common',										//通用JS'
        'home':url+'home',										    //首页'
        'financialData':url+'financialData',						//财务数据JS'
        'baseData':url+'baseData',								    //基础数据JS'
        'extendAndRepay':url+'extendAndRepay',					    //展期与还款JS'
        'loanContract':url+'loanContract',					        //贷款合同JS'
        'lossManage':url + 'lossManage',                            //报损管理
        'accountData':url + 'accountData',                           //账户数据
        'financing':url + 'financing',                               //融资数据
        'contractTransfer':url + 'contractTransfer',                //合同转让
        'litigationData':url + 'litigationData',                    //诉讼数据
        'user':url + 'user',                                        //用户登录模块
        
        // 'upload':url+'upload.min',										//图片上传配置

    },
    charset:'utf-8',
})