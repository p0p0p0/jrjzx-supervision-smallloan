/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.controller
 * ExtendRepayPlanController.java
 * 
 * 2017年6月8日-下午5:38:42
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.jrjzx.supervision.smallloan.common.bean.EasyUIResult;
import cn.jrjzx.supervision.smallloan.entity.ExtendRepayPlan;
import cn.jrjzx.supervision.smallloan.service.ExtendRepayPlanService;

import com.github.pagehelper.PageInfo;

/**
 *
 * ExtendRepayPlanController
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月8日 下午5:38:42
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("extendRepayPlan")
public class ExtendRepayPlanController extends BaseController<ExtendRepayPlan,ExtendRepayPlanService>{


	
	@PostMapping(value="saveList")
	public void saveList(@RequestBody List<ExtendRepayPlan> list) throws Exception{
		this.getService().newExtendRepayPlans(list);
	}
	
	
	@PostMapping("add.html") 
	public ModelAndView toAdd(String contractId,String remainMoneySum) throws Exception{
		ModelAndView mv = new ModelAndView("extendAndRepay/add");
		mv.addObject("contractId", contractId);
		mv.addObject("remainMoneySum", remainMoneySum);
		return mv ;
	}
	
	@GetMapping("history/page") 
	public ResponseEntity<EasyUIResult> findHistoryByPage(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
			@RequestParam(value = "limit", defaultValue = "30") Integer rows,String[] sort,String[] order, ExtendRepayPlan plan) throws Exception{
		sort = new String[]{"end_date"};
		order = new String[]{"asc"};
		plan.setFlag(2);
		PageInfo<ExtendRepayPlan> pageInfo = baseService.queryListByPageAndOrder(plan, (offset/rows+1), rows,sort,order);
		 EasyUIResult easyUIResult = new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
		return ResponseEntity.ok(easyUIResult);
	}
	
	@GetMapping("page/{contractId}") 
	public ResponseEntity<EasyUIResult> findByPage(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
			@RequestParam(value = "limit", defaultValue = "30") Integer rows,String[] sort,String[] order, @PathVariable("contractId") Long contractId) throws Exception{
		//order by endDate asc
		sort = new String[]{"end_date"};
		order = new String[]{"asc"};
		ExtendRepayPlan con = new ExtendRepayPlan();
		con.setContractId(contractId);
		con.setFlag(1);
		PageInfo<ExtendRepayPlan> pageInfo = baseService.queryListByPageAndOrder(con, (offset/rows+1), rows,sort,order);
		 EasyUIResult easyUIResult = new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
		return ResponseEntity.ok(easyUIResult);
	}
	
	@GetMapping("/extendDates/{contractId}")
	public List<String> findExtendDates(@PathVariable("contractId") Long contractId){
		return this.getService().findExtendDates(contractId);
	}
	
	@GetMapping("moneySum/{contractId}")
	public Double findMoneySum(@PathVariable("contractId") Long contractId){
		return this.getService().findMoneySum(contractId);
	}
}
