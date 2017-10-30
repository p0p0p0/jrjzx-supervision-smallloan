package cn.jrjzx.supervision.smallloan.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jrjzx.supervision.smallloan.common.util.ObjectUtil;
import cn.jrjzx.supervision.smallloan.entity.DataLexicon;
import cn.jrjzx.supervision.smallloan.entity.DataOption;
import cn.jrjzx.supervision.smallloan.entity.Partner;
import cn.jrjzx.supervision.smallloan.entity.PartnerChangeLog;
import cn.jrjzx.supervision.smallloan.entity.PartnerTemp;

@Service
@Transactional
public class PartnerService extends ModelService<Partner> {
	@Autowired
	DataLexiconService dataLexiconService;
	@Autowired
	PartnerChangeLogService partnerChangeLogService;
	@Autowired
	PartnerTempService partnerTempService;

	/**
	 * 保存待审核状态，当前数据只改状态，新记录保存到临时表，审核通过后再更新
	 * 
	 * @param temp
	 * @param nval
	 */
	public void updateForReview(Partner oval, Partner nval) throws Exception {
		PartnerTemp temp = new PartnerTemp();
		temp.setPartnerId(oval.getId());
		temp.setName(nval.getName());
		temp.setCapitalAmount(nval.getCapitalAmount());
		temp.setCapitalProportion(nval.getCapitalProportion());
		if (nval.getCapitalAmount() == 0
				&& nval.getCapitalAmount() != temp.getCapitalAmount()) {
			temp.setShareEnd(new Date());
		}
		temp.setPhone(nval.getPhone());
		temp.setEmail(nval.getEmail());
		temp.setAddress(nval.getAddress());
		temp.setCompanyLegalPerson(nval.getCompanyLegalPerson());
		temp.setCompanyLicenseCode(nval.getCompanyLicenseCode());
		temp.setCompanyListed(nval.getCompanyListed());
		temp.setCompanyStateOwned(nval.getCompanyStateOwned());
		temp.setCompanyFax(nval.getCompanyFax());
		temp.setCompanyBusiScope(nval.getCompanyBusiScope());
		temp.setCompanyBusiAddress(nval.getCompanyBusiAddress());
		temp.setCompanyRegistAddress(nval.getCompanyRegistAddress());
		temp.setPersonSex(nval.getPersonSex());
		temp.setPersonCardNumber(nval.getPersonCardNumber());
		temp.setPersonAddress(nval.getPersonAddress());
		temp.setPersonWorkUnit(nval.getPersonWorkUnit());
		partnerTempService.saveSelective(temp);
		oval.setStatus(0);
		super.updateByIdSelective(oval);
	}

	/**
	 * 数据字典中设置了变动项这里就会保存相关变动项的记录。 数据字典设置规则，lexicon中code为partner_change_type，
	 * option中key为字段名如：partner.name、partner.capitalAmount等，
	 * option中的value为变动项文字说明如：股东名称，股东出资额
	 * 
	 * @param oval
	 *            旧的值
	 * @param nval
	 *            新的值
	 * @return
	 * @throws Exception
	 */
	public void updateForLog(Partner oval, Partner nval) throws Exception {
		// 取出数据字典设置的值
		DataLexicon lexicon = dataLexiconService
				.queryByCode("partner_change_type");
		List<DataOption> list = new ArrayList<DataOption>();
		if (lexicon != null && lexicon.getDataOptions() != null) {
			list = lexicon.getDataOptions();
		}
		// 比较数据字典中设置的字段值
		List<PartnerChangeLog> changes = new ArrayList<PartnerChangeLog>();
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
					PartnerChangeLog clog = new PartnerChangeLog();
					clog.setAliasName(option.getOptionValue());
					clog.setBeforeValue(ov.toString());
					clog.setAfterValue(nv.toString());
					clog.setPartnerId(oval.getId());
					changes.add(clog);
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		partnerChangeLogService.insertBatch(changes);// 批量插入变动记录
		// 提交的新对象复制到旧对象中保存
		oval.setName(nval.getName());
		oval.setCapitalAmount(nval.getCapitalAmount());
		oval.setCapitalProportion(nval.getCapitalProportion());
		if (nval.getCapitalAmount() == 0
				&& nval.getCapitalAmount() != oval.getCapitalAmount()) {
			oval.setShareEnd(new Date());
		}
		oval.setPhone(nval.getPhone());
		oval.setEmail(nval.getEmail());
		oval.setAddress(nval.getAddress());
		oval.setCompanyLegalPerson(nval.getCompanyLegalPerson());
		oval.setCompanyLicenseCode(nval.getCompanyLicenseCode());
		oval.setCompanyListed(nval.getCompanyListed());
		oval.setCompanyStateOwned(nval.getCompanyStateOwned());
		oval.setCompanyFax(nval.getCompanyFax());
		oval.setCompanyBusiScope(nval.getCompanyBusiScope());
		oval.setCompanyBusiAddress(nval.getCompanyBusiAddress());
		oval.setCompanyRegistAddress(nval.getCompanyRegistAddress());
		oval.setPersonSex(nval.getPersonSex());
		oval.setPersonCardNumber(nval.getPersonCardNumber());
		oval.setPersonAddress(nval.getPersonAddress());
		oval.setPersonWorkUnit(nval.getPersonWorkUnit());
		super.updateByIdSelective(oval);
	}

}
