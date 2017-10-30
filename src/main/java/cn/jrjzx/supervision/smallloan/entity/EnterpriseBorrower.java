/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.entity
 * User.java
 * 
 * 2017年6月9日-下午2:19:10
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * 借款客户（企业）
 * 
 * @date 2017年6月29日 
 * 
 * @version 1.0.0
 *
 */
@Table(name="enterprise_borrower")
public class EnterpriseBorrower extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer scale;
	private Integer industryType;
	private Integer industryInvolved;
	private String legalPerson;
	private String foundDate;
	private String organizationCode;
	private String registCode;
	private String registOffice;
	private String registDate;
	private String nationalTaxCode;
	private String landTaxCode;
	private String licenseCode;//营业执照编号/统一社会信用代码(唯一索引)
	private String licenseEndDate;
	private String controller;
	private String reallyCapital;
	private String linkman;
	private String phone;
	private String email;
	private String website;
	private String registerAddress;
	private String address;
	private String businessScope;
	private String reqId;
	private Long companyId;//（索引）
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getScale() {
		return scale;
	}
	public void setScale(Integer scale) {
		this.scale = scale;
	}
	public Integer getIndustryType() {
		return industryType;
	}
	public void setIndustryType(Integer industryType) {
		this.industryType = industryType;
	}
	public Integer getIndustryInvolved() {
		return industryInvolved;
	}
	public void setIndustryInvolved(Integer industryInvolved) {
		this.industryInvolved = industryInvolved;
	}
	public String getLegalPerson() {
		return legalPerson;
	}
	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}
	public String getFoundDate() {
		return foundDate;
	}
	public void setFoundDate(String foundDate) {
		this.foundDate = foundDate;
	}
	public String getOrganizationCode() {
		return organizationCode;
	}
	public void setOrganizationCode(String organizationCode) {
		this.organizationCode = organizationCode;
	}
	public String getRegistCode() {
		return registCode;
	}
	public void setRegistCode(String registCode) {
		this.registCode = registCode;
	}
	public String getRegistOffice() {
		return registOffice;
	}
	public void setRegistOffice(String registOffice) {
		this.registOffice = registOffice;
	}
	public String getRegistDate() {
		return registDate;
	}
	public void setRegistDate(String registDate) {
		this.registDate = registDate;
	}
	public String getNationalTaxCode() {
		return nationalTaxCode;
	}
	public void setNationalTaxCode(String nationalTaxCode) {
		this.nationalTaxCode = nationalTaxCode;
	}
	public String getLandTaxCode() {
		return landTaxCode;
	}
	public void setLandTaxCode(String landTaxCode) {
		this.landTaxCode = landTaxCode;
	}
	public String getLicenseCode() {
		return licenseCode;
	}
	public void setLicenseCode(String licenseCode) {
		this.licenseCode = licenseCode;
	}
	public String getLicenseEndDate() {
		return licenseEndDate;
	}
	public void setLicenseEndDate(String licenseEndDate) {
		this.licenseEndDate = licenseEndDate;
	}
	public String getController() {
		return controller;
	}
	public void setController(String controller) {
		this.controller = controller;
	}
	public String getReallyCapital() {
		return reallyCapital;
	}
	public void setReallyCapital(String reallyCapital) {
		this.reallyCapital = reallyCapital;
	}
	public String getLinkman() {
		return linkman;
	}
	public void setLinkman(String linkman) {
		this.linkman = linkman;
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
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getRegisterAddress() {
		return registerAddress;
	}
	public void setRegisterAddress(String registerAddress) {
		this.registerAddress = registerAddress;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBusinessScope() {
		return businessScope;
	}
	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	public Long getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
}
