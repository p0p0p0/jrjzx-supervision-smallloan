package cn.jrjzx.supervision.smallloan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 家庭成员信息 
 *
 */
@Table(name = "family")
public class Family implements Serializable {
	private static final long serialVersionUID = 1992452039472727585L;
	@Id
	private int id;
	// 姓名
	private String name;
	// 性别
	private String sex;
	// 关系
	private String relation;
	// 目前工作或学习单位
	private String curUnit;
	// 人员信息id
	private int employeesId;
	// 更新日期
	private Date updateTime;
	// 创建日期
	private int createTime;
	// 逻辑删除标识
	private int flag = 1;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getCurUnit() {
		return curUnit;
	}

	public void setCurUnit(String curUnit) {
		this.curUnit = curUnit;
	}

	public int getEmployeesId() {
		return employeesId;
	}

	public void setEmployeesId(int employeesId) {
		this.employeesId = employeesId;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
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
		return "Family [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", relation=" + relation + ", curUnit=" + curUnit
				+ ", employeesId=" + employeesId + ", createTime=" + createTime
				+ ", flag=" + flag + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Family other = (Family) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
