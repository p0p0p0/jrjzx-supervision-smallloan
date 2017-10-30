package cn.jrjzx.supervision.smallloan.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jrjzx.supervision.smallloan.common.constant.Constant;
import cn.jrjzx.supervision.smallloan.config.GlobalConfig;
import cn.jrjzx.supervision.smallloan.entity.Company;
import cn.jrjzx.supervision.smallloan.entity.Partner;
import cn.jrjzx.supervision.smallloan.entity.User;
import cn.jrjzx.supervision.smallloan.service.CompanyService;
import cn.jrjzx.supervision.smallloan.service.PartnerService;

/**
 * 机构信息controller
 *
 */
@Controller
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	CompanyService companyService;
	@Autowired
	PartnerService partnerService;

	/**
	 * 跳转基础数据页面
	 * 
	 * @param map
	 *            页面显示的数据
	 * @return
	 */
	@RequestMapping("/info")
	public String info(Map<String, Object> map, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(
				Constant.SESSION_KEY);
		Company company = companyService.queryByID(user.getCompanyId());
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyId", company.getId());
		param.put("flag", 1);
		List<Partner> ps = partnerService.queryListByWhere(param);
		company.setPartners(ps);
		map.put("company", company);
		map.put("rows", GlobalConfig.readProperty("page.rows"));
		return "company/base_data";
	}

	/**
	 * 跳转机构信息修改页
	 * 
	 * @param map
	 *            页面显示的数据
	 * @return
	 */
	@RequestMapping("/modify_company")
	public String modifyCompany(Map<String, Object> map,
			HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute(
				Constant.SESSION_KEY);
		Company company = companyService.queryByID(user.getCompanyId());
		map.put("company", company);
		return "company/modify_company";
	}

	/**
	 * 更新公司信息
	 * 
	 * @param company
	 * @param session
	 * @return
	 */
	@RequestMapping("/update_company")
	public String updateCompany(Company company, HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		Company old = companyService.queryByID(user.getCompanyId());
		try {
			// companyService.updateForLog(old, company);//直接更新，不需要审核后才更新
			companyService.updateForReview(old, company);// 提交审核
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
