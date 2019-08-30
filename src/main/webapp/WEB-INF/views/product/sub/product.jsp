<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#loc").on("change", function() {
			console.log($("#loc").val());
			if ($("#loc").val() != "지역선택") {
				$.ajax({
					type : "get",
					url : "productSelectGolfCC",
					data : {
						loc_ID : $("#loc").val()
					},
					dataType : "text",
					success : function(data, status, xhr) {
						console.log(">>" + data.trim());
						$("#defaultgolf").html(data);
					},
					error : function(xhr, status, error) {
						console.log(error);
						console.log(status);
					}
				});
			} else {
				$("#defaultgolf").html("<option>골프장선택</option>");
			}
		});
		$("#gomain").on("click", function() {
			location.href = "/golfhi/";
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
		
		var date = new Date();
		console.dir(date);
		console.log(date.format('yyyy-MM-dd')+"T"+date.format('HH:mm:ss'));
		console.log(Number.parseInt(date.format('HH'))+12);
		var day = date.format('yyyy-MM-dd')+"T23:59";
		console.log(Number.parseInt(day)<10);
		$("#p_pdate").attr("min",day);
		$("#p_pdate").attr("max",(Number.parseInt(date.format('yyyy'))+1)+"-12-31T23:59");
		
	});
</script>


<form action="productInsert" method="get" name="myForm" class="form_main">
	<!-- 각각의 유효성 체크 해야한다 -->
	<table class="line_table">
		<tr>
			<th class="line_th">지역</th>
			<td class="line_td"><select id="loc">
					<option selected="selected">지역선택</option>
					<c:forEach var="loc" items="${LocationList}" varStatus="status">
						<option value="${loc.loc_id }">${loc.loc_name }</option>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<th class="line_th">골프장</th>
			<td class="line_td"><select name="cc_id" id="defaultgolf">
					<!-- 지역을 선택할 때 마다 골프장select박스 내용이나온다, ajax : SelectGolfCCServlet CC_NAME을 받아 CC_ID추출  -->
					<option>골프장선택</option>
			</select></td>
		</tr>
		<tr>
			<th class="line_th">티업일자</th>
			<td class="line_td"><input type="datetime-local" id="p_pdate" name="p_pdate"  max="2020-01-01T00:00" pattern="" required="required"></td>
		</tr>
		<!-- <tr>
			<th>티업시간</th>
			<td><input type="time" name="time" pattern=""></td>
			jQuery로 date + time 해서 hidden태그로 productDTO의 p_date에다가 넣자
			P_UPLOADDATE는 default가 sysdate
		</tr> -->
		<tr>
			<th class="line_th">그린피</th>
			<td class="line_td"><input type="number" min="1" name="p_price" style="width:50px" required="required">만원</td>
		</tr>
		<tr>
			<td class="line_td" align="center">식사<input type="checkbox" name="p_babyn"></td>
			<td class="line_td" align="center">카트비 포함<input type="checkbox" name="p_cartyn"></td>
		</tr>
		<tr>
			<th class="line_th">홀 선택</th>
			<td class="line_td"><input type="radio" name="p_hole" value="18"
				checked="checked">18홀&nbsp;&nbsp; <input type="radio"
				name="p_hole" value="27">27홀&nbsp;&nbsp; <input type="radio"
				name="p_hole" value="36">36홀</td>
		</tr>
		<tr>
			<th class="line_th">캐디</th>
			<td class="line_td"><input type="radio" name="p_caddyyn" value="Y"
				checked="checked">캐디&nbsp;&nbsp; <input type="radio"
				name="p_caddyyn" value="N">노캐디</td>
		</tr>
		<tr>
			<th class="line_th">인원</th>
			<td class="line_td"><input type="text" style="width:30px" name="p_maxpeople" readonly="readonly"
				value="4">명</td>
		</tr>
		<tr>
			<th class="line_th">상품설명</th>
			<td class="line_td"><textarea role="20" cols="50" name="p_content"></textarea></td>
		</tr>
	</table>

	<br> <input type="button" value="메인으로" id="gomain">&nbsp;
	<input type="submit" value="등록">
	<!-- 이전버튼 누를 시 전화면으로 돌아간다->자바스크립트로 찝어서 해당갈 곳으로 src 해주면 될듯 -->
	<!-- USER_NO는 form에서 보낼 때 해당 서블릿에서 세션으로 받을것 -->
	<!-- p_id - 시퀀스자동넘버링
P_PDATE - 서블릿에서 date와 time을 합친다
P_UPLOADDATE - default : sysdate
USER_NO - ProductInsertServlet서블릿에서 세션으로 검색
CC_ID - Ajax로 지역이 change -> 그럼 골프장명이 나오면서 option의 value가 cc_id  해서 등록버튼 누르면 자동으로 넘어감
P_VCOUNT(조회수) - 시퀀스자동넘버링 -->
</form>