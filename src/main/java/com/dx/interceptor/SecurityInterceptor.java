package com.dx.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dx.model.user.UserMain;
import com.dx.service.user.UserService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;





/**
 * 权限拦截器
 * @author zhangxx
 *
 */

@Component
public class SecurityInterceptor implements HandlerInterceptor {


	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		//获取登录用户session信息
		HttpSession session = request.getSession();
		UserMain userBean = (UserMain) session.getAttribute(session.getId());
		if(userBean==null){
			response.sendRedirect("/user/tologin");
			return false;
		}
		//取出userid
		String userId = userBean.getId();
		//获取浏览器请求的地址
		String requestURI = request.getRequestURI();//注意 是URI 不是 URL
		List<String> powerList = new ArrayList<>();
		//根据用户id查询出用户所拥有的权限地址列表
		powerList = userService.findUserPowerUrl(userId);
		//判断浏览器请求的地址是否在所查询出权限列表当中，如果在 return true;
		for (String url : powerList) {
			//判断是否包含
			if (requestURI.equals(url)) {
				System.out.println(url);
				return true;
			}
		}
		//如果上面没有return 则表明没有权限访问，拦截到提示页面，return false
		response.sendRedirect("/nopower.jsp");
		return false;
		//  null == request.getHeader("x-requested-with") TODO 暂时用这个来判断是否为ajax请求
		// 如果没有权限 则抛403异常 springboot会处理，跳转到 /error/403 页面
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
