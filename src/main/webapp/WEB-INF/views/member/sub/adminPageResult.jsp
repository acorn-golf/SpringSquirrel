<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.js"></script>

<form method="get" class="form_main">
<table class="line_table">
<c:if test="${adminSelect eq 'member' || adminSelect eq 'none'}">
<c:forEach var="member" items="${list}" varStatus="status">
<c:if test="${status.first}">
<tr>
<th class="line_th">아이디</th>
<th class="line_th">이름</th>
<th class="line_th">닉네임</th>
<th class="line_th">등급</th>
<th class="line_th">e-mail</th>
</tr>
</c:if>
<tr>
<td class="line_td" ><a href="/teamSquirrel/AdminModifiedUIServlet?phoneid=${member.phone_id}">${member.phone_id}</a></td>
<td class="line_td" style="background-color: lightblue">${member.username}</td>
<td class="line_td" style="background-color: lightgreen">${member.nickname}</td>
<td class="text_center line_td" 
<c:if test="${member.rating eq 'A'}">style="background-color: #00ff00"</c:if>
<c:if test="${member.rating eq 'M'}">style="background-color: #7fffd4"</c:if>>${member.rating}</td>
<td class="line_td">${member.email}</td>
</tr>
</c:forEach>
</c:if>
<c:if test="${adminSelect eq 'product'}">
<c:forEach var="pList" items="${pList}" varStatus="status">
<c:if test="${status.first}">
<tr>
<th class="line_th">골프장명</th>
<th class="line_th">매니져</th>
<th class="line_th">매니져폰</th>
<th class="line_th">티업시간</th>
<th class="line_th">그린피</th>
<th class="line_th">지역</th>
</tr>
</c:if>
<tr>
<td class="line_td">${pList.cc_name}</td>
<td class="line_td" style="background-color: lightblue">${pList.nickname}</td>
<td class="line_td" style="background-color: lightgreen">${pList.phone_id}</td>
<td class="text_center line_td">${pList.p_pdate}</td>
<td class="text_right line_td">${pList.p_price}</td>
<td class="line_td">${pList.loc_name}</td>
</tr>
</c:forEach>
</c:if>
<c:if test="${adminSelect eq 'ccinfo'}">
<c:forEach var="gList" items="${gList}" varStatus="status">
<c:if test="${status.first}">
<tr>
<th class="line_th">골프장명</th>
<th class="line_th">주소</th>
<th class="line_th">전화번호</th>
<th class="line_th">url</th>
</tr>
</c:if>
<tr>
<td class="line_td">${gList.cc_name}</td>
<td class="line_td" style="background-color: lightblue">${gList.cc_addr2}</td>
<td class="line_td" style="background-color: lightgreen">${gList.cc_phone}</td>
<td class="text_center line_td">${gList.cc_url}</td>
</tr>
</c:forEach>
</c:if>
<c:if test="${adminSearch eq '' || adminSearch eq null }">
<tr>
<td colspan="4" class="text_center">
	<c:if test="${curPage != 1}">
				<a href="AdminPageServlet?adminSelect=${adminSelect}&curPage=1">◀◀</a>&nbsp;&nbsp;</c:if>
		<c:if test="${curPage-showPage >= 1}"><a href="AdminPageServlet?adminSelect=${adminSelect}&curPage=${startPage-showPage}">◁</a>&nbsp;&nbsp;</c:if>			
			
		<c:forEach var="i" begin="${startPage}" end="${lastPage}" step="1">	
				<c:choose>
					<c:when test="${curPage eq i}">
						<span style="color:red">${i}</span>
					</c:when>
					<c:when test="${curPage != i}">
						<a href="AdminPageServlet?adminSelect=${adminSelect}&curPage=${i}">${i}</a>&nbsp;
					</c:when>
				</c:choose>	
		</c:forEach>
			
				<c:if test="${endPage != curPage && lastPage != endPage && startPage != endPage}">		
				<a href="AdminPageServlet?adminSelect=${adminSelect}&curPage=${lastPage+1}">▷</a>&nbsp;&nbsp;</c:if>
				<%-- test="${endPage == lastPage || curPage < endPage}">
				<a href="AdminPageServlet?adminSelect=${adminSelect}&curPage=${startPage+lastPage-1}">▷</a> --%>
				<c:if test="${curPage != endPage && curPage != 0}">	<a href="AdminPageServlet?adminSelect=${adminSelect}&curPage=${endPage}">▶▶</a></c:if>
</tr>
</c:if>
</table>
</form>