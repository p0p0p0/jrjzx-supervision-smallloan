package cn.jrjzx.supervision.smallloan.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jrjzx.supervision.smallloan.entity.PledgeContract;
import cn.jrjzx.supervision.smallloan.mapper.PledgeContractMapper;

/**
 *
 * 
 * 
 * @date 2017年6月9日 下午2:49:27
 * 
 * @version 1.0.0
 *
 */
@Service
@Transactional
public class PledgeContractService extends BaseService<PledgeContract> {
	
	@Autowired
	PledgeContractMapper pledgeContractMapper;

	/**
	 * 批量保存
	 * @param pledgeContracts
	 * @param loanContractId
	 * @throws Exception
	 */
	public void saveBatch(List<PledgeContract> pledgeContracts, Long loanContractId) throws Exception{
		for (PledgeContract pledgeContract : pledgeContracts) {
			if(pledgeContract != null && StringUtils.isNotBlank(pledgeContract.getName())){
				pledgeContract.setLoanContractId(loanContractId);
				super.saveSelective(pledgeContract);
			}
		}
	}
	
	 
}