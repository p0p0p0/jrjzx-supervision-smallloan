package cn.jrjzx.supervision.smallloan.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Transactional
public class ModelService<T> extends BaseService<T> {
	protected Class<T> clazz;

	public ModelService() {
		super();
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType ptype = (ParameterizedType) type;
		this.clazz = (Class<T>) ptype.getActualTypeArguments()[0];
	}

	/**
	 * 使用数据库默认值保存
	 */
	@Override
	public void saveSelective(T t) {
		super.getMapper().insertSelective(t);
	}

	/**
	 * 使用数据库默认值更新,如：updateTime
	 */
	@Override
	public void updateByIdSelective(T t) {
		this.getMapper().updateByPrimaryKeySelective(t);
	}

	/**
	 * 根据条件分页查询
	 * 
	 * @param map
	 * @param page
	 * @param rows
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<T> queryPageByWhere(Map<String, Object> map, Integer page,
			Integer rows) {
		// 第一个参数是起始页，第二个参数是，页面显示的数据条数
		PageHelper.startPage(page, rows);
		Example example = new Example(clazz);
		for (String str : map.keySet()) {
			example.createCriteria().andEqualTo(str, map.get(str));
		}
		return new PageInfo<T>(this.getMapper().selectByExample(example));
	}

	/**
	 * 根据条件分页查询
	 * 
	 * @param example
	 * @param page
	 * @param rows
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<T> queryPageByWhere(Example example, Integer page,
			Integer rows) {
		// 第一个参数是起始页，第二个参数是，页面显示的数据条数
		PageHelper.startPage(page, rows);
		return new PageInfo<T>(this.getMapper().selectByExample(example));
	}

	/**
	 * 根据条件查询
	 * 
	 * @param map
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<T> queryListByWhere(Map<String, Object> map) {
		Example example = new Example(clazz);
		Criteria criteria = example.createCriteria();
		for (String str : map.keySet()) {
			if (map.get(str) != null)
				criteria.andEqualTo(str, map.get(str));
		}
		return this.getMapper().selectByExample(example);
	}

	/**
	 * 根据条件查询
	 * 
	 * @param example
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<T> queryListByWhere(Example example) {
		return this.getMapper().selectByExample(example);
	}
}
