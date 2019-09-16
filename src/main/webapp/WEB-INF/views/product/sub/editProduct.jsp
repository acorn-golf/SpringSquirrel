<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		// 전체선택
		$("#chkall").on("click",function(){
			$(".check").each(function(idx,ele){
				$(ele).prop("checked",$("#chkall").prop("checked"));
			});
		});
		
		// 하나라도 체크 풀면 전체선택 체크풀림
		$(".check").on("click",function(){
			if($(".check:checked").length == ${productList.size()}){
				$("#chkall").prop("checked",true);
			}else{
				$("#chkall").prop("checked",false);
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
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<h3 style="margin-left:200px;margin-top: 15px">상품 관리</h3>
	<table class="line_table" style="margin-left:200px">
		<tr>
			<td><input type="checkbox" style="width: 30px" id="chkall">전체선택</td>
			<th colspan="2" style="border-bottom: 1px solid #444444;width: 300px">골프장</th>
			<th style="border-bottom: 1px solid #444444">티업시간</th>
			<th style="border-bottom: 1px solid #444444">그린피</th>
		</tr>
		<c:choose>
			<c:when test="${empty productList}">
				<tr>
					<td colspan="5" align="center" style="border-bottom: 1px solid #444444"><h3 style="color:#665b5f">상품이 없습니다</h3></td>
				
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach var="pList" items="${productList}">
				<input type="hidden" name="p_id" value="${pList.p_id}">
					<tr>
						<td><input type="checkbox" name="check" class="check" value="${pList.p_id}" id="check${pList.p_id}"></td>
						<td width="120" style="border-bottom: 1px solid #444444"><img
							src="img/GOLFCC/${pList.loc_id}/${pList.cc_img}"
							onerror="this.src='<c:url value="img/GOLFCC/noimg.jpg"/>'" border="0"
							align="middle" width="120" height="80" /></td>
						<td style="border-bottom: 1px solid #444444">
							<table>
								<tr>
									<td><a href="ProductRetrieve?p_id=${pList.p_id}"><b>${pList.cc_name}</b></a></td>
									<td style="width: 220px"><span>${pList.p_maxpeople}명 
										<!-- <script>
											$("input[name='p_hole']").each(function(idx,ele){
												console.log($(ele).val());
												if($(ele).val()==${pList.p_hole}){
													$(ele).prop("checked",true);
												}
											});
												
										</script> -->
										<input type="radio" name="p_hole" value="18" checked="checked">18홀&nbsp;&nbsp; 
										<input type="radio"	name="p_hole" value="27">27홀&nbsp;&nbsp;
										<input type="radio"	name="p_hole" value="36">36홀<br>${pList.p_hole}홀</span></td>
								</tr>
								<tr>
									<td><font size="2" color="#4374D9">${pList.p_uploaddate}</font></td>
									<td>
										<font size="2" color="#665b5f">캐디유무 :</font>
										<input type="radio" name="p_caddyyn" value="Y" checked="checked">캐디&nbsp;&nbsp; 
										<input type="radio" name="p_caddyyn" value="N">노캐디
											${pList.p_caddyyn} <br>
										<font size="2" color="#665b5f">식사유무 : </font>
										<input type="checkbox" name="p_babyn" style="width: 30px">
										${pList.p_babyn}
										<br>
										<font size="2" color="#665b5f"> 카트유무 : </font>
										<input type="checkbox" name="p_cartyn" style="width: 30px">
											${pList.p_cartyn}</td>
								</tr>
							</table>
						</td >
						<td style="border-bottom: 1px solid #444444">
							<input type="datetime-local" id="p_pdate" name="p_pdate"  max="2020-01-01T00:00" pattern="" required="required" value="${pList.p_pdate}">
							<br><b>${pList.p_pdate}</b><br>${pList.nickname}
							☎${pList.phone_id}</td>
						<td align="center" style="border-bottom: 1px solid #444444">
							<input type="number" min="1" name="p_price" style="width:50px" required="required" value="${pList.p_price}"> 만원<br> 
							<c:if test="${pList.emergency eq '긴급'}">
								<b style="color: red">[${pList.emergency}]</b>
							</c:if></td>
					</tr>
				</c:forEach>

				<tr>
					<td colspan="5" align="center" style="border-bottom: 1px solid #444444">
						<ul class="pagination justify-content-center" style="margin-top:10px">
						<c:set var="curPage" value="${curPage+1}" /> <%-- 1 --%> 
						<c:set var="maxBlock" value="${maxBlock}" />
						<c:set var="minBlock" value="${minBlock+1}" /> 
						<c:if test="${curPage != 1}">
							<li class="page-item"><a class="page-link" href="productList?curPage=1"><<</a></li>
								<c:if test="${curPage>showBlock}">
									<li class="page-item"><a class="page-link" href="productList?curPage=${minBlock-1}"><</a></li>
								</c:if>
						</c:if>
						<c:forEach var="i" begin="${minBlock}" end="${maxBlock}" step="1">
							<c:choose>
								<c:when test="${curPage eq i}">
									<li class="page-item disabled"><a class="page-link" href="productList?curPage=${i}">${i}</a></li>
								</c:when>
								<c:when test="${curPage != i}">
									<li class="page-item"><a class="page-link" href="productList?curPage=${i}">${i}</a></li>
								</c:when>
							</c:choose>
						</c:forEach>
						<c:if test="${curPage != totalPage}">
							<c:if test="${curPage<=showBlock*perBlock}">
								<li class="page-item"><a class="page-link" href="productList?curPage=${maxBlock+1}">></a></li>
							</c:if>
							<li class="page-item"><a class="page-link" href="productList?curPage=${totalPage}">>></a></li>
						</c:if>
						</ul>
					</td>
				</tr>
			</c:otherwise>
		</c:choose>

	</table>
