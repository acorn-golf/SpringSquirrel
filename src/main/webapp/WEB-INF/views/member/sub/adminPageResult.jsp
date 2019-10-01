<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.js"></script>

<form class="form_main">
	<table class="line_table" style="table-layout: fixed">
		<c:if test="${adminSelect eq 'member'}">
			<c:forEach var="mList" items="${mList}" varStatus="status">
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
					<!-- 번호를 누르면 user_no를 검색 변경 -->
					<td class="line_td"><a
						href="/golfhi/adminModified?item=member&user_no=${mList.user_no}">${mList.phone_id}</a></td>
					<td class="line_td" style="background-color: lightblue">${mList.username}</td>
					<td class="line_td" style="background-color: lightgreen">${mList.nickname}</td>
					<td class="text_center line_td"
						<c:if test="${mList.rating eq 'A'}">style="background-color: #00ff00"</c:if>
						<c:if test="${mList.rating eq 'M'}">style="background-color: #7fffd4"</c:if>>${mList.rating}</td>
					<td class="line_td">${mList.email}</td>
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
					<!-- 골프장명을 누르면 p_id를 검색후 변경 -->
					<td class="line_td"><a
						href="/golfhi/adminModified?item=product&p_id=${pList.p_id}">${pList.cc_name}</a></td>
					<td class="line_td" style="background-color: lightblue">${pList.nickname}</td>
					<td class="line_td" style="background-color: lightgreen">${pList.phone_id}</td>
					<td class="text_center line_td">${pList.p_pdate}</td>
					<td class="text_right line_td">${pList.p_price}</td>
					<td class="line_td">${pList.loc_name}</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${adminSelect eq 'golfcc'}">
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
					<!-- 골프장명을 누르면  cc_id를 검색후 변경-->
					<td class="line_td"><a
						href="/golfhi/adminModified?item=golf&cc_id=${gList.cc_id}">${gList.cc_name}</a></td>
					<td class="line_td"
						style="width: 170px; background-color: lightblue; word-break: break-all;">${gList.cc_addr2}</td>
					<td class="line_td" style="background-color: lightgreen">${gList.cc_phone}</td>
					<td class="text_center line_td"
						style="width: 150px; word-break: break-all;"><a
						href="https://${gList.cc_url}">${gList.cc_url}</a></td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${totalRecord != 0}">
			<tr>
				<td colspan="4" class="text_center"><c:if
						test="${curPage != 1}">
						<a
							href="adminPage?adminSelect=${adminSelect}&curPage=1&adminSearch=${adminSearch}">◀◀</a>&nbsp;&nbsp;</c:if>
					<c:if test="${curPage-showPage >= 1}">
						<a
							href="adminPage?adminSelect=${adminSelect}&curPage=${beforeShow}&adminSearch=${adminSearch}">◁</a>&nbsp;&nbsp;</c:if>

					<c:forEach var="i" begin="${startPage}" end="${lastPage}" step="1">
						<c:choose>
							<c:when test="${curPage eq i}">
								<span style="color: red">${i}</span>
							</c:when>
							<c:when test="${curPage != i}">
								<a
									href="adminPage?adminSelect=${adminSelect}&curPage=${i}&adminSearch=${adminSearch}">${i}</a>&nbsp;
					</c:when>
						</c:choose>
					</c:forEach> <c:if
						test="${endPage != curPage && startPage != endPage}">
						<a
							href="adminPage?adminSelect=${adminSelect}&curPage=${afterShow}&adminSearch=${adminSearch}">▷</a>&nbsp;&nbsp;</c:if>
					<c:if test="${curPage != endPage && curPage != 0}">
						<a
							href="adminPage?adminSelect=${adminSelect}&curPage=${endPage}&adminSearch=${adminSearch}">▶▶</a>
					</c:if></td>
			</tr>
		</c:if>
	</table>
</form>