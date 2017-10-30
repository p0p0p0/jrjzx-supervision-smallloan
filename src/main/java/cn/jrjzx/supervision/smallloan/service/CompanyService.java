package cn.jrjzx.supervision.smallloan.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jrjzx.supervision.smallloan.common.util.ObjectUtil;
import cn.jrjzx.supervision.smallloan.entity.Company;
import cn.jrjzx.supervision.smallloan.entity.CompanyChangeLog;
import cn.jrjzx.supervision.smallloan.entity.CompanyTemp;
import cn.jrjzx.supervision.smallloan.entity.DataLexicon;
import cn.jrjzx.supervision.smallloan.entity.DataOption;

@Service
@Transactional
public class CompanyService extends ModelService<Company> {
	@Autowired
	DataLexiconService dataLexiconService;
	@Autowired
	CompanyChangeLogService companyChangeLogService;
	@Autowired
	CompanyTempService companyTempService;

	/**
	 * 保存待审核状态，当前数据只改状态，新记录保存到临时表，审核通过后再更新
	 * 
	 * @param oval
	 * @param nval
	 */
	public void updateForReview(Company oval, Company nval) throws Exception {
		CompanyTemp temp = new CompanyTemp();
		temp.setCompanyId(oval.getId());
		temp.setName(nval.getName());
		temp.setLegalPerson(nval.getLegalPerson());
		temp.setRegistCapital(nval.getRegistCapital());
		temp.setOrgCode(nval.getOrgCode());
		temp.setNationalTaxCode(nval.getNationalTaxCode());
		temp.setLandTaxCode(nval.getLandTaxCode());
		temp.setOpenTime(nval.getOpenTime());
		temp.setFoundTime(nval.getFoundTime());
		temp.setLicenseCode(nval.getLicenseCode());
		temp.setCreditCode(nval.getCreditCode());
		temp.setIsInternet(nval.getIsInternet());
		temp.setOrgForm(nval.getOrgForm());
		temp.setAcreage(nval.getAcreage());
		temp.setFax(nval.getFax());
		temp.setLinkman(nval.getLinkman());
		temp.setTel(nval.getTel());
		temp.setAddress(nval.getAddress());
		temp.setBusiScope(nval.getBusiScope());
		companyTempService.saveSelective(temp);
		oval.setStatus(0);
		super.updateByIdSelective(oval);

	}

	/**
	 * 数据字典中设置了变动项这里就会保存相关变动项的记录。 数据字典设置规则，lexicon中code为company_change_type，
	 * option中key为字段名如：company.name、company.registCapital等，
	 * option中的value为变动项文字说明如：公司名称，公司法人
	 * 
	 * @param oval
	 *            旧的值
	 * @param nval
	 *            新的值
	 * @return
	 * @throws Exception
	 */
	public void updateForLog(Company oval, Company nval) throws Exception {
		// 取出数据字典设置的值
		DataLexicon lexicon = dataLexiconService
				.queryByCode("company_change_type");
		List<DataOption> list = new ArrayList<DataOption>();
		if (lexicon != null && lexicon.getDataOptions() != null) {
			list = lexicon.getDataOptions();
		}
		// 比较数据字典中设置的字段值
		List<CompanyChangeLog> changes = new ArrayList<CompanyChangeLog>();
		for (DataOption option : list) {
			try {
				String tem = option.getOptionKey();
				String fname = tem.substring(tem.indexOf(".") + 1);
				Field f = oval.getClass().getDeclaredField(fname);
				f.setAccessible(true);
				Object ov = f.get(oval);
				Object nv = f.get(nval);
				if (ObjectUtil.isEqual(ov, nv)) {
					// 一致则没变动
					continue;
				} else {
					// 不一致则变动，保存记录
					CompanyChangeLog clog = new CompanyChangeLog();
					clog.setAliasName(option.getOptionValue());
					clog.setBeforeValue(ov.toString());
					clog.setAfterValue(nv.toString());
					clog.setCompanyId(oval.getId());
					changes.add(clog);
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		companyChangeLogService.insertBatch(changes);// 批量插入变动记录
		// 提交的新对象复制到旧对象中保存
		oval.setName(nval.getName());
		oval.setLegalPerson(nval.getLegalPerson());
		oval.setRegistCapital(nval.getRegistCapital());
		oval.setOrgCode(nval.getOrgCode());
		oval.setNationalTaxCode(nval.getNationalTaxCode());
		oval.setLandTaxCode(nval.getLandTaxCode());
		oval.setOpenTime(nval.getOpenTime());
		oval.setFoundTime(nval.getFoundTime());
		oval.setLicenseCode(nval.getLicenseCode());
		oval.setCreditCode(nval.getCreditCode());
		oval.setIsInternet(nval.getIsInternet());
		oval.setOrgForm(nval.getOrgForm());
		oval.setAcreage(nval.getAcreage());
		oval.setFax(nval.getFax());
		oval.setLinkman(nval.getLinkman());
		oval.setTel(nval.getTel());
		oval.setAddress(nval.getAddress());
		oval.setBusiScope(nval.getBusiScope());
		super.updateByIdSelective(oval);
	}
}
