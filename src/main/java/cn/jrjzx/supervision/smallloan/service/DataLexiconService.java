package cn.jrjzx.supervision.smallloan.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;
import cn.jrjzx.supervision.smallloan.entity.DataLexicon;
import cn.jrjzx.supervision.smallloan.entity.DataOption;

@Service
@Transactional
public class DataLexiconService extends ModelService<DataLexicon> {
	@Autowired
	DataOptionService dataOptionService;

	@Transactional(readOnly = true)
	public DataLexicon queryByCode(String code) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("code", code);
		List<DataLexicon> list = super.queryListByWhere(param);
		if (list == null || list.size() == 0) {
			return null;
		}
		DataLexicon dl = list.get(0);
		Example example = new Example(DataOption.class);
		example.createCriteria().andEqualTo("lexiconId", dl.getId());
		example.setOrderByClause(" level ");
		List<DataOption> options = dataOptionService.queryListByWhere(example);
		dl.setDataOptions(options);
		return dl;
	}
}
