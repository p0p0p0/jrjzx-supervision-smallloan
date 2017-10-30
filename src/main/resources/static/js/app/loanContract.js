define(function (require, exports, module) {
    /**
     *
     * @constructor
     */
    exports.LoanStyle = function () {
        //监听input的onchange 事件
        $("#ensure").on('change', function () {
            var $ensure = $('.ensure');
            if ($(this).prop("checked")) {
                $ensure.fadeIn(200);
            } else {
                $ensure.fadeOut(200);
            }
        });

        $("#mortgage").on('change', function () {
            var $mortgage = $('.mortgage');
            if ($(this).prop("checked")) {
                $mortgage.fadeIn(200);
            } else {
                $mortgage.fadeOut(200);
            }
        });

        $("#pledge").on('change', function () {
            var $pledge = $('.pledge');
            if ($(this).prop("checked")) {
                $pledge.fadeIn(200);
            } else {
                $pledge.fadeOut(200);
            }
        });
    };

    /**
     * 省市区三级联动
     * @param initArea 地域数组
     */
    exports.city = function (initArea) {
        require('area')
        _init_area();
        var show = document.getElementById('show');
        var county = document.getElementById('s_county');
        var city = document.getElementById('s_city');
        var province = document.getElementById('s_province');
        var showArea = function () {
            show.innerHTML = "<h3> 省" + province.value + " - 市" +
                city.value + " - 县/区" +
                county.value + "</h3>"
        }
        changeTo();
        function changeTo() {
            var sheng = initArea[0];
            var shi = initArea[1];
            var xian = initArea[2];
            var shengOpt = $(province).find('option');
            var shiOpt =[];



            for(var i =0;i <shengOpt.length;i++){
                if(sheng === shengOpt[i].value){
                    shengOpt[i].setAttribute('selected','true');
                 
                    change(1);
                    shiOpt = $(city).find('option');
                    for(var j =0;j <shiOpt.length;j++){
                        if(shi === shiOpt[j].value){
                            shiOpt[j].setAttribute('selected','true');
                            // opt0 = [sheng,shi,"县级市"]
                            change(2);
                            county.value = xian;
                        }
                    }
                    break;
                }
            }
        }
    }
    
    
  //添加一行
    exports.addItem = function (btnName) {
        var $btn = $('.' + btnName);
        $btn.on('click', function () {
            if ($(this).parent().find('tbody').hasClass('new_rep')) {
                var $tbody = $('.new_rep');
                var str =
                    '<td ><input type="text" name="endDate" class="dateIcon form_datetime"></td>' +
                    '<td ><input class="number" type="text" name="money" ></td>' +
                    '<td ><input type="text" name="interest" ></td>';
            } else if ($(this).parent().find('tbody').hasClass('new_repay')) {
                //添加还款计划
                var $tbody = $('.new_repay');
                var size = $('.new_repay tr').length;
                if(size>0) {
                    var inputName=$('.new_repay').find('tr:last input:first').attr('name');
                    size = parseInt(inputName.substring(inputName.indexOf('[')+1,inputName.indexOf(']')))+1;
                }
                var str =
                    '<td ><input name="repayPlans['+size+'].endDate" type="text" class="dateIcon form_datetime"></td>'+
                    '<td ><input class="number" name="repayPlans['+size+'].money" type="text" ></td>'+
                    '<td ><input name="repayPlans['+size+'].interest" type="text" ></td>'+
                    '<td><a class="del"  href="javascript:;">删除</a></td>';
            } else if ($(this).parent().find('tbody').hasClass('ensure-tb')) {
                //保证合同
                var $tbody = $('.ensure-tb');
                var size = $('.ensure-tb tr').length;
                if(size>0) {
                    var inputName=$('.ensure-tb').find('tr:last input:first').attr('name');
                    size = parseInt(inputName.substring(inputName.indexOf('[')+1,inputName.indexOf(']')))+1;
                }
                var str =
                    '<td><input type="text" name="ensureContracts['+size+'].name" class="form-control"></td>'+
                    '<td>'+
                    '<select name="ensureContracts['+size+'].cardType" class="form-control">'+
                    '<option value="1">身份证</option>'+
                    '<option value="2">驾驶证</option>'+
                    '<option value="3">港澳通行证</option>'+
                    '<option value="4">护照</option>'+
                    '<option value="5">营业执照</option>'+
                    '</select>'+
                    '</td>'+
                    '<td><input type="text" name="ensureContracts['+size+'].cardNumber" class="form-control"></td>'+
                    '<td><input type="text" name="ensureContracts['+size+'].address" class="form-control"></td>'+
                    '<td>'+
                    '<a href="javascript:;" class="del">删除</a>'+
                    '</td>';
            } else if ($(this).parent().find('tbody').hasClass('mortgage-tb')) {
                //抵押
                var $tbody = $('.mortgage-tb');
                var size = $('.mortgage-tb tr').length;
                if(size>0) {
                    var inputName=$('.mortgage-tb').find('tr:last input:first').attr('name');
                    size = parseInt(inputName.substring(inputName.indexOf('[')+1,inputName.indexOf(']')))+1;
                }
                var str =
                    '<td><input type="text" name="mortgageContracts['+size+'].name" class="form-control"></td>' +
                    '<td><input type="text" name="mortgageContracts['+size+'].unit" class="form-control"></td>' +
                    '<td><input type="text" name="mortgageContracts['+size+'].address" class="form-control"></td>' +
                    '<td>' +
                    '<select name="mortgageContracts['+size+'].mortgageType" class="form-control">' +
                    '<option value="1">存货抵押</option>' +
                    '<option value="2">客账抵押</option>' +
                    '<option value="3">证券抵押</option>' +
                    '<option value="4">设备抵押</option>' +
                    '<option value="5">不动产抵押</option>' +
                    '<option value="6">人寿抵押</option>' +
                    '<option value="7">其它</option>' +
                    '</select>' +
                    '</td>' +
                    '<td><input type="text" name="mortgageContracts['+size+'].worth" class="form-control"></td>' +
                    '<td>' +
                    '<a href="javascript:;" class="del">删除</a>' +
                    '</td>';
            } else if ($(this).parent().find('tbody').hasClass('pledge-tb')) {
                //质押
                var $tbody = $('.pledge-tb');
                var size = $('.pledge-tb tr').length;
                if(size>0) {
                    var inputName=$('.pledge-tb').find('tr:last input:first').attr('name');
                    size = parseInt(inputName.substring(inputName.indexOf('[')+1,inputName.indexOf(']')))+1;
                }
                var str =
                    '<td><input type="text" name="pledgeContracts['+size+'].name" class="form-control"></td>'+
                    '<td><input type="text" name="pledgeContracts['+size+'].unit" class="form-control"></td>'+
                    '<td><input type="text" name="pledgeContracts['+size+'].address" class="form-control"></td>'+
                    '<td>'+
                    '<select name="pledgeContracts['+size+'].pledgeType" class="form-control">'+
                    '<option value="1">股权质押</option>'+
                    '<option value="2">定期存单质押</option>'+
                    '<option value="3">专利权质押</option>'+
                    '<option value="4">应收账款质押</option>'+
                    '<option value="5">其它</option>'+
                    '</select>'+
                    '</td>'+
                    '<td><input type="text" name="pledgeContracts['+size+'].worth" class="form-control"></td>'+
                    '<td>'+
                    '<a href="javascript:;" class="del">删除</a>'+
                    '</td>';

            }else {
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
    

    /**
     * 合同操作步骤
     * @constructor
     */
    exports.ContractProcess = function () {
    	
        var stepList = $('.add_loan_contract .step li'),
            $contentList = $('.add_loan_contract .fieldset');
            
        function trunTo(index) {
            stepList.eq(index).addClass('active').siblings().removeClass('active');
            $contentList.eq(index).addClass('active').siblings().removeClass('active');
        }
        
        //第一页按钮(0)
        //存稿
        
        //下一页
        $("#pageOneNext").click(function(){
        	
        	//校验合同基本信息必填字段
        	/*if($("input[name='contractNumber']").val() == ''){
				layer.msg('贷款合同编号不能为空');
				return false;
			}
			if($("input[name='signTime']").val() == ''){
				layer.msg('合同签订日期不能为空');
				return false;
			}
			if($("input[name='startDate']").val() == ''){
				layer.msg('贷款开始日期不能为空');
				return false;
			}
			if($("input[name='endDate']").val() == ''){
				layer.msg('贷款截止日期不能为空');
				return false;
			}
			if($("input[name='money']").val() <= 0){
				layer.msg('贷款金额必填，且金额须大于0');
				return false;
			}
			if($("input[name='termType']:checked").length==0){
				layer.msg('请选择贷款期限类型');
				return false;
			}
			if($("input[name='term']").val() <= 0){
				layer.msg('贷款期限必填，且数值须大于0');
				return false;
			}
			if($("input[name='rate']").val() <= 0){
				layer.msg('贷款利率必填，且数值须大于0');
				return false;
			}
			if($("select[name='repayMethod'] option:selected").val() <= 0){
				layer.msg('请选择还款方式');
				return false;
			}
			if($("select[name='areaProvince'] option:selected").val() == '省份' || $("select[name='areaCity'] option:selected").val() == '地级市'){
				layer.msg('请选择借款区域');
				return false;
			}
			if($("select[name='areaDistrict'] option:selected").val() == '市、县级市' && $("select[name='areaDistrict'] option").length > 1){
				layer.msg('请选择借款区域-【市、县级市】');
				return false;
			}
			if($("select[name='business'] option:selected").val() <= 0){
				layer.msg('请选择投向行业');
				return false;
			}
			if($("select[name='purpose'] option:selected").val() <= 0){
				layer.msg('请选择贷款用途');
				return false;
			}
			if($("input[name='repaySource']").val() == ''){
				layer.msg('还款来源不能为空');
				return false;
			}
			//贷款截止日期要晚于贷款开始日期
			var startDate=$("input[name='startDate']").val();
		    var endDate=$("input[name='endDate']").val();
		    var start=new Date(startDate.replace(/-/g, "/"));
		    var end=new Date(endDate.replace(/-/g, "/"));  
		    if(end<start){
		    	layer.msg('贷款截止日期不能早于贷款开始日期');
				return false;
		    }
			//校验还款计划必填字段
		    var flag = true;
        	$('.new_repay').find('tr input').each(function(){
        		if($(this).val() == '' || $(this).val().length == 0){
        			layer.msg('请填写完整还款计划');
					flag = false;
        		}
				
        	});
        	if(!flag){
        		return false;
        	}*/
			//第一页全部校验通过，进入下一页
			trunTo(1);
        });
        
        //第二页的按钮(1)
        $("#pageTwoPre").click(function(){
			trunTo(0);
        });
        $("#pageTwoNext").click(function(){
        	//借款客户类型
        	var borrowerType = $("input[name='borrowerType']:checked").val();
        	/*if(borrowerType == 2){
        		//校验自然人客户必填字段
        		if($("input[name='personBorrower.name']").val() == ''){
    				layer.msg('借款人不能为空');
    				return false;
    			}
        		if($("select[name='personBorrower.sex'] option:selected").val() <= 0){
    				layer.msg('请选择性别');
    				return false;
    			}
        		if($("input[name='personBorrower.cardNumber']").val() == ''){
    				layer.msg('证件号码不能为空');
    				return false;
    			}
        		if($("input[name='personBorrower.phone']").val() == ''){
    				layer.msg('联系电话不能为空');
    				return false;
    			}
        		if($("select[name='personBorrower.isMarry'] option:selected").val() < 0){
    				layer.msg('请选择婚姻状况');
    				return false;
    			}
        		if($("input[name='personBorrower.address']").val() == ''){
    				layer.msg('固定住址不能为空');
    				return false;
    			}
        	}else if (borrowerType == 1) {
        		//校验企业客户必填字段
        		if($("input[name='enterpriseBorrower.name']").val() == ''){
    				layer.msg('企业名称不能为空');
    				return false;
    			}
        		if($("select[name='enterpriseBorrower.scale'] option:selected").val() <= 0){
    				layer.msg('请选择企业规模');
    				return false;
    			}
        		if($("select[name='enterpriseBorrower.industryType'] option:selected").val() <= 0){
    				layer.msg('请选择产业类型');
    				return false;
    			}
        		if($("select[name='enterpriseBorrower.industryInvolved'] option:selected").val() <= 0){
    				layer.msg('请选择所属行业');
    				return false;
    			}
        		if($("input[name='enterpriseBorrower.organizationCode']").val() == ''){
    				layer.msg('组织机构代码不能为空');
    				return false;
    			}
        		if($("input[name='enterpriseBorrower.licenseCode']").val() == ''){
    				layer.msg('营业执照编号不能为空');
    				return false;
    			}
        		if($("input[name='enterpriseBorrower.licenseEndDate']").val() == ''){
    				layer.msg('营业执照截止日期不能为空');
    				return false;
    			}
        		if($("input[name='enterpriseBorrower.address']").val() == ''){
    				layer.msg('通信地址不能为空');
    				return false;
    			}
			}*/
        	
        	trunTo(2);
        });
        
        //第三页按钮(2)
        $("#pageThreePre").click(function(){
        	trunTo(1);
        });
        
        //最后提交
		$(".submitBtn").click(function(){
			//校验  抵押合同,质押合同,保证合同
			
			
			
			layer.confirm('确定提交保存合同信息？', {
				  btn: ['确定','取消'] //按钮
				}, function(){
					//layer.msg('的确很重要', {icon: 1});
					//提交表单
					$("#dataForm").submit();
				});
		});
    }

    exports.annualRate  = function () {
    	/*********自动计算和填充"折合年化利率"**************START**********/
        function annualRate(){
        	var annualRate = 0;
        	var termType = $("input[name='termType']:checked").val();
        	var rate = $("input[name='rate']").val();
        	if(termType > 0 && rate > 0){
        		//计算折合年化利率
        		if(termType == 1){
        			//天
        			annualRate = Math.round((rate * 360) * 100) / 100; 
            	}else if(termType == 2){
            		//周
            		annualRate = Math.round((rate * 52) * 100) / 100;
            	}else if(termType == 3){
            		//月
            		annualRate = Math.round((rate * 12) * 100) / 100;
            	}else if(termType == 4){
            		//季
            		annualRate = Math.round((rate * 4) * 100) / 100;
            	}else if(termType == 5){
            		annualRate = Math.round(rate * 100) / 100;
            	}
        		$("input[name='annualRate']").val(annualRate);
        	}
        };
        $("input[name='termType']").change(function(){
        	annualRate();
        	//动态改变贷款利率的"%(月)""
        	var termTypeDesc = '';
        	var termType = $("input[name='termType']:checked").val();
        	if(termType == 1){
        		termTypeDesc = '日';
        	}else if(termType == 2){
        		termTypeDesc = '周';
        	}else if(termType == 3){
        		termTypeDesc = '月';
        	}else if(termType == 4){
        		termTypeDesc = '季';
        	}else if(termType == 5){
        		termTypeDesc = '年';
        	}
        	if(termTypeDesc != ''){
	        	$(".termTypeDesc").html('%(' + termTypeDesc + ')');
        	}
        });
        $("input[name='rate']").change(function(){
        	annualRate();
        });
       /*********自动计算和填充折合年化利率**************END**********/
    };
    
    exports.createRepayPlan  = function () {
    	/****************自动生成还款计划*********START**********/
		$("#createRepayPlan").click(function(){
        	//还款方式
        	
        	var repayMethod = $("select[name='repayMethod'] option:selected").val();
        	if(repayMethod == 1){
	        	//等额本息
        		insertDengebenxiRow();
        		seajs.use('common',function (c) {
                    c.getDayly();
                    c.getMonthly();
                })
        	}else if (repayMethod == 2) {
	        	//等额本金
        		insertDengebenjinRow();
        		seajs.use('common',function (c) {
                    c.getDayly();
                    c.getMonthly();
                })
			}else if (repayMethod == 3) {
	        	//先息后本
				insertXianXiHouBenRow();
				seajs.use('common',function (c) {
		               c.getDayly();
		               c.getMonthly();
		           })
			}
        });
		/****************自动生成还款计划*********END**********/
    };

    /**
     * 高管普通员工信息表切换
     */
    exports.employeeInfoTab  = function () {
        $("input[name='borrowerType']").click(function () {
            if ($(this).val() == '2') {
                $('.naturalPerson').addClass('active').siblings().removeClass('active');
            } else {
                $('.enterprise').addClass('active').siblings().removeClass('active');
            }
        });
    };

    /**
     * 新建展期计划 弹窗
     */
    exports.newPlan = function () {
        require('layer');
        var $newBtn = $('#new-plan');
        $newBtn.on('click', function () {
            layer.confirm('新建展期后，原计划不可用，新建展期计划还款本金不能小于原计划还款本金余额。', {
                btn: ['确定', '取消'] //按钮
            }, function () {
                layer.closeAll();
            }, function () {
                //取消回调
            });

        })
    };

})