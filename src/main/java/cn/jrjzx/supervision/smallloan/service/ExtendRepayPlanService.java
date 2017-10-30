/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.service
 * ExtendRepayPlanService.java
 * 
 * 2017年6月9日-下午2:49:27
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;
import cn.jrjzx.supervision.smallloan.common.util.RejoiceUtil;
import cn.jrjzx.supervision.smallloan.entity.ExtendRepayPlan;
import cn.jrjzx.supervision.smallloan.entity.LoanContract;
import cn.jrjzx.supervision.smallloan.mapper.ExtendRepayPlanMapper;

/**
 *
 * ExtendRepayPlanService
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月9日 下午2:49:27
 * 
 * @version 1.0.0
 *
 */
@Service
public class ExtendRepayPlanService extends BaseService<ExtendRepayPlan> {

	@Autowired
	ExtendRepayPlanMapper extendRepayPlanMapper;
	@Autowired
	LoanContractService contractService;
	
	/**
	 * saveList(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param list
	 * void
	 * @throws Exception 
	*/
	public void saveList(List<ExtendRepayPlan> list) throws Exception {
		for(ExtendRepayPlan plan: list){
			this.saveSelective(plan);
		}
	}

	/**
	 * 
	 * newExtendRepayPlans 新建展期还款计划<br/>
	 * 以最新的为主，之前的展期全部变为历史
	 * @param list
	 * void
	 * @throws Exception 
	 */
	public void newExtendRepayPlans(List<ExtendRepayPlan> list) throws Exception {
		//1、get current extendDate
		String currentDate = RejoiceUtil.getDateStr2(new Date());
		String newExtendDate = currentDate;
		ExtendRepayPlan con = new ExtendRepayPlan();
		con.setFlag(1);
		con.setContractId(list.get(0).getContractId());
		List<ExtendRepayPlan> oldList = this.queryPageByWhere(con, 1, 1).getList();
		if(oldList.size() > 0){
			String extendDate = oldList.get(0).getExtendDate();
			if(extendDate.contains(currentDate)){
				String number = extendDate.length() == 10? null:extendDate.substring(11);
				newExtendDate = number == null?(currentDate+"-1"):(currentDate+"-"+(Integer.parseInt(number)+1));
			}else{
				newExtendDate = currentDate;
			}
		}
		//1、change old plans to history:flag = 2
		ExtendRepayPlan plan = new ExtendRepayPlan();
		plan.setFlag(2);
		Example example = new Example(ExtendRepayPlan.class);
		example.createCriteria().andEqualTo("flag", 1)
		.andEqualTo("contractId", list.get(0).getContractId());
		this.getMapper().updateByExampleSelective(plan, example);
		
		//2、save new plans
		
		for(ExtendRepayPlan newPlan: list){
			newPlan.setFlag(1);
			newPlan.setExtendDate(newExtendDate);
			this.saveSelective(newPlan);
		}
		//3、update contract extend
		LoanContract contract = new LoanContract();
		contract.setId(list.get(0).getContractId());
		contract.setIsExtend(1);
		contractService.updateByIdSelective(contract);
	}

	/**
	 * findHistoryExtendDates(这里用一句话描述这个方法的作用)
	 * (这里描述这个方法适用条件 – 可选)
	 * @param contractId
	 * @return
	 * List<String>
	*/
	public List<String> findExtendDates(Long contractId) {
		return extendRepayPlanMapper.findExtendDates(contractId);
	}

	/**
	 * findMoneySum 查询未还本金总额
	 * @param contractId
	 * @return
	 * Double
	*/
	public Double findMoneySum(Long contractId) {
		return extendRepayPlanMapper.findMoneySum(contractId);
	}
	/**
	 * 查询逾期天数
	 * 
	 * @param loanContractId
	 * @return
	 */
	@Transactional(readOnly = true)
	public double getOverdue(long loanContractId) {
		return ((ExtendRepayPlanMapper) this.getMapper())
				.getOverdueDays(loanContractId);
	}
	
}