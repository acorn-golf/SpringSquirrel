<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>리뷰게시판</title>
</head>
<body>
	<%-- <jsp:include page="common/top.jsp" flush="true" /><br>
<jsp:include page="common/menu.jsp" flush="true" /> --%>
	<jsp:include page="../show/main.jsp" flush="false" />
	<jsp:include page="sub/review.jsp" flush="true" />
	<c:if test="${Golfcc != null}">
		<jsp:include page="../GOLFCC/sub/golfccRetrieve.jsp" flush="false" />
		<jsp:include page="sub/review.jsp" flush="true" />
	</c:if>
	<jsp:include page="../GOLFCC/sub/ccListGrid.jsp" flush="false" />
</body>
</html>