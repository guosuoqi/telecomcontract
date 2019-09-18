package com.dx.interceptor;

import com.dx.model.user.UserMain;
import com.dx.service.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoginInterceptor implements HandlerInterceptor {

	@Autowired
	private UserServiceImpl userService;

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		HttpSession session = request.getSession();
		Object token = session.getAttribute(session.getId());
		if (token == null) {
			response.sendRedirect("/login.jsp");
			return false;
		}else {
			UserMain userBean = (UserMain) session.getAttribute(session.getId());
			//取出userid
			Integer userId = userBean.getId();
			//获取浏览器请求的地址
			String requestURI = request.getRequestURI();//注意 是URI 不是 URL
			List<String> powerList = new ArrayList<>();
			//根据用户id查询出用户所拥有的权限地址列表
			powerList = userService.findUserPowerUrl(userId);
			if(powerList==null){
				System.out.println("request = [" + powerList + "]");

			}
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
	}

}
