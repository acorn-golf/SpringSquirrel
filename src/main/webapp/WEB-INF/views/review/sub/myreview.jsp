<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		/* if($(".loc").val()=='${loc_id}'){
			$(this).attr("selected","selected");
		}
		$("#location").on("change",function(){
			location.href="myReview?loc_id="+${loc_id};
		}); */
		
		var reviewDivision = '${reviewDivision}';		
		if(reviewDivision != ""){
			$("select").val(reviewDivision);
		}else{
			$("select").val('rv_title');
		}
		
	});
</script>

<form action="myReview" method="post" style="margin-left:200px;margin-top: 15px">
<h3>나의 골프장 후기</h3>
<%System.out.print(request.getRequestURI());  %>
	<table class="line_table">
		<tr>
			<td colspan="5" style="border-bottom: 1px solid #444444;width: 500px" align="center">
				<select name="reviewDivision" >
					<option value="rv_title">글 제목</option>
					<option value="cc_name">골프장</option>
				</select> 
					<input type="text" name="reviewValue" value="${reviewValue}" size="30"> 
					<input type="submit" value="검색" style="width:50px"></td>
		</tr>
		<tr>
			<th style="border-bottom: 1px solid #444444">골프장</th>
			<th style="border-bottom: 1px solid #444444">글 제목</th>
			<th style="border-bottom: 1px solid #444444">평점</th>
			<th style="border-bottom: 1px solid #444444">조회수</th>
			<th style="border-bottom: 1px solid #444444">등록일자</th>
		</tr>
		<c:choose>
			<c:when test="${empty myReviewList}">
				<tr>
					<td colspan="5" align="center" style="border-bottom: 1px solid #444444"><h3 style="color:#665b5f">등록한 게시물이 없습니다</h3></td>
				
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="pList" items="${myReviewList}">
					<tr>
						<td width="120" style="border-bottom: 1px solid #444444" align="center">
							<img src="img/GOLFCC/${pList.loc_id}/${pList.cc_img}" onerror="this.src='<c:url value="img/GOLFCC/noimg.jpg"/>'" border="0" align="middle" width="120" height="80" /><br>
							<b>${pList.cc_name}</b></td>
						<td style="border-bottom: 1px solid #444444">
							<a href="reviewDetail?score_no=${pList.score_no}&user_no=${pList.user_no}">${pList.rv_title}</a>
						</td>
						<td style="border-bottom: 1px solid #444444">
							<b>${pList.score}</b>
						</td>
						<td align="center" style="border-bottom: 1px solid #444444">
							${pList.rv_vcount}
						</td>
						<td align="center" style="border-bottom: 1px solid #444444">
							${pList.score_date}
						</td>
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
							<a href="myReview?curPage=1">〈〈</a>
								<c:choose>
									<c:when test="${curPage>showBlock}">
										<a href="myReview?curPage=${minBlock-1}">〈</a>
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
									<a href="myReview?curPage=${i}">${i}</a>
								</c:when>
							</c:choose>
						</c:forEach>
						
						<c:if test="${curPage != totalPage}">
							<c:choose>
								<c:when test="${curPage<=showBlock*perBlock}">
									<a href="myReview?curPage=${maxBlock+1}">〉</a>
								</c:when>
								<c:otherwise>
									<span class="disabled">〉</span>
								</c:otherwise>
							</c:choose>
							<a href="myReview?curPage=${totalPage}">〉〉</a>
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