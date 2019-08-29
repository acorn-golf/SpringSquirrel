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
		// 어노테이션이 없는 경우 아래 작업 미실행

		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO) session.getAttribute("login");

		if (dto == null) {
			String mesg = "";
			mesg+="<script type=\"text/javascript\">";
			mesg+="alert('로그인 해주세요');";
			mesg+="</script>";
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().print(mesg);
			response.sendRedirect("loginForm");
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
			else {
				String mesg = "";
				mesg+="<script type=\"text/javascript\">";
				mesg+="alert('권한이 없습니다');";
				mesg+="</script>";
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print(mesg);
				response.sendRedirect("main");
				return false; 
			}
			// 나중에 에러 메세지를 통해 매세지또한 보내주기

			// return true;
		}
	}
//
//	@Override
//	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
//			ModelAndView modelAndView) throws Exception {
//
//	}
//
//	@Override
//	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
//			throws Exception {
//	
//	}

}
