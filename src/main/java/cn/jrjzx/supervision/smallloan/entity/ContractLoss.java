/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.entity
 * ContractLoss.java
 * 
 * 2017年7月12日-上午9:50:05
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * ContractLoss
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年7月12日 上午9:50:05
 * 
 * @version 1.0.0
 *
 */
@Table(name="contract_loss")
public class ContractLoss extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Double badMoney;
	private String lossDate;
	private String contractNumber;
	private Integer companyId;
	private Integer isWrittenOff;//0:未核销，1：已核销
	private Integer badType; 
	private String followUp;
	
	public String getFollowUp() {
		return followUp;
	}
	public void setFollowUp(String followUp) {
		this.followUp = followUp;
	}
	public Integer getBadType() {
		return badType;
	}
	public void setBadType(Integer badType) {
		this.badType = badType;
	}
	private String reqId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Double getBadMoney() {
		return badMoney;
	}
	public void setBadMoney(Double badMoney) {
		this.badMoney = badMoney;
	}
	public String getLossDate() {
		return lossDate;
	}
	public void setLossDate(String lossDate) {
		this.lossDate = lossDate;
	}
	
	
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public Integer getIsWrittenOff() {
		return isWrittenOff;
	}
	public void setIsWrittenOff(Integer isWrittenOff) {
		this.isWrittenOff = isWrittenOff;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	
}
