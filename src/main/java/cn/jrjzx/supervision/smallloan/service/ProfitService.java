package cn.jrjzx.supervision.smallloan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jrjzx.supervision.smallloan.entity.Profit;
import cn.jrjzx.supervision.smallloan.mapper.ProfitMapper;

@Service
@Transactional
public class ProfitService extends ModelService<Profit> {
	@Transactional(readOnly = true)
	public List<String> queryDateMonth(int companyId) {
		return ((ProfitMapper) this.getMapper()).getDateMonth(companyId);
	}
}
