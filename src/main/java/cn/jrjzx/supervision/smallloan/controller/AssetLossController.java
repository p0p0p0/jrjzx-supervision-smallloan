package cn.jrjzx.supervision.smallloan.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import cn.jrjzx.supervision.smallloan.common.constant.Constant;
import cn.jrjzx.supervision.smallloan.entity.AssetLoss;
import cn.jrjzx.supervision.smallloan.entity.User;
import cn.jrjzx.supervision.smallloan.service.AssetLossService;

/**
 * 资产损失准备计提controller
 *
 */
@Controller
@RequestMapping("/asset_loss")
public class AssetLossController {

	@Autowired
	AssetLossService assetLossService;

	/**
	 * 跳转到资产损失准备计提页面
	 * 
	 * @param dateMonth
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping("/info")
	public String info(String dateMonth, Map<String, Object> map,
			HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);// 当前用户
		Example example = new Example(AssetLoss.class);// 查询条件
		Criteria c = example.createCriteria();
		c.andEqualTo("companyId", user.getCompanyId());
		c.andEqualTo("flag", 1);
		example.setOrderByClause("date_month desc");// 根据所属月份排序，一般一个月只有一条记录
		// 如果查询条件为空，取最近的一条记录
		if (dateMonth == null) {
			List<AssetLoss> list = assetLossService.queryPageByWhere(example,
					1, 1).getList();
			if (list != null && list.size() > 0) {// 判断是否有数据
				AssetLoss al = list.get(0);
				map.put("dateMonth", al.getDateMonth());
				map.put("asset", al);
			}
		} else {
			// 查询条件不为空根据所属月份查询
			c.andEqualTo("dateMonth", dateMonth);
			List<AssetLoss> list = assetLossService.queryListByWhere(example);
			if (list != null && list.size() > 0) {
				AssetLoss al = list.get(0);
				map.put("dateMonth", al.getDateMonth());
				map.put("asset", al);

			}
		}
		return "asset_loss/asset_loss_info";
	}

	/**
	 * 跳转添加资产损失准备计提页面
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	public String add() {
		return "asset_loss/add_asset_loss";
	}

	/**
	 * 保存资产损失准备计提一个月的数据
	 * 
	 * @param asset
	 * @param session
	 * @return
	 */
	@RequestMapping("/save")
	public String save(AssetLoss asset, HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		asset.setCompanyId(user.getCompanyId());
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("flag", 1);
		param.put("companyId", asset.getCompanyId());
		param.put("dateMonth", asset.getDateMonth());
		// 查询该月份是否有可用数据，有则更新，没有则新增
		List<AssetLoss> list = assetLossService.queryListByWhere(param);
		if (list != null && list.size() > 0) {
			asset.setId(list.get(0).getId());
			assetLossService.updateByIdSelective(asset);
		} else {
			assetLossService.saveSelective(asset);
		}
		return "redirect:/asset_loss/info";
	}
	

}
