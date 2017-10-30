/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan
 * CompanyServiceTest.java
 * 
 * 2017年5月23日-下午5:35:24
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.jrjzx.supervision.smallloan.entity.ExtendRepayPlan;
import cn.jrjzx.supervision.smallloan.service.ExtendRepayPlanService;

/**
 *
 * CompanyServiceTest
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年5月23日 下午5:35:24
 * 
 * @version 1.0.0
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=SupervisionSmallloan.class)
public class ExtendRepayPlanServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExtendRepayPlanServiceTest.class);

	@Autowired
	ExtendRepayPlanService extendRepayPlanService;
	
	
	@Test
	public void save() throws Exception{
		  for(int i = 0; i  < 50; i++){
			  ExtendRepayPlan e = new ExtendRepayPlan();
			  e.setMoney(Double.parseDouble(i+""));
			  e.setInterest(Double.parseDouble(i+""));
			  e.setEndDate("2015-04-"+(i<=10?"0"+i:i));
			  e.setIsOverdue(i%2==0?0:1);
			  e.setIsSettle(i%2==0?0:1);
			  e.setContractId(123L);
			  e.setExtendDate("2017-07-03");
			  e.setFlag(2);
			  extendRepayPlanService.saveSelective(e);
			  
		  }
	}
}
