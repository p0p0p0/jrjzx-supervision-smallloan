package cn.jrjzx.supervision.smallloan.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * 保证信息
 * 
 * @date 2017年6月29日 下午4:15:43
 * 
 * @version 1.0.0
 *
 */
@Table(name="ensure_contract")
public class EnsureContract extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;//保证人姓名/企业名称
	private Integer cardType;//证件类型;
	private String cardNumber;//证件号码/营业执照编号
	private String address;//联系地址/注册地址
	private Long loanContractId;//贷款合同id（索引）
	private String reqId;//第三方记录id（索引）
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
	public Integer getCardType() {
		return cardType;
	}
	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Long getLoanContractId() {
		return loanContractId;
	}
	public void setLoanContractId(Long loanContractId) {
		this.loanContractId = loanContractId;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	@Override
	public String toString() {
		return "EnsureContract [name=" + name + ", cardType=" + cardType + ", cardNumber=" + cardNumber + ", address="
				+ address + "]";
	}
}
