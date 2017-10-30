package cn.jrjzx.supervision.smallloan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 股东变更记录
 */
@Table(name = "partner_change_log")
public class PartnerChangeLog implements Serializable {
	private static final long serialVersionUID = 3722681910754781196L;
	@Id
	private int id;
	// 变动类型
	private int changeType;
	// 变动名称
	private String aliasName;
	// 变更后的值
	private String afterValue;
	// 变更前的值
	private String beforeValue;
	// 股东id
	private int partnerId;
	// 记录创建日期,mysql自动填充
	private Date createTime;
	// 逻辑删除标识
	private int flag = 1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getChangeType() {
		return changeType;
	}

	public void setChangeType(int changeType) {
		this.changeType = changeType;
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

	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
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
		return "PartnerChangeLog [id=" + id + ", changeType=" + changeType
				+ ", afterValue=" + afterValue + ", beforeValue=" + beforeValue
				+ ", partnerId=" + partnerId + ", createTime=" + createTime
				+ ", flag=" + flag + "]";
	}

}
