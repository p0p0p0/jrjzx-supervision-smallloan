package cn.jrjzx.supervision.smallloan.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jrjzx.supervision.smallloan.common.util.ObjectUtil;
import cn.jrjzx.supervision.smallloan.entity.DataLexicon;
import cn.jrjzx.supervision.smallloan.entity.DataOption;
import cn.jrjzx.supervision.smallloan.entity.Employees;
import cn.jrjzx.supervision.smallloan.entity.EmployeesChangeLog;
import cn.jrjzx.supervision.smallloan.entity.Family;
import cn.jrjzx.supervision.smallloan.entity.LearningExp;
import cn.jrjzx.supervision.smallloan.entity.WorkExp;

@Service
@Transactional
public class EmployeesService extends ModelService<Employees> {
	@Autowired
	WorkExpService workExpService;
	@Autowired
	LearningExpService learningExpService;
	@Autowired
	FamilyService familyService;
	@Autowired
	EmployeesChangeLogService employeesChangeLogService;
	@Autowired
	DataLexiconService dataLexiconService;

	/**
	 * 更新员工信息，并保存变更记录 数据字典中设置了变动项这里就会保存相关变动项的记录。
	 * 数据字典设置规则，lexicon中code为employee_change_type，
	 * option中key为字段名如：company.position等， option中的value为变动项文字说明如：高管职位变动
	 * 
	 * @param oval
	 * @param nval
	 * @throws Exception
	 */
	public void updateForLog(Employees oval, Employees nval) throws Exception {
		if (oval.getPositionType() >= 50) {
			// 普通员工更新，直接更新
			this.copyEmployee(oval, nval);
			super.saveSelective(oval);
			return;
		}
		// 高管更新，需要同时更新学习经历，添加变动记录等
		// 取出数据字典设置的值
		DataLexicon lexicon = dataLexiconService
				.queryByCode("employee_change_type");
		List<DataOption> list = new ArrayList<DataOption>();
		if (lexicon != null && lexicon.getDataOptions() != null) {
			list = lexicon.getDataOptions();
		}
		// 比较数据字典中设置的字段值
		List<EmployeesChangeLog> changes = new ArrayList<EmployeesChangeLog>();
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
					EmployeesChangeLog log = new EmployeesChangeLog();
					log.setAliasName(option.getOptionValue());
					log.setBeforeValue(ov.toString());
					log.setAfterValue(nv.toString());
					log.setCompanyId(oval.getId());
					changes.add(log);
				}
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
		}
		employeesChangeLogService.insertBatch(changes);// 批量插入变动记录
		// 提交的新对象复制到旧对象中保存
		this.copyEmployee(oval, nval);
		super.updateByIdSelective(oval);
		// 更新学习经历
		for (LearningExp nle : nval.getLearningExps()) {
			if (oval.getLearningExps().contains(nle)) {
				// 更新
				nle.setEmployeesId(oval.getId());
				learningExpService.updateByIdSelective(nle);
			} else {
				// 新增
				if (nle.getStartTime() != null
						&& !"".equals(nle.getStartTime())) {
					nle.setEmployeesId(oval.getId());
					learningExpService.saveSelective(nle);
				}
			}
		}
		for (LearningExp ole : oval.getLearningExps()) {
			if (!nval.getLearningExps().contains(ole)) {
				// 删除
				ole.setFlag(0);
				learningExpService.updateByIdSelective(ole);
			}
		}
		// 更新工作经历
		for (WorkExp nwe : nval.getWorkExps()) {
			if (oval.getWorkExps().contains(nwe)) {
				// 更新
				nwe.setEmployeesId(oval.getId());
				workExpService.updateByIdSelective(nwe);
			} else {
				// 新增
				if (nwe.getStartTime() != null
						&& !"".equals(nwe.getStartTime())) {
					nwe.setEmployeesId(oval.getId());
					workExpService.saveSelective(nwe);
				}
			}
		}
		for (WorkExp owe : oval.getWorkExps()) {
			if (!nval.getWorkExps().contains(owe)) {
				// 删除
				owe.setFlag(0);
				workExpService.updateByIdSelective(owe);
			}
		}
		// 更新家庭成员
		for (Family nf : nval.getFamilys()) {
			if (oval.getFamilys().contains(nf)) {
				// 更新
				nf.setEmployeesId(oval.getId());
				familyService.updateByIdSelective(nf);
			} else {
				// 新增
				if (nf.getName() != null && !"".equals(nf.getName())) {
					nf.setEmployeesId(oval.getId());
					familyService.saveSelective(nf);
				}
			}
		}
		for (Family of : oval.getFamilys()) {
			if (!nval.getFamilys().contains(of)) {
				// 删除
				of.setFlag(0);
				familyService.updateByIdSelective(of);
			}
		}

	}

	/**
	 * 保存员工信息，如果员工为高管，则员工信息中包含学习经历等信息
	 */
	@Override
	public void saveSelective(Employees emp) {
		super.saveSelective(emp);// 保存员工基本信息
		EmployeesChangeLog log = new EmployeesChangeLog();
		log.setAliasName("新增员工");
		log.setCompanyId(emp.getCompanyId());
		log.setEmployeeId(emp.getId());
		employeesChangeLogService.saveSelective(log);
		if (emp.getPositionType() < 50) {// 如果录入的是高管信息
			if (emp.getLearningExps() != null) {
				for (LearningExp le : emp.getLearningExps()) {
					if (le.getStartTime() == null
							|| "".equals(le.getStartTime())) {
						continue;
					}
					le.setEmployeesId(emp.getId());
					// 保存高管学习经历
					learningExpService.saveSelective(le);
				}
			}
			if (emp.getWorkExps() != null) {
				for (WorkExp we : emp.getWorkExps()) {
					if (we.getStartTime() == null
							|| "".equals(we.getStartTime())) {
						continue;
					}
					we.setEmployeesId(emp.getId());
					// 保存高管工作经历
					workExpService.saveSelective(we);
				}
			}
			if (emp.getFamilys() != null) {
				for (Family f : emp.getFamilys()) {
					if (f.getName() == null || "".equals(f.getName())) {
						continue;
					}
					f.setEmployeesId(emp.getId());
					// 保存高管家庭成员信息
					familyService.saveSelective(f);
				}
			}
		}
	}

	public void copyEmployee(Employees oval, Employees nval) {
		oval.setName(nval.getName());
		oval.setSex(nval.getSex());
		oval.setCardType(nval.getCardType());
		oval.setCardNumber(nval.getCardNumber());
		oval.setEducation(nval.getEducation());
		oval.setEntryTime(nval.getEntryTime());
		oval.setPosition(nval.getPosition());
		oval.setPositionType(nval.getPositionType());
		oval.setIsonjob(nval.getIsonjob());
		oval.setDepartment(nval.getDepartment());
		oval.setPhone(nval.getPhone());
		oval.setQuitTime(nval.getQuitTime());
		oval.setTitle(nval.getTitle());
		oval.setEmail(nval.getEmail());
		oval.setAddress(nval.getAddress());

	}
}
