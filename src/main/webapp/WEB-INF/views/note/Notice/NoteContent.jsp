<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="../../show/main.jsp" flush="false" />

	<table border="1">
		<tr>
		    <th>글번호</th>
			<th>작성자</th>
			<th>내용</th>
		</tr>
		<c:forEach items="${NoteContent}" var="note">
			<tr>
			    <td>${note.note_no}</td>
				<td>${note.nickname}</td>
				<td>${note.note_content}</td>
			</tr>
		</c:forEach>

	</table>
<c:if test="${empty login.rating eq 'A'}">
<a href="NoteUpdate">
<input type="submit" value="수정">
</a>
<a href="NoteDelete">
<input type="submit" value="삭제">
</a>
</c:if>



</body>
</html>