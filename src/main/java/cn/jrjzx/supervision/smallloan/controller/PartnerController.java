package cn.jrjzx.supervision.smallloan.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jrjzx.supervision.smallloan.common.constant.Constant;
import cn.jrjzx.supervision.smallloan.entity.Company;
import cn.jrjzx.supervision.smallloan.entity.DataLexicon;
import cn.jrjzx.supervision.smallloan.entity.Partner;
import cn.jrjzx.supervision.smallloan.entity.PartnerChangeLog;
import cn.jrjzx.supervision.smallloan.entity.User;
import cn.jrjzx.supervision.smallloan.service.CompanyService;
import cn.jrjzx.supervision.smallloan.service.DataLexiconService;
import cn.jrjzx.supervision.smallloan.service.PartnerChangeLogService;
import cn.jrjzx.supervision.smallloan.service.PartnerService;

/**
 * 
 * 股东信息controller
 */
@Controller
@RequestMapping("/partner")
public class PartnerController {
	@Autowired
	CompanyService companyService;
	@Autowired
	PartnerService partnerService;
	@Autowired
	DataLexiconService dataLexiconService;
	@Autowired
	PartnerChangeLogService partnerChangeLogService;

	/**
	 * 跳转添加股东页面
	 * 
	 * @return
	 */
	@RequestMapping("/add_partner")
	public String addPartner(Map<String, Object> map, HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		Company company = companyService.queryByID(user.getCompanyId());
		DataLexicon lexicon = dataLexiconService.queryByCode("card_type");// 证件类型数据字典
		map.put("company", company);
		map.put("lexicon", lexicon);
		return "company/add_partner";
	}

	/**
	 * 保存股东信息，并记录变更表
	 * 
	 * @param partner
	 * @return
	 */
	@RequestMapping("/save_partner")
	public String savePartner(Partner partner, HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		// 保存股东信息
		partner.setCompanyId(user.getCompanyId());
		partnerService.saveSelective(partner);
		// 保存变更记录
		PartnerChangeLog log = new PartnerChangeLog();
		log.setAliasName("股东新增");
		log.setBeforeValue("0");
		log.setAfterValue(partner.getCapitalAmount() + "");
		log.setPartnerId(partner.getId());
		partnerChangeLogService.saveSelective(log);
		return "redirect:/company/info";
	}

	/**
	 * 跳转股东编辑页面
	 * 
	 * @param partnerId
	 * @param map
	 * @return
	 */
	@RequestMapping("/modify_partner")
	public String modifyPartner(String partnerId, Map<String, Object> map,
			HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		int pid = 0;
		if (partnerId != null) {
			try {
				pid = Integer.valueOf(partnerId);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Partner partner = partnerService.queryByID(pid);// 股东信息
		Company company = companyService.queryByID(user.getCompanyId());
		DataLexicon lexicon = dataLexiconService.queryByCode("card_type");// 证件类型数据字典
		map.put("company", company);
		map.put("lexicon", lexicon);
		map.put("partner", partner);
		return "company/modify_partner";
	}

	/**
	 * 更新股东信息
	 * 
	 * @param partner
	 * @return
	 */
	@RequestMapping("/update_partner")
	public String updatePartner(Partner partner) {
		Partner old = partnerService.queryByID(partner.getId());
		try {
			// partnerService.updateForLog(old, partner);
			partnerService.updateForReview(old, partner);//不直接更新，需要审核后才更新
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/company/info";
	}

	/**
	 * 前端日期和后端的日期转化
	 */
	@InitBinder
	public void bindingPreparation(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat,
				true);
		binder.registerCustomEditor(Date.class, orderDateEditor);
	}
}
