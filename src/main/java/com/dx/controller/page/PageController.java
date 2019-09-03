/** 
 * <pre>项目名称:stu_bootcar 
 * 文件名称:PageController.java 
 * 包名:com.jk.ldd.controller.page 
 * 创建日期:2018年10月27日上午11:05:46 
 * Copyright (c) 2018, 634369607@qq.com All Rights Reserved.</pre> 
 */  
package com.dx.controller.page;

import com.dx.model.contract.Contract;
import com.dx.model.contract.ContractExtension;
import com.dx.model.nav.RoleBean;
import com.dx.service.user.UserService;
import org.springframework.ui.Model;
import com.dx.model.site.EquipmentBBU;
import com.dx.model.site.EquipmentRRUAAU;
import com.dx.service.contract.ContractService;
import com.dx.service.site.SiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/** 
 * <pre>项目名称：stu_bootcar    
 * 类名称：PageController    
 * 类描述：    
 * 创建人：张小雪
 * 创建时间：2019年6月17日 上午11:05:46
 * 修改人：张小雪
 * 修改时间：2019年6月17日 上午11:05:46
 * 修改备注：       
 * @version </pre>    
 */
@Controller
@RequestMapping("page")
public class PageController {

	@Autowired
	private ContractService contractService;
	@Autowired
	private SiteService siteService;
	@Autowired
	private UserService userService;
	//去新增合同管理的页面
	@RequestMapping("toAddContract")
	public String toAddContract() {

		return "/view/addContract";
	}
//去新增合同续约的页面
	@RequestMapping("toAddContractExtension")
	public String toAddContractExtension() {

		return "/view/addContractExtension";
	}
	//去新增用户的页面



	@RequestMapping("toAddUser")
	public String toAddUser(Model model) {
		List<RoleBean> role = userService.queryRole();
		model.addAttribute("roleList",role);
		return "/view/addUser";
	}

	//去修改合同管理页面
	@RequestMapping("toUpdateContract")
	public String toUpdateContract(Integer contractId, ModelMap modelMap) {
		Contract contract = contractService.queryContractById(contractId);
		modelMap.put("contract", contract);
		return "view/updateContract";
	}

	//去修改合同续费页面
		@RequestMapping("toUpdateContractExtension")
		public String toUpdateContractExtension(Integer contractId, ModelMap modelMap) {
			Contract contract = contractService.queryContractById(contractId);
			modelMap.put("contract", contract);
			return "view/updateContractExtension";
	}
	//去修改合同续约页面
	@RequestMapping("toUpdateContractDue")
	public String toUpdateContractDue(Integer contractId, ModelMap modelMap) {
		Contract contract = contractService.queryContractById(contractId);
		modelMap.put("contract", contract);
		return "view/updateContractDue";
	}

	//去新增3/4/5GBBU的页面
	@RequestMapping("toAdd3GBBU")
	public String toAdd3GBBU() {
		return "/view/add3GBBU";
	}

	//去修改3/4/5GBBU页面
	@RequestMapping("toUpdate3GBBU")
	public String toUpdate3GBBU(Integer id, ModelMap modelMap) {
		EquipmentBBU equipmentBBU = siteService.query3GBBUById(id);
		modelMap.put("equipmentBBU", equipmentBBU);
		return "view/update3GBBU";
	}

	//去新增3/4/5GRRU的页面
	@RequestMapping("toAdd3GRRU")
	public String toAdd3GRRU() {
		return "/view/add3GRRU";
	}

	//去新增navMenu的页面
	@RequestMapping("toAddNavMenu")
	public String toAddNavMenu() {
		return "/view/addNavMenu";
	}

	//去修改3/4/5GBBU页面
	@RequestMapping("toUpdate3GRRU")
	public String toUpdate3GRRU(Integer id, ModelMap modelMap) {
		EquipmentRRUAAU equipmentRRUAAU = siteService.query3GRRUById(id);
		modelMap.put("equipmentRRUAAU", equipmentRRUAAU);
		return "view/update3GRRU";
	}

}
