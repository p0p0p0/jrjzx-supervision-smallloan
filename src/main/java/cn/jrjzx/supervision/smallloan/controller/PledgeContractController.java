/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.controller
 * PledgeContractController.java
 * 
 * 2017年6月8日-下午5:38:42
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jrjzx.supervision.smallloan.entity.PledgeContract;
import cn.jrjzx.supervision.smallloan.service.PledgeContractService;

/**
 *
 * 保证合同
 * 
 * @date 2017年6月8日 下午5:38:42
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("pledgeContract")
public class PledgeContractController extends BaseController<PledgeContract,PledgeContractService>{

	
	
}
