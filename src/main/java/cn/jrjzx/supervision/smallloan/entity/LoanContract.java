/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.entity
 * User.java
 * 
 * 2017年6月9日-下午2:19:10
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.entity;

import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 *
 * 贷款合同
 * 
 * @date 2017年6月29日 
 * 
 * @version 1.0.0
 *
 */
@Table(name="loan_contract")
public class LoanContract extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long companyId;
	private Long borrowerId;
	private String contractNumber;
	private String contractName;
	private String signTime;
	private String reportTime;
	private String startDate;
	private String endDate;
	private Double money;
	private Integer termType;
	private String term;
	private Double rate;
	private Double annualRate;
	private Integer isEntrust;
	private Integer repayMethod;
	private String areaProvince;
	private String areaCity;
	private String areaDistrict;
	private Integer borrowerType;
	private Integer contractType;
	private Integer purpose;
	private Integer business;
	private String repaySource;
	private String loanMethod;//贷款方式（多选，中间英文逗号隔开：1信用，2保证，3抵押，4质押，5其它）
	private Integer isCheck;
	private Integer isUnion;
	private Integer isExtend;
	private Integer isDraft;
	private Integer statusIsSettle;
	private Integer statusIsOverdue;
	private Double loanBalance;
	private String reqId;
	
	private Integer isLoss;//是否报损
	private Integer isTransfer;//是否转让
	private Integer loanInfoCount;//放款笔数
	
	public Integer getLoanInfoCount() {
		return loanInfoCount;
	}
	public void setLoanInfoCount(Integer loanInfoCount) {
		this.loanInfoCount = loanInfoCount;
	}
	
	@Transient
	private String borrowerName;
	
	@Transient
	EnterpriseBorrower enterpriseBorrower;
	@Transient
	PersonBorrower personBorrower;
	@Transient
	private List<RepayPlan> repayPlans;
	@Transient
	private List<EnsureContract> ensureContracts;
	@Transient
	private List<MortgageContract> mortgageContracts;
	@Transient
	private List<PledgeContract> pledgeContracts;
	
	public Integer getIsLoss() {
		return isLoss;
	}
	public void setIsLoss(Integer isLoss) {
		this.isLoss = isLoss;
	}
	public Integer getIsTransfer() {
		return isTransfer;
	}
	public void setIsTransfer(Integer isTransfer) {
		this.isTransfer = isTransfer;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	public Long getBorrowerId() {
		return borrowerId;
	}
	public void setBorrowerId(Long borrowerId) {
		this.borrowerId = borrowerId;
	}
	public String getContractNumber() {
		return contractNumber;
	}
	public void setContractNumber(String contractNumber) {
		this.contractNumber = contractNumber;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getSignTime() {
		return signTime;
	}
	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}
	public String getReportTime() {
		return reportTime;
	}
	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
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
	public Integer getTermType() {
		return termType;
	}
	public void setTermType(Integer termType) {
		this.termType = termType;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getAnnualRate() {
		return annualRate;
	}
	public void setAnnualRate(Double annualRate) {
		this.annualRate = annualRate;
	}
	public Integer getIsEntrust() {
		return isEntrust;
	}
	public void setIsEntrust(Integer isEntrust) {
		this.isEntrust = isEntrust;
	}
	public Integer getRepayMethod() {
		return repayMethod;
	}
	public void setRepayMethod(Integer repayMethod) {
		this.repayMethod = repayMethod;
	}
	public String getAreaProvince() {
		return areaProvince;
	}
	public void setAreaProvince(String areaProvince) {
		this.areaProvince = areaProvince;
	}
	public String getAreaCity() {
		return areaCity;
	}
	public void setAreaCity(String areaCity) {
		this.areaCity = areaCity;
	}
	public String getAreaDistrict() {
		return areaDistrict;
	}
	public void setAreaDistrict(String areaDistrict) {
		this.areaDistrict = areaDistrict;
	}
	public Integer getBorrowerType() {
		return borrowerType;
	}
	public void setBorrowerType(Integer borrowerType) {
		this.borrowerType = borrowerType;
	}
	public Integer getContractType() {
		return contractType;
	}
	public void setContractType(Integer contractType) {
		this.contractType = contractType;
	}
	public Integer getPurpose() {
		return purpose;
	}
	public void setPurpose(Integer purpose) {
		this.purpose = purpose;
	}
	public Integer getBusiness() {
		return business;
	}
	public void setBusiness(Integer business) {
		this.business = business;
	}
	public String getRepaySource() {
		return repaySource;
	}
	public void setRepaySource(String repaySource) {
		this.repaySource = repaySource;
	}
	public String getLoanMethod() {
		return loanMethod;
	}
	public void setLoanMethod(String loanMethod) {
		this.loanMethod = loanMethod;
	}
	public Integer getIsCheck() {
		return isCheck;
	}
	public void setIsCheck(Integer isCheck) {
		this.isCheck = isCheck;
	}
	public Integer getIsUnion() {
		return isUnion;
	}
	public void setIsUnion(Integer isUnion) {
		this.isUnion = isUnion;
	}
	public Integer getIsExtend() {
		return isExtend;
	}
	public void setIsExtend(Integer isExtend) {
		this.isExtend = isExtend;
	}
	public Integer getIsDraft() {
		return isDraft;
	}
	public void setIsDraft(Integer isDraft) {
		this.isDraft = isDraft;
	}
	public Integer getStatusIsSettle() {
		return statusIsSettle;
	}
	public void setStatusIsSettle(Integer statusIsSettle) {
		this.statusIsSettle = statusIsSettle;
	}
	public Integer getStatusIsOverdue() {
		return statusIsOverdue;
	}
	public void setStatusIsOverdue(Integer statusIsOverdue) {
		this.statusIsOverdue = statusIsOverdue;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public Double getLoanBalance() {
		return loanBalance;
	}
	public void setLoanBalance(Double loanBalance) {
		this.loanBalance = loanBalance;
	}
	public EnterpriseBorrower getEnterpriseBorrower() {
		return enterpriseBorrower;
	}
	public void setEnterpriseBorrower(EnterpriseBorrower enterpriseBorrower) {
		this.enterpriseBorrower = enterpriseBorrower;
	}
	public PersonBorrower getPersonBorrower() {
		return personBorrower;
	}
	public void setPersonBorrower(PersonBorrower personBorrower) {
		this.personBorrower = personBorrower;
	}
	public List<RepayPlan> getRepayPlans() {
		return repayPlans;
	}
	public void setRepayPlans(List<RepayPlan> repayPlans) {
		this.repayPlans = repayPlans;
	}
	public List<EnsureContract> getEnsureContracts() {
		return ensureContracts;
	}
	public void setEnsureContracts(List<EnsureContract> ensureContracts) {
		this.ensureContracts = ensureContracts;
	}
	public List<MortgageContract> getMortgageContracts() {
		return mortgageContracts;
	}
	public void setMortgageContracts(List<MortgageContract> mortgageContracts) {
		this.mortgageContracts = mortgageContracts;
	}
	public List<PledgeContract> getPledgeContracts() {
		return pledgeContracts;
	}
	public void setPledgeContracts(List<PledgeContract> pledgeContracts) {
		this.pledgeContracts = pledgeContracts;
	}
	public String getBorrowerName() {
		return borrowerName;
	}
	public void setBorrowerName(String borrowerName) {
		this.borrowerName = borrowerName;
	}
	@Override
	public String toString() {
		return "LoanContract [id=" + id + ", companyId=" + companyId + ", borrowerId=" + borrowerId
				+ ", contractNumber=" + contractNumber + ", contractName=" + contractName + ", signTime=" + signTime
				+ ", reportTime=" + reportTime + ", startDate=" + startDate + ", endDate=" + endDate + ", money="
				+ money + ", termType=" + termType + ", term=" + term + ", rate=" + rate + ", annualRate=" + annualRate
				+ ", isEntrust=" + isEntrust + ", repayMethod=" + repayMethod + ", areaProvince=" + areaProvince
				+ ", areaCity=" + areaCity + ", areaDistrict=" + areaDistrict + ", borrowerType=" + borrowerType
				+ ", contractType=" + contractType + ", purpose=" + purpose + ", business=" + business
				+ ", repaySource=" + repaySource + ", loanMethod=" + loanMethod + ", isCheck=" + isCheck + ", isUnion="
				+ isUnion + ", isExtend=" + isExtend + ", isDraft=" + isDraft + ", statusIsSettle=" + statusIsSettle
				+ ", statusIsOverdue=" + statusIsOverdue + ", loanBalance=" + loanBalance + ", reqId=" + reqId + "]";
	}
}
