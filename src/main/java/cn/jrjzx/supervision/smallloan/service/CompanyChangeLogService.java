package cn.jrjzx.supervision.smallloan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jrjzx.supervision.smallloan.entity.CompanyChangeLog;

@Service
@Transactional
public class CompanyChangeLogService extends ModelService<CompanyChangeLog> {
/**
 * 批量插入
 * @param list
 */
	public void insertBatch(List<CompanyChangeLog> list) {
		for (CompanyChangeLog log : list) {
			super.getMapper().insertSelective(log);
		}
	}
}
