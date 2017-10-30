/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.controller
 * IndexController.java
 * 
 * 2017年7月10日-下午2:22:54
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.controller;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tk.mybatis.mapper.entity.Example;
import cn.jrjzx.supervision.smallloan.common.bean.EasyUIResult;
import cn.jrjzx.supervision.smallloan.common.constant.Constant;
import cn.jrjzx.supervision.smallloan.common.util.RejoiceUtil;
import cn.jrjzx.supervision.smallloan.entity.AssetLoss;
import cn.jrjzx.supervision.smallloan.entity.LoanContract;
import cn.jrjzx.supervision.smallloan.entity.User;
import cn.jrjzx.supervision.smallloan.service.AssetLossService;
import cn.jrjzx.supervision.smallloan.service.LoanContractService;

import com.github.pagehelper.PageInfo;

/**
 *
 * IndexController
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年7月10日 下午2:22:54
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("index")
public class IndexController {
	
	@Autowired
	LoanContractService contractService;
	
	@Autowired
	AssetLossService assetService;
	
	@GetMapping("basicStatistics")
	public Map<String,Object> basicStatistics(HttpServletRequest request){
		User user = (User) request.getSession().getAttribute(Constant.SESSION_KEY);
		Map<String, Object> basicStatistics = contractService.basicStatistics(user.getCompanyId());
		
		if(basicStatistics == null){
			basicStatistics = new HashMap<String, Object>();
			basicStatistics.put("averageTerm", "0天");
			basicStatistics.put("moneySum", 0);
			basicStatistics.put("loanBalanceSum", 0);
			basicStatistics.put("loanInfoCount", 0);
			basicStatistics.put("averageRate", 0);
			
			return basicStatistics;
		}
		LoanContract countCon = new LoanContract();
		countCon.setIsDraft(0);
		countCon.setFlag(1);
		countCon.setCompanyId(user.getCompanyId().longValue());
		Integer contractCount = contractService.queryCount(countCon);
		//format
		
		//totalDays
		Double totalDays = (Double) basicStatistics.get("totalDays")/contractCount;
		long totalDaysLong = totalDays.longValue();
		Long[] yearMoneyDays = RejoiceUtil.getYearMonthDays(totalDaysLong);
		String averageTerm = (yearMoneyDays[0].compareTo(0L) <= 0 ?"":yearMoneyDays[0]+"年")+
							 (yearMoneyDays[1].compareTo(0L) <= 0 ?"":yearMoneyDays[1]+"个月")+
							 (yearMoneyDays[2].compareTo(0L) <= 0 ?"":yearMoneyDays[2]+"天");
		basicStatistics.put("averageTerm", averageTerm);
		
		//money
		basicStatistics.put("moneySum", RejoiceUtil.formateMoneyToStr(basicStatistics.get("moneySum")));
		basicStatistics.put("loanBalanceSum", RejoiceUtil.formateMoneyToStr(basicStatistics.get("loanBalanceSum")));
		return basicStatistics;
	}
	
	
	@GetMapping("todayRepayPlans") 
	public ResponseEntity<EasyUIResult> findByPage(HttpServletRequest request,@RequestParam(value = "offset", defaultValue = "0") Integer offset,
			@RequestParam(value = "limit", defaultValue = "30") Integer rows,String contractNumber,Integer isSettle) throws Exception{
		User user = (User) request.getSession().getAttribute(Constant.SESSION_KEY);
		PageInfo<Map<String,Object>> pageInfo = contractService.findRepayPlanAndExtendPlanAndContractList((offset/rows+1), rows,user.getCompanyId(),contractNumber,isSettle);
		 EasyUIResult easyUIResult = new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
		return ResponseEntity.ok(easyUIResult);
	}
	
	
	@GetMapping("five-degrees-asset")
	public Map<String,List<String>> fiveDegreesAsset(HttpServletRequest request){
		//query last six months
		//startMonth and endMonth
		DateTime currentTime = new DateTime();
		String endMonth = currentTime.minusMonths(1).toString("yyyy-MM");
		String startMonth = currentTime.minusMonths(6).toString("yyyy-MM");
		//query
		Integer companyId = ((User)request.getSession().getAttribute(Constant.SESSION_KEY)).getCompanyId();
		Example example = new Example(AssetLoss.class);
		example.createCriteria().andEqualTo("flag",1).andBetween("dateMonth",startMonth,endMonth)
		.andEqualTo("companyId", companyId);
		List<AssetLoss> assetLossList = assetService.getMapper().selectByExample(example);
		//return data
		Map<String,List<String>> data = new HashMap<String, List<String>>();
		Map<String,AssetLoss> allMonthsAssetLoss = new HashMap<String, AssetLoss>();
		for(AssetLoss assetLoss: assetLossList){
			allMonthsAssetLoss.put(assetLoss.getDateMonth(), assetLoss);
		}
		//each level's data
		List<String> normalList = new ArrayList<String>();
		List<String> followList = new ArrayList<String>();
		List<String> minorList = new ArrayList<String>();
		List<String> suspiciousList = new ArrayList<String>();
		List<String> lossList = new ArrayList<String>();
		//fill each level's data, if null then replace with 0
		String nextMonth = currentTime.minusMonths(1).toString("yyyy-MM");
		for(int i = 0; i < 6; i++){
			AssetLoss assetLoss = allMonthsAssetLoss.get(nextMonth);
			if(assetLoss != null){
				normalList.add(assetLoss.getNormalBalance());
				followList.add(assetLoss.getFollowBalance());
				minorList.add(assetLoss.getMinorBalance());
				suspiciousList.add(assetLoss.getSuspiciousBalance());
				lossList.add(assetLoss.getLossBalance());
			}else{
				normalList.add("0");
				followList.add("0");
				minorList.add("0");
				suspiciousList.add("0");
				lossList.add("0");
			}
			nextMonth = currentTime.minusMonths(i+2).toString("yyyy-MM");
		}
		Collections.reverse(normalList);
		Collections.reverse(followList);
		Collections.reverse(minorList);
		Collections.reverse(suspiciousList);
		Collections.reverse(lossList);
		data.put("normal", normalList);
		data.put("follow", followList);
		data.put("minor", minorList);
		data.put("suspicious", suspiciousList);
		data.put("loss", lossList);
		return data;
		
	}
}
