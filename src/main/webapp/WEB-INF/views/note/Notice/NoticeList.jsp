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
			<th>제목</th>
			<th>날짜</th>
		</tr>
		<c:forEach items="${NoteView}" var="note">
			<tr>
			    <td>${note.note_no}</td>
				<td>${note.nickname}</td>
				<td><a
					href="
					<c:url value="NoteContent">
						<c:param name="note_no" value="${note.note_no}"/>
					</c:url>"
				>${note.note_title}</a></td>
				<td>${note.notedate}</td>
			</tr>
		</c:forEach>

	</table>




</body>
</html>