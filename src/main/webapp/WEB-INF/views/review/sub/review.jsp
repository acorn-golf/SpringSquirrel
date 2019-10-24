<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$("#insertReview").on("click", function() {
			$("form").attr({
				"action" : "insertReviewForm",
				"method" : "post"
			});
		});
		/* $(".radio").on("click",function(){
			$(".radio").each(function(idx,ele){
				//location.href="reviewList?orderby="+$(ele).val();
				var ${orderby} = String.valueOf(${orderby});
				if($(ele).val()==${orderby}){
					console.log("hi");
				}
			});
		}); */
	});
</script>

<form name="ReviewListForm" action="reviewList" method="get" style="margin-left: 200px;margin-top: 15px">
	<input type="hidden" name="cc_id" value="${cc_id}">
	<!-- 후기글쓰기 할 때 갖고갈 파라미터 -->

	
	<table>
		<tr>
			<td colspan="6" align="right">
				<input type="radio" name="orderby" value="score_date" class="radio" style="width: 20px">최신순&nbsp;&nbsp;
				<input type="radio" name="orderby" value="score" class="radio" style="width: 20px">평점순&nbsp;&nbsp;
				<input type="radio" name="orderby" value="rv_vcount" class="radio" style="width: 20px">조회순<br>
			</td>	
		</tr>
		<tr>
			<td colspan="6" class="line_th" align="center" ><select name="searchName">
					<option value="rv_title">제목</option>
					<option value="nickname">작성자</option>
			</select> <input type="text" name="searchValue" size="40"> <input
				type="submit" value="검색" style="width: 50px"></td>
		</tr>
		<tr>
			<th class="line_th">골프장</th>
			<th class="line_th">평점</th>
			<th class="line_th">제목</th>
			<th class="line_th">작성자</th>
			<th class="line_th">작성일</th>
			<th class="line_th">조회수</th>
		</tr>
		<c:choose>
			<c:when test="${empty reviewList}">
				<tr>
					<td colspan="6" align="center"><h3 style="color: #665b5f">게시글이
							없습니다</h3></td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="dto" items="${reviewList}" varStatus="status">
					<tr>
						<td class="line_td">${dto.cc_name}</td>
						<td class="line_td">${dto.score}</td>
						<td class="line_td"><a
							href="reviewDetail?score_no=${dto.score_no}&user_no=${dto.user_no}">${dto.rv_title}</a></td>
						<td class="line_td">${dto.nickname}</td>
						<td class="line_td">${dto.score_date}</td>
						<td class="line_td">${dto.rv_vcount}</td>
					</tr>

				</c:forEach>

				<tr>
					<td align="center" colspan="6">
						<div class="list_number">
						<c:set var="curPage" value="${curPage+1}" /> <%-- 1 --%> 
						<c:set var="maxBlock" value="${maxBlock}" />
						<c:set var="minBlock" value="${minBlock+1}" /> 
						<p><div class="list_n_menu">					
						<c:if test="${curPage != 1}">
							<a href="reviewList?curPage=1">〈〈</a>
								<c:choose>
									<c:when test="${curPage>showBlock}">
										<a href="reviewList?curPage=${minBlock-1}">〈</a>
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
									<a href="reviewList?curPage=${i}">${i}</a>
								</c:when>
							</c:choose>
						</c:forEach>
						
						<c:if test="${curPage != totalPage}">
							<c:choose>
								<c:when test="${curPage<=showBlock*perBlock}">
									<a href="reviewList?curPage=${maxBlock+1}">〉</a>
								</c:when>
								<c:otherwise>
									<span class="disabled">〉</span>
								</c:otherwise>
							</c:choose>
							<a href="reviewList?curPage=${totalPage}">〉〉</a>
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
	<button id="insertReview">글쓰기</button>
</form>
