package cn.jrjzx.supervision.smallloan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 工作经历
 */
@Table(name = "work_exp")
public class WorkExp implements Serializable {
	private static final long serialVersionUID = -8551423847304766254L;
	@Id
	private int id;
	// 单位
	private String company;
	// 职位
	private String position;
	// 开始时间
	private Date startTime;
	// 结束时间
	private Date endTime;
	// 人员信息id
	private int employeesId;
	// 更新日期
	private Date updateTime;
	// 创建日期
	private Date createTime;
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

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getEmployeesId() {
		return employeesId;
	}

	public void setEmployeesId(int employeesId) {
		this.employeesId = employeesId;
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
		return "WorkExp [id=" + id + ", company=" + company + ", position="
				+ position + ", startTime=" + startTime + ", endTime="
				+ endTime + ", employeesId=" + employeesId + ", createTime="
				+ createTime + ", flag=" + flag + "]";
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
		WorkExp other = (WorkExp) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
