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


<form action="dealHistory" method="post" style="margin-top: 15px;margin-left: 200px">
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
			<div class="list_number">
				<c:set var="curPage" value="${curPage+1}" /> <%-- 1 --%> 
				<c:set var="maxBlock" value="${maxBlock}" />
				<c:set var="minBlock" value="${minBlock+1}" /> 
				<p><div class="list_n_menu">					
				<c:if test="${curPage != 1}">
					<a href="dealHistory?curPage=1">〈〈</a>
						<c:choose>
							<c:when test="${curPage>showBlock}">
								<a href="dealHistory?curPage=${minBlock-1}">〈</a>
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
							<a href="dealHistory?curPage=${i}">${i}</a>
						</c:when>
					</c:choose>
				</c:forEach>
				
				<c:if test="${curPage != totalPage}">
					<c:choose>
						<c:when test="${curPage<=showBlock*perBlock}">
							<a href="dealHistory?curPage=${maxBlock+1}">〉</a>
						</c:when>
						<c:otherwise>
							<span class="disabled">〉</span>
						</c:otherwise>
					</c:choose>
					<a href="dealHistory?curPage=${totalPage}">〉〉</a>
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