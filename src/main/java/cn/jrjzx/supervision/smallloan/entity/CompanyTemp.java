package cn.jrjzx.supervision.smallloan.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * 机构变更审核临时表 
 *
 */
@Table(name = "company_temp")
public class CompanyTemp implements Serializable {

	private static final long serialVersionUID = 2370546398062773424L;
	@Id
	private int id;
	private int companyId;
	private String name;// 机构名称
	private String registCapital;// 注册资金
	private Date openTime;// 开业时间
	private Date foundTime;// 成立时间
	private double acreage;// 场地面积
	private Date signTime;// 报备日期
	private String legalPerson;// 法人
	private int orgForm;// 组织形式1.股份有限公司2.有限责任公司
	private String linkman;// 联系人
	private String tel;// 联系电话
	private String fax;// 传真
	private String address;// 营业地址
	private String busiScope;// 经营范围
	private String orgCode;// 组织机构代码
	private String landTaxCode;// 地税编码
	private String nationalTaxCode;// 国税编码
	private String creditCode;// 统一信用代码
	private String licenseCode;// 营业执照编号
	private int isInternet;// 是否互联网小贷
	private Date updateTime;// 更新时间
	private Date createTime;// 创建时间
	private int flag;// 逻辑删除标识(0删除，1正常)
	@Transient
	private List<Partner> partners;

	public List<Partner> getPartners() {
		return partners;
	}

	public void setPartners(List<Partner> partners) {
		this.partners = partners;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getRegistCapital() {
		return registCapital;
	}

	public void setRegistCapital(String registCapital) {
		this.registCapital = registCapital;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Date getFoundTime() {
		return foundTime;
	}

	public void setFoundTime(Date foundTime) {
		this.foundTime = foundTime;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getLandTaxCode() {
		return landTaxCode;
	}

	public void setLandTaxCode(String landTaxCode) {
		this.landTaxCode = landTaxCode;
	}

	public String getNationalTaxCode() {
		return nationalTaxCode;
	}

	public void setNationalTaxCode(String nationalTaxCode) {
		this.nationalTaxCode = nationalTaxCode;
	}

	public double getAcreage() {
		return acreage;
	}

	public void setAcreage(double acreage) {
		this.acreage = acreage;
	}

	public Date getSignTime() {
		return signTime;
	}

	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public int getOrgForm() {
		return orgForm;
	}

	public void setOrgForm(int orgForm) {
		this.orgForm = orgForm;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBusiScope() {
		return busiScope;
	}

	public void setBusiScope(String busiScope) {
		this.busiScope = busiScope;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
	}

	public String getLicenseCode() {
		return licenseCode;
	}

	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}

	public int getIsInternet() {
		return isInternet;
	}

	public void setIsInternet(int isInternet) {
		this.isInternet = isInternet;
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

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", registCapital="
				+ registCapital + ", openTime=" + openTime + ", foundTime="
				+ foundTime + ", acreage=" + acreage + ", signTime=" + signTime
				+ ", legalPerson=" + legalPerson + ", orgForm=" + orgForm
				+ ", linkman=" + linkman + ", tel=" + tel + ", fax=" + fax
				+ ", address=" + address + ", busiScope=" + busiScope
				+ ", creditCode=" + creditCode + ", licenseCode=" + licenseCode
				+ ", isInternet=" + isInternet + ", updateTime=" + updateTime
				+ ", createTime=" + createTime + ", flag=" + flag + "]";
	}

}
