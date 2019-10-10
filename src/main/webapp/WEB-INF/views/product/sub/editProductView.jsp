<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<c:if test="${mesg != null}">
	<script>
		alert('${mesg}');
	</script>
</c:if>
<script type="text/javascript">
	$(document).ready(function(){
		
		// 전체선택
		$("#chkall").on("click",function(){
			$(".check").each(function(idx,ele){
				$(ele).prop("checked",$("#chkall").prop("checked"));
			});
		});
		
		// 하나라도 체크 풀면 전체선택 체크풀림
		$(".check").on("click",function(){
			if($(".check:checked").length == ${productList.size()}){
				$("#chkall").prop("checked",true);
			}else{
				$("#chkall").prop("checked",false);
			}
		});
				
		
		
	});
</script>

<h3 style="margin-left:200px;margin-top: 15px">상품 관리</h3>
	<table class="line_table" style="margin-left:200px">
		<tr>
			<th colspan="2" style="border-bottom: 1px solid #444444">골프장</th>
			<th style="border-bottom: 1px solid #444444">티업시간</th>
			<th style="border-bottom: 1px solid #444444">그린피</th>
		</tr>
		<c:choose>
			<c:when test="${empty productList}">
				<tr>
					<td colspan="4" align="center" style="border-bottom: 1px solid #444444"><h3 style="color:#665b5f">상품이 없습니다</h3></td>
				
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="pList" items="${productList}">
				<input type="hidden" name="p_id" value="${pList.p_id}">
					<tr>
						<td width="120" style="border-bottom: 1px solid #444444"><img
							src="img/GOLFCC/${pList.loc_id}/${pList.cc_img}"
							onerror="this.src='<c:url value="img/GOLFCC/noimg.jpg"/>'" border="0"
							align="middle" width="120" height="80" /></td>
						<td style="border-bottom: 1px solid #444444">
							<table>
								<tr>
									<td><a href="editProduct?p_id=${pList.p_id}"><b>${pList.cc_name}</b></a></td>
									<td><span>${pList.p_maxpeople}명 
										${pList.p_hole}홀</span></td>
								</tr>
								<tr>
									<td><font size="2" color="#4374D9">${pList.p_uploaddate}</font></td>
									<td>
										<font size="2" color="#665b5f">캐디유무 : ${pList.p_caddyyn}</font>
										<font size="2" color="#665b5f">식사유무 : ${pList.p_babyn}</font>
										<font size="2" color="#665b5f"> 카트유무 : ${pList.p_cartyn}</font>
									</td>
								</tr>
							</table>
						</td >
						<td style="border-bottom: 1px solid #444444">
							<b>${pList.p_pdate}</b><br>${pList.nickname}
							☎${pList.phone_id}</td>
						<td align="center" style="border-bottom: 1px solid #444444">
							${pList.p_price} 만원<br> 
							<c:if test="${pList.emergency eq '긴급'}">
								<b style="color: red">[${pList.emergency}]</b>
							</c:if></td>
					</tr>
				</c:forEach>

				<tr>
					<td colspan="5" align="center" style="border-bottom: 1px solid #444444">
												<div class="list_number">
						<c:set var="curPage" value="${curPage+1}" /> <%-- 1 --%> 
						<c:set var="maxBlock" value="${maxBlock}" />
						<c:set var="minBlock" value="${minBlock+1}" /> 
						<p><div class="list_n_menu">					
						<c:if test="${curPage != 1}">
							<a href="editProductView?curPage=1">〈〈</a>
								<c:choose>
									<c:when test="${curPage>showBlock}">
										<a href="editProductView?curPage=${minBlock-1}">〈</a>
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
									<a href="editProductView?curPage=${i}">${i}</a>
								</c:when>
							</c:choose>
						</c:forEach>
						
						<c:if test="${curPage != totalPage}">
							<c:choose>
								<c:when test="${curPage<=showBlock*perBlock}">
									<a href="editProductView?curPage=${maxBlock+1}">〉</a>
								</c:when>
								<c:otherwise>
									<span class="disabled">〉</span>
								</c:otherwise>
							</c:choose>
							<a href="editProductView?curPage=${totalPage}">〉〉</a>
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
