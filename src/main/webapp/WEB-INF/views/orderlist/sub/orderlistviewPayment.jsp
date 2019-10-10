<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
	});
</script>


<form action="orderList" method="post" class="form_main">
<h3>결제 내역</h3>
<table class="line_table">
	<tr>
		<th class="line_th">상품명</th>
		<th class="line_th">판매가</th>
		<th class="line_th">수량</th>
		<th class="line_th">총 구매 가격</th>
		<th class="line_th">구매일</th>
		<th class="line_th">담당 매니저</th>
	</tr>
	<c:choose>
		<c:when test="${empty orderList}">
			<td colspan="6" align="center" class="line_td"><h3 style="color: #665b5f">결제내역이 없습니다</h3></td>
		</c:when>
		<c:otherwise>
			<c:forEach var="oList" items="${orderList}">
	<tr>
		<td class="line_td" align="center">${oList.cc_name}</td>
		<td class="line_td" align="center">${oList.p_price} 만원</td>
		<td class="line_td" align="center">${oList.o_amount} 명</td>
		<td class="line_td" align="center">${oList.o_price} 만원</td>
		<td class="line_td" align="center">${oList.o_date}</td>
		<td class="line_td" align="center">${oList.nickname} <font style="color:blue" size="3">☎ ${oList.phone_id}</font></td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="6" align="center">
			<div class="list_number">
				<c:set var="curPage" value="${curPage+1}" /> <%-- 1 --%> 
				<c:set var="maxBlock" value="${maxBlock}" />
				<c:set var="minBlock" value="${minBlock+1}" /> 
				<p><div class="list_n_menu">					
				<c:if test="${curPage != 1}">
					<a href="orderListPayment?curPage=1">〈〈</a>
						<c:choose>
							<c:when test="${curPage>showBlock}">
								<a href="orderListPayment?curPage=${minBlock-1}">〈</a>
							</c:when>
							<c:otherwise>
								<span class="disabled">〈</span>
							</c:otherwise>
						</c:choose>
						
				</c:if>
				<c:if test="${curPage == 1}">
					<span class="disabled">〈〈</span>
					<span class="disabled">〈</span>
				</c:if>
				<c:forEach var="i" begin="${minBlock}" end="${maxBlock}" step="1">
					<c:choose>
						<c:when test="${curPage eq i}">
							<span class="current">${i}</span>
						</c:when>
						<c:when test="${curPage != i}">
							<a href="orderListPayment?curPage=${i}">${i}</a>
						</c:when>
					</c:choose>
				</c:forEach>
				
				<c:if test="${curPage != totalPage}">
					<c:choose>
						<c:when test="${curPage<=showBlock*perBlock}">
							<a href="orderListPayment?curPage=${maxBlock+1}">〉</a>
						</c:when>
						<c:otherwise>
							<span class="disabled">〉</span>
						</c:otherwise>
					</c:choose>
					<a href="orderListPayment?curPage=${totalPage}">〉〉</a>
				</c:if>
				<c:if test="${curPage == totalPage}">
					<span class="disabled">〉</span>
					<span class="disabled">〉〉</span>
				</c:if>
				</div></p>
			</div>
		</td>
	</tr>
		</c:otherwise>
	</c:choose>
	
</table>

</form>