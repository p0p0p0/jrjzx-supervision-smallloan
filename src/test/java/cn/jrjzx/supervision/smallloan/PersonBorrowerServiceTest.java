package cn.jrjzx.supervision.smallloan;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.jrjzx.supervision.smallloan.entity.PersonBorrower;
import cn.jrjzx.supervision.smallloan.service.PersonBorrowerService;

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
public class PersonBorrowerServiceTest {
	
	//private static final Logger LOGGER = LoggerFactory.getLogger(PersonBorrowerServiceTest.class);

	@Autowired
	PersonBorrowerService service;
	
	
	@Test
	public void testSave() throws Exception{
		PersonBorrower p = new PersonBorrower();
		p.setName("蔡成功");
		p.setCardNumber("440100001005");
		p.setIsFarmer(0);
		p.setAddress("汉东省京州市");
		
		System.out.println("-------------");
		
		try {
			service.saveSelective(p);
			System.out.println(p.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
