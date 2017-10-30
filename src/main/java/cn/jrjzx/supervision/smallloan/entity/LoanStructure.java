package cn.jrjzx.supervision.smallloan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 贷款结构
 *
 */
@Table(name = "loan_structure")
public class LoanStructure implements Serializable {
	private static final long serialVersionUID = 8241575196821000246L;
	@Id
	private int id;
	private String balance="0.00";// 贷款余额(万元)
	private int categoryType;// 结果展示表中 1)借款主体，2)借款额度，3)借款行业 ...7)借款利率，等7大类别
	private int dataType; // 结果展示表中每个大类下面的数据，如借款行业类型下面共有1至13共13条数据
	private int monthCount;// 月累计投放笔数
	private String monthMoney="0.00";// 月累计投放金额(万元)
	private String monthRatio="0.00";// 月累计投放占比
	private Date searchDate;// 数据统计时间
	private int yearCount;// 年累计投放笔数
	private String yearMoney="0.00";// 月累计投放金额(万元)
	private String yearRatio="0.00";// 年累计投放占比
	private long companyId;// 机构id
	private Date updateTime;// 更新时间
	private Date createTime;// 创建时间
	private int flag = 1;// 逻辑删除标识
	@Transient
	private Company company;

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public int getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(int categoryType) {
		this.categoryType = categoryType;
	}

	public int getDataType() {
		return dataType;
	}

	public void setDataType(int dataType) {
		this.dataType = dataType;
	}

	public int getMonthCount() {
		return monthCount;
	}

	public void setMonthCount(int monthCount) {
		this.monthCount = monthCount;
	}

	public String getMonthMoney() {
		return monthMoney;
	}

	public void setMonthMoney(String monthMoney) {
		this.monthMoney = monthMoney;
	}

	public String getMonthRatio() {
		return monthRatio;
	}

	public void setMonthRatio(String monthRatio) {
		this.monthRatio = monthRatio;
	}

	public Date getSearchDate() {
		return searchDate;
	}

	public void setSearchDate(Date searchDate) {
		this.searchDate = searchDate;
	}

	public int getYearCount() {
		return yearCount;
	}

	public void setYearCount(int yearCount) {
		this.yearCount = yearCount;
	}

	public String getYearMoney() {
		return yearMoney;
	}

	public void setYearMoney(String yearMoney) {
		this.yearMoney = yearMoney;
	}

	public String getYearRatio() {
		return yearRatio;
	}

	public void setYearRatio(String yearRatio) {
		this.yearRatio = yearRatio;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "LoanStructure [id=" + id + ", balance=" + balance
				+ ", categoryType=" + categoryType + ", dataType=" + dataType
				+ ", monthCount=" + monthCount + ", monthMoney=" + monthMoney
				+ ", monthRatio=" + monthRatio + ", searchDate=" + searchDate
				+ ", yearCount=" + yearCount + ", yearMoney=" + yearMoney
				+ ", yearRatio=" + yearRatio + ", companyId=" + companyId
				+ ", updateTime=" + updateTime + ", createTime=" + createTime
				+ ", flag=" + flag + "]";
	}

}
