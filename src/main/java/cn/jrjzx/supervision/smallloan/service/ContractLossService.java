package cn.jrjzx.supervision.smallloan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jrjzx.supervision.smallloan.entity.ContractLoss;
import cn.jrjzx.supervision.smallloan.entity.LoanContract;

@Service
public class ContractLossService extends BaseService<ContractLoss> {
	
	@Autowired
	LoanContractService contractService;
	
	/**
	 * 
	 * saveContractLoss 
	 * @param contractLoss
	 * @throws Exception
	 * void
	 */
	 public void saveContractLoss(ContractLoss contractLoss) throws Exception{
		this.saveSelective(contractLoss);
		//update contract
		if(contractLoss.getIsWrittenOff().intValue()==1){
			LoanContract contract = this.contractService.queryOneByContractNumberAndCompanyId(contractLoss.getContractNumber(), contractLoss.getCompanyId().longValue(), 1);
			 LoanContract newContract = new LoanContract();
			 newContract.setIsLoss(1);
			 newContract.setId(contract.getId());
			 this.contractService.updateByIdSelective(newContract);
		}
	 }

	/**
	 * updateContractLoss
	 * @param contractLoss
	 * void
	 * @throws Exception 
	*/
	public void updateContractLoss(ContractLoss contractLoss) throws Exception {
		
		 ContractLoss oldContractLoss = this.queryByID(contractLoss.getId());
		 LoanContract contract = this.contractService.queryOneByContractNumberAndCompanyId(contractLoss.getContractNumber(), contractLoss.getCompanyId().longValue(), 1);
		 LoanContract newContract = new LoanContract();
		 newContract.setId(contract.getId());
		//isWrittenOff:1->0 || isWrittenOff:0->1,updateContract
		if((oldContractLoss.getIsWrittenOff().intValue()==1 && contractLoss.getIsWrittenOff().intValue()==0) || 
				(oldContractLoss.getIsWrittenOff().intValue()==0 && contractLoss.getIsWrittenOff().intValue()==1)){
			newContract.setIsLoss(contractLoss.getIsWrittenOff());
			this.contractService.updateByIdSelective(newContract);
		}
		//update contractLoss
		this.updateByIdSelective(contractLoss);
		
	}

	/**
	 * deleteContractLoss
	 * @param contractLoss
	 * void
	 * @throws Exception 
	*/
	public void deleteContractLoss(ContractLoss contractLoss) throws Exception {
		//delete contractLoss
		contractLoss.setFlag(0);
		this.updateByIdSelective(contractLoss);
		//update contract
		if(contractLoss.getIsWrittenOff().intValue()==1){
			 LoanContract contract = this.contractService.queryOneByContractNumberAndCompanyId(contractLoss.getContractNumber(), contractLoss.getCompanyId().longValue(), 1);
			 LoanContract newContract = new LoanContract();
			 newContract.setIsLoss(0);
			 newContract.setId(contract.getId());
			 this.contractService.updateByIdSelective(newContract);
		}
		
	}
}