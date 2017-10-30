/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.entity
 * BaseEntity.java
 * 
 * 2017年6月9日-下午2:17:40
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.entity;

import java.util.Date;

import javax.persistence.Column;

import org.apache.ibatis.annotations.Update;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 *
 * BaseEntity
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月9日 下午2:17:40
 * 
 * @version 1.0.0
 *
 */
public class BaseEntity {
	

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@Column(updatable=true,name="update_time")
	private Date updateTime;
	private Integer flag;//是否有效，0：删除，1：有效
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
	
}
