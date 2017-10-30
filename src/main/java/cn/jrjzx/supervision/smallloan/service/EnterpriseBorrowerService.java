package cn.jrjzx.supervision.smallloan.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;
import cn.jrjzx.supervision.smallloan.common.util.RejoiceUtil;
import cn.jrjzx.supervision.smallloan.entity.EnterpriseBorrower;
import cn.jrjzx.supervision.smallloan.entity.User;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 *
 * EnterpriseBorrowerService
 * 
 * @date 2017年6月9日 下午2:49:27
 * 
 * @version 1.0.0
 *
 */
@Service
@Transactional
public class EnterpriseBorrowerService extends BaseService<EnterpriseBorrower> {

	@Transactional(readOnly = true)
	public PageInfo<EnterpriseBorrower> queryListByPageAndOrder(
			EnterpriseBorrower enterpriseBorrower, Integer page, Integer rows,
			String sorts[], String orders[]) {
		// 第一个参数是起始页，第二个参数是，页面显示的数据条数
		PageHelper.startPage(page, rows);
		Example example = new Example(User.class);
		if (RejoiceUtil.isNotBlank(sorts) && RejoiceUtil.isNotBlank(orders)) {
			StringBuilder sortSB = new StringBuilder();
			for (int i = 0; i < sorts.length; i++) {
				sortSB.append(StringUtil.camelhumpToUnderline(sorts[i]))
						.append(" ").append(orders[i]).append(",");
			}
			example.setOrderByClause(sortSB.substring(0, sortSB.length() - 1));
		}
		Criteria criteria = example.createCriteria();

		/*
		 * if(StringUtils.isNoneBlank(user.getUsername())){
		 * criteria.andLike("username", "%"+user.getUsername()+"%"); }
		 * if(StringUtils.isNoneBlank(user.getRealName())){
		 * criteria.andLike("realName", "%"+user.getRealName()+"%"); }
		 */
		List<EnterpriseBorrower> list = this.getMapper().selectByExample(
				example);
		return new PageInfo<EnterpriseBorrower>(list);
	}

	/**
	 * 根据条件查询
	 * 
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<EnterpriseBorrower> queryListByWhere(Example example) {
		return this.getMapper().selectByExample(example);
	}
}