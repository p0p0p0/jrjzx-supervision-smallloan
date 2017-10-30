/**
 * 系统项目名称
 * cn.jrjzx.supervision.smallloan.controller
 * MonthFinanceController.java
 * 
 * 2017年7月11日-下午2:29:56
 *  2017金融街在线公司-版权所有
 *
 */
package cn.jrjzx.supervision.smallloan.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.jrjzx.supervision.smallloan.entity.MonthFinance;
import cn.jrjzx.supervision.smallloan.service.MonthFinanceService;

/**
 *
 * MonthFinanceController
 * 
 * @author rejoice 948870341@qq.com
 * @date 2017年7月11日 下午2:29:56
 * 
 * @version 1.0.0
 *
 */
@RestController
@RequestMapping("month-finance")
public class MonthFinanceController extends BaseController<MonthFinance, MonthFinanceService>{

}
