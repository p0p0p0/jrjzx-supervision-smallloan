/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.controller
 * RepayController.java
 * 
 * 2017年7月7日-下午3:09:17
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jrjzx.supervision.smallloan.entity.Repay;
import cn.jrjzx.supervision.smallloan.service.RepayService;

/**
 *
 * RepayController
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年7月7日 下午3:09:17
 * 
 * @version 1.0.0
 *
 */
@RequestMapping("repay")
@RestController
public class RepayController extends BaseController<Repay, RepayService>{
	@PostMapping 
	public void save(@RequestBody Repay repay) throws Exception{
		if(repay.getMoney() <= 0){
			throw new RuntimeException("money must gt 0:"+repay.getMoney());
		}
		if(repay.getInterest() <=0){
			throw new RuntimeException("interest must gt 0:"+repay.getInterest());
		}
		this.getService().addRepay(repay);
	}
	
	
	@PutMapping
	public void update(@RequestBody Repay repay) throws Exception{
		if(repay.getMoney() <= 0){
			throw new RuntimeException("money must gt 0:"+repay.getMoney());
		}
		if(repay.getInterest() <=0){
			throw new RuntimeException("interest must gt 0:"+repay.getInterest());
		}
		this.getService().updateRepay(repay);
	}
	
	@DeleteMapping("/delete")
	public void delete(@RequestBody Repay repay) throws Exception{
		this.getService().deleteRepay(repay);
	}

}
