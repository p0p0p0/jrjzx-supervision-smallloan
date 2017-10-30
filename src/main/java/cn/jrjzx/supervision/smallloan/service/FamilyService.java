package cn.jrjzx.supervision.smallloan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jrjzx.supervision.smallloan.entity.Family;

@Service
@Transactional
public class FamilyService extends ModelService<Family> {
	/**
	 * 批量插入
	 * 
	 * @param list
	 */
	public void insertBatch(List<Family> list) {
		for (Family f : list) {
			if(f.getName()==null||"".equals(f.getName())) {
				continue;
			}
			super.getMapper().insertSelective(f);
		}
	}
}
