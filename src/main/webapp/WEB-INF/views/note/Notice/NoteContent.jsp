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
	
			<tr>
			    <td>${NoteContent.note_no}</td>
				<td>${NoteContent.nickname}</td>
				<td>${NoteContent.note_content}</td>
			</tr>
		

	</table>
<c:if test="${empty login.rating eq 'A'}">
<a href="NoteUpdate?note_no= ${NoteContent.note_no}">
<input type="button" value="수정">
</a>
<a href="NoteDelete?note_no= ${NoteContent.note_no}">
<input type="button" value="삭제">
</a>
</c:if>



</body>
</html>