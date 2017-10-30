package cn.jrjzx.supervision.smallloan.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 人员信息 
 *
 */
@Table(name = "employees")
public class Employees implements Serializable {
	private static final long serialVersionUID = 1293357375019058352L;
	@Id
	@GeneratedValue(generator = "JDBC")
	// 保存数据库后获取当前的id值
	private int id;
	// 姓名
	private String name;
	// 性别
	private int sex;
	// 证件类型,数据字典
	private String cardType;
	// 证件号
	private String cardNumber;
	// 户口所在地
	private String residenceAddress;
	// 现任职位
	private String position;
	// 职务类型（1~50高管-50~99其他从业人员）
	private int positionType;
	// 学历(1博士，2硕士，3本科，4大专，5高中以下)
	private int education;
	// 部门
	private String department;
	// 电话
	private String phone;
	// 邮箱
	private String email;
	// 通信地址
	private String address;
	// 技能职称
	private String title;
	// 入职时间
	private Date entryTime;
	// 离职时间
	private Date quitTime;
	// 报备日期
	private Date signTime;
	// 是否在职
	private int isonjob;
	// 记录更新时间,mysql自动填充
	private Date updateTime;
	// 逻辑删除标识
	private int flag=1;
	// 机构id
	private int companyId;
	@Transient
	private List<LearningExp> learningExps;
	@Transient
	private List<WorkExp> workExps;
	@Transient
	private List<Family> familys;

	public List<Family> getFamilys() {
		return familys;
	}

	public void setFamilys(List<Family> familys) {
		this.familys = familys;
	}

	public List<WorkExp> getWorkExps() {
		return workExps;
	}

	public void setWorkExps(List<WorkExp> workExps) {
		this.workExps = workExps;
	}

	public List<LearningExp> getLearningExps() {
		return learningExps;
	}

	public void setLearningExps(List<LearningExp> learningExps) {
		this.learningExps = learningExps;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getResidenceAddress() {
		return residenceAddress;
	}

	public void setResidenceAddress(String residenceAddress) {
		this.residenceAddress = residenceAddress;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getPositionType() {
		return positionType;
	}

	public void setPositionType(int positionType) {
		this.positionType = positionType;
	}

	public int getEducation() {
		return education;
	}

	public void setEducation(int education) {
		this.education = education;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	public Date getQuitTime() {
		return quitTime;
	}

	public void setQuitTime(Date quitTime) {
		this.quitTime = quitTime;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public int getIsonjob() {
		return isonjob;
	}

	public void setIsonjob(int isonjob) {
		this.isonjob = isonjob;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	@Override
	public String toString() {
		return "Employees [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", cardType=" + cardType + ", cardNumber=" + cardNumber
				+ ", residenceAddress=" + residenceAddress + ", position="
				+ position + ", positionType=" + positionType + ", education="
				+ education + ", phone=" + phone + ", email=" + email
				+ ", address=" + address + ", title=" + title + ", entryTime="
				+ entryTime + ", quitTime=" + quitTime + ", signTime="
				+ signTime + ", isonjob=" + isonjob + ", updateTime="
				+ updateTime + ", flag=" + flag + ", companyId=" + companyId
				+ "]";
	}

}
