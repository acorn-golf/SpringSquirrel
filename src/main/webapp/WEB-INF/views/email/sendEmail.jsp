<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일</title>
</head>
<body>
<%-- <jsp:include page="common/top.jsp" flush="true" /><br>
<jsp:include page="common/menu.jsp" flush="true" /> --%>
<jsp:include page="/show/main.jsp" flush="false"/>

<jsp:include page="sub/sendEmail.jsp" flush="true" />
</body>
</html>