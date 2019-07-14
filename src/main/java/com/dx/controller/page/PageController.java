/** 
 * <pre>项目名称:stu_bootcar 
 * 文件名称:PageController.java 
 * 包名:com.jk.ldd.controller.page 
 * 创建日期:2018年10月27日上午11:05:46 
 * Copyright (c) 2018, 634369607@qq.com All Rights Reserved.</pre> 
 */  
package com.dx.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * <pre>项目名称：stu_bootcar    
 * 类名称：PageController    
 * 类描述：    
 * 创建人：张小雪
 * 创建时间：2018年10月27日 上午11:05:46    
 * 修改人：刘东晔      
 * 修改时间：2018年10月27日 上午11:05:46    
 * 修改备注：       
 * @version </pre>    
 */
@Controller
@RequestMapping("page")
public class PageController {
	@RequestMapping("toShowMain")
	public String toShowMain() {
		
		return "WEB-INF/view/showMain";
	}
	
	@RequestMapping("toShowCar")
	public String toShowCar() {
		
		return "WEB-INF/view/showCar";
	}
	
	@RequestMapping("toAddCar")
	public String toAddCar() {
		
		return "WEB-INF/view/addCar";
	}
}
