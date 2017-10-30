package cn.jrjzx.supervision.smallloan.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.jrjzx.supervision.smallloan.entity.Balance;
import cn.jrjzx.supervision.smallloan.entity.Profit;

public class ExcelUtils {

	public static void main(String[] args) throws Exception {
//		ExcelUtils eu = new ExcelUtils();
//		File file = new File("uploads/finance/1_balance_201704月度总表.xls");
//		Balance balance = new Balance();
//		eu.parseBalance(file, balance);
//		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@" + balance);
		String path = "";
		if(System.getProperty("os.name").toUpperCase().indexOf("WINDOWS") != -1) {
			path = ExcelUtils.class.getClassLoader().getResource("").getFile().substring(1)+"templates/xls/";
		}else {
			path = ExcelUtils.class.getClassLoader().getResource("").getFile()+"templates/xls/";
		}
		File file = new File(path+"模板_利润表.xls");
		System.out.println(file.exists());
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getName());
	}

	/**
	 * 解析利润表xls
	 * 
	 * @param file
	 * @param profit
	 * @throws Exception
	 */
	public void parseProfit(File file, Profit profit) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		Workbook book = createWorkBook(fis, file.getName());
		if (book == null) {
			if (file != null) {
				fis.close();
			}
			throw new Exception("导入文件格式不对");
		}
		// 只解析第一个sheet表，后面的sheet表不做解析
		Sheet sheet = book.getSheetAt(0);
		Row row = sheet.getRow(3);// 4行
		Cell cell = row.getCell(4);// 5列
		String value = getCellValue(cell, book).toString().trim();
		profit.setBusinessIncome("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearBusinessIncome("".equals(value) ? "0" : value);
		row = sheet.getRow(4);// 5行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setInterestIncome("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearInterestIncome("".equals(value) ? "0" : value);
		row = sheet.getRow(5);// 6行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setFinancialInterestIncome("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearFinancialInterestIncome("".equals(value) ? "0" : value);
		row = sheet.getRow(6);// 7行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setFeeIncome("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearFeeIncome("".equals(value) ? "0" : value);
		row = sheet.getRow(7);// 8行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setChangeIncome("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearChangeIncome("".equals(value) ? "0" : value);
		row = sheet.getRow(8);// 9行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setAggregateIncome("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearAggregateIncome("".equals(value) ? "0" : value);
		row = sheet.getRow(9);// 10行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setOtherIncome("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearOtherIncome("".equals(value) ? "0" : value);
		row = sheet.getRow(10);// 11行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setBusinessExpenses("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearBusinessExpenses("".equals(value) ? "0" : value);
		row = sheet.getRow(11);// 12行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setInterestExpense("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearInterestExpense("".equals(value) ? "0" : value);
		row = sheet.getRow(12);// 13行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setFinancialInterestExpense("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearFinancialInterestExpense("".equals(value) ? "0" : value);
		row = sheet.getRow(13);// 14行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setFeeExpense("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearFeeExpense("".equals(value) ? "0" : value);
		row = sheet.getRow(14);// 15行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setManagementFees("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearManagementFees("".equals(value) ? "0" : value);
		row = sheet.getRow(15);// 16行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setExchangeLoss("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearExchangeLoss("".equals(value) ? "0" : value);
		row = sheet.getRow(16);// 17行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setAssetsLoss("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearAssetsLoss("".equals(value) ? "0" : value);
		row = sheet.getRow(17);// 18行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setOtherExpenses("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearOtherExpenses("".equals(value) ? "0" : value);
		row = sheet.getRow(18);// 19行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setBusinessTax("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearBusinessTax("".equals(value) ? "0" : value);
		row = sheet.getRow(19);// 20行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setOperatingIncome("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearOperatingIncome("".equals(value) ? "0" : value);
		row = sheet.getRow(20);// 21行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setInvestmentIncome("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearInvestmentIncome("".equals(value) ? "0" : value);
		row = sheet.getRow(21);// 22行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setNonOperatingIncome("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearNonOperatingIncome("".equals(value) ? "0" : value);
		row = sheet.getRow(22);// 23行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setNonOperatingPayment("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearNonOperatingPayment("".equals(value) ? "0" : value);
		row = sheet.getRow(23);// 24行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setTotalProfit("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearTotalProfit("".equals(value) ? "0" : value);
		row = sheet.getRow(24);// 25行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setIncomeTax("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearIncomeTax("".equals(value) ? "0" : value);
		row = sheet.getRow(25);// 26行
		cell = row.getCell(4);// 5列
		value = getCellValue(cell, book).toString().trim();
		profit.setNetProfit("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setYearNetProfit("".equals(value) ? "0" : value);
		row = sheet.getRow(26);// 27行
		cell = row.getCell(1);// 2列
		value = getCellValue(cell, book).toString().trim();
		profit.setLeader(value);
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setInformant(value);
		row = sheet.getRow(27);// 28行
		cell = row.getCell(5);// 6列
		value = getCellValue(cell, book).toString().trim();
		profit.setReportDate(value);
		if (fis != null)
			fis.close();
		if (book != null)
			book.close();
	}

	/**
	 * 解析资产负债表xls
	 * 
	 * @param file
	 * @param balance
	 * @throws Exception
	 */
	public void parseBalance(File file, Balance balance) throws Exception {
		FileInputStream fis = new FileInputStream(file);
		Workbook book = createWorkBook(fis, file.getName());
		if (book == null) {
			if (file != null) {
				fis.close();
			}
			throw new Exception("导入文件格式不对");
		}
		// 判断有几张活动的sheet表
		// int length = book.getNumberOfSheets();
		// 只解析第一个sheet表，后面的sheet表不做解析
		Sheet sheet = book.getSheetAt(0);
		Row row = sheet.getRow(4);// 5行
		Cell cell = row.getCell(3);// 4列
		String value = getCellValue(cell, book).toString().trim();
		balance.setMonetary("".equals(value) ? "0" : value);// 货币资金
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setBorrowing("".equals(value) ? "0" : value);// 短期借款
		row = sheet.getRow(5);// 6行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setReceivable("".equals(value) ? "0" : value);// 应收账款
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setPayable("".equals(value) ? "0" : value);// 应付账款
		row = sheet.getRow(6);// 7行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setPrepayment("".equals(value) ? "0" : value);// 预付账款
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setPremium("".equals(value) ? "0" : value);// 应付保费
		row = sheet.getRow(7);// 8行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setInterestReceivable("".equals(value) ? "0" : value);// 应收利息
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setReceived("".equals(value) ? "0" : value);// 预收账款
		row = sheet.getRow(8);// 9行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setOtherReceivable("".equals(value) ? "0" : value);// 其他应收款
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setPayroll("".equals(value) ? "0" : value);// 应付职工薪酬
		row = sheet.getRow(9);// 10行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setLoanMoney("".equals(value) ? "0" : value);// 贷款
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setTaxation(getCellValue(cell, book).toString().trim());// 应交税费
		row = sheet.getRow(10);// 11行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setLoanLoss("".equals(value) ? "0" : value);
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setInterestPayable("".equals(value) ? "0" : value);
		row = sheet.getRow(11);// 12行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setPrepaidExpenses("".equals(value) ? "0" : value);
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setOtherAccount("".equals(value) ? "0" : value);
		row = sheet.getRow(12);// 13行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setPendingCurrentAssets("".equals(value) ? "0" : value);
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setOtherPayable("".equals(value) ? "0" : value);
		row = sheet.getRow(13);// 14行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setShortInvestment("".equals(value) ? "0" : value);
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setProfitPayable("".equals(value) ? "0" : value);
		row = sheet.getRow(14);// 15行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setOtherCurrentAssets("".equals(value) ? "0" : value);
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setBondIssued("".equals(value) ? "0" : value);
		row = sheet.getRow(15);// 16行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setTotalCurrentAssets("".equals(value) ? "0" : value);
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setTotalCurrentLiabilities("".equals(value) ? "0" : value);
		row = sheet.getRow(17);// 18行
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setLongBorrowing("".equals(value) ? "0" : value);
		row = sheet.getRow(18);// 19行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setLongInvestment("".equals(value) ? "0" : value);
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setLongPayables("".equals(value) ? "0" : value);
		row = sheet.getRow(19);// 20行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setTotalLongInvestment("".equals(value) ? "0" : value);
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setTotalLongLiabilities("".equals(value) ? "0" : value);
		row = sheet.getRow(21);// 22行
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setDeferredTax("".equals(value) ? "0" : value);
		row = sheet.getRow(22);// 23行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setFixedAssets("".equals(value) ? "0" : value);
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setTotalLiabilities("".equals(value) ? "0" : value);
		row = sheet.getRow(23);// 24行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setDepreciation("".equals(value) ? "0" : value);
		row = sheet.getRow(24);// 25行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setNetFixedAssets("".equals(value) ? "0" : value);
		row = sheet.getRow(25);// 26行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setLiquidationFixedAssets("".equals(value) ? "0" : value);
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setPaidCapital("".equals(value) ? "0" : value);
		row = sheet.getRow(26);// 27行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setTotalFixedAssets("".equals(value) ? "0" : value);
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setCapitalSurplus("".equals(value) ? "0" : value);
		row = sheet.getRow(27);// 28行
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setReserveSurplus("".equals(value) ? "0" : value);
		row = sheet.getRow(28);// 29行
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setLegalSurplus("".equals(value) ? "0" : value);
		row = sheet.getRow(29);// 30行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setIntangibleAssets("".equals(value) ? "0" : value);
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setPublicWelfare("".equals(value) ? "0" : value);
		row = sheet.getRow(30);// 31行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setDeferredAssets("".equals(value) ? "0" : value);
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setUndistributedProfit("".equals(value) ? "0" : value);
		row = sheet.getRow(31);// 32行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setLongPrepaidExpenses("".equals(value) ? "0" : value);
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setGeneralReserve("".equals(value) ? "0" : value);
		row = sheet.getRow(32);// 33行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setTotalOtherAssets("".equals(value) ? "0" : value);
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setTotalOwnershipInterest("".equals(value) ? "0" : value);
		row = sheet.getRow(34);// 35行
		cell = row.getCell(3);// 4列
		value = getCellValue(cell, book).toString().trim();
		balance.setTotalAssets("".equals(value) ? "0" : value);
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setTotalLiabilitiesOwnershipInterest("".equals(value) ? "0"
				: value);
		row = sheet.getRow(35);// 36行
		cell = row.getCell(1);// 2列
		value = getCellValue(cell, book).toString().trim();
		balance.setLeader("".equals(value) ? "0" : value);
		cell = row.getCell(5);// 2列
		value = getCellValue(cell, book).toString().trim();
		balance.setInformant("".equals(value) ? "0" : value);
		cell = row.getCell(7);// 8列
		value = getCellValue(cell, book).toString().trim();
		balance.setReportDate("".equals(value) ? null : value);
		if (fis != null) {
			fis.close();
		}
		if (book != null) {
			book.close();
		}
	}

	/**
	 * 获取单元格的值
	 */
	public Object getCellValue(Cell cell, Workbook book) {
		Object cellStr = "";
		FormulaEvaluator eval = null;
		if (book instanceof HSSFWorkbook)
			eval = new HSSFFormulaEvaluator((HSSFWorkbook) book);
		else if (book instanceof XSSFWorkbook)
			eval = new XSSFFormulaEvaluator((XSSFWorkbook) book);
		CellValue cellValue = eval.evaluate(cell);
		if (cellValue == null) {
			return cellStr;
		}
		switch (cellValue.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			cellStr = cellValue.getBooleanValue();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			// 这里的日期类型会被转换为数字类型，需要判别后区分处理
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				cellStr = cell.getDateCellValue();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				cellStr = sdf.format(cell.getDateCellValue());
			} else {
				cellStr = formatNumber(cellValue.getNumberValue());

			}
			// cellStr = cellValue.getStringValue();
			break;
		case Cell.CELL_TYPE_STRING:
			cellStr = cellValue.getStringValue();
			break;
		case Cell.CELL_TYPE_FORMULA:
			break;
		case Cell.CELL_TYPE_BLANK:
			break;
		case Cell.CELL_TYPE_ERROR:
			break;
		default:
			break;
		}
		return cellStr;
	}

	/**
	 * 数字格式化
	 * 
	 * @param
	 * @return
	 */
	public static String formatNumber(double dou) {
		DecimalFormat fmt = new DecimalFormat("0.00");
		String outStr = null;
		try {
			outStr = fmt.format(dou);
		} catch (Exception e) {
		}
		return outStr;
	}

	// 判断文件类型
	public Workbook createWorkBook(InputStream is, String fileName)
			throws IOException {
		if (fileName.endsWith("xls")) {
			return new HSSFWorkbook(is);
		} else if (fileName.endsWith("xlsx")) {
			return new XSSFWorkbook(is);
		} else {
			return null;
		}
	}
}
