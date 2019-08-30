<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" src="/teamSquirrel/jquery-3.4.1.js"></script>
<script>
	$(document).ready(function() {
		$("#insertReview").on("click", function() {
			$("form").attr({
				"action" : "InsertReviewFormServlet",
				"method" : "post"
			});
		});
		/* $(".radio").on("click",function(){
			$(".radio").each(function(idx,ele){
				//location.href="ReviewListServlet?orderby="+$(ele).val();
				var ${orderby} = String.valueOf(${orderby});
				if($(ele).val()==${orderby}){
					console.log("hi");
				}
			});
		}); */
	});
</script>

<form name="ReviewListForm" action="ReviewListServlet" method="post">
	<input type="hidden" name="cc_id" value="${cc_id}">
	<!-- 후기글쓰기 할 때 갖고갈 파라미터 -->

	<input type="radio" name="orderby" value="score_date" class="radio">최신순&nbsp;&nbsp;
	<input type="radio" name="orderby" value="score" class="radio">평점순<br>
	<table class="line_table">
		<tr>
			<td colspan="5" class="line_th"><select name="searchName">
					<option value="rv_title">제목</option>
					<option value="nickname">작성자</option>
			</select> <input type="text" name="searchValue" size="40"> <input
				type="submit" value="검색"></td>
		</tr>
		<tr>
			<th class="line_th">골프장</th>
			<th class="line_th">평점</th>
			<th class="line_th">제목</th>
			<th class="line_th">작성자</th>
			<th class="line_th">작성일</th>
		</tr>
		<c:choose>
			<c:when test="${empty reviewList}">
				<tr>
					<td colspan="5" align="center"><h3 style="color: #665b5f">게시글이
							없습니다</h3></td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="dto" items="${reviewList}" varStatus="status">
					<tr>
						<td class="line_td">${dto.cc_name}</td>
						<td class="line_td">${dto.score}</td>
						<td class="line_td"><a
							href="ReviewDetailServlet?score_no=${dto.score_no}&user_no=${dto.user_no}">${dto.rv_title}</a></td>
						<td class="line_td">${dto.nickname}</td>
						<td class="line_td">${dto.score_date}</td>
					</tr>

				</c:forEach>

				<tr>
					<td align="center" colspan="5">
					<c:set var="curPage" value="${curPage+1}" /> <%-- 1 --%> 
					<c:set var="maxBlock" value="${maxBlock}" /> 
					<c:set var="minBlock" value="${minBlock+1}" /> 
					<c:if test="${curPage != 1}">
							<a href="ReviewListServlet?curPage=1">◀◀</a>&nbsp;&nbsp;
							<c:if test="${curPage>showBlock}">
								<a href="ReviewListServlet?curPage=${minBlock-1}">◁</a>&nbsp;&nbsp;
							</c:if>
					</c:if> &nbsp;&nbsp; 
					<c:forEach var="i" begin="${minBlock}" end="${maxBlock}" step="1">
							<c:choose>
								<c:when test="${curPage eq i}">
									<span style="color: red">${i}</span>
								</c:when>
								<c:when test="${curPage != i}">
									<a href="ReviewListServlet?curPage=${i}">${i}</a>&nbsp;
								</c:when>
							</c:choose>
					</c:forEach>&nbsp;
					<c:set var="i" value="${Math.floor(totalPage/showBlock)}" />
						<c:if test="${curPage != totalPage}">
							<c:if test="${curPage<=showBlock*perBlock}">
								<a href="ReviewListServlet?curPage=${maxBlock+1}">▷</a>
							</c:if>
							<a href="ReviewListServlet?curPage=${totalPage}">▶▶</a>
						</c:if>
					</td>
				</tr>

			</c:otherwise>
		</c:choose>
	</table>
	<button id="insertReview">글쓰기</button>
</form>
