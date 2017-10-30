package cn.jrjzx.supervision.smallloan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
/**
 * 学习经历 
 *
 */
@Table(name = "learning_exp")
public class LearningExp implements Serializable {
	private static final long serialVersionUID = -795207549765071842L;
	@Id
	private int id;
	// 学校，培训机构
	private String school;
	// 专业
	private String professional;
	// 开始时间
	private Date startTime;
	// 结束时间
	private Date endTime;
	// 学历
	private String qualifications;
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

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getProfessional() {
		return professional;
	}

	public void setProfessional(String professional) {
		this.professional = professional;
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

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
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
		return "LearningExp [id=" + id + ", school=" + school
				+ ", professional=" + professional + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", qualifications=" + qualifications
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
		LearningExp other = (LearningExp) obj;
		if (id != other.id)
			return false;
		return true;
	}

}
