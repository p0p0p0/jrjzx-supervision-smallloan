package cn.jrjzx.supervision.smallloan.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jrjzx.supervision.smallloan.entity.MortgageContract;
import cn.jrjzx.supervision.smallloan.mapper.MortgageContractMapper;

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
public class MortgageContractService extends BaseService<MortgageContract> {
	
	@Autowired
	MortgageContractMapper MortgageContractMapper;

	/**
	 * 批量保存
	 * @param mortgageContracts
	 * @param loanContractId
	 * @throws Exception
	 */
	public void saveBatch(List<MortgageContract> mortgageContracts, Long loanContractId) throws Exception{
		for (MortgageContract mortgageContract : mortgageContracts) {
			if(mortgageContract != null && StringUtils.isNotBlank(mortgageContract.getName())){
				mortgageContract.setLoanContractId(loanContractId);
				super.saveSelective(mortgageContract);				
			}
		}
	}
	
	 
}