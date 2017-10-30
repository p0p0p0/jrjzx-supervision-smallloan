package cn.jrjzx.supervision.smallloan.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;
import cn.jrjzx.supervision.smallloan.common.util.ObjectUtil;
import cn.jrjzx.supervision.smallloan.common.util.RejoiceUtil;
import cn.jrjzx.supervision.smallloan.entity.ContractLoss;
import cn.jrjzx.supervision.smallloan.entity.EnsureContract;
import cn.jrjzx.supervision.smallloan.entity.EnterpriseBorrower;
import cn.jrjzx.supervision.smallloan.entity.ExtendRepay;
import cn.jrjzx.supervision.smallloan.entity.ExtendRepayPlan;
import cn.jrjzx.supervision.smallloan.entity.LoanContract;
import cn.jrjzx.supervision.smallloan.entity.LoanInfo;
import cn.jrjzx.supervision.smallloan.entity.MortgageContract;
import cn.jrjzx.supervision.smallloan.entity.PersonBorrower;
import cn.jrjzx.supervision.smallloan.entity.PledgeContract;
import cn.jrjzx.supervision.smallloan.entity.Repay;
import cn.jrjzx.supervision.smallloan.entity.RepayPlan;
import cn.jrjzx.supervision.smallloan.mapper.LoanContractMapper;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 *
 * LoanContractService
 * 
 * @date 2017年6月9日 下午2:49:27
 * 
 * @version 1.0.0
 *
 */
@Service
@Transactional
public class LoanContractService extends ModelService<LoanContract> {

	@Autowired
	LoanContractMapper contractMapper;

	@Autowired
	private PersonBorrowerService personBorrowerService;
	@Autowired
	private EnterpriseBorrowerService enterpriseBorrowerService;
	@Autowired
	private RepayPlanService repayPlanService;
	@Autowired
	private EnsureContractService ensureContractService;
	@Autowired
	private MortgageContractService mortgageContractService;
	@Autowired
	private PledgeContractService pledgeContractService;

	@Autowired
	private RepayService repayService;

	@Autowired
	private ExtendRepayPlanService extendRepayPlanService;

	@Autowired
	private ExtendRepayService extendRepayService;

	@Autowired
	private LoanInfoService loanInfoService;
	
	@Autowired
	private ContractLossService contractLossService;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(LoanContractService.class);

	/**
	 * 多条件查询
	 * 
	 * @param companyId
	 *            非空
	 * @param name
	 *            null时不查询该条件
	 * @param contractNumber
	 *            null时不查询该条件
	 * @param sreportTime
	 *            null时不查询该条件
	 * @param ereportTime
	 *            null时不查询该条件
	 * @param ssignTime
	 *            null时不查询该条件
	 * @param esignTime
	 *            null时不查询该条件
	 * @param smoney
	 *            0时不查询该条件
	 * @param emoney
	 *            0时不查询该条件
	 * @param sannualRate
	 *            0时不查询该条件
	 * @param eannualRate
	 *            0时不查询该条件
	 * @param status
	 *            0时不查询该条件
	 * @param isExtend
	 *            -1时不查询该条件
	 * @param term
	 *            贷款期限0 全部,1 10天以下,2 10天-30天,3 30天-90天,4 90天-半年,5 半年到1年,6 1年以上
	 * @param page
	 * @param rows
	 * @return
	 */
	@Transactional(readOnly = true)
	public PageInfo<LoanContract> findPageByParam(int companyId, String name,
			String contractNumber, String sreportTime, String ereportTime,
			String ssignTime, String esignTime, double smoney, double emoney,
			double sannualRate, double eannualRate, int status, int isExtend,
			int term, Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		LoanContractMapper lm = (LoanContractMapper) this.getMapper();
		return new PageInfo<LoanContract>(lm.findByParam(companyId, name,
				contractNumber, sreportTime, ereportTime, ssignTime, esignTime,
				smoney, emoney, sannualRate, eannualRate, status, isExtend,
				term));

	}

	@Transactional(readOnly = true)
	public PageInfo<LoanContract> queryListByPageAndOrder(
			LoanContract loanContract, Integer page, Integer rows,
			String sorts[], String orders[]) {
		// 第一个参数是起始页，第二个参数是，页面显示的数据条数
		PageHelper.startPage(page, rows);
		Example example = new Example(this.clazz);
		if (RejoiceUtil.isNotBlank(sorts) && RejoiceUtil.isNotBlank(orders)) {
			StringBuilder sortSB = new StringBuilder();
			for (int i = 0; i < sorts.length; i++) {
				sortSB.append(StringUtil.camelhumpToUnderline(sorts[i]))
						.append(" ").append(orders[i]).append(",");
			}
			example.setOrderByClause(sortSB.substring(0, sortSB.length() - 1));
		}

		// 搜索条件
		/*
		 * if(StringUtils.isNoneBlank(loanContract.getUsername())){
		 * criteria.andLike("username", "%"+loanContract.getUsername()+"%"); }
		 * if(StringUtils.isNoneBlank(loanContract.getRealName())){
		 * criteria.andLike("realName", "%"+loanContract.getRealName()+"%"); }
		 */
		List<LoanContract> list = this.getMapper().selectByExample(example);
		for (LoanContract l : list) {
			if (l.getBorrowerType() != null && l.getBorrowerType() == 1) {
				EnterpriseBorrower en = enterpriseBorrowerService.queryByID(l
						.getBorrowerId());
				l.setBorrowerName(en.getName());
			} else if (l.getBorrowerType() != null && l.getBorrowerType() == 2) {
				PersonBorrower p = personBorrowerService.queryByID(l
						.getBorrowerId());
				l.setBorrowerName(p.getName());
			}
		}

		return new PageInfo<LoanContract>(list);
	}

	/**
	 * 保存合同以及相关借款客户，还款计划，抵押合同,质押合同,保证合同
	 * 
	 * @param loanContract
	 * @param personBorrower
	 * @param enterpriseBorrower
	 * @param repayPlans
	 * @throws Exception
	 */
	public void saveAll(LoanContract loanContract) throws Exception {
		// 保存借款客户
		if (loanContract.getBorrowerType() != null
				&& loanContract.getBorrowerType() == 1) {
			// 企业
			// 判断是否存在
			EnterpriseBorrower temp = new EnterpriseBorrower();
			temp.setLicenseCode(loanContract.getEnterpriseBorrower()
					.getLicenseCode());// 根据companyId和LicenseCode判断是否存在
			temp.setFlag(1);
			temp.setCompanyId(loanContract.getCompanyId());
			List<EnterpriseBorrower> list = enterpriseBorrowerService
					.queryListByWhere(temp);
			if (list != null && list.size() > 0) {
				// 已存在的，用原来的数据，更新
				ObjectUtil.copyPropertiesIgnoreNull(
						loanContract.getEnterpriseBorrower(), list.get(0));
				enterpriseBorrowerService.updateByIdSelective(list.get(0));
				// loanContract.setEnterpriseBorrower(list.get(0));
			} else if (list != null && list.size() == 0) {
				// 不存在，判断是否有借款人数据，有则保存
				if (StringUtils.isNoneBlank(loanContract
						.getEnterpriseBorrower().getLicenseCode())) {
					enterpriseBorrowerService.saveSelective(loanContract
							.getEnterpriseBorrower());
				}
			}
			loanContract.setBorrowerId(loanContract.getEnterpriseBorrower()
					.getId());
		} else if (loanContract.getBorrowerType() != null
				&& loanContract.getBorrowerType() == 2) {
			// 自然人
			// 判断是否存在
			PersonBorrower temp = new PersonBorrower();
			temp.setCardNumber(loanContract.getPersonBorrower().getCardNumber());
			temp.setCardType(loanContract.getPersonBorrower().getCardType());// 根据companyId和CardNumber、CardType判断是否存在
			temp.setFlag(1);
			temp.setCompanyId(loanContract.getCompanyId());
			List<PersonBorrower> list = personBorrowerService
					.queryListByWhere(temp);
			if (list != null && list.size() > 0) {
				// 已存在的，用原来的数据，更新
				ObjectUtil.copyPropertiesIgnoreNull(
						loanContract.getPersonBorrower(), list.get(0));
				personBorrowerService.updateByIdSelective(list.get(0));
			} else if (list != null && list.size() == 0) {
				if (StringUtils.isNoneBlank(loanContract.getPersonBorrower()
						.getCardNumber())) {
					personBorrowerService.saveSelective(loanContract
							.getPersonBorrower());
				}
			}
			loanContract
					.setBorrowerId(loanContract.getPersonBorrower().getId());
		}
		LoanContract temp = new LoanContract();
		temp.setFlag(1);
		temp.setCompanyId(loanContract.getCompanyId());
		temp.setContractNumber(loanContract.getContractNumber());
		temp.setIsDraft(1);
		List<LoanContract> list = this.queryListByWhere(temp);
		if (list != null && list.size() > 0) {
			ObjectUtil.copyPropertiesIgnoreNull(loanContract, list.get(0));
			this.updateByIdSelective(list.get(0));
			loanContract = list.get(0);
		} else {
			// 保存合同基本信息
			this.saveSelective(loanContract);
		}
		RepayPlan rptemp = new RepayPlan();
		rptemp.setLoanContractId(loanContract.getId());
		List<RepayPlan> rplist = repayPlanService.queryListByWhere(rptemp);
		if (rplist != null && rplist.size() > 0) {
			List<String> ids = new ArrayList<String>();
			for (RepayPlan rp : rplist) {
				ids.add(rp.getId() + "");
			}
			repayPlanService.deleteByIds(ids);
		}
		// 批量保存还款计划(关联合同id)
		repayPlanService.saveBatch(loanContract.getRepayPlans(),
				loanContract.getId());

		// 保证合同,抵押合同,质押合同

		if (StringUtils.isNotBlank(loanContract.getLoanMethod())
				&& loanContract.getLoanMethod().contains("2")) {
			EnsureContract ectemp = new EnsureContract();
			ectemp.setLoanContractId(loanContract.getId());
			List<EnsureContract> eclist = ensureContractService
					.queryListByWhere(ectemp);
			if (eclist != null && eclist.size() > 0) {
				List<String> ids = new ArrayList<String>();
				for (EnsureContract ec : eclist) {
					ids.add(ec.getId() + "");
				}
				ensureContractService.deleteByIds(ids);
			}
			ensureContractService.saveBatch(loanContract.getEnsureContracts(),
					loanContract.getId());
		}
		if (StringUtils.isNotBlank(loanContract.getLoanMethod())
				&& loanContract.getLoanMethod().contains("3")) {
			MortgageContract mctemp = new MortgageContract();
			mctemp.setLoanContractId(loanContract.getId());
			List<MortgageContract> mclist = mortgageContractService
					.queryListByWhere(mctemp);
			if (mclist != null && mclist.size() > 0) {
				List<String> ids = new ArrayList<String>();
				for (MortgageContract mc : mclist) {
					ids.add(mc.getId() + "");
				}
				mortgageContractService.deleteByIds(ids);
			}
			mortgageContractService.saveBatch(
					loanContract.getMortgageContracts(), loanContract.getId());
		}
		if (StringUtils.isNotBlank(loanContract.getLoanMethod())
				&& loanContract.getLoanMethod().contains("4")) {
			PledgeContract pctemp = new PledgeContract();
			pctemp.setLoanContractId(loanContract.getId());
			List<PledgeContract> pclist = pledgeContractService
					.queryListByWhere(pctemp);
			if (pclist != null && pclist.size() > 0) {
				List<String> ids = new ArrayList<String>();
				for (PledgeContract pc : pclist) {
					ids.add(pc.getId() + "");
				}
				pledgeContractService.deleteByIds(ids);
			}
			pledgeContractService.saveBatch(loanContract.getPledgeContracts(),
					loanContract.getId());
		}
	}

	public void updateAll(LoanContract loanContract) throws Exception {
		// 保存借款客户
		if (loanContract.getBorrowerType() != null
				&& loanContract.getBorrowerType() == 1) {
			// 企业
			// 判断是否存在
			EnterpriseBorrower temp = new EnterpriseBorrower();
			temp.setLicenseCode(loanContract.getEnterpriseBorrower()
					.getLicenseCode());// 根据companyId和LicenseCode判断是否存在
			temp.setFlag(1);
			temp.setCompanyId(loanContract.getCompanyId());
			List<EnterpriseBorrower> list = enterpriseBorrowerService
					.queryListByWhere(temp);
			if (list != null && list.size() > 0) {
				// 已存在的，用原来的数据，更新
				ObjectUtil.copyPropertiesIgnoreNull(
						loanContract.getEnterpriseBorrower(), list.get(0));
				enterpriseBorrowerService.updateByIdSelective(list.get(0));
				// loanContract.setEnterpriseBorrower(list.get(0));
			} else if (list != null && list.size() == 0) {
				// 不存在，判断是否有借款人数据，有则保存
				if (StringUtils.isNoneBlank(loanContract
						.getEnterpriseBorrower().getLicenseCode())) {
					enterpriseBorrowerService.saveSelective(loanContract
							.getEnterpriseBorrower());
				}
			}
			loanContract.setBorrowerId(loanContract.getEnterpriseBorrower()
					.getId());
		} else if (loanContract.getBorrowerType() != null
				&& loanContract.getBorrowerType() == 2) {
			// 自然人
			// 判断是否存在
			PersonBorrower temp = new PersonBorrower();
			temp.setCardNumber(loanContract.getPersonBorrower().getCardNumber());
			temp.setCardType(loanContract.getPersonBorrower().getCardType());// 根据companyId和CardNumber、CardType判断是否存在
			temp.setFlag(1);
			temp.setCompanyId(loanContract.getCompanyId());
			List<PersonBorrower> list = personBorrowerService
					.queryListByWhere(temp);
			if (list != null && list.size() > 0) {
				// 已存在的，用原来的数据，更新
				ObjectUtil.copyPropertiesIgnoreNull(
						loanContract.getPersonBorrower(), list.get(0));
				personBorrowerService.updateByIdSelective(list.get(0));
			} else if (list != null && list.size() == 0) {
				if (StringUtils.isNoneBlank(loanContract.getPersonBorrower()
						.getCardNumber())) {
					personBorrowerService.saveSelective(loanContract
							.getPersonBorrower());
				}
			}
			loanContract
					.setBorrowerId(loanContract.getPersonBorrower().getId());
		}
		LoanContract temp = new LoanContract();
		temp.setFlag(1);
		temp.setCompanyId(loanContract.getCompanyId());
		temp.setContractNumber(loanContract.getContractNumber());
		temp.setIsDraft(0);
		List<LoanContract> list = this.queryListByWhere(temp);
		if (list != null && list.size() > 0) {
			ObjectUtil.copyPropertiesIgnoreNull(loanContract, list.get(0));
			this.updateByIdSelective(list.get(0));
			loanContract = list.get(0);
		} else {
			// 保存合同基本信息
			// this.saveSelective(loanContract);
			LOGGER.error("更新合同[合同编号=" + loanContract.getContractNumber()
					+ "]及合同相关信息出错！update loanContract error!");
			throw new RuntimeException("更新合同[合同编号="
					+ loanContract.getContractNumber()
					+ "]及合同相关信息出错！update loanContract error");
		}
		// 保证合同,抵押合同,质押合同

		if (StringUtils.isNotBlank(loanContract.getLoanMethod())
				&& loanContract.getLoanMethod().contains("2")) {
			EnsureContract ectemp = new EnsureContract();
			ectemp.setLoanContractId(loanContract.getId());
			List<EnsureContract> eclist = ensureContractService
					.queryListByWhere(ectemp);
			if (eclist != null && eclist.size() > 0) {
				List<String> ids = new ArrayList<String>();
				for (EnsureContract ec : eclist) {
					ids.add(ec.getId() + "");
				}
				ensureContractService.deleteByIds(ids);
			}
			ensureContractService.saveBatch(loanContract.getEnsureContracts(),
					loanContract.getId());
		}
		if (StringUtils.isNotBlank(loanContract.getLoanMethod())
				&& loanContract.getLoanMethod().contains("3")) {
			MortgageContract mctemp = new MortgageContract();
			mctemp.setLoanContractId(loanContract.getId());
			List<MortgageContract> mclist = mortgageContractService
					.queryListByWhere(mctemp);
			if (mclist != null && mclist.size() > 0) {
				List<String> ids = new ArrayList<String>();
				for (MortgageContract mc : mclist) {
					ids.add(mc.getId() + "");
				}
				mortgageContractService.deleteByIds(ids);
			}
			mortgageContractService.saveBatch(
					loanContract.getMortgageContracts(), loanContract.getId());
		}
		if (StringUtils.isNotBlank(loanContract.getLoanMethod())
				&& loanContract.getLoanMethod().contains("4")) {
			PledgeContract pctemp = new PledgeContract();
			pctemp.setLoanContractId(loanContract.getId());
			List<PledgeContract> pclist = pledgeContractService
					.queryListByWhere(pctemp);
			if (pclist != null && pclist.size() > 0) {
				List<String> ids = new ArrayList<String>();
				for (PledgeContract pc : pclist) {
					ids.add(pc.getId() + "");
				}
				pledgeContractService.deleteByIds(ids);
			}
			pledgeContractService.saveBatch(loanContract.getPledgeContracts(),
					loanContract.getId());
		}
	}

	@Override
	public void saveSelective(LoanContract loanContract) {
		loanContract.setReportTime(RejoiceUtil.getDateStr3(new Date()));// 报备时间是当前时间yyyy-MM-dd
																		// HH:mm:ss
		if (StringUtils.isNotBlank(loanContract.getSignTime())) {
			loanContract.setSignTime(loanContract.getSignTime() + " 00:00:00");// 传统小贷只录入yyyy-MM-dd,互联网小贷需录入yyyy-MM-dd
																				// HH:mm:ss
		}
		loanContract.setLoanBalance(loanContract.getMoney());// 刚录入合同时贷款余额等于贷款金额
		LOGGER.debug("start to save LoanContract," + loanContract.toString());
		super.saveSelective(loanContract);
	}

	@Override
	public void updateByIdSelective(LoanContract loanContract) {
		LOGGER.debug("start to updaate LoanContract," + loanContract.toString());
		super.updateByIdSelective(loanContract);
	}

	/**
	 * basicStatistics<br/>
	 * 基础统计
	 * 
	 * @return Map<String,Object>
	 */
	@Transactional(readOnly = true)
	public Map<String, Object> basicStatistics(Integer companyId) {
		return this.contractMapper.basicStatics(companyId);
	}

	@Transactional(readOnly = true)
	public PageInfo<Map<String, Object>> findRepayPlanAndExtendPlanAndContractList(
			Integer page, Integer rows, Integer companyId,
			String contractNumber, Integer isSettle) {
		PageHelper.startPage(page, rows);
		List<Map<String, Object>> list = this.contractMapper
				.findRepayPlanAndExtendPlanAndContractList(companyId,
						StringUtils.trim(contractNumber), isSettle);
		return new PageInfo<Map<String, Object>>(list);
	}

	@Transactional(readOnly = true)
	public LoanContract queryOneByContractNumberAndCompanyId(
			String contractNumber, Long companyId, Integer flag) {
		LoanContract contract = new LoanContract();
		contract.setFlag(flag);
		contract.setContractNumber(contractNumber);
		contract.setCompanyId(companyId);
		List<LoanContract> list = this.queryPageByWhere(contract, 1, 1)
				.getList();
		return list.size() > 0 ? list.get(0) : null;
	}

	/**
	 * deleteContract void
	 * 
	 * @throws Exception
	 */
	public void deleteContract(LoanContract lc) throws Exception {
		this.updateById(lc);

		// 抵押，保证，质押合同
		if (lc != null) {
			EnsureContract ectemp = new EnsureContract();
			ectemp.setLoanContractId(lc.getId());
			List<EnsureContract> eclist = ensureContractService
					.queryListByWhere(ectemp);
			for (EnsureContract ec : eclist) {
				ec.setFlag(0);
				ensureContractService.updateById(ec);
			}
			PledgeContract pctemp = new PledgeContract();
			pctemp.setLoanContractId(lc.getId());
			List<PledgeContract> pclist = pledgeContractService
					.queryListByWhere(pctemp);
			for (PledgeContract pc : pclist) {
				pc.setFlag(0);
				pledgeContractService.updateById(pc);
			}
			MortgageContract mctemp = new MortgageContract();
			mctemp.setLoanContractId(lc.getId());
			List<MortgageContract> mclist = mortgageContractService
					.queryListByWhere(mctemp);
			for (MortgageContract mc : mclist) {
				mc.setFlag(0);
				mortgageContractService.updateById(mc);
			}
		}
		// delete casecade

		// RepayPlan
		RepayPlan repayPlan = new RepayPlan();
		repayPlan.setFlag(0);
		repayPlan.setUpdateTime(new Date());
		Example example = new Example(RepayPlan.class);
		example.createCriteria().andEqualTo("loanContractId", lc.getId());
		repayPlanService.getMapper().updateByExampleSelective(repayPlan,
				example);
		// Repay
		Repay repay = new Repay();
		repay.setFlag(0);
		repay.setUpdateTime(new Date());
		example = new Example(Repay.class);
		example.createCriteria().andEqualTo("contractId", lc.getId());
		repayService.getMapper().updateByExampleSelective(repay, example);
		// ExtendRepayPlan
		ExtendRepayPlan extendRepayPlan = new ExtendRepayPlan();
		extendRepayPlan.setFlag(0);
		extendRepayPlan.setUpdateTime(new Date());
		example = new Example(ExtendRepayPlan.class);
		example.createCriteria().andEqualTo("contractId", lc.getId());
		extendRepayPlanService.getMapper().updateByExampleSelective(
				extendRepayPlan, example);

		// ExtendRepay
		ExtendRepay extendRepay = new ExtendRepay();
		extendRepay.setFlag(0);
		extendRepay.setUpdateTime(new Date());
		example = new Example(ExtendRepay.class);
		example.createCriteria().andEqualTo("contractId", lc.getId());
		extendRepayService.getMapper().updateByExampleSelective(extendRepay,
				example);

		// LoanInfo
		LoanInfo loanInfo = new LoanInfo();
		loanInfo.setFlag(0);
		loanInfo.setUpdateTime(new Date());
		example = new Example(LoanInfo.class);
		example.createCriteria().andEqualTo("contractId", lc.getId());
		
		// ContractLoss
		ContractLoss contractLoss = new ContractLoss();
		contractLoss.setFlag(0);
		contractLoss.setUpdateTime(new Date());
		example = new Example(ContractLoss.class);
		example.createCriteria().andEqualTo("contractNumber", lc.getContractNumber());
		contractLossService.getMapper().updateByExampleSelective(contractLoss, example);

	}

}