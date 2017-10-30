package cn.jrjzx.supervision.smallloan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jrjzx.supervision.smallloan.entity.WorkExp;
@Service
@Transactional
public class WorkExpService extends ModelService<WorkExp>{
	/**
	 * 批量插入
	 * 
	 * @param list
	 */
	public void insertBatch(List<WorkExp> list) {
		for(WorkExp we:list) {
			if(we.getStartTime()==null) {
				continue;
			}
			super.getMapper().insertSelective(we);
		}
	}
}
