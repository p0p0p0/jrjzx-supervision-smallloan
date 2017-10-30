package cn.jrjzx.supervision.smallloan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 资产负债表 
 *
 */
@Table(name = "balance")
public class Balance implements Serializable {
	private static final long serialVersionUID = 2464351432343656559L;
	@Id
	private int id;
	// 小贷公司id
	private int companyId;
	// 上传文件的id
	private int fileId;
	// 货币资金
	private String monetary;
	// 应收账款
	private String receivable;
	// 预付账款
	private String prepayment;
	// 应收利息
	private String interestReceivable;
	// 其他应收款
	private String otherReceivable;
	// 贷款
	private String loanMoney;
	// 贷款损失准备
	private String loanLoss;
	// 待处理流动资产净损失
	private String pendingCurrentAssets;
	// 短期投资
	private String shortInvestment;
	// 其他流动资产
	private String otherCurrentAssets;
	// 流动资产合计
	private String totalCurrentAssets;
	// 长期股权投资
	private String longInvestment;
	// 长期投资合计
	private String totalLongInvestment;
	// 固定资产原价
	private String fixedAssets;
	// 累计折旧
	private String depreciation;
	// 固定资产净值
	private String netFixedAssets;
	// 固定资产清理
	private String liquidationFixedAssets;
	// 固定资产合计
	private String totalFixedAssets;
	// 无形资产
	private String intangibleAssets;
	// 递延资产
	private String deferredAssets;
	// 待摊费用
	private String prepaidExpenses;
	// 长期待摊费用
	private String longPrepaidExpenses;
	// 其他资产合计
	private String totalOtherAssets;
	// 资产合计
	private String totalAssets;
	// 短期借款
	private String borrowing;
	// 应付账款
	private String payable;
	// 应付保费
	private String premium;
	// 预收账款
	private String received;
	// 应付职工薪酬
	private String payroll;
	// 应缴税费
	private String taxation;
	// 应付利息
	private String interestPayable;
	// 其他应缴款
	private String otherAccount;
	// 其他应付款
	private String otherPayable;
	// 应付利润
	private String profitPayable;
	// 发行短期债券
	private String bondIssued;
	// 流动负债合计
	private String totalCurrentLiabilities;
	// 长期借款
	private String longBorrowing;
	// 长期应付款
	private String longPayables;
	// 长期负债合计
	private String totalLongLiabilities;
	// 递延税款
	private String deferredTax;
	// 负债合计
	private String totalLiabilities;
	// 实收资本
	private String paidCapital;
	// 资本公积
	private String capitalSurplus;
	// 盈余公积
	private String reserveSurplus;
	// 法定盈余公积
	private String legalSurplus;
	// 公益金
	private String publicWelfare;
	// 未分配利润
	private String undistributedProfit;
	// 一般准备金
	private String generalReserve;
	// 所有者权益合计
	private String totalOwnershipInterest;
	// 负债及所有者权益合计
	private String totalLiabilitiesOwnershipInterest;
	// 所属月份
	private String dateMonth;
	// 单位负责人
	private String leader;
	// 填报人
	private String informant;
	// 填报日期
	private String reportDate;
	// 记录创建日期
	private Date createTime;
	// 删除标识
	private int flag = 1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPremium() {
		return premium;
	}

	public void setPremium(String premium) {
		this.premium = premium;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getFileId() {
		return fileId;
	}

	public void setFileId(int fileId) {
		this.fileId = fileId;
	}

	public String getLongPrepaidExpenses() {
		return longPrepaidExpenses;
	}

	public void setLongPrepaidExpenses(String longPrepaidExpenses) {
		this.longPrepaidExpenses = longPrepaidExpenses;
	}

	public String getMonetary() {
		return monetary;
	}

	public void setMonetary(String monetary) {
		this.monetary = monetary;
	}

	public String getReceivable() {
		return receivable;
	}

	public void setReceivable(String receivable) {
		this.receivable = receivable;
	}

	public String getPrepayment() {
		return prepayment;
	}

	public void setPrepayment(String prepayment) {
		this.prepayment = prepayment;
	}

	public String getProfitPayable() {
		return profitPayable;
	}

	public void setProfitPayable(String profitPayable) {
		this.profitPayable = profitPayable;
	}

	public String getInterestReceivable() {
		return interestReceivable;
	}

	public void setInterestReceivable(String interestReceivable) {
		this.interestReceivable = interestReceivable;
	}

	public String getOtherReceivable() {
		return otherReceivable;
	}

	public void setOtherReceivable(String otherReceivable) {
		this.otherReceivable = otherReceivable;
	}

	public String getLoanMoney() {
		return loanMoney;
	}

	public void setLoanMoney(String loanMoney) {
		this.loanMoney = loanMoney;
	}

	public String getLoanLoss() {
		return loanLoss;
	}

	public void setLoanLoss(String loanLoss) {
		this.loanLoss = loanLoss;
	}

	public String getPendingCurrentAssets() {
		return pendingCurrentAssets;
	}

	public void setPendingCurrentAssets(String pendingCurrentAssets) {
		this.pendingCurrentAssets = pendingCurrentAssets;
	}

	public String getOtherCurrentAssets() {
		return otherCurrentAssets;
	}

	public void setOtherCurrentAssets(String otherCurrentAssets) {
		this.otherCurrentAssets = otherCurrentAssets;
	}

	public String getTotalCurrentAssets() {
		return totalCurrentAssets;
	}

	public void setTotalCurrentAssets(String totalCurrentAssets) {
		this.totalCurrentAssets = totalCurrentAssets;
	}

	public String getFixedAssets() {
		return fixedAssets;
	}

	public void setFixedAssets(String fixedAssets) {
		this.fixedAssets = fixedAssets;
	}

	public String getDepreciation() {
		return depreciation;
	}

	public void setDepreciation(String depreciation) {
		this.depreciation = depreciation;
	}

	public String getNetFixedAssets() {
		return netFixedAssets;
	}

	public void setNetFixedAssets(String netFixedAssets) {
		this.netFixedAssets = netFixedAssets;
	}

	public String getLiquidationFixedAssets() {
		return liquidationFixedAssets;
	}

	public void setLiquidationFixedAssets(String liquidationFixedAssets) {
		this.liquidationFixedAssets = liquidationFixedAssets;
	}

	public String getTotalFixedAssets() {
		return totalFixedAssets;
	}

	public void setTotalFixedAssets(String totalFixedAssets) {
		this.totalFixedAssets = totalFixedAssets;
	}

	public String getIntangibleAssets() {
		return intangibleAssets;
	}

	public void setIntangibleAssets(String intangibleAssets) {
		this.intangibleAssets = intangibleAssets;
	}

	public String getDeferredAssets() {
		return deferredAssets;
	}

	public void setDeferredAssets(String deferredAssets) {
		this.deferredAssets = deferredAssets;
	}

	public String getPrepaidExpenses() {
		return prepaidExpenses;
	}

	public void setPrepaidExpenses(String prepaidExpenses) {
		this.prepaidExpenses = prepaidExpenses;
	}

	public String getLongInvestment() {
		return longInvestment;
	}

	public void setLongInvestment(String longInvestment) {
		this.longInvestment = longInvestment;
	}

	public String getTotalLongInvestment() {
		return totalLongInvestment;
	}

	public void setTotalLongInvestment(String totalLongInvestment) {
		this.totalLongInvestment = totalLongInvestment;
	}

	public String getTotalOtherAssets() {
		return totalOtherAssets;
	}

	public void setTotalOtherAssets(String totalOtherAssets) {
		this.totalOtherAssets = totalOtherAssets;
	}

	public String getTotalAssets() {
		return totalAssets;
	}

	public void setTotalAssets(String totalAssets) {
		this.totalAssets = totalAssets;
	}

	public String getBorrowing() {
		return borrowing;
	}

	public void setBorrowing(String borrowing) {
		this.borrowing = borrowing;
	}

	public String getPayable() {
		return payable;
	}

	public void setPayable(String payable) {
		this.payable = payable;
	}

	public String getReceived() {
		return received;
	}

	public void setReceived(String received) {
		this.received = received;
	}

	public String getPayroll() {
		return payroll;
	}

	public void setPayroll(String payroll) {
		this.payroll = payroll;
	}

	public String getTaxation() {
		return taxation;
	}

	public void setTaxation(String taxation) {
		this.taxation = taxation;
	}

	public String getInterestPayable() {
		return interestPayable;
	}

	public void setInterestPayable(String interestPayable) {
		this.interestPayable = interestPayable;
	}

	public String getOtherAccount() {
		return otherAccount;
	}

	public void setOtherAccount(String otherAccount) {
		this.otherAccount = otherAccount;
	}

	public String getOtherPayable() {
		return otherPayable;
	}

	public void setOtherPayable(String otherPayable) {
		this.otherPayable = otherPayable;
	}

	public String getBondIssued() {
		return bondIssued;
	}

	public void setBondIssued(String bondIssued) {
		this.bondIssued = bondIssued;
	}

	public String getTotalCurrentLiabilities() {
		return totalCurrentLiabilities;
	}

	public void setTotalCurrentLiabilities(String totalCurrentLiabilities) {
		this.totalCurrentLiabilities = totalCurrentLiabilities;
	}

	public String getLongBorrowing() {
		return longBorrowing;
	}

	public void setLongBorrowing(String longBorrowing) {
		this.longBorrowing = longBorrowing;
	}

	public String getLongPayables() {
		return longPayables;
	}

	public void setLongPayables(String longPayables) {
		this.longPayables = longPayables;
	}

	public String getTotalLongLiabilities() {
		return totalLongLiabilities;
	}

	public void setTotalLongLiabilities(String totalLongLiabilities) {
		this.totalLongLiabilities = totalLongLiabilities;
	}

	public String getDeferredTax() {
		return deferredTax;
	}

	public void setDeferredTax(String deferredTax) {
		this.deferredTax = deferredTax;
	}

	public String getTotalLiabilities() {
		return totalLiabilities;
	}

	public void setTotalLiabilities(String totalLiabilities) {
		this.totalLiabilities = totalLiabilities;
	}

	public String getPaidCapital() {
		return paidCapital;
	}

	public void setPaidCapital(String paidCapital) {
		this.paidCapital = paidCapital;
	}

	public String getCapitalSurplus() {
		return capitalSurplus;
	}

	public void setCapitalSurplus(String capitalSurplus) {
		this.capitalSurplus = capitalSurplus;
	}

	public String getReserveSurplus() {
		return reserveSurplus;
	}

	public void setReserveSurplus(String reserveSurplus) {
		this.reserveSurplus = reserveSurplus;
	}

	public String getLegalSurplus() {
		return legalSurplus;
	}

	public void setLegalSurplus(String legalSurplus) {
		this.legalSurplus = legalSurplus;
	}

	public String getPublicWelfare() {
		return publicWelfare;
	}

	public void setPublicWelfare(String publicWelfare) {
		this.publicWelfare = publicWelfare;
	}

	public String getUndistributedProfit() {
		return undistributedProfit;
	}

	public void setUndistributedProfit(String undistributedProfit) {
		this.undistributedProfit = undistributedProfit;
	}

	public String getShortInvestment() {
		return shortInvestment;
	}

	public void setShortInvestment(String shortInvestment) {
		this.shortInvestment = shortInvestment;
	}

	public String getGeneralReserve() {
		return generalReserve;
	}

	public void setGeneralReserve(String generalReserve) {
		this.generalReserve = generalReserve;
	}

	public String getTotalOwnershipInterest() {
		return totalOwnershipInterest;
	}

	public void setTotalOwnershipInterest(String totalOwnershipInterest) {
		this.totalOwnershipInterest = totalOwnershipInterest;
	}

	public String getTotalLiabilitiesOwnershipInterest() {
		return totalLiabilitiesOwnershipInterest;
	}

	public void setTotalLiabilitiesOwnershipInterest(
			String totalLiabilitiesOwnershipInterest) {
		this.totalLiabilitiesOwnershipInterest = totalLiabilitiesOwnershipInterest;
	}

	public String getDateMonth() {
		return dateMonth;
	}

	public void setDateMonth(String dateMonth) {
		this.dateMonth = dateMonth;
	}

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public String getLeader() {
		return leader;
	}

	public void setLeader(String leader) {
		this.leader = leader;
	}

	public String getInformant() {
		return informant;
	}

	public void setInformant(String informant) {
		this.informant = informant;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Balance [id=" + id + ", companyId=" + companyId + ", fileId="
				+ fileId + ", monetary=" + monetary + ", receivable="
				+ receivable + ", prepayment=" + prepayment
				+ ", interestReceivable=" + interestReceivable
				+ ", otherReceivable=" + otherReceivable + ", dateMonth="
				+ dateMonth + ", leader=" + leader + ", informant=" + informant
				+ ", reportDate=" + reportDate + ", flag=" + flag + "]";
	}

}
