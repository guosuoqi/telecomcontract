/** 
 * <pre>项目名称:stu_bootcar 
 * 文件名称:PageController.java 
 * 包名:com.jk.ldd.controller.page 
 * 创建日期:2018年10月27日上午11:05:46 
 * Copyright (c) 2018, 634369607@qq.com All Rights Reserved.</pre> 
 */  
package com.dx.controller.contract;

import com.dx.controller.user.UserController;
import com.dx.model.contract.Contract;
import com.dx.model.contract.ContractExtension;
import com.dx.model.contract.SysCode;
import com.dx.model.nav.NavTree;
import com.dx.service.contract.ContractService;
import com.dx.service.nav.NavService;
import com.dx.util.DateUtils;
import com.dx.util.ExceptionPrintUtil;
import com.dx.util.PageResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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
    private Logger logger = LoggerFactory.getLogger(ContractController.class);

    //查询合同管理页面
    @RequestMapping(value = "queryContract", method = RequestMethod.POST)
    @ResponseBody
    public PageResult queryContract(Integer page, Integer rows, Contract contract) {
        PageResult pageResult = contractService.queryContract(page, rows, contract);
        return pageResult;
    }


    //合同管理页面新增
    @RequestMapping("addContract")
    @ResponseBody
    public HashMap<String, String>  addContract(Contract contract) {
             HashMap<String, String> result= new HashMap<String, String>();;
        try {
            if ((contract != null )) {
                int count =   contractService.addContract(contract);
                if (count == 0) {
                    result.put("code", "1");
                    result.put("msg", "合同新增失败！");
                    logger.info(this.getClass() + "，合同新增失败！");
                    return result;
                }
                result = new HashMap<String, String>();
                result.put("code", "0");
                result.put("msg", "操作成功！");
                return result;
            }else{
                result = new HashMap<String, String>();
                result.put("code", "2");
                result.put("msg", "合并工单失败！");
                logger.info(this.getClass() + "，合并工单参数为空！");
                return result;
            }
        }catch (Exception e){
            result = new HashMap<String, String>();
            result.put("code", "2");
            result.put("msg", "合同新增失败！");
            logger.info(this.getClass() + "，合同新增异常！");
            //异常输出
            logger.error("exception toString and track space : {}", "\r\n" + e);
            logger.error(ExceptionPrintUtil.errorTrackSpace(e));
            return result;
        }
    }
    //合同管理页面批量删除
    @RequestMapping("delAll")
    @ResponseBody
    public  HashMap<String, String>  delAll(String ids){
        HashMap<String, String> result;
        try {
            if ((ids != null || "".equals(ids))) {
                int count =   contractService.delAll(ids);;
                if (count == 0) {
                    result = new HashMap<String, String>();
                    result.put("code", "1");
                    result.put("msg", "合同删除失败！");
                    logger.info(this.getClass() + "，合同删除失败！");
                    return result;
                }
                result = new HashMap<String, String>();
                result.put("code", "0");
                result.put("msg", "操作成功！");
                return result;
            }else{
                result = new HashMap<String, String>();
                result.put("code", "2");
                result.put("msg", "合同删除失败！");
                logger.info(this.getClass() + "，合同删除失败！");
            }

        } catch (Exception e) {
            result = new HashMap<String, String>();
            result.put("code", "3");
            result.put("msg", "合同删除失败！");
            logger.info(this.getClass() + "，合同删除失败！");
            //异常输出
            logger.error("exception toString and track space : {}", "\r\n" + e);
            logger.error(ExceptionPrintUtil.errorTrackSpace(e));
            return result;
        }
        return result;
    }


    //合同类型查询
    @RequestMapping("queryType")
    @ResponseBody
    public List<SysCode> queryType(){
        List<SysCode> contracts = contractService.queryType();
        return contracts;
    }

  //修改合同管理页面
    @RequestMapping("updateContract")
    @ResponseBody
    public void updateContract(Contract contract){
         contractService.updateContract(contract);
    }

    //合同续约页面查询
    @RequestMapping(value = "queryContractExtension", method = RequestMethod.POST)
    @ResponseBody
    public PageResult queryContractExtension(Integer page, Integer rows, Contract contract) {
        PageResult pageResult = contractService.queryContract(page, rows, contract);
        return pageResult;
    }


    //查询合同待续费数目
    @RequestMapping(value = "queryContractEndTimeCount", method = RequestMethod.POST)
    @ResponseBody
    public int queryContractEndTimeCount(){
        Contract contract = new Contract();
        contract.setEndTime(DateUtils.getPastDate(3));
        return contractService.queryContractByEndTime(contract);
    }
    //查询合同待续费数目
    @RequestMapping(value = "queryContractPayEndTimeCount", method = RequestMethod.POST)
    @ResponseBody
    public int queryContractPayEndTimeCount(){
        Contract contract = new Contract();
        contract.setPayEndTime(DateUtils.getPastDate(3));
        return contractService.queryContractByEndTime(contract);
    }


}
