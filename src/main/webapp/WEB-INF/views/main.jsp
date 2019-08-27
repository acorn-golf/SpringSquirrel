<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${mesg != null|| not mesg eq ''}">
<script type="text/javascript">
alert('${mesg}');

</script>


<c:set var="mesg" value="${null}" scope="session"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Squirrel Team Project</title>
</head>
<body>
<jsp:include page="show/main.jsp"/>
<hr>
</body>
</html>