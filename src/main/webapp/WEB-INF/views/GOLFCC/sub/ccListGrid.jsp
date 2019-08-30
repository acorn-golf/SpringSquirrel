<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript" src="/teamSquirrel/jquery-3.4.1.js"></script>
<script type="text/javascript">

$(document).ready(function() {
	$(".ccGridOne").on("click",function(event){
		console.log($(this).attr("cc_Id"));
		var tmphref = location.href.substr(0, location.href.indexOf('?'));
		location.href = tmphref+"?cc_id="+$(this).attr("cc_Id");
		
		

		
	});
	

});


</script>

<table width="100%" cellspacing="0" cellpadding="0">

	<tr>
		<td>
			<table align="center" width="710" cellspacing="0" cellpadding="0"
				border="0">

				<tr>
					<td height="5"></td>
				</tr>
				<tr>
					<td height="1" colspan="8" bgcolor="CECECE"></td>
				</tr>
				<tr>
					<td height="10"></td>
				</tr>

				<tr>
					<c:set var="CcGolfScoreList" value="${CcGolfScoreListPage.list}" />
					<c:forEach items="${CcGolfScoreList}" var="Golfcc"
						varStatus="status">
						<td>
							<table style='padding: 15px' class="ccGridOne" cc_Id="${Golfcc.cc_id}">
								<tr>
									<td>
											<c:if test="${Golfcc.cc_img != null}">
												<img
													src="/teamSquirrel/GOLFCC/${Golfcc.loc_id}/${Golfcc.cc_img}"
													onerror="this.src='/teamSquirrel/GOLFCC/noimg.jpg'"
													border="0" align="center" width="200">
											</c:if> <c:if test="${empty Golfcc.cc_img}">
												<img src="/teamSquirrel/GOLFCC/noimg.jpg" border="0"
													align="center" width="200">
											</c:if>
									</td>
								</tr>
								<tr>

									<td height="10">
								</tr>
								<tr>
									<td class="td_default" align="center"><a class="a_black"
										href="">${Golfcc.cc_name}<br>
									</a> <font color="gray"> -------------------- </font></td>

								</tr>
								<tr>
									<td height="10">
								</tr>
								<tr>
									<td class="td_gray" align="center">${Golfcc.score}점</td>
								</tr>
								<tr>
									<td height="10">
								</tr>
								<tr>
									<td class="td_red" align="center"><font color="red"><strong>
												${Golfcc.cc_addr1} </strong></font></td>
								</tr>
							</table>
						</td>
						<c:if test="${status.count %4==0}">
							<tr>
								<td height="10">
							</tr>
						</c:if>

					</c:forEach>
					<!-- 반복끝-->
				</tr>
				<tr>

					<c:if test="${CCminBlock != 0}">
						<a href="ReviewListServlet?GolfCCcurPage=0"> ◀◀ </a>
						<a href="ReviewListServlet?GolfCCcurPage<fmt:formatNumber value="${minpage -10}" type="number"
							maxFractionDigits="0" />"> ◀ </a>
					</c:if>


					<c:forEach var="i" begin="${CCminBlock}" end="${CCmaxBlock-1}" step="1">
						<c:choose>
							<c:when test="${CcGolfScoreListPage.curPage eq i}">
								<a> ${i+1} </a>
							</c:when>
							<c:otherwise>
								<a href="ReviewListServlet?GolfCCcurPage=${i}"> ${i+1} </a>
							</c:otherwise>
						</c:choose>
					</c:forEach>

					<c:if
						test="${CcGolfScoreListPage.totalRecord/CcGolfScoreListPage.perPage>CCmaxBlock}">
						<a
							href="ReviewListServlet?GolfCCcurPage=${CCmaxBlock}"> ▶ </a>
						<c:set var="realmaxpage"
							value="${CcGolfScoreListPage.totalRecord/CcGolfScoreListPage.perPage}" />
						
						<a
							href="ReviewListServlet?GolfCCcurPage=<fmt:formatNumber value="${realmaxpage}" type="number"
							maxFractionDigits="0" />">
							▶▶ </a>
					</c:if>

				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td height="10">
	</tr>
</table>