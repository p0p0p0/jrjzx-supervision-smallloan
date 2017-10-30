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
 * 借款客户（个人）
 * 
 * @date 2017年6月29日 
 * 
 * @version 1.0.0
 *
 */
@Table(name="person_borrower")
public class PersonBorrower extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;//借款人姓名
	private Integer sex;//性别（1男/2女）
	private String cardType;//证件类型
	private String cardNumber;//证件号码（索引）
	private String phone;//电话
	private String email;//邮箱
	private String nationality;//国籍
	private String nation;//民族
	private String nativePlace;//籍贯
	private String birthday;//出生日期（varchar类型，yyyy-MM-dd）
	private Integer isFarmer;//是农牧民（0：否，1：是）
	private Integer education;//学历（1：博士，2：硕士，3：本科，4：专科，5：高中及以下）
	private Integer isMarry;//婚姻状况，1：已婚，0：未婚
	private String workUnit;//工作单位名称
	private String position;//职务
	private String postCode;//邮编
	private String registeredAddress;//户口所在地
	private String address;//固定住址
	private String reqId;//第三方ID（索引）
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
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
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
	public String getNationality() {
		return nationality;
	}
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public Integer getIsFarmer() {
		return isFarmer;
	}
	public void setIsFarmer(Integer isFarmer) {
		this.isFarmer = isFarmer;
	}
	public Integer getEducation() {
		return education;
	}
	public void setEducation(Integer education) {
		this.education = education;
	}
	public Integer getIsMarry() {
		return isMarry;
	}
	public void setIsMarry(Integer isMarry) {
		this.isMarry = isMarry;
	}
	public String getWorkUnit() {
		return workUnit;
	}
	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	public String getRegisteredAddress() {
		return registeredAddress;
	}
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
