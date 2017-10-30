/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.service
 * ExtendRepayPlanService.java
 * 
 * 2017年6月9日-下午2:49:27
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jrjzx.supervision.smallloan.common.util.RejoiceUtil;
import cn.jrjzx.supervision.smallloan.entity.ExtendRepay;
import cn.jrjzx.supervision.smallloan.entity.ExtendRepayPlan;
import cn.jrjzx.supervision.smallloan.entity.LoanContract;
import cn.jrjzx.supervision.smallloan.mapper.ExtendRepayMapper;

/**
 *
 * ExtendRepayPlanService
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月9日 下午2:49:27
 * 
 * @version 1.0.0
 *
 */
@Service
public class ExtendRepayService extends BaseService<ExtendRepay> {

	@Autowired
	ExtendRepayMapper extendRepayMapper;

	@Autowired
	ExtendRepayPlanService extendRepayPlanService;
	@Autowired
	LoanContractService contractService;

	@Autowired
	RepayPlanService repayPlanService; 
	/**
	 * 
	 * repayAllOverAgain
	 * @param extendRepay
	 * @param opt
	 * @throws Exception
	 * void
	 */
	public void repayAllOverAgain(ExtendRepay extendRepay,String opt) throws Exception {
		/**
		 * Repay extendRepayPlan order by extendDate asc with extendRepay order by repayTime asc;
		 * Therefore, repay all again by extendDate asc every time add/update/delete;
		 */

		//get extendRepays ready
		//1、query all extendRepay
		ExtendRepay repayCon = new ExtendRepay();
		repayCon.setFlag(1);
		repayCon.setContractId(extendRepay.getContractId());
		repayCon.setExtendDate(extendRepay.getExtendDate());
		List<ExtendRepay> extendRepayList = this.queryListByWhere(repayCon);
		//2、add current extendRepay to the extendRepay collection and order by repayTime asc\
		if(opt.equals("A")){
			extendRepayList.add(extendRepay);
		}
		ExtendRepay[] extendRepayArray = extendRepayList
				.toArray(new ExtendRepay[] {});
		Arrays.sort(extendRepayArray, new Comparator<ExtendRepay>() {
			@Override
			public int compare(ExtendRepay e1, ExtendRepay e2) {
				return e1.getRepayTime().compareTo(e2.getRepayTime());
			}

		});

		//get extendRepayPlans ready
		//3、query all extendRepayPlan order by endDate asc
		ExtendRepayPlan planCon = new ExtendRepayPlan();
		planCon.setFlag(1);
		planCon.setContractId(extendRepay.getContractId());
		String[] sorts = { "end_date" };
		String[] orders = { "asc" };
		List<ExtendRepayPlan> planList = extendRepayPlanService
				.queryListByWhereAndOrder(planCon, sorts, orders);
		//4、reset plan
		boolean isAllSettle = true;
		for (ExtendRepayPlan plan : planList) {
			if(plan.getIsSettle().intValue()==0){
				isAllSettle = false;
			}
			plan.setRepaidMoney(0.0);
			plan.setRepaidInterest(0.0);
			plan.setIsOverdue(0);
			plan.setIsSettle(0);
			plan.setOverdue(0);
		}
		if(isAllSettle && "A".equals(opt))return;//all settle,no need to repay
		//do the repayment
		//5、repay by extendReay with loop
		for (ExtendRepay repay : extendRepayArray) {
			if("D".equals(opt) && repay.getId().equals(extendRepay.getId())){
				continue;//the repay will be deleted;
			}
			if("U".equals(opt) && repay.getId().equals(extendRepay.getId())){
				repay = extendRepay;//use newExtendRepay to repay money
			}
			//auto repay money,order by endDate asc
			Double remainMoney = repay.getMoney();
			Double remainInterest = repay.getInterest();

			int repayTimeInt = Integer.parseInt(repay.getRepayTime()
					.substring(0, 10).replace("-", ""));
			DateTime repayTime = DateTime.parse(repay.getRepayTime(),
					DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
			// do repayment
			ExtendRepayPlan plan;
			for (int i = 0; i < planList.size(); i++) {
				plan = planList.get(i);
				if(plan.getIsSettle().intValue() == 1){
					continue;
				}
				if (remainInterest > 0 || remainMoney > 0) {
					//repay con:aleast one gt 0
					if (remainMoney > 0) {
						//repay money
						Double remainRepaidMoney = plan.getMoney()
								- plan.getRepaidMoney();
						if (remainRepaidMoney <= remainMoney) {
							//settle
							plan.setRepaidMoney(plan.getMoney());
							plan.setIsSettle(1);
							remainMoney -= remainRepaidMoney;
						} else {
							//unSettle
							plan.setRepaidMoney(plan.getRepaidMoney()
									+ remainMoney);
							remainMoney = 0.0;
						}
						//overdue
						if (repayTimeInt > Integer.parseInt(plan.getEndDate()
								.replace("-", ""))) {
							plan.setIsOverdue(1);
							DateTime endDate = DateTime.parse(
									plan.getEndDate(),
									DateTimeFormat.forPattern("yyyy-MM-dd"));
							int overdue = Days.daysBetween(
									endDate.withTimeAtStartOfDay(),
									repayTime.withTimeAtStartOfDay()).getDays();
							plan.setOverdue(overdue);
						}
					}
					if (remainInterest > 0) {
						//repay interest
						if (i == planList.size() - 1) {
							//last one,repay all interest
							plan.setRepaidInterest(remainInterest);
							remainInterest = 0.0;
						} else {
							Double remainRepaidInterest = plan.getInterest()
									- plan.getRepaidInterest();
							if (remainRepaidInterest > 0) {//can repaid
								if (remainRepaidInterest < remainInterest) {
									//repay all remainRepaidInterest in plan
									plan.setRepaidInterest(plan.getInterest());
									remainInterest -= remainRepaidInterest;
								} else {
									//repay remain interest
									plan.setRepaidInterest(plan
											.getRepaidInterest()
											+ remainInterest);
									remainInterest = 0.0;
								}
							}

						}
					}
				}

				if (remainMoney <= 0 && remainInterest <= 0) {
					break;
				}
			}

		}
		
		
		
		//6、update all plan
		Double repaidMoney = 0d;
		int isOverdue = 0;
		int isSettle = 1;
		double balance = 0.0;
		for(ExtendRepayPlan plan: planList){
			//update plan
			plan.setRepaidMoney(RejoiceUtil.formatMoneyToDouble(plan.getRepaidMoney()));
			plan.setRepaidInterest(RejoiceUtil.formatMoneyToDouble(plan.getRepaidInterest()));
			extendRepayPlanService.updateByIdSelective(plan);
			repaidMoney+=plan.getRepaidMoney();
			if(plan.getIsOverdue().intValue() == 1){
				isOverdue = 1;
			}
			if(plan.getIsSettle().intValue() == 0){
				isSettle = 0;
				balance += plan.getMoney()-plan.getRepaidMoney();
			}
		}
		//7、update contract
		LoanContract updateContract = new LoanContract();
		updateContract.setId(extendRepay.getContractId());
		updateContract.setLoanBalance(balance);
		updateContract.setStatusIsOverdue(isOverdue);
		updateContract.setStatusIsSettle(isSettle);
		contractService.updateByIdSelective(updateContract);

	}
	/**
	 * addExtendRepay
	 * @param repay
	 * void
	 * @throws Exception 
	*/
	public void addExtendRepay(ExtendRepay extendRepay) throws Exception {
		//1、repay all aover agin
		this.repayAllOverAgain(extendRepay, "A");
		//2、save repay
		extendRepay.setReportTime(RejoiceUtil.getDateStr3(new Date()));
		this.saveSelective(extendRepay);
		
	}
	
	/**
	 * deleteExtendRepay
	 * @param repay
	 * void
	 * @throws Exception 
	*/
	public void deleteExtendRepay(ExtendRepay repay) throws Exception {
		//1、repay all aover agin
		this.repayAllOverAgain(repay, "D");
		//2、delete repay
		repay.setFlag(0);
		this.updateByIdSelective(repay);
		
	}
	
	
	/**
	 * deleteExtendRepay
	 * @param repay
	 * void
	 * @throws Exception 
	*/
	public void updateExtendRepay(ExtendRepay extendRepay) throws Exception {
		//1、repay all aover agin
		this.repayAllOverAgain(extendRepay, "U");
		//2、delete repay
		this.updateByIdSelective(extendRepay);
		
	}

}