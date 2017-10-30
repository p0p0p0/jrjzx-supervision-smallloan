package cn.jrjzx.supervision.smallloan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 高管变更记录 
 *
 */
@Table(name = "employees_change_log")
public class EmployeesChangeLog implements Serializable {

	private static final long serialVersionUID = -5695412840664722816L;
	@Id
	private int id;
	// 变动类型(离职，入职，停职...)
	private String changeType;
	// 变动名称
	private String aliasName;
	// 创建日期，mysql自动填充
	private Date createTime;
	// 变更后的值
	private String afterValue;
	// 变更前的值
	private String beforeValue;
	// 机构id
	private int companyId;
	// 人员id
	private int employeeId;
	// 逻辑删除标识
	private int flag = 1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAfterValue() {
		return afterValue;
	}

	public void setAfterValue(String afterValue) {
		this.afterValue = afterValue;
	}

	public String getBeforeValue() {
		return beforeValue;
	}

	public void setBeforeValue(String beforeValue) {
		this.beforeValue = beforeValue;
	}

	public String getChangeType() {
		return changeType;
	}

	public void setChangeType(String changeType) {
		this.changeType = changeType;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
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

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "EmployeesChangeLog [id=" + id + ", changeType=" + changeType
				+ ", createTime=" + createTime + ", companyId=" + companyId
				+ ", employeeId=" + employeeId + ", flag=" + flag + "]";
	}

}
