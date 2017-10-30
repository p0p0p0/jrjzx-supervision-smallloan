/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.mapper
 * UserMapper.java
 * 
 * 2017年6月9日-下午2:54:01
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;
import cn.jrjzx.supervision.smallloan.entity.LoanContract;

/**
 *
 * LoanContractMapper合同
 * 
 * @date 2017年6月9日 下午2:54:01
 * 
 * @version 1.0.0
 *
 */
public interface LoanContractMapper extends Mapper<LoanContract> {

	public Map<String, Object> basicStatics(
			@Param("companyId") Integer companyId);

	public List<Map<String, Object>> findRepayPlanAndExtendPlanAndContractList(
			@Param("companyId") Integer companyId,
			@Param("contractNumber") String contractNumber,
			@Param("isSettle") Integer isSettle);

	public List<LoanContract> findByParam(@Param("companyId") int companyId,
			@Param("name") String name,
			@Param("contractNumber") String contractNumber,
			@Param("sreportTime") String sreportTime,
			@Param("ereportTime") String ereportTime,
			@Param("ssignTime") String ssignTime,
			@Param("esignTime") String esignTime,
			@Param("smoney") double smoney, @Param("emoney") double emoney,
			@Param("sannualRate") double sannualRate,
			@Param("eannualRate") double eannualRate,
			@Param("status") int status, @Param("isExtend") int isExtend,
			@Param("term") int term);

}
