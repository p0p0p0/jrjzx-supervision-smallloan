/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.entity
 * SingleFinance.java
 * 
 * 2017年7月11日-下午2:15:56
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
 * SingleFinance
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年7月11日 下午2:15:56
 * 
 * @version 1.0.0
 *
 */
@Table(name="single_finance")
public class SingleFinance extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer companyId;
	private String financeDate;
	private Double money;
	private String channel;
	private String endDate;
	private Double costRate;
	private Double rate;
	private Double guaranteeRate;
	private String reqId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getFinanceDate() {
		return financeDate;
	}
	public void setFinanceDate(String financeDate) {
		this.financeDate = financeDate;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Double getCostRate() {
		return costRate;
	}
	public void setCostRate(Double costRate) {
		this.costRate = costRate;
	}
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public Double getGuaranteeRate() {
		return guaranteeRate;
	}
	public void setGuaranteeRate(Double guaranteeRate) {
		this.guaranteeRate = guaranteeRate;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	
}
