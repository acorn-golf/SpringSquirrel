<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> 
<script>
	$(document).ready(function(){
		$("#before").on("click",function(){
			window.history.back();
		});
	});
</script>

<form action="updateReviewDetail" method="get" class="form_main">
<input type="hidden" name="score_no" value="${reviewdetail.score_no}">
<input type="hidden" name="user_no" value="${reviewdetail.user_no}">
<table class="line_table">
			<tr>
				<th class="line_th">작성자</th>
				<td class="line_td">${nickname}</td>
				<th class="line_th">평점</th>
				<td class="line_td"><select name="score">
					<c:forEach var="i" begin="0" end="5" step="1">
						<c:choose>
							<c:when test="${reviewdetail.score eq i}">
								<option selected="selected">${reviewdetail.score}</option>
							</c:when>
							<c:when test="${reviewdetail.score != i}">
								<option>${i}</option>
							</c:when>
						</c:choose>
					</c:forEach>
				</select></td>
			</tr>
			<tr>
				<th class="line_th">작성일</th>
				<td class="line_td">${reviewdetail.score_date}</td>
				<th class="line_th">조회수</th>
				<td class="line_td">${reviewdetail.rv_vcount}</td>
			</tr>
			<tr>
				<th class="line_th">제목</th>
				<td colspan="3"  class="line_td"><input type="text" name="rv_title" value="${reviewdetail.rv_title}" size="39"></td>
			</tr>
			<tr>
				<th  class="line_th">내용</th>
				<td colspan="3" class="line_td"><textarea name="rv_content" cols="40" rows="3">${reviewdetail.rv_content}</textarea></td>
			</tr>
</table>
<input type="button" value="이전" id="before">&nbsp;<input type="submit" value="수정하기">
</form>