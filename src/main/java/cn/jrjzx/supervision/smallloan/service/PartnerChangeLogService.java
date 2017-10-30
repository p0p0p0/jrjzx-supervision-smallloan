package cn.jrjzx.supervision.smallloan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jrjzx.supervision.smallloan.entity.PartnerChangeLog;

@Service
@Transactional
public class PartnerChangeLogService extends ModelService<PartnerChangeLog> {
	/**
	 * 批量插入
	 * 
	 * @param list
	 */
	public void insertBatch(List<PartnerChangeLog> list) {
		for (PartnerChangeLog log : list) {
			super.getMapper().insertSelective(log);
		}
	}
}
