package cn.jrjzx.supervision.smallloan.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import cn.jrjzx.supervision.smallloan.common.constant.Constant;
import cn.jrjzx.supervision.smallloan.config.GlobalConfig;
import cn.jrjzx.supervision.smallloan.entity.DataLexicon;
import cn.jrjzx.supervision.smallloan.entity.Employees;
import cn.jrjzx.supervision.smallloan.entity.Family;
import cn.jrjzx.supervision.smallloan.entity.LearningExp;
import cn.jrjzx.supervision.smallloan.entity.User;
import cn.jrjzx.supervision.smallloan.entity.WorkExp;
import cn.jrjzx.supervision.smallloan.service.DataLexiconService;
import cn.jrjzx.supervision.smallloan.service.EmployeesService;
import cn.jrjzx.supervision.smallloan.service.FamilyService;
import cn.jrjzx.supervision.smallloan.service.LearningExpService;
import cn.jrjzx.supervision.smallloan.service.WorkExpService;

import com.github.pagehelper.PageInfo;

/**
 * 
 * 人员信息controller
 */
@Controller
@RequestMapping("/employees")
public class EmployeesController {
	private String rows = GlobalConfig.readProperty("page.rows");// 每页显示的记录数
	@Autowired
	EmployeesService employeesService;
	@Autowired
	WorkExpService workExpService;
	@Autowired
	LearningExpService learningExpService;
	@Autowired
	FamilyService familyService;
	@Autowired
	DataLexiconService dataLexiconService;

	/**
	 * 跳转员工添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/add_emp")
	public String addEmp(Map<String, Object> map) {
		DataLexicon lexicon = dataLexiconService.queryByCode("card_type");// 证件类型数据字典
		map.put("lexicon", lexicon);
		return "company/add_employee";
	}

	/**
	 * 跳转员工信息修改页
	 * 
	 * @param employeeId
	 * @return
	 */
	@RequestMapping("/modify_emp")
	public String modifyEmp(Map<String, Object> map, String employeeId) {
		Employees emp = null;
		// 查询员工信息
		if (employeeId != null && !"".equals(employeeId)) {
			emp = employeesService.queryByID(Integer.parseInt(employeeId));
		}
		if (emp == null) {
			return "error/error.jsp";
		}
		DataLexicon lexicon = dataLexiconService.queryByCode("card_type");// 证件类型数据字典
		map.put("lexicon", lexicon);
		if (emp.getPositionType() < 50) {
			// 如果查询的员工信息为高管的还要查询相关的工作经历，学习经历，家庭成员
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("employeesId", employeeId);
			param.put("flag", 1);
			List<WorkExp> wes = workExpService.queryListByWhere(param);
			emp.setWorkExps(wes);
			List<LearningExp> les = learningExpService.queryListByWhere(param);
			emp.setLearningExps(les);
			List<Family> fs = familyService.queryListByWhere(param);
			emp.setFamilys(fs);
		}
		map.put("emp", emp);
		return "company/modify_employee";
	}

	/**
	 * 更新员工信息
	 * 
	 * @param emp
	 * @return
	 */
	@RequestMapping("/update_emp")
	public String updateEmp(Employees nval) {
		System.out.println("@@@@@@@@@@@@@" + nval);
		System.out.println("@@@@@@@@@@@@@" + nval.getLearningExps());
		System.out.println("@@@@@@@@@@@@@" + nval.getWorkExps());
		System.out.println("@@@@@@@@@@@@@" + nval.getFamilys());
		Employees oval = employeesService.queryByID(nval.getId());
		if (oval.getPositionType() < 50) {
			// 如果查询的员工信息为高管的还要查询相关的工作经历，学习经历，家庭成员
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("employeesId", oval.getId());
			List<WorkExp> wes = workExpService.queryListByWhere(param);
			oval.setWorkExps(wes);
			List<LearningExp> les = learningExpService.queryListByWhere(param);
			oval.setLearningExps(les);
			List<Family> fs = familyService.queryListByWhere(param);
			oval.setFamilys(fs);
		}
		try {
			employeesService.updateForLog(oval, nval);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/company/info";
	}

	/**
	 * 保存新增员工信息
	 * 
	 * @param emp
	 * @return
	 */
	@RequestMapping("save_emp")
	public String saveEmp(Employees emp, HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		emp.setCompanyId(user.getCompanyId());
		emp.setSignTime(new Date());
		// employeesService.saveSelective(emp);// 保存员工基本信息
		// if (emp.getPositionType() < 50) {// 如果录入的是高管信息
		// if (emp.getLearningExps() != null) {
		// for (LearningExp le : emp.getLearningExps()) {
		// le.setEmployeesId(emp.getId());
		// }
		// // 保存高管学习经历
		// learningExpService.insertBatch(emp.getLearningExps());
		// }
		// if (emp.getWorkExps() != null) {
		// for (WorkExp we : emp.getWorkExps()) {
		// we.setEmployeesId(emp.getId());
		// }
		// // 保存高管工作经历
		// workExpService.insertBatch(emp.getWorkExps());
		// }
		// if (emp.getFamilys() != null) {
		// for (Family f : emp.getFamilys()) {
		// f.setEmployeesId(emp.getId());
		// }
		// // 保存高管家庭成员信息
		// familyService.insertBatch(emp.getFamilys());
		// }
		// }
		employeesService.saveSelective(emp);// 保存员工信息
		return "redirect:/company/info";
	}

	/**
	 * 人员信息ajax请求
	 * 
	 * @param type
	 *            高管，普通员工
	 * @param page
	 *            第几页
	 * @param name
	 *            搜索条件，姓名
	 * @param sex
	 *            搜索条件，性别
	 * @param education
	 *            搜索条件，学历
	 * @param department
	 *            搜索条件，部门
	 * @param isonjob
	 *            搜索条件，是否在职
	 * @return
	 */
	@RequestMapping("/findByParam")
	@ResponseBody
	public ResponseEntity<PageInfo<Employees>> findByCompany(String type,
			String page, String name, String sex, String education,
			String department, String isonjob, HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		PageInfo<Employees> pi = new PageInfo<Employees>();
		// 查询高管还是普通员工
		if (type != null && !"".equals(type)) {
			Example example = new Example(Employees.class);
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("flag", 1);
			criteria.andEqualTo("companyId", user.getCompanyId());
			if ("1".equals(type)) {
				// 高管
				criteria.andLessThan("positionType", 50);
			} else {
				// 普通员工
				criteria.andGreaterThanOrEqualTo("positionType", 50);
			}
			int p = 1;
			if (page != null && !"".equals(page)) {
				// 分页
				p = Integer.valueOf(page);
			}
			if (name != null && !"".equals(name.trim())) {
				// 姓名
				criteria.andEqualTo("name", name);
			}
			if (sex != null && !"".equals(sex.trim())) {
				// 性别
				criteria.andEqualTo("sex", sex);
			}
			if (education != null && !"".equals(education.trim())) {
				// 学历
				criteria.andEqualTo("education", education);
			}
			if (department != null && !"".equals(department.trim())) {
				// 部门
				criteria.andEqualTo("department", department);
			}
			if (isonjob != null && !"".equals(isonjob.trim())) {
				// 是否在职
				criteria.andEqualTo("isonjob", isonjob);
			}
			example.setOrderByClause("position_type");
			// example查询条件，p第几页，rows每页显示多少条
			pi = employeesService.queryPageByWhere(example, p,
					Integer.valueOf(rows));
		}
		return ResponseEntity.ok(pi);
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
