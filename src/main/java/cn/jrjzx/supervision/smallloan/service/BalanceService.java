package cn.jrjzx.supervision.smallloan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jrjzx.supervision.smallloan.entity.Balance;
import cn.jrjzx.supervision.smallloan.mapper.BalanceMapper;

@Service
@Transactional
public class BalanceService extends ModelService<Balance> {
	@Transactional(readOnly=true)
	public List<String> queryDateMonth(int companyId) {
		return ((BalanceMapper)this.getMapper()).getDateMonth(companyId);
	}
}
