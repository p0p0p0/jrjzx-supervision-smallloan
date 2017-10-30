package cn.jrjzx.supervision.smallloan.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jrjzx.supervision.smallloan.entity.EnsureContract;
import cn.jrjzx.supervision.smallloan.mapper.EnsureContractMapper;

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
public class EnsureContractService extends BaseService<EnsureContract> {
	
	@Autowired
	EnsureContractMapper EnsureContractMapper;

	/**
	 * 批量保存
	 * @param EnsureContracts
	 * @param loanContractId
	 * @throws Exception
	 */
	public void saveBatch(List<EnsureContract> ensureContracts, Long loanContractId) throws Exception{
		for (EnsureContract ensureContract : ensureContracts) {
			if(ensureContract != null && StringUtils.isNotBlank(ensureContract.getName())){
				ensureContract.setLoanContractId(loanContractId);
				super.saveSelective(ensureContract);				
			}
		}
	}
	
	 
}