package cn.jrjzx.supervision.smallloan.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * RepayPlan
 * 
 * @date 2017年6月29日 下午4:15:43
 * 
 * @version 1.0.0
 *
 */
@Table(name="repay_plan")
public class RepayPlan extends BaseEntity{

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
	private Long loanContractId;
	private String reqId;
	
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
	public Long getLoanContractId() {
		return loanContractId;
	}
	public void setLoanContractId(Long loanContractId) {
		this.loanContractId = loanContractId;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	@Override
	public String toString() {
		return "RepayPlan [id=" + id + ", endDate=" + endDate + ", money=" + money + ", interest=" + interest
				+ ", repaidMoney=" + repaidMoney + ", repaidInterest=" + repaidInterest + ", isSettle=" + isSettle
				+ ", isOverdue=" + isOverdue + ", overdue=" + overdue + ", loanContractId=" + loanContractId
				+ ", reqId=" + reqId + "]";
	}
}
