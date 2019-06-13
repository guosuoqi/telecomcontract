/** 
 * <pre>项目名称:stu_bootcar 
 * 文件名称:PageController.java 
 * 包名:com.jk.ldd.controller.page 
 * 创建日期:2018年10月27日上午11:05:46 
 * Copyright (c) 2018, 634369607@qq.com All Rights Reserved.</pre> 
 */  
package com.dx.controller.contract;

import com.dx.model.contract.Contract;
import com.dx.model.nav.NavTree;
import com.dx.service.contract.ContractService;
import com.dx.service.nav.NavService;
import com.dx.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/** 
 * <pre>项目名称：stu_bootcar    
 * 类名称：PageController    
 * 类描述：    
 * 创建人：张小雪
 * 创建时间：2019年6月13日 上午11:05:46
 * 修改人：张小雪
 * 修改时间：2019年6月13日 上午11:05:46
 * 修改备注：       
 * @version </pre>    
 */
@Controller
@RequestMapping("contract")
public class ContractController {
    @Autowired
    private ContractService contractService;

    //查询合同管理页面
    @RequestMapping(value = "queryContract", method = RequestMethod.POST)
    @ResponseBody
    public PageResult queryContract(Integer page, Integer rows, Contract Contract) {
        return  contractService.queryContract(page,rows,Contract);
    }
}
