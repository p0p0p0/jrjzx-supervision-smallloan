package cn.jrjzx.supervision.smallloan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 银行余额
 *
 */
@Table(name = "bank_balance")
public class BankBalance implements Serializable {
	private static final long serialVersionUID = 880451526490572229L;
	@Id
	private int id;
	private int accountId;// 账户信息id
	private int companyId;// 机构信息id
	private String dateMonth;// 所属月份
	private String money;// 余额
	private int flag = 1;// 删除标识
	private Date updateTime;// 更新时间
	private Date createTime;// 创建时间
	@Transient
	private Account account;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getDateMonth() {
		return dateMonth;
	}

	public void setDateMonth(String dateMonth) {
		this.dateMonth = dateMonth;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
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

	@Override
	public String toString() {
		return "BankBalance [id=" + id + ", accountId=" + accountId
				+ ", companyId=" + companyId + ", dateMonth=" + dateMonth
				+ ", money=" + money + ", flag=" + flag + ", updateTime="
				+ updateTime + ", createTime=" + createTime + "]";
	}

}
