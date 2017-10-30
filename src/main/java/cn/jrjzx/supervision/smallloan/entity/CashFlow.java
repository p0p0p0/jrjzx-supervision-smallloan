package cn.jrjzx.supervision.smallloan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 现金流量表 
 *
 */
@Table(name = "cash_flow")
public class CashFlow implements Serializable {
	private static final long serialVersionUID = -1767997149810713324L;
	@Id
	private int id;
	// 小贷公司id
	private int companyId;
	// 收到利息、劳务费用现金
	private String money;
	// 所属月份
	private String dateMonth;
	// 创建时间
	private Date createTime;
	// 删除标识
	private int flag=1;

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

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getDateMonth() {
		return dateMonth;
	}

	public void setDateMonth(String dateMonth) {
		this.dateMonth = dateMonth;
	}

	@Override
	public String toString() {
		return "CashFlow [id=" + id + ", companyId=" + companyId + ", money="
				+ money + ", dateMonth=" + dateMonth + "]";
	}

}
