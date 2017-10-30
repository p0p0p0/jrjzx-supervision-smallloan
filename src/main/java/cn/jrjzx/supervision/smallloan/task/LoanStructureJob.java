package cn.jrjzx.supervision.smallloan.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import cn.jrjzx.supervision.smallloan.entity.EnterpriseBorrower;
import cn.jrjzx.supervision.smallloan.entity.LoanContract;
import cn.jrjzx.supervision.smallloan.entity.LoanStructure;
import cn.jrjzx.supervision.smallloan.entity.PersonBorrower;
import cn.jrjzx.supervision.smallloan.service.EnterpriseBorrowerService;
import cn.jrjzx.supervision.smallloan.service.ExtendRepayPlanService;
import cn.jrjzx.supervision.smallloan.service.LoanContractService;
import cn.jrjzx.supervision.smallloan.service.LoanStructureService;
import cn.jrjzx.supervision.smallloan.service.PersonBorrowerService;
import cn.jrjzx.supervision.smallloan.service.RepayPlanService;

/**
 * 
 * 生成贷款结构数据的定时任务
 */
@Service
public class LoanStructureJob {
	@Autowired
	private LoanContractService loanContractService;
	@Autowired
	private LoanStructureService loanStructureService;

	@Autowired
	private RepayPlanService repayPlanService;

	@Autowired
	private PersonBorrowerService personBorrowerService;
	@Autowired
	EnterpriseBorrowerService enterpriseBorrowerService;

	@Autowired
	private ExtendRepayPlanService extendCountService;

	private Calendar calendar = Calendar.getInstance();

	private SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	private Date monthStartDate;
	private Date preDate; // 定时任务执行时间的前一天

	/**
	 * 定时任务执行的主体,每个月保存一条记录
	 * 
	 * @throws Exception
	 */
	public void countLoanStructure() throws Exception {
		System.out.println("--------------统计贷款业务结构 定时任务-----START------------");
		Set<Long> companyIdSet = new HashSet<Long>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

		// sigDate>= '前一天所在年'-01-01，即:当天凌晨执行定时任务统计截止前一天的合同数据
		calendar.add(Calendar.DAY_OF_MONTH, -1);// 前一天
		String yearStartDate = calendar.get(Calendar.YEAR) + "-01-01";

		int monthInt = calendar.get(Calendar.MONTH) + 1; // Calendar.MONTH
															// 的值是0~11
		String monthStr = monthInt > 9 ? monthInt + "" : "0" + monthInt;
		String monthStartStr = calendar.get(Calendar.YEAR) + "-" + monthStr
				+ "-01";

		monthStartDate = df.parse(monthStartStr);

		// 保存的日期为指定月份的1号
		// 定时任务执行时间的前一天
		String preDateStr = calendar.get(Calendar.YEAR) + "-" + monthStr + "-"
				+ "01";
		// + calendar.get(Calendar.DAY_OF_MONTH);
		preDate = df.parse(preDateStr);

		System.out.println("yearStartDate:" + yearStartDate
				+ "---monthStartDate:" + monthStartDate.toString()
				+ "---preDate:" + preDate.toString());

		// sigDate>= '前一天所在年'-01-01，即:当天凌晨执行定时任务统计截止前一天的合同数据
		Example example = new Example(LoanContract.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("flag", 1);
		criteria.andGreaterThanOrEqualTo("signTime", yearStartDate);
		List<LoanContract> allLoanList = loanContractService
				.queryListByWhere(example);
		System.out.println("---合同总数1:" + allLoanList.size());

		List<LoanContract> removeList = new ArrayList<LoanContract>();
		for (int i = 0; i < allLoanList.size(); i++) {
			LoanContract contract = allLoanList.get(i);
			if (contract.getLoanBalance() != null
					&& contract.getLoanBalance() > 0d) {
				companyIdSet.add(contract.getCompanyId());
			} else {
				removeList.add(contract);// 把无效的清除
			}
		}
		allLoanList.removeAll(removeList);// 把无效的清除

		System.out.println("---合同总数2:" + allLoanList.size());

		System.out.println("公司总数:" + companyIdSet.size());

		countByLenderType(allLoanList, companyIdSet); // 一
		countByAmount(allLoanList, companyIdSet); // 二
		countByBusiness(allLoanList, companyIdSet); // 三
		countByTerm(allLoanList, companyIdSet); // 四
		countByDaiMethod(allLoanList, companyIdSet); // 五
		countByStatu(allLoanList, companyIdSet); // 六
		countByRate(allLoanList, companyIdSet); // 七

		System.out.println("--------------统计贷款业务结构 定时任务-----END------------");
	}

	/**
	 * 一、借款主体 企业规模lenderInfo的scale属性：1) 个体工商户 2) 农村专业合作组织 3)
	 * 微型企业4)小型企业5)中型企业6)大型企业7)其他组织
	 * 
	 * @param loanList1
	 * @throws ParseException
	 */
	private void countByLenderType(List<LoanContract> loanList1,
			Set<Long> companyIdSetTemp) throws ParseException {
		/*
		 * data_type 1.个人贷款 1 其中农牧户贷款 2 2.个体工商户贷款 3 3.农村专业合作组织贷款 4 4.微型企业贷款 5
		 * 5.小型企业贷款 6 6.中型及以上企业贷款 7 7.其他组织贷款 8
		 */
		List<LoanStructure> hjLists = new ArrayList<LoanStructure>();
		initLists(hjLists, 8, 1, null);

		Iterator<Long> it = companyIdSetTemp.iterator();

		while (it.hasNext()) {

			Long companyId = it.next();

			List<LoanStructure> lists = new ArrayList<LoanStructure>();
			initLists(lists, 8, 1, companyId);

			System.out.println("------------1.companyId：" + companyId
					+ "---------------");

			for (LoanContract contract : loanList1) {

				int loanType = contract.getBorrowerType(); // 自然人OR企业

				if (contract.getCompanyId() == companyId) {
					if (loanType == 1) {
						// 企业客户
						EnterpriseBorrower lenderInfor = enterpriseBorrowerService
								.queryByID(contract.getBorrowerId());
						int scale = lenderInfor.getScale();
						// scale属性：1) 个体工商户 2) 农村专业合作组织 3)
						// 微型企业4)小型企业5)中型企业6)大型企业7)其他组织
						/*
						 * 1.个人贷款 2/其中农牧户贷款 3.个体工商户贷款 4.农村专业合作组织贷款 5.微型企业贷款
						 * 6.小型企业贷款 7.中型及以上企业贷款 8.其他组织贷款
						 */
						switch (scale) {
						case 1:
							lists.get(2).setDataType(3);
							countAndSetDataForLoanStructure(lists.get(2),
									contract);

							hjLists.get(2).setDataType(3);
							countAndSetDataForLoanStructure(hjLists.get(2),
									contract);
							break;
						case 2:
							lists.get(3).setDataType(4);
							countAndSetDataForLoanStructure(lists.get(3),
									contract);

							hjLists.get(3).setDataType(4);
							countAndSetDataForLoanStructure(hjLists.get(3),
									contract);
							break;
						case 3:
							lists.get(4).setDataType(5);
							countAndSetDataForLoanStructure(lists.get(4),
									contract);

							hjLists.get(4).setDataType(5);
							countAndSetDataForLoanStructure(hjLists.get(4),
									contract);
							break;
						case 4:
							lists.get(5).setDataType(6);
							countAndSetDataForLoanStructure(lists.get(5),
									contract);

							hjLists.get(5).setDataType(6);
							countAndSetDataForLoanStructure(hjLists.get(5),
									contract);
							break;
						case 5:
							lists.get(6).setDataType(7);
							countAndSetDataForLoanStructure(lists.get(6),
									contract);

							hjLists.get(6).setDataType(7);
							countAndSetDataForLoanStructure(hjLists.get(6),
									contract);
							break;
						case 6:
							lists.get(6).setDataType(7);
							countAndSetDataForLoanStructure(lists.get(6),
									contract);

							hjLists.get(6).setDataType(7);
							countAndSetDataForLoanStructure(hjLists.get(6),
									contract);
							break;
						case 7:
							lists.get(7).setDataType(8);
							countAndSetDataForLoanStructure(lists.get(7),
									contract);

							hjLists.get(7).setDataType(8);
							countAndSetDataForLoanStructure(hjLists.get(7),
									contract);
							break;
						}
					} else if (loanType == 2) {
						PersonBorrower lenderInfor = personBorrowerService
								.queryByID(contract.getBorrowerId());
						// 1.个人贷款
						lists.get(0).setDataType(1);
						countAndSetDataForLoanStructure(lists.get(0), contract);

						hjLists.get(0).setDataType(1);
						countAndSetDataForLoanStructure(hjLists.get(0),
								contract);
						int peasant = lenderInfor.getIsFarmer();
						if (peasant == 1) {
							// 2、其中农牧户贷款
							lists.get(1).setDataType(2);
							countAndSetDataForLoanStructure(lists.get(1),
									contract);

							hjLists.get(1).setDataType(2);
							countAndSetDataForLoanStructure(hjLists.get(1),
									contract);
						}
					}

				}
			}

			saveByList(lists);
		}

		saveByList(hjLists);
	}

	/**
	 * 二、借款额度
	 * 
	 * @param loanList2
	 * @throws ParseException
	 */
	private void countByAmount(List<LoanContract> loanList2,
			Set<Long> companyIdSetTemp) throws ParseException {
		Iterator<Long> it = companyIdSetTemp.iterator();

		List<LoanStructure> hjLists = new ArrayList<LoanStructure>();
		initLists(hjLists, 5, 2, null);

		while (it.hasNext()) {
			Long companyId = it.next();

			List<LoanStructure> lists = new ArrayList<LoanStructure>();
			initLists(lists, 5, 2, companyId);
			System.out.println("------------2.companyId：" + companyId
					+ "---------------");
			for (LoanContract contract : loanList2) {
				Double money = contract.getMoney();
				if (contract.getCompanyId().equals(companyId) && money != null
						&& money > 0) {
					if (money <= 50000) {
						lists.get(0).setDataType(1);
						countAndSetDataForLoanStructure(lists.get(0), contract);

						hjLists.get(0).setDataType(1);
						countAndSetDataForLoanStructure(hjLists.get(0),
								contract);
					} else if (money > 50000 && money <= 100000) {
						lists.get(1).setDataType(2);
						countAndSetDataForLoanStructure(lists.get(1), contract);

						hjLists.get(1).setDataType(2);
						countAndSetDataForLoanStructure(hjLists.get(1),
								contract);
					} else if (money > 100000 && money <= 500000) {
						lists.get(2).setDataType(3);
						countAndSetDataForLoanStructure(lists.get(2), contract);

						hjLists.get(2).setDataType(3);
						countAndSetDataForLoanStructure(hjLists.get(2),
								contract);
					} else if (money > 500000 && money <= 1000000) {
						lists.get(3).setDataType(4);
						countAndSetDataForLoanStructure(lists.get(3), contract);

						hjLists.get(3).setDataType(4);
						countAndSetDataForLoanStructure(hjLists.get(3),
								contract);
					} else if (money > 1000000) {
						lists.get(4).setDataType(5);
						countAndSetDataForLoanStructure(lists.get(4), contract);

						hjLists.get(4).setDataType(5);
						countAndSetDataForLoanStructure(hjLists.get(4),
								contract);
					}
				}
			}
			saveByList(lists);
		}

		saveByList(hjLists);
	}

	/**
	 * 三、行业类型
	 * 
	 * @param loanList3
	 * @throws ParseException
	 */
	private void countByBusiness(List<LoanContract> loanList3,
			Set<Long> companyIdSetTemp) throws ParseException {
		Iterator<Long> it = companyIdSetTemp.iterator();

		List<LoanStructure> hjLists = new ArrayList<LoanStructure>();
		initLists(hjLists, 13, 3, null);

		while (it.hasNext()) {
			Long companyId = it.next();

			List<LoanStructure> lists = new ArrayList<LoanStructure>();
			initLists(lists, 13, 3, companyId);

			System.out.println("------------3.companyId：" + companyId
					+ "---------------");

			for (LoanContract contract : loanList3) {
				int business = contract.getBusiness();
				// 本类统计有效样本：business为1-13
				if (contract.getCompanyId().equals(companyId) && business > 0) {
					lists.get(business - 1).setDataType(business);
					countAndSetDataForLoanStructure(lists.get(business - 1),
							contract);

					hjLists.get(business - 1).setDataType(business);
					countAndSetDataForLoanStructure(hjLists.get(business - 1),
							contract);
				}
			}
			saveByList(lists);

			System.out.println("------------companyId：" + companyId
					+ "--------END-------");
		}
		saveByList(hjLists);
	}

	/**
	 * 四、借款期限
	 * 
	 * @param loanlist4
	 * @throws ParseException
	 */
	private void countByTerm(List<LoanContract> loanlist4,
			Set<Long> companyIdSetTemp) throws ParseException {
		Iterator<Long> it = companyIdSetTemp.iterator();

		List<LoanStructure> hjLists = new ArrayList<LoanStructure>();
		initLists(hjLists, 4, 4, null);

		while (it.hasNext()) {
			Long companyId = it.next();

			List<LoanStructure> listsForSave4 = new ArrayList<LoanStructure>();
			initLists(listsForSave4, 4, 4, companyId);

			System.out.println("------------4.companyId：" + companyId
					+ "---------------");

			for (LoanContract contract : loanlist4) {
				if (contract.getCompanyId().equals(companyId)) {
					// 数据类型，1-4，分别对应结果表中的"3个月（含）以内","3-6个月（含）"等
					int dataType = getTerm(contract);
					if (dataType > 0) {
						listsForSave4.get(dataType - 1).setDataType(dataType);
						countAndSetDataForLoanStructure(
								listsForSave4.get(dataType - 1), contract);

						hjLists.get(dataType - 1).setDataType(dataType);
						countAndSetDataForLoanStructure(
								hjLists.get(dataType - 1), contract);
					}
				}
			}
			saveByList(listsForSave4);
		}
		saveByList(hjLists);
	}

	/**
	 * 五、担保方式 数据库合同表中 贷款方式(daiMethod)： 1）信用 2）保证 3）抵押 4）质押 5）其他 dataType: 1）信用,
	 * 2）保证, 3）其中:多户联保, 4）抵押, 5）质押, 6）其他
	 * 
	 * @param loanList5
	 * @throws ParseException
	 */
	private void countByDaiMethod(List<LoanContract> loanList5,
			Set<Long> companyIdSetTemp) throws ParseException {
		Iterator<Long> it = companyIdSetTemp.iterator();

		List<LoanStructure> hjLists = new ArrayList<LoanStructure>();
		initLists(hjLists, 6, 5, null);

		while (it.hasNext()) {
			Long companyId = it.next();

			List<LoanStructure> lists = new ArrayList<LoanStructure>();
			initLists(lists, 6, 5, companyId);

			System.out.println("------------5.companyId：" + companyId
					+ "---------------");

			for (LoanContract contract : loanList5) {
				String daiMethod = contract.getLoanMethod() + "";
				if (contract.getCompanyId().equals(companyId)
						&& daiMethod != null) {
					switch (daiMethod) {
					case "1":
						lists.get(0).setDataType(1);
						countAndSetDataForLoanStructure(lists.get(0), contract);

						hjLists.get(0).setDataType(1);
						countAndSetDataForLoanStructure(hjLists.get(0),
								contract);
						break;
					case "2":
						lists.get(1).setDataType(2);
						countAndSetDataForLoanStructure(lists.get(1), contract);

						hjLists.get(1).setDataType(2);
						countAndSetDataForLoanStructure(hjLists.get(1),
								contract);

						// 多户联保
						if (contract.getIsUnion() == 1) {
							lists.get(2).setDataType(3);
							countAndSetDataForLoanStructure(lists.get(2),
									contract);

							hjLists.get(2).setDataType(3);
							countAndSetDataForLoanStructure(hjLists.get(2),
									contract);
						}
						break;
					case "3":
						lists.get(3).setDataType(4);
						countAndSetDataForLoanStructure(lists.get(3), contract);

						hjLists.get(3).setDataType(4);
						countAndSetDataForLoanStructure(hjLists.get(3),
								contract);
						break;
					case "4":
						lists.get(4).setDataType(5);
						countAndSetDataForLoanStructure(lists.get(4), contract);

						hjLists.get(4).setDataType(5);
						countAndSetDataForLoanStructure(hjLists.get(4),
								contract);
						break;
					case "5":
						lists.get(5).setDataType(6);
						countAndSetDataForLoanStructure(lists.get(5), contract);

						hjLists.get(5).setDataType(6);
						countAndSetDataForLoanStructure(hjLists.get(5),
								contract);
						break;

					default:
						break;
					}
				}
			}
			saveByList(lists);
		}
		saveByList(hjLists);
	}

	/**
	 * 六、借款形态 statu: 未结清,已结清,逾期未结清,逾期已结清
	 * 如果逾期的，逾期天数查表RepayCount(yuQiDay),如果是展期的(合同里isExtend =
	 * "是")查表ExtendCount(yuQiDay)
	 * 
	 * @param loanList6
	 * @throws ParseException
	 */
	private void countByStatu(List<LoanContract> loanList6,
			Set<Long> companyIdSetTemp) throws ParseException {
		Iterator<Long> it = companyIdSetTemp.iterator();

		List<LoanStructure> hjLists = new ArrayList<LoanStructure>();
		initLists(hjLists, 5, 6, null);

		while (it.hasNext()) {
			/*
			 * 1.正常贷款 2.逾期1～30天 3.逾期31～60天 4.逾期61～90天 5.逾期>90天
			 */
			Long companyId = it.next();

			List<LoanStructure> lists = new ArrayList<LoanStructure>();
			initLists(lists, 5, 6, companyId);

			System.out.println("------------6.companyId：" + companyId
					+ "---------------");

			for (LoanContract contract : loanList6) {
				// String isSettle = contract.getStatusIsSettle() == 1 ? "已结清"
				// : "未结清";
				// String isOverdue = contract.getStatusIsOverdue() == 1 ? "逾期"
				// : "";
				if (contract.getCompanyId().equals(companyId)) {
					if (contract.getStatusIsOverdue() == 0) {
						// 未逾期
						lists.get(0).setDataType(1);
						countAndSetDataForLoanStructure(lists.get(0), contract);

						hjLists.get(0).setDataType(1);
						countAndSetDataForLoanStructure(hjLists.get(0),
								contract);
					} else if (contract.getStatusIsOverdue() == 1) {
						// 逾期
						int extend = contract.getIsExtend();
						Double days = 0d;
						// 是否展期
						if (extend == 0) {
							// 未展期
							days = repayPlanService
									.getOverdue(contract.getId());
						} else if (extend == 1) {
							// 展期
							days = extendCountService.getOverdue(contract
									.getId());
						}
						if (1 <= days && days <= 30) {
							lists.get(1).setDataType(2);
							countAndSetDataForLoanStructure(lists.get(1),
									contract);

							hjLists.get(1).setDataType(2);
							countAndSetDataForLoanStructure(hjLists.get(1),
									contract);
						} else if (31 <= days && days <= 60) {
							lists.get(2).setDataType(3);
							countAndSetDataForLoanStructure(lists.get(2),
									contract);

							hjLists.get(2).setDataType(3);
							countAndSetDataForLoanStructure(hjLists.get(2),
									contract);
						} else if (61 <= days && days <= 90) {
							lists.get(3).setDataType(4);
							countAndSetDataForLoanStructure(lists.get(3),
									contract);

							hjLists.get(3).setDataType(4);
							countAndSetDataForLoanStructure(hjLists.get(3),
									contract);
						} else if (days > 90) {
							lists.get(4).setDataType(5);
							countAndSetDataForLoanStructure(lists.get(4),
									contract);

							hjLists.get(4).setDataType(5);
							countAndSetDataForLoanStructure(hjLists.get(4),
									contract);
						}
					}
				}
			}
			saveByList(lists);
		}

		saveByList(hjLists);
	}

	/**
	 * 七、借款利率
	 * 
	 * @param loanList7
	 * @throws ParseException
	 */
	private void countByRate(List<LoanContract> loanList7,
			Set<Long> companyIdSetTemp) throws ParseException {

		Iterator<Long> it = companyIdSetTemp.iterator();

		List<LoanStructure> hjLists = new ArrayList<LoanStructure>();
		initLists(hjLists, 5, 7, null);

		while (it.hasNext()) {
			/*
			 * 1.年利率低于10%（含） 2.年利率介于10%-15%（含） 3.年利率介于15%-20%（含）
			 * 4.年利率介于20%-25%（含） 5.年利率高于25%
			 */
			Long companyId = it.next();

			List<LoanStructure> lists = new ArrayList<LoanStructure>();
			initLists(lists, 5, 7, companyId);

			System.out.println("------------7.companyId：" + companyId
					+ "---------------");

			for (LoanContract contract : loanList7) {
				// 年利率
				Double rate = contract.getAnnualRate();
				if (contract.getCompanyId().equals(companyId) && rate != null
						&& rate > 0) {
					if (rate <= 10) {
						lists.get(0).setDataType(1);
						countAndSetDataForLoanStructure(lists.get(0), contract);

						hjLists.get(0).setDataType(1);
						countAndSetDataForLoanStructure(hjLists.get(0),
								contract);
					} else if (10 < rate && rate <= 15) {
						lists.get(1).setDataType(2);
						countAndSetDataForLoanStructure(lists.get(1), contract);

						hjLists.get(1).setDataType(2);
						countAndSetDataForLoanStructure(hjLists.get(1),
								contract);
					} else if (15 < rate && rate <= 20) {
						lists.get(2).setDataType(3);
						countAndSetDataForLoanStructure(lists.get(2), contract);

						hjLists.get(2).setDataType(3);
						countAndSetDataForLoanStructure(hjLists.get(2),
								contract);
					} else if (20 < rate && rate <= 25) {
						lists.get(3).setDataType(4);
						countAndSetDataForLoanStructure(lists.get(3), contract);

						hjLists.get(3).setDataType(4);
						countAndSetDataForLoanStructure(hjLists.get(3),
								contract);
					} else if (25 < rate) {
						lists.get(4).setDataType(5);
						countAndSetDataForLoanStructure(lists.get(4), contract);

						hjLists.get(4).setDataType(5);
						countAndSetDataForLoanStructure(hjLists.get(4),
								contract);
					}
				}
			}
			saveByList(lists);
		}
		saveByList(hjLists);
	}

	/**
	 * 统计并设值
	 * 
	 * @param info
	 * @param contract
	 * @throws ParseException
	 */
	private void countAndSetDataForLoanStructure(LoanStructure info,
			LoanContract contract) throws ParseException {
		if (contract.getLoanBalance() != null && contract.getLoanBalance() > 0d) {

			System.out.println("id:" + contract.getId());

			Double conMoney = contract.getMoney() == null ? 0d : contract
					.getMoney();

			// 贷款余额
			Double balance = StringUtils.isBlank(info.getBalance()) ? 0d
					: Double.parseDouble(info.getBalance());
			info.setBalance(String.format("%.6f",
					balance + (contract.getLoanBalance() / 10000))); // 单位转化成万元

			// 本年累计笔数
			info.setYearCount(info.getYearCount() + 1);
			// 本年累计金额
			Double year_money = StringUtils.isBlank(info.getYearMoney()) ? 0d
					: Double.parseDouble(info.getYearMoney());
			info.setYearMoney(String.format("%.6f", year_money
					+ (conMoney / 10000)));

			Date sig = df.parse(contract.getSignTime());
			if (sig.getTime() > monthStartDate.getTime()) {
				// 本月累计笔数
				info.setMonthCount(info.getMonthCount() + 1);
				// 本年累计金额
				Double month_money = StringUtils.isBlank(info.getMonthMoney()) ? 0d
						: Double.parseDouble(info.getMonthMoney());
				info.setMonthMoney(String.format("%.6f", month_money
						+ (conMoney / 10000)));
			}
		}
	}

	/**
	 * 保存贷款结构信息
	 * 
	 * @param lists
	 */
	private void saveByList(List<LoanStructure> lists) {
		for (LoanStructure info : lists) {
			if (info.getDataType() > 0) {
				/*
				 * String companyId = info.getCompany() == null ? "--" :
				 * info.getCompany().getId()+"";
				 * System.out.println("大类:"+info.getCategory_type
				 * ()+" 小类:"+info.getData_type()+" companyId:"+companyId
				 * +" 贷款余额:"+info.getBalance()
				 * +" 年笔数:"+info.getYear_count()+" 年累计:"+info.getYear_money()
				 * +" 月笔数:"
				 * +info.getMonth_count()+" 月累计:"+info.getMonth_money());
				 */
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("searchDate", info.getSearchDate());
				param.put("flag", 1);
				List<LoanStructure> list = loanStructureService
						.queryListByWhere(param);
				if (list != null && list.size() > 0) {
					LoanStructure ls = list.get(0);
					info.setId(ls.getId());
					loanStructureService.updateByIdSelective(info);
					return;
				}
				loanStructureService.saveSelective(info);
			}
		}
	}

	/**
	 * 获取贷款期限
	 * 
	 * @param contract
	 * @return
	 */
	private int getTerm(LoanContract contract) {

		int dataType = 0;

		// 贷款期限类型：1)月,2)日,3)周,4)季,5)年
		String termType = contract.getTermType() + "";
		// 期限
		String term = StringUtils.isBlank(contract.getTerm()) ? "0" : contract
				.getTerm().trim();

		// 判断条件:借款期限
		int term2 = Integer.parseInt(term);
		if (termType.equals("1")) {
			if (term2 <= 3) {
				// 三个月内的合同
				dataType = 1;
			} else if (3 < term2 && term2 <= 6) {
				// 三到六个月合同;
				dataType = 2;
			} else if (6 < term2 && term2 <= 12) {
				// 6到12个月合同
				dataType = 3;
			} else if (12 < term2) {
				// 一年以上的合同
				dataType = 4;
			}
		} else if (termType.equals("2")) {
			if (term2 <= 30 * 3) {
				// 三个月内的合同
				dataType = 1;
			} else if (90 < term2 && term2 <= 180) {
				// 三到六个月合同;
				dataType = 2;
			} else if (180 < term2 && term2 <= 360) {
				// 6到12个月合同
				dataType = 3;
			} else if (360 < term2) {
				// 一年以上的合同
				dataType = 4;
			}
		} else if (termType.equals("3")) {
			if (term2 <= 12) {
				// 三个月内的合同
				dataType = 1;
			} else if (12 < term2 && term2 <= 24) {
				// 三到六个月合同;
				dataType = 2;
			} else if (24 < term2 && term2 <= 48) {
				// 6到12个月合同
				dataType = 3;
			} else if (48 < term2) {
				// 一年以上的合同
				dataType = 4;
			}
		} else if (termType.equals("4")) {
			// 此处处理季度类型的合同
			if (term2 <= 1) {
				// 三个月内的合同
				dataType = 1;
			} else if (1 < term2 && term2 <= 2) {
				// 三到六个月合同;
				dataType = 2;
			} else if (2 < term2 && term2 <= 4) {
				// 6到12个月合同
				dataType = 3;
			} else if (4 < term2) {
				// 一年以上的合同
				dataType = 4;
			}
		} else if (termType.equals("5")) {
			// 此处处理年类型合同
			if (term2 <= 1) {
				// 一年期合同
				// 6到12个月合同
				dataType = 3;
			} else if (term2 > 1) {
				// 一年以上的合同
				dataType = 4;
			}
		}
		return dataType;
	}

	/**
	 * 创建size个LoanStructure并初始化category_type等信息，加入到list中
	 * 
	 * @param initList
	 * @param size
	 * @param category_type
	 */
	private void initLists(List<LoanStructure> initList, int size,
			int category_type, Long companyId) {

		for (int i = 0; i < size; i++) {
			LoanStructure info = new LoanStructure();
			info.setSearchDate(preDate);
			info.setCompanyId(companyId == null ? 0 : companyId.longValue());
			info.setCategoryType(category_type); // 设置大类别
			initList.add(info);
		}
	}
}
