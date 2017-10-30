		   	
		//格式化日期
		Date.prototype.format = function(fmt) {
		    var o = {
		        "M+": this.getMonth() + 1,
		        //月份
		        "d+": this.getDate(),
		        //日
		        "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12,
		        //小时
		        "H+": this.getHours(),
		        //小时
		        "m+": this.getMinutes(),
		        //分
		        "s+": this.getSeconds(),
		        //秒
		        "q+": Math.floor((this.getMonth() + 3) / 3),
		        //季度
		        "S": this.getMilliseconds() //毫秒
		    };
		    var week = {"0": "\u65e5", "1": "\u4e00", "2": "\u4e8c", "3": "\u4e09", "4": "\u56db","5": "\u4e94", "6": "\u516d"};
		    if (/(y+)/.test(fmt)) {
		        fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
		    }
		    if (/(E+)/.test(fmt)) {
		        fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f": "\u5468") : "") + week[this.getDay() + ""]);
		    }
		    for (var k in o) {
		        if (new RegExp("(" + k + ")").test(fmt)) {
		            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
		        }
		    }
		    return fmt;
		}

		//个人贷款用途			
	    var purposeArray = ["流动资金贷款","固定资产投资贷款","其他"];			    			
		
	    //1）,2）, 3）, 4）, 5）, 6）, 7）, 8）, 9）,10）, 11）, 12）, 13）
	    var businessArray = ["居民服务和其他服务业","建筑业","交通运输、仓储和邮政业","农、林、牧、渔业","采矿业","制造业", "电力、燃气及水的生产和供应业","信息传输、计算机服务和软件业","批发和零售业","住宿和餐饮业","房地产业","租赁和商务服务业","其他"];	    	
	    	
	    var businessSubArray = [
	    						["服装","鞋帽","布匹","皮具","药材","水产品","其他"]
	    					 ];
		
		
		
		//小额贷款增加受托支付
		function insertShouTuoRow() {
		   
			var content = "开户行：<input name='loanContract.bank' class='inputbox set_width_04' type='text' />	"+			 
				  	  " 开户人：<input name='loanContract.openPeople' class='inputbox set_width_04' type='text' />"+
				 	  " 账户：<input name='loanContract.cardNum' class='inputbox set_width_04' type='text' />" +
				 	  " 金额：<input name='loanContract.cardMoney' class='inputbox set_width_02' type='text' />";
		  
		   var table = document.getElementById("shouTuoTable");
		   var newRow = table.insertRow(table.rows.length);
		   
		   var cell = newRow.insertCell(0);		  
		   cell.innerHTML = content;		    		  
		}
		
		//小额贷款删除受托支付
		function deleteShouTuoRow() { 
			var table = document.getElementById("shouTuoTable");			
			var index = table.rows.length - 1;
			
			if (index == 0) {  
				alert("至少定义一个账户!");
			  	return; 
			}
						
			if(confirm("确定删除?")) {  
				table.deleteRow(index); 
			}
		}
		
		//小额贷款增加定义期数
		function insertRow(index){
		   var table = document.getElementById("repayCountTable");		
		   var endDateArray = document.getElementsByName("repayCount.endDate");		
		   var tempEndDate = endDateArray[index].value;
		 
		   var moneyArray = document.getElementsByName("repayCount.money");		
		   var tempMoney = moneyArray[index].value;
		   
		   var lixiArray = document.getElementsByName("repayCount.lixi");		
		   var tempLixi = lixiArray[index].value;	   
		   
		   //alert(index + 1);
		   var content = " 截至日期<input name='repayCount.endDate' readonly='readonly' type='text' class='Wdate' onClick='WdatePicker()' value='"+tempEndDate+"'/>"+
					     " 还款本金<input name='repayCount.money' class='inputbox set_width_02' type='text' value='"+tempMoney+"'/>元"+
					     " 还款利息<input name='repayCount.lixi' class='inputbox set_width_02' type='text' value='"+tempLixi+"'/>元"+
					     " <a name='indexDelete' href='javascript:deleteRow("+(index + 1)+")' />删除</a>"+
					     " <a name='indexInsert' href='javascript:insertRow("+(index + 1)+")' />增加</a>";
		  
		  
		   var newRow = table.insertRow((index + 1));
		   
		   var cell = newRow.insertCell(0);		  
		   cell.innerHTML = content;	
		   
		   //重置还款计划 增删
			resetRepayCountTable();
		   	     
		}
		
		//重置还款计划 增删
		function resetRepayCountTable() {
			var indexDeleteArray = document.getElementsByName("indexDelete");
		    var indexInsertArray = document.getElementsByName("indexInsert");
		    //alert(indexDeleteArray.length);
		    for (var i = 0; i < indexDeleteArray.length; i++ ) {
		   		indexDeleteArray[i].href = "javascript: deleteRow("+i+")";
		   		indexInsertArray[i].href = "javascript: insertRow("+i+")";
		   		//alert(i);
		    }   		
		}
		
		//小额贷款删除定义期数
		function deleteRow(index) { 
			var table = document.getElementById("repayCountTable");			
			var tableIndex = table.rows.length - 1;
			
			if (tableIndex == 0) {  
				alert("至少定义一期还款!");
			  	return; 
			}
						
			if(confirm("确定删除?")) {  
				table.deleteRow(index); 
			}
			
		  	//重置还款计划 增删
			resetRepayCountTable();
		}
		
				
		//增加借款人		
		function insertLenderInfoTableRow() {
			//借款人类型
			var loanType = document.getElementById('loanContract.loanType');
			if(loanType.value == '自然人'){
				  var table = document.getElementById("personTable");
				   
				   var newRow = table.insertRow(table.rows.length);	
				   var cell = newRow.insertCell(0);			  
				
				   var tempCount = table.rows.length * 2;
				    
				   cell.innerHTML = getPersonValue(tempCount);//js定义	
				   
				   $.parser.parse(cell);				      
				   changePurposeOrBusiness();//调用切换	
				   var temp1 = "lenderInfor.province_"+tempCount;
				   var temp2 = "lenderInfor.city_"+tempCount;
				   var temp3 = "lenderInfor.area_"+tempCount;
				   	
				   addressInit(temp1, temp2, temp3);
			}else if(loanType.value == '企业'){
				  var table = document.getElementById("companyTable");
				   
				   var newRow = table.insertRow(table.rows.length);	
				   var cell = newRow.insertCell(0);			  
				
				   var tempCount = table.rows.length * 2;
				
				   cell.innerHTML = getCompanyValue(tempCount);//js定义			   
				      
				   $.parser.parse(cell);
				   changePurposeOrBusiness();//调用切换	
				   
				   var temp1 = "lenderInfor.province_"+tempCount+"y";
				   var temp2 = "lenderInfor.city_"+tempCount+"y";
				   var temp3 = "lenderInfor.area_"+tempCount+"y";
				   	
				   addressInit(temp1, temp2, temp3);
			}	
		   
		}
		
		//删除借款人		
		function deleteLenderInfoTableRow() { 
			
			//借款人类型
			var loanType = document.getElementById('loanContract.loanType');
			if(loanType.value == '自然人'){
				var table = document.getElementById("personTable");			
				var index = table.rows.length - 1;			
				
				if (index == 0) {  
					alert("不能删除!");
				  	return; 
				}
							
				if(confirm("确定删除?")) {  
					table.deleteRow(index); 
				}	
			}else if(loanType.value == '企业'){
				var table = document.getElementById("companyTable");			
				var index = table.rows.length - 1;			
				
				if (index == 0) {  
					alert("不能删除!");
				  	return; 
				}
							
				if(confirm("确定删除?")) {  
					table.deleteRow(index); 
				}	
				
			}
			
			
		}
		
		//隐藏或显示借款人信息
		function controlHideOrShow(div, value) {
			//alert('');
			
			//alert(div);
			var table = document.getElementById("lenderInfoTable");
		   	var tempCount = table.rows.length * 2;
		  	//alert(tempCount);			
			for (var i = 1; i <= tempCount; i++) {
				//alert(div+i);
				document.getElementById(div+i).style.display = value;
			}
		}  		
		
		//增加抵押物
		function insertDyRow(){						      		
		   var content = " <div class='clearfix'><span class='set_font_width'>名称</span><input name='mortgageContract.name' class='inputbox set_width_03' type='text'/>"+
					     " <span class='set_font_width'>数量及单位</span><input name='mortgageContract.unit' class='inputbox set_width_02' type='text'/>"+
					     " </div><div class='clearfix'><span class='set_font_width'>存放地点</span><input name='mortgageContract.address' class='inputbox set_width_03' type='text'/>"+
					     " <span class='set_font_width'>评估价值(元)</span><input name='mortgageContract.worth' class='inputbox set_width_02' style='float:left;' type='text'/>" +
					     "</div><div><span class='set_font_width'>抵押物类型</span><select class='inputbox set_width_02' style='float:left; width:85px;' name='mortgageContract.dzywType' ><option value='1'>存货抵押</option><option value='2'>客账抵押</option><option value='3'>证券抵押</option><option value='4'>设备抵押</option><option value='5'>不动产抵押</option><option value='6'>人寿险抵押</option><option value='7'>其他</option></select>" +
						 
					     "</div>";
		  
		   var table = document.getElementById("dyTable");
		   var newRow = table.insertRow(table.rows.length);
		   
		   var cell = newRow.insertCell(0);		  
		   cell.innerHTML = content;		    		  
		}
		
		//删除抵押物
		function deleteDyRow() { 
			var table = document.getElementById("dyTable");			
			var index = table.rows.length - 1;
			
			if (index == 0) {  
				alert("不能删除!");
			  	return; 
			}
						
			if(confirm("确定删除?")) {  
				table.deleteRow(index); 
			}
		}
		
		//增加质押物
		function insertZyRow(){						      		
		   var content = "<div class='clearfix'><span class='set_font_width'>名称</span><input name='zhiyaContract.name' class='inputbox set_width_03' type='text'/>"+
					     " <span class='set_font_width'>数量及单位</span><input name='zhiyaContract.unit' class='inputbox set_width_02' type='text'/>"+
					     " </div><div class='clearfix'><span class='set_font_width'>存放地点</span><input name='zhiyaContract.address' class='inputbox set_width_03' type='text'/>"+
					     " <span class='set_font_width'>评估价值(元)</span><input name='zhiyaContract.worth' class='inputbox set_width_02' style='float:left;' type='text'/>"+
					     "</div><div><span class='set_font_width'>质押物类型</span><select class='inputbox set_width_02' style='float:left; width:85px;' name='zhiyaContract.dzywType' ><option value='1'>股权质押</option><option value='2'>定期存单质押</option><option value='3'>专利权质押</option><option value='4'>应收账款质押</option><option value='5'>其他</option></select>" +
					     "" +
					     "</div>";
		  
		   var table = document.getElementById("zyTable");
		   var newRow = table.insertRow(table.rows.length);
		   
		   var cell = newRow.insertCell(0);		  
		   cell.innerHTML = content;		    		  
		}
		
		//删除质押物
		function deleteZyRow() { 
			var table = document.getElementById("zyTable");			
			var index = table.rows.length - 1;
			
			if (index == 0) {  
				alert("不能删除!");
			  	return; 
			}
						
			if(confirm("确定删除?")) {  
				table.deleteRow(index); 
			}
		}
		
		//增加保证人
		function insertBzRow(){					      		
		   var content = "<div class='clearfix'><span class='set_font_width'>保证人</span><input name='ensureContract.name' class='inputbox set_width_02' style='float:left;' type='text'/>"+
					     " <span class='set_font_width'>联系地址</span><input name='ensureContract.address' class='inputbox set_width_03' type='text'/>"+
					     " </div><div class='clearfix'><span class='set_font_width'>证件种类</span><select class='inputbox set_width_02' style='float:left; width:85px;' name='ensureContract.category' ><option value='身份证'>身份证</option><option value='港澳通行证'>港澳通行证</option><option value='护照'>护照</option><option value='营业执照'>营业执照</option></select>"+
		  				" <span class='set_font_width'>证件号码</span><input name='ensureContract.cardNum' class='inputbox set_width_03' type='text'/></div>";
		   var table = document.getElementById("bzTable");
		   var newRow = table.insertRow(table.rows.length);
		   
		   var cell = newRow.insertCell(0);		  
		   cell.innerHTML = content;		    		  
		}
		
		//删除保证人
		function deleteBzRow() { 
			var table = document.getElementById("bzTable");			
			var index = table.rows.length - 1;
			
			if (index == 0) {  
				alert("不能删除!");
			  	return; 
			}
						
			if(confirm("确定删除?")) {  
				table.deleteRow(index); 
			}
		}
		
		//动态控制还款方式
	    function controlHuanMethod(temp) {	
		    var slt = document.getElementById('loanContract.huanMethod');
		    if (slt == null) {
		    	return;
		    }
		   
		    slt.options.length = 0;//清空
		    slt[0] = new Option('请选择还款方式', 0);//默认项		
			//1）等额本息  2）等额本金  3）先息后本   4）灵活还款						
		    var array = ['等额本息','等额本金','先息后本','灵活还款'];  
	        for (var i = 0; i < array.length; i++) {
	         	if (array[temp-1] == array[i]) {
	         		slt[i + 1] = new Option(array[i], i+1, true, true);
	         	} else {
	         		slt[i + 1] = new Option(array[i],i+1);
	         	}
	      	}
		}	
		
		

		//日期的加减
	    function addDate(date,years,months,days,interestDay){ 
	    	//alert(date);
		    date = date.replace(/-/g,"/"); //更改日期格式
		    var cdate;
		    var nd = new Date(date);
		    //alert("m  : "  +nd.getMonth() );
		    //alert("1 nd : " + nd.format("yyyy-MM-dd"));
		    if(years != 0){
		    	nd.setFullYear(nd.getFullYear() + parseInt(years));
		    }
		   // alert(" nd : " + nd.format("yyyy-MM-dd"));
		    var idAddDay = false;
		    if(months != 0){		    	
		    	var arr = date.split("/");
		    	var year = arr[0]; //获取当前日期的年份
	            var month = arr[1]; //获取当前日期的月份
	            var day = arr[2]; //获取当前日期的日
	            //alert("year:" + year + "  month : " + month + "  day:"  +day );
	            //当前月 的月底日
	            var tempDate = new Date(year, month, 0);
	            var yueDiDay = tempDate.getDate();
	            
	            var month2 = parseInt(month) + parseInt(months);
	            
	          // alert("month2:  "  +month2);
	            var toMonth = 0;
	            if (month2 > 12) {
	            	toMonth = month2%12;
	            }
	           // alert("toMonth :  " + toMonth);
	            var toMonthToAddYear;
	            //如果超过1年以上
	            if (toMonth > 0) {
	            	toMonthToAddYear = (month2 -toMonth) /12;
	            }	           
	            if (toMonthToAddYear > 0) {	            	
	            	year = parseInt(year) + toMonthToAddYear;
	            }
	            //如果 相等 说明是月底
	            /*if (parseInt(day) == parseInt(yueDiDay)) {	
	            	if (toMonth > 0) {
	            		nd = new Date(year, toMonth, 0); 
	            	} else {
	            		nd = new Date(year, month2, 0); 
	            	}	            	
	            } else {	         	
	            	
	            }
		    	*/   
	            
			    if (toMonth > 0) {
	        		//alert("toMonth > 0");
	        		if (parseInt(day) > 28) {
	        			
	        			//alert("tempVal : "  +tempVal);
	        			nd = new Date(year, toMonth, 0); 
	        			var tempVal2 = nd.getDate();
	        			if (tempVal2 >= day) {
	        				nd.setDate(day);
	        			} else {
	        				idAddDay = true;
	        				nd.setDate(tempVal2);
	        			}
	        			/*var tempVal = yueDiDay - parseInt(day);
	        			 * var tempVal2 = nd.getDate() - tempVal;
	        			//alert("tempVal2 : "  +tempVal2);
	        			nd.setDate(tempVal2);*/
	        		} else {
	        			nd = new Date(year, toMonth, 0); 
	        			nd.setDate(day);
	        		}
	        		
	        	} else {	            		
	        		if (parseInt(day) > 28) {
	        			
	        			nd = new Date(year, month2, 0); 
	        			var tempVal2 = nd.getDate();
	        			if (tempVal2 >= day) {
	        				nd.setDate(day);
	        			} else {
	        				idAddDay = true;
	        				nd.setDate(tempVal2);
	        			}
	        			//alert(" nd : " + nd.format("yyyy-MM-dd"));
	        			
	        			/*var tempVal = yueDiDay - parseInt(day);
	        			var tempVal2 = nd.getDate() - tempVal;
	        			nd.setDate(tempVal2);*/
	        			//nd = new Date(year, month2, tempVal2); 
	        			//alert(" nd : " + nd.format("yyyy-MM-dd"));
	        		} else {
	        			nd = new Date(year, month2, 0); 
	        			nd.setDate(day);
	        		}
	        	}	
		       
		    }
		   // alert(" nd : " + nd.format("yyyy-MM-dd"));
		    if(days != 0){
		    	/*nd = nd.valueOf();
			    nd = nd + days * 24 * 60 * 60 * 1000;
			    nd = new Date(nd);*/
		    	if (idAddDay) {
		    		nd.setDate(nd.getDate()+parseInt(days) + 1);
		    	} else {
		    		//alert("nd.getDate()+parseInt(days) : " + (nd.getDate()+parseInt(days)));
		    		nd.setDate(nd.getDate()+parseInt(days));
		    	}
		    	
		    }

		    var y = nd.getFullYear();
		    var m = nd.getMonth()+1;
		   // var m = nd.getMonth();//前面 已经修改结构 这里不用加1 
		    var d = nd.getDate();
		   // alert("d : "  +d );
		    
		    if(m <= 9) m = "0"+m;
		    if(d <= 9) d = "0"+d;
		    if(interestDay != 0){
		    	//alert("interestDay:" + interestDay);
		    	cdate = y + "-" + m + "-" + interestDay;
		    }else {
		    	cdate = y + "-" + m + "-" + d;
		    }
		   //alert("cdate : "  +cdate);
		    return cdate;
	    } 
	    
	    
		//动态切换还款方式设置
		function changeHuanMethod(value) {
			//'等额本金','等额本息','按月付息，到期还本','利随本清','其他'
			//还款方式： 1）等额本息  2）等额本金  3）先息后本   4）灵活还款
			if (value == '2') {
				document.getElementById("huanMethodTool").style.display = '';
				document.getElementById("loanContract.benjinDate").style.display = 'none';
				document.getElementById("benjinDateText").style.display = 'none';
				document.getElementById("dengebenjin").style.display = '';
				document.getElementById("dengebenxi").style.display = 'none';
				document.getElementById("huanxiben").style.display = 'none';
			} else if (value == '1') {
				document.getElementById("huanMethodTool").style.display = '';
				document.getElementById("loanContract.benjinDate").style.display = 'none';
				document.getElementById("benjinDateText").style.display = 'none';
				document.getElementById("dengebenjin").style.display = 'none';
				document.getElementById("dengebenxi").style.display = '';
				document.getElementById("huanxiben").style.display = 'none';
			} else if (value == '3') {
				document.getElementById("huanMethodTool").style.display = '';
				document.getElementById("loanContract.benjinDate").style.display = '';
				document.getElementById("benjinDateText").style.display = '';
				document.getElementById("dengebenjin").style.display = 'none';
				document.getElementById("dengebenxi").style.display = 'none';
				document.getElementById("huanxiben").style.display = '';
			} else {
				var tempHM = document.getElementById("huanMethodTool")
				if (tempHM != null) {
					tempHM.style.display = 'none';
				}
				
			}
		}	
		
		//清空还款计划
		function clearRepayCount() {
		   if(confirm("生成还款计划会清空原有显示的还款计划，确认清空还款计划？")) {
	           var table = document.getElementById("repayPlanTable");
	           var deleteRowcCount = table.rows.length;
	           //先清空表格
	           for (var i = deleteRowcCount - 1 ; i > 0; i--) {
	           		table.deleteRow(i); 
	           }   
				   
			  /* var content = " 截至日期<input name='repayCount.endDate' readonly='readonly' type='text' class='Wdate' onClick='WdatePicker()' value=''/>"+
						     " 还款本金<input name='repayCount.money' class='inputbox set_width_02' type='text' value=''/>元"+
						     " 还款利息<input name='repayCount.lixi' class='inputbox set_width_02' type='text' value=''/>元"+
						     " <a name='indexDelete' href='javascript:deleteRow(0)' />删除</a>"+
						     " <a name='indexInsert' href='javascript:insertRow(0)' />增加</a>";
			 
			   var newRow = table.insertRow(table.rows.length);
			   
			   var cell = newRow.insertCell(0);		  
			   cell.innerHTML = content;*/
		   }	
		}	
		
		//动态控制约定还本付息日
	    function controlFuxiri(temp) {
		    var slt = document.getElementById('loanContract.fuxiri');
		    if (slt == null) {
		    	return;
		    }
		    slt.options.length = 0;//清空	
		    var dd;	  
	        for (var i = 0; i < 31; i++) {
	       		
	            if (i < 9) {
	            	dd = "0" + (i + 1);
	            } else {
	            	dd = i + 1;
	            }
	         	if (temp == dd) {
	         		slt[i] = new Option(dd, dd, true, true);
	         	} else {
	         		slt[i] = new Option(dd, dd);
	         	}
	      	}
		}	
		
		
		
		//动态生成 等额本金 还款计划期数
		function insertDengebenjinRow(){
			clearRepayCount();
			//var count = 1;
		   var count = $("input[name='term']").val();//贷款期限(总期数)
		   var termType = $("input[name='termType']:checked").val();//贷款期限类型
		   
		   var daiDate = $("input[name='startDate']").val();//贷款开始日期		   
		   if (daiDate == '') {
		   		alert('先输入贷款开始日期');
		   		return ;
		   } 
		   
		   var money = $("input[name='money']").val();
		   if (money == '') {
		   		alert('先输入贷款金额');
		   		return ;
		   } else if (isNaN(money)) {
		   		alert('输入正确的贷款金额');
		   		return ;
		   }
		   
		   var rate = $("input[name='rate']").val();
		   if (rate == '') {
		   		alert('先输入贷款利率');
		   		return ;
		   } else if (isNaN(rate)) {
		   		alert('输入正确的贷款利率');
		   		return ;
		   }
		   
		  // var monthMoney = (money/count).toFixed(2);//总本金/还款期数\
		   var monthMoney = (money/count);//总本金/还款期数
		   
		  // var monthRate = (rate/(12*100)).toFixed(4);//月利率	
		   //年
		   var monthRate= 0;
		   var rateType = termType;//每期还款日类型
		   if(rateType == "1"){
			   monthRate = (rate*30/100);//日利率
		   } else if(rateType == "2"){
			   monthRate = (rate*4/100);//周
		   }else if(rateType == "3"){
			   monthRate = rate/100;//月利率
		   }else if(rateType == "4"){
			   monthRate = (rate/(3*100));//季
		   }else if(rateType == "5"){
			   monthRate = (rate/(12*100));//年利率
		   }
		  
		   
		   var fuxiri = $("select[name='fuxiri'] option:selected").val();//还本付息日
		 
           var tempEndDate = new Date(daiDate); 
           //alert(tempEndDate);
           var tempEndDateDay = tempEndDate.format("dd");          
           if (Number(tempEndDateDay) > Number(fuxiri)) {
           		tempEndDate.setMonth(tempEndDate.getMonth()+1);  
           } 
           var formatTempEndDate;            
             
           var table = document.getElementById("repayPlanTable");
           var deleteRowcCount = table.rows.length;
           //先清空表格
           for (var i = deleteRowcCount - 1 ; i > 0; i--) {
           		table.deleteRow(i); 
           }
          
          // alert(tempEndDate.format("yyyy-MM-dd"));//贷款期限类型：1）月 2）日
           for (var i = 0; i < count; i++) {
        	   tempEndDate = tempEndDate.format("yyyy-MM-dd");
    		   if(termType == "1"){		
    			   //alert("日");
    			   tempEndDate = addDate(tempEndDate,0,0,1,0);
    		   } else if(termType == "2"){
    			   //alert("周");
   	    			tempEndDate = addDate(tempEndDate,0,0,7,0);	    			
    	    	}else if(termType == "3"){
    	    		// alert("月");
     			   tempEndDate = addDate(tempEndDate,0,1,0,0);
    	    	}else if(termType == "4"){
    	    		//alert("季");
    	    		tempEndDate = addDate(tempEndDate,0,3,0,fuxiri);
    	    	}else if(termType == "5"){
    	    		//alert("年");
    	    		tempEndDate = addDate(tempEndDate,1,0,0,fuxiri);
    	    	}
    		   
    		   
           	  /* if (i > 0) {           		    
           	   		tempEndDate.setMonth(tempEndDate.getMonth()+1);   
           	   } */          	              	 
              //2月份强制设置28号
           		//alert(tempEndDate.getMonth());
    		   tempEndDate = new Date(tempEndDate); 
    		   ///alert(tempEndDate.format("yyyy-MM-dd"));
    		 // alert(tempEndDate.format("yyyy-MM-dd"));
           	   if (tempEndDate.getMonth() == '1') {  
           	   		//alert("if:" + Number(fuxiri));         	   		
           	   		if (Number(fuxiri) > 28) {
           	   			//alert('');
           	   			//tempEndDate = new Date(tempEndDate.format("yyyy-MM") + "-" + "28");
           	   			tempEndDate.setDate("28");
           	   			//alert(tempEndDate);
           	   			formatTempEndDate = tempEndDate.format("yyyy-MM-dd"); 
           	   		} else {
	           	   		if(termType == "1"){
	 	     			   //alert("日");
	 	           			formatTempEndDate = tempEndDate.format("yyyy-MM-dd"); 
	 	     		   } else if(termType == "2"){
	 	     			   //alert("周");
	 	     	    		formatTempEndDate = tempEndDate.format("yyyy-MM-dd"); 	
	 	     	    	}else if(termType == "3"){
	 	     	    		// alert("月");
		 	     			formatTempEndDate = tempEndDate.format("yyyy-MM") + "-" + fuxiri;
	 	     	    	}else if(termType == "4"){
	 	     	    		//alert("季");
	 	     	    		formatTempEndDate = tempEndDate.format("yyyy-MM") + "-" + fuxiri; 
	 	     	    	}else if(termType == "5"){
	 	     	    		//alert("年");
	 	     	    		formatTempEndDate = tempEndDate.format("yyyy-MM") + "-" + fuxiri; 
	 	     	    	}
           	   		}           	   		
           	   		
           	   } else {
           		   if(termType == "1"){		
	     			   //alert("日");
	           			formatTempEndDate = tempEndDate.format("yyyy-MM-dd"); 
	     		   } else if(termType == "2"){
	     			   //alert("周");
	     			   formatTempEndDate = tempEndDate.format("yyyy-MM-dd"); 
	     	    	}else if(termType == "3"){
	     	    		// alert("月");
	     	    		formatTempEndDate = tempEndDate.format("yyyy-MM") + "-" + fuxiri; 	
	     	    	}else if(termType == "4"){
	     	    		//alert("季");
	     	    		formatTempEndDate = tempEndDate.format("yyyy-MM") + "-" + fuxiri; 
	     	    	}else if(termType == "5"){
	     	    		//alert("年");
	     	    		formatTempEndDate = tempEndDate.format("yyyy-MM") + "-" + fuxiri; 
	     	    	}
           	   }
               
               money = i == 0 ? money : money - monthMoney;   
               //alert("benjin:"+money+" lilv:"+monthRate);
               var monthLixi = (money*monthRate).toFixed(2);//每月利息=(本金-累计已还本金)×月利率         	   
			   
			   var content =
                   "<td ><input name='repayPlans["+i+"].endDate' type='text' class='dateIcon form_datetime' value='"+formatTempEndDate+"'></td>"+
                   "<td ><input name='repayPlans["+i+"].money' type='text' value='"+parseFloat(monthMoney).toFixed(2)+"'></td>"+
                   "<td ><input name='repayPlans["+i+"].interest' type='text' value='"+parseFloat(monthLixi).toFixed(2)+"'></td>"+
                   "<td><a class='del' href='javascript:;'>删除</a></td>";
			   var $tbody = $('.new_repay');
               var $tr = $('<tr></tr>');
               $tr.get(0).innerHTML = content;
               $tbody.append($tr);
		   }  		  
		}
		
		
		//动态生成 等额本息 还款计划期数
		function insertDengebenxiRow(){
			clearRepayCount();
			var count = $("input[name='term']").val();//贷款期限(总期数)
		   var termType = $("input[name='termType']:checked").val();//贷款期限类型
		   var daiDate = $("input[name='startDate']").val();//贷款开始日期
		   if (daiDate == '') {
		   		alert('先输入贷款开始日期');
		   		return ;
		   } 
		   
		   var money = $("input[name='money']").val();
		   
		   //alert(money);
		   if (money == '') {
		   		alert('先输入贷款金额');
		   		return ;
		   } else if (isNaN(money)) {
		   		alert('输入正确的贷款金额');
		   		return ;
		   }
		   
		   var rate = $("input[name='rate']").val();
		   //alert(rate);
		   if (rate == '') {
		   		alert('先输入贷款利率');
		   		return ;
		   } else if (isNaN(rate)) {
		   		alert('输入正确的贷款利率');
		   		return ;
		   }
		   
		  /// var monthRate = (rate/(12*100)).toFixed(4);//月利率	
		   //年
		   var monthRate= 0;
		   var rateType = $("input[name='termType']:checked").val();//贷款期限类型
		   if(rateType == "1"){
			   monthRate = (rate*30/100);//日利率
		   } else if(rateType == "2"){
			   monthRate = (rate*4/100);//周
		   }else if(rateType == "3"){
			   monthRate = rate/100;//月利率
		   }else if(rateType == "4"){
			   monthRate = (rate/(3*100));//季
		   }else if(rateType == "5"){
			   monthRate = (rate/(12*100));//年利率
		   }
		   //alert(monthRate);
		 
		   var fuxiri = $("select[name='fuxiri'] option:selected").val();//还本付息日
		 
           var tempEndDate = new Date(daiDate); 
           var tempEndDateDay = tempEndDate.format("dd");          
           if (Number(tempEndDateDay) > Number(fuxiri)) {
           		tempEndDate.setMonth(tempEndDate.getMonth()+1);  
           }
            
           var formatTempEndDate;            
             
           var table = document.getElementById("repayPlanTable");
           var deleteRowcCount = table.rows.length;
           //先清空表格
           for (var i = deleteRowcCount - 1 ; i > 0; i--) {
           		table.deleteRow(i); 
           }
           
           //每月应还金额：a*[i*(1+i)^n]/[(1+i)^n-1] （注：a：贷款本金 ，i：贷款月利率， n：贷款月数 ） 
           //alert("money:" + money + " monthRate:" + monthRate + " count:" + count);
           var shang = monthRate*Math.pow(1+Number(monthRate), count);
           var xia = Math.pow(1+Number(monthRate), count)-1;
          	//alert("shang:"+shang + " xia:"+xia);
		   var monthJine = (money*(shang/xia)).toFixed(2);//每月应还金额
		    //var monthJine = (money*(shang/xia));//每月应还金额
		    if (isNaN(monthJine)) {
		    	monthJine = 0;
		    }
		   //alert("monthJine:" + monthJine);
		   var monthMoney=0 ;//每月本金
           //var money=0 ;//剩余本金  
           var monthLixi=0;//每月利息 
         //贷款期限类型：1）月 2）日
           for (var i = 0; i < count; i++) {
        	   tempEndDate = tempEndDate.format("yyyy-MM-dd");
    		   if(termType == "1"){
    			   tempEndDate = addDate(tempEndDate,0,0,1,0);
    		   } else if(termType == "2"){
    			   //alert("周");
   	    		   tempEndDate = addDate(tempEndDate,0,0,7,0);
    	    	}else if(termType == "3"){
    	    		tempEndDate = addDate(tempEndDate,0,1,0,0);
    	    	}else if(termType == "4"){
    	    		//alert("季");
    	    		tempEndDate = addDate(tempEndDate,0,3,0,fuxiri);
    	    	}else if(termType == "5"){
    	    		//alert("年");
    	    		tempEndDate = addDate(tempEndDate,1,0,0,fuxiri);
    	    	}
           	  /* if (i > 0) {           		    
           	   		tempEndDate.setMonth(tempEndDate.getMonth()+1);   
           	   } */          	              	 
              //2月份强制设置28号
           		//alert(tempEndDate.getMonth());
    		   tempEndDate = new Date(tempEndDate); 
           	   
           	   //2月份强制设置28号
           		//alert(tempEndDate.getMonth());
           	   if (tempEndDate.getMonth() == '1') {
           	   		//alert(Number(fuxiri));         	   		
           	   		if (Number(fuxiri) > 28) {
           	   			//alert('');
           	   			//tempEndDate = new Date(tempEndDate.format("yyyy-MM") + "-" + "28");
           	   			tempEndDate.setDate("28");
           	   			//alert(tempEndDate);
           	   			formatTempEndDate = tempEndDate.format("yyyy-MM-dd"); 
           	   		} else {
	           	   		if(termType == "1"){		
		 	     			   //alert("日");
		 	           			formatTempEndDate = tempEndDate.format("yyyy-MM-dd"); 
		 	     		   } else if(termType == "2"){
		 	     			   //alert("周");
		 	     			   formatTempEndDate = tempEndDate.format("yyyy-MM-dd"); 
		 	     	    	}else if(termType == "3"){
		 	     	    		// alert("月");
			 	     			formatTempEndDate = tempEndDate.format("yyyy-MM") + "-" + fuxiri; 	
		 	     	    	}else if(termType == "4"){
		 	     	    		//alert("季");
		 	     	    		formatTempEndDate = tempEndDate.format("yyyy-MM") + "-" + fuxiri; 
		 	     	    	}else if(termType == "5"){
		 	     	    		//alert("年");
		 	     	    		formatTempEndDate = tempEndDate.format("yyyy-MM") + "-" + fuxiri; 
		 	     	    	}
           	   		} 
           	   } else {
	           		if(termType == "1"){		
		     			   //alert("日");
		           			formatTempEndDate = tempEndDate.format("yyyy-MM-dd"); 
		     		   } else if(termType == "2"){
		     			   //alert("周");
		     			   formatTempEndDate = tempEndDate.format("yyyy-MM-dd"); 
		     	    	}else if(termType == "3"){
		     	    		// alert("月");
		     	    		formatTempEndDate = tempEndDate.format("yyyy-MM") + "-" + fuxiri; 	
		     	    	}else if(termType == "4"){
		     	    		//alert("季");
		     	    		formatTempEndDate = tempEndDate.format("yyyy-MM") + "-" + fuxiri; 
		     	    	}else if(termType == "5"){
		     	    		//alert("年");
		     	    		formatTempEndDate = tempEndDate.format("yyyy-MM") + "-" + fuxiri; 
		     	    	}
           	   }     	              	 
                             
               if (i > 0) {
	               money = money - monthMoney;//剩余本金  
	               monthLixi = (money*monthRate).toFixed(2);//每月利息 = 剩余本金x贷款月利率
	               monthMoney = (monthJine - monthLixi).toFixed(2);//每月本金
               } else {
	               money = money;//剩余本金  
	               monthLixi = (money*monthRate).toFixed(2);//每月利息 = 剩余本金x贷款月利率
	               monthMoney = (monthJine - monthLixi).toFixed(2);//每月本金
               }
               
               var content =
                   "<td ><input name='repayPlans["+i+"].endDate' type='text' class='dateIcon form_datetime' value='"+formatTempEndDate+"'></td>"+
                   "<td ><input name='repayPlans["+i+"].money' type='text' value='"+parseFloat(monthMoney).toFixed(2)+"'></td>"+
                   "<td ><input name='repayPlans["+i+"].interest' type='text' value='"+parseFloat(monthLixi).toFixed(2)+"'></td>"+
                   "<td><a class='del' href='javascript:;'>删除</a></td>";
               
               var $tbody = $('.new_repay');
               var $tr = $('<tr></tr>');
               $tr.get(0).innerHTML = content;
               $tbody.append($tr);
		   }  		  
		}
		
		//动态生成 先息后本  按月付息，到期还本 还款计划期数
		function insertXianXiHouBenRow(){
		  
		   var count = $("input[name='term']").val();//贷款期限(总期数)
		   var termType = $("input[name='termType']:checked").val();//贷款期限类型
		   var daiDate = $("input[name='startDate']").val();//贷款开始日期
		   if (daiDate == '') {
		   		alert('先输入贷款日期');
		   		return ;
		   } 
		   
		   var money = $("input[name='money']").val();		   
		   if (money == '') {
		   		alert('先输入贷款金额');
		   		return ;
		   } else if (isNaN(money)) {
		   		alert('输入正确的贷款金额');
		   		return ;
		   }
		   
		   var rate = $("input[name='rate']").val();
		   if (rate == '') {
		   		alert('先输入贷款利率');
		   		return ;
		   } else if (isNaN(rate)) {
		   		alert('输入正确的贷款利率');
		   		return ;
		   }
		   
		   //var monthRate = (rate/(12*100)).toFixed(4);//月利率
		   //年
		   var monthRate= 0;
		   var rateType = termType;//每期还款日类型
		   if(rateType == "1"){
			   monthRate = (rate*30/100);//日利率
		   } else if(rateType == "2"){
			   monthRate = (rate*4/100);//周
		   }else if(rateType == "3"){
			   monthRate = rate/100;//月利率
		   }else if(rateType == "4"){
			   monthRate = (rate/(3*100));//季
		   }else if(rateType == "5"){
			   monthRate = (rate/(12*100));//年利率
		   }
		   //var monthLixi = (money*monthRate).toFixed(2);//月利息
		   var monthLixi = (money*monthRate);//月利息
		   //alert();
		   
		   /*var benjinDate = document.getElementById('loanContract.benjinDate').value;
		   //alert(benjinDate);
		   if (benjinDate == '') {
		   		alert('先输入还本金日期');
		   		return ;
		   }
		   
		   var parseBenjinDateDD = new Date(benjinDate).format("dd");*/
		   var fuxiri = $("select[name='fuxiri'] option:selected").val();//付息日
		   
		   var isUnion = true;
		   /*var isUnion = false;		 
		   if (fuxiri == parseBenjinDateDD) {
		   		isUnion = true;
		   } else {
		   		count ++;
		   }*/    
         
           var tempEndDate = new Date(daiDate);  
           var tempEndDateDay = tempEndDate.format("dd");        
           //var aa  =   Number(tempEndDateDay) > Number(fuxiri);
          // alert(aa);
           if (Number(tempEndDateDay) > Number(fuxiri)) {
           		//tempEndDate.setMonth(tempEndDate.getMonth()+1);  
           }
           
           var formatTempEndDate;           
           var tempMoney = 0;
       
           var table = document.getElementById("repayPlanTable");
           var deleteRowcCount = table.rows.length;
         
           //先清空表格
           for (var i = deleteRowcCount - 1 ; i > 0; i--) {
           		//alert(i);
           		table.deleteRow(i); 
           }
           
           for (var i = 0; i < count; i++) {
           	   /*if (i > 0) {
           	   		tempEndDate.setMonth(tempEndDate.getMonth()+1);   
           	   }*/
           	   
           	 tempEndDate = tempEndDate.format("yyyy-MM-dd");
           //贷款期限类型：1）月 2）日
           	if(termType == "1"){		
 			   //alert("日");
 			   tempEndDate = addDate(tempEndDate,0,0,1,0);
 		   } else if(termType == "2"){
 			   //alert("周");
	    			tempEndDate = addDate(tempEndDate,0,0,7,0);	    			
 	    	}else if(termType == "3"){
 	    		// alert("月");
  			   tempEndDate = addDate(tempEndDate,0,1,0,0);
 	    	}else if(termType == "4"){
 	    		//alert("季");
 	    		tempEndDate = addDate(tempEndDate,0,3,0,fuxiri);
 	    	}else if(termType == "5"){
 	    		//alert("年");
 	    		tempEndDate = addDate(tempEndDate,1,0,0,fuxiri);
 	    	}
         	  /* if (i > 0) {           		    
         	   		tempEndDate.setMonth(tempEndDate.getMonth()+1);   
         	   } */          	              	 
            //2月份强制设置28号
         		//alert(tempEndDate.getMonth());
  		   tempEndDate = new Date(tempEndDate); 
           	   
           	   if (isUnion == false && i == count-1) {     
           	   		//alert(benjinDate);      	   		
               		//formatTempEndDate = new Date(benjinDate).format("yyyy-MM-dd");
               		formatTempEndDate = benjinDate;
               		//alert(formatTempEndDate);
               } else {
               		//alert(tempEndDate.getMonth());
               		if (tempEndDate.getMonth() == '1') {  
	           	   		if (Number(fuxiri) > 28) {
	           	   			//formatTempEndDate = new Date(benjinDate.format("yyyy-MM") + "-" + "28");
	           	   			tempEndDate.setDate("28");
	           	   			//alert(formatTempEndDate);
	           	   			formatTempEndDate = tempEndDate.format("yyyy-MM-dd");
	           	   		} else {
	           	   			if(termType == "1"){
		 	     			   //alert("日");
		 	           			formatTempEndDate = tempEndDate.format("yyyy-MM-dd"); 
		 	     		   } else if(termType == "2"){
		 	     			   //alert("周");
		 	     	    		formatTempEndDate = tempEndDate.format("yyyy-MM-dd"); 	
		 	     	    	}else if(termType == "3"){
		 	     	    		// alert("月");
			 	     			formatTempEndDate = tempEndDate.format("yyyy-MM") + "-" + fuxiri;
		 	     	    	}else if(termType == "4"){
		 	     	    		//alert("季");
		 	     	    		formatTempEndDate = tempEndDate.format("yyyy-MM") + "-" + fuxiri; 
		 	     	    	}else if(termType == "5"){
		 	     	    		//alert("年");
		 	     	    		formatTempEndDate = tempEndDate.format("yyyy-MM") + "-" + fuxiri; 
		 	     	    	}
	           	   			//alert(formatTempEndDate);
	           	   		}
	           	   		
	           	    } else {
	           	    	if(termType == "1"){		
	 	     			   //alert("日");
	 	           			formatTempEndDate = tempEndDate.format("yyyy-MM-dd"); 
	 	     		   } else if(termType == "2"){
	 	     			   //alert("周");
	 	     			   formatTempEndDate = tempEndDate.format("yyyy-MM-dd"); 
	 	     	    	}else if(termType == "3"){
	 	     	    		// alert("月");
	 	     	    		formatTempEndDate = tempEndDate.format("yyyy-MM") + "-" + fuxiri; 	
	 	     	    	}else if(termType == "4"){
	 	     	    		//alert("季");
	 	     	    		formatTempEndDate = tempEndDate.format("yyyy-MM") + "-" + fuxiri; 
	 	     	    	}else if(termType == "5"){
	 	     	    		//alert("年");
	 	     	    		formatTempEndDate = tempEndDate.format("yyyy-MM") + "-" + fuxiri; 
	 	     	    	}
	           	    	//alert(formatTempEndDate);
	           	    }
               }               
           	   
               if (isUnion == false && i == count-1) {
               		tempMoney = money;
               		monthLixi = 0;
               		tempEndDate = new Date(benjinDate);
               } else if (isUnion == true && i == count-1) {
               		tempMoney = money;//约定还本付息日跟还本金日期的日相同则合并最后一期的还息及本金
               }	  
			   
			   var content =
                   "<td ><input name='repayPlans["+i+"].endDate' type='text' class='dateIcon form_datetime' value='"+formatTempEndDate+"'></td>"+
                   "<td ><input name='repayPlans["+i+"].money' type='text' value='"+parseFloat(tempMoney).toFixed(2)+"'></td>"+
                   "<td ><input name='repayPlans["+i+"].interest' type='text' value='"+parseFloat(monthLixi).toFixed(2)+"'></td>"+
                   "<td><a class='del' href='javascript:;'>删除</a></td>";
			   var $tbody = $('.new_repay');
               var $tr = $('<tr></tr>');
               $tr.get(0).innerHTML = content;
               $tbody.append($tr);
		   }
		}	
		
		
		//增加实际收取咨询费
		function insertZixunMoneyRow(){						      		
		   var content = "<div><span class='set_font_width'>报备日期</span><input name='zixunMoney.signDate' readonly='readonly' class='inputbox set_width_03 Wdate' onClick='WdatePicker()' type='text' value='"+new Date().format("yyyy-MM-dd")+"'/>"+
					      "</div><div><span class='set_font_width'>收取日期</span><input name='zixunMoney.shouquDate'  readonly='readonly'  class='Wdate' onClick='WdatePicker()' style='float:left;'  type='text'/>"+
					      "<span class='set_font_width'>咨询费(万元)</span><input name='zixunMoney.money' class='inputbox set_width_02' type='text' /></div>";
		  
		   var table = document.getElementById("zixunMoneyTable");
		   var newRow = table.insertRow(table.rows.length);
		   
		   var cell = newRow.insertCell(0);		  
		   cell.innerHTML = content;		    		  
		}
		
		//删除实际收取咨询费
		function deleteZixunMoneyRow() { 
			var table = document.getElementById("zixunMoneyTable");			
			var index = table.rows.length - 1;
			
			if (index == 0) {  
				alert("不能删除!");
			  	return; 
			}
						
			if(confirm("确定删除?")) {  
				table.deleteRow(index); 
			}
		}
		
		//增加违约金
		function insertZixunWeiyueRow(){						      		
		   var content = "<div><span class='set_font_width'>报备日期</span><input name='zixunWeiyue.signDate' readonly='readonly' class='inputbox set_width_03 Wdate' type='text'  onClick='WdatePicker()' value='"+new Date().format("yyyy-MM-dd")+"'/>"+
					      "</div><div><span class='set_font_width'>收取日期</span><input name='zixunWeiyue.shouquDate'  readonly='readonly'  class='Wdate' onClick='WdatePicker()' style='float:left;'  type='text'/>"+
					      "<span class='set_font_width'>违约金(万元)</span><input name='zixunWeiyue.money' class='inputbox set_width_02' type='text' /></div>";
		  
		   var table = document.getElementById("zixunWeiyueTable");
		   var newRow = table.insertRow(table.rows.length);
		   
		   var cell = newRow.insertCell(0);		  
		   cell.innerHTML = content;		    		  
		}
		
		//删除违约金
		function deleteZixunWeiyueRow() { 
			var table = document.getElementById("zixunWeiyueTable");			
			var index = table.rows.length - 1;
			
			if (index == 0) {  
				alert("不能删除!");
			  	return; 
			}
						
			if(confirm("确定删除?")) {  
				table.deleteRow(index); 
			}
		}
		
		//增加违约金
		function insertWeiMoneyRow(){						      		
		   var content = "<div><span class='set_font_width'>收取日期</span><input name='weiMoney.shouquDate'  class='inputbox set_width_03 Wdate'  onClick='WdatePicker()' type='text' value=''/>"+
						     
						      	"</div><div><span class='set_font_width'>收费项</span><input name='weiMoney.sfxiang'  class='inputbox set_width_03'  value=''  style='float:left;'  type='text'/>"+
						      	"<span class='set_font_width'>费用(万元)</span><input name='weiMoney.money' class='inputbox set_width_02' type='text'  value=''/></div>";
		  
		   var table = document.getElementById('weiMoneyTable');
		   var newRow = table.insertRow(table.rows.length);
		   
		   var cell = newRow.insertCell(0);		  
		   cell.innerHTML = content;		    		  
		}
		
		//删除违约金
		function deleteWeiMoneyRow() { 
			var table = document.getElementById('weiMoneyTable');			
			var index = table.rows.length - 1;
			
			if (index == 0) {  
				alert('不能删除!');
			  	return; 
			}
						
			if(confirm('确定删除?')) {  
				table.deleteRow(index); 
			}
		}
		