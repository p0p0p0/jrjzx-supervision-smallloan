/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.service
 * UserService.java
 * 
 * 2017年6月9日-下午2:49:27
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.service;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import tk.mybatis.mapper.util.StringUtil;
import cn.jrjzx.supervision.smallloan.common.util.RejoiceUtil;
import cn.jrjzx.supervision.smallloan.entity.User;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 *
 * UserService
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月9日 下午2:49:27
 * 
 * @version 1.0.0
 *
 */
@Service
public class UserService extends BaseService<User> {

	  @Transactional(readOnly=true)
	public PageInfo<User> queryListByPageAndOrder(User user, Integer page,
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
        if(StringUtils.isNoneBlank(user.getUsername())){
        	criteria.andLike("username", "%"+user.getUsername()+"%");
        }
        if(StringUtils.isNoneBlank(user.getRealName())){
        	criteria.andLike("realName", "%"+user.getRealName()+"%");
        }
		List<User> list = this.getMapper().selectByExample(example);
		return new PageInfo<User>(list);
	}
	
	/* (non-Javadoc)
	 * @see cn.jrjzx.supervision.smallloan.service.BaseService#save(java.lang.Object)
	 */
	@Override
	public void save(User user) throws Exception {
		user.setPassword(DigestUtils.md5Hex(user.getPassword()));
		super.save(user);
	}
	 
}