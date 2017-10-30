package cn.jrjzx.supervision.smallloan.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tk.mybatis.mapper.entity.Example;
import cn.jrjzx.supervision.smallloan.common.constant.Constant;
import cn.jrjzx.supervision.smallloan.config.GlobalConfig;
import cn.jrjzx.supervision.smallloan.entity.Account;
import cn.jrjzx.supervision.smallloan.entity.BankBalance;
import cn.jrjzx.supervision.smallloan.entity.DataLexicon;
import cn.jrjzx.supervision.smallloan.entity.User;
import cn.jrjzx.supervision.smallloan.service.AccountService;
import cn.jrjzx.supervision.smallloan.service.BankBalanceService;
import cn.jrjzx.supervision.smallloan.service.DataLexiconService;
import cn.jrjzx.supervision.smallloan.service.DataOptionService;

import com.github.pagehelper.PageInfo;

/**
 * 账户数据controller
 *
 */
@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	DataLexiconService dataLexiconService;
	@Autowired
	DataOptionService dataOptionService;
	@Autowired
	AccountService accountService;
	@Autowired
	BankBalanceService bankBalanceService;

	/**
	 * 跳转账户数据页面
	 * 
	 * @return
	 */
	@RequestMapping("/info")
	public String info(Map<String, Object> map, HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyId", user.getCompanyId());
		param.put("flag", 1);
		List<Account> accounts = accountService.queryListByWhere(param);// 查询指定机构下的所有账户
		for (Account account : accounts) {
			// 对查询出来的账户初始化银行名称选项，账户状态数据字典的信息
			account.setBankOption(dataOptionService.queryByCodeAndKey("bank",
					account.getBank()));
			account.setStatusOption(dataOptionService.queryByCodeAndKey(
					"account_status", account.getAccountStatus()));
		}
		map.put("rows", GlobalConfig.readProperty("page.rows"));
		map.put("accounts", accounts);
		return "account/account_info";
	}

	/**
	 * 跳转添加账户页面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/add_account")
	public String add(Map<String, Object> map) {
		DataLexicon bank = dataLexiconService.queryByCode("bank");// 银行名称数据字典
		DataLexicon accountStatus = dataLexiconService
				.queryByCode("account_status");// 银行名称数据字典
		map.put("bank", bank);
		map.put("status", accountStatus);
		return "account/add_account";
	}

	/**
	 * 跳转添加账户余额页面
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping("/add_balance")
	public String addBalance(Map<String, Object> map, HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyId", user.getCompanyId());
		param.put("flag", 1);
		List<Account> accounts = accountService.queryListByWhere(param);// 查询指定机构下的所有账户
		for (Account account : accounts) {
			// 对查询出来的账户初始化银行名称选项
			account.setBankOption(dataOptionService.queryByCodeAndKey("bank",
					account.getBank()));
		}
		map.put("accounts", accounts);
		return "account/add_balance";
	}

	/**
	 * 保存新增账户信息
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping("/save_account")
	public String saveAccount(Account account, HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		account.setCompanyId(user.getCompanyId());
		accountService.saveSelective(account);
		return "redirect:/account/info";
	}

	/**
	 * 保存新增账户余额报备
	 * 
	 * @param balance
	 * @param session
	 * @return
	 */
	@RequestMapping("/save_balance")
	public String saveBalance(BankBalance balance, HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		balance.setCompanyId(user.getCompanyId());
		bankBalanceService.saveSelective(balance);
		return "redirect:/account/info";
	}

	/**
	 * 跳转修改账户信息页面
	 * 
	 * @return
	 */
	@RequestMapping("/modify_account")
	public String modifyAccount(String accountId, Map<String, Object> map) {
		DataLexicon bank = dataLexiconService.queryByCode("bank");// 银行名称数据字典
		DataLexicon accountStatus = dataLexiconService
				.queryByCode("account_status");// 账户状态数据字典
		Account account = accountService.queryByID(Integer.parseInt(accountId));
		map.put("bank", bank);
		map.put("status", accountStatus);
		map.put("account", account);
		return "account/modify_account";
	}

	/**
	 * 跳转修改账户余额页面
	 * 
	 * @param id
	 * @param map
	 * @param session
	 * @return
	 */
	@RequestMapping("/modify_balance")
	public String modifyBalance(String id, Map<String, Object> map,
			HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("companyId", user.getCompanyId());
		param.put("flag", 1);
		List<Account> accounts = accountService.queryListByWhere(param);// 查询指定机构下的所有账户
		for (Account account : accounts) {
			// 对查询出来的账户初始化银行名称选项
			account.setBankOption(dataOptionService.queryByCodeAndKey("bank",
					account.getBank()));
		}
		BankBalance balance = bankBalanceService
				.queryByID(Integer.parseInt(id));
		map.put("balance", balance);
		map.put("accounts", accounts);
		return "account/modify_balance";
	}

	/**
	 * 更新银行账户信息
	 * 
	 * @param account
	 * @return
	 */
	@RequestMapping("/upd_account")
	public String updateAccount(Account account, HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		account.setCompanyId(user.getCompanyId());
		accountService.updateByIdSelective(account);
		return "redirect:/account/info";
	}

	/**
	 * 更新账户余额信息
	 * 
	 * @param balance
	 * @param session
	 * @return
	 */
	@RequestMapping("/upd_balance")
	public String updateBalance(BankBalance balance, HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		balance.setCompanyId(user.getCompanyId());
		bankBalanceService.updateByIdSelective(balance);
		return "redirect:/account/info";
	}

	/**
	 * ajax请求 查询机构下的所有账户余额信息，分页
	 * 
	 * @param page
	 * @param session
	 * @return
	 */
	@RequestMapping("/show_balance")
	@ResponseBody
	public ResponseEntity<PageInfo<BankBalance>> showBalance(String page,
			HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		Example example = new Example(BankBalance.class);
		example.createCriteria().andEqualTo("companyId", user.getCompanyId())
				.andEqualTo("flag", 1);
		example.setOrderByClause("date_month desc");// 根据所属月份排序
		PageInfo<BankBalance> balances = bankBalanceService.queryPageByWhere(
				example, Integer.parseInt(page),
				Integer.parseInt(GlobalConfig.readProperty("page.rows")));
		for (BankBalance balance : balances.getList()) {
			// 初始化账户余额中的账户信息已经账户信息中的银行名称数据字典选项
			balance.setAccount(accountService.queryByID(balance.getAccountId()));
			Account account = balance.getAccount();
			account.setBankOption(dataOptionService.queryByCodeAndKey("bank",
					account.getBank()));
		}
		return ResponseEntity.ok(balances);
	}

	/**
	 * 前端日期和后端的日期转化
	 */
	@InitBinder
	public void bindingPreparation(WebDataBinder binder) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor orderDateEditor = new CustomDateEditor(dateFormat,
				true);
		binder.registerCustomEditor(Date.class, orderDateEditor);
	}
}
