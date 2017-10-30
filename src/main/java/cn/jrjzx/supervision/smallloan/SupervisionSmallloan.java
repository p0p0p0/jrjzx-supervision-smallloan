/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan
 * SupervisionSmallloan.java
 * 
 * 2017年5月10日-下午2:23:55
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan;

import org.joda.time.DateTime;
import org.joda.time.convert.Converter;
import org.joda.time.format.FormatUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 *
 * SupervisionSmallloan
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年5月10日 下午2:23:55
 * 
 * @version 1.0.0
 *
 */
@SpringBootApplication
@ServletComponentScan
@MapperScan(basePackages = "cn.jrjzx.supervision.smallloan.mapper")
public class SupervisionSmallloan  extends SpringBootServletInitializer {
	

	public static void main(String[] args) {
		new SpringApplicationBuilder(SupervisionSmallloan.class)
				.properties(
						"spring.config.name:application,constant,activemq",
						"spring.config.location:classpath:/config/").build()
				.run(args);
	}
	
	 
	  @Override
	    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		//set register error pagefilter false
	       //setRegisterErrorPageFilter(false);
	        return application.sources(SupervisionSmallloan.class);
	    } 
	  

	  
}
