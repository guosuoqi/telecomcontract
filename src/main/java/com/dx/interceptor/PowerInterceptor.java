/*
package com.dx.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dx.model.user.UserMain;
import com.dx.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;





*/
/**
 * 权限拦截器
 * @author zhangxx
 *
 *//*

@Component
public class PowerInterceptor implements HandlerInterceptor {

	@Autowired
	private UserService userService;
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//获取登录用户session信息
		HttpSession session = request.getSession();
		UserMain userBean = (UserMain) session.getAttribute(session.getId());
		//取出userid
		Integer userId = userBean.getId();
		//获取浏览器请求的地址
		String requestURI = request.getRequestURI();//注意 是URI 不是 URL
		List<String> powerList = new ArrayList<>();
			//根据用户id查询出用户所拥有的权限地址列表
			powerList = userService.findUserPowerUrl(userId);
		//判断浏览器请求的地址是否在所查询出权限列表当中，如果在 return true;
		for (String url : powerList) {
			//判断是否包含
			boolean contains = url.contains(requestURI);
			if (contains) {
				return true;
			}
		}
		//如果上面没有return 则表明没有权限访问，拦截到提示页面，return false
		response.sendRedirect("/nopower.jsp");
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
*/
