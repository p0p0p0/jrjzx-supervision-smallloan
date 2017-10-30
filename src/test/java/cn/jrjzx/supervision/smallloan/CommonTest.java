/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan
 * CommonTest.java
 * 
 * 2017年6月11日-下午1:58:04
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import cn.jrjzx.supervision.smallloan.common.util.RejoiceUtil;
import cn.jrjzx.supervision.smallloan.entity.ExtendRepay;
/**
 *
 * CommonTest
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年6月11日 下午1:58:04
 * 
 * @version 1.0.0
 *
 */
public class CommonTest {
  
	
	
	@Test
	public void CamelTest(){
		System.out.println(RejoiceUtil.camelName("companyName"));
	}
	
	@Test 
	public void moneyTest(){
		Double d = 1.0E-4;
		System.out.println(RejoiceUtil.formatMoneyToDouble(d));
		d = 10000000012.125555;
		System.out.println(RejoiceUtil.formatMoneyToDoubleString(d));
		String a = "1232.13565";
		System.out.println(RejoiceUtil.formatMoneyToDoubleString(a));
		String b = "1.0E-4";
		System.out.println(RejoiceUtil.formatMoneyToDoubleString(b));
	}
	
	@Test
	public void subStringTest(){
		StringBuilder sb = new StringBuilder("23,");
		System.out.println(sb.substring(0, sb.length()-1));
	}
	@Test
	public void md5Test(){
		System.out.println(DigestUtils.md5Hex("admin"));
	}
	
	@Test
	public void subString(){
		System.out.println("2017-01-01".substring(11));
	}
	@Test
	public void testSort(){
		List<ExtendRepay> repayList = new ArrayList<ExtendRepay>();
		ExtendRepay repay = new ExtendRepay();
		repay.setRepayTime("2017-11-11 12:12:12");
		repayList.add(repay);
		repay = new ExtendRepay();
		repay.setRepayTime("2017-11-12 12:12:12");
		repayList.add(repay);
		repay = new ExtendRepay();
		repay.setRepayTime("2017-11-10 12:12:12");
		repayList.add(repay);
		ExtendRepay[] extendRepayArray = repayList.toArray(new ExtendRepay[]{});
		Arrays.sort(extendRepayArray, new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				ExtendRepay e1 = (ExtendRepay) o1;
				ExtendRepay e2 = (ExtendRepay) o2;
				return e1.getRepayTime().compareTo(e2.getRepayTime());
			}
			
		});
		for(ExtendRepay extendRepay: extendRepayArray){
			System.out.println(extendRepay.getRepayTime());
		}
	}
	
	@Test
	public void testDays(){
		Long[] yearMoneyDays = RejoiceUtil.getYearMonthDays(1000);
		for (Long long1 : yearMoneyDays) {
			System.out.println(long1+" ");
		}
	}
	
	@Test
	public void testDigits(){
		DecimalFormat df1 = new DecimalFormat("#.##"); 
		DecimalFormat df2 = new DecimalFormat("#0.00"); 
		System.out.println(df1.format(3d));
		System.out.println(df2.format(3d));
		System.out.println(df1.format(0.419));
		System.out.println(df2.format(0.419));
		double d = 39+12.12+12-61.7;
		System.out.println(d);
		d = Double.parseDouble(df1.format(d));
		System.out.println(d);
	}
	
	
	
}
