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
import cn.jrjzx.supervision.smallloan.entity.LoanContract;
import cn.jrjzx.supervision.smallloan.entity.Repay;
import cn.jrjzx.supervision.smallloan.entity.RepayPlan;

/**
 *
 * 还款计划
 * 
 * @date 2017年6月9日 下午2:49:27
 * 
 * @version 1.0.0
 *
 */
@Service
public class RepayService extends BaseService<Repay> {
	 
	
	@Autowired
	RepayPlanService repayPlanService;
	@Autowired
	LoanContractService contractService;
	
	/**
	 * 
	 * repayAllOverAgain
	 * @param extendRepay
	 * @param opt
	 * @throws Exception
	 * void
	 */
	public void repayAllOverAgain(Repay newRepay,String opt) throws Exception {
		/**
		 * Repay RepayPlan order by Date asc with Repay order by repayTime asc;
		 * Therefore, repay all again by Date asc every time add/update/delete;
		 */

		//get Repays ready
		//1、query all Repay
		Repay repayCon = new Repay();
		repayCon.setFlag(1);
		repayCon.setContractId(newRepay.getContractId());
		List<Repay> RepayList = this.queryListByWhere(repayCon);
		//2、add current Repay to the Repay collection and order by repayTime asc\
		if(opt.equals("A")){
			RepayList.add(newRepay);
		}
		Repay[] repayArray = RepayList
				.toArray(new Repay[] {});
		Arrays.sort(repayArray, new Comparator<Repay>() {
			@Override
			public int compare(Repay e1, Repay e2) {
				return e1.getRepayTime().compareTo(e2.getRepayTime());
			}

		});

		//get RepayPlans ready
		//3、query all RepayPlan order by endDate asc
		RepayPlan planCon = new RepayPlan();
		planCon.setFlag(1);
		planCon.setLoanContractId(newRepay.getContractId());
		String[] sorts = { "end_date" };
		String[] orders = { "asc" };
		List<RepayPlan> planList = repayPlanService
				.queryListByWhereAndOrder(planCon, sorts, orders);
		//4、reset plan
		boolean isAllSettle = true;
		for (RepayPlan plan : planList) {
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
		//5、repay by Reay with loop
		for (Repay repay : repayArray) {
			if("D".equals(opt) && repay.getId().equals(newRepay.getId())){
				continue;//the repay will be deleted;
			}
			if("U".equals(opt) && repay.getId().equals(newRepay.getId())){
				repay = newRepay;//use newRepay to repay money
			}
			//auto repay money,order by endDate asc
			Double remainMoney = repay.getMoney();
			Double remainInterest = repay.getInterest();

			int repayTimeInt = Integer.parseInt(repay.getRepayTime()
					.substring(0, 10).replace("-", ""));
			DateTime repayTime = DateTime.parse(repay.getRepayTime(),
					DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss"));
			// do repayment
			RepayPlan plan;
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
		double loanBalance = 0.0;
		for(RepayPlan plan: planList){
			//update plan
			plan.setRepaidMoney(RejoiceUtil.formatMoneyToDouble(plan.getRepaidMoney()));
			plan.setRepaidInterest(RejoiceUtil.formatMoneyToDouble(plan.getRepaidInterest()));
			repayPlanService.updateByIdSelective(plan);
			repaidMoney+=plan.getRepaidMoney();
			if(plan.getIsOverdue().intValue() == 1){
				isOverdue = 1;
			}
			if(plan.getIsSettle().intValue() == 0){
				isSettle = 0;
				loanBalance += plan.getMoney()-plan.getRepaidMoney();
			}
		}
		//7、update contract
		LoanContract updateContract = new LoanContract();
		updateContract.setId(newRepay.getContractId());
		loanBalance = RejoiceUtil.formatMoneyToDouble(loanBalance);
		updateContract.setLoanBalance(loanBalance);
		updateContract.setStatusIsOverdue(isOverdue);
		updateContract.setStatusIsSettle(isSettle);
		contractService.updateByIdSelective(updateContract);
	}
	
	
	/**
	 * addRepay
	 * @param repay
	 * void
	 * @throws Exception 
	*/
	public void addRepay(Repay repay) throws Exception {
		//1、repay all aover agin
		this.repayAllOverAgain(repay, "A");
		//2、save repay
		repay.setReportTime(RejoiceUtil.getDateStr3(new Date()));
		this.saveSelective(repay);
		
	}
	
	/**
	 * deleteRepay
	 * @param repay
	 * void
	 * @throws Exception 
	*/
	public void deleteRepay(Repay repay) throws Exception {
		//1、repay all aover agin
		this.repayAllOverAgain(repay, "D");
		//2、delete repay
		repay.setFlag(0);
		this.updateByIdSelective(repay);
		
	}
	
	
	/**
	 * deleteRepay
	 * @param repay
	 * void
	 * @throws Exception 
	*/
	public void updateRepay(Repay repay) throws Exception {
		//1、repay all aover agin
		this.repayAllOverAgain(repay, "U");
		//2、delete repay
		this.updateByIdSelective(repay);
		
	}
}