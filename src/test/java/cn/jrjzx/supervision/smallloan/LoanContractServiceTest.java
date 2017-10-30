package cn.jrjzx.supervision.smallloan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.jrjzx.supervision.smallloan.entity.LoanContract;
import cn.jrjzx.supervision.smallloan.entity.PersonBorrower;
import cn.jrjzx.supervision.smallloan.service.LoanContractService;

/**
 *
 * 
 * @date 2017年5月23日 下午5:35:24
 * 
 * @version 1.0.0
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes=SupervisionSmallloan.class)
public class LoanContractServiceTest {
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(PersonBorrowerServiceTest.class);

	@Autowired
	LoanContractService service;
	
	
	@Test
	public void testSave() throws Exception{
		LoanContract l = new LoanContract();
		l.setBorrowerType(2);
		
		PersonBorrower p = new PersonBorrower();
		p.setName("蔡成功");
		p.setCardNumber("440100001003");
		p.setIsFarmer(0);
		p.setAddress("汉东省京州市");
		
		l.setPersonBorrower(p);
		
		service.saveAll(l);
	}
}
