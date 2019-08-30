<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일</title>
<c:if test="${login.email_chk eq 'N' || login.email_chk==null }">
	<script type="text/javascript">
		var con_email = confirm("이메일 인증을 하시면 비밀번호 찾기 시 임시번호를 받을 수 있습니다\n 인증하시겠습니까?");
		if (con_email == true) {
			location.href='../sendMail';
		} else if (con_email == false) {
			alert('이메일 인증은 마이페이지에서 가능합니다');
			location.href='/golfhi/';
		}
	</script>
</c:if>
<c:if test="${login.email_chk eq 'Y' }">
	<script type="text/javascript">
		location.href='/golfhi/';
	</script>
</c:if>
<c:if test="${login == null}">
	<script type="text/javascript">
		location.href='/golfhi/';
	</script>
</c:if>
</head>
<body>
</body>
</html>