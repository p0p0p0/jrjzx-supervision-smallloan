/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan
 * RestTemplateTest.java
 * 
 * 2017年7月14日-下午2:44:47
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.AsyncRestTemplate;
import org.springframework.web.client.RestTemplate;

/**
 *
 * RestTemplateTest
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年7月14日 下午2:44:47
 * 
 * @version 1.0.0
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RestTemplateTest {
	@Autowired
	AsyncRestTemplate asyncRestTemplate;
	
	@Resource(name="restTemplate")
	RestTemplate restTemplate;
	
	@Test
	public void testAsync() throws InterruptedException{
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		LinkedMultiValueMap map=new LinkedMultiValueMap();
		map.add("name","张三");
		map.add("age","18");
		HttpEntity<LinkedMultiValueMap> entity = new HttpEntity<LinkedMultiValueMap>(map,headers);
		ResponseEntity<Object> responseEntity = restTemplate.postForEntity("https://113.108.173.193/api/jrjApi/v2/pushRepayData",entity,Object.class);
		/*ListenableFuture<ResponseEntity<Object>> postForEntity = asyncRestTemplate.postForEntity("https://113.108.173.193/api/jrjApi/v2/pushRepayData",entity,Object.class);
		postForEntity.addCallback(new ListenableFutureCallback<Object>() {

			@Override
			public void onSuccess(Object result) {
				System.out.println(result);
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println(ex);
			}
		});*/
		
		Thread.sleep(5000);
	}
	
}
