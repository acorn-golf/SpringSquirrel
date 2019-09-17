<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	
	$(document).ready(function() {
		console.log(new Date('${dto.p_pdate}'));
		if('${dto.p_babyn}' == 'Y'){
			$("input[name='p_babyn']").prop("checked",true);
		}else{
			$("input[name='p_babyn']").prop("checked",false);
		}
		if('${dto.p_cartyn}' == 'Y'){
			$("input[name='p_cartyn']").prop("checked",true);
		}else{
			$("input[name='p_cartyn']").prop("checked",false);
		}
		if('${dto.p_caddyyn}' == 'Y'){
			$("input[name='p_caddyyn']").prop("checked",true);
		}else{
			$("input[name='p_caddyyn']").prop("checked",false);
		}
		
		$("input[name='p_hole']").each(function(idx,ele){
			if('${dto.p_hole}' == $(ele).val()){
				$(ele).prop("checked",true);
			}
		});
		
		
				
		Date.prototype.format = function (f) {
		    if (!this.valueOf()) return " ";
		    var weekKorName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
		    var weekKorShortName = ["일", "월", "화", "수", "목", "금", "토"];
		    var weekEngName = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
		    var weekEngShortName = ["Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"];
		    var d = this;
		    return f.replace(/(yyyy|yy|MM|dd|KS|KL|ES|EL|HH|hh|mm|ss|a\/p)/gi, function ($1) {
		        switch ($1) {
		            case "yyyy": return d.getFullYear(); // 년 (4자리)
		            case "yy": return (d.getFullYear() % 1000).zf(2); // 년 (2자리)
		            case "MM": return (d.getMonth() + 1).zf(2); // 월 (2자리)
		            case "dd": return d.getDate().zf(2); // 일 (2자리)
		            case "KS": return weekKorShortName[d.getDay()]; // 요일 (짧은 한글)
		            case "KL": return weekKorName[d.getDay()]; // 요일 (긴 한글)
		            case "ES": return weekEngShortName[d.getDay()]; // 요일 (짧은 영어)
		            case "EL": return weekEngName[d.getDay()]; // 요일 (긴 영어)
		            case "HH": return d.getHours().zf(2); // 시간 (24시간 기준, 2자리)
		            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2); // 시간 (12시간 기준, 2자리)
		            case "mm": return d.getMinutes().zf(2); // 분 (2자리)
		            case "ss": return d.getSeconds().zf(2); // 초 (2자리)
		            case "a/p": return d.getHours() < 12 ? "오전" : "오후"; // 오전/오후 구분
		            default: return $1;
		        }
		    });
		};

		String.prototype.string = function (len) { var s = '', i = 0; while (i++ < len) { s += this; } return s; };
		String.prototype.zf = function (len) { return "0".string(len - this.length) + this; };
		Number.prototype.zf = function (len) { return this.toString().zf(len); };
		
		function parse(str){
			var day = new Date(str)
			day.format('yyyy-MM-dd')+"T23:59";
			//https://hianna.tistory.com/319
			return day;
		}
		
		var date = new Date();
		console.dir(date);
		console.log(date.format('yyyy-MM-dd')+"T"+date.format('HH:mm:ss'));
		console.log(Number.parseInt(date.format('HH'))+12);
		var day = date.format('yyyy-MM-dd')+"T23:59";
		console.log(Number.parseInt(day)<10);
		$("#p_pdate").attr("value",parse('${dto.p_pdate}').toISOString().slice(0, 19));
		$("#p_pdate").attr("min",day);
		$("#p_pdate").attr("max",(Number.parseInt(date.format('yyyy'))+1)+"-12-31T23:59");
		
	});
</script>

<FORM name="productForm" method="GET" action="#">
<input type="hidden" name="p_id" value="${dto.p_id}">

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
					
						<td rowspan="6"><img
							src="<c:url value="img/GOLFCC/${dto.loc_id}/${dto.cc_img}"/>"
							onerror="this.src='<c:url value="img/GOLFCC/noimg.jpg"/>'"
							border="0" align="center" width="300" /> <br>

						<td class="td_title" style="width: 80px">골프장 명</td>
						<td class="td_default" colspan="2" style='padding-left: 30px'>${dto.cc_name}</td>

					</tr>
					<tr>

						<td class="td_title">티업 시간</td>
						<td class="td_default" colspan="2" style='padding-left: 30px'>
						<input type="datetime-local" name="p_pdate" id="p_pdate">
						${dto.p_pdate}
						</td>
					</tr>
					<tr>
						<td class="td_title">가격</td>

						<td class="td_red" colspan="2" style='padding-left: 30px'>
							<input type="number" name="p_price" value="${dto.p_price}" min="1" style="width: 40px"> 만원</td>
					</tr>
					
					<tr>
						<td class="td_title" rowspan="2">상품옵션</td>
						<td colspan="2" style='padding-left: 30px'>
							<input type="checkbox" name="p_babyn" style="width: 20px"><span>식사제공</span>&nbsp;
							<input type="checkbox" name="p_cartyn" style="width: 20px"><span>카트제공</span>&nbsp;
							<input type="checkbox" name="p_caddyyn" style="width: 20px"><span>캐디제공</span>&nbsp;
							<input type="radio" name="p_hole" value="18"><span>18 홀</span>&nbsp;
							<input type="radio" name="p_hole" value="27"><span>27 홀</span>&nbsp;
							<input type="radio" name="p_hole" value="36"><span>36 홀</span><br>
						</td>
					</tr>
					<tr>
						<td colspan="2" style='padding-left: 30px'></td>
					</tr>

					<tr>
						<td class="td_title">인원 수&nbsp;&nbsp;
						</td>
						<td style="padding-left: 30px">
							${dto.p_maxpeople} 명
						</td>
					</tr>
					<tr>
						<td colspan="3">
							<hr>
							<font size="5"><b>- 상품 설명 -</b></font>
							<br>
							<br>
							<div>
								<textarea rows="1" cols="10">${dto.p_content}</textarea> 
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