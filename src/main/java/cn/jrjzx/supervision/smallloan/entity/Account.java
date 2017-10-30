package cn.jrjzx.supervision.smallloan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * 
 * 银行账户信息
 */
@Table(name = "account")
public class Account implements Serializable {
	private static final long serialVersionUID = -4074232731808654331L;
	@Id
	private int id;
	private int companyId;// 机构基本信息id
	private int type;// 账户类型1.基本户，2.一般户，3.监管专用户
	private String bank;// 银行名称
	private String account;// 账号
	private String accountStatus;// 账户状态
	private Date openTime;// 开户日期
	private int flag = 1;// 删除标识
	private Date updateTime;// 更新时间
	private Date createTime;// 创建日期
	@Transient
	private DataOption bankOption;// 银行名称的数据字典选项
	@Transient
	private DataOption statusOption;// 账户状态的数据字典选项

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DataOption getStatusOption() {
		return statusOption;
	}

	public void setStatusOption(DataOption statusOption) {
		this.statusOption = statusOption;
	}

	public DataOption getBankOption() {
		return bankOption;
	}

	public void setBankOption(DataOption bankOption) {
		this.bankOption = bankOption;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
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
		return "Account [id=" + id + ", companyId=" + companyId + ", type="
				+ type + ", bank=" + bank + ", account=" + account
				+ ", accountStatus=" + accountStatus + ", openTime=" + openTime
				+ ", flag=" + flag + ", updateTime=" + updateTime
				+ ", createTime=" + createTime + "]";
	}

}
