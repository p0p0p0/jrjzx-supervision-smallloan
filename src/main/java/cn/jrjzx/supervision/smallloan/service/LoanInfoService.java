/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.service
 * LoanInfoService.java
 * 
 * 2017年6月9日-下午2:49:27
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jrjzx.supervision.smallloan.entity.LoanContract;
import cn.jrjzx.supervision.smallloan.entity.LoanInfo;
import cn.jrjzx.supervision.smallloan.mapper.LoanInfoMapper;

/**
 *
 * LoanInfoPlanService
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月9日 下午2:49:27
 * 
 * @version 1.0.0
 *
 */
@Service
public class LoanInfoService extends BaseService<LoanInfo> {

	@Autowired
	LoanInfoMapper LoanInfoMapper;

	
	@Autowired
	LoanContractService contractService;
	
	/**
	 * saveOrUpdate(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param list
	 * void
	 * @throws Exception 
	*/
	public void saveOrUpdate(List<LoanInfo> list) throws Exception {
		for(LoanInfo loanInfo: list){
			this.saveOrUpdateSelective(loanInfo);
		}
		if(list.size() > 0){
			//update contract loanInfoCount
			LoanContract contract = new LoanContract();
			contract.setId(list.get(0).getContractId());
			contract.setLoanInfoCount(list.size());
			contractService.updateByIdSelective(contract);	
		}
		
	}

	

}