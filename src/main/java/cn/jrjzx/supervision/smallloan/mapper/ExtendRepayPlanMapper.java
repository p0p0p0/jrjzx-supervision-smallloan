/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.mapper
 * ExtendRepayPlanMapper.java
 * 
 * 2017年6月9日-下午2:54:01
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;
import cn.jrjzx.supervision.smallloan.entity.ExtendRepayPlan;

/**
 *
 * ExtendRepayPlanMapper
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月9日 下午2:54:01
 * 
 * @version 1.0.0
 *
 */
public interface ExtendRepayPlanMapper extends Mapper<ExtendRepayPlan>{

	public List<String> findExtendDates(@Param("contractId")Long contractId);

	/**
	 * findMoneySum 查询还款本金总额（未结清的还款本经总额-已还本金总额）
	 * @param contractId
	 * @return
	 * Double
	*/
	public Double findMoneySum(@Param("contractId")Long contractId);
	
	@Select("select IFNULL(SUM(overdue),0) from extend_repay_plan where loan_contract_id=#{loanContractId} and overdue > 0 and flag=1")
	public double getOverdueDays(@Param("loanContractId") long loanContractId);
}
