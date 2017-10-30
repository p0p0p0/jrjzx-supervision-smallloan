package cn.jrjzx.supervision.smallloan.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.jrjzx.supervision.smallloan.entity.DataOption;
import cn.jrjzx.supervision.smallloan.mapper.DataOptionMapper;

@Service
@Transactional
public class DataOptionService extends ModelService<DataOption> {
	@Transactional(readOnly = true)
	public DataOption queryByCodeAndKey(String code, String key) {
		return ((DataOptionMapper) this.getMapper()).getOptionByCodeAndKey(
				code, key);
	}
}
