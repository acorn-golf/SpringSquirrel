<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript" src="/teamSquirrel/jquery-3.4.1.js"></script> 
<script type="text/javascript">
	$(document).ready(function(){
		
	});
</script>

<form action="ProductListServlet" method="post">

<table border="1">
	<tr>
		<th>상품명</th>
		<th>판매가</th>
		<th>수량</th>
		<th>총 구매 가격</th>
		<th>구매일</th>
		<th>담당 매니저</th>
	</tr>
	<c:forEach var="oList" items="${orderList}">
	<tr>
		<td>${oList.cc_name}</td>
		<td>${oList.p_price} 만원</td>
		<td>${oList.o_amount} 명</td>
		<td>${oList.o_price} 만원</td>
		<td>${oList.o_date}</td>
		<td>${oList.nickname} <font style="color:blue" size="3">☎ ${oList.phone_id}</font></td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="6" align="center">
			<c:set var="curPage" value="${curPage+1}"/> <%-- 1 --%>
			<c:set var="maxBlock" value="${maxBlock}"/> 
			<c:set var="minBlock" value="${minBlock+1}"/> 
			
			<c:if test="${curPage != 1}">
				<a href="ProductListServlet?curPage=1">◀◀</a>&nbsp;&nbsp;
				<c:if test="${curPage>showBlock}"><a href="ProductListServlet?curPage=${minBlock-1}">◁</a>&nbsp;&nbsp;</c:if>
			</c:if>
			&nbsp;&nbsp;
			
			<c:forEach var="i" begin="${minBlock}" end="${maxBlock}" step="1">
				<c:choose>
					<c:when test="${curPage eq i}">
						<span style="color:red">${i}</span>
					</c:when>
					<c:when test="${curPage != i}">
						<a href="ProductListServlet?curPage=${i}">${i}</a>&nbsp;
					</c:when>
				</c:choose>	
			</c:forEach>&nbsp;&nbsp;
			
			<c:if test="${curPage != totalPage}">
				<c:if test="${curPage<=showBlock*perBlock}"><a href="ProductListServlet?curPage=${maxBlock+1}">▷</a></c:if>
				<a href="ProductListServlet?curPage=${totalPage}">▶▶</a>
			</c:if>
			
		</td>
	</tr>
</table>

</form>