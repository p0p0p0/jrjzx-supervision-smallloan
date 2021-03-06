/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.controller
 * EnsureContractController.java
 * 
 * 2017年6月8日-下午5:38:42
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jrjzx.supervision.smallloan.entity.EnsureContract;
import cn.jrjzx.supervision.smallloan.service.EnsureContractService;

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
@RequestMapping("ensureContract")
public class EnsureContractController extends BaseController<EnsureContract,EnsureContractService>{

	
}
