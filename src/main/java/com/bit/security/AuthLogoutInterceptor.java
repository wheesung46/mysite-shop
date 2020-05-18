package com.bit.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthLogoutInterceptor extends HandlerInterceptorAdapter {
	@Override
	public boolean preHandle(HttpServletRequest request
							, HttpServletResponse response
							, Object handler) throws Exception{
		// 현재 세션을 가져와
		HttpSession session = request.getSession();
		
		// 세년이 존재한다면
		if(session != null) {
			// authUser 속성 삭제
			session.removeAttribute("authUser");
			// 세션 삭제
			session.invalidate();
		}
		
		// 메인 페이지 이동
		response.sendRedirect(request.getContextPath());
		
		// false 반환
		//	-> Controller가 아니라 Interceptor 처리!
		return false;
	}
}
