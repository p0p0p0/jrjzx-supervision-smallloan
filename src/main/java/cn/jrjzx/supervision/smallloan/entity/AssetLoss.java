package cn.jrjzx.supervision.smallloan.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 资产损失准备计提
 *
 */
@Table(name = "asset_loss")
public class AssetLoss implements Serializable {

	private static final long serialVersionUID = 6987547247453402725L;
	@Id
	private int id;
	private int companyId; // 机构id
	private String dateMonth;// 所属月份
	// 五级分类-正常类
	private String normalBalance;// 正常类贷款余额
	private String normalRate;// 正常类贷款计提比例
	private String normalAccrued;// 正常类贷款应计金额
	private String normalReal;// 正常类贷款实计金额
	private String normalAdequacy;// 正常类贷款充足率
	// 五级分类-关注类
	private String followBalance;// 关注类贷款余额
	private String followRate;// 关注类贷款计提比例
	private String followAccrued;// 关注类贷款应计金额
	private String followReal;// 关注类贷款实计金额
	private String followAdequacy;// 关注类贷款充足率
	// 五级分类-次级类
	private String minorBalance;// 次级类贷款余额
	private String minorRate;// 次级类贷款计提比例
	private String minorAccrued;// 次级类贷款应计金额
	private String minorReal;// 次级类贷款实计金额
	private String minorAdequacy;// 次级类贷款充足率
	// 五级分类-可疑类
	private String suspiciousBalance;// 可疑类贷款余额
	private String suspiciousRate;// 可疑类贷款计提比例
	private String suspiciousAccrued;// 可疑类贷款应计金额
	private String suspiciousReal;// 可疑类贷款实计金额
	private String suspiciousAdequacy;// 可疑类贷款充足率
	// 五级分类-损失类
	private String lossBalance;// 损失类贷款余额
	private String lossRate;// 损失类贷款计提比例
	private String lossAccrued;// 损失类贷款应计金额
	private String lossReal;// 损失类贷款实计金额
	private String lossAdequacy;// 损失类贷款充足率
	// 五级分类-合计
	private String totalBalance;// 合计贷款余额
	private String totalAccrued;// 合计贷款应计金额
	private String totalReal;// 合计贷款实计金额
	private String totalAdequacy;// 合计贷款充足率
	private int flag = 1;// 逻辑删除标识
	private Date createTime;// 创建时间
	private Date updateTime;// 更新时间

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

	public String getDateMonth() {
		return dateMonth;
	}

	public void setDateMonth(String dateMonth) {
		this.dateMonth = dateMonth;
	}

	public String getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(String totalBalance) {
		this.totalBalance = totalBalance;
	}

	public String getTotalAccrued() {
		return totalAccrued;
	}

	public void setTotalAccrued(String totalAccrued) {
		this.totalAccrued = totalAccrued;
	}

	public String getTotalReal() {
		return totalReal;
	}

	public void setTotalReal(String totalReal) {
		this.totalReal = totalReal;
	}

	public String getTotalAdequacy() {
		return totalAdequacy;
	}

	public void setTotalAdequacy(String totalAdequacy) {
		this.totalAdequacy = totalAdequacy;
	}

	public String getNormalBalance() {
		return normalBalance;
	}

	public void setNormalBalance(String normalBalance) {
		this.normalBalance = normalBalance;
	}

	public String getNormalRate() {
		return normalRate;
	}

	public void setNormalRate(String normalRate) {
		this.normalRate = normalRate;
	}

	public String getNormalAccrued() {
		return normalAccrued;
	}

	public void setNormalAccrued(String normalAccrued) {
		this.normalAccrued = normalAccrued;
	}

	public String getNormalReal() {
		return normalReal;
	}

	public void setNormalReal(String normalReal) {
		this.normalReal = normalReal;
	}

	public String getNormalAdequacy() {
		return normalAdequacy;
	}

	public void setNormalAdequacy(String normalAdequacy) {
		this.normalAdequacy = normalAdequacy;
	}

	public String getFollowBalance() {
		return followBalance;
	}

	public void setFollowBalance(String followBalance) {
		this.followBalance = followBalance;
	}

	public String getFollowRate() {
		return followRate;
	}

	public void setFollowRate(String followRate) {
		this.followRate = followRate;
	}

	public String getFollowAccrued() {
		return followAccrued;
	}

	public void setFollowAccrued(String followAccrued) {
		this.followAccrued = followAccrued;
	}

	public String getFollowReal() {
		return followReal;
	}

	public void setFollowReal(String followReal) {
		this.followReal = followReal;
	}

	public String getFollowAdequacy() {
		return followAdequacy;
	}

	public void setFollowAdequacy(String followAdequacy) {
		this.followAdequacy = followAdequacy;
	}

	public String getMinorBalance() {
		return minorBalance;
	}

	public void setMinorBalance(String minorBalance) {
		this.minorBalance = minorBalance;
	}

	public String getMinorRate() {
		return minorRate;
	}

	public void setMinorRate(String minorRate) {
		this.minorRate = minorRate;
	}

	public String getMinorAccrued() {
		return minorAccrued;
	}

	public void setMinorAccrued(String minorAccrued) {
		this.minorAccrued = minorAccrued;
	}

	public String getMinorReal() {
		return minorReal;
	}

	public void setMinorReal(String minorReal) {
		this.minorReal = minorReal;
	}

	public String getMinorAdequacy() {
		return minorAdequacy;
	}

	public void setMinorAdequacy(String minorAdequacy) {
		this.minorAdequacy = minorAdequacy;
	}

	public String getSuspiciousBalance() {
		return suspiciousBalance;
	}

	public void setSuspiciousBalance(String suspiciousBalance) {
		this.suspiciousBalance = suspiciousBalance;
	}

	public String getSuspiciousRate() {
		return suspiciousRate;
	}

	public void setSuspiciousRate(String suspiciousRate) {
		this.suspiciousRate = suspiciousRate;
	}

	public String getSuspiciousAccrued() {
		return suspiciousAccrued;
	}

	public void setSuspiciousAccrued(String suspiciousAccrued) {
		this.suspiciousAccrued = suspiciousAccrued;
	}

	public String getSuspiciousReal() {
		return suspiciousReal;
	}

	public void setSuspiciousReal(String suspiciousReal) {
		this.suspiciousReal = suspiciousReal;
	}

	public String getSuspiciousAdequacy() {
		return suspiciousAdequacy;
	}

	public void setSuspiciousAdequacy(String suspiciousAdequacy) {
		this.suspiciousAdequacy = suspiciousAdequacy;
	}

	public String getLossBalance() {
		return lossBalance;
	}

	public void setLossBalance(String lossBalance) {
		this.lossBalance = lossBalance;
	}

	public String getLossRate() {
		return lossRate;
	}

	public void setLossRate(String lossRate) {
		this.lossRate = lossRate;
	}

	public String getLossAccrued() {
		return lossAccrued;
	}

	public void setLossAccrued(String lossAccrued) {
		this.lossAccrued = lossAccrued;
	}

	public String getLossReal() {
		return lossReal;
	}

	public void setLossReal(String lossReal) {
		this.lossReal = lossReal;
	}

	public String getLossAdequacy() {
		return lossAdequacy;
	}

	public void setLossAdequacy(String lossAdequacy) {
		this.lossAdequacy = lossAdequacy;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

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

	@Override
	public String toString() {
		return "AssetLoss [id=" + id + ", companyId=" + companyId
				+ ", dateMonth=" + dateMonth + ", normalBalance="
				+ normalBalance + ", normalRate=" + normalRate
				+ ", normalAccrued=" + normalAccrued + ", normalReal="
				+ normalReal + ", normalAdequacy=" + normalAdequacy
				+ ", followBalance=" + followBalance + ", followRate="
				+ followRate + ", followAccrued=" + followAccrued
				+ ", followReal=" + followReal + ", followAdequacy="
				+ followAdequacy + ", minorBalance=" + minorBalance
				+ ", minorRate=" + minorRate + ", minorAccrued=" + minorAccrued
				+ ", minorReal=" + minorReal + ", minorAdequacy="
				+ minorAdequacy + ", suspiciousBalance=" + suspiciousBalance
				+ ", suspiciousRate=" + suspiciousRate + ", suspiciousAccrued="
				+ suspiciousAccrued + ", suspiciousReal=" + suspiciousReal
				+ ", suspiciousAdequacy=" + suspiciousAdequacy
				+ ", lossBalance=" + lossBalance + ", lossRate=" + lossRate
				+ ", lossAccrued=" + lossAccrued + ", lossReal=" + lossReal
				+ ", lossAdequacy=" + lossAdequacy + ", flag=" + flag
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}

}
