<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$(document).ready(function(){
		$("#cancle").on("click",function(event){
			event.preventDefault();
			history.back();
		});
		$("#amount").on("change",function(){
			$("#order_price").text(Number.parseInt($("#amount").val() * $("#price").text()));
		});
	});
</script>

<form action="addOrder" method="get" class="form_main">
<h3>주문 상품 확인</h3>
<input type="hidden" name="p_id" value="${dto.p_id}">
<c:if test="${pick_no != null}">
	<input type="hidden" name="pick_no" value="${pick_no}">
</c:if>

<input type="hidden" name="o_price" value="${amount * dto.p_price}">

<table class="line_table">
	<tr>
		<th colspan="2" class="line_th">골프장</th>
		<th class="line_th">티업시간</th>
		<th class="line_th">그린피</th>
		<th class="line_th">총 결제금액</th>
	</tr>
	<tr>
		<td width="120" class="line_td"><img src="img/GOLFCC/${dto.loc_id}/${dto.cc_img}"
							onerror="this.src='/golfhi/GOLFCC/noimg.jpg'" border="0" align="middle" width="120" height="80" />
		</td>
		<td class="line_td"> 
		<table>
			<tr>
				<td><b>${dto.cc_name}</b></td>
				<td>${dto.p_maxpeople}명  ${dto.p_hole}홀&nbsp;&nbsp;<span style="color:blue">예약인원 : <input type="number" id="amount" min="1" max="${dto.p_maxpeople}" name="o_amount" value="${amount}"> 명</span></td>
			</tr>
			<tr>
				<td><font size="2" color="#4374D9">${dto.p_uploaddate}</font></td>
				<td><font size="2" color="#665b5f">캐디유무 : ${dto.p_caddyyn} 식사유무 : ${dto.p_babyn} 카트유무 : ${dto.p_cartyn}</font></td>
			</tr>
		</table>
		</td>
		<td class="line_td"><b>${dto.p_pdate}</b><br>${dto.nickname} ☎${dto.phone_id}</td>
		<td align="center" class="line_td"><span id="price">${dto.p_price}</span> 만원<br>
		<c:if test="${dto.emergency eq '긴급'}">
		<b style="color:red">[${dto.emergency}]</b></c:if></td>
		<td class="line_td" align="center"><b style="color:blue"><span id="order_price">${amount * dto.p_price}</span> 만원</b></td>
	</tr>
	<tr>
		<td align="right" colspan="5">
			<button id="cancle">취소</button> <button>구매하기</button><!-- <input type="submit" value="구매하기"> -->
		</td>
	</tr>
</table>

</form>
