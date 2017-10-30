package cn.jrjzx.supervision.smallloan.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
import cn.jrjzx.supervision.smallloan.common.constant.Constant;
import cn.jrjzx.supervision.smallloan.common.util.ExcelUtils;
import cn.jrjzx.supervision.smallloan.config.GlobalConfig;
import cn.jrjzx.supervision.smallloan.entity.Balance;
import cn.jrjzx.supervision.smallloan.entity.CashFlow;
import cn.jrjzx.supervision.smallloan.entity.Company;
import cn.jrjzx.supervision.smallloan.entity.FileUpload;
import cn.jrjzx.supervision.smallloan.entity.Profit;
import cn.jrjzx.supervision.smallloan.entity.User;
import cn.jrjzx.supervision.smallloan.response.ResponseCode;
import cn.jrjzx.supervision.smallloan.response.ResponseVo;
import cn.jrjzx.supervision.smallloan.service.BalanceService;
import cn.jrjzx.supervision.smallloan.service.CashFlowService;
import cn.jrjzx.supervision.smallloan.service.CompanyService;
import cn.jrjzx.supervision.smallloan.service.FileUploadService;
import cn.jrjzx.supervision.smallloan.service.ProfitService;

import com.github.pagehelper.PageInfo;

/**
 * 
 * 财务信息controller
 */
@Controller
@RequestMapping("/finance")
public class FinanceController {
	@Autowired
	ProfitService profitService;
	@Autowired
	CashFlowService cashFlowService;
	@Autowired
	BalanceService balanceService;
	@Autowired
	CompanyService companyService;
	@Autowired
	FileUploadService fileUploadService;

	/**
	 * 跳转财务数据页面
	 * 
	 * @param map
	 *            页面显示的数据
	 * @return
	 */
	@RequestMapping("/info")
	public String info(Map<String, Object> map) {
		return "finance/finance_data";
	}

	/**
	 * 跳转财务数据添加页面
	 * 
	 * @return
	 */
	@RequestMapping("/add_finance")
	public String addFinance() {
		return "finance/add_finance";
	}

	/**
	 * 保存资产负债表
	 * 
	 * @return
	 */
	@RequestMapping("save_balance")
	public String saveBalance(String dateMonth, MultipartFile file,
			HttpSession session) {
		if (StringUtils.isBlank(dateMonth)) {// 所属月份不能为空
			return "redirect:/finance/add_finance";
		}
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		Company company = companyService.queryByID(user.getCompanyId());

		// 文件保存到磁盘
		File dir = new File(GlobalConfig.readProperty("finance.dir"));// 检查保存文件的目录是否存在，不存在则创建目录
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File saveFile = new File(GlobalConfig.readProperty("finance.dir")
				+ company.getId() + "_balance_" + file.getOriginalFilename());// 保存到本地的文件
		FileOutputStream fos = null;
		try {
			if (saveFile.exists()) {
				saveFile.delete();// 文件存在先删除
			}
			fos = new FileOutputStream(saveFile);
			fos.write(file.getBytes());
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {

				}
			}
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("flag", 1);
		param.put("dateMonth", dateMonth);
		List<Balance> list = balanceService.queryListByWhere(param);
		if (list == null || list.size() == 0) {
			// 该月份没记录，添加
			FileUpload upload = new FileUpload();
			upload.setFileName(saveFile.getName());
			upload.setFilePath(saveFile.getPath());
			upload.setCreateBy(user.getId());
			fileUploadService.saveSelective(upload);
			// 资产负债添加
			Balance b = new Balance();
			b.setDateMonth(dateMonth);
			b.setCompanyId(company.getId());
			b.setFileId(upload.getId());
			// 解析Excel
			ExcelUtils eu = new ExcelUtils();
			File f = new File(upload.getFilePath());
			try {
				eu.parseBalance(f, b);
			} catch (Exception e) {
				e.printStackTrace();
			}
			balanceService.saveSelective(b);
		} else {
			// 该月份已存在记录，更新
			Balance b = list.get(0);
			FileUpload upload = fileUploadService.queryByID(b.getFileId());
			upload.setFileName(saveFile.getName());
			upload.setFilePath(saveFile.getPath());
			upload.setCreateBy(user.getId());
			fileUploadService.updateByIdSelective(upload);
			b.setCompanyId(company.getId());
			b.setFileId(upload.getId());
			// 解析Excel
			ExcelUtils eu = new ExcelUtils();
			File f = new File(upload.getFilePath());
			try {
				eu.parseBalance(f, b);
			} catch (Exception e) {
				e.printStackTrace();
			}
			balanceService.updateByIdSelective(b);
		}
		return "redirect:/finance/add_finance";
	}

	/**
	 * 保存资产负债表
	 * 
	 * @return
	 */
	@RequestMapping("save_profit")
	public String saveProfit(String dateMonth, MultipartFile file,
			HttpSession session) {
		if (StringUtils.isBlank(dateMonth)) {// 所属月份不能为空
			return "redirect:/finance/add_finance";
		}
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		Company company = companyService.queryByID(user.getCompanyId());

		// 文件保存到磁盘
		File dir = new File(GlobalConfig.readProperty("finance.dir"));// 检查保存文件的目录是否存在，不存在则创建目录
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File saveFile = new File(GlobalConfig.readProperty("finance.dir")
				+ company.getId() + "_profit_" + file.getOriginalFilename());// 保存到本地的文件
		FileOutputStream fos = null;
		try {
			if (saveFile.exists()) {
				saveFile.delete();// 文件存在先删除
			}
			fos = new FileOutputStream(saveFile);
			fos.write(file.getBytes());
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {

				}
			}
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("flag", 1);
		param.put("dateMonth", dateMonth);
		List<Profit> list = profitService.queryListByWhere(param);
		if (list == null || list.size() == 0) {
			// 该月份没记录，添加
			FileUpload upload = new FileUpload();
			upload.setFileName(saveFile.getName());
			upload.setFilePath(saveFile.getPath());
			upload.setCreateBy(user.getId());
			fileUploadService.saveSelective(upload);
			Profit profit = new Profit();
			profit.setDateMonth(dateMonth);
			profit.setCompanyId(company.getId());
			profit.setFileId(upload.getId());
			// 解析Excel
			ExcelUtils eu = new ExcelUtils();
			File f = new File(upload.getFilePath());
			try {
				eu.parseProfit(f, profit);
			} catch (Exception e) {
				e.printStackTrace();
			}
			profitService.saveSelective(profit);
		} else {
			// 该月份已存在记录，更新
			Profit profit = list.get(0);
			FileUpload upload = fileUploadService.queryByID(profit.getFileId());
			upload.setFileName(saveFile.getName());
			upload.setFilePath(saveFile.getPath());
			upload.setCreateBy(user.getId());
			fileUploadService.updateByIdSelective(upload);
			profit.setCompanyId(company.getId());
			profit.setFileId(upload.getId());
			// 解析Excel
			ExcelUtils eu = new ExcelUtils();
			File f = new File(upload.getFilePath());
			try {
				eu.parseProfit(f, profit);
			} catch (Exception e) {
				e.printStackTrace();
			}
			profitService.updateByIdSelective(profit);
		}
		return "redirect:/finance/add_finance";
	}

	/**
	 * 保存现金流量表
	 * 
	 * @return
	 */
	@RequestMapping("save_cash")
	public String saveCash(CashFlow cash, HttpSession session) {
		if (cash == null || StringUtils.isBlank(cash.getDateMonth())) {// 所属月份不能为空
			return "redirect:/finance/add_finance";
		}
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		Company company = companyService.queryByID(user.getCompanyId());

		Map<String, Object> param = new HashMap<String, Object>();// 查询条件
		param.put("dateMonth", cash.getDateMonth());
		param.put("flag", 1);
		List<CashFlow> list = cashFlowService.queryListByWhere(param);
		if (list != null && list.size() > 0) {
			// 记录已经存在，更新
			CashFlow cf = list.get(0);
			cf.setMoney(cash.getMoney());
			cashFlowService.updateByIdSelective(cf);
		} else {
			// 不存在，新增
			cash.setCompanyId(company.getId());
			cashFlowService.saveSelective(cash);
		}
		return "redirect:/finance/add_finance";
	}

	/**
	 * ajax请求 查询资产负债表所有月份
	 * 
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping("/show_balance_month")
	@ResponseBody
	public ResponseEntity<List<String>> showBalanceMonth(HttpSession session,
			HttpServletResponse response) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		Company company = companyService.queryByID(user.getCompanyId());

		List<String> list = balanceService.queryDateMonth(company.getId());
		return ResponseEntity.ok(list);
	}

	/**
	 * ajax请求 查询所有月份的数据
	 * 
	 * @param session
	 * @param response
	 * @return 返回月份
	 */
	@RequestMapping("/show_profit_month")
	@ResponseBody
	public ResponseEntity<List<String>> showProfitMonth(HttpSession session,
			HttpServletResponse response) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		Company company = companyService.queryByID(user.getCompanyId());

		List<String> list = profitService.queryDateMonth(company.getId());
		return ResponseEntity.ok(list);
	}

	/**
	 * 根据月份查询资产负债表详细信息
	 * 
	 * @param dateMonth
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping("/show_balance")
	public String showBalance(String dateMonth, Map<String, Object> map,
			HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		Company company = companyService.queryByID(user.getCompanyId());

		Example example = new Example(Balance.class);// 查询条件
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("companyId", company.getId());
		criteria.andEqualTo("flag", 1);
		criteria.andEqualTo("dateMonth", dateMonth);
		example.setOrderByClause(" date_month asc");
		List<Balance> list = balanceService.queryListByWhere(example);
		if (list != null && list.size() > 0) {
			Balance b = list.get(0);
			map.put("balance", b);
		}
		map.put("company", company);
		map.put("dateMonth", dateMonth);
		return "finance/balance_table";
	}

	/**
	 * 根据月份查询利润表详细信息
	 * 
	 * @param dateMonth
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping("/show_profit")
	public String showProfit(String dateMonth, Map<String, Object> map,
			HttpSession session) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		Company company = companyService.queryByID(user.getCompanyId());

		Example example = new Example(Balance.class);// 查询条件
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("companyId", company.getId());
		criteria.andEqualTo("flag", 1);
		criteria.andEqualTo("dateMonth", dateMonth);
		example.setOrderByClause(" date_month asc");
		List<Profit> list = profitService.queryListByWhere(example);
		if (list != null && list.size() > 0) {
			Profit p = list.get(0);
			map.put("profit", p);
		}
		map.put("company", company);
		map.put("dateMonth", dateMonth);
		return "finance/profit_table";
	}

	/**
	 * ajax请求 查询现金流量表数据
	 * 
	 * @param pageNum
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping("/show_cashflow")
	@ResponseBody
	public ResponseEntity<PageInfo<CashFlow>> showCashFlow(String pageNum,
			HttpSession session, HttpServletResponse response) {
		User user = (User) session.getAttribute(Constant.SESSION_KEY);
		Company company = companyService.queryByID(user.getCompanyId());

		Example example = new Example(Balance.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("companyId", company.getId());
		criteria.andEqualTo("flag", 1);
		example.setOrderByClause(" date_month asc");// 按所属月份排序
		PageInfo<CashFlow> list = cashFlowService.queryPageByWhere(example,
				Integer.parseInt(pageNum),
				Integer.parseInt(GlobalConfig.readProperty("page.rows")));
		return ResponseEntity.ok(list);
	}

	/**
	 * 删除现金流表
	 * 
	 * @param cashFlowId
	 * @param session
	 * @return
	 */
	@RequestMapping("/del_cashflow")
	@ResponseBody
	public ResponseEntity<ResponseVo> delCashFlow(String cashFlowId,
			HttpSession session) {
		try {
			CashFlow cash = cashFlowService.queryByID(Integer
					.parseInt(cashFlowId));
			cash.setFlag(0);// 逻辑删除
			cashFlowService.updateByIdSelective(cash);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ResponseVo(ResponseCode.PARAM_ERROR,
					null));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new ResponseVo(ResponseCode.SYSTEM_ERROR,
					null));
		}
		return ResponseEntity.ok(new ResponseVo(ResponseCode.SUCCESS, null));
	}

	/**
	 * xls模板下载
	 * 
	 * @param type
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("download")
	public ResponseEntity<byte[]> download(String type) throws IOException {
		String path = "";
		// 判断是否Windows系统
		if (System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1) {
			path = this.getClass().getClassLoader().getResource("").getFile()
					.substring(1)
					+ "templates/xls/";
		} else {
			path = this.getClass().getClassLoader().getResource("").getFile()
					+ "templates/xls/";
		}
		File file = null;
		// 判断是下载资产负债表模板还是利润表模板
		if ("1".equals(type)) {
			file = new File(path + "模板_资产负债表XXX年XX月.xls");
		} else if ("2".equals(type)) {
			file = new File(path + "模板_利润表XXX年XX月.xls");
		}
		String fileName = new String(file.getName().getBytes("UTF-8"),
				"iso-8859-1");// 避免中文名字乱码
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", fileName);// 设置下载文件名
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),
				headers, HttpStatus.CREATED);
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
