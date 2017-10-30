package cn.jrjzx.supervision.smallloan.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.jrjzx.supervision.smallloan.common.constant.Constant;
import cn.jrjzx.supervision.smallloan.entity.LoanStructure;
import cn.jrjzx.supervision.smallloan.entity.User;
import cn.jrjzx.supervision.smallloan.service.LoanStructureService;

/**
 * 贷款结构controller
 *
 */
@Controller
@RequestMapping("/structure")
public class LoanStructureController {
	@Autowired
	LoanStructureService loanStructureService;

	/**
	 * 跳转贷款结构分析页面
	 * 
	 * @param dateMonth
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping("loan_structure")
	public String searchLoanStructure(String dateMonth,
			Map<String, Object> map, HttpSession session) {
		// 各大类的每个小类的详细
		List<LoanStructure> list1 = new ArrayList<LoanStructure>();
		List<LoanStructure> list2 = new ArrayList<LoanStructure>();
		List<LoanStructure> list3 = new ArrayList<LoanStructure>();
		List<LoanStructure> list4 = new ArrayList<LoanStructure>();
		List<LoanStructure> list5 = new ArrayList<LoanStructure>();
		List<LoanStructure> list6 = new ArrayList<LoanStructure>();
		List<LoanStructure> list7 = new ArrayList<LoanStructure>();
		// 用于统计各大类的合计
		LoanStructure info1 = new LoanStructure();
		LoanStructure info2 = new LoanStructure();
		LoanStructure info3 = new LoanStructure();
		LoanStructure info4 = new LoanStructure();
		LoanStructure info5 = new LoanStructure();
		LoanStructure info6 = new LoanStructure();
		LoanStructure info7 = new LoanStructure();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		if (dateMonth != null) {
			try {
				// 每个月保存一条记录，默认保存的日期为当月的1号
				Date date = sdf.parse(dateMonth + "-01");
				int companyId = user.getCompanyId();// 机构id
				List<LoanStructure> tempList1 = new ArrayList<LoanStructure>();
				List<LoanStructure> tempList2 = new ArrayList<LoanStructure>();
				List<LoanStructure> tempList3 = new ArrayList<LoanStructure>();
				List<LoanStructure> tempList4 = new ArrayList<LoanStructure>();
				List<LoanStructure> tempList5 = new ArrayList<LoanStructure>();
				List<LoanStructure> tempList6 = new ArrayList<LoanStructure>();
				List<LoanStructure> tempList7 = new ArrayList<LoanStructure>();
				// 根据时间和company_id查询数据
				tempList1 = loanStructureService.searchByCategoryType(
						companyId, 1, date);
				tempList2 = loanStructureService.searchByCategoryType(
						companyId, 2, date);
				tempList3 = loanStructureService.searchByCategoryType(
						companyId, 3, date);
				tempList4 = loanStructureService.searchByCategoryType(
						companyId, 4, date);
				tempList5 = loanStructureService.searchByCategoryType(
						companyId, 5, date);
				tempList6 = loanStructureService.searchByCategoryType(
						companyId, 6, date);
				tempList7 = loanStructureService.searchByCategoryType(
						companyId, 7, date);
				// 初始化各大类合计信息的类型
				info1.setCategoryType(1);
				info2.setCategoryType(2);
				info3.setCategoryType(3);
				info4.setCategoryType(4);
				info5.setCategoryType(5);
				info6.setCategoryType(6);
				info7.setCategoryType(7);

				// 统计各大类的合计
				initList(info1, tempList1, list1, 8);
				initList(info2, tempList2, list2, 5);
				initList(info3, tempList3, list3, 13);
				initList(info4, tempList4, list4, 4);
				initList(info5, tempList5, list5, 7);
				initList(info6, tempList6, list6, 5);
				initList(info7, tempList7, list7, 5);

				// 计算每条数据的金额占比
				initRatio(info1, list1);
				initRatio(info2, list2);
				initRatio(info3, list3);
				initRatio(info4, list4);
				initRatio(info5, list5);
				initRatio(info6, list6);
				initRatio(info7, list7);

				map.put("info1", info1);
				map.put("info2", info2);
				map.put("info3", info3);
				map.put("info4", info4);
				map.put("info5", info5);
				map.put("info6", info6);
				map.put("info7", info7);

				map.put("list1", list1);
				map.put("list2", list2);
				map.put("list3", list3);
				map.put("list4", list4);
				map.put("list5", list5);
				map.put("list6", list6);
				map.put("list7", list7);

				map.put("dateMonth", sdf.parse(dateMonth + "-01"));
			} catch (ParseException e) {
				e.printStackTrace();

			}
		}

		return "structure/loan_structure";
	}

	/**
	 * 统计各大类的合计
	 * 
	 * @param info
	 *            合计信息
	 * @param tempList
	 *            数据库中查询到的记录
	 * @param list
	 *            最终显示在页面处理后的数据
	 * @param dataTypeCount
	 *            共有多少个小类
	 */
	private void initList(LoanStructure info, List<LoanStructure> tempList,
			List<LoanStructure> list, int dataTypeCount) {
		// 遍历数据库中查询的记录
		for (int i = 0; i < tempList.size(); i++) {

			LoanStructure current = tempList.get(i);
			// 计算合计
			countTotal(info, current);

			// 第一个不是1的，补全前面所有
			if (i == 0 && current.getDataType() != 1) {
				for (int j = 1; j < current.getDataType(); j++) {
					LoanStructure temp = new LoanStructure();
					temp.setCategoryType(info.getCategoryType());
					temp.setDataType(j);
					list.add(temp);
				}
			} else if (i == 0 && current.getDataType() == 1) {
				list.add(current);
			}

			if (current.getDataType() == (list.get(list.size() - 1)
					.getDataType()) + 1) {
				list.add(current);
			}

			if (i < tempList.size() - 1) {
				LoanStructure tempNext = tempList.get(i + 1);// 下一条数据
				int d2 = tempNext.getDataType();
				for (int j = current.getDataType(); j < d2 - 1; j++) {
					LoanStructure temp = new LoanStructure();
					temp.setCategoryType(info.getCategoryType());
					temp.setDataType(j + 1);
					list.add(temp);
				}
			} else {
				// 最后一条数据
				for (int j = current.getDataType(); j < dataTypeCount; j++) {
					LoanStructure temp = new LoanStructure();
					temp.setCategoryType(info.getCategoryType());
					temp.setDataType(j + 1);
					list.add(temp);
				}
			}
		}

		// 如果全部为空
		if (tempList.size() == 0) {
			// 最后一条数据
			for (int j = 1; j <= dataTypeCount; j++) {
				LoanStructure temp = new LoanStructure();
				temp.setCategoryType(info.getCategoryType());
				temp.setDataType(j);
				list.add(temp);
			}
		}
	}

	/**
	 * 统计每个大类的合计
	 * 
	 * @param info
	 * @param infoTemp
	 * @throws ParseException
	 */
	private void countTotal(LoanStructure info, LoanStructure infoTemp) {

		System.out.println("统计合计***loanStructure.id:" + infoTemp.getId()
				+ "--大类" + infoTemp.getCategoryType() + "--小类"
				+ infoTemp.getDataType());

		// 贷款余额
		Double balance = info.getBalance() == null ? 0d : Double
				.parseDouble(info.getBalance());
		Double tempBalance = infoTemp.getBalance() == null ? 0d : Double
				.parseDouble(infoTemp.getBalance());
		info.setBalance(String.format("%.6f", (balance + tempBalance)));

		// 本年累计笔数
		info.setYearCount(info.getYearCount() + infoTemp.getYearCount());
		// 本年累计金额
		Double year_money = info.getYearMoney() == null ? 0d : Double
				.parseDouble(info.getYearMoney());
		Double temp_year_money = infoTemp.getYearMoney() == null ? 0d : Double
				.parseDouble(infoTemp.getYearMoney());
		info.setYearMoney(String.format("%.6f", (year_money + temp_year_money)));

		// 本月累计笔数
		info.setMonthCount(info.getMonthCount() + infoTemp.getMonthCount());
		Double month_money = info.getMonthMoney() == null ? 0d : Double
				.parseDouble(info.getMonthMoney());
		// 本年累计金额
		Double temp_month_money = infoTemp.getMonthMoney() == null ? 0d
				: Double.parseDouble(infoTemp.getMonthMoney());
		info.setMonthMoney(String.format("%.6f",
				(month_money + temp_month_money)));
	}

	/**
	 * 计算占比
	 * 
	 * @param info
	 * @param list
	 */
	private void initRatio(LoanStructure info, List<LoanStructure> list) {
		for (LoanStructure infoTemp : list) {
			if (info.getCategoryType() == infoTemp.getCategoryType()) {
				Double yearMoney = StringUtils.isBlank(infoTemp.getYearMoney()) ? 0d
						: Double.parseDouble(infoTemp.getYearMoney());
				if (yearMoney > 0) {
					Double yearMoneyHj = StringUtils.isBlank(info
							.getYearMoney()) ? 0d : Double.parseDouble(info
							.getYearMoney());
					if (yearMoneyHj > 0) {
						Double ratioTemp = (yearMoney / yearMoneyHj) * 100;
						infoTemp.setYearRatio(String.format("%.2f", ratioTemp));
					}
				}

				Double monthMoney = StringUtils.isBlank(infoTemp
						.getMonthMoney()) ? 0d : Double.parseDouble(infoTemp
						.getMonthMoney());
				if (monthMoney > 0) {
					Double monthMoneyHj = StringUtils.isBlank(info
							.getMonthMoney()) ? 0d : Double.parseDouble(info
							.getMonthMoney());
					if (monthMoneyHj > 0) {
						Double ratioTemp = (monthMoney / monthMoneyHj) * 100;
						infoTemp.setMonthRatio(String.format("%.2f", ratioTemp));
					}
				}
			}
		}
	}
}
