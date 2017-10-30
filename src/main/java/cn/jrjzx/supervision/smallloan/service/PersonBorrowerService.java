package cn.jrjzx.supervision.smallloan.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;
import cn.jrjzx.supervision.smallloan.common.util.RejoiceUtil;
import cn.jrjzx.supervision.smallloan.entity.PersonBorrower;
import cn.jrjzx.supervision.smallloan.entity.User;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 *
 * PersonBorrowerService
 * 
 * @date 2017年6月9日 下午2:49:27
 * 
 * @version 1.0.0
 *
 */
@Service
@Transactional
public class PersonBorrowerService extends ModelService<PersonBorrower> {

	private static final Logger LOGGER = LoggerFactory.getLogger(PersonBorrowerService.class);
	
	@Transactional(readOnly=true)
	public PageInfo<PersonBorrower> queryListByPageAndOrder(PersonBorrower personBorrower, Integer page,
			Integer rows,String sorts[],String orders[]) {
		// 第一个参数是起始页，第二个参数是，页面显示的数据条数
        PageHelper.startPage(page, rows);
        Example example = new Example(User.class);
        if (RejoiceUtil.isNotBlank(sorts) && RejoiceUtil.isNotBlank(orders)) {
        	StringBuilder sortSB = new StringBuilder();
        	for(int i = 0; i < sorts.length; i++){
        		sortSB.append(StringUtil.camelhumpToUnderline(sorts[i])).append(" ").append(orders[i]).append(",");
        	}
            example.setOrderByClause(sortSB.substring(0, sortSB.length()-1));
        }
        Criteria criteria = example.createCriteria();
        
		List<PersonBorrower> list = this.getMapper().selectByExample(example);
		return new PageInfo<PersonBorrower>(list);
	}
	
	 
}