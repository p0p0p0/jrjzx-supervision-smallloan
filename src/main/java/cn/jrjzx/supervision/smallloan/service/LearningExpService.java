package cn.jrjzx.supervision.smallloan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jrjzx.supervision.smallloan.entity.LearningExp;

@Service
@Transactional
public class LearningExpService extends ModelService<LearningExp> {
	/**
	 * 批量插入
	 * 
	 * @param list
	 */
	public void insertBatch(List<LearningExp> list) {
		for (LearningExp le : list) {
			if(le.getStartTime()==null) {
				continue;
			}
			super.getMapper().insertSelective(le);
		}
	}
}
