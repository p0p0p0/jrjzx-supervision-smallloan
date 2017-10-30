/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.controller
 * RepayPlanController.java
 * 
 * 2017年7月7日-下午3:09:17
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jrjzx.supervision.smallloan.entity.RepayPlan;
import cn.jrjzx.supervision.smallloan.service.RepayPlanService;

/**
 *
 * RepayPlanController
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年7月7日 下午3:09:17
 * 
 * @version 1.0.0
 *
 */
@RequestMapping("repayPlan")
@RestController
public class RepayPlanController extends BaseController<RepayPlan, RepayPlanService>{

}
