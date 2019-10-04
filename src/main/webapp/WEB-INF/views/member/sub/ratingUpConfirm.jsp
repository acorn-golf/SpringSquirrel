<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".approval").on("click",function(){
			var num = $(this).attr("data-num");
			var user_no = $("#user_no"+num).val();
			console.log(user_no);
			$.ajax({
				type : "POST",
				url : "/golfhi/confirmRatingUp",
				data : { 
					num: num,
					user_no: user_no
				},
				datatype : "text",
				success : function(data){
					alert(data);
					location.reload();
				}						
			});
			//$("form").attr({"action":"confirmRatingUp","method":"post"});
		});
		$(".reject").on("click",function(){
			var num = $(this).attr("data-num");
			$.ajax({
				type : "POST",
				url : "/golfhi/deleteRatingTable",
				data : { 
					num: num
				},
				datatype : "text",
				success : function(data){
					alert(data);
					location.reload();
				}						
			});
		});

	})
</script>
<div style="margin-left: 200px;margin-top: 15px">
<h3>등업신청 목록</h3>
<table>
<tr>
<th>유저이름</th>
<th>닉네임</th>
<th>핸드폰 번호</th>
<th colspan="2">이메일</th>
</tr>
<c:choose>
<c:when test="${RatingInfo.size() == 0}">
<tr>
	<td colspan="4">등업 신청목록이 없습니다</td>
</tr>
</c:when>
<c:otherwise>
<c:forEach var="info" items="${RatingInfo}" varStatus="status">
<tr>
<td>${info.username}<input type="hidden" id="user_no${info.num}" name="user_no" value="${info.user_no}"></td>
<td>${info.nickname}</td>
<td>${info.phone_id}</td>
<td>${info.email}</td>
<td><button class="approval" data-num="${info.num}">승인</button>&nbsp;<button class="reject" data-num="${info.num}">거절</button></td>
</tr>
</c:forEach>
</c:otherwise>
</c:choose>


</table>
</div>