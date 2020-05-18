package com.bit.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.bit.myshop.vo.UserVo;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request
							, HttpServletResponse response
							, Object handler
							) throws Exception{
		if(handler instanceof HandlerMethod == false) {
			return true;
		}
		
		Auth auth = ((HandlerMethod)handler).getMethodAnnotation(Auth.class);
		if(auth==null) return true;
		
		HttpSession session = request.getSession();
		if(session == null) {
			response.sendRedirect(
					request.getContextPath() + "/user/loginform"
					);
		}
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		if(authUser == null) {
			response.sendRedirect(
					request.getContextPath() + "/user/loginform"
					);
			return false;
		}
		
		return true;
	}
}
