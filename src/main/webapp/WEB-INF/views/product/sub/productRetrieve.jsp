<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" src="/teamSquirrel/jquery-3.4.1.js"></script>
<script>
	function amountUpDown(num) {
		var tmp = document.getElementById("g_amount");
		tmp.value = parseInt(num) + parseInt(tmp.value);
		if (parseInt(tmp.value) < 1)
			tmp.value = 1;
		if (parseInt(tmp.value) > ${productDTO.p_maxpeople})
			tmp.value =  ${productDTO.p_maxpeople};
	}

	function reqCheck(action, myform,e) {

		if (validityF()) {
			if (action == 'order') {
				myform.action = "addOrder";
			} else if (action == 'cart') {
				myform.action = "insertPickList";
			}
			alert("예약하였습니다.");
		} else {
			e.preventDefault();
			alert("다시 확인해 주세요");
		}
	}
	function validityF() {
		var numberchk = /[0-9]{1}/;
		
		var validityChk = false;
		validityChk = numberchk.test(document.getElementById("g_amount").value);

		return validityChk;
	}

	$(document).ready(
			function() {
				var tmpStr = $("#sellerPhoneNumber").text();
				$("#sellerPhoneNumber").text(
						tmpStr.replace(
								/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/,
								"$1-$2-$3"));

			});
</script>

<FORM name="productForm" method="GET" action="#">
<input type="hidden" name="p_id" value="${productDTO.p_id}">
<input type="hidden" name="p_price" value="${productDTO.p_price}">

	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td height="30">
		</tr>
		<tr>
			<td>
				<table align="center" width="710" cellspacing="0" cellpadding="0"
					border="0" style='margin-left: 30px'>
					<tr>
						<td class="td_default"><font size="5"><b>- 상품 보기 -</b></font>
							&nbsp;</td>
					</tr>
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
					
						<td rowspan="7"><img
							src="<c:url value="img/GOLFCC/${Golfcc.loc_id}/${Golfcc.cc_img}"/>"
							onerror="this.src='<c:url value="img/GOLFCC/noimg.jpg"/>'"
							border="0" align="center" width="300" /> <br>

							<table width="300">
								<tr>
									<td colspan="6" style="text-align: center;"><font
										color="#2e56a9" size="2">주소 : ${Golfcc.cc_addr1}</font></td>
								</tr>
								<td colspan="2"></td>
								<th colspan="2">골프장 평점</th>
								<td colspan="2" style="font-size: 1px; text-align: right;">${Golfcc.count}명이
									평가</td>
								<tr>
									<td colspan="3">
										<!-- 버튼 이미지 추가 --> <font size="1px">관심골프장 추가</font>
									</td>
									<td colspan="3" style="text-align: right; font-size: 10;">
										<c:forEach begin="0" end="4" step="1" var="index">
											<c:choose>
												<c:when test="${Golfcc.score - index eq 0.5}">
							◐
							</c:when>
												<c:when test="${Golfcc.score-index > 0}">
							★
							</c:when>
												<c:otherwise>
							☆
							</c:otherwise>
											</c:choose>
										</c:forEach>
									</td>
								</tr>


							</table></td>
						<td class="td_title">골프장 명</td>
						<td class="td_default" colspan="2" style='padding-left: 30px'>${Golfcc.cc_name}</td>

					</tr>
					<tr>

						<td class="td_title">티업 시간</td>
						<td class="td_default" colspan="2" style='padding-left: 30px'>${productDTO.p_pdate}
						</td>
					</tr>
					<tr>
						<td class="td_title">가격</td>

						<td class="td_red" colspan="2" style='padding-left: 30px'>
							${productDTO.p_price}원</td>
					</tr>
					<tr>
						<td class="td_title">판매자 정보</td>
						<td><font color="#2e56a9" size="2" style='padding-left: 30px'><b>${Seller.nickname}</b>
								<font size="2"> 전화번호 : <span id="sellerPhoneNumber">${Seller.phone_id}</span>
							</font></td>
						</font>
						</td>
					</tr>
					<tr>
						<td class="td_title" rowspan="2">상품옵션</td>
						<td colspan="2" style='padding-left: 30px'><c:if
								test="${productDTO.p_babyn eq 'Y'}">
								<span>식사제공</span>&nbsp;&nbsp;</c:if> <c:if
								test="${productDTO.p_cartyn eq 'Y'}">카트제공&nbsp;&nbsp; </c:if> <c:if
								test="${productDTO.p_caddyyn eq 'Y'}">캐디있음&nbsp;&nbsp; </c:if></td>
					</tr>
					<tr>
						<td colspan="2" style='padding-left: 30px'></td>
					</tr>

					<tr>
						<td class="td_title">인원 수&nbsp;&nbsp;<br>
						<font color="red" size="2px">최대 ${productDTO.p_maxpeople} 명</font>
						</td>
						<td style="padding-left: 30px"><input type="text"
							name="g_amount" value="1" id="g_amount"
							style="text-align: right; height: 18px"> <img
							src="<c:url value = "img/button/up.PNG" />" id="up"
							onclick="amountUpDown('1')"> <img
							src="<c:url value = "img/button/down.PNG" />" id="down"
							onclick="amountUpDown('-1')"></td>
					</tr>
					<tr>
						<td colspan="3">
							<hr>
							<font size="5"><b>- 상품 설명 -</b></font>
							<br>
							<br>
							<div>
								${productDTO.p_content}
							</div>
						</td>

					</tr>


				</table>
			</td>
		</tr>
	</table>
	<br>
	<hr>

	<br>
	<button onclick="reqCheck('order',productForm,event)">구매</button>
	&nbsp;&nbsp;
	<button onclick="reqCheck('cart',productForm,event)">장바구니</button>
</FORM>