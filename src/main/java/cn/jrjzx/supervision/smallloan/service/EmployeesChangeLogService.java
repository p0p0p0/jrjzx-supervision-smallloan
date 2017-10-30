package cn.jrjzx.supervision.smallloan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jrjzx.supervision.smallloan.entity.EmployeesChangeLog;
@Service
@Transactional
public class EmployeesChangeLogService extends ModelService<EmployeesChangeLog>{
	/**
	 * 批量插入
	 * @param list
	 */
		public void insertBatch(List<EmployeesChangeLog> list) {
			for (EmployeesChangeLog log : list) {
				super.getMapper().insertSelective(log);
			}
		}
}
