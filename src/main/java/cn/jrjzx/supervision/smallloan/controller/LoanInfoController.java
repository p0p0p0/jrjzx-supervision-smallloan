/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.controller
 * LoanInfoController.java
 * 
 * 2017年7月7日-下午5:40:05
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jrjzx.supervision.smallloan.entity.LoanInfo;
import cn.jrjzx.supervision.smallloan.service.LoanInfoService;

/**
 *
 * LoanInfoController
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年7月7日 下午5:40:05
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("loanInfo")
public class LoanInfoController extends BaseController<LoanInfo, LoanInfoService> {

	@PostMapping("saveOrUpdateList")
	public void sdaveOrUpdateList(@RequestBody List<LoanInfo> list) throws Exception{
		this.getService().saveOrUpdate(list);
	}
}
