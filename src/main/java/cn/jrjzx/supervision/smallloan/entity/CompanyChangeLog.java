package cn.jrjzx.supervision.smallloan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 机构变更记录 
 *
 */
@Table(name = "company_change_log")
public class CompanyChangeLog implements Serializable {

	private static final long serialVersionUID = -5439648148098328664L;
	@Id
	private int id;
	// 变动类型，1公司名称、2法人、3注册资本、4经营地址、5经营范围、7组织形式
	private int type;
	// 变动名称，公司名称、注册资本、法人、经营地址、经营范围
	private String aliasName;
	// 变更后的值
	private String afterValue;
	// 变更前的值
	private String beforeValue;
	// 机构id
	private int companyId;
	// 创建日期
	private Date createTime;
	// 逻辑删除标识
	private int flag;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
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

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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
		return "CompanyChangeLog [id=" + id + ", type=" + type + ", aliasName="
				+ aliasName + ", afterValue=" + afterValue + ", beforeValue="
				+ beforeValue + ", companyId=" + companyId + ", createTime="
				+ createTime + ", flag=" + flag + "]";
	}

}
