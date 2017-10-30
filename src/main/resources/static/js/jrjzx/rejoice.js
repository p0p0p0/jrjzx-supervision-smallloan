/*getSelectedIds*/
function getSelectedIds(rows){
	var ids = []
	rows.forEach(function(row) {
	    ids.push(row.id)
	});
	return ids.join(",");
}

//global plugin
$.extend({
    StandardPost:function(url,args){
        var body = $(document.body),
            form = $("<form method='post'></form>"),
            input;
        form.attr({"action":url});
        $.each(args,function(key,value){
            input = $("<input type='hidden'>");
            input.attr({"name":key});
            input.val(value);
            form.append(input);
        });

        form.appendTo(document.body);
        form.submit();
        document.body.removeChild(form[0]);
    },
    getLastMonths:function(date,months){
    	var fullMonths = []; 
        var currentMonth =  date.getMonth();
        var currentYear = date.getFullYear();
        for(var i = 0; i < months; i++){
        	if(currentMonth == 0){
        		currentYear = currentYear-1;
        		currentMonth = 11;
        	}else{
        		currentMonth = currentMonth-1;
        	}
        	fullMonths.push(currentYear+"-"+(currentMonth+1));
        }
        return fullMonths;
    },
    toolTip:function(){
    	
    }
    
});

//obj plugin
jQuery.fn.extend({
	  toolTip: function(text) { 
		  //$(this).parent().find(".bs-bars").prepend("<span>"+text+"</span>")
		 $(this).parent().prepend("<span style='color:#FFF;background:black;'>"+text+"</span>")
	  }
	});


function showAlert($alert,title, type) {
    $alert.attr('class', 'alert alert-' + type || 'success')
    .html('<i class="glyphicon glyphicon-check"></i> ' + title).show();
setTimeout(function () {
  $alert.hide();
}, 3000);
}
/*global error*/
$( document ).ajaxError(function(event, jqxhr, settings, thrownError ) {
	if(jqxhr.status == 0){
		return;
	}
	console.info(jqxhr.responseText)
	if(jqxhr.responseText.indexOf("登录")!=-1){
		window.location=contextPath+"/page/login.html";
	}
	bootbox.alert("系统错误："+jqxhr.status+" "+jqxhr.responseText)
	throw new Error("something went wrong")
});
(function($){
	//override ajax method,redirect to login page when session expired
    //首先备份下jquery的ajax方法
    var _ajax=$.ajax;
     
    //重写jquery的ajax方法
    $.ajax=function(opt){
        //备份opt中error和success方法
        var fn = {
            error:function(XMLHttpRequest, textStatus, errorThrown){},
            success:function(data, textStatus){}
        }
        if(opt.error){
            fn.error=opt.error;
        }
        if(opt.success){
            fn.success=opt.success;
        }
         
        //扩展增强处理
        var _opt = $.extend(opt,{
            error:function(XMLHttpRequest, textStatus, errorThrown){
                //错误方法增强处理
            	if(XMLHttpRequest.responseText.indexOf("登录")!=-1){
            		window.location=contextPath+"/page/login.html";
            		return;
            	}
                fn.error(XMLHttpRequest, textStatus, errorThrown);
            },
            success:function(data, textStatus){
                //成功回调方法增强处理
            	if(JSON.stringify(data).indexOf("登录")!=-1){
            		window.location=contextPath+"/page/login.html";
            		return;
            	}
                fn.success(data, textStatus);
            },
            beforeSend:function(XHR){
                //提交前回调方法
                $('body').append("<div id='ajaxInfo' style=''>正在加载,请稍后...</div>");
            },
            complete:function(XHR, TS){
                //请求完成后回调函数 (请求成功或失败之后均调用)。
            	
                $("#ajaxInfo").remove();;
            }
        });
        return _ajax(_opt);
    };
})(jQuery);
//yyyy-MM-dd
var dateformat1 = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)";
//yyyy-MM-dd HH:mm:ss
var dateformat2 = "((([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)) (20|21|22|23|[0-1][0-9]):[0-5][0-9]:[0-5][0-9]";

//custom plugin
jQuery.extend({
	/*serialize form to json*/
    serializeFormJSON: function () {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function () {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    },
    //check date
    isDateFormat1:function(val){
    	return new RegExp(dateformat1).test(val);
    },
    isDateFormat2:function(val){
    	return new RegExp(dateformat2).test(val);
    }
});

/*get url param*/
function getUrlParam(name) {  
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)","i");  
    var r = window.location.search.substr(1).match(reg);  
    if (r!=null) return (r[2]); return null;  
} 