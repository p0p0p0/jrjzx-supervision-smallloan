/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.entity
 * ExtendRepayPlan.java
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
 * ExtendRepayPlan
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月29日 下午4:15:43
 * 
 * @version 1.0.0
 *
 */
@Table(name="extend_repay_plan")
public class ExtendRepayPlan extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String endDate;
	private Double money;
	private Double interest;
	private Double repaidMoney;
	private Double repaidInterest;
	private Integer isSettle;
	private Integer isOverdue;
	private Integer overdue;
	private Integer count;
	private Long contractId;
	private String reqId;
	private String extendDate;
	
	public String getExtendDate() {
		return extendDate;
	}
	public void setExtendDate(String extendDate) {
		this.extendDate = extendDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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
	public Double getRepaidMoney() {
		return repaidMoney;
	}
	public void setRepaidMoney(Double repaidMoney) {
		this.repaidMoney = repaidMoney;
	}
	public Double getRepaidInterest() {
		return repaidInterest;
	}
	public void setRepaidInterest(Double repaidInterest) {
		this.repaidInterest = repaidInterest;
	}
	public Integer getIsSettle() {
		return isSettle;
	}
	public void setIsSettle(Integer isSettle) {
		this.isSettle = isSettle;
	}
	public Integer getIsOverdue() {
		return isOverdue;
	}
	public void setIsOverdue(Integer isOverdue) {
		this.isOverdue = isOverdue;
	}
	public Integer getOverdue() {
		return overdue;
	}
	public void setOverdue(Integer overdue) {
		this.overdue = overdue;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
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
