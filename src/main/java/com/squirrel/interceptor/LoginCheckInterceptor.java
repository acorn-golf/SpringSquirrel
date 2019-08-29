package com.squirrel.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.annotation.Loginchk;
import com.squirrel.dto.MemberDTO;

public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (!(handler instanceof HandlerMethod))
			return true;

		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Loginchk loginchk = handlerMethod.getMethodAnnotation(Loginchk.class);
		if (loginchk == null) {
			return true;
		}
		// �뼱�한글
	

		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");

		if (dto == null) {
			response.sendRedirect("../loginForm");
			return false;
		} else {
			boolean chk = false;

			switch (loginchk.role()) {

			case USER:
				chk = chk || (dto.getRating().equals("U"));

			case MANAGER:
				chk = chk || (dto.getRating().equals("M"));

			case ADMIN:
				chk = chk || (dto.getRating().equals("A"));

			default:
				break;
			}

			if (chk)
				return true;
			else
				return false;
			// �굹以묒뿉 �뿉�윭 硫붿꽭吏�瑜� �넻�빐 留ㅼ꽭吏��삉�븳

			// return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
	}

}
