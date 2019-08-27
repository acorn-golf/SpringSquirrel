<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>환영합니다.^_^</title>
</head>
<body>
<jsp:include page="../show/main.jsp"/>
<hr>
<h1>${login.nickname} 님 성공적으로 가입되셨습니다.</h1>
<button onclick="location.href='../main.jsp'">확인</button>
</body>
</html>