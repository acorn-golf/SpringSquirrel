<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript" src="/teamSquirrel/jquery-3.4.1.js"></script> 
<script>
	$(document).ready(function(){
		$("#cancle").on("click",function(event){
			event.preventDefault();
			history.back();
		});
	});
</script>
<h3>주문 상품 확인</h3>
<form action="AddOrderServlet" method="post">
<input type="hidden" name="p_id" value="${dto.p_id}">
<table border="1">
	<tr>
		<th colspan="2">골프장</th>
		<th>티업시간</th>
		<th>그린피</th>
	</tr>
	<tr>
		<td width="120"><img src="GOLFCC/${dto.loc_id}/${dto.cc_img}" border="0" align="middle" width="120" height="80" />
		</td>
		<td> 
		<table>
			<tr>
				<td><b>${dto.cc_name}</b></td>
				<td>${dto.p_maxpeople}명  ${dto.p_hole}홀&nbsp;&nbsp;<span style="color:blue">예약인원 : <input type="text" name="o_amount" value="${g_amount}"> 명</span></td>
			</tr>
			<tr>
				<td><font size="2" color="#4374D9">${dto.p_uploaddate}</font></td>
				<td><font size="2" color="#665b5f">캐디유무 : ${dto.p_caddyyn} 식사유무 : ${dto.p_babyn} 카트유무 : ${dto.p_cartyn}</font></td>
			</tr>
		</table>
		</td>
		<td><b>${dto.p_pdate}</b><br>${dto.nickname} ☎${dto.phone_id}</td>
		<td align="center">${dto.p_price} 만원<br>
		<c:if test="${dto.emergency eq '긴급'}">
		<b style="color:red">[${dto.emergency}]</b></c:if></td>
	</tr>
	
</table>
<button id="cancle">취소</button> <input type="submit" value="구매하기">
</form>
