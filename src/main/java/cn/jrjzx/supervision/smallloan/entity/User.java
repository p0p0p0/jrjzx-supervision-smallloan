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
 * User
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月9日 下午2:19:10
 * 
 * @version 1.0.0
 *
 */
@Table(name="user")
public class User extends BaseEntity {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String realName;
	private String username;
	private String password;
	private Integer companyId;
	private String mobile;
	private String phone;
	private String email;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	
}
