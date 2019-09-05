<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:choose>
	<c:when test="${login != null}">
		<h2 style="margin-left: 200px">${login.username}님에게 인증메일을 보냈습니다</h2>
	</c:when>
	<c:otherwise>
		<h2 style="margin-left: 200px">${email} 로 임시비밀번호를 보냈습니다</h2>
		<a href="/golfhi/member/loginForm" style="margin-left: 200px"><button>로그인페이지</button></a>
	</c:otherwise>
</c:choose>
