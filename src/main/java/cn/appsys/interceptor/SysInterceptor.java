package cn.appsys.interceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.appsys.pojo.BackendUser;
import cn.appsys.pojo.DevUser;
import cn.appsys.tools.Constants;


public class SysInterceptor extends HandlerInterceptorAdapter {
	private Logger logger = Logger.getLogger(SysInterceptor.class);
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// 执行完毕，返回前拦截

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// 在处理过程中，执行拦截

	}
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response,Object handler) throws Exception {
		logger.debug("SysInterceptor preHandle ==========================");
		HttpSession session = request.getSession();

		BackendUser backendUser = (BackendUser) session.getAttribute(Constants.USER_SESSION);
		DevUser devUser = (DevUser) session.getAttribute(Constants.DEV_USER_SESSION);

		if (null != devUser) { //dev SUCCESS
			return true;
		} else if (null != backendUser) { //backend SUCCESS
			return true;
		} else {
			response.sendRedirect(request.getContextPath() + "/403.jsp");
			return false;
		}
	}
}
