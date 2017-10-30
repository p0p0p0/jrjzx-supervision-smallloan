package cn.jrjzx.supervision.smallloan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 股东信息
 */
@Table(name = "partner")
public class Partner implements Serializable {
	private static final long serialVersionUID = 1944129654090356824L;
	@Id
	@GeneratedValue(generator = "JDBC")
	// 保存数据库后获取当前的id值
	private int id;
	// 股东类型，机构股东或自然人股东
	private int partnerType;
	// 机构id
	private int companyId;
	// 股东名称
	private String name;
	// 出资额
	private double capitalAmount;
	// 出资比例
	private double capitalProportion;
	// 持股开始日期
	private Date shareStart;
	// 持股结束日期
	private Date shareEnd;
	// 电话
	private String phone;
	// 邮箱
	private String email;
	// 通讯地址
	private String address;
	// 机构股东，法定代表人
	private String companyLegalPerson;
	// 机构股东，营业执照编号
	private String companyLicenseCode;
	// 机构股东，统一信用代码
	private String companyCreditCode;
	// 机构股东，机构类型，该字段取消
	private int companyType;
	// 机构股东，是否上市
	private int companyListed;
	// 机构股东，是否国企
	private int companyStateOwned;
	// 机构股东，传真
	private String companyFax;
	// 机构股东，经营范围
	private String companyBusiScope;
	// 机构股东，经营地址
	private String companyBusiAddress;
	// 机构股东，注册地址
	private String companyRegistAddress;
	// 自然人股东，性别
	private int personSex;
	// 自然人股东，证件类型，数据字典id
	private String personCardType;
	// 自然人股东，证件号
	private String personCardNumber;
	// 自然人股东，户口地址
	private String personAddress;
	// 自然人股东，工作单位
	private String personWorkUnit;
	private int status;// 记录状态(0待审核，1正常)
	// 逻辑删除标识
	private int flag = 1;
	// 更新时间,mysql自动填充
	private Date updateTime;
	// 创建时间
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCompanyFax() {
		return companyFax;
	}

	public void setCompanyFax(String companyFax) {
		this.companyFax = companyFax;
	}

	public int getCompanyListed() {
		return companyListed;
	}

	public void setCompanyListed(int companyListed) {
		this.companyListed = companyListed;
	}

	public int getCompanyStateOwned() {
		return companyStateOwned;
	}

	public void setCompanyStateOwned(int companyStateOwned) {
		this.companyStateOwned = companyStateOwned;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getShareStart() {
		return shareStart;
	}

	public void setShareStart(Date shareStart) {
		this.shareStart = shareStart;
	}

	public Date getShareEnd() {
		return shareEnd;
	}

	public void setShareEnd(Date shareEnd) {
		this.shareEnd = shareEnd;
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

	public String getCompanyLegalPerson() {
		return companyLegalPerson;
	}

	public void setCompanyLegalPerson(String companyLegalPerson) {
		this.companyLegalPerson = companyLegalPerson;
	}

	public String getCompanyLicenseCode() {
		return companyLicenseCode;
	}

	public void setCompanyLicenseCode(String companyLicenseCode) {
		this.companyLicenseCode = companyLicenseCode;
	}

	public String getCompanyCreditCode() {
		return companyCreditCode;
	}

	public void setCompanyCreditCode(String companyCreditCode) {
		this.companyCreditCode = companyCreditCode;
	}

	public int getCompanyType() {
		return companyType;
	}

	public void setCompanyType(int companyType) {
		this.companyType = companyType;
	}

	public String getCompanyBusiScope() {
		return companyBusiScope;
	}

	public void setCompanyBusiScope(String companyBusiScope) {
		this.companyBusiScope = companyBusiScope;
	}

	public String getCompanyBusiAddress() {
		return companyBusiAddress;
	}

	public void setCompanyBusiAddress(String companyBusiAddress) {
		this.companyBusiAddress = companyBusiAddress;
	}

	public String getCompanyRegistAddress() {
		return companyRegistAddress;
	}

	public void setCompanyRegistAddress(String companyRegistAddress) {
		this.companyRegistAddress = companyRegistAddress;
	}

	public int getPersonSex() {
		return personSex;
	}

	public void setPersonSex(int personSex) {
		this.personSex = personSex;
	}

	public String getPersonCardType() {
		return personCardType;
	}

	public void setPersonCardType(String personCardType) {
		this.personCardType = personCardType;
	}

	public String getPersonCardNumber() {
		return personCardNumber;
	}

	public void setPersonCardNumber(String personCardNumber) {
		this.personCardNumber = personCardNumber;
	}

	public String getPersonAddress() {
		return personAddress;
	}

	public void setPersonAddress(String personAddress) {
		this.personAddress = personAddress;
	}

	public String getPersonWorkUnit() {
		return personWorkUnit;
	}

	public void setPersonWorkUnit(String personWorkUnit) {
		this.personWorkUnit = personWorkUnit;
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

	public int getPartnerType() {
		return partnerType;
	}

	public void setPartnerType(int partnerType) {
		this.partnerType = partnerType;
	}

	public double getCapitalAmount() {
		return capitalAmount;
	}

	public void setCapitalAmount(double capitalAmount) {
		this.capitalAmount = capitalAmount;
	}

	public double getCapitalProportion() {
		return capitalProportion;
	}

	public void setCapitalProportion(double capitalProportion) {
		this.capitalProportion = capitalProportion;
	}

	@Override
	public String toString() {
		return "Partner [id=" + id + ", partnerType=" + partnerType
				+ ", companyId=" + companyId + ", name=" + name
				+ ", capitalAmount=" + capitalAmount + ", capitalProportion="
				+ capitalProportion + ", shareStart=" + shareStart
				+ ", shareEnd=" + shareEnd + ", phone=" + phone + ", email="
				+ email + ", address=" + address + ", companyLegalPerson="
				+ companyLegalPerson + ", companyLicenseCode="
				+ companyLicenseCode + ", companyCreditCode="
				+ companyCreditCode + ", companyType=" + companyType
				+ ", companyBusiScope=" + companyBusiScope
				+ ", companyBusiAddress=" + companyBusiAddress
				+ ", companyRegistAddress=" + companyRegistAddress
				+ ", personSex=" + personSex + ", personCardType="
				+ personCardType + ", personCardNumber=" + personCardNumber
				+ ", personAddress=" + personAddress + ", personWorkUnit="
				+ personWorkUnit + ", flag=" + flag + ", updateTime="
				+ updateTime + ", createTime=" + createTime + "]";
	}

}
