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

import cn.jrjzx.supervision.smallloan.entity.User;
import cn.jrjzx.supervision.smallloan.service.UserService;

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
@SpringBootTest
public class UserServiceTest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceTest.class);

	@Autowired
	UserService userService;
	
	
	@Test
	public void save() throws Exception{
		// for(int i = 0; i  < 200000; i++){
			  User user = new User();
			  user.setUsername("dfsdfsdfsdfs");
			  user.setPassword("123");
			  System.out.println(userService.queryAll());
		 // }
	}
}
