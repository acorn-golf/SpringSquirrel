<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" src="/teamSquirrel/jquery-3.4.1.js"></script>
<script type="text/javascript">
var likeChk = false;

$(document).ready(function() {
	
	//초기 세팅. (ajax 실행하여 찜한 상품인지 아닌지 표시)
	<c:if test="${login != null }">	
	$.ajax({ //로그인 초기설정
		type: "post",
		URL: "likeGolfGet",
		data:{
			cc_id:"${Golfcc.cc_id}",
			user_no:"${login.user_no}"},
		dataType: "json",
		success: function(data,textStatus,xhr) {
			likeChk = data.chk;
			if(likeChk)
				$("#likeAdd").attr("src","<c:url value="img/GOLFCC/sub/likeButton_on.png"/>");	
			else
				$("#likeAdd").attr("src","<c:url value="img/GOLFCC/sub/likeButton_off.png"/>");
				
		},
		error: function(xhr,textStatus,e) {
			alert(e);
		}
	}); // (로그인)초기 설정 종료
	
	//로그인시 버튼에 기능 부여 ( 함수 2개 사용. 외부에 저장.)
	
	$("#likeAdd").on("click", function(event) {
		$.ajax({
			type: "post",
			URL: "likeGolfSet",
			data:{
				cc_id:"${Golfcc.cc_id}",
				user_no:"${login.user_no}",
				like_chk: likeChk
			},
			dataType: "json",
			success: function() {
				likeChk = data.chk;
				if(likeChk)
					$("#likeAdd").attr("src","<c:url value="img/GOLFCC/sub/likeButton_on.png"/>");	
				else
					$("#likeAdd").attr("src","<c:url value="img/GOLFCC/sub/likeButton_off.png"/>");
			},
			error: function(xhr,textStatus,e) {
				alert("변경 실패");
			}
		}); //ajax설정 종료
	}); //끝끝끝
	
	</c:if> 

	<c:if test="${empty login}">
	$("#likeAdd").on("click", function(event) {
		alert("로그인이 필요한 서비스입니다.");
	location.href = "<c:url value="/"/>";
	}
	//비로그인 시 로그인 페이지로 이동.
	</c:if>
	

	
});//onload 이벤트 종료
</script>



	<table width="100%" cellspacing="0" cellpadding="0" border="0">
		<tr>
			<td height="30">
		</tr>
		<tr>
			<td>
				<table align="center" width="710" cellspacing="0" cellpadding="0"
					border="0" style='margin-left: 30px'>
					<tr>
						<td class="td_default"><font size="5"><b>- 골프장 보기
									-</b></font> &nbsp;</td>
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
							src="/teamSquirrel/GOLFCC/${Golfcc.loc_id}/${Golfcc.cc_img}"
							onerror="this.src='/teamSquirrel/GOLFCC/noimg.jpg'"
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
										<!-- 버튼 이미지 추가 --> 
										
										<img src="<c:url value="img/GOLFCC/sub/likeButton_off.png"/>" width="20" height="20" id="likeAdd">
										<font size="2px">관심골프장 추가</font>
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

						<td class="td_title">사이트</td>
						<td class="td_default" colspan="2" style='padding-left: 30px'><a href="http://${Golfcc.cc_url}">${Golfcc.cc_url}</a>
						</td>
					</tr>
					<tr>
						<td class="td_title">가격</td>

						<td class="td_red" colspan="2" style='padding-left: 30px'>
							${productDTO.p_price}원</td>
					</tr>
					<tr>
						<td class="td_title">연락처</td>
						<td><font color="#2e56a9" size="2" style='padding-left: 30px'><b>사번 :</b></font>
							<font size="2"><span id="sellerPhoneNumber">${Golfcc.cc_phone}</span>
						</font></td>

						
					</tr>
					<tr>
						<td colspan="2" style='padding-left: 30px'></td>
					</tr>

					<tr>
						<td class="td_title"> 수&nbsp;&nbsp;<br> <font
							color="red" size="2px">ㅈㅅㅈㅅ</font>
						</td>
						<td style="padding-left: 30px">상품 평점별 보여주기 목적</td>
					</tr>



				</table>
			</td>
		</tr>
	</table>