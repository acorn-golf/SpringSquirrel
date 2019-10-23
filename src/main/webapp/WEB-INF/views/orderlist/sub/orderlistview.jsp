<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(".cancle").on("click",function(event){
			
			var o_no = $(this).attr("data-o_no");
			$("#o_no"+o_no).attr("checked","checked");
			console.log(o_no);
			console.log($("#o_no"+o_no).val());
			console.log($("#receipt_id"+o_no).val());
			var r_id = $("#receipt_id"+o_no).val();
			var cancleConfirm = confirm("상품 예약을 취소하시겠습니까?");
			if (cancleConfirm == true) {
				$("form[name='myForm']").attr({"action":"orderCancle","method":"post"});
				var token = "";
				$.ajax({
					type : "get",
					url : "getToken",
					dataType : "text",
					async: false,
					success : function(data, status, xhr) {
						console.dir(data);
						token = data;
					},
					error : function(xhr, status, error) {
						console.log(error);
						console.log(status);
					}
				});
				
				$.ajax({
					type : "post",
					url : "cancle",
					headers : {"Authorization" : token},
					data : {
						gettoken : token,
						name : "aa",
						reson : "사용자 구매취소",
						receipt_id : r_id
					},
					//dataType : "json",
					success : function(data, status, xhr) {
						
					},
					error : function(xhr, status, error) {
						console.log("?>?>?>?>?>"+xhr);
						console.log("?>?>?>?>?>"+status);
						console.log("?>?>?>?>?>"+error);
					}
				});
			} else if (cancleConfirm == false) {
				event.preventDefault();
				$("#o_no"+o_no).prop("checked",false);
			}
			
		});
	});
</script>


<form action="orderList" method="post" class="form_main" name="myForm">
<h3>예약 정보</h3>
<table class="line_table">
	<tr>
		<th class="line_th">상품명</th>
		<th class="line_th">판매가</th>
		<th class="line_th">수량</th>
		<th class="line_th">총 구매 가격</th>
		<th class="line_th">구매일</th>
		<th class="line_th">담당 매니저</th>
		<th class="line_th"></th>
	</tr>
	<c:choose>
		<c:when test="${empty orderList}">
			<td colspan="7" align="center" class="line_td"><h3 style="color: #665b5f">예약내역이 없습니다</h3></td>
		</c:when>
		<c:otherwise>
			<c:forEach var="oList" items="${orderList}">
	<tr>
		<td class="line_td" align="center">${oList.cc_name}</td>
		<td class="line_td" align="center">${oList.p_price} 만원</td>
		<td class="line_td" align="center">${oList.o_amount} 명</td>
		<td class="line_td" align="center">${oList.o_price} 만원</td>
		<td class="line_td" align="center">${oList.o_date}</td>
		<td class="line_td" align="center">${oList.nickname} <font style="color:blue" size="3">☎ ${oList.phone_id}</font></td>
		<td class="orderVal" style="border-bottom: 1px solid #444444">
			<%-- <input type="button" class="cancle" data-o_no="${oList.o_no}" value="예약취소"> --%>
			<button class="cancle" data-o_no="${oList.o_no}">예약취소</button>
			<input type="hidden" name="o_amount${oList.o_no}" value="${oList.o_amount}">
			<input type="hidden" name="p_id${oList.o_no}" value="${oList.p_id}">
			<input type="checkbox" hidden="true" id="o_no${oList.o_no}" name="o_no" value="${oList.o_no}">
			<input type="hidden" id="receipt_id${oList.o_no}" name="receipt_id" value="${oList.receipt_id}">
		</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="6" align="center">
			<div class="list_number">
				<c:set var="curPage" value="${curPage+1}" /> <%-- 1 --%> 
				<c:set var="maxBlock" value="${maxBlock}" />
				<c:set var="minBlock" value="${minBlock+1}" /> 
				<p><div class="list_n_menu">					
				<c:if test="${curPage != 1}">
					<a href="orderList?curPage=1">〈〈</a>
						<c:choose>
							<c:when test="${curPage>showBlock}">
								<a href="orderList?curPage=${minBlock-1}">〈</a>
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
							<a href="orderList?curPage=${i}">${i}</a>
						</c:when>
					</c:choose>
				</c:forEach>
				
				<c:if test="${curPage != totalPage}">
					<c:choose>
						<c:when test="${curPage<=showBlock*perBlock}">
							<a href="orderList?curPage=${maxBlock+1}">〉</a>
						</c:when>
						<c:otherwise>
							<span class="disabled">〉</span>
						</c:otherwise>
					</c:choose>
					<a href="orderList?curPage=${totalPage}">〉〉</a>
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