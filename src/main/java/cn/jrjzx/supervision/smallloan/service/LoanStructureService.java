package cn.jrjzx.supervision.smallloan.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import cn.jrjzx.supervision.smallloan.entity.LoanStructure;

@Service
@Transactional
public class LoanStructureService extends ModelService<LoanStructure> {
	@Transactional(readOnly = true)
	public List<LoanStructure> searchByCategoryType(int companyId,
			int category_type, Date date) {
		Example example = new Example(LoanStructure.class);
		Criteria c = example.createCriteria();
		c.andEqualTo("flag", 1);
		c.andEqualTo("companyId", companyId);
		c.andEqualTo("searchDate", date);
		c.andEqualTo("categoryType", category_type);
		example.setOrderByClause("category_type,data_type ");
		return queryListByWhere(example);
	}
}
