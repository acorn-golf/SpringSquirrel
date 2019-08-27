<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" src="/teamSquirrel/jquery-3.4.1.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		/* if($(".loc").val()=='${loc_id}'){
			$(this).attr("selected","selected");
		}
		$("#location").on("change",function(){
			location.href="ProductListServlet?loc_id="+${loc_id};
		}); */
		if(${emergency != null}){
			$("input[type='checkbox']").attr("checked","checked");
		}
		
	});
</script>

<form action="ProductListServlet" method="post">

	<select name="loc_id" id="location">
		<option value="all" class="loc">전체</option>
		<option value="gg" class="loc">경기</option>
		<option value="gw" class="loc">강원</option>
		<option value="cc" class="loc">충청</option>
		<option value="jl" class="loc">전라</option>
		<option value="gs" class="loc">경상</option>
		<option value="jj" class="loc">제주</option>
	</select>&nbsp; <input type="radio" name="productOrderby" value="p_pdate"
		class="radio">티업시간 순&nbsp;&nbsp; <input type="radio"
		name="productOrderby" value="cc_name" class="radio">골프장
	이름순&nbsp;&nbsp; <input type="checkbox" name="emergency"
		value="p_pdate-sysdate" class="radio">긴급상품&nbsp;&nbsp;<br>
	<table border="1">
		<tr>
			<td colspan="4"><select name="productDivision">
					<option value="cc_name">골프장 이름</option>
					<option value="nickname">매니저 이름</option>
			</select> <input type="text" name="productValue" size="30"> <input
				type="submit" value="검색" style="width:50px"></td>
		</tr>
		<tr>
			<th colspan="2">골프장</th>
			<th>티업시간</th>
			<th>그린피</th>
		</tr>
		<c:choose>
			<c:when test="${empty productList}">
				<tr>
					<td colspan="4" align="center"><h3 style="color:#665b5f">상품이 없습니다</h3></td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="pList" items="${productList}">
					<tr>
						<td width="120"><img
							src="GOLFCC/${pList.loc_id}/${pList.cc_img}"
							onerror="this.src='/teamSquirrel/GOLFCC/noimg.jpg'" border="0"
							align="middle" width="120" height="80" /></td>
						<td>
							<table>
								<tr>
									<td><a href="ProductRetrieveServlet?p_id=${pList.p_id}"><b>${pList.cc_name}</b></a></td>
									<td><span>${pList.p_maxpeople}명 ${pList.p_hole}홀</span></td>
								</tr>
								<tr>
									<td><font size="2" color="#4374D9">${pList.p_uploaddate}</font></td>
									<td><font size="2" color="#665b5f">캐디유무 :
											${pList.p_caddyyn} 식사유무 : ${pList.p_babyn} 카트유무 :
											${pList.p_cartyn}</font></td>
								</tr>
							</table>
						</td>
						<td><b>${pList.p_pdate}</b><br>${pList.nickname}
							☎${pList.phone_id}</td>
						<td align="center">${pList.p_price}만원<br> <c:if
								test="${pList.emergency eq '긴급'}">
								<b style="color: red">[${pList.emergency}]</b>
							</c:if></td>
					</tr>
				</c:forEach>

				<tr>
					<td colspan="4" align="center">
						<c:set var="curPage" value="${curPage+1}" /> <%-- 1 --%> 
						<c:set var="maxBlock" value="${maxBlock}" />
						<c:set var="minBlock" value="${minBlock+1}" /> 
						<c:if test="${curPage != 1}">
							<a href="ProductListServlet?curPage=1">◀◀</a>&nbsp;&nbsp;
								<c:if test="${curPage>showBlock}">
									<a href="ProductListServlet?curPage=${minBlock-1}">◁</a>&nbsp;&nbsp;
								</c:if>
						</c:if> &nbsp;&nbsp; 
						<c:forEach var="i" begin="${minBlock}" end="${maxBlock}" step="1">
							<c:choose>
								<c:when test="${curPage eq i}">
									<span style="color: red">${i}</span>
								</c:when>
								<c:when test="${curPage != i}">
									<a href="ProductListServlet?curPage=${i}">${i}</a>&nbsp;
								</c:when>
							</c:choose>
						</c:forEach>&nbsp;&nbsp; 
						<c:if test="${curPage != totalPage}">
							<c:if test="${curPage<=showBlock*perBlock}">
								<a href="ProductListServlet?curPage=${maxBlock+1}">▷</a>
							</c:if>
							<a href="ProductListServlet?curPage=${totalPage}">▶▶</a>
						</c:if>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>

	</table>

</form>