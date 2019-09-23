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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">

<form action="orderList" method="post" style="margin-top: 15px;margin-left: 200px">
<h3>거래 내역</h3>
<table class="line_table">
	<tr>
		<th class="line_th">상품</th>
		<th class="line_th">티업시간</th>
		<th class="line_th">등록시간</th>
		<th class="line_th">상품 가격</th>
		<th class="line_th">구매고객</th>
		<th class="line_th">전화번호</th>
		<th class="line_th">이메일</th>
		<th class="line_th">구매일</th>
		<th class="line_th">구매 수량</th>
		<th class="line_th">구매 금액</th>
	</tr>
	<c:choose>
		<c:when test="${empty dealHistoryList}">
			<td colspan="10" align="center" class="line_td"><h3 style="color: #665b5f">거래내역이 없습니다</h3></td>
		</c:when>
		<c:otherwise>
			<c:forEach var="dList" items="${dealHistoryList}">
			<tr>
				<td width="120" style="border-bottom: 1px solid #444444" align="center">
					<img src="img/GOLFCC/${dList.loc_id}/${dList.cc_img}" onerror="this.src='<c:url value="img/GOLFCC/noimg.jpg"/>'"
				 	border="0" align="middle" width="120" height="80" /><br><b style="color:blue">${dList.cc_name}</b></td>
				<td class="line_td" align="center">${dList.p_pdate}</td>
				<td class="line_td" align="center">${dList.p_uploaddate}</td>
				<td class="line_td" align="center">${dList.p_price} 만원</td>
				<td class="line_td" align="center">${dList.nickname}</td>
				<td class="line_td" align="center"><font style="color:blue" size="3">☎ ${dList.phone_id}</font></td>
				<td class="line_td" align="center">${dList.email}</td>
				<td class="line_td" align="center">${dList.o_date}</td>
				<td class="line_td" align="center">${dList.o_amount} 명</td>
				<td class="line_td" align="center">${dList.o_price} 만원</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="10" align="center">
		<ul class="pagination justify-content-center" style="margin-top:10px">
			<c:set var="curPage" value="${curPage+1}"/> <%-- 1 --%>
			<c:set var="maxBlock" value="${maxBlock}"/> 
			<c:set var="minBlock" value="${minBlock+1}"/> 
			
			<c:if test="${curPage != 1}">
				<li class="page-item"><a class="page-link" href="orderList?curPage=1"><<</a></li>
				<c:if test="${curPage>showBlock}"><li class="page-item"><a class="page-link" href="orderList?curPage=${minBlock-1}"><</a></li></c:if>
			</c:if>
			
			
			<c:forEach var="i" begin="${minBlock}" end="${maxBlock}" step="1">
				<c:choose>
					<c:when test="${curPage eq i}">
						<li class="page-item disabled"><a class="page-link" href="orderList?curPage=${i}">${i}</a></li>
					</c:when>
					<c:when test="${curPage != i}">
						<li class="page-item"><a class="page-link" href="orderList?curPage=${i}">${i}</a></li>
					</c:when>
				</c:choose>	
			</c:forEach>
			
			<c:if test="${curPage != totalPage}">
				<c:if test="${curPage<=showBlock*perBlock}"><li class="page-item"><a class="page-link" href="orderList?curPage=${maxBlock+1}">></a></li></c:if>
				<li class="page-item"><a class="page-link" href="orderList?curPage=${totalPage}">>></a></li>
			</c:if>
			</ul>
		</td>
	</tr>
		</c:otherwise>
	</c:choose>
	
</table>

</form>