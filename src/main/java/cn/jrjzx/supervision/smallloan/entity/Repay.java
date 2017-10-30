/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.entity
 * Repay.java
 * 
 * 2017年6月29日-下午4:15:43
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
 * Repay
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月29日 下午4:15:43
 * 
 * @version 1.0.0
 *
 */
@Table(name="repay")
public class Repay extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String reportTime;
	private String repayTime;
	private Double money;
	private Double interest;
	private Long contractId;
	private String reqId;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	public String getRepayTime() {
		return repayTime;
	}
	public void setRepayTime(String repayTime) {
		this.repayTime = repayTime;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Double getInterest() {
		return interest;
	}
	public void setInterest(Double interest) {
		this.interest = interest;
	}
	public Long getContractId() {
		return contractId;
	}
	public void setContractId(Long contractId) {
		this.contractId = contractId;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	
	
	
}
