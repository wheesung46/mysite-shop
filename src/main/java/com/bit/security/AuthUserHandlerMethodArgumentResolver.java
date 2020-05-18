package com.bit.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.bit.myshop.vo.UserVo;

/*
	HandlerMethodArgumentResolver interface
		- 해당 인터페이스를 상속하여 ArgumentResolver 구현! (사용자 정의)
		- 유저 요청이 Controller로 넘어가기 전 요첨에 대한 파라미터 제어 가능!
		- 로그인 정보를 통해 해당 유저의 모든 정보를 가져와 사용하자니,
			매번 세션에서 가져와 따로 추가시키는 형태
			 -> AuthInterceptor
			
		ArgumentResolver
			 -> UserVo 객체의 바인딩을 통해 중복 코드를 최소화!
			 -> Controller에서  Parameter 바인딩 역할!
 */

public class AuthUserHandlerMethodArgumentResolver 
	implements HandlerMethodArgumentResolver {
	
	/*
		supportsParameter(...)
			- Resolver가 적용 가능한지 검사하는 역할
				-> @AuthUser가 붙어 있는지
			- 바인딩할 클래스를 지정하는 메소드
				-> UserVo 객체를 바인딩!
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser
			= parameter.getParameterAnnotation(AuthUser.class);
		
		// 1단계 : @AuthUser 여부 판단
		if(authUser == null)
			return false; // 바인딩을 수행하지 마라.
		
		// 2단계 : 파라미터의 타입이 UserVo인지 판단
		if(parameter.getParameterType().equals(UserVo.class)== false)
			return false; // 바인딩을 수행하지 마라.
		
		// 3단계 : 모두 만족하는 상태
		//	-> @AuthUser가 붙어 있는 UserVo 타입이라면!
		return true; // 데이터(객체) 바인딩을 수행해라.
	}
	
	/*
		resolveArgument(...)
			- 파라미터 정보를 받아 실제 객체를 반환
			- 바인딩할 객체를 제어할 수 있는 메소드
			- 객체를 생성하여 데이터를 입력하는 역할 (단, 세션 활용)
	 */
	@Override
	public Object resolveArgument(
				MethodParameter parameter,
				ModelAndViewContainer mavContainer,
				NativeWebRequest webRequest,
				WebDataBinderFactory binderFactory
				) throws Exception{
		// 1단계 : supportsParameter(...) 결과 판단
		if(supportsParameter(parameter) == false)
			return WebArgumentResolver.UNRESOLVED;
			// 해당 파라미터는 처리 불가능한 파라미터임을 전달!
		
		// 2단계 : 모두 만족하는 상태, 1단계 결과 -> true
		HttpServletRequest request
			= webRequest.getNativeRequest(HttpServletRequest.class);
		// 웹 환경의 요청 객체를 활용하여,
		// HttpServletRequest 객체 형식을 전달 후 요청 객체 반환!
		//	-> 서블릿 요청 객체를 가져와야 세션을 가져올 수 있다.
		
		HttpSession session = request.getSession(); // 세션을 가져오되,
		if(session == null) // 세션이 존재하지 않는다면,
			return WebArgumentResolver.UNRESOLVED;
			// 리졸버 처리가 불가능함을 전달, 더 이상 수행하지 마라.
		
		// 세션이 존재한다면,
		// 세션의 "authUser"를 가져와 해당 파라미터와 바인딩
		return session.getAttribute("authUser"); // UserVo
	}
}
