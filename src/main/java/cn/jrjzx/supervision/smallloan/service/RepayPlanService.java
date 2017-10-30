package cn.jrjzx.supervision.smallloan.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jrjzx.supervision.smallloan.entity.RepayPlan;
import cn.jrjzx.supervision.smallloan.mapper.RepayPlanMapper;

/**
 *
 * 还款计划
 * 
 * @date 2017年6月9日 下午2:49:27
 * 
 * @version 1.0.0
 *
 */
@Service
@Transactional
public class RepayPlanService extends BaseService<RepayPlan> {
	
	@Autowired
	RepayPlanMapper repayPlanMapper;

	private static final Logger LOGGER = LoggerFactory.getLogger(RepayPlanService.class);
	
	/**
	 * 批量保存还款计划
	 * @param repayPlans
	 * @param loanContractId
	 * @throws Exception
	 */
	public void saveBatch(List<RepayPlan> repayPlans, Long loanContractId) throws Exception{
		for (RepayPlan repayPlan : repayPlans) {
			if(repayPlan != null && StringUtils.isNotBlank(repayPlan.getEndDate())){
				repayPlan.setLoanContractId(loanContractId);
				LOGGER.debug("start to save RepayPlan,"+repayPlan.toString());
				super.saveSelective(repayPlan);				
			}
		}
	}
	/**
	 * 查询逾期天数
	 * 
	 * @param loanContractId
	 * @return
	 */
	@Transactional(readOnly = true)
	public double getOverdue(long loanContractId) {
		return ((RepayPlanMapper) this.getMapper())
				.getOverdueDays(loanContractId);
	}
	 
}