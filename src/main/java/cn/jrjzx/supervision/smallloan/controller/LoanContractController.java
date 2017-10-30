package cn.jrjzx.supervision.smallloan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import cn.jrjzx.supervision.smallloan.common.bean.EasyUIResult;
import cn.jrjzx.supervision.smallloan.common.constant.Constant;
import cn.jrjzx.supervision.smallloan.entity.DataLexicon;
import cn.jrjzx.supervision.smallloan.entity.EnsureContract;
import cn.jrjzx.supervision.smallloan.entity.EnterpriseBorrower;
import cn.jrjzx.supervision.smallloan.entity.LoanContract;
import cn.jrjzx.supervision.smallloan.entity.LoanInfo;
import cn.jrjzx.supervision.smallloan.entity.MortgageContract;
import cn.jrjzx.supervision.smallloan.entity.PersonBorrower;
import cn.jrjzx.supervision.smallloan.entity.PledgeContract;
import cn.jrjzx.supervision.smallloan.entity.RepayPlan;
import cn.jrjzx.supervision.smallloan.entity.User;
import cn.jrjzx.supervision.smallloan.service.DataLexiconService;
import cn.jrjzx.supervision.smallloan.service.EnsureContractService;
import cn.jrjzx.supervision.smallloan.service.EnterpriseBorrowerService;
import cn.jrjzx.supervision.smallloan.service.ExtendRepayPlanService;
import cn.jrjzx.supervision.smallloan.service.ExtendRepayService;
import cn.jrjzx.supervision.smallloan.service.LoanContractService;
import cn.jrjzx.supervision.smallloan.service.LoanInfoService;
import cn.jrjzx.supervision.smallloan.service.MortgageContractService;
import cn.jrjzx.supervision.smallloan.service.PersonBorrowerService;
import cn.jrjzx.supervision.smallloan.service.PledgeContractService;
import cn.jrjzx.supervision.smallloan.service.RepayPlanService;
import cn.jrjzx.supervision.smallloan.service.RepayService;

import com.github.pagehelper.PageInfo;

/**
 *
 * LoanContractController
 * 
 * @date 2017年6月8日 下午5:38:42
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("loanContract")
public class LoanContractController extends
		BaseController<LoanContract, LoanContractService> {

	@Autowired
	private PersonBorrowerService personBorrowerService;
	@Autowired
	private EnterpriseBorrowerService enterpriseBorrowerService;
	@Autowired
	private RepayPlanService repayPlanService;
	@Autowired
	private RepayService repayService;
	@Autowired
	private ExtendRepayPlanService extendRepayPlanService;
	@Autowired
	private ExtendRepayService extendRepayService;
	@Autowired
	private LoanContractService loanContractService;
	@Autowired
	private EnsureContractService ensureContractService;
	@Autowired
	private MortgageContractService mortgageContractService;
	@Autowired
	private PledgeContractService pledgeContractService;
	@Autowired
	DataLexiconService dataLexiconService;
	@Autowired
	LoanInfoService loanInfoService;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(LoanContractController.class);

	@GetMapping("contract-num/{contractNum}")
	public Integer isContractNumberExist(
			@PathVariable("contractNum") String contractNum,
			HttpServletRequest request) {
		LoanContract contract = new LoanContract();
		contract.setCompanyId(((User) request.getSession().getAttribute(
				Constant.SESSION_KEY)).getCompanyId().longValue());
		contract.setContractNumber(contractNum);
		contract.setFlag(1);
		contract.setIsDraft(0);
		List<LoanContract> list = this.loanContractService.queryPageByWhere(
				contract, 1, 1).getList();
		return list.size() == 0 ? 0 : 1;
	}

	/**
	 * 跳转添加合同页
	 * 
	 * @param request
	 * @return
	 */
	@GetMapping("toAdd")
	public ModelAndView toAdd(HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		DataLexicon lexicon = dataLexiconService.queryByCode("card_type");// 证件类型数据字典
		model.addObject("lexicon", lexicon);
		model.setViewName("loan_contract/add_loan_contract");
		return model;
	}

	/**
	 * 跳转更新合同页
	 * 
	 * @param contractId
	 * @return
	 */
	@GetMapping("toUpdate")
	public ModelAndView toUpdate(String contractId) {
		ModelAndView model = new ModelAndView();
		LoanContract contract = loanContractService.queryByID(Long
				.parseLong(contractId));
		// 设置企业借款人和自然人借款人，避免jsp空值时异常
		contract.setEnterpriseBorrower(new EnterpriseBorrower());
		contract.setPersonBorrower(new PersonBorrower());

		if (contract.getBorrowerType() == 1) {
			// 企业借款人
			contract.setEnterpriseBorrower(enterpriseBorrowerService
					.queryByID(contract.getBorrowerId() == null ? 0 : contract
							.getBorrowerId()));

		} else if (contract.getBorrowerType() == 2) {
			// 自然人借款人
			contract.setPersonBorrower(personBorrowerService.queryByID(contract
					.getBorrowerId() == null ? 0 : contract.getBorrowerId()));

		}
		// 还款计划
		RepayPlan temp = new RepayPlan();
		temp.setLoanContractId(contract.getId());
		List<RepayPlan> list = repayPlanService.queryListByWhere(temp);
		contract.setRepayPlans(list);

		if (StringUtils.isNotBlank(contract.getLoanMethod())) {
			// 担保，质押，抵押合同
			if (contract.getLoanMethod().contains("2")) {
				EnsureContract ectemp = new EnsureContract();
				ectemp.setLoanContractId(contract.getId());
				contract.setEnsureContracts(ensureContractService
						.queryListByWhere(ectemp));
			}
			if (contract.getLoanMethod().contains("3")) {
				MortgageContract mctemp = new MortgageContract();
				mctemp.setLoanContractId(contract.getId());
				contract.setMortgageContracts(mortgageContractService
						.queryListByWhere(mctemp));
			}
			if (contract.getLoanMethod().contains("4")) {
				PledgeContract pctemp = new PledgeContract();
				pctemp.setLoanContractId(contract.getId());
				contract.setPledgeContracts(pledgeContractService
						.queryListByWhere(pctemp));
			}
		}
		DataLexicon lexicon = dataLexiconService.queryByCode("card_type");// 证件类型数据字典
		model.addObject("lexicon", lexicon);
		model.addObject("contract", contract);
		model.setViewName("loan_contract/update_loan_contract");
		return model;
	}

	/**
	 * 存稿后继续编辑
	 * 
	 * @param contractId
	 * @return
	 */
	@GetMapping("continue")
	public ModelAndView toContinue(String contractId) {
		ModelAndView model = new ModelAndView();
		LoanContract contract = loanContractService.queryByID(Long
				.parseLong(contractId));
		// 设置企业借款人和自然人借款人，避免jsp空值时异常
		contract.setEnterpriseBorrower(new EnterpriseBorrower());
		contract.setPersonBorrower(new PersonBorrower());
		if (contract.getBorrowerType() != null) {
			if (contract.getBorrowerType() == 1) {
				// 企业借款人
				contract.setEnterpriseBorrower(enterpriseBorrowerService
						.queryByID(contract.getBorrowerId() == null ? 0
								: contract.getBorrowerId()));

			} else if (contract.getBorrowerType() == 2) {
				// 自然人借款人
				contract.setPersonBorrower(personBorrowerService
						.queryByID(contract.getBorrowerId() == null ? 0
								: contract.getBorrowerId()));

			}
		} else {
			contract.setBorrowerType(1);
		}
		// 还款计划
		RepayPlan temp = new RepayPlan();
		temp.setLoanContractId(contract.getId());
		List<RepayPlan> list = repayPlanService.queryListByWhere(temp);
		contract.setRepayPlans(list);

		if (StringUtils.isNotBlank(contract.getLoanMethod())) {
			// 担保，质押，抵押合同
			if (contract.getLoanMethod().contains("2")) {
				EnsureContract ectemp = new EnsureContract();
				ectemp.setLoanContractId(contract.getId());
				contract.setEnsureContracts(ensureContractService
						.queryListByWhere(ectemp));
			}
			if (contract.getLoanMethod().contains("3")) {
				MortgageContract mctemp = new MortgageContract();
				mctemp.setLoanContractId(contract.getId());
				contract.setMortgageContracts(mortgageContractService
						.queryListByWhere(mctemp));
			}
			if (contract.getLoanMethod().contains("4")) {
				PledgeContract pctemp = new PledgeContract();
				pctemp.setLoanContractId(contract.getId());
				contract.setPledgeContracts(pledgeContractService
						.queryListByWhere(pctemp));
			}
		}
		DataLexicon lexicon = dataLexiconService.queryByCode("card_type");// 证件类型数据字典
		model.addObject("lexicon", lexicon);
		model.addObject("contract", contract);
		model.setViewName("loan_contract/add_loan_contract");
		return model;
	}

	/**
	 * 修改更新合同
	 * 
	 * @param loanContract
	 * @param request
	 * @return
	 */
	@PostMapping("update")
	public ModelAndView update(LoanContract loanContract,
			HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/loanContract/list");

		User user = (User) request.getSession().getAttribute(
				Constant.SESSION_KEY);
		Long companyId = user.getCompanyId().longValue();
		loanContract.setCompanyId(companyId);// 设置为登录用户的机构id
		loanContract.setIsDraft(0);// 不是存稿

		// 给借款客户设置companyId
		if (loanContract.getBorrowerType() != null
				&& loanContract.getBorrowerType() == 2
				&& loanContract.getPersonBorrower() != null) {
			loanContract.getPersonBorrower().setCompanyId(companyId);
		} else if (loanContract.getBorrowerType() != null
				&& loanContract.getBorrowerType() == 1
				&& loanContract.getEnterpriseBorrower() != null) {
			loanContract.getEnterpriseBorrower().setCompanyId(companyId);
		}

		try {
			loanContractService.updateAll(loanContract);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("保存合同[合同编号=" + loanContract.getContractNumber()
					+ "]及合同相关信息出错！save loanContract error message="
					+ e.getMessage());
			throw new RuntimeException("保存合同[合同编号="
					+ loanContract.getContractNumber()
					+ "]及合同相关信息出错！save loanContract error message="
					+ e.getMessage());
		}

		return model;
	}

	/**
	 * 保存新增借款合同
	 * 
	 * @param loanContract
	 * @param request
	 * @return
	 */
	@PostMapping("save")
	public ModelAndView save(LoanContract loanContract,
			HttpServletRequest request) {

		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/loanContract/list");

		User user = (User) request.getSession().getAttribute(
				Constant.SESSION_KEY);
		Long companyId = user.getCompanyId().longValue();
		loanContract.setCompanyId(companyId);
		loanContract.setIsDraft(0);

		// 判断同一小贷公司是否已存在相同的合同编号
		LoanContract temp = new LoanContract();
		temp.setFlag(1);
		temp.setCompanyId(companyId);
		temp.setIsDraft(0);
		temp.setContractNumber(loanContract.getContractNumber());
		List<LoanContract> list = loanContractService.queryListByWhere(temp);
		if (list != null && list.size() > 0) {
			// 同一个小贷公司已有重复合同编号，不能继续新增
			throw new RuntimeException("贷款合同编号["
					+ loanContract.getContractNumber() + "]已存在，不能继续新增！");
		}

		// 给借款客户设置companyId
		if (loanContract.getBorrowerType() != null
				&& loanContract.getBorrowerType() == 2
				&& loanContract.getPersonBorrower() != null) {
			loanContract.getPersonBorrower().setCompanyId(companyId);
		} else if (loanContract.getBorrowerType() != null
				&& loanContract.getBorrowerType() == 1
				&& loanContract.getEnterpriseBorrower() != null) {
			loanContract.getEnterpriseBorrower().setCompanyId(companyId);
		}

		try {
			loanContractService.saveAll(loanContract);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("保存合同[合同编号=" + loanContract.getContractNumber()
					+ "]及合同相关信息出错！save loanContract error message="
					+ e.getMessage());
			throw new RuntimeException("保存合同[合同编号="
					+ loanContract.getContractNumber()
					+ "]及合同相关信息出错！save loanContract error message="
					+ e.getMessage());
		}

		return model;
	}

	/**
	 * 存稿
	 * 
	 * @param loanContract
	 * @param request
	 * @return
	 */
	@PostMapping("saveDraft")
	public ModelAndView saveDraft(LoanContract loanContract,
			HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/loanContract/list");

		User user = (User) request.getSession().getAttribute(
				Constant.SESSION_KEY);
		Long companyId = user.getCompanyId().longValue();
		loanContract.setCompanyId(companyId);
		loanContract.setIsDraft(1);

		// 给借款客户设置companyId
		if (loanContract.getBorrowerType() != null
				&& loanContract.getBorrowerType() == 2
				&& loanContract.getPersonBorrower() != null) {
			loanContract.getPersonBorrower().setCompanyId(companyId);
		} else if (loanContract.getBorrowerType() != null
				&& loanContract.getBorrowerType() == 1
				&& loanContract.getEnterpriseBorrower() != null) {
			loanContract.getEnterpriseBorrower().setCompanyId(companyId);
		}

		try {
			loanContractService.saveAll(loanContract);
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("保存合同[合同编号=" + loanContract.getContractNumber()
					+ "]及合同相关信息出错！save loanContract error message="
					+ e.getMessage());
			throw new RuntimeException("保存合同[合同编号="
					+ loanContract.getContractNumber()
					+ "]及合同相关信息出错！save loanContract error message="
					+ e.getMessage());
		}

		return model;
	}

	/**
	 * 合同存稿列表
	 * 
	 * @param session
	 * @return
	 */
	@GetMapping("listDraft")
	public ResponseEntity<EasyUIResult> listDraft(HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		LoanContract temp = new LoanContract();
		temp.setFlag(1);
		temp.setCompanyId((long) user.getCompanyId());
		temp.setIsDraft(1);
		List<LoanContract> list = loanContractService.queryListByWhere(temp);
		EasyUIResult easyUIResult = new EasyUIResult(0, list) {
			public List<?> getData() {
				return this.getRows();
			}
		};
		return ResponseEntity.ok(easyUIResult);
	}

	/**
	 * 跳转合同列表页
	 * 
	 * @return
	 */
	@GetMapping("list")
	public ModelAndView list() {
		ModelAndView model = new ModelAndView();
		// 分页查询合同数据
		// PageInfo<LoanContract> page = loanContractService
		// .queryListByPageAndOrder(loanContract, 1, 30, null, null);
		// 分页查询合同存稿数据

		// model.addObject("page", page);
		model.setViewName("loan_contract/contract_list");
		return model;
	}

	/**
	 * 合同条件查询
	 * 
	 * @param offset
	 * @param rows
	 * @param name
	 * @param contractNumber
	 * @param sreportTime
	 * @param ereportTime
	 * @param ssignTime
	 * @param esignTime
	 * @param smoney
	 * @param emoney
	 * @param sannualRate
	 * @param eannualRate
	 * @param status
	 * @param isExtend
	 * @param term
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@GetMapping("search")
	public ResponseEntity<EasyUIResult> searchByPage(
			@RequestParam(value = "offset", defaultValue = "0") Integer offset,
			@RequestParam(value = "limit", defaultValue = "10") Integer rows,
			String name, String contractNumber, String sreportTime,
			String ereportTime, String ssignTime, String esignTime,
			String smoney, String emoney, String sannualRate,
			String eannualRate, String status, String isExtend, String term,
			HttpSession session) throws Exception {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		PageInfo<LoanContract> pageInfo = loanContractService
				.findPageByParam(
						user.getCompanyId(),
						StringUtils.isBlank(name) ? null : name,
						StringUtils.isBlank(contractNumber) ? null
								: contractNumber,
						StringUtils.isBlank(sreportTime) ? null : sreportTime,
						StringUtils.isBlank(ereportTime) ? null : ereportTime,
						StringUtils.isBlank(ssignTime) ? null : ssignTime,
						StringUtils.isBlank(esignTime) ? null : esignTime,
						StringUtils.isBlank(smoney) ? 0 : Double
								.parseDouble(smoney),
						StringUtils.isBlank(emoney) ? 0 : Double
								.parseDouble(emoney),
						StringUtils.isBlank(sannualRate) ? 0 : Double
								.parseDouble(sannualRate),
						StringUtils.isBlank(eannualRate) ? 0 : Double
								.parseDouble(eannualRate),
						StringUtils.isBlank(status) ? 0 : Integer
								.parseInt(status),
						StringUtils.isBlank(isExtend) ? -1 : Integer
								.parseInt(isExtend),
						StringUtils.isBlank(term) ? 0 : Integer.parseInt(term),
						(offset / rows + 1), rows);
		EasyUIResult easyUIResult = new EasyUIResult(pageInfo.getTotal(),
				pageInfo.getList());
		return ResponseEntity.ok(easyUIResult);
	}

	/**
	 * 逻辑删除合同
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping("delete/{id}")
	public void deleteById(@PathVariable("id") String id) {
		ModelAndView model = new ModelAndView();
		model.setViewName("redirect:/loanContract/list");
		LoanContract lc = loanContractService.queryByID(Long
				.parseLong(StringUtils.isNoneBlank(id) ? id : "0"));
		if (lc != null) {
			lc.setFlag(0);
			try {
				loanContractService.deleteContract(lc);
			} catch (Exception e) {
				LOGGER.error("delete contract error:", e);
				e.printStackTrace();
			}
		}
	}

	/**
	 * 查看合同
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@GetMapping("view")
	public ModelAndView view(Long id, HttpServletRequest request) {
		ModelAndView model = new ModelAndView();
		DataLexicon lexicon = dataLexiconService.queryByCode("card_type");// 证件类型数据字典
		model.addObject("lexicon", lexicon);
		// 贷款合同信息
		LoanContract loanContract = loanContractService.queryByID(id);
		String loanMethod = loanContract.getLoanMethod();
		loanMethod = loanMethod.replace("1", "信用");
		loanMethod = loanMethod.replace("2", "保证");
		loanMethod = loanMethod.replace("3", "抵押");
		loanMethod = loanMethod.replace("4", "质押");
		loanMethod = loanMethod.replace("5", "其它");
		loanContract.setLoanMethod(loanMethod);
		model.addObject("loanContract", loanContract);

		// 借款人信息
		if (loanContract.getBorrowerType() != null && loanContract.getBorrowerId() != null
				&& loanContract.getBorrowerType() == 1) {
			// 企业
			EnterpriseBorrower enterpriseBorrower = enterpriseBorrowerService
					.queryByID(loanContract.getBorrowerId());
			loanContract.setBorrowerName(enterpriseBorrower.getName());
			loanContract.setEnterpriseBorrower(enterpriseBorrower);
		} else if (loanContract.getBorrowerType() != null  && loanContract.getBorrowerId() != null
				&& loanContract.getBorrowerType() == 2) {
			// 自然人
			PersonBorrower personBorrower = personBorrowerService
					.queryByID(loanContract.getBorrowerId());
			loanContract.setBorrowerName(personBorrower.getName());
			loanContract.setPersonBorrower(personBorrower);
		}

		// 放款信息
		LoanInfo loanInfo = new LoanInfo();
		loanInfo.setContractId(loanContract.getId());
		loanInfo.setFlag(1);
		List<LoanInfo> loanInfos = loanInfoService.queryListByWhere(loanInfo);
		model.addObject("loanInfos", loanInfos);

		/*
		 * //计划还款信息 RepayPlan repayPlan = new RepayPlan();
		 * repayPlan.setLoanContractId(loanContract.getId());
		 * repayPlan.setFlag(1); List<RepayPlan> repayPlans =
		 * repayPlanService.queryListByWhere(repayPlan);
		 * model.addObject("repayPlans", repayPlans);
		 * 
		 * //实际还款信息 Repay repay = new Repay();
		 * repay.setContractId(loanContract.getId()); repay.setFlag(1);
		 * List<Repay> repays = repayService.queryListByWhere(repay);
		 * model.addObject("repays", repays);
		 * 
		 * //展期计划还款信息 ExtendRepayPlan extendRepayPlan = new ExtendRepayPlan();
		 * extendRepayPlan.setContractId(loanContract.getId());
		 * extendRepayPlan.setFlag(1); List<ExtendRepayPlan> extendRepayPlans =
		 * extendRepayPlanService.queryListByWhere(extendRepayPlan);
		 * model.addObject("extendRepayPlans", extendRepayPlans);
		 * 
		 * //展期实际还款信息 ExtendRepay extendRepay = new ExtendRepay();
		 * extendRepay.setContractId(loanContract.getId());
		 * extendRepay.setFlag(1); List<ExtendRepay> extendRepays =
		 * extendRepayService.queryListByWhere(extendRepay);
		 * model.addObject("extendRepays", extendRepays);
		 * 
		 * 
		 * 
		 * String loanMethod =
		 * StringUtils.isNotBlank(loanContract.getLoanMethod()) ?
		 * loanContract.getLoanMethod().trim() : ""; //保证合同信息
		 * if(loanMethod.contains("2")){ EnsureContract ens = new
		 * EnsureContract(); ens.setLoanContractId(loanContract.getId());
		 * ens.setFlag(1); List<EnsureContract> ensureContracts =
		 * ensureContractService.queryListByWhere(ens);
		 * model.addObject("ensureContracts", ensureContracts); }
		 * 
		 * //抵押合同信息 if(loanMethod.contains("3")){ MortgageContract mort = new
		 * MortgageContract(); mort.setLoanContractId(loanContract.getId());
		 * mort.setFlag(1); List<MortgageContract> mortgageContracts =
		 * mortgageContractService.queryListByWhere(mort);
		 * model.addObject("mortgageContracts", mortgageContracts); } //质押合同信息
		 * if(loanMethod.contains("4")){ PledgeContract pled = new
		 * PledgeContract(); pled.setLoanContractId(loanContract.getId());
		 * pled.setFlag(1); List<PledgeContract> pledgeContracts =
		 * pledgeContractService.queryListByWhere(pled);
		 * model.addObject("pledgeContracts", pledgeContracts); }
		 */

		model.setViewName("loan_contract/contract_view");
		return model;
	}
}
