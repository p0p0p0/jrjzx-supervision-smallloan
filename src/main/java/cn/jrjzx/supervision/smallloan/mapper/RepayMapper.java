package cn.jrjzx.supervision.smallloan.mapper;


import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;
import cn.jrjzx.supervision.smallloan.entity.Repay;


public interface RepayMapper extends Mapper<Repay>{
	@Select("select IFNULL(SUM(overdue),0) from repay_plan where loan_contract_id=#{loanContractId} and overdue > 0 and flag=1")
	public double getOverdueDays(@Param("loanContractId") long loanContractId);
}
