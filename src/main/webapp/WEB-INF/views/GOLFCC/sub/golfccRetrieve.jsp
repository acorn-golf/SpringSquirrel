<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" src="/teamSquirrel/jquery-3.4.1.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	
	
	
	
/* 	
	$("#likeAdd").on("click", function() { //ajax
		$.ajax({
			type : "post",
			url : "LikeGolfccAddServlet",
			data:{
				cc_id:"${Golfcc.cc_id}",
				LikeChk: //변수값넣기
			},
			dataType : "json", //응답 타입 
			success : function(data,textStatus,xhr) {
				if(data.mesg!=null)
					alert(data.mesg);
				$("#likeAdd").attr("src",data.img);
			},
			error : function(xhr,textStatus,e) {
				console.log("error:"+e);
			}
		}); //ajax 종료
		
	}); //likeAdd 버튼이벤트 종료
	 */
	
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
										<img src="/teamSquirrel/GOLFCC/sub/likeButton_off.png" width="20" height="20" id="likeAdd">
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