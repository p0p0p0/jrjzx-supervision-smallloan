/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.entity
 * MonthFinance.java
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
 * MonthFinance
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年7月11日 下午2:15:56
 * 
 * @version 1.0.0
 *
 */
@Table(name="month_finance")
public class MonthFinance extends BaseEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer companyId;
	private String financeMonth;
	private Double balance;
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
	public String getFinanceMonth() {
		return financeMonth;
	}
	public void setFinanceMonth(String financeMonth) {
		this.financeMonth = financeMonth;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	public String getReqId() {
		return reqId;
	}
	public void setReqId(String reqId) {
		this.reqId = reqId;
	}
	
}
