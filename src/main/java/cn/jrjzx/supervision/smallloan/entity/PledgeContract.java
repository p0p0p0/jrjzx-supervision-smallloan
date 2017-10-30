package cn.jrjzx.supervision.smallloan.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * 质押信息
 * 
 * @date 2017年6月29日 下午4:15:43
 * 
 * @version 1.0.0
 *
 */
@Table(name="pledge_contract")
public class PledgeContract extends BaseEntity{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;//质押物名称
	private String unit;//数据及单位
	private String address;//存放地
	private Integer pledgeType;//质押物类型
	private Double worth;//评估价值（元）
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getPledgeType() {
		return pledgeType;
	}
	public void setPledgeType(Integer pledgeType) {
		this.pledgeType = pledgeType;
	}
	public Double getWorth() {
		return worth;
	}
	public void setWorth(Double worth) {
		this.worth = worth;
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
		return "PledgeContract [name=" + name + ", unit=" + unit + ", address=" + address + ", pledgeType=" + pledgeType
				+ ", worth=" + worth + "]";
	}
}
