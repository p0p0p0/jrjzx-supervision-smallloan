/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.controller
 * ExtendRepayController.java
 * 
 * 2017年6月8日-下午5:38:42
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.jrjzx.supervision.smallloan.common.bean.EasyUIResult;
import cn.jrjzx.supervision.smallloan.entity.ExtendRepay;
import cn.jrjzx.supervision.smallloan.service.ExtendRepayService;

import com.github.pagehelper.PageInfo;

/**
 *
 * ExtendRepayController
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月8日 下午5:38:42
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("extendRepay")
public class ExtendRepayController extends BaseController<ExtendRepay,ExtendRepayService>{

	@PostMapping 
	public void save(@RequestBody ExtendRepay extendRepay) throws Exception{
		if(extendRepay.getMoney() <= 0){
			throw new RuntimeException("money must gt 0:"+extendRepay.getMoney());
		}
		if(extendRepay.getInterest() <=0){
			throw new RuntimeException("interest must gt 0:"+extendRepay.getInterest());
		}
		this.getService().addExtendRepay(extendRepay);
	}
	
	
	@PutMapping
	public void update(@RequestBody ExtendRepay extendRepay) throws Exception{
		if(extendRepay.getMoney() <= 0){
			throw new RuntimeException("money must gt 0:"+extendRepay.getMoney());
		}
		if(extendRepay.getInterest() <=0){
			throw new RuntimeException("interest must gt 0:"+extendRepay.getInterest());
		}
		this.getService().updateExtendRepay(extendRepay);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestBody ExtendRepay repay) throws Exception{
		this.getService().deleteExtendRepay(repay);
	}
	
	@GetMapping("history/page") 
	public ResponseEntity<EasyUIResult> findHistoryByPage(@RequestParam(value = "offset", defaultValue = "0") Integer offset,
			@RequestParam(value = "limit", defaultValue = "30") Integer rows,String[] sort,String[] order, ExtendRepay repay) throws Exception{
		sort = new String[]{"repayTime"};
		order = new String[]{"asc"};
		repay.setFlag(1);
		PageInfo<ExtendRepay> pageInfo = baseService.queryListByPageAndOrder(repay, (offset/rows+1), rows,sort,order);
		 EasyUIResult easyUIResult = new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
		return ResponseEntity.ok(easyUIResult);
	}
	
}
