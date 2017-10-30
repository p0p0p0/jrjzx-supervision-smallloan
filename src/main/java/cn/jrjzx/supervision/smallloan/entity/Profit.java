package cn.jrjzx.supervision.smallloan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 利润表
 */
@Table(name = "profit")
public class Profit implements Serializable {
	private static final long serialVersionUID = -401509891214523386L;
	@Id
	private int id;
	// 小贷公司id
	private int companyId;
	// 上传文件的id
	private int fileId;
	// 营业收入
	private String businessIncome;
	// 本年累计营业收入
	private String yearBusinessIncome;
	// 利息收入
	private String interestIncome;
	// 本年累计利息收入
	private String yearInterestIncome;
	// 金融机构往来利息收入
	private String financialInterestIncome;
	// 本年累计金融机构往来利息收入
	private String yearFinancialInterestIncome;
	// 手续费收入
	private String feeIncome;
	// 本年累计手续费收入
	private String yearFeeIncome;
	// 公允价值变动收益
	private String changeIncome;
	// 本年累计公允价值变动收益
	private String yearChangeIncome;
	// 汇总收益
	private String aggregateIncome;
	// 本年累计汇总收益
	private String yearAggregateIncome;
	// 其他营业收入
	private String otherIncome;
	// 本年累计其他营业收入
	private String yearOtherIncome;
	// 营业支出
	private String businessExpenses;
	// 本年累计营业支出
	private String yearBusinessExpenses;
	// 利息支出
	private String interestExpense;
	// 本年累计利息支出
	private String yearInterestExpense;
	// 金融机构往来利息支出
	private String financialInterestExpense;
	// 本年累计金融机构往来利息支出
	private String yearFinancialInterestExpense;
	// 手续费支出
	private String feeExpense;
	// 本年累计手续费支出
	private String yearFeeExpense;
	// 业务及管理费
	private String managementFees;
	// 本年累计业务及管理费
	private String yearManagementFees;
	// 兑汇损失
	private String exchangeLoss;
	// 本年累计兑汇损失
	private String yearExchangeLoss;
	// 资产减值损失
	private String assetsLoss;
	// 本年累计资产减值损失
	private String yearAssetsLoss;
	// 其他营业支出
	private String otherExpenses;
	// 本年累计其他营业支出
	private String yearOtherExpenses;
	// 营业税及附加
	private String businessTax;
	// 本年累计 营业税及附加
	private String yearBusinessTax;
	// 营业利润
	private String operatingIncome;
	// 本年累计营业利润
	private String yearOperatingIncome;
	// 投资收益
	private String investmentIncome;
	// 本年累计投资收益
	private String yearInvestmentIncome;
	// 营业外收入
	private String nonOperatingIncome;
	// 本年累计营业外收入
	private String yearNonOperatingIncome;
	// 营业外支出
	private String nonOperatingPayment;
	// 本年累计营业外支出
	private String yearNonOperatingPayment;
	// 利润总额
	private String totalProfit;
	// 本年累计利润总额
	private String yearTotalProfit;
	// 所得税
	private String incomeTax;
	// 本年累计所得税
	private String yearIncomeTax;
	// 净利润
	private String netProfit;
	// 本年累计净利润
	private String yearNetProfit;
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

	public String getBusinessIncome() {
		return businessIncome;
	}

	public void setBusinessIncome(String businessIncome) {
		this.businessIncome = businessIncome;
	}

	public String getInterestIncome() {
		return interestIncome;
	}

	public void setInterestIncome(String interestIncome) {
		this.interestIncome = interestIncome;
	}

	public String getFinancialInterestIncome() {
		return financialInterestIncome;
	}

	public void setFinancialInterestIncome(String financialInterestIncome) {
		this.financialInterestIncome = financialInterestIncome;
	}

	public String getFeeIncome() {
		return feeIncome;
	}

	public void setFeeIncome(String feeIncome) {
		this.feeIncome = feeIncome;
	}

	public String getChangeIncome() {
		return changeIncome;
	}

	public void setChangeIncome(String changeIncome) {
		this.changeIncome = changeIncome;
	}

	public String getAggregateIncome() {
		return aggregateIncome;
	}

	public void setAggregateIncome(String aggregateIncome) {
		this.aggregateIncome = aggregateIncome;
	}

	public String getOtherIncome() {
		return otherIncome;
	}

	public void setOtherIncome(String otherIncome) {
		this.otherIncome = otherIncome;
	}

	public String getBusinessExpenses() {
		return businessExpenses;
	}

	public void setBusinessExpenses(String businessExpenses) {
		this.businessExpenses = businessExpenses;
	}

	public String getInterestExpense() {
		return interestExpense;
	}

	public void setInterestExpense(String interestExpense) {
		this.interestExpense = interestExpense;
	}

	public String getFinancialInterestExpense() {
		return financialInterestExpense;
	}

	public void setFinancialInterestExpense(String financialInterestExpense) {
		this.financialInterestExpense = financialInterestExpense;
	}

	public String getFeeExpense() {
		return feeExpense;
	}

	public void setFeeExpense(String feeExpense) {
		this.feeExpense = feeExpense;
	}

	public String getManagementFees() {
		return managementFees;
	}

	public void setManagementFees(String managementFees) {
		this.managementFees = managementFees;
	}

	public String getExchangeLoss() {
		return exchangeLoss;
	}

	public void setExchangeLoss(String exchangeLoss) {
		this.exchangeLoss = exchangeLoss;
	}

	public String getAssetsLoss() {
		return assetsLoss;
	}

	public void setAssetsLoss(String assetsLoss) {
		this.assetsLoss = assetsLoss;
	}

	public String getOtherExpenses() {
		return otherExpenses;
	}

	public void setOtherExpenses(String otherExpenses) {
		this.otherExpenses = otherExpenses;
	}

	public String getBusinessTax() {
		return businessTax;
	}

	public void setBusinessTax(String businessTax) {
		this.businessTax = businessTax;
	}

	public String getOperatingIncome() {
		return operatingIncome;
	}

	public void setOperatingIncome(String operatingIncome) {
		this.operatingIncome = operatingIncome;
	}

	public String getInvestmentIncome() {
		return investmentIncome;
	}

	public void setInvestmentIncome(String investmentIncome) {
		this.investmentIncome = investmentIncome;
	}

	public String getNonOperatingIncome() {
		return nonOperatingIncome;
	}

	public void setNonOperatingIncome(String nonOperatingIncome) {
		this.nonOperatingIncome = nonOperatingIncome;
	}

	public String getNonOperatingPayment() {
		return nonOperatingPayment;
	}

	public void setNonOperatingPayment(String nonOperatingPayment) {
		this.nonOperatingPayment = nonOperatingPayment;
	}

	public String getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(String totalProfit) {
		this.totalProfit = totalProfit;
	}

	public String getIncomeTax() {
		return incomeTax;
	}

	public void setIncomeTax(String incomeTax) {
		this.incomeTax = incomeTax;
	}

	public String getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(String netProfit) {
		this.netProfit = netProfit;
	}

	public String getYearBusinessIncome() {
		return yearBusinessIncome;
	}

	public void setYearBusinessIncome(String yearBusinessIncome) {
		this.yearBusinessIncome = yearBusinessIncome;
	}

	public String getYearInterestIncome() {
		return yearInterestIncome;
	}

	public void setYearInterestIncome(String yearInterestIncome) {
		this.yearInterestIncome = yearInterestIncome;
	}

	public String getYearFinancialInterestIncome() {
		return yearFinancialInterestIncome;
	}

	public void setYearFinancialInterestIncome(
			String yearFinancialInterestIncome) {
		this.yearFinancialInterestIncome = yearFinancialInterestIncome;
	}

	public String getYearFeeIncome() {
		return yearFeeIncome;
	}

	public void setYearFeeIncome(String yearFeeIncome) {
		this.yearFeeIncome = yearFeeIncome;
	}

	public String getYearChangeIncome() {
		return yearChangeIncome;
	}

	public void setYearChangeIncome(String yearChangeIncome) {
		this.yearChangeIncome = yearChangeIncome;
	}

	public String getYearAggregateIncome() {
		return yearAggregateIncome;
	}

	public void setYearAggregateIncome(String yearAggregateIncome) {
		this.yearAggregateIncome = yearAggregateIncome;
	}

	public String getYearOtherIncome() {
		return yearOtherIncome;
	}

	public void setYearOtherIncome(String yearOtherIncome) {
		this.yearOtherIncome = yearOtherIncome;
	}

	public String getYearBusinessExpenses() {
		return yearBusinessExpenses;
	}

	public void setYearBusinessExpenses(String yearBusinessExpenses) {
		this.yearBusinessExpenses = yearBusinessExpenses;
	}

	public String getYearInterestExpense() {
		return yearInterestExpense;
	}

	public void setYearInterestExpense(String yearInterestExpense) {
		this.yearInterestExpense = yearInterestExpense;
	}

	public String getYearFinancialInterestExpense() {
		return yearFinancialInterestExpense;
	}

	public void setYearFinancialInterestExpense(
			String yearFinancialInterestExpense) {
		this.yearFinancialInterestExpense = yearFinancialInterestExpense;
	}

	public String getYearFeeExpense() {
		return yearFeeExpense;
	}

	public void setYearFeeExpense(String yearFeeExpense) {
		this.yearFeeExpense = yearFeeExpense;
	}

	public String getYearManagementFees() {
		return yearManagementFees;
	}

	public void setYearManagementFees(String yearManagementFees) {
		this.yearManagementFees = yearManagementFees;
	}

	public String getYearExchangeLoss() {
		return yearExchangeLoss;
	}

	public void setYearExchangeLoss(String yearExchangeLoss) {
		this.yearExchangeLoss = yearExchangeLoss;
	}

	public String getYearAssetsLoss() {
		return yearAssetsLoss;
	}

	public void setYearAssetsLoss(String yearAssetsLoss) {
		this.yearAssetsLoss = yearAssetsLoss;
	}

	public String getYearOtherExpenses() {
		return yearOtherExpenses;
	}

	public void setYearOtherExpenses(String yearOtherExpenses) {
		this.yearOtherExpenses = yearOtherExpenses;
	}

	public String getYearBusinessTax() {
		return yearBusinessTax;
	}

	public void setYearBusinessTax(String yearBusinessTax) {
		this.yearBusinessTax = yearBusinessTax;
	}

	public String getYearOperatingIncome() {
		return yearOperatingIncome;
	}

	public void setYearOperatingIncome(String yearOperatingIncome) {
		this.yearOperatingIncome = yearOperatingIncome;
	}

	public String getYearInvestmentIncome() {
		return yearInvestmentIncome;
	}

	public void setYearInvestmentIncome(String yearInvestmentIncome) {
		this.yearInvestmentIncome = yearInvestmentIncome;
	}

	public String getYearNonOperatingIncome() {
		return yearNonOperatingIncome;
	}

	public void setYearNonOperatingIncome(String yearNonOperatingIncome) {
		this.yearNonOperatingIncome = yearNonOperatingIncome;
	}

	public String getYearNonOperatingPayment() {
		return yearNonOperatingPayment;
	}

	public void setYearNonOperatingPayment(String yearNonOperatingPayment) {
		this.yearNonOperatingPayment = yearNonOperatingPayment;
	}

	public String getYearTotalProfit() {
		return yearTotalProfit;
	}

	public void setYearTotalProfit(String yearTotalProfit) {
		this.yearTotalProfit = yearTotalProfit;
	}

	public String getYearIncomeTax() {
		return yearIncomeTax;
	}

	public void setYearIncomeTax(String yearIncomeTax) {
		this.yearIncomeTax = yearIncomeTax;
	}

	public String getYearNetProfit() {
		return yearNetProfit;
	}

	public void setYearNetProfit(String yearNetProfit) {
		this.yearNetProfit = yearNetProfit;
	}

	public String getDateMonth() {
		return dateMonth;
	}

	public void setDateMonth(String dateMonth) {
		this.dateMonth = dateMonth;
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

	public String getReportDate() {
		return reportDate;
	}

	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Profit [id=" + id + ", companyId=" + companyId + ", fileId="
				+ fileId + ", businessIncome=" + businessIncome
				+ ", interestIncome=" + interestIncome
				+ ", financialInterestIncome=" + financialInterestIncome
				+ ", feeIncome=" + feeIncome + ", changeIncome=" + changeIncome
				+ ", aggregateIncome=" + aggregateIncome + ", otherIncome="
				+ otherIncome + ", businessExpenses=" + businessExpenses
				+ ", interestExpense=" + interestExpense
				+ ", financialInterestExpense=" + financialInterestExpense
				+ ", feeExpense=" + feeExpense + ", managementFees="
				+ managementFees + ", exchangeLoss=" + exchangeLoss
				+ ", assetsLoss=" + assetsLoss + ", otherExpenses="
				+ otherExpenses + ", businessTax=" + businessTax
				+ ", operatingIncome=" + operatingIncome
				+ ", investmentIncome=" + investmentIncome
				+ ", nonOperatingIncome=" + nonOperatingIncome
				+ ", nonOperatingPayment=" + nonOperatingPayment
				+ ", totalProfit=" + totalProfit + ", incomeTax=" + incomeTax
				+ ", netProfit=" + netProfit + ", dateMonth=" + dateMonth
				+ ", leader=" + leader + ", informant=" + informant
				+ ", reportDate=" + reportDate + ", flag=" + flag + "]";
	}

}
