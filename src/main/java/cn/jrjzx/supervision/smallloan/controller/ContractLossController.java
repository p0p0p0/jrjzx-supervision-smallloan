/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.controller
 * ContractLossController.java
 * 
 * 2017年7月12日-上午10:22:25
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jrjzx.supervision.smallloan.common.constant.Constant;
import cn.jrjzx.supervision.smallloan.entity.ContractLoss;
import cn.jrjzx.supervision.smallloan.entity.User;
import cn.jrjzx.supervision.smallloan.service.ContractLossService;

/**
 *
 * ContractLossController
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年7月12日 上午10:22:25
 * 
 * @version 1.0.0
 *
 */
@RequestMapping("contract-loss")
@RestController
public class ContractLossController extends BaseController<ContractLoss, ContractLossService> {
	
	@PostMapping("save")
	public void save(@RequestBody ContractLoss contractLoss,HttpServletRequest request) throws Exception {
		contractLoss.setCompanyId(((User)request.getSession().getAttribute(Constant.SESSION_KEY)).getCompanyId());
		this.getService().saveContractLoss(contractLoss);
	}
	
	@PutMapping("update")
	public void update(@RequestBody ContractLoss contractLoss) throws Exception {
		this.getService().updateContractLoss(contractLoss);
	}
	@DeleteMapping("delete")
	public void delete(@RequestBody ContractLoss contractLoss) throws Exception {
		this.getService().deleteContractLoss(contractLoss);
	}
	@GetMapping("contract-num/{contractNumber}")
	public Integer contractNumberExist(@PathVariable("contractNumber") String contractNumber, HttpServletRequest request){
		ContractLoss contractLoss = new ContractLoss();
		contractLoss.setCompanyId(((User) request.getSession().getAttribute(
				Constant.SESSION_KEY)).getCompanyId());
		contractLoss.setContractNumber(contractNumber);
		contractLoss.setFlag(1);
		List<ContractLoss> list = this.getService().queryPageByWhere(contractLoss, 1, 1).getList();
		return list.size() == 0?0:1;
	}

}
