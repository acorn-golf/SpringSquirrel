<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 리스트</title>
</head>
<body>
<%-- <jsp:include page="common/top.jsp" flush="true" /><br>
<jsp:include page="common/menu.jsp" flush="true" /> --%>
<%System.out.println(request.getRequestURI());  %>
<%-- <jsp:include page="../show/main.jsp"/> --%>
<%System.out.println("에러1");  %>
<jsp:include page="sub/productList.jsp" flush="true" />
<%System.out.println("에러2");  %>
</body>
</html>