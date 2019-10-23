<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script src="https://cdn.bootpay.co.kr/js/bootpay-3.0.6.min.js" type="application/javascript"></script>
<script>
	$(document).ready(function(){
		
		$("#cancle").on("click",function(event){
			event.preventDefault();
			history.back();
		});
		$("#amount").on("change",function(){
			$("#order_price").text(Number.parseInt($("#amount").val() * $("#price").text()));
		});
		
		$("#order").on("click",function(){
			//실제 복사하여 사용시에는 모든 주석을 지운 후 사용하세요
			BootPay.request({
				price: Number($("#order_price").text())*10000, //실제 결제되는 가격
				application_id: "5dafee3f5ade160030569abe",
				name: '${dto.cc_name}', //결제창에서 보여질 이름
				pg: 'kakao',
				method: 'easy', //결제수단, 입력하지 않으면 결제수단 선택부터 화면이 시작합니다.
				show_agree_window: 0, // 부트페이 정보 동의 창 보이기 여부
				items: [
					{
						item_name: '${dto.cc_name}', //상품명
						qty: Number($("#amount").val()), //수량
						unique: '${pick_no}', //해당 상품을 구분짓는 primary key
						price: Number('${dto.p_price}'), //상품 단가
						cat1: 'TOP', // 대표 상품의 카테고리 상, 50글자 이내
						cat2: '티셔츠', // 대표 상품의 카테고리 중, 50글자 이내
						cat3: '라운드 티', // 대표상품의 카테고리 하, 50글자 이내
					}
				],
				user_info: {
					username: '사용자 이름',
					email: '사용자 이메일',
					addr: '사용자 주소',
					phone: '010-1234-4567'
				},
				order_id: '${pick_no}', //고유 주문번호로, 생성하신 값을 보내주셔야 합니다.
				params: {callback1: '그대로 콜백받을 변수 1', callback2: '그대로 콜백받을 변수 2', customvar1234: '변수명도 마음대로'},
				account_expire_at: '2018-05-25', // 가상계좌 입금기간 제한 ( yyyy-mm-dd 포멧으로 입력해주세요. 가상계좌만 적용됩니다. )
				extra: {
				    start_at: '2019-05-10', // 정기 결제 시작일 - 시작일을 지정하지 않으면 그 날 당일로부터 결제가 가능한 Billing key 지급
					end_at: '2022-05-10', // 정기결제 만료일 -  기간 없음 - 무제한
			        vbank_result: 1, // 가상계좌 사용시 사용, 가상계좌 결과창을 볼지(1), 말지(0), 미설정시 봄(1)
			        quota: '0,2,3' // 결제금액이 5만원 이상시 할부개월 허용범위를 설정할 수 있음, [0(일시불), 2개월, 3개월] 허용, 미설정시 12개월까지 허용
				}
			}).error(function (data) {
				//결제 진행시 에러가 발생하면 수행됩니다.
				console.log(data);
			}).cancel(function (data) {
				//결제가 취소되면 수행됩니다.
				console.log(data);
			}).ready(function (data) {
				// 가상계좌 입금 계좌번호가 발급되면 호출되는 함수입니다.
				console.log(data);
			}).confirm(function (data) {
				//결제가 실행되기 전에 수행되며, 주로 재고를 확인하는 로직이 들어갑니다.
				//주의 - 카드 수기결제일 경우 이 부분이 실행되지 않습니다.
				console.log(">>>>>>>>>>>>>>>>"+data);
				console.dir(data);
				console.log(">>>>>>>>>>>>>>>>");
				
				
				var enable = true; // 재고 수량 관리 로직 혹은 다른 처리
				if (enable) {
					BootPay.transactionConfirm(data); // 조건이 맞으면 승인 처리를 한다.
				} else {
					BootPay.removePaymentWindow(); // 조건이 맞지 않으면 결제 창을 닫고 결제를 승인하지 않는다.
				}
			}).close(function (data) {
			    // 결제창이 닫힐때 수행됩니다. (성공,실패,취소에 상관없이 모두 수행됨)
			    console.log(data);
			}).done(function (data) {
				console.dir(data)
				//결제가 정상적으로 완료되면 수행됩니다
				//비즈니스 로직을 수행하기 전에 결제 유효성 검증을 하시길 추천합니다.
				var token = "";
				$.ajax({
					type : "get",
					url : "getToken",
					/* data : {
						restApplicationID : "5dafee3f5ade160030569ac1",
						key : "IglrTcbxJHo3N6b+7FsWZaaeL1W7r9dwpE5uExZ0cjw=",
					}, */
					dataType : "text",
					async: false,
					success : function(tdata, status, xhr) {
						token = tdata;
					},
					error : function(xhr, status, error) {
						console.log(error);
						console.log(status);
					}
				});
				//alert("#########"+token);
				
				
				$.ajax({
					type : "get",
					url : "https://api.bootpay.co.kr/receipt/"+data.receipt_id,
					headers : {"Authorization" : token},
					//dataType : "json",
					success : function(data, status, xhr) {
						//alert(data);
						console.dir(data);
						
						$.ajax({
							type : "get",
							url : "addOrder",
							data : {
								pick_no : data.data.order_id,
								p_id : "${dto.p_id}",
								o_amount : $("#amount").val(),
								o_price : data.data.payment_data.p / 10000,
								receipt_id : data.data.receipt_id
							},
							dataType : "text",
							success : function(data, status, xhr) {
								alert(data);
								location.href="http://localhost:8090/golfhi/orderList"
							},
							error : function(xhr, status, error) {
								console.log(error);
								console.log(status);
							}
						});
					},
					
					
					error : function(xhr, status, error) {
						console.log("?>?>?>?>?>"+xhr);
						console.log("?>?>?>?>?>"+status);
						console.log("?>?>?>?>?>"+error);
					}
				});
				
				
				
			});
		});
	});
</script>

<form action="addOrder" method="get" class="form_main" name="addOrder">
<h3>주문 상품 확인</h3>
<input type="hidden" name="p_id" value="${dto.p_id}">
<c:if test="${pick_no != null}">
	<input type="hidden" name="pick_no" value="${pick_no}">
</c:if>

<input type="hidden" name="o_price" value="${amount * dto.p_price}">

<table class="line_table">
	<tr>
		<th colspan="2" class="line_th">골프장</th>
		<th class="line_th">티업시간</th>
		<th class="line_th">그린피</th>
		<th class="line_th">총 결제금액</th>
	</tr>
	<tr>
		<td width="120" class="line_td"><img src="img/GOLFCC/${dto.loc_id}/${dto.cc_img}"
							onerror="this.src='/golfhi/GOLFCC/noimg.jpg'" border="0" align="middle" width="120" height="80" />
		</td>
		<td class="line_td"> 
		<table>
			<tr>
				<td><b>${dto.cc_name}</b></td>
				<td>${dto.p_maxpeople}명  ${dto.p_hole}홀&nbsp;&nbsp;<span style="color:blue">예약인원 : <input type="number" id="amount" min="1" max="${dto.p_maxpeople}" name="o_amount" value="${amount}"> 명</span></td>
			</tr>
			<tr>
				<td><font size="2" color="#4374D9">${dto.p_uploaddate}</font></td>
				<td><font size="2" color="#665b5f">캐디유무 : ${dto.p_caddyyn} 식사유무 : ${dto.p_babyn} 카트유무 : ${dto.p_cartyn}</font></td>
			</tr>
		</table>
		</td>
		<td class="line_td"><b>${dto.p_pdate}</b><br>${dto.nickname} ☎${dto.phone_id}</td>
		<td align="center" class="line_td"><span id="price">${dto.p_price}</span> 만원<br>
		<c:if test="${dto.emergency eq '긴급'}">
		<b style="color:red">[${dto.emergency}]</b></c:if></td>
		<td class="line_td" align="center"><b style="color:blue"><span id="order_price">${amount * dto.p_price}</span> 만원</b></td>
	</tr>
	<tr>
		<td align="right" colspan="5">
			<button id="cancle">취소</button> <input type="button" style="width: 50px" value="구매" id="order"><!-- <button>구매하기</button> --><!-- <input type="submit" value="구매하기"> -->
		</td>
	</tr>
</table>

</form>
